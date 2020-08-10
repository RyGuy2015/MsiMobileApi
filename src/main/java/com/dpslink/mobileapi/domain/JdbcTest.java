package com.dpslink.mobileapi.domain;

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


}
