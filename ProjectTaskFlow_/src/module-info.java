module ProjectTaskFlow_ {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;	
	opens Controller to javafx.graphics, javafx.fxml;
	opens Model to javafx.base, javafx.graphics, javafx.fxml;

}
