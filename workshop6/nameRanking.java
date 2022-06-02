package workshop6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class nameRanking extends Application {
	
	static TextField fieldYear = new TextField();
    static TextField fieldGender = new TextField();
    static TextField fieldName = new TextField();
    
    private static String[] results = new String[5];
    Label lbl = new Label();
    
	@Override
	public void start(Stage primaryStage) throws Exception {

		Label lab1 = new Label("Enter the Year: ");
        Label lab2 = new Label("Enter the Gender: ");
        Label lab3 = new Label("Enter the Name: ");
        
        Button btnSubmit = new Button("Submit Query");
        Button btnExit = new Button("Exit");
        
        btnSubmit.setPrefWidth(90);
        btnExit.setPrefWidth(90);
        
        btnExit.setOnAction(e -> primaryStage.close());
        
        btnSubmit.setOnAction(e -> {
        	
        	try {
				search();
				
                if(fieldGender.getText().toUpperCase().equals("F")){
                    String msg = "Girl name " + results[3] + " is ranked #" + results[0] + " in " + fieldYear.getText() + " year";
                	Boolean display = popUp.display(msg);
                	
                    if (display) {
                      	 clearField();
                      }else if (!display){
                      	primaryStage.close();
                      }else {
                   	   System.out.println("Oh shit here we go again");
                      }
                    
                }
                else if(fieldGender.getText().toUpperCase().equals("M")){
                	String msg = "Boy name " + results[1] + " is ranked #" + results[0] + " in " + fieldYear.getText() + " year";
                	Boolean display = popUp.display(msg);
                	
                    if (display) {
                      	 clearField();
                      }else if (!display){
                      	primaryStage.close();
                      }else {
                   	   System.out.println("Oh shit here we go again");
                      }
                    
                }else {
                	System.out.println("Oh shit");
                }
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        });
        
        GridPane mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(25,50,25,50));
		mainGrid.setHgap(10);
		mainGrid.setVgap(10);
		
		mainGrid.add(lab1, 0, 0);
		fieldYear.setPrefWidth(100);
		fieldYear.setMaxWidth(100);
		mainGrid.add(fieldYear, 1, 0);

		mainGrid.add(lab2, 0, 1);
		fieldGender.setPrefWidth(30);
		fieldGender.setMaxWidth(30);
		mainGrid.add(fieldGender, 1, 1);
		
		mainGrid.add(lab3, 0, 2);
		fieldName.setPrefWidth(100);
		fieldName.setMaxWidth(100);
		mainGrid.add(fieldName, 1, 2);
		
		GridPane btnGrid = new GridPane();
		btnGrid.setPadding(new Insets(10,50,25,50));
		btnGrid.setHgap(25);
		btnGrid.add(btnSubmit, 0, 0);
		btnGrid.add(btnExit, 1, 0);
		mainGrid.add(btnGrid, 0, 3);
		
		GridPane superMain = new GridPane();
		superMain.setPadding(new Insets(0,25,0,25));
		superMain.add(mainGrid, 0, 0);
		superMain.add(btnGrid, 0, 1);
		
		Scene scene = new Scene(superMain);
		primaryStage.setTitle("Search Name Ranking Application");
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
    public static String[] search() throws IOException{
    	
        BufferedReader buff = null;
        
        File sourceFile = new File(System.getProperty("user.dir")+"\\babynamesfolder\\babynamesranking" + fieldYear.getText() + ".txt");
        String line;
        try{
        	buff = new BufferedReader(new FileReader(sourceFile));
        } catch (Exception e){
            System.out.println("File not found!");
            System.exit(0);
        }

        try{
            while((line = buff.readLine()) != null) {
                String[] columns = line.split("\t");
                String rank = columns[0].replaceAll("\\s+", "");
                String boyName = columns[1].replaceAll("\\s+", "");
                String girlName = columns[3].replaceAll("\\s+", "");
                
                if (fieldGender.getText().toUpperCase().equals("F")) {
                    if (girlName.toLowerCase().startsWith(fieldName.getText().toLowerCase())) {
                        results[0] = rank;
                        results[3] = girlName;
                        return results;
                    }
                } else if (fieldGender.getText().toUpperCase().equals("M")) {
                    if (boyName.toLowerCase().startsWith(fieldName.getText().toLowerCase())) {
                        results[0] = rank;
                        results[1] = boyName;
                        return results;
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage() + " Error Reading File");
        }
        return results;
    }
	
    public void clearField() {
    	fieldYear.setText("");        	
    	fieldGender.setText("");        	
    	fieldName.setText("");        	

    }
    
}
