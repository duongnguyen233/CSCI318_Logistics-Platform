package com.route.model;

import java.util.List;

public class RouteRequest {
    private List<String> addresses;

    public RouteRequest() {}

    public RouteRequest(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() { return addresses; }
    public void setAddresses(List<String> addresses) { this.addresses = addresses; }
}
