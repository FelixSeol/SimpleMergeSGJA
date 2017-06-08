
package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.jws.soap.SOAPBinding.Use;

import Controller.FileIOController;
import View.MainFrame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainFrameController implements Initializable {

	private MainFrame mainFrame;
	private CompareCode compareCode;
	private FileIOController fileIOController;
	@FXML
	private BorderPane border_main_frame;
	@FXML
	private ButtonBar buttonBar_main;
	@FXML
	private Button button_compare;
	@FXML
	private SplitMenuButton splitMenu_merge;
	@FXML
	private MenuItem menuItem_lefttoright, menuItem_righttoleft;
	@FXML
	private Parent split_text_frame;
	@FXML
	private SplitPaneController split_text_frameController; 		//Nested Controllers
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		buttonBar_main = null;
		menuItem_lefttoright = null;
		menuItem_righttoleft = null;
		
		button_compare.setDisable(true);
		splitMenu_merge.setDisable(true);
		
		fileIOController = new FileIOController();
		split_text_frameController.setControllerFileIO(fileIOController);
		split_text_frameController.setMainFrameController(this);
		
	}
	
	public void setCompare(String compare){
		boolean myCompare = compare == "true" ? true : false;
		if(!myCompare) button_compare.setDisable(true);
		else button_compare.setDisable(false);
	}
	
	public void setMerge(String merge){
		boolean myMerge = merge == "true" ? true : false;
		if(!myMerge) splitMenu_merge.setDisable(true);
		else splitMenu_merge.setDisable(false);
	}
	
	
	@FXML
 	public void compareOnAction(){
 		compareCode = new CompareCode(fileIOController);
 		compareCode.createBlock();
 		System.out.println("COMPARE!");
 		ListViewLeftController lc = split_text_frameController.getListViewLeftController();
 		ListViewRightController rc = split_text_frameController.getListViewRightController();
 		ScrollbarBinding.bind(lc.getListViewLeft(), rc.getListViewRight(), true);
 		
 	}
	
	@FXML
	public void lefttorightOnAction(){
		System.out.println("Left To Right MERGE!");
		MergeCode mc = new MergeCode(fileIOController);
		mc.copyLeftToRight();
	}
	
	@FXML
	public void righttoleftOnAction(){
		System.out.println("Right To Left MERGE!");
		MergeCode mc = new MergeCode(fileIOController);
		mc.copyRightToleft();
	}	
	
	public void setMainFrame(MainFrame mainFrame){
		this.mainFrame = mainFrame;
	}
	
}
