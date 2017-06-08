package Model;

import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Model_Block {
	
	private ArrayList<Integer> leftLineInfo;
	private ArrayList<Integer> rightLineInfo;
	private int leftBlank;
	private int rightBlank;
	private int flag; 		//flag==1 getData From Left, flag==2 getDataFromright
	private boolean modified;
	private boolean isSame;
	
	public Model_Block(){
		leftLineInfo = new ArrayList<Integer>();
		rightLineInfo = new ArrayList<Integer>();
		leftBlank = 0;
		rightBlank = 0;
		flag = 0;
		modified = false;
		isSame = true;
		
	}
	public Model_Block(Model_File fileleft, Model_File fileright){
		leftLineInfo = new ArrayList<Integer>();
		rightLineInfo = new ArrayList<Integer>();
		leftBlank = 0;
		rightBlank = 0;
		flag = 0;
		modified = false;
		isSame = true;
		ArrayList<Integer> all = new ArrayList<Integer>();
		ArrayList<Integer> alr = new ArrayList<Integer>();
		try{
			for (int i = 0 ; i < fileleft.getLines().size() ; i++){
				all.add(i);
			}
		}catch (NullPointerException e){
			System.out.println("Right only ");
		}
		try{
			for (int i = 0 ; i < fileright.getLines().size() ; i++){
				alr.add(i);
			}
		}catch (NullPointerException e){
			System.out.println("Left only ");
		}
		setLeftLineInfo(all);
		setRightLineInfo(alr);
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	//getter
	public boolean isModified(){
		return modified;
	}
	public boolean isSame(){
		return isSame;
	}
	//setter
	public ArrayList<Integer> getLeftLineInfo() {
		return leftLineInfo;
	}
	public void setLeftLineInfo(ArrayList<Integer> leftLineInfo) {
		this.leftLineInfo = leftLineInfo;
	}
	public ArrayList<Integer> getRightLineInfo() {
		return rightLineInfo;
	}
	public void setRightLineInfo(ArrayList<Integer> rightLineInfo) {
		this.rightLineInfo = rightLineInfo;
	}
	public int getLeftBlank() {
		return leftBlank;
	}
	public void setLeftBlank(int leftBlank) {
		this.leftBlank = leftBlank;
	}
	public int getRightBlank() {
		return rightBlank;
	}
	public void setRightBlank(int rightBlank) {
		this.rightBlank = rightBlank;
	}
	public void setisModified(boolean modified){
		this.modified = modified;
	}
	public void setisSame(boolean isSame){
		this.isSame = isSame;
	}
	//TODO
	/*public ObservableList<String> toString(Model_File file, int flag){
		
		if(flag == 1){
			for(int i = 0 ; i < this.leftLineInfo.size(); i++){
				file.getLines().get(this.leftLineInfo.get(i));
			}
		}
		return ;
	}*/
}
