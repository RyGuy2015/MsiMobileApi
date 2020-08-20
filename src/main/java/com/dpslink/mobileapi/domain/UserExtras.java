package com.dpslink.mobileapi.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserExtras.
 */
@Entity
@Table(name = "user_extras")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserExtras implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "customer_number_1")
    private Integer customerNumber1;

    @Column(name = "customer_number_2")
    private Integer customerNumber2;

    @Column(name = "sales_rep_code")
    private String salesRepCode;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerNumber1() {
        return customerNumber1;
    }

    public UserExtras customerNumber1(Integer customerNumber1) {
        this.customerNumber1 = customerNumber1;
        return this;
    }

    public void setCustomerNumber1(Integer customerNumber1) {
        this.customerNumber1 = customerNumber1;
    }

    public Integer getCustomerNumber2() {
        return customerNumber2;
    }

    public UserExtras customerNumber2(Integer customerNumber2) {
        this.customerNumber2 = customerNumber2;
        return this;
    }

    public void setCustomerNumber2(Integer customerNumber2) {
        this.customerNumber2 = customerNumber2;
    }

    public String getSalesRepCode() {
        return salesRepCode;
    }

    public UserExtras salesRepCode(String salesRepCode) {
        this.salesRepCode = salesRepCode;
        return this;
    }

    public void setSalesRepCode(String salesRepCode) {
        this.salesRepCode = salesRepCode;
    }

    public User getUser() {
        return user;
    }

    public UserExtras user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtras)) {
            return false;
        }
        return id != null && id.equals(((UserExtras) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserExtras{" +
            "id=" + getId() +
            ", customerNumber1=" + getCustomerNumber1() +
            ", customerNumber2=" + getCustomerNumber2() +
            ", salesRepCode='" + getSalesRepCode() + "'" +
            "}";
    }
}
