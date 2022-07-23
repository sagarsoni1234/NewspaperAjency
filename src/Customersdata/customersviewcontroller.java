/**
 * Sample Skeleton for 'customersview.fxml' Controller Class
 */

package Customersdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.jar.Attributes.Name;


import HawkerData.Hawkerbean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import papermaster.mysqqlconnection;

public class customersviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comarea"
    private ComboBox<String> comarea; // Value injected by FXMLLoader

    @FXML // fx:id="compap"
    private ComboBox<String> compap; // Value injected by FXMLLoader

    @FXML // fx:id="ttable"
    private TableView<customersbean> ttable; // Value injected by FXMLLoader
    java.sql.PreparedStatement pst;
    @FXML
    void dofetch(ActionEvent event) {
    	 String pape;
         pape=compap.getSelectionModel().getSelectedItem();
         ObservableList<customersbean> records=getdata(pape);
         ttable.setItems(records);
    }

    @FXML
    void dofind(ActionEvent event) {
    	String area;
        area=comarea.getSelectionModel().getSelectedItem();
        ObservableList<customersbean> records=getareadata(area);
        ttable.setItems(records);
    }

    @FXML
    void doshow(ActionEvent event) {
    	ObservableList<customersbean> records=getalldata();
        ttable.setItems(records);
    }

    @FXML
    void gotoexcel(ActionEvent event) {
       writeExcel();
    }
    public void writeExcel()  {
        Writer writer = null;
        try {
        	File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Name,Area,Address,Mobile,Selpapers,Total price,Date of Start\n";
            writer.write(text);
            for (customersbean p : lis)
            {
            	
				text = p.getName()+ "," + p.getArea()+ "," + p.getAddress()+ "," + p.getMobile()+","+p.getSelpapers()+","+p.getTotalprice()+","+p.getDostart()+ "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
        	try{
             writer.flush();
             writer.close();
        	}
        	catch(Exception exp){exp.printStackTrace();}
        }
    }
    ObservableList<customersbean> lis;
   ObservableList<customersbean> getdata(String pape)
    {
    	lis=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from customers where selpapers like ?");
			pst.setString(1, "%"+pape+"%");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				String area=table.getString("area");
				String address=table.getString("address");
				String mobile=table.getString("mobile");
				String selpapers=table.getString("selpapers");
				float totalprice=table.getFloat("totalprice");
				String dostart=table.getString("dostart");
				customersbean cus=new customersbean(name, area, address, mobile, selpapers, totalprice, dostart);
				lis.add(cus);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return lis;
    }
   ObservableList<customersbean> getareadata(String area)
   {
   	lis=FXCollections.observableArrayList();
   	try {
			pst=con.prepareStatement("select * from customers where area=?");
			pst.setString(1,area);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				String areas=table.getString("area");
				String address=table.getString("address");
				String mobile=table.getString("mobile");
				String selpapers=table.getString("selpapers");
				float totalprice=table.getFloat("totalprice");
				String dostart=table.getString("dostart");
				customersbean cus=new customersbean(name, areas, address, mobile, selpapers, totalprice, dostart);
				lis.add(cus);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	return lis;
   }
   ObservableList<customersbean> getalldata()
   {
   	
   	lis=FXCollections.observableArrayList();
   	try {
			pst=con.prepareStatement("select * from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				String areas=table.getString("area");
				String address=table.getString("address");
				String mobile=table.getString("mobile");
				String selpapers=table.getString("selpapers");
				float totalprice=table.getFloat("totalprice");
				String dostart=table.getString("dostart");
				customersbean cus=new customersbean(name, areas, address, mobile, selpapers, totalprice, dostart);
				lis.add(cus);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	return lis;
   }
    void fillpap()
    {
    	ArrayList<String> ids= new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select paper from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String pap=table.getString("paper");
				ids.add(pap);
			}
			compap.getItems().addAll(ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void fillarea()
    {
    	ArrayList<String> ids= new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select area from areas");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String area=table.getString("area");
				ids.add(area);
			}
			comarea.getItems().addAll(ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void filltable()
    {
    	TableColumn<customersbean,String> name=new TableColumn<customersbean,String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<customersbean,String>("name"));
        name.setMaxWidth(1000);
        TableColumn<customersbean,String> area=new TableColumn<customersbean,String>("Area");
        area.setCellValueFactory(new PropertyValueFactory<customersbean,String>("area"));
        TableColumn<customersbean,String> adresss=new TableColumn<customersbean,String>("Address");
        adresss.setCellValueFactory(new PropertyValueFactory<customersbean,String>("address"));
        TableColumn<customersbean,String> mobile=new TableColumn<customersbean,String>("Mobile");
        mobile.setCellValueFactory(new PropertyValueFactory<customersbean,String>("mobile"));
        TableColumn<customersbean,String> paper=new TableColumn<customersbean,String>("Papers");
        paper.setCellValueFactory(new PropertyValueFactory<customersbean,String>("selpapers"));
        TableColumn<customersbean,Float> price=new TableColumn<customersbean,Float>("Price");
        price.setCellValueFactory(new PropertyValueFactory<customersbean,Float>("totalprice"));
        TableColumn<customersbean,String> date=new TableColumn<customersbean,String>("Date of Start");
        date.setCellValueFactory(new PropertyValueFactory<customersbean,String>("dostart"));
       
        
        
        ttable.getColumns().addAll(name,area,adresss,mobile,paper,price,date);
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        con=mysqqlconnection.getConnection();
         fillpap();
         fillarea();
         filltable();
    }

}
