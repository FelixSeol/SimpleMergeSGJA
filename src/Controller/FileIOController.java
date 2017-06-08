package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Model_Block;
import Model.Model_File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileIOController {

	private BufferedReader bufferedReader = null;
	private BufferedWriter bufferedWriter = null;
	private Model_File leftFile = null;
	private Model_File rightFile = null;
	private ObservableList<Model_Block> blocks = null;

	private Scanner in;

	private int selectedBlockIndex;
	
	public Model_File getLeftFile() {return leftFile;}
	public void setLeftFile(Model_File leftFile) {this.leftFile = leftFile;}
	public Model_File getRightFile() {return rightFile;}
	public void setRightFile(Model_File rightFile) {this.rightFile = rightFile;}
	public BufferedReader getBufferedReader() {return bufferedReader;}	
	public void setBufferedReader(BufferedReader bufferedReader) {this.bufferedReader = bufferedReader;}	
	public void newBufferedReader(String fileName) throws FileNotFoundException{this.bufferedReader = new BufferedReader(new FileReader(fileName));}	
	public ObservableList<Model_Block> getBlocks() {return blocks;}	
	public void setBlocks(ObservableList<Model_Block> blocks) {this.blocks = blocks;}	
	public BufferedWriter getBufferedWriter() {return bufferedWriter;}
	public void setBufferedWriter(BufferedWriter bufferedWriter) {this.bufferedWriter = bufferedWriter;}
	public void newBufferedWriter(String fileName) throws IOException{this.bufferedWriter = new BufferedWriter(new FileWriter(fileName));}
	public Scanner getIn() {return in;}
	public void setIn(Scanner in) {this.in = in;}

	public FileIOController(){ blocks = FXCollections.observableArrayList(); }

	
	public void setSelectedBlockIndex(int selectedBlockIndex){ this.selectedBlockIndex = selectedBlockIndex; }
	
	public int getSelectedBlockIndex(){ return this.selectedBlockIndex; }
	
	public void fileLoad(String fileName, int leftOrRight) throws IOException {
		
		Model_File new_file = new Model_File(fileName);
		this.newBufferedReader(fileName);
		this.in = new Scanner(this.bufferedReader);
		String s;
		
		while((s = this.getBufferedReader().readLine()) != null)		
			new_file.setLinesLineByLine(s);
		switch(leftOrRight){
		case 0:
			this.leftFile = new_file;
			break;
		case 1:
			this.rightFile = new_file;
			break;
		default:
			System.out.println("Load operation has been failed");
			System.exit(1);
		}
	}
	public void saveWithNoChange(Model_File file) throws IOException {
		
		for(int i = 0; i< file.getLines().size(); i++){
			this.bufferedWriter.write(file.getLines().get(i).getValue());
			this.bufferedWriter.newLine();
		}
	}
	public void saveLeftWithChange(Model_File file) throws IOException {
		
		for(int i = 0; i < this.blocks.size(); i++)
			this.useInfoToSave(i, this.blocks.get(i).getLeftLineInfo());
	}
	public void saveRightWithChange(Model_File file) throws IOException {
		
		for(int i = 0; i < this.blocks.size(); i++)
			this.useInfoToSave(i, this.blocks.get(i).getRightLineInfo());
	}
	public void useInfoToSave(int index, ArrayList<Integer> lineInfo) throws IOException{
		
		int k;
		
		for(int i = 0; i < lineInfo.size(); i++){
			k = lineInfo.get(i);
			
			if(this.blocks.get(i).getFlag() == 1)	this.write(this.leftFile, k);
			else									this.write(this.rightFile, k);
		}
	}
	
	public void write(Model_File file, int index) throws IOException {	this.bufferedWriter.write(file.getLines().get(index).getValue());	}
	public void fileSave(int leftOrRight) throws IOException{
		
		if(this.blocks.size() == 1){	//compare,merge 占쏙옙占쏙옙 占쏙옙占�
			System.out.println("x");
			switch(leftOrRight){
			case 0:	//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.newBufferedWriter(this.leftFile.getfileName());
				this.saveWithNoChange(this.leftFile);
				break;
			case 1:	//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.newBufferedWriter(this.rightFile.getfileName());
				this.saveWithNoChange(this.rightFile);
				break;
			default:
				System.out.println("Save operation has been failed");
				System.exit(1);
				break;
			}
		}
		else{	//compare, merge占쏙옙 占쏙옙占�
			switch(leftOrRight){
			case 0:	//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙	
				this.newBufferedWriter(this.leftFile.getfileName());
				this.saveLeftWithChange(this.leftFile);
				break;
			case 1:	//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.newBufferedWriter(this.rightFile.getfileName());
				this.saveRightWithChange(this.rightFile);
				break;
			default:
				System.out.println("Save operation has been failed");
				System.exit(1);
				break;
			}
		}
		this.bufferedWriter.close();
	}
	public void fileSaveAs(int leftOrRight, String fileName) throws IOException{
		
		this.newBufferedWriter(fileName);
		
		if(this.blocks.size() == 1){	//compare,merge 占쏙옙占쏙옙 占쏙옙占�
			switch(leftOrRight){
			case 0:	//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.saveWithNoChange(this.leftFile);
				break;
			case 1:	//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.saveWithNoChange(this.rightFile);
				break;
			default:
				System.out.println("SaveAs operation has been failed");
				System.exit(1);
				break;
			}
		}
		else{	//compare, merge占쏙옙 占쏙옙占�
			switch(leftOrRight){
			case 0:	//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.saveLeftWithChange(this.leftFile);
				break;
			case 1:	//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				this.saveRightWithChange(this.rightFile);
				break;
			default:
				System.out.println("SaveAs operation has been failed");
				System.exit(1);
				break;
			}
		}
		this.bufferedWriter.close();
	}
}
