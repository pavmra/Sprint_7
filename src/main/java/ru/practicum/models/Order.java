package ru.practicum.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private Integer id;
    private Integer courierId;
    private Integer track;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private String[] colors;


    public Integer getCourierId() {
        return courierId;
    }

    public Order withCourierId(Integer courierId) {
        this.courierId = courierId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Order withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTrack() {
        return track;
    }

    public Order withTrack(Integer track) {
        this.track = track;
        return this;

    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public String[] getColor() {
        return color;

    }

    public String[] getColors() {
        return colors;
    }

    // With-методы (fluent interface)
    public Order withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Order withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Order withAddress(String address) {
        this.address = address;
        return this;
    }

    public Order withMetroStation(String metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public Order withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Order withRentTime(int rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public Order withDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public Order withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Order withColor(String[] color) {
        this.color = color;
        return this;

    }

    public Order withColors(String[] colors) {
        this.colors = colors;
        return this;
    }
}