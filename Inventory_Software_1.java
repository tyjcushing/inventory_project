/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_software_1;

import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Tyler
 */
public class Inventory_Software_1 extends Application {
    private Part selectedPart;
    private Product selectedProduct;
    public static boolean isNumber(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        Label title = new Label();
        title.setText("Inventory Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        title.setTextFill(Color.CADETBLUE);
        
        //Sets Part side of Window !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        BorderPane root = new BorderPane();
        GridPane parts = new GridPane();
        parts.setHgap(10);
        parts.setVgap(10);
        parts.setPadding(new Insets(25,25,25,25));
        
        //Defines Content of Parts Side
        Label partTitle = new Label();
        partTitle.setText("Parts");
        partTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        partTitle.setTextFill(Color.CADETBLUE);
        TextField textFieldPart = new TextField();
        
        
        //Parts Table Setup
        Inventory.addPart(new InHouse(0, "Bronze Gear", 5.0, 45, 1, 100, 104));
        Inventory.addPart(new Outsourced(1, "Steel Screw", 0.50, 250, 1, 500, "123 LLC"));
        Inventory.addPart(new InHouse(2, "Spring", 1.0, 5, 1, 100, 197));
        Inventory.addPart(new Outsourced(3, "Power Supply", 30.0, 20, 1, 100, "Part Producers USA"));
        
        TableView<Part> partsTable = new TableView<Part>(Inventory.getPartList());
        TableColumn<Part, String> colPartId = new TableColumn<>("Part ID");
        colPartId.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partsTable.getColumns().add(colPartId);
        
        TableColumn<Part, String> colPartName = new TableColumn<>("Name");
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsTable.getColumns().add(colPartName);
        
        TableColumn<Part, String> colPartStock = new TableColumn<>("Stock Level");
        colPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsTable.getColumns().add(colPartStock);
        
        TableColumn<Part, String> colPartPrice = new TableColumn<>("Price");
        colPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getColumns().add(colPartPrice);
        
        partsTable.setPrefSize(280,130);
        TableView.TableViewSelectionModel<Part> selectedPartTable = partsTable.getSelectionModel();
        
        
        //Buttons and other elements
        Button partSearchBtn = new Button();
        partSearchBtn.setText("Search");
        partSearchBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String searchTerm = textFieldPart.getText();
                ObservableList<Part> foundParts = FXCollections.observableArrayList();

                if(isNumber(searchTerm)) 
                    foundParts = (Inventory.lookupPart(Integer.parseInt(searchTerm)));
                else 
                    foundParts = (Inventory.lookupPart(searchTerm));
                
                if(foundParts.size() > 0)
                    partsTable.setItems(foundParts);
                else {
                    partsTable.setItems(Inventory.getPartList());
                }      
            }
        });

        Button partAddBtn = new Button();
        partAddBtn.setText("Add");
        partAddBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                showPartsScreen("Add");
            }
        });
        
        Button partModifyBtn = new Button();
        partModifyBtn.setText("Modify");
        partModifyBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(selectedPartTable.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("No Part Selected");
                    alert.setHeaderText("No Part Selected");
                    alert.setContentText("Please Select a part to continue");
                    Optional<ButtonType> result = alert.showAndWait();
                } else {
                    selectedPart = selectedPartTable.getSelectedItem(); //_
                    showPartsScreen("Edit");
                }
        }
        });
        
        Button partDeleteBtn = new Button();
        partDeleteBtn.setText("Delete");
        partDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Inventory.removePart(selectedPartTable.getSelectedItem());
                partsTable.setItems(Inventory.getPartList());
            }
        });
        
        //Adds all obejects to grid
        parts.add(partTitle,0,0);
        parts.add(partSearchBtn,2,0);
        parts.add(textFieldPart,3,0);
        parts.add(partsTable,0,1,4,1);
        parts.add(partAddBtn,0,2);
        parts.add(partModifyBtn,2,2);
        parts.add(partDeleteBtn,3,2);
        //Parts DONE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        
        //Defines Content of Products Side
        Label productTitle = new Label();
        productTitle.setText("Products");
        productTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        productTitle.setTextFill(Color.CADETBLUE);
        TextField textFieldProduct = new TextField();
        
        
        //Products Table Setup
        Product prod1 = new Product(0, "Uphone 11", 899.0, 100, 1, 1000);
        Product prod2 = new Product(1, "Clock", 32.10, 20, 1, 150);
        Product prod3 = new Product(2, "Chrome Wheel", 50.00, 45, 1, 100);
        prod1.setAssociatedParts(Inventory.lookupPart(0));
        Inventory.addProduct(prod1);  
        prod2.setAssociatedParts(Inventory.lookupPart(3));
        Inventory.addProduct(prod2);  
        prod3.setAssociatedParts(Inventory.lookupPart(2));
        Inventory.addProduct(prod3);  


        TableView<Product> productsTable = new TableView<Product>(Inventory.getProductList());
        TableColumn<Product, String> colProductId = new TableColumn<>("Prod ID");
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productsTable.getColumns().add(colProductId);
        
        TableColumn<Product, String> colProductName = new TableColumn<>("Name");
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productsTable.getColumns().add(colProductName);
        
        TableColumn<Product, String> colProductStock = new TableColumn<>("Stock Level");
        colProductStock.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        productsTable.getColumns().add(colProductStock);
        
        TableColumn<Product, String> colProductPrice = new TableColumn<>("Price");
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productsTable.getColumns().add(colProductPrice);
        
        productsTable.setPrefSize(180,130);
        TableView.TableViewSelectionModel<Product> selectedProductTable = productsTable.getSelectionModel();
        
        
        //Buttons and other elements
        Button productSearchBtn = new Button();
        productSearchBtn.setText("Search");
        productSearchBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String searchTerm = textFieldProduct.getText();
                ObservableList<Product> foundProducts = FXCollections.observableArrayList();

                if(isNumber(searchTerm)) 
                    foundProducts = (Inventory.lookupProduct(Integer.parseInt(searchTerm)));
                else 
                    foundProducts = (Inventory.lookupProduct(searchTerm));
                
                if(foundProducts.size() > 0)
                    productsTable.setItems(foundProducts);
                else {
                    productsTable.setItems(Inventory.getProductList());
                }      
            }
        });

        Button productAddBtn = new Button();
        productAddBtn.setText("Add");
        productAddBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                showProductsScreen("Add");
            }
        });
        
        Button productModifyBtn = new Button();
        productModifyBtn.setText("Modify");
        productModifyBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(selectedProductTable.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.NONE);
                    alert.setTitle("No Product Selected");
                    alert.setHeaderText("No Product Selected");
                    alert.setContentText("Please Select a product to continue");
                    Optional<ButtonType> result = alert.showAndWait();
                } else {
                    selectedProduct = selectedProductTable.getSelectedItem();
                    showProductsScreen("Edit");
                }
        }
        });
        
        Button productDeleteBtn = new Button();
        productDeleteBtn.setText("Delete");
        productDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Inventory.removeProduct(selectedProductTable.getSelectedItem());
                productsTable.setItems(Inventory.getProductList());
            }
        });
        
        Button exitMain = new Button();
        exitMain.setText("Exit");
        exitMain.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        GridPane products = new GridPane();
        products.setHgap(10);
        products.setVgap(10);
        products.setPadding(new Insets(25,25,25,25));
        
        //Adds all obejects to grid
        products.add(productTitle,0,0);
        products.add(productSearchBtn,2,0);
        products.add(textFieldProduct,3,0);
        products.add(productsTable,0,1,4,1);
        products.add(productAddBtn,0,2);
        products.add(productModifyBtn,2,2);
        products.add(productDeleteBtn,3,2);
        //Products DONE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        //Separator between Parts and Products
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.VERTICAL);   
        BorderPane setRight = new BorderPane();
        
        //Set the Main Scene
        root.setLeft(parts);
        root.setRight(products);
        root.setTop(title);
        root.setCenter(hSeparator);
        root.setBottom(setRight);
        setRight.setRight(exitMain);
        
        Scene scene = new Scene(root, 800, 300);
        
        primaryStage.setTitle("Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
         
     public void showPartsScreen(String addOrEdit) {	
        Stage stage = new Stage();
 
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10,10,10,10));
        form.setPrefSize(800,800);
        
        Label title = new Label();
        title.setText(addOrEdit + " Part");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        title.setTextFill(Color.CADETBLUE);        
        
        Label iDLabel = new Label();
	iDLabel.setText("ID");
        TextField iDField = new TextField();
        iDField.setEditable(false);
        iDField.setDisable(true);
        iDField.setPromptText("Auto Gen ID");
        
	Label nameLabel = new Label();
	nameLabel.setText("Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Part Name");
		
	Label stockLabel = new Label();
	stockLabel.setText("Stock");
	TextField stockField = new TextField();
        stockField.setPromptText("Stock");
		
	Label priceLabel = new Label();
	priceLabel.setText("Price");
	TextField priceField = new TextField();
        priceField.setPromptText("Price Per Unit");
		
	Label minLabel = new Label();
	minLabel.setText("Min");
	TextField minField = new TextField();
        minField.setPromptText("Minium Stock");
		
	Label maxLabel = new Label();
	maxLabel.setText("Max");
	TextField maxField = new TextField();
        maxField.setPromptText("Maxium Stock");
		
	Label otherLabel = new Label();
	otherLabel.setText("Machine ID     ");
	TextField otherField = new TextField();
        otherField.setPromptText("Machine ID");
        
        Button inhouseBtn = new Button();
        inhouseBtn.setText("Inhouse");
        inhouseBtn.setOnAction(new EventHandler<ActionEvent>() {      
        @Override
        public void handle(ActionEvent event) {
            if(inhouseBtn.getText().equals("Outsourced")) {  
                otherLabel.setText("Machine ID     ");
                otherField.setPromptText("Machine ID");
                inhouseBtn.setText("Inhouse");
            } else {
                otherLabel.setText("Company");
                otherField.setPromptText("Company Name");
                inhouseBtn.setText("Outsourced");
            }
            }
        });
        
        Button savePartBtn = new Button();
        savePartBtn.setText("Save");
        savePartBtn.setOnAction(new EventHandler<ActionEvent>() {      
        @Override
        public void handle(ActionEvent event) {
            String errorMessage="";
            Integer Min=null,Max=null,Inv;
            Double partPrice;
            Boolean valid;
            
            //Part Name Check
            if(nameField.getText() == null || nameField.getText().length() == 0) {
                errorMessage += ("Invalid, 'Part Name' is empty\n");
            }
            
            //Checks Price
            try {
            partPrice = Double.parseDouble(priceField.getText());
				
            if(partPrice <= 0.00)
                errorMessage += ("Invalid, 'Price' must be higher than 0.00\n"); 
            } catch (NumberFormatException e) {
                errorMessage += ("Invalid, 'Price' must be numerical\n");
            }
            
            //Checks Min/Max stock
            try {
                Min = Integer.parseInt(minField.getText());
            } catch (NumberFormatException e) {
                errorMessage += ("Invalid, 'Minimum' must be numerical\n");
            }
            try {
                Max = Integer.parseInt(maxField.getText());
            } catch (NumberFormatException e) {
                errorMessage += ("Invalid, 'Maximum' must be numerical\n");
            }
        
            if(Min != null && Min < 0) 
                errorMessage += ("Invalid, 'Minimum' a positive number\n");
        
            if(Min != null && Max != null && Min > Max) 
                errorMessage += ("Invalid, 'Maximum' must be higher than Minimum\n");
            
            //Checks Inventory
            try {
                Inv = Integer.parseInt(stockField.getText());
                if(Min != null && Max != null && Inv < Min || Inv > Max) 
                    errorMessage += ("Invalid, 'Inventory' must be between minimum and maximum\n");
            } catch (NumberFormatException e) {
                errorMessage += ("Invalid, 'Inventory' must be numerical\n");
            }
            
            //Checks valid Company Name/Machine ID
            if (inhouseBtn.getText().equals("Inhouse")){
                //intMachineId
                try {
                    Integer.parseInt(otherField.getText());
                } catch (NumberFormatException e) {
                    errorMessage += ("Invald, 'Machine ID' must be numeric\n");
                }
            } else if (inhouseBtn.getText().equals("Outsourced")) {
                    
            if(otherField.getText() == null || otherField.getText().length() == 0) 
                errorMessage += ("Invald, 'Company Name' is empty\n");	
            }    
            
            if (errorMessage.length() > 0) {            
                Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Part Error");
		alert.setHeaderText("Error");
		alert.setContentText(errorMessage);
		alert.showAndWait();
            } else {
                if(inhouseBtn.getText().equals("Inhouse"))
                    Inventory.addPart(new InHouse(Inventory.getPartList().size(), nameField.getText(), Double.parseDouble(priceField.getText()), 
                            Integer.parseInt(stockField.getText()), Integer.parseInt(minField.getText()), 
                            Integer.parseInt(maxField.getText()), Integer.parseInt(otherField.getText())));
                else
                    Inventory.addPart(new Outsourced(Inventory.getPartList().size(), nameField.getText(), Double.parseDouble(priceField.getText()), 
                            Integer.parseInt(stockField.getText()), Integer.parseInt(minField.getText()), 
                            Integer.parseInt(maxField.getText()), otherField.getText()));
                //partsTable.setItems(Inventory.getPartList());
		stage.close();
            }
            }
        });
        
        Button cancelBtn = new Button();
        cancelBtn.setText("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            
        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Confirm cancel");
            alert.setHeaderText("Are you sure you want to cancel?");
            alert.setContentText("Any unsaved changes will be lost");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) 
                stage.close();
            }
        });
		
	//Desides to populate form or not 
	if(addOrEdit.toLowerCase().equals("edit")) {
            iDField.setText(selectedPart.getId()+ "");
            nameField.setText(selectedPart.getName());
            stockField.setText(selectedPart.getStock()+ "");
            priceField.setText(selectedPart.getPrice()+ "");
            minField.setText(selectedPart.getMin()+ "");
            maxField.setText(selectedPart.getMax()+ "");
            if(selectedPart instanceof InHouse) {
                otherField.setText(Integer.toString(((InHouse) selectedPart).getMachineID()));
            } else {
                otherField.setText(((Outsourced) selectedPart).getCompanyName());
            }

	} else {
            
	}        
        
        form.add(title,0,0);
	form.add(iDLabel,0,1);
	form.add(nameLabel,0,2);
	form.add(stockLabel,0,3);
	form.add(priceLabel,0,4);
	form.add(minLabel,0,5);
	form.add(maxLabel,2,5);
	form.add(otherLabel,0,6);
        form.add(inhouseBtn,0,7);
		
	form.add(iDField,1,1);
	form.add(nameField,1,2);
	form.add(stockField,1,3);
	form.add(priceField,1,4);
	form.add(minField,1,5);
	form.add(maxField,3,5);
        form.add(otherField,1,6);
		
	form.add(savePartBtn,10,8);
	form.add(cancelBtn,9,8);
        Scene scene = new Scene(form, 630, 350);
        stage.setScene(scene);
        stage.show();
    }

