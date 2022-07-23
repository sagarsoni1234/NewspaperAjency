/**
 * Sample Skeleton for 'bill.fxml' Controller Class
 */

package billcreator;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import papermaster.mysqqlconnection;

public class billController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbill"
    private TextField tbill; // Value injected by FXMLLoader

    @FXML // fx:id="tcom"
    private ListView<String> tcom; // Value injected by FXMLLoader

    @FXML // fx:id="tday"
    private TextField tday; // Value injected by FXMLLoader

    @FXML // fx:id="tdoj"
    private DatePicker tdoj; // Value injected by FXMLLoader

    @FXML // fx:id="tdojf"
    private DatePicker tdojf; // Value injected by FXMLLoader

    @FXML // fx:id="tprice"
    private TextField tprice; // Value injected by FXMLLoader
    PreparedStatement pst;
    @FXML
    void dobill(ActionEvent event) {
            float bill=Float.parseFloat(tprice.getText())*Float.parseFloat(tday.getText());
            tbill.setText(bill+"");
    }

    @FXML
    void doday(ActionEvent event) {
           LocalDate local=tdoj.getValue();
           Date doj=Date.valueOf(local);
           LocalDate local1=tdojf.getValue();
           Date edoj=Date.valueOf(local1);
           long dif=doj.getTime()-(edoj.getTime());
           TimeUnit time=TimeUnit.DAYS;
           long diff = time.convert(dif, TimeUnit.MILLISECONDS);
          tday.setText(diff+"");
    }
    @FXML
    void dosave(ActionEvent event) {
           try {
			pst= con.prepareStatement("insert into bills values(?,?,?,?,0)");
			pst.setString(1,ls);
			LocalDate local=tdojf.getValue();
			Date doj=Date.valueOf(local);
			pst.setDate(2,doj);
			LocalDate locall=tdoj.getValue();
			Date dojj=Date.valueOf(locall);
			pst.setDate(3, dojj);
			pst.setFloat(4, Float.parseFloat(tbill.getText()));
			pst.executeUpdate();
			pst=con.prepareStatement("update customers set dostart=? where mobile=?");
			pst.setDate(1, dojj);
			pst.setString(2, ls);
			pst.executeUpdate();
			showmg("Data Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    String ls;
    @FXML
    void doselected(MouseEvent event) {
       ls=tcom.getSelectionModel().getSelectedItem();
      try {
		pst=(PreparedStatement) con.prepareStatement("select * from customers where mobile=?");
		pst.setString(1, ls);
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			Date doj=table.getDate("dostart");
			float tp=table.getFloat("totalprice");
			tdojf.setValue(doj.toLocalDate());
			tprice.setText(tp+"");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    void filllis()
    {
    	ArrayList<String> ids=new ArrayList<String>();
    	try {
			pst= con.prepareStatement("select mobile from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				ids.add(table.getString("mobile"));
			}
			tcom.getItems().addAll(ids);
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
    filllis();
    }

}
