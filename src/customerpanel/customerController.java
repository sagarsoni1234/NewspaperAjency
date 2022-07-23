/**
 * Sample Skeleton for 'customer.fxml' Controller Class
 */

package customerpanel;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import papermaster.mysqqlconnection;

public class customerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cohawk"
    private ComboBox<String> cohawk; // Value injected by FXMLLoader

    @FXML // fx:id="comarea"
    private ComboBox<String> comarea; // Value injected by FXMLLoader

    @FXML // fx:id="doj"
    private DatePicker doj; // Value injected by FXMLLoader

    @FXML // fx:id="lispap"
    private ListView<String> lispap; // Value injected by FXMLLoader

    @FXML // fx:id="lisprice"
    private ListView<Float> lisprice; // Value injected by FXMLLoader

    @FXML // fx:id="tdress"
    private TextField tdress; // Value injected by FXMLLoader

    @FXML // fx:id="tmob"
    private TextField tmob; // Value injected by FXMLLoader
    
    @FXML // fx:id="tnam"
    private TextField tnam; // Value injected by FXMLLoader


    @FXML // fx:id="tpaper"
    private ListView<String> tpaper; // Value injected by FXMLLoader

    @FXML // fx:id="tprice"
    private ListView<Float> tprice; // Value injected by FXMLLoader
    PreparedStatement pst;
    @FXML
    void doclear(ActionEvent event) {
           tmob.setText("");
           tnam.setText("");
           comarea.getItems().clear();
            
           tdress.setText("");
           tpaper.getItems().clear();
           tprice.getItems().clear();
           showmg("Enter new data");
          
    }

    @FXML
    void dodelete(ActionEvent event) {
           try {
			pst=(PreparedStatement) con.prepareStatement("delete from customers where mobile=?");
			pst.setString(1, tmob.getText());
			int count=pst.executeUpdate();
			if(count==0)
			{
				showmg("Invalid mobile no");
			}
			else {
				showmg("Data deleted Successfully");
				 tmob.setText("");
		           tnam.setText("");
		           comarea.getItems().clear();
		            
		           tdress.setText("");
		           tpaper.getItems().clear();
		           tprice.getItems().clear();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void dosave(ActionEvent event) {
       try {
		pst=(PreparedStatement) con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?)");
		pst.setString(1,tmob.getText());
		pst.setString(2, tnam.getText());
		pst.setString(3, comarea.getEditor().getText());
		pst.setString(4, cohawk.getEditor().getText());
		pst.setString(5, tdress.getText());
		String ls=tpaper.getItems().toString();
		pst.setString(6,ls);
		int n=tprice.getItems().size();
		float sum=0;
		  Object[] obj= tprice.getItems().toArray();
		  for(int i=0;i<n;i++)
		  {
			  sum=sum+Float.parseFloat(obj[i].toString());
		  }
		 
		pst.setFloat(7, sum);
		LocalDate local=doj.getValue();
		Date doj=Date.valueOf(local);
		pst.setDate(8, doj); 
		pst.executeUpdate();
		showmg("Data Saved");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void dosearch(ActionEvent event) {
            try {
				pst=(PreparedStatement) con.prepareStatement("select * from customers where mobile=?");
				pst.setString(1, tmob.getText());
				ResultSet table=pst.executeQuery();
				boolean count=false;
				while(table.next())
				{
					count=true;
					String name=table.getString("name");
					String area=table.getString("area");
					String hawker=table.getString("hawker");
					String address=table.getString("address");
					String selpap=table.getString("selpapers");
					float pr=table.getFloat("totalprice");
					Date edoj=table.getDate("dostart");
					tnam.setText(name);
					comarea.setValue(area);
					cohawk.setValue(hawker);
					tdress.setText(address);
					tpaper.getItems().add(selpap);
					tprice.getItems().add(pr);
					doj.setValue(edoj.toLocalDate());
				}
				if(count==false)
				{
					showmg("Invalid mobile no");
				}
				else
				{
					System.out.println("data search successfully");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    @FXML
    void doupdate(ActionEvent event) {
         try {
			pst=(PreparedStatement) con.prepareStatement("update customers set name=?,area=?,hawker=?,address=?,selpapers=?,totalprice=?,dostart=? where mobile=?");
			pst.setString(8, tmob.getText());
			pst.setString(1, tnam.getText());
			pst.setString(2, comarea.getEditor().getText());
			pst.setString(3, cohawk.getEditor().getText());
			pst.setString(4, tdress.getText());
			String ls=tpaper.getItems().toString();
			pst.setString(5,ls);
			int n=tprice.getItems().size();
			float sum=0;
			for(int i=0;i<n;i++)
			{
				sum=sum+Float.parseFloat(tprice.getItems().get(i).toString());
			}
			pst.setFloat(6, sum);
			LocalDate local=doj.getValue();
			Date doj=Date.valueOf(local);
			pst.setDate(7, doj);
			int count=pst.executeUpdate();
			if(count==0)
			{
				showmg("Invalid mobile no");
			}
			else
			{
				showmg("Data update successfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void doselected(MouseEvent event) {
    	String idd=lispap.getSelectionModel().getSelectedItem();
    	tpaper.getItems().add(idd);
    	int indx=lispap.getSelectionModel().getSelectedIndex();
    	
    float selPrice=	lisprice.getItems().get(indx);
    tprice.getItems().add(selPrice);
    	
//    	try {
//			pst=(PreparedStatement) con.prepareStatement("select * from papers where paper=?");
//			pst.setString(1, idd);
//			ResultSet table=pst.executeQuery();
//			while(table.next())
//			{
//				tprice.getItems().add(table.getFloat("price"));
//			}
//		    
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }
    @FXML
    void doselcteddelet(MouseEvent event) {
        
       if(event.getClickCount()==1)
       {
    	   int index=tpaper.getSelectionModel().getSelectedIndex();
    	   tpaper.getItems().remove(index);
    	   tprice.getItems().remove(index);
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
    @FXML
    void doselhawk(ActionEvent event) {
    	String sel=comarea.getSelectionModel().getSelectedItem();
    	
    	cohawk.getItems().clear();
    	ArrayList<String> ids= new ArrayList<String>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select distinct name from hawker where areas like ?");
			pst.setString(1, "%"+sel+"%");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("name"));
			}
			cohawk.getItems().addAll(ids);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void fillarea()
    {
    	comarea.getItems().clear();
    	ArrayList<String> ids= new ArrayList<String>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from areas");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("area"));
			}
			comarea.getItems().addAll(ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void filllist()
    {
    	ArrayList<String> ids=new ArrayList<String>();
    	ArrayList<Float> id=new ArrayList<Float>();
    	try {
			pst=(PreparedStatement) con.prepareStatement("select * from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("paper"));
				id.add(table.getFloat("price"));
			}
			lispap.getItems().addAll(ids);
			lisprice.getItems().addAll(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        con = mysqqlconnection.getConnection();
        fillarea();
        filllist();
        
    }

}
