module casgim.juanma.ProyectoTercerTrimestre {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.xml.bind;
	requires java.sql;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;

    opens casgim.juanma.ProyectoTercerTrimestre to javafx.fxml,java.xml.bind;
    opens casgim.juanma.ProyectoTercerTrimestre.utils;
    opens casgim.juanma.ProyectoTercerTrimestre.model.DAO;
    opens casgim.juanma.ProyectoTercerTrimestre.model.DataObject;
    exports casgim.juanma.ProyectoTercerTrimestre;
}
