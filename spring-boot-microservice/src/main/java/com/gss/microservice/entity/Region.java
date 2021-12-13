package com.gss.microservice.entity;

public enum Region {

    CENTRAL_COAST("Central Coast"),
    SOUTHERN_CALIFORNIA("Southern California"),
    NORTHERN_CALIFORNIA("Northern California"),
    VARIES("Varies");

    private String value;

    Region(String value) {
        this.value = value;
    }

    public static Region findByValue(String value) {
        for (Region region : Region.values()) {
            if (region.value.equalsIgnoreCase(value)) {
                return region;
            }
        }
        return null;
    }
}
