package com.dpslink.mobileapi.web.rest;

import com.dpslink.mobileapi.MobileapiApp;
import com.dpslink.mobileapi.domain.UserExtras;
import com.dpslink.mobileapi.repository.UserExtrasRepository;

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
 * Integration tests for the {@link UserExtrasResource} REST controller.
 */
@SpringBootTest(classes = MobileapiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserExtrasResourceIT {

    private static final Integer DEFAULT_CUSTOMER_NUMBER_1 = 1;
    private static final Integer UPDATED_CUSTOMER_NUMBER_1 = 2;

    private static final Integer DEFAULT_CUSTOMER_NUMBER_2 = 1;
    private static final Integer UPDATED_CUSTOMER_NUMBER_2 = 2;

    private static final String DEFAULT_SALES_REP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_REP_CODE = "BBBBBBBBBB";

    @Autowired
    private UserExtrasRepository userExtrasRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserExtrasMockMvc;

    private UserExtras userExtras;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExtras createEntity(EntityManager em) {
        UserExtras userExtras = new UserExtras()
            .customerNumber1(DEFAULT_CUSTOMER_NUMBER_1)
            .customerNumber2(DEFAULT_CUSTOMER_NUMBER_2)
            .salesRepCode(DEFAULT_SALES_REP_CODE);
        return userExtras;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExtras createUpdatedEntity(EntityManager em) {
        UserExtras userExtras = new UserExtras()
            .customerNumber1(UPDATED_CUSTOMER_NUMBER_1)
            .customerNumber2(UPDATED_CUSTOMER_NUMBER_2)
            .salesRepCode(UPDATED_SALES_REP_CODE);
        return userExtras;
    }

    @BeforeEach
    public void initTest() {
        userExtras = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserExtras() throws Exception {
        int databaseSizeBeforeCreate = userExtrasRepository.findAll().size();
        // Create the UserExtras
        restUserExtrasMockMvc.perform(post("/api/user-extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userExtras)))
            .andExpect(status().isCreated());

        // Validate the UserExtras in the database
        List<UserExtras> userExtrasList = userExtrasRepository.findAll();
        assertThat(userExtrasList).hasSize(databaseSizeBeforeCreate + 1);
        UserExtras testUserExtras = userExtrasList.get(userExtrasList.size() - 1);
        assertThat(testUserExtras.getCustomerNumber1()).isEqualTo(DEFAULT_CUSTOMER_NUMBER_1);
        assertThat(testUserExtras.getCustomerNumber2()).isEqualTo(DEFAULT_CUSTOMER_NUMBER_2);
        assertThat(testUserExtras.getSalesRepCode()).isEqualTo(DEFAULT_SALES_REP_CODE);
    }

    @Test
    @Transactional
    public void createUserExtrasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userExtrasRepository.findAll().size();

        // Create the UserExtras with an existing ID
        userExtras.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserExtrasMockMvc.perform(post("/api/user-extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userExtras)))
            .andExpect(status().isBadRequest());

        // Validate the UserExtras in the database
        List<UserExtras> userExtrasList = userExtrasRepository.findAll();
        assertThat(userExtrasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserExtras() throws Exception {
        // Initialize the database
        userExtrasRepository.saveAndFlush(userExtras);

        // Get all the userExtrasList
        restUserExtrasMockMvc.perform(get("/api/user-extras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userExtras.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerNumber1").value(hasItem(DEFAULT_CUSTOMER_NUMBER_1)))
            .andExpect(jsonPath("$.[*].customerNumber2").value(hasItem(DEFAULT_CUSTOMER_NUMBER_2)))
            .andExpect(jsonPath("$.[*].salesRepCode").value(hasItem(DEFAULT_SALES_REP_CODE)));
    }
    
    @Test
    @Transactional
    public void getUserExtras() throws Exception {
        // Initialize the database
        userExtrasRepository.saveAndFlush(userExtras);

        // Get the userExtras
        restUserExtrasMockMvc.perform(get("/api/user-extras/{id}", userExtras.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userExtras.getId().intValue()))
            .andExpect(jsonPath("$.customerNumber1").value(DEFAULT_CUSTOMER_NUMBER_1))
            .andExpect(jsonPath("$.customerNumber2").value(DEFAULT_CUSTOMER_NUMBER_2))
            .andExpect(jsonPath("$.salesRepCode").value(DEFAULT_SALES_REP_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingUserExtras() throws Exception {
        // Get the userExtras
        restUserExtrasMockMvc.perform(get("/api/user-extras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserExtras() throws Exception {
        // Initialize the database
        userExtrasRepository.saveAndFlush(userExtras);

        int databaseSizeBeforeUpdate = userExtrasRepository.findAll().size();

        // Update the userExtras
        UserExtras updatedUserExtras = userExtrasRepository.findById(userExtras.getId()).get();
        // Disconnect from session so that the updates on updatedUserExtras are not directly saved in db
        em.detach(updatedUserExtras);
        updatedUserExtras
            .customerNumber1(UPDATED_CUSTOMER_NUMBER_1)
            .customerNumber2(UPDATED_CUSTOMER_NUMBER_2)
            .salesRepCode(UPDATED_SALES_REP_CODE);

        restUserExtrasMockMvc.perform(put("/api/user-extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserExtras)))
            .andExpect(status().isOk());

        // Validate the UserExtras in the database
        List<UserExtras> userExtrasList = userExtrasRepository.findAll();
        assertThat(userExtrasList).hasSize(databaseSizeBeforeUpdate);
        UserExtras testUserExtras = userExtrasList.get(userExtrasList.size() - 1);
        assertThat(testUserExtras.getCustomerNumber1()).isEqualTo(UPDATED_CUSTOMER_NUMBER_1);
        assertThat(testUserExtras.getCustomerNumber2()).isEqualTo(UPDATED_CUSTOMER_NUMBER_2);
        assertThat(testUserExtras.getSalesRepCode()).isEqualTo(UPDATED_SALES_REP_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserExtras() throws Exception {
        int databaseSizeBeforeUpdate = userExtrasRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserExtrasMockMvc.perform(put("/api/user-extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userExtras)))
            .andExpect(status().isBadRequest());

        // Validate the UserExtras in the database
        List<UserExtras> userExtrasList = userExtrasRepository.findAll();
        assertThat(userExtrasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserExtras() throws Exception {
        // Initialize the database
        userExtrasRepository.saveAndFlush(userExtras);

        int databaseSizeBeforeDelete = userExtrasRepository.findAll().size();

        // Delete the userExtras
        restUserExtrasMockMvc.perform(delete("/api/user-extras/{id}", userExtras.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserExtras> userExtrasList = userExtrasRepository.findAll();
        assertThat(userExtrasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
