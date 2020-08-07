package com.dpslink.mobileapi.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Delivery.
 */
@Entity
@Table(name = "delivery")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "delivery_number")
    private String deliveryNumber;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "delivery_warehouse")
    private String deliveryWarehouse;

    @OneToMany(mappedBy = "delivery")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Stop> stops = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public Delivery deliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
        return this;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public Delivery deliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    public Delivery deliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
        return this;
    }

    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    public Set<Stop> getStops() {
        return stops;
    }

    public Delivery stops(Set<Stop> stops) {
        this.stops = stops;
        return this;
    }

    public Delivery addStop(Stop stop) {
        this.stops.add(stop);
        stop.setDelivery(this);
        return this;
    }

    public Delivery removeStop(Stop stop) {
        this.stops.remove(stop);
        stop.setDelivery(null);
        return this;
    }

    public void setStops(Set<Stop> stops) {
        this.stops = stops;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Delivery)) {
            return false;
        }
        return id != null && id.equals(((Delivery) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Delivery{" +
            "id=" + getId() +
            ", deliveryNumber='" + getDeliveryNumber() + "'" +
            ", deliveryStatus='" + getDeliveryStatus() + "'" +
            ", deliveryWarehouse='" + getDeliveryWarehouse() + "'" +
            "}";
    }
}
