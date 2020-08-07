package com.dpslink.mobileapi.web.rest;

import com.dpslink.mobileapi.MobileapiApp;
import com.dpslink.mobileapi.domain.Stop;
import com.dpslink.mobileapi.repository.StopRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StopResource} REST controller.
 */
@SpringBootTest(classes = MobileapiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StopResourceIT {

    private static final Long DEFAULT_ROUTE_NUMBER = 1L;
    private static final Long UPDATED_ROUTE_NUMBER = 2L;

    private static final Long DEFAULT_STOP_NUMBER = 1L;
    private static final Long UPDATED_STOP_NUMBER = 2L;

    private static final Long DEFAULT_CUSTOMER_NUMBER_1 = 1L;
    private static final Long UPDATED_CUSTOMER_NUMBER_1 = 2L;

    private static final Long DEFAULT_CUSTOMER_NUMBER_2 = 1L;
    private static final Long UPDATED_CUSTOMER_NUMBER_2 = 2L;

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStopMockMvc;

    private Stop stop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stop createEntity(EntityManager em) {
        Stop stop = new Stop()
            .routeNumber(DEFAULT_ROUTE_NUMBER)
            .stopNumber(DEFAULT_STOP_NUMBER)
            .customerNumber1(DEFAULT_CUSTOMER_NUMBER_1)
            .customerNumber2(DEFAULT_CUSTOMER_NUMBER_2)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerAddress(DEFAULT_CUSTOMER_ADDRESS);
        return stop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stop createUpdatedEntity(EntityManager em) {
        Stop stop = new Stop()
            .routeNumber(UPDATED_ROUTE_NUMBER)
            .stopNumber(UPDATED_STOP_NUMBER)
            .customerNumber1(UPDATED_CUSTOMER_NUMBER_1)
            .customerNumber2(UPDATED_CUSTOMER_NUMBER_2)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS);
        return stop;
    }

    @BeforeEach
    public void initTest() {
        stop = createEntity(em);
    }

    @Test
    @Transactional
    public void createStop() throws Exception {
        int databaseSizeBeforeCreate = stopRepository.findAll().size();
        // Create the Stop
        restStopMockMvc.perform(post("/api/stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stop)))
            .andExpect(status().isCreated());

        // Validate the Stop in the database
        List<Stop> stopList = stopRepository.findAll();
        assertThat(stopList).hasSize(databaseSizeBeforeCreate + 1);
        Stop testStop = stopList.get(stopList.size() - 1);
        assertThat(testStop.getRouteNumber()).isEqualTo(DEFAULT_ROUTE_NUMBER);
        assertThat(testStop.getStopNumber()).isEqualTo(DEFAULT_STOP_NUMBER);
        assertThat(testStop.getCustomerNumber1()).isEqualTo(DEFAULT_CUSTOMER_NUMBER_1);
        assertThat(testStop.getCustomerNumber2()).isEqualTo(DEFAULT_CUSTOMER_NUMBER_2);
        assertThat(testStop.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testStop.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
    }

    @Test
    @Transactional
    public void createStopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stopRepository.findAll().size();

        // Create the Stop with an existing ID
        stop.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopMockMvc.perform(post("/api/stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stop)))
            .andExpect(status().isBadRequest());

        // Validate the Stop in the database
        List<Stop> stopList = stopRepository.findAll();
        assertThat(stopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStops() throws Exception {
        // Initialize the database
        stopRepository.saveAndFlush(stop);

        // Get all the stopList
        restStopMockMvc.perform(get("/api/stops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stop.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeNumber").value(hasItem(DEFAULT_ROUTE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].stopNumber").value(hasItem(DEFAULT_STOP_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].customerNumber1").value(hasItem(DEFAULT_CUSTOMER_NUMBER_1.intValue())))
            .andExpect(jsonPath("$.[*].customerNumber2").value(hasItem(DEFAULT_CUSTOMER_NUMBER_2.intValue())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].customerAddress").value(hasItem(DEFAULT_CUSTOMER_ADDRESS)));
    }
    
    @Test
    @Transactional
    public void getStop() throws Exception {
        // Initialize the database
        stopRepository.saveAndFlush(stop);

        // Get the stop
        restStopMockMvc.perform(get("/api/stops/{id}", stop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stop.getId().intValue()))
            .andExpect(jsonPath("$.routeNumber").value(DEFAULT_ROUTE_NUMBER.intValue()))
            .andExpect(jsonPath("$.stopNumber").value(DEFAULT_STOP_NUMBER.intValue()))
            .andExpect(jsonPath("$.customerNumber1").value(DEFAULT_CUSTOMER_NUMBER_1.intValue()))
            .andExpect(jsonPath("$.customerNumber2").value(DEFAULT_CUSTOMER_NUMBER_2.intValue()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.customerAddress").value(DEFAULT_CUSTOMER_ADDRESS));
    }
    @Test
    @Transactional
    public void getNonExistingStop() throws Exception {
        // Get the stop
        restStopMockMvc.perform(get("/api/stops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStop() throws Exception {
        // Initialize the database
        stopRepository.saveAndFlush(stop);

        int databaseSizeBeforeUpdate = stopRepository.findAll().size();

        // Update the stop
        Stop updatedStop = stopRepository.findById(stop.getId()).get();
        // Disconnect from session so that the updates on updatedStop are not directly saved in db
        em.detach(updatedStop);
        updatedStop
            .routeNumber(UPDATED_ROUTE_NUMBER)
            .stopNumber(UPDATED_STOP_NUMBER)
            .customerNumber1(UPDATED_CUSTOMER_NUMBER_1)
            .customerNumber2(UPDATED_CUSTOMER_NUMBER_2)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS);

        restStopMockMvc.perform(put("/api/stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStop)))
            .andExpect(status().isOk());

        // Validate the Stop in the database
        List<Stop> stopList = stopRepository.findAll();
        assertThat(stopList).hasSize(databaseSizeBeforeUpdate);
        Stop testStop = stopList.get(stopList.size() - 1);
        assertThat(testStop.getRouteNumber()).isEqualTo(UPDATED_ROUTE_NUMBER);
        assertThat(testStop.getStopNumber()).isEqualTo(UPDATED_STOP_NUMBER);
        assertThat(testStop.getCustomerNumber1()).isEqualTo(UPDATED_CUSTOMER_NUMBER_1);
        assertThat(testStop.getCustomerNumber2()).isEqualTo(UPDATED_CUSTOMER_NUMBER_2);
        assertThat(testStop.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testStop.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingStop() throws Exception {
        int databaseSizeBeforeUpdate = stopRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStopMockMvc.perform(put("/api/stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stop)))
            .andExpect(status().isBadRequest());

        // Validate the Stop in the database
        List<Stop> stopList = stopRepository.findAll();
        assertThat(stopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStop() throws Exception {
        // Initialize the database
        stopRepository.saveAndFlush(stop);

        int databaseSizeBeforeDelete = stopRepository.findAll().size();

        // Delete the stop
        restStopMockMvc.perform(delete("/api/stops/{id}", stop.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stop> stopList = stopRepository.findAll();
        assertThat(stopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
