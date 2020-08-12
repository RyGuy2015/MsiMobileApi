package com.dpslink.mobileapi.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class JdbcTest {
    public JdbcTest(String status, String warehouse, String deliverynumber) {
        this.status = status;
        this.warehouse = warehouse;
        this.deliverynumber = deliverynumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getDeliverynumber() {
        return deliverynumber;
    }

    public void setDeliverynumber(String deliverynumber) {
        this.deliverynumber = deliverynumber;
    }

    private String status;
    private String warehouse;
    private String deliverynumber;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


}
