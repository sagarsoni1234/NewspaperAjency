/**
 * Sample Skeleton for 'adminview.fxml' Controller Class
 */

package admindahboard;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class adminviewcontroller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void doareamas(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/areamaster/areas.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dobillhis(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/billhistory/billview.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void docolbill(ActionEvent event) {

    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/billcollector/bills.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doencus(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/customerpanel/customer.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dogenbill(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/billcreator/bill.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dohawkmas(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/hawkersboard/hawkers.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dopepmas(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/papermaster/paper.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doshowcus(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/Customersdata/customersview.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void doshowhaw(ActionEvent event) {
    	try{
    		javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/HawkerData/hawkerveiw.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			javafx.stage.Stage stage=new javafx.stage.Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

}
