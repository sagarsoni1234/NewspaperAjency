module NewsPaperAjencySystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.web;
	requires java.desktop;	
	opens papermaster to javafx.graphics, javafx.fxml;
	opens Areamaster to javafx.graphics, javafx.fxml;
	opens hawkersboard to javafx.graphics, javafx.fxml;
	opens customerpanel to javafx.graphics, javafx.fxml;
	opens billcreator to javafx.graphics, javafx.fxml;
	opens billcollector to javafx.graphics, javafx.fxml;
	opens HawkerData to javafx.graphics, javafx.fxml;
	opens Customersdata to javfx.graphics, javafx.fxml;
	opens billhistory to javfx.graphics, javafx.fxml;
	opens admindahboard to javfx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}
