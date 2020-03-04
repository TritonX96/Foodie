package com.isaac.foodie;

public class RestaurantDetails {
        //private UUID mId;
        private String mName;
        private String mLocation;
        private String mAddress;
        private String mCategory;
        private String mLang;
        private String mLong;

//    public  RestaurantDetails(String name, String location, String address, String category, String lang, String aLong) {
//        mName = name;
//        mLocation = location;
//        mAddress = address;
//        mCategory = category;
//        mLang = lang;
//        mLong = aLong;
//    }

    //    public UUID getId() {
//        return mId;
//    }
//
//    public void setId(UUID id) {
//        mId = id;
//    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        mLang = lang;
    }

    public String getLong() {
        return mLong;
    }

    public void setLong(String aLong) {
        mLong = aLong;
    }
}
