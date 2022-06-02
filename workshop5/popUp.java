package workshop5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class popUp {

	public static void display(String title ,String msg) {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setHeight(150);
		window.setWidth(300);
		
		Label label = new Label();
		label.setText(msg);
		
		Button btn = new Button("OK!");
		btn.setOnAction(e -> {
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, btn);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
}
