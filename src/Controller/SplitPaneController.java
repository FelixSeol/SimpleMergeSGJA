
package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Controller.FileIOController;
import View.EditingWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

public class SplitPaneController implements Initializable {
	private static int FILE_LEFT = 0;
	private static int FILE_RIGHT = 1;
	private FileIOController fileIOController;
	private MainFrameController mainFrameController;
	private boolean rightEditFlag = false;
	private boolean leftEditFlag = false;
	
	@FXML
	private SplitPane split_text_frame;
	@FXML
	private BorderPane border_left_frame, border_right_frame;
	@FXML
	private MenuBar menuBar_left, menuBar_right;
	@FXML
	private Menu menu_left_file, menu_left_edit,
				menu_right_file, menu_right_edit;
	@FXML
	private MenuItem menuItem_left_load, menuItem_left_save, menuItem_left_saveas, menuItem_left_close, menuItem_left_editOn, menuItem_left_editOff,
					menuItem_right_load, menuItem_right_save, menuItem_right_saveas, menuItem_right_close, menuItem_right_editOn, menuItem_right_editOff;
	
	@FXML
	private Parent listView_left;
	@FXML
	private ListViewLeftController listView_leftController;
	@FXML
	private ListViewRightController listView_rightController;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		menuBar_left = null; menuBar_right = null;
		menu_left_file = null; menu_left_edit = null; menu_right_file = null; menu_right_edit = null;

		
		setLeftDisableButton("true","false","false","false","false","false");
		setRightDisableButton("true","false","false","false","false","false");
	
	}
	
	@FXML
 	public void leftLoadOnAction(){
		FileChooser filechooser = myFileChooser("Left FileChooser");
		File file = filechooser.showOpenDialog(null);
		try {
			fileIOController.fileLoad(file.getAbsolutePath().toString(), FILE_LEFT);
			listView_leftController.showFile();
			setLeftDisableButton("true","true","true","true","true","false");	
			checkCompareButton();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		System.out.println("Left LOAD!");
	}
	
	@FXML
 	public void leftSaveOnAction() throws IOException{
		this.fileIOController.fileSave(FILE_LEFT);
 		System.out.println("Left SAVE!");
	}
	
	@FXML
 	public void leftSaveasOnAction(){
 		System.out.println("Left SAVEAS!");
	}
	
	@FXML
 	public void leftCloseOnAction(){
 		System.out.println("Left CLOSE!");
 		setLeftDisableButton("true","false","false","false","false","false");
		checkCompareButton();
 		fileIOController.setLeftFile(null);
		listView_leftController.showFile();
		listView_rightController.showFile();
	}
	
	@FXML

 	public void leftEditOnOnAction(){
 		System.out.println("Left EDIT!");
 		leftEditFlag = true;
 	 	setLeftDisableButton("false","false","false","false","false","true");
 	 	checkCompareButton();
 	 	fileIOController.getBlocks().clear();
		listView_leftController.showFile();
		listView_rightController.showFile();
		EditingWindow ew = new EditingWindow(fileIOController.getLeftFile());
	}
	public void leftEditOffOnAction(){
 		System.out.println("Left EDITOFF!");
 		leftEditFlag = false;
 		listView_leftController.showFile();
 		setLeftDisableButton("true","true","true","true","true","false");
 		checkCompareButton();
	}
	
	@FXML
 	public void rightLoadOnAction(){
		System.out.println("Right LOAD!");
		FileChooser filechooser = myFileChooser("Right FileChooser");
		File file = filechooser.showOpenDialog(null);
		
		try {
			fileIOController.fileLoad(file.getAbsolutePath().toString(), FILE_RIGHT);
			listView_rightController.showFile();
			setRightDisableButton("true","true","true","true","true","false");
			checkCompareButton();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
 	public void rightSaveOnAction(){
 		System.out.println("Right SAVE!");
	}
	
	@FXML
 	public void rightSaveasOnAction(){
 		System.out.println("Right SAVEAS!");
	}
	
	@FXML
 	public void rightCloseOnAction(){
		System.out.println("Right CLOSE!");
		setRightDisableButton("true","false","false","false","false","false");
		checkCompareButton();
		fileIOController.setRightFile(null);
		listView_leftController.showFile();
		listView_rightController.showFile();
	}
	
	@FXML
 	public void rightEditOnOnAction(){
		System.out.println("Right EDIT!");
 		rightEditFlag = true;
 	 	setRightDisableButton("false","false","false","false","false","true");
 	 	checkCompareButton();
 		fileIOController.getBlocks().clear();
		listView_leftController.showFile();
		listView_rightController.showFile();
		EditingWindow ew = new EditingWindow(fileIOController.getRightFile());
	}
	
	public void rightEditOffOnAction(){
 		System.out.println("Right EDITOFF!");
 		setRightDisableButton("true","true","true","true","true","false");
 		rightEditFlag = false;
 		checkCompareButton();
 		listView_rightController.showFile();
	}
	
	public void setControllerFileIO(FileIOController fileIOController){
		this.fileIOController = fileIOController;
		listView_leftController.setControllerFileIO(fileIOController);
		listView_rightController.setControllerFileIO(fileIOController);
	}
	
	public void setMainFrameController(MainFrameController mainFrameController){
		this.mainFrameController = mainFrameController;
		listView_leftController.setMainFrameController(mainFrameController);
		listView_rightController.setMainFrameController(mainFrameController);
	}
	
	public ListViewLeftController getListViewLeftController(){
		return this.listView_leftController;
	}
	public ListViewRightController getListViewRightController(){
		return this.listView_rightController;
	}
	
	public void foo(String foo) {
	       System.out.println(foo);
	    }

	/*FileChooser*/
	FileChooser myFileChooser(String name){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(name);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files","*.txt","*.c","*.java"),
				new FileChooser.ExtensionFilter("Text Files","*.txt")
				);
		return fileChooser;
	}
	
	private void setLeftDisableButton(String load, String save, String saveas, String close, String editOn, String editOff){
		boolean myload = load == "true" ? true : false;
		boolean mysave = save == "true" ? true : false;
		boolean mysaveas = saveas == "true" ? true : false;
		boolean myclose = close == "true" ? true : false;
		boolean myeditOn = editOn == "true" ? true : false;
		boolean myeditOff = editOff == "true" ? true : false;

		if(!myload) menuItem_left_load.setDisable(true);
		else menuItem_left_load.setDisable(false);
		
		if(!mysave) menuItem_left_save.setDisable(true);
		else menuItem_left_save.setDisable(false);
		
		if(!mysaveas) menuItem_left_saveas.setDisable(true);
		else menuItem_left_saveas.setDisable(false);
		
		if(!myclose) menuItem_left_close.setDisable(true);
		else menuItem_left_close.setDisable(false);
		
		if(!myeditOn) menuItem_left_editOn.setDisable(true);
		else menuItem_left_editOn.setDisable(false);	
		
		if(!myeditOff) menuItem_left_editOff.setDisable(true);
		else menuItem_left_editOff.setDisable(false);	
	}
	
	private void setRightDisableButton(String load, String save, String saveas, String close, String editOn, String editOff){
		boolean myload = load == "true" ? true : false;
		boolean mysave = save == "true" ? true : false;
		boolean mysaveas = saveas == "true" ? true : false;
		boolean myclose = close == "true" ? true : false;
		boolean myeditOn = editOn == "true" ? true : false;
		boolean myeditOff = editOff == "true" ? true : false;
		
		if(!myload) menuItem_right_load.setDisable(true);
		else menuItem_right_load.setDisable(false);
		
		if(!mysave) menuItem_right_save.setDisable(true);
		else menuItem_right_save.setDisable(false);
		
		if(!mysaveas) menuItem_right_saveas.setDisable(true);
		else menuItem_right_saveas.setDisable(false);
		
		if(!myclose) menuItem_right_close.setDisable(true);
		else menuItem_right_close.setDisable(false);
		
		if(!myeditOn) menuItem_right_editOn.setDisable(true);
		else menuItem_right_editOn.setDisable(false);
	
		if(!myeditOff) menuItem_right_editOff.setDisable(true);
		else menuItem_right_editOff.setDisable(false);
	}

	public void checkCompareButton(){
		if(fileIOController.getBlocks().size()==1){
			if(fileIOController.getLeftFile()!=null && fileIOController.getRightFile()!= null){
				if(leftEditFlag || rightEditFlag){
					mainFrameController.setCompare("false");
				}
				else mainFrameController.setCompare("true");
			}
			else mainFrameController.setCompare("false");
		}else{
			mainFrameController.setCompare("false");
		}
	}
}
