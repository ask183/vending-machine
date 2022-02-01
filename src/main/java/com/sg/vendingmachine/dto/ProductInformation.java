/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author abekoppal
 */
public class ProductInformation {
    
    private String name;
    private String cost;
    private int inventoryLeft;

    public ProductInformation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getInventoryLeft() {
        return inventoryLeft;
    }

    public void setInventoryLeft(int inventoryLeft) {
        this.inventoryLeft = inventoryLeft;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.cost);
        hash = 79 * hash + Objects.hashCode(this.inventoryLeft);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductInformation other = (ProductInformation) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return Objects.equals(this.inventoryLeft, other.inventoryLeft);
    }

    @Override
    public String toString() {
        return "ProductInformation{" + "name=" + name + ", cost=" + cost + ", inventoryLeft=" + inventoryLeft + '}';
    } 
    
}