//************************************************************************************************************************************************************************************************
    public void showProductsScreen(String addOrEdit) {	
        Stage stage = new Stage();
        ObservableList<Part> assocParts = FXCollections.observableArrayList();
        ObservableList<Part> initAssocParts = FXCollections.observableArrayList();
        ObservableList<Part> emptyList = FXCollections.observableArrayList();
        
        
        if(addOrEdit.toLowerCase().equals("edit")) 
            initAssocParts = selectedProduct.getAllAssociatedParts();
        
        if(addOrEdit.toLowerCase().equals("add"))  {
            assocParts.setAll(emptyList);
            initAssocParts.setAll(emptyList);
        }
	
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10,10,10,10));
        form.setPrefSize(800,800);
    
        Label title = new Label();
        title.setText(addOrEdit + " Product");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        title.setTextFill(Color.CADETBLUE);        
    
        Label iDLabel = new Label();
        iDLabel.setText("ID");
        TextField iDField = new TextField();
        iDField.setEditable(false);
        iDField.setDisable(true);
        iDField.setPromptText("Auto Gen ID");
       
        Label nameLabel = new Label();
        nameLabel.setText("Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
	
        Label stockLabel = new Label();
        stockLabel.setText("Stock");
        TextField stockField = new TextField();
        stockField.setPromptText("Stock");
	
        Label priceLabel = new Label();
        priceLabel.setText("Price");
        TextField priceField = new TextField();
        priceField.setPromptText("Price Per Unit");
	
        Label minLabel = new Label();
        minLabel.setText("Min");
        TextField minField = new TextField();
        minField.setPromptText("Minium Stock");
	
        Label maxLabel = new Label();
        maxLabel.setText("Max");
        TextField maxField = new TextField();
        maxField.setPromptText("Maxium Stock");
        
        Button savePartBtn = new Button();
        savePartBtn.setText("Save");
        savePartBtn.setOnAction(new EventHandler<ActionEvent>() {      
        @Override
        public void handle(ActionEvent event) {
            String errorMessage="";
            Integer Min=null,Max=null,Inv;
            Double partPrice;
            Boolean valid;
           
            //Part Name Check
            if(nameField.getText() == null || nameField.getText().length() == 0) {
                errorMessage += ("Invalid, 'Product Name' is empty\n");
            }    
           
            //Checks Price
            try {
            partPrice = Double.parseDouble(priceField.getText());
			
            if(partPrice <= 0.00)
               errorMessage += ("Invalid, 'Price' must be higher than 0.00\n"); 
            } catch (NumberFormatException e) {
               errorMessage += ("Invalid, 'Price' must be numerical\n");
            }
           
           //Checks Min/Max stock
            try {
               Min = Integer.parseInt(minField.getText());
            } catch (NumberFormatException e) {
               errorMessage += ("Invalid, 'Minimum' must be numerical\n");
            }
            try {
               Max = Integer.parseInt(maxField.getText());
            } catch (NumberFormatException e) {
               errorMessage += ("Invalid, 'Maximum' must be numerical\n");
            }
       
            if(Min != null && Min < 0) 
               errorMessage += ("Invalid, 'Minimum' a positive number\n");
       
            if(Min != null && Max != null && Min > Max) 
               errorMessage += ("Invalid, 'Maximum' must be higher than Minimum\n");
           
           //Checks Inventory
           try {
               Inv = Integer.parseInt(stockField.getText());
               if(Min != null && Max != null && Inv < Min || Inv > Max) 
                   errorMessage += ("Invalid, 'Inventory' must be between minimum and maximum\n");
           } catch (NumberFormatException e) {
               errorMessage += ("Invalid, 'Inventory' must be numerical\n");
           }    
           
           if (errorMessage.length() > 0) {            
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Product Error");
                alert.setHeaderText("Error");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            } else {
               if(addOrEdit.toLowerCase().equals("add")) {
                Product newProd = new Product(Inventory.getProductList().size(), nameField.getText(), Double.parseDouble(priceField.getText()), 
                           Integer.parseInt(stockField.getText()), Integer.parseInt(minField.getText()), 
                           Integer.parseInt(maxField.getText()));
                newProd.setAssociatedParts(assocParts);
                Inventory.addProduct(newProd);  
                stage.close();
               } else {
                   selectedProduct.setName(nameField.getText());
                   selectedProduct.setStock(Integer.parseInt(stockField.getText()));
                   selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
                   selectedProduct.setMin(Min);
                   selectedProduct.setMax(Max);
                   selectedProduct.setAssociatedParts(assocParts);
                   stage.close();
               }
               }
        }
       });
       
       Button cancelBtn = new Button();
       cancelBtn.setText("Cancel");
       cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
           
       @Override
       public void handle(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.initModality(Modality.NONE);
           alert.setTitle("Confirm cancel");
           alert.setHeaderText("Are you sure you want to cancel?");
           alert.setContentText("Any unsaved changes will be lost");
           Optional<ButtonType> result = alert.showAndWait();
           if (result.get() == ButtonType.OK) {
                stage.close();
           }
           }
       });
	
        //Desides to populate form or not 
        if(addOrEdit.toLowerCase().equals("edit")) {
           iDField.setText(selectedProduct.getProductID()+ "");
           nameField.setText(selectedProduct.getName());
           stockField.setText(selectedProduct.getStock()+ "");
           priceField.setText(selectedProduct.getPrice()+ "");
           minField.setText(selectedProduct.getMin()+ "");
           maxField.setText(selectedProduct.getMax()+ "");
           
        }       
	
	//Defines Content of Parts Side
        Label partTitle = new Label();
        partTitle.setText("Parts");
        partTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        partTitle.setTextFill(Color.CADETBLUE);
        TextField textFieldPart = new TextField();

        Label currentPartsLabel = new Label();
        currentPartsLabel.setText("Current Parts");
        Label assocPartsLabel = new Label();
        assocPartsLabel.setText("Associated Parts");
        //Parts Table Setup
        TableView<Part> partsTable = new TableView<Part>(Inventory.getPartList());
        TableColumn<Part, String> colPartId = new TableColumn<>("Part ID");
        colPartId.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partsTable.getColumns().add(colPartId);

        TableColumn<Part, String> colPartName = new TableColumn<>("Name");
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsTable.getColumns().add(colPartName);

        TableColumn<Part, String> colPartStock = new TableColumn<>("Stock Level");
        colPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsTable.getColumns().add(colPartStock);

        TableColumn<Part, String> colPartPrice = new TableColumn<>("Price");
        colPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getColumns().add(colPartPrice);

        partsTable.setPrefSize(280,130);
        TableView.TableViewSelectionModel<Part> selectedPartTable = partsTable.getSelectionModel();

        //Associated Parts Table Set up
        //Parts Table Setup
        TableView<Part> associatedPartsTable = new TableView<Part>(initAssocParts);
        TableColumn<Part, String> colAssocPartId = new TableColumn<>("Part ID");
        colAssocPartId.setCellValueFactory(new PropertyValueFactory<>("partId"));
        associatedPartsTable.getColumns().add(colAssocPartId);

        TableColumn<Part, String> colAssocPartName = new TableColumn<>("Name");
        colAssocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsTable.getColumns().add(colAssocPartName);

        TableColumn<Part, String> colAssocPartStock = new TableColumn<>("Stock Level");
        colAssocPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsTable.getColumns().add(colPartStock);

        TableColumn<Part, String> colAssocPartPrice = new TableColumn<>("Price");
        colAssocPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.getColumns().add(colAssocPartPrice);

        associatedPartsTable.setPrefSize(280,130);
        TableView.TableViewSelectionModel<Part> selectedAssocPartTable = associatedPartsTable.getSelectionModel();
        
        //Buttons and other elements
         Button assocPartDeleteBtn = new Button();
        assocPartDeleteBtn.setText("Delete");
        assocPartDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                selectedProduct.deleteAssociatedPart(selectedAssocPartTable.getSelectedItem());
                assocParts.remove(selectedAssocPartTable.getSelectedItem());
                associatedPartsTable.setItems(selectedProduct.getAllAssociatedParts());
            }
        });
        
        Button partSearchBtn = new Button();
        partSearchBtn.setText("Search");
        partSearchBtn.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent event) {
            String searchTerm = textFieldPart.getText();
            ObservableList<Part> foundParts = FXCollections.observableArrayList();

            if(isNumber(searchTerm)) 
                foundParts = (Inventory.lookupPart(Integer.parseInt(searchTerm)));
            else 
                foundParts = (Inventory.lookupPart(searchTerm));
            
            if(foundParts.size() > 0)
                partsTable.setItems(foundParts);
            else {
                partsTable.setItems(Inventory.getPartList());
            }      
        }
    });

    Button partAddBtn = new Button();
    partAddBtn.setText("Add");
    partAddBtn.setOnAction(new EventHandler<ActionEvent>() {       
        @Override
        public void handle(ActionEvent event) {  
            Boolean foundItem = false;
            Part addPart = selectedPartTable.getSelectedItem();

             if(addPart != null) {
                 for (int i = 0; i < assocParts.size(); i++) {
                     if(assocParts.get(i).equals(addPart)) {
                         foundItem = true;
                     }
                 } 

                 if(foundItem) {
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                     alert.setTitle("Part Already Associated");
                     alert.setHeaderText("The Part selected is already in Product");
                     alert.setContentText("This part is already associated to the product");
                     alert.showAndWait();
                 } else {
                     if(addOrEdit.toLowerCase().equals("edit")) {
                         assocParts.setAll(selectedProduct.getAllAssociatedParts());
                     }
                     assocParts.add(addPart);
                     associatedPartsTable.setItems(assocParts);   
                 }
             } else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.initModality(Modality.APPLICATION_MODAL);
                 alert.setTitle("No Part selected");
                 alert.setHeaderText("Please select a part from the current parts lists"); 
                 alert.showAndWait();
             }
       }
    });
     //Adds Form elements
    form.add(title,0,0);
    form.add(iDLabel,0,1);
    form.add(nameLabel,0,2);
    form.add(stockLabel,0,3);
    form.add(priceLabel,0,4);
    form.add(minLabel,0,5);
    form.add(maxLabel,2,5);

    form.add(iDField,1,1);
    form.add(nameField,1,2);
    form.add(stockField,1,3);
    form.add(priceField,1,4);
    form.add(minField,1,5);
    form.add(maxField,3,5);
    
    form.add(assocPartDeleteBtn,5,8);
    form.add(savePartBtn,5,9);
    form.add(cancelBtn,4,9);
        
    //Adds Table 1 Elements
    form.add(partTitle,4,0);
    form.add(partSearchBtn,6,0);
    form.add(textFieldPart,7,0);
    form.add(partsTable,4,1,4,3);
    form.add(currentPartsLabel,5,0);
    form.add(associatedPartsTable,4,5,4,3);
    form.add(assocPartsLabel,5,4);
    form.add(partAddBtn,7,4);
    
	
    Scene scene = new Scene(form, 1000, 500);
    stage.setScene(scene);
    stage.show();
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
