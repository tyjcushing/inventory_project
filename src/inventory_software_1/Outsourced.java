package inventory_software_1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
public class Outsourced extends Part{
    private final StringProperty companyName;

    public Outsourced(int partID, String name, double price, int inStock, int min, int max, String companyName) {
        this.companyName = new SimpleStringProperty(companyName);
        this.stock = new SimpleIntegerProperty(inStock);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        this.name = new SimpleStringProperty(name);
        this.partId = new SimpleIntegerProperty(partID);
        this.price = new SimpleDoubleProperty(price);
    }

    public Outsourced() {
        this.companyName = new SimpleStringProperty("");
        this.stock = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.partId = new SimpleIntegerProperty(0);
        this.price = new SimpleDoubleProperty(0);
    }

    public String getCompanyName() {
        return this.companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    public StringProperty companyNameProperty() {
            return companyName;
    }
}
