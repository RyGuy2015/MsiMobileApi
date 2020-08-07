package com.dpslink.mobileapi.web.rest;

import com.dpslink.mobileapi.domain.Stop;
import com.dpslink.mobileapi.repository.StopRepository;
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
 * REST controller for managing {@link com.dpslink.mobileapi.domain.Stop}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StopResource {

    private final Logger log = LoggerFactory.getLogger(StopResource.class);

    private static final String ENTITY_NAME = "stop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StopRepository stopRepository;

    public StopResource(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    /**
     * {@code POST  /stops} : Create a new stop.
     *
     * @param stop the stop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stop, or with status {@code 400 (Bad Request)} if the stop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stops")
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop) throws URISyntaxException {
        log.debug("REST request to save Stop : {}", stop);
        if (stop.getId() != null) {
            throw new BadRequestAlertException("A new stop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Stop result = stopRepository.save(stop);
        return ResponseEntity.created(new URI("/api/stops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stops} : Updates an existing stop.
     *
     * @param stop the stop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stop,
     * or with status {@code 400 (Bad Request)} if the stop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stops")
    public ResponseEntity<Stop> updateStop(@RequestBody Stop stop) throws URISyntaxException {
        log.debug("REST request to update Stop : {}", stop);
        if (stop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Stop result = stopRepository.save(stop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stop.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stops} : get all the stops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stops in body.
     */
    @GetMapping("/stops")
    public List<Stop> getAllStops() {
        log.debug("REST request to get all Stops");
        return stopRepository.findAll();
    }

    /**
     * {@code GET  /stops/:id} : get the "id" stop.
     *
     * @param id the id of the stop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stops/{id}")
    public ResponseEntity<Stop> getStop(@PathVariable Long id) {
        log.debug("REST request to get Stop : {}", id);
        Optional<Stop> stop = stopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(stop);
    }

    /**
     * {@code DELETE  /stops/:id} : delete the "id" stop.
     *
     * @param id the id of the stop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stops/{id}")
    public ResponseEntity<Void> deleteStop(@PathVariable Long id) {
        log.debug("REST request to delete Stop : {}", id);
        stopRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
