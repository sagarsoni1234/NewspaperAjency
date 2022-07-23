/**
bg * Sample Skeleton for 'paper.fxml' Controller Class
 */

package papermaster;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class papercontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combold"
    private ComboBox<String> combold; // Value injected by FXMLLoader

    @FXML // fx:id="tprice"
    private TextField tprice; // Value injected by FXMLLoader

    @FXML
    void dodelete(ActionEvent event) {
          try {
			pst=con.prepareStatement("delete from papers where paper=?");
			pst.setString(1,combold.getEditor().getText());
			int count=pst.executeUpdate();
			fillcombo();
			if(count==0)
			{
			showmg("Invalid Id");
			}
			else
			{
				showmg("Data Deleted Successfuly");
				tprice.setText("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		  
    }
      PreparedStatement pst;
    @FXML
    void dosave(ActionEvent event) {
                try {
					pst=con.prepareStatement("insert into papers values(?,?)");
					pst.setString(1, combold.getEditor().getText());
					pst.setFloat(2,Float.parseFloat(tprice.getText()));
					pst.executeUpdate();
					fillcombo();
					showmg("DATA SAVED SUCCESSFULLY");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }

    @FXML
    void dosearch(ActionEvent event) {
                 try {
					pst=con.prepareStatement("select * from papers where paper=?");
					pst.setString(1,combold.getEditor().getText());
					ResultSet table= pst.executeQuery();
					Boolean count=false;
					while(table.next())
					{
						count=true;
						float price=table.getFloat("price");
						tprice.setText(price+"");
					}
					if(count==true)
					{
						System.out.println("Data search succesfully");
					}
					else
					{
						System.out.println("Invalid id");
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 
    }

    @FXML
    void doupdate(ActionEvent event) {
    	try {
			pst=con.prepareStatement("update papers set price=? where paper=?");
			pst.setFloat(1,Float.parseFloat(tprice.getText()));
			pst.setString(2, combold.getEditor().getText());
			int count=pst.executeUpdate();
			if(count==0)
			{
				showmg("Paper not found");
			}
			else
			{
				showmg("Data updated successfully");
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
    void fillcombo()
    {
    	combold.getItems().clear();
    	ArrayList<String> com= new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select * from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				com.add(table.getString("paper"));
			}
			combold.getItems().addAll(com);
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
