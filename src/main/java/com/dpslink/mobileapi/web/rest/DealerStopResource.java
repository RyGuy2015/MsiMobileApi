package com.dpslink.mobileapi.web.rest;

import com.dpslink.mobileapi.domain.DealerStop;
import com.dpslink.mobileapi.repository.DealerStopRepository;
import com.dpslink.mobileapi.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dpslink.mobileapi.domain.DealerStop}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DealerStopResource {

    private final Logger log = LoggerFactory.getLogger(DealerStopResource.class);

    private static final String ENTITY_NAME = "dealerStop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealerStopRepository dealerStopRepository;

    public DealerStopResource(DealerStopRepository dealerStopRepository) {
        this.dealerStopRepository = dealerStopRepository;
    }

    /**
     * {@code POST  /dealer-stops} : Create a new dealerStop.
     *
     * @param dealerStop the dealerStop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealerStop, or with status {@code 400 (Bad Request)} if the dealerStop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dealer-stops")
    public ResponseEntity<DealerStop> createDealerStop(@RequestBody DealerStop dealerStop) throws URISyntaxException {
        log.debug("REST request to save DealerStop : {}", dealerStop);
        if (dealerStop.getId() != null) {
            throw new BadRequestAlertException("A new dealerStop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealerStop result = dealerStopRepository.save(dealerStop);
        return ResponseEntity.created(new URI("/api/dealer-stops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dealer-stops} : Updates an existing dealerStop.
     *
     * @param dealerStop the dealerStop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealerStop,
     * or with status {@code 400 (Bad Request)} if the dealerStop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealerStop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dealer-stops")
    public ResponseEntity<DealerStop> updateDealerStop(@RequestBody DealerStop dealerStop) throws URISyntaxException {
        log.debug("REST request to update DealerStop : {}", dealerStop);
        if (dealerStop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealerStop result = dealerStopRepository.save(dealerStop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dealerStop.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dealer-stops} : get all the dealerStops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealerStops in body.
     */
    @GetMapping("/dealer-stops")
    public List<DealerStop> getAllDealerStops() {
        log.debug("REST request to get all DealerStops");
        return dealerStopRepository.findAll();
    }

    /**
     * {@code GET  /dealer-stops/:id} : get the "id" dealerStop.
     *
     * @param id the id of the dealerStop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealerStop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dealer-stops/{id}")
    public ResponseEntity<DealerStop> getDealerStop(@PathVariable Long id) {
        log.debug("REST request to get DealerStop : {}", id);
        Optional<DealerStop> dealerStop = dealerStopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dealerStop);
    }

    /**
     * {@code GET  /dealer-stops/sales-rep/:salesRepCode} : get the sales reps dealer stops.
     *
     * @param salesRepCode the sales rep code of the dealerStops to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealerStop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dealer-stops/sales-rep/{salesRepCode}")
    public ResponseEntity<DealerStop> getDealerStopBySalesRep(@PathVariable String salesRepCode) {
        log.debug("REST request to get DealerStop : {}", salesRepCode);
        Optional<DealerStop> dealerStop = dealerStopRepository.findBySalesRepCode(salesRepCode);
        return ResponseUtil.wrapOrNotFound(dealerStop);
    }

    /**
     * {@code GET  /dealer-stops/delivery-id/:deliveryId} : get the delivery dealer stops.
     *
     * @param deliveryId the sales rep code of the dealerStops to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealerStop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dealer-stops/delivery-id/{deliveryId}")
    public List<DealerStop> getDealerStopByDeliveryId(@PathVariable Long deliveryId) {
        log.debug("REST request to get DealerStop by delivery id : {}", deliveryId);
        List<DealerStop> dealerStop = dealerStopRepository.findByCurrentDelivery(deliveryId);
//        return ResponseUtil.wrapOrNotFound(dealerStop);
        return dealerStop;
    }

    /**
     * {@code GET  /dealer-stops/customer/:customerNumber1/:customerNumber2} : get the "customer" dealer stops.
     *
     * @param customerNumber1 part one of the customer number associated with the stop.
     * @param customerNumber2 part two of the customer number associated with the stop
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealerStop, or with status {@code 404 (Not Found)}.
     */
    @RequestMapping(path="/dealer-stops/customer/{customerNumber1}/{customerNumber2}", method = RequestMethod.GET)
    public ResponseEntity<DealerStop> getDealerStopsByCustomer(@PathVariable Integer customerNumber1, @PathVariable Integer customerNumber2) {
        log.debug("REST request to get DealerStop : {} : {}", customerNumber1, customerNumber2);
        Optional<DealerStop> dealerStop = dealerStopRepository.findByCustomerNumber(customerNumber1, customerNumber2);
        return ResponseUtil.wrapOrNotFound(dealerStop);
    }

    /**
     * {@code DELETE  /dealer-stops/:id} : delete the "id" dealerStop.
     *
     * @param id the id of the dealerStop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dealer-stops/{id}")
    public ResponseEntity<Void> deleteDealerStop(@PathVariable Long id) {
        log.debug("REST request to delete DealerStop : {}", id);
        dealerStopRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
