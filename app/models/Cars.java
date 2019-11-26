//package model;

public class Cars {
    private String license_plate;
    private int seat_count;
    private String convertible;
    private String rating;
    private String engine_type;
    private String manufacturer;
   

    public Cars() {
    }

    public Cars(String license_plate, int seat_count, String convertible,String rating, String engine_type, String manufacturer) {
        this.license_plate = license_plate;
        this.seat_count = seat_count;
        this.convertible = convertible;
        this.rating = rating;
        this.engine_type = engine_type;
        this.manufacturer = manufacturer;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public int getConvertible() {
        return convertible;
    }

    public void setConvertible(int age) {
        this.age = age;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEngine_type(){
        return engine_type;
    }

    public void setEngine_type(String engine_type){
        this.engine_type = engine_type;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
}