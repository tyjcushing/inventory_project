package inventory_software_1;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tyler Cushing
 */
public abstract class Part {
    protected IntegerProperty partId;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty stock;
    protected IntegerProperty max;
    protected IntegerProperty min;
            
    //partID
    public void setId(int partId) {
        this.partId.set(partId);
    }
    
    public int getId() {
        return this.partId.get();
    }
    
    public IntegerProperty partIdProperty() {
        return partId;
    }
    
    //name
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    //price
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public double getPrice() {
        return this.price.get();
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    
    //inStock
    public void setStock(int stock) {
        this.stock.set(stock);
    }
    
    public int getStock() {
        return this.stock.get();
    }
    
    public IntegerProperty stockProperty() {
        return stock;
    }
    
    //min
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public int getMin() {
        return this.min.get();
    }
    
    public IntegerProperty minProperty() {
        return min;
    }
    
    //max
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public int getMax() {
        return this.max.get();
    }
    
    public IntegerProperty maxProperty() {
        return max;
    }
}
