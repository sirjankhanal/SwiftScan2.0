package com.example.swiftscan;

public class Drink {
    private String name;
    private String brand;
    private String price;
    private String description;
    private String ABV;
    private String country;
    private String type;
    private String category;
    private String code;
    private String volume;
    private String quantity;
    private String alcpercentes;
    public static Drink getSample() {
        Drink drink = new Drink();
        drink.name = "Water";
        drink.brand = "None";
        drink.price = "0.00";
        drink.description = "Basic of all life";
        drink.ABV = "Unknown";
        drink.country = "Whole planet";
        drink.type = "Basic";
        drink.category = "Water";
        drink.code = "000";
        drink.volume = "0";
        drink.quantity = "0";
        drink.alcpercentes = "0";
        return drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getABV() {
        return ABV;
    }

    public void setABV(String ABV) {
        this.ABV = ABV;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAlcpercentes() {
        return alcpercentes;
    }

    public void setAlcpercentes(String alcpercentes) {
        this.alcpercentes = alcpercentes;
    }
}
