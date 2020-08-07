package com.dpslink.mobileapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Stop.
 */
@Entity
@Table(name = "stop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "route_number")
    private Long routeNumber;

    @Column(name = "stop_number")
    private Long stopNumber;

    @Column(name = "customer_number_1")
    private Long customerNumber1;

    @Column(name = "customer_number_2")
    private Long customerNumber2;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @ManyToOne
    @JsonIgnoreProperties(value = "stops", allowSetters = true)
    private Delivery delivery;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteNumber() {
        return routeNumber;
    }

    public Stop routeNumber(Long routeNumber) {
        this.routeNumber = routeNumber;
        return this;
    }

    public void setRouteNumber(Long routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Long getStopNumber() {
        return stopNumber;
    }

    public Stop stopNumber(Long stopNumber) {
        this.stopNumber = stopNumber;
        return this;
    }

    public void setStopNumber(Long stopNumber) {
        this.stopNumber = stopNumber;
    }

    public Long getCustomerNumber1() {
        return customerNumber1;
    }

    public Stop customerNumber1(Long customerNumber1) {
        this.customerNumber1 = customerNumber1;
        return this;
    }

    public void setCustomerNumber1(Long customerNumber1) {
        this.customerNumber1 = customerNumber1;
    }

    public Long getCustomerNumber2() {
        return customerNumber2;
    }

    public Stop customerNumber2(Long customerNumber2) {
        this.customerNumber2 = customerNumber2;
        return this;
    }

    public void setCustomerNumber2(Long customerNumber2) {
        this.customerNumber2 = customerNumber2;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Stop customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public Stop customerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Stop delivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stop)) {
            return false;
        }
        return id != null && id.equals(((Stop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stop{" +
            "id=" + getId() +
            ", routeNumber=" + getRouteNumber() +
            ", stopNumber=" + getStopNumber() +
            ", customerNumber1=" + getCustomerNumber1() +
            ", customerNumber2=" + getCustomerNumber2() +
            ", customerName='" + getCustomerName() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            "}";
    }
}
