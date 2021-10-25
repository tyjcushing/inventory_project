package inventory_software_1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tyler Cushing
 */
public class InHouse extends Part {
    private final IntegerProperty machineID;
    
    public InHouse(int partID, String name, double price, int inStock, int min, int max, int machineID) {
        this.partId = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }

    public InHouse() {
        this.partId = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.machineID = new SimpleIntegerProperty(0);
    }
    
    //machineID
    public int getMachineID() {
        return this.machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public IntegerProperty machineIDProperty(){
        return machineID;
    }
}
