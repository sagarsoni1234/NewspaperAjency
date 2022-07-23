/**
 * Sample Skeleton for 'areas.fxml' Controller Class
 */

package Areamaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import papermaster.mysqqlconnection;

public class areascontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combold"
    private ComboBox<String> combold; // Value injected by FXMLLoader

    @FXML
    void doremove(ActionEvent event) {
              try {
				pst=(PreparedStatement) con.prepareStatement("delete from areas");
				pst.executeUpdate();
				showmg("Delete successfully");
				fillcombo();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            		  
    }
     PreparedStatement pst;
    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=(PreparedStatement) con.prepareStatement("insert into areas values(?)");
			pst.setString(1, combold.getEditor().getText());
			pst.executeUpdate();
			showmg("Saved");
			fillcombo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
      
    }
    void showmg(String msg)
    {
    	Alert alt= new Alert(AlertType.INFORMATION);
    	alt.setTitle("Imformation dialog");
    	alt.setHeaderText("Response");
    	alt.setContentText(msg);
    	alt.showAndWait();
    }
    void fillcombo()
    {
    	combold.getItems().clear();
    	ArrayList<String> ar=new ArrayList<>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from areas");
			ResultSet table= pst.executeQuery();
			while(table.next())
			{
				ar.add(table.getString("area"));
			}
			combold.getItems().addAll(ar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
  Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=mysqqlconnection.getConnection();
         fillcombo();
    }
}
