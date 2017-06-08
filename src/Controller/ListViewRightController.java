package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import Model.Model_Block;
import Model.Model_File;
import View.MainFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.control.ListCell;
public class ListViewRightController implements Initializable {
	/*TODO
	 * FXML濡� TextArea吏쒓린
	 * Model_File �씫�뼱�궡怨�, Model_Block �삎�깭濡� 蹂��솚, Model_Block �븯�굹�븯�굹 TextArea�뿉 �꽔
	 */

	private FileIOController fileIOController;
	private MainFrameController mainFrameController;
	
	@FXML
	private ListView<Model_Block> listView_right;
	@FXML
	private ObservableList<Model_Block> listItems = FXCollections.observableArrayList();
	@FXML
	private Model_File file;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listView_right.setCellFactory(new Callback<ListView<Model_Block>, ListCell<Model_Block>>() {
	            @Override
	            public ListCell<Model_Block> call(ListView<Model_Block> p) {
	 
	                ListCell<Model_Block> cell = new ListCell<Model_Block>() {
	                    @Override
	                    protected void updateItem(Model_Block t, boolean bln) {
	                        super.updateItem(t, bln); 
	                        
	                        if (t != null) {
	                        	int blankNum = t.getRightBlank();
	                        	StringBuilder sb = new StringBuilder();
	                        	if(!t.isModified()){
		                        	for(int j = 0 ; j < t.getRightLineInfo().size() ; j++){
		                        		sb.append(file.getLines().get((t.getRightLineInfo().get(j))).getValue()+"\t\n");
		                        	}
		                        	for(int j = 0 ; j < blankNum ; j++){
		                        		sb.append("\t\n");
		                        	}
	                        	}else if(t.isModified() && t.getFlag()==2){
	                        		for(int j = 0 ; j < t.getRightLineInfo().size() ; j++){
		                        		sb.append(fileIOController.getLeftFile().getLines().get((t.getRightLineInfo().get(j))).getValue()+"\t\n");
		                        	}
	                        	}else if(t.isModified() && t.getFlag()==1){
	                        		for(int j = 0 ; j < t.getRightLineInfo().size() ; j++){
		                        		sb.append(file.getLines().get((t.getRightLineInfo().get(j))).getValue()+"\t\n");
		                        	}
	                        	}
	                        	if(!t.isSame()){
	                        		this.getStyleClass().add("diff-cell");
	                         	}else{
	                         	}
	                        	this.setText(sb.toString());   
	                        }else{
	                        	this.getStyleClass().remove("diff-cell");
	                        	setText("");
	                        }
	                    }
	                };
	                return cell;
	            }
		 });
		 listView_right.setItems(listItems);
	}
	public void showFile(){
		file = fileIOController.getRightFile();
		listItems = fileIOController.getBlocks();
		Model_Block initBlock;
		if(listItems.isEmpty()){
			initBlock = new Model_Block(fileIOController.getLeftFile(), file);
			listItems.add(initBlock);
		}else{
			initBlock = new Model_Block(fileIOController.getLeftFile(), file);
			listItems.set(0, initBlock);
		}
		listView_right.setItems(listItems);
	}
	
	public void setControllerFileIO(FileIOController fileIOController){
		this.fileIOController = fileIOController;
	} 
	public void setMainFrameController(MainFrameController mainFrameController){
		this.mainFrameController = mainFrameController;
	}
	public ListView<Model_Block> getListViewRight(){
		return this.listView_right;
	}
	
	
	@FXML
	public void onListViewRightMouseClicked(){
		int index = listView_right.focusModelProperty().getValue().getFocusedIndex();
		fileIOController.setSelectedBlockIndex(index);
		
		if(!listView_right.getItems().get(index).isSame()){
			mainFrameController.setMerge("true");
		}
		else mainFrameController.setMerge("false");
	}
	
}
