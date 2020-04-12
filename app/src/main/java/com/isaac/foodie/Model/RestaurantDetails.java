package com.isaac.foodie.Model;

public class RestaurantDetails {
        private String name;
        private String location;
        private String address;
        private String category;
        private String image;
        private Double latitude;
        private Double longitude;


    public RestaurantDetails(String name, String location, String address, String category, String image, Double latitude, Double longitude) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.category = category;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public RestaurantDetails() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) { this.image = image; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLatitude() {return latitude;}

    public void setLatitude(Double lang) {this.latitude = latitude;}

    public Double getLongitude() {return longitude;}

    public void setLongitude(Double longitude) {this.longitude = longitude;}



}
