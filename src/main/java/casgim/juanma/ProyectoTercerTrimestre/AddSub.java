package casgim.juanma.ProyectoTercerTrimestre;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class AddSub implements Initializable{

	@FXML
	private ChoiceBox<String> ChB;
	
	private String[] sub = {"Diaria","Semanal","Mensual","Anual"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChB.setValue("Diaria");
		ChB.getItems().addAll(sub);
		
	}

}
