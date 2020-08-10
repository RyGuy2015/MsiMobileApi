package com.dpslink.mobileapi.domain;

public class UnparsedDelivery {

    private String status;
    private String warehouse;
    private String deliverynumber;
    private Integer route;
    private Integer routestop;
    private Integer customerpart1;
    private String customername;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String zip;
    private String overname;
    private String overaddressline1;
    private String overaddressline2;
    private String overcity;
    private String overstate;
    private String overzip;

    public UnparsedDelivery(String status, String warehouse, String deliverynumber, Integer route, Integer routestop, Integer customerpart1, Integer customerpart2, String customername, String addressline1, String addressline2, String city, String state, String zip, String overname, String overaddressline1, String overaddressline2, String overcity, String overstate, String overzip) {
        this.status = status;
        this.warehouse = warehouse;
        this.deliverynumber = deliverynumber;
        this.route = route;
        this.routestop = routestop;
        this.customerpart1 = customerpart1;
        this.customerpart2 = customerpart2;
        this.customername = customername;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.overname = overname;
        this.overaddressline1 = overaddressline1;
        this.overaddressline2 = overaddressline2;
        this.overcity = overcity;
        this.overstate = overstate;
        this.overzip = overzip;
    }

    private Integer customerpart2;

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

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }

    public Integer getRoutestop() {
        return routestop;
    }

    public void setRoutestop(Integer routestop) {
        this.routestop = routestop;
    }

    public Integer getCustomerpart1() {
        return customerpart1;
    }

    public void setCustomerpart1(Integer customerpart1) {
        this.customerpart1 = customerpart1;
    }

    public Integer getCustomerpart2() {
        return customerpart2;
    }

    public void setCustomerpart2(Integer customerpart2) {
        this.customerpart2 = customerpart2;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOvername() {
        return overname;
    }

    public void setOvername(String overname) {
        this.overname = overname;
    }

    public String getOveraddressline1() {
        return overaddressline1;
    }

    public void setOveraddressline1(String overaddressline1) {
        this.overaddressline1 = overaddressline1;
    }

    public String getOveraddressline2() {
        return overaddressline2;
    }

    public void setOveraddressline2(String overaddressline2) {
        this.overaddressline2 = overaddressline2;
    }

    public String getOvercity() {
        return overcity;
    }

    public void setOvercity(String overcity) {
        this.overcity = overcity;
    }

    public String getOverstate() {
        return overstate;
    }

    public void setOverstate(String overstate) {
        this.overstate = overstate;
    }

    public String getOverzip() {
        return overzip;
    }

    public void setOverzip(String overzip) {
        this.overzip = overzip;
    }

}
