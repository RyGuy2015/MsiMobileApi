package com.dpslink.mobileapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DealerStop.
 */
@Entity
@Table(name = "dealer_stop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealerStop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "route_number")
    private Integer routeNumber;

    @Column(name = "stop_number")
    private Integer stopNumber;

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    @Column(name = "sales_rep_code")
    private String salesRepCode;

    @Column(name = "customer_number_1")
    private Integer customerNumber1;

    @Column(name = "customer_number_2")
    private Integer customerNumber2;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "status")
    private String status;

    @ManyToOne
//    @JoinColumn(name="delivery_id")
    @JsonIgnoreProperties(value = "dealerstops", allowSetters = true)
    private Delivery delivery;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public DealerStop routeNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
        return this;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Integer getStopNumber() {
        return stopNumber;
    }

    public DealerStop stopNumber(Integer stopNumber) {
        this.stopNumber = stopNumber;
        return this;
    }

    public void setStopNumber(Integer stopNumber) {
        this.stopNumber = stopNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public DealerStop sequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getSalesRepCode() {
        return salesRepCode;
    }

    public DealerStop salesRepCode(String salesRepCode) {
        this.salesRepCode = salesRepCode;
        return this;
    }

    public void setSalesRepCode(String salesRepCode) {
        this.salesRepCode = salesRepCode;
    }

    public Integer getCustomerNumber1() {
        return customerNumber1;
    }

    public DealerStop customerNumber1(Integer customerNumber1) {
        this.customerNumber1 = customerNumber1;
        return this;
    }

    public void setCustomerNumber1(Integer customerNumber1) {
        this.customerNumber1 = customerNumber1;
    }

    public Integer getCustomerNumber2() {
        return customerNumber2;
    }

    public DealerStop customerNumber2(Integer customerNumber2) {
        this.customerNumber2 = customerNumber2;
        return this;
    }

    public void setCustomerNumber2(Integer customerNumber2) {
        this.customerNumber2 = customerNumber2;
    }

    public String getCustomerName() {
        return customerName;
    }

    public DealerStop customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public DealerStop customerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getStatus() {
        return status;
    }

    public DealerStop status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public DealerStop delivery(Delivery delivery) {
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
        if (!(o instanceof DealerStop)) {
            return false;
        }
        return id != null && id.equals(((DealerStop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealerStop{" +
            "id=" + getId() +
            ", routeNumber=" + getRouteNumber() +
            ", stopNumber=" + getStopNumber() +
            ", sequenceNumber=" + getSequenceNumber() +
            ", salesRepCode='" + getSalesRepCode() + "'" +
            ", customerNumber1=" + getCustomerNumber1() +
            ", customerNumber2=" + getCustomerNumber2() +
            ", customerName='" + getCustomerName() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
