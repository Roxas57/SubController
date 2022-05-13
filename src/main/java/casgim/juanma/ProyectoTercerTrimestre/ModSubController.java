package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class ModSubController implements Initializable{
	
	@FXML
	private ChoiceBox<String> ChB;
	
	private String[] sub = {"Diaria","Semanal","Mensual","Anual"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChB.setValue("Diaria");
		ChB.getItems().addAll(sub);
		
	}
    @FXML
    private void switchToConfDel() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("confdel.fxml"));
    	Scene scene = new Scene(root,300,179);
    	Stage newStage = new Stage();
    	newStage.setScene(scene);
    	newStage.show();
    	
    	
    }
}
