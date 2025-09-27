package com.route.model;

import java.util.List;

public class RouteResponse {
    private List<String> optimizedRoute;

    public RouteResponse() {}

    public RouteResponse(List<String> optimizedRoute) {
        this.optimizedRoute = optimizedRoute;
    }

    public List<String> getOptimizedRoute() { return optimizedRoute; }
    public void setOptimizedRoute(List<String> optimizedRoute) { this.optimizedRoute = optimizedRoute; }
}
