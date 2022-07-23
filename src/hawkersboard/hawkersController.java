/**
 * Sample Skeleton for 'hawkers.fxml' Controller Class
 */

package hawkersboard;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.*;
import javax.swing.JButton;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import papermaster.mysqqlconnection;

public class hawkersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="coname"
    private ComboBox<String> coname; // Value injected by FXMLLoader

    @FXML // fx:id="tdress"
    private TextField tdress; // Value injected by FXMLLoader

    @FXML // fx:id="tdhaar"
    private TextField tdhaar; // Value injected by FXMLLoader

    @FXML // fx:id="tmob"
    private TextField tmob; // Value injected by FXMLLoader

    @FXML // fx:id="tsal"
    private TextField tsal; // Value injected by FXMLLoader

    @FXML // fx:id="doj"
    private DatePicker doj; // Value injected by FXMLLoader

    @FXML // fx:id="tarea"
    private TextField tarea; // Value injected by FXMLLoader

    @FXML // fx:id="comarea"
    private ComboBox<String> comarea; // Value injected by FXMLLoader

    @FXML // fx:id="timage"
    private ImageView timage; // Value injected by FXMLLoader

    @FXML
    void doadd(ActionEvent event) {
    	coname.getSelectionModel().clearSelection();
    	comarea.getSelectionModel().clearSelection();
    	tdress.setText("");
		tdhaar.setText("");
		tmob.setText("");
		tsal.setText("");
		
		tarea.setText("");
        timage.setImage(null);
    }
    String filepath;
    @FXML
    void dobrowse(ActionEvent event) {
          FileChooser chooser=new FileChooser();
          chooser.setTitle("select profile pic");
          chooser.getExtensionFilters().addAll(
        		  new FileChooser.ExtensionFilter("All Images", "*.*"),
        		  new FileChooser.ExtensionFilter("JPG", "*.jpg"),
        		  new FileChooser.ExtensionFilter("PNG","*.png"),
        		  new FileChooser.ExtensionFilter("*.*", "*.*")
        		  );
          File file=chooser.showOpenDialog(null);
          filepath=file.getAbsolutePath();
          try {
			timage.setImage(new javafx.scene.image.Image(new FileInputStream(filepath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }

    @FXML
    void dodelete(ActionEvent event) {
        try {
			pst=(PreparedStatement) con.prepareStatement("delete from hawker where name=?");
			pst.setString(1, coname.getEditor().getText());
			int count=pst.executeUpdate();
			fillcombo();
			if(count==0)
			{
				showmg("Invalid name");
			}
			else
			{
				showmg("Data deleted Successfully");
				tdhaar.setText("");
				tdress.setText("");
				tarea.setText("");
				timage.setImage(null);
				tmob.setText("");
				tsal.setText("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    PreparedStatement pst;
    PreparedStatement est;
    @FXML
    void dosave(ActionEvent event) {
           try {
			pst=(PreparedStatement) con.prepareStatement("insert into hawker values(?,?,?,?,?,?,?,?)");
			pst.setString(1, coname.getEditor().getText());
			pst.setString(2, tdress.getText());
			pst.setString(3,tdhaar.getText());
			pst.setString(4, tmob.getText());
			pst.setFloat(5, Float.parseFloat(tsal.getText()));
			LocalDate local=doj.getValue();
			Date doj=Date.valueOf(local);
			pst.setDate(6, doj);
			pst.setString(7,tarea.getText());
			pst.setString(8, filepath);
			pst.executeUpdate();
			showmg("Saved");
			fillcombo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void dosearch(ActionEvent event) {
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from hawker where name=?");
			pst.setString(1,coname.getEditor().getText());
			ResultSet table= pst.executeQuery();
			Boolean count=false;
			while(table.next())
			{
				count=true;
				String adress=table.getString("adresss");
				String addhaar=table.getString("Addhaar");
				String mobile=table.getString("mobile");
				float sal=table.getFloat("esal");
				java.sql.Date edoj=table.getDate("date");
				String area=table.getString("areas");
				filepath=table.getString("path");
				tdress.setText(adress);
				tdhaar.setText(addhaar);
				tmob.setText(mobile);
				tsal.setText(sal+"");
				doj.setValue(edoj.toLocalDate());
				tarea.setText(area);
				try {
					timage.setImage(new javafx.scene.image.Image(new FileInputStream(filepath)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
    void doarea(ActionEvent event) {
           String idd=comarea.getSelectionModel().getSelectedItem();
           tarea.setText(tarea.getText()+idd+",");
    }

    @FXML
    void doupdate(ActionEvent event) {
    	try {
			pst=(PreparedStatement) con.prepareStatement("update hawker set adresss=?,Addhaar=?,mobile=?,esal=?,date=?,areas=?,path=? where name=?");
			pst.setString(8, coname.getEditor().getText());
			pst.setString(1, tdress.getText());
			pst.setString(2,tdhaar.getText());
			pst.setString(3, tmob.getText());
			pst.setFloat(4, Float.parseFloat(tsal.getText()));
			LocalDate local=doj.getValue();
			Date doj=Date.valueOf(local);
			pst.setDate(5, doj);
			pst.setString(6, tarea.getText());
			pst.setString(7, filepath);
			int count=pst.executeUpdate();
			if(count==0)
			{
				showmg("Invalid name");
			}
			else
			{
				showmg("Updated successfully");
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
    	coname.getItems().clear();
    	ArrayList<String> cona= new ArrayList<String>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from hawker");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				cona.add(table.getString("name"));
			}
			coname.getItems().addAll(cona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void fillcoarea()
    {
    	comarea.getItems().clear();
    	ArrayList<String> cona= new ArrayList<String>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from areas");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				cona.add(table.getString("area"));
			}
			comarea.getItems().addAll(cona);
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
         fillcoarea();
    }
}
