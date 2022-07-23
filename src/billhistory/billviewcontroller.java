/**
 * Sample Skeleton for 'billview.fxml' Controller Class
 */

package billhistory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import Customersdata.customersbean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import papermaster.mysqqlconnection;
import javafx.scene.control.ToggleGroup;
public class billviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tmobile"
    private TextField tmobile; // Value injected by FXMLLoader

    @FXML // fx:id="tpaid"
    private RadioButton tpaid; // Value injected by FXMLLoader

    @FXML // fx:id="ttable"
    private TableView<billbean> ttable; // Value injected by FXMLLoader

    @FXML // fx:id="tunpaid"
    private RadioButton tunpaid; // Value injected by FXMLLoader

    @FXML
    void dofectch(ActionEvent event) {

       if(tpaid.isSelected()==true)
       {
    	   ObservableList<billbean> record=getalldata(1);
    	   ttable.setItems(record);
       }
       else if(tunpaid.isSelected()==true)
       {
    	   ObservableList<billbean> record=getalldata(0);
    	   ttable.setItems(record);
       }
    }

    @FXML
    void dopaid(ActionEvent event) {

    }

    @FXML
    void dounpaid(ActionEvent event) {

    }

    @FXML
    void gethis(ActionEvent event) {
         String mob=tmobile.getText();
         ObservableList<billbean> record=getcdata(mob);
         ttable.setItems(record);
    }
    PreparedStatement pst;
    ObservableList<billbean> list;
    ObservableList<billbean> getcdata(String mob)
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from bills where cmobile=?");
			pst.setString(1, mob);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String mobi=table.getString("cmobile");
				String datef=table.getString("datefrom");
				String datet=table.getString("dateto");
				float amount=table.getFloat("amount");
				int stat=table.getInt("status");
				billbean bil=new billbean(mobi, datef, datet, amount, stat);
				list.add(bil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    ObservableList<billbean> getalldata(int statu)
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from bills where status=?");
			pst.setInt(1, statu);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String mobi=table.getString("cmobile");
				String datef=table.getString("datefrom");
				String datet=table.getString("dateto");
				float amount=table.getFloat("amount");
				int stat=table.getInt("status");
				billbean bil=new billbean(mobi, datef, datet, amount, stat);
				list.add(bil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    void filltable()
    {
    	TableColumn<billbean,String> mobile=new TableColumn<billbean,String>("Mobile");
        mobile.setCellValueFactory(new PropertyValueFactory<billbean,String>("cmobile"));
        TableColumn<billbean,String> date=new TableColumn<billbean,String>("Date of Start");
        date.setCellValueFactory(new PropertyValueFactory<billbean,String>("datefrom"));
        TableColumn<billbean,String> datecol=new TableColumn<billbean,String>("Date of End");
        datecol.setCellValueFactory(new PropertyValueFactory<billbean,String>("dateto"));
        TableColumn<billbean,Float> price=new TableColumn<billbean,Float>("Amount");
        price.setCellValueFactory(new PropertyValueFactory<billbean,Float>("amount"));
        TableColumn<billbean,Integer> status=new TableColumn<billbean, Integer>("Status");
        status.setCellValueFactory(new PropertyValueFactory<billbean,Integer>("status"));
        
        ttable.getColumns().addAll(mobile,date,datecol,price,status);
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       con=mysqqlconnection.getConnection();
         filltable();
    }

}
