package workshop6;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class popUp {

	private static Boolean myBool = false ;
	
	public static Boolean display(String msg) {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Search Name Ranking Application");
		window.setHeight(200);
		window.setWidth(400);
		
		Label label = new Label();
		label.setText(msg);
		label.setPadding(new Insets(20,0,0,0));
		
		Label label2 = new Label();
		label2.setText("Do you want to Search for another Name:");
		label2.setPadding(new Insets(20,0,0,0));
		
		Button btnYes = new Button("Yes");
		btnYes.setPrefWidth(90);
		btnYes.setOnAction(e -> {
			myBool = true;
			window.close();
		});
		
		Button btnNo = new Button("No");
		btnNo.setPrefWidth(90);
		btnNo.setOnAction(e -> {
			myBool = false;
			window.close();
		});
		
		GridPane btnGrid = new GridPane();
		btnGrid.setAlignment(Pos.CENTER);
		btnGrid.setHgap(25);
		btnGrid.add(btnYes, 0, 0);
		btnGrid.add(btnNo, 1, 0);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, label2, btnGrid);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		return myBool ;
	}
	
}