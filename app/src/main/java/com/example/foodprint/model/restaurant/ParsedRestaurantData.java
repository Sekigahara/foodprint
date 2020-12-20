package com.example.foodprint.model.restaurant;

import java.io.Serializable;
import java.util.List;

public class ParsedRestaurantData implements Serializable {
    private Double distance;
    private String name;
    private Float rating;
    private Boolean isOpen;
    private String photo;
    private String idGoogle;
    private String id;
    private String vicinity;
    private Integer priceLevel;
    private List<String> types;
    private Double lat;
    private Double lng;

    public Double getLat(){
        return lat;
    }

    public void setLat(Double lat){
        this.lat = lat;
    }

    public Double getLng(){
        return lng;
    }

    public void setLng(Double lng){
        this.lng = lng;
    }

    public List<String> getTypes(){
        return types;
    }

    public void setTypes(List<String> data){
        this.types = data;
    }

    public String getVicinity(){
        return vicinity;
    }

    public void setVicinity(String vicinity){
        this.vicinity = vicinity;
    }

    public Integer getPriceLevel(){
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel){
        this.priceLevel = priceLevel;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
