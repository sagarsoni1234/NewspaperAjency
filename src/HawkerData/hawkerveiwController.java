/**
 * Sample Skeleton for 'hawkerveiw.fxml' Controller Class
 */

package HawkerData;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import papermaster.mysqqlconnection;

public class hawkerveiwController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ttable"
    private TableView<Hawkerbean> ttable; // Value injected by FXMLLoader
     java.sql.PreparedStatement pst;
    @FXML
    void doshow(ActionEvent event) {
    	 
           ObservableList<Hawkerbean> records=getdata();
          
           ttable.setItems(records);
           
    }
    void addCols()
    {
    	 TableColumn<Hawkerbean,String> name=new TableColumn<Hawkerbean,String>("hname");
         name.setCellValueFactory(new PropertyValueFactory<Hawkerbean,String>("name"));
         name.setMaxWidth(1000);
         TableColumn<Hawkerbean,String> adresss=new TableColumn<Hawkerbean,String>("adresss");
         adresss.setCellValueFactory(new PropertyValueFactory<Hawkerbean,String>("addresss"));
         TableColumn<Hawkerbean,String> addhaar=new TableColumn<Hawkerbean,String>("addhaar");
         addhaar.setCellValueFactory(new PropertyValueFactory<Hawkerbean,String>("addhaar"));
         TableColumn<Hawkerbean,String> mobile=new TableColumn<Hawkerbean,String>("mobile");
         mobile.setCellValueFactory(new PropertyValueFactory<Hawkerbean,String>("mobile"));
         TableColumn<Hawkerbean,Float> esal=new TableColumn<Hawkerbean,Float>("esal");
         esal.setCellValueFactory(new PropertyValueFactory<Hawkerbean,Float>("name"));
         TableColumn<Hawkerbean,String> date=new TableColumn<Hawkerbean,String>("date");
         date.setCellValueFactory(new PropertyValueFactory<Hawkerbean,String>("date"));
         TableColumn<Hawkerbean,String> areas=new TableColumn<Hawkerbean,String>("areas");
         areas.setCellValueFactory(new PropertyValueFactory<Hawkerbean,String>("areas"));
         areas.setMaxWidth(400);
        
         
         
         ttable.getColumns().addAll(name,adresss,addhaar,mobile,esal,date,areas);
    }
    ObservableList<Hawkerbean> getdata()
    {
    	ObservableList<Hawkerbean> lis;
		lis=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from hawker");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				String adresss=table.getString("adresss");
				String Addhaar=table.getString("Addhaar");
				String mobile=table.getString("mobile");
				float esal=table.getFloat("esal");
				String date=table.getString("date");
				String areas=table.getString("areas");
				Hawkerbean haw=new Hawkerbean(name, adresss, Addhaar, mobile, esal, date, areas);
				lis.add(haw);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return lis;
    }
   Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        con=mysqqlconnection.getConnection();
        addCols();

    }

}
