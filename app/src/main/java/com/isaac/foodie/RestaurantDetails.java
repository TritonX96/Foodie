package com.isaac.foodie;

public class RestaurantDetails {
        //private UUID mId;
        private String name;
        private String location;
        private String address;
        private String category;
        private String image;
//        private String lang;
//        private String lon;

    public RestaurantDetails(String name, String location, String address, String category, String image) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.category = category;
//        this.lang = lang;
//        this.lon = lon;
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

//    public String getLang() {
//        return lang;
//    }
//
//    public void setLang(String lang) {
//        this.lang = lang;
//    }
//
//    public String getLon() {
//        return lon;
//    }
//
//    public void setLon(String lon) {
//        this.lon = lon;
//    }

}
