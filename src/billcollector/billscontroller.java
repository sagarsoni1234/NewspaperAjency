/**
 * Sample Skeleton for 'bills.fxml' Controller Class
 */

package billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import papermaster.mysqqlconnection;

public class billscontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tamo"
    private Label tamo; // Value injected by FXMLLoader

    @FXML // fx:id="tdf"
    private Label tdf; // Value injected by FXMLLoader

    @FXML // fx:id="tdo"
    private Label tdo; // Value injected by FXMLLoader

    @FXML // fx:id="tmobile"
    private TextField tmobile; // Value injected by FXMLLoader
     PreparedStatement pst;
    @FXML
    void dosearch(ActionEvent event) {
          try {
			pst=(PreparedStatement) con.prepareStatement("select * from bills where cmobile=? and status=0");
			pst.setString(1,tmobile.getText());
			ResultSet table=pst.executeQuery();
			boolean count=false;
			while(table.next())
			{
				count=true;
				float prc=table.getFloat("amount");
				Date doj=table.getDate("datefrom");
				Date edoj=table.getDate("dateto");
				tamo.setText(prc+"");
				tdf.setText(doj+"");
				tdo.setText(edoj+"");
			}
			if(count==false)
			{
				showmg("either Mobile no is incorrect or bill have done already");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doupdate(ActionEvent event) {
        try {
			pst=(PreparedStatement) con.prepareStatement("update bills set status=1 where cmobile=? and status=0");
			pst.setString(1,tmobile.getText());
			int count=pst.executeUpdate();
			if(count==0)
			{
				showmg("");
			}
			else
			{
				showmg("Thanks Payment Done!!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void showmg(String msg)
    {
    	Alert alt=new Alert(AlertType.INFORMATION);
    	alt.setTitle("Imformation dialog");
    	alt.setHeaderText("Response");
    	alt.setContentText(msg);
    	alt.showAndWait();
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        con=mysqqlconnection.getConnection();

    }

}
