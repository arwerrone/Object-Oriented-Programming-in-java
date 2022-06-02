package workshop5;


import java.io.RandomAccessFile;
import java.util.Vector;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ws05 extends Application{

    int currIdx = 0;
	TextField fieldFName = new TextField();
    TextField fieldLName = new TextField();
    TextField fieldCity = new TextField();
    TextField fieldPcode = new TextField();
    ChoiceBox<String> fieldProvince = new ChoiceBox<>();

    public Vector<String> addresses = new Vector<>();

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		readAllAddresses();
		
		fieldProvince.getItems().addAll("NL", "PE", "NS", "NB", "QC", "ON", "MB", "SK", "AB", "BC", "YT", "NT", "NU");
		
        Label lab1 = new Label("First Name: ");
        Label lab2 = new Label("Last Name: ");
        Label lab3 = new Label("City: ");
        Label lab4 = new Label("Province: ");
        Label lab5 = new Label("Postal Code: ");
        
        Button btnAdd = new Button("Add");
        Button btnFirst = new Button("First");
        Button btnNext = new Button("Next");
        Button btnPrevious = new Button("Previous");
        Button btnLast = new Button("Last");
        Button btnUpdate = new Button("Update");
        
        btnAdd.setPrefWidth(90);
        btnFirst.setPrefWidth(90);
        btnNext.setPrefWidth(90);
        btnPrevious.setPrefWidth(90);
        btnLast.setPrefWidth(90);
        btnUpdate.setPrefWidth(90);
        
        btnAdd.setOnAction(e -> {
        	addresses.add(String.join(",", getCurrentVal()));
        	write();
        	addresses.clear();
        	clearField();
        	popUp.display("Address Book", "New address added successfully");
        	readAllAddresses();
        });
        
        btnFirst.setOnAction(e -> {
        	currIdx = 0;
        	setCurrentRecord(getCurrentRec());
        });
        
        btnNext.setOnAction(e -> {
        	setCurrentRecord(getNextRec());
        });
        
        btnPrevious.setOnAction(e -> {
        	setCurrentRecord(getPreviousRec());
        });
        
        btnLast.setOnAction(e -> {
        	currIdx = addresses.size() - 1;
        	setCurrentRecord(getCurrentRec());
        });
        
        btnUpdate.setOnAction(e -> {
        	addresses.set(currIdx, String.join(",", getCurrentVal()));
        	write();
        	addresses.clear();
        	clearField();
        	popUp.display("Address Book", "Address updated successfully");
        	readAllAddresses();
        });
        
		GridPane mainGrid = new GridPane();
		GridPane firstGrid = new GridPane();
        GridPane secondGrid = new GridPane();
        GridPane thirdGrid = new GridPane();
		
		mainGrid.setPadding(new Insets(10,10,10,10));
		mainGrid.setHgap(10);
		mainGrid.setVgap(10);
		
		firstGrid.setPadding(new Insets(10,10,10,10));
		firstGrid.setVgap(5);
		firstGrid.setHgap(5);
		
		secondGrid.setPadding(new Insets(10, 10, 10, 10));
		secondGrid.setVgap(5);
		secondGrid.setHgap(5);
        
		thirdGrid.setPadding(new Insets(10, 10, 10, 10));
		thirdGrid.setVgap(5);
		thirdGrid.setHgap(5);

		// NAME GRID
		fieldFName.setPrefWidth(480);
		firstGrid.add(lab1, 1, 1);
		firstGrid.add(fieldFName, 2, 1);
		firstGrid.add(lab2, 1, 2);
		firstGrid.add(fieldLName, 2, 2);
		
		// ADDRESS GRID
		secondGrid.add(lab3, 1, 1);
		secondGrid.add(fieldCity, 2, 1);
		secondGrid.add(lab4, 3, 1);
		secondGrid.add(fieldProvince, 4, 1);
		secondGrid.add(lab5, 5, 1);
		secondGrid.add(fieldPcode, 6, 1);
		
		// BUTTON GRID
		thirdGrid.add(btnAdd, 1, 1);
		thirdGrid.add(btnFirst, 2, 1);
		thirdGrid.add(btnNext, 3, 1);
		thirdGrid.add(btnPrevious, 4, 1);
		thirdGrid.add(btnLast, 5, 1);
		thirdGrid.add(btnUpdate, 6, 1);
		
		// MAIN GRID ADD
		mainGrid.add(firstGrid, 1, 1);
		mainGrid.add(secondGrid, 1, 2);
		mainGrid.add(thirdGrid, 1, 3);
		
		Scene scene = new Scene(mainGrid);
		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	//Functions
	
    public void readAllAddresses() {
        String address;
        try {
        	RandomAccessFile file = new RandomAccessFile("addressBook.txt", "rw");
        	file.seek(0);
        	while ((address = file.readLine()) != null) {
        		addresses.add(address);
        		}
        	file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public String[] getCurrentVal() {
        return new String[]{
        		fieldFName.getText(), fieldLName.getText(), fieldCity.getText(), fieldProvince.getValue(), fieldPcode.getText()
        	};
    }
	
    public void write() {
    	try {
    		RandomAccessFile file = new RandomAccessFile("addressBook.txt", "rw");
    		file.seek(0);
    		for (String line : addresses) {
    			file.write(line.getBytes());
    			file.write("\n".getBytes());
    		}
    		file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public void setCurrentRecord(String[] str) {
    	
    	fieldFName.setText(str[0]);
    	fieldLName.setText(str[1]);
    	fieldCity.setText(str[2]);
    	fieldProvince.setValue(str[3]);
    	fieldPcode.setText(str[4]);
        
    }
    
    public String[] getCurrentRec() {
        return addresses.get(currIdx).split(",");
    }
    
    public String[] getNextRec() {
    	
        if (currIdx != addresses.size() - 1) {
            return addresses.get(++currIdx).split(",");
        } else {
            return getCurrentRec();
        }
        
    }
	
    public String[] getPreviousRec() {
        if (currIdx != 0) {
            return addresses.get(--currIdx).split(",");
        } else {
            return getCurrentRec();
        }
    }
    
    public void clearField() {
    	fieldFName.setText("");        	
    	fieldLName.setText("");        	
    	fieldCity.setText("");        	
    	fieldPcode.setText("");
    	fieldProvince.setValue("");
    }
    
}
