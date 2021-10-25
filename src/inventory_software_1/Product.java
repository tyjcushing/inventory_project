package inventory_software_1;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tyler Cushing
 */
public class Product extends Part{
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private final IntegerProperty productID;
    private final StringProperty productName;
    private final DoubleProperty productPrice;
    private final IntegerProperty productStock;
    private final IntegerProperty productMin;
    private final IntegerProperty productMax;

    public Product(int productID, String name, double price, int stock, int min, int max) {
        this.productID = new SimpleIntegerProperty(productID);
        this.productName = new SimpleStringProperty(name);
        this.productPrice = new SimpleDoubleProperty(price);
        this.productStock = new SimpleIntegerProperty(stock);
        this.productMin = new SimpleIntegerProperty(min);
        this.productMax = new SimpleIntegerProperty(max);
    }

    public Product() {
        this.productID = new SimpleIntegerProperty(0);
        this.productName = new SimpleStringProperty("");
        this.productPrice = new SimpleDoubleProperty(0);
        this.productStock = new SimpleIntegerProperty(0);
        this.productMin = new SimpleIntegerProperty(0);
        this.productMax = new SimpleIntegerProperty(0);
    }

    //productID
    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public int getProductID() {
        return this.productID.get();
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }
    
    //productName
    @Override
     public void setName(String name) {
        this.productName.set(name);
    }

    @Override
    public String getName() {
        return this.productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }
    
    //productPrice
    @Override
    public void setPrice(double price) {
        this.productPrice.set(price);
    }

    @Override
    public double getPrice() {
        return this.productPrice.get();
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }

    //productInStock
    public void setStock(int inStock) {
        this.productStock.set(inStock);
    }

    public int getStock() {
        return this.productStock.get();
    }

    public IntegerProperty productStockProperty() {
        return productStock;
    }
    
    //productMin
    @Override
    public void setMin(int min) {
        this.productMin.set(min);
    }
    
    @Override
    public int getMin() {
        return this.productMin.get();
    }

    public IntegerProperty productMinProperty() {
        return productMin;
    }
    
    //productMax
    @Override
     public void setMax(int max) {
        this.productMax.set(max);
    }

    @Override
    public int getMax() {
        return this.productMax.get();
    }
    
    public IntegerProperty productMaxProperty() {
        return productMax;
    }
    
    //associatedParts
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
    
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
    
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}

