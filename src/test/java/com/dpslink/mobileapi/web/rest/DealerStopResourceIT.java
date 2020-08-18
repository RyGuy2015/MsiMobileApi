package com.dpslink.mobileapi.web.rest;

import com.dpslink.mobileapi.MobileapiApp;
import com.dpslink.mobileapi.domain.DealerStop;
import com.dpslink.mobileapi.repository.DealerStopRepository;

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
 * Integration tests for the {@link DealerStopResource} REST controller.
 */
@SpringBootTest(classes = MobileapiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealerStopResourceIT {

    private static final Integer DEFAULT_ROUTE_NUMBER = 1;
    private static final Integer UPDATED_ROUTE_NUMBER = 2;

    private static final Integer DEFAULT_STOP_NUMBER = 1;
    private static final Integer UPDATED_STOP_NUMBER = 2;

    private static final Integer DEFAULT_SEQUENCE_NUMBER = 1;
    private static final Integer UPDATED_SEQUENCE_NUMBER = 2;

    private static final String DEFAULT_SALES_REP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_REP_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMER_NUMBER_1 = 1;
    private static final Integer UPDATED_CUSTOMER_NUMBER_1 = 2;

    private static final Integer DEFAULT_CUSTOMER_NUMBER_2 = 1;
    private static final Integer UPDATED_CUSTOMER_NUMBER_2 = 2;

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private DealerStopRepository dealerStopRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealerStopMockMvc;

    private DealerStop dealerStop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealerStop createEntity(EntityManager em) {
        DealerStop dealerStop = new DealerStop()
            .routeNumber(DEFAULT_ROUTE_NUMBER)
            .stopNumber(DEFAULT_STOP_NUMBER)
            .sequenceNumber(DEFAULT_SEQUENCE_NUMBER)
            .salesRepCode(DEFAULT_SALES_REP_CODE)
            .customerNumber1(DEFAULT_CUSTOMER_NUMBER_1)
            .customerNumber2(DEFAULT_CUSTOMER_NUMBER_2)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerAddress(DEFAULT_CUSTOMER_ADDRESS)
            .status(DEFAULT_STATUS);
        return dealerStop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealerStop createUpdatedEntity(EntityManager em) {
        DealerStop dealerStop = new DealerStop()
            .routeNumber(UPDATED_ROUTE_NUMBER)
            .stopNumber(UPDATED_STOP_NUMBER)
            .sequenceNumber(UPDATED_SEQUENCE_NUMBER)
            .salesRepCode(UPDATED_SALES_REP_CODE)
            .customerNumber1(UPDATED_CUSTOMER_NUMBER_1)
            .customerNumber2(UPDATED_CUSTOMER_NUMBER_2)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .status(UPDATED_STATUS);
        return dealerStop;
    }

    @BeforeEach
    public void initTest() {
        dealerStop = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealerStop() throws Exception {
        int databaseSizeBeforeCreate = dealerStopRepository.findAll().size();
        // Create the DealerStop
        restDealerStopMockMvc.perform(post("/api/dealer-stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealerStop)))
            .andExpect(status().isCreated());

        // Validate the DealerStop in the database
        List<DealerStop> dealerStopList = dealerStopRepository.findAll();
        assertThat(dealerStopList).hasSize(databaseSizeBeforeCreate + 1);
        DealerStop testDealerStop = dealerStopList.get(dealerStopList.size() - 1);
        assertThat(testDealerStop.getRouteNumber()).isEqualTo(DEFAULT_ROUTE_NUMBER);
        assertThat(testDealerStop.getStopNumber()).isEqualTo(DEFAULT_STOP_NUMBER);
        assertThat(testDealerStop.getSequenceNumber()).isEqualTo(DEFAULT_SEQUENCE_NUMBER);
        assertThat(testDealerStop.getSalesRepCode()).isEqualTo(DEFAULT_SALES_REP_CODE);
        assertThat(testDealerStop.getCustomerNumber1()).isEqualTo(DEFAULT_CUSTOMER_NUMBER_1);
        assertThat(testDealerStop.getCustomerNumber2()).isEqualTo(DEFAULT_CUSTOMER_NUMBER_2);
        assertThat(testDealerStop.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testDealerStop.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
        assertThat(testDealerStop.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createDealerStopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealerStopRepository.findAll().size();

        // Create the DealerStop with an existing ID
        dealerStop.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealerStopMockMvc.perform(post("/api/dealer-stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealerStop)))
            .andExpect(status().isBadRequest());

        // Validate the DealerStop in the database
        List<DealerStop> dealerStopList = dealerStopRepository.findAll();
        assertThat(dealerStopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDealerStops() throws Exception {
        // Initialize the database
        dealerStopRepository.saveAndFlush(dealerStop);

        // Get all the dealerStopList
        restDealerStopMockMvc.perform(get("/api/dealer-stops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealerStop.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeNumber").value(hasItem(DEFAULT_ROUTE_NUMBER)))
            .andExpect(jsonPath("$.[*].stopNumber").value(hasItem(DEFAULT_STOP_NUMBER)))
            .andExpect(jsonPath("$.[*].sequenceNumber").value(hasItem(DEFAULT_SEQUENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].salesRepCode").value(hasItem(DEFAULT_SALES_REP_CODE)))
            .andExpect(jsonPath("$.[*].customerNumber1").value(hasItem(DEFAULT_CUSTOMER_NUMBER_1)))
            .andExpect(jsonPath("$.[*].customerNumber2").value(hasItem(DEFAULT_CUSTOMER_NUMBER_2)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].customerAddress").value(hasItem(DEFAULT_CUSTOMER_ADDRESS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getDealerStop() throws Exception {
        // Initialize the database
        dealerStopRepository.saveAndFlush(dealerStop);

        // Get the dealerStop
        restDealerStopMockMvc.perform(get("/api/dealer-stops/{id}", dealerStop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealerStop.getId().intValue()))
            .andExpect(jsonPath("$.routeNumber").value(DEFAULT_ROUTE_NUMBER))
            .andExpect(jsonPath("$.stopNumber").value(DEFAULT_STOP_NUMBER))
            .andExpect(jsonPath("$.sequenceNumber").value(DEFAULT_SEQUENCE_NUMBER))
            .andExpect(jsonPath("$.salesRepCode").value(DEFAULT_SALES_REP_CODE))
            .andExpect(jsonPath("$.customerNumber1").value(DEFAULT_CUSTOMER_NUMBER_1))
            .andExpect(jsonPath("$.customerNumber2").value(DEFAULT_CUSTOMER_NUMBER_2))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.customerAddress").value(DEFAULT_CUSTOMER_ADDRESS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingDealerStop() throws Exception {
        // Get the dealerStop
        restDealerStopMockMvc.perform(get("/api/dealer-stops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealerStop() throws Exception {
        // Initialize the database
        dealerStopRepository.saveAndFlush(dealerStop);

        int databaseSizeBeforeUpdate = dealerStopRepository.findAll().size();

        // Update the dealerStop
        DealerStop updatedDealerStop = dealerStopRepository.findById(dealerStop.getId()).get();
        // Disconnect from session so that the updates on updatedDealerStop are not directly saved in db
        em.detach(updatedDealerStop);
        updatedDealerStop
            .routeNumber(UPDATED_ROUTE_NUMBER)
            .stopNumber(UPDATED_STOP_NUMBER)
            .sequenceNumber(UPDATED_SEQUENCE_NUMBER)
            .salesRepCode(UPDATED_SALES_REP_CODE)
            .customerNumber1(UPDATED_CUSTOMER_NUMBER_1)
            .customerNumber2(UPDATED_CUSTOMER_NUMBER_2)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .status(UPDATED_STATUS);

        restDealerStopMockMvc.perform(put("/api/dealer-stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDealerStop)))
            .andExpect(status().isOk());

        // Validate the DealerStop in the database
        List<DealerStop> dealerStopList = dealerStopRepository.findAll();
        assertThat(dealerStopList).hasSize(databaseSizeBeforeUpdate);
        DealerStop testDealerStop = dealerStopList.get(dealerStopList.size() - 1);
        assertThat(testDealerStop.getRouteNumber()).isEqualTo(UPDATED_ROUTE_NUMBER);
        assertThat(testDealerStop.getStopNumber()).isEqualTo(UPDATED_STOP_NUMBER);
        assertThat(testDealerStop.getSequenceNumber()).isEqualTo(UPDATED_SEQUENCE_NUMBER);
        assertThat(testDealerStop.getSalesRepCode()).isEqualTo(UPDATED_SALES_REP_CODE);
        assertThat(testDealerStop.getCustomerNumber1()).isEqualTo(UPDATED_CUSTOMER_NUMBER_1);
        assertThat(testDealerStop.getCustomerNumber2()).isEqualTo(UPDATED_CUSTOMER_NUMBER_2);
        assertThat(testDealerStop.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testDealerStop.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testDealerStop.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDealerStop() throws Exception {
        int databaseSizeBeforeUpdate = dealerStopRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealerStopMockMvc.perform(put("/api/dealer-stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealerStop)))
            .andExpect(status().isBadRequest());

        // Validate the DealerStop in the database
        List<DealerStop> dealerStopList = dealerStopRepository.findAll();
        assertThat(dealerStopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealerStop() throws Exception {
        // Initialize the database
        dealerStopRepository.saveAndFlush(dealerStop);

        int databaseSizeBeforeDelete = dealerStopRepository.findAll().size();

        // Delete the dealerStop
        restDealerStopMockMvc.perform(delete("/api/dealer-stops/{id}", dealerStop.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealerStop> dealerStopList = dealerStopRepository.findAll();
        assertThat(dealerStopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
