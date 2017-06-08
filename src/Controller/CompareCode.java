package Controller;

import Controller.FileIOController;
import Model.Model_File;
import Model.Model_Block;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CompareCode {
	//Model_File left;
	//Model_File right;
	ObservableList<StringProperty> file1Lines;
	ObservableList<StringProperty> file2Lines;
	//ObservableList<Model_Block> blocks;
	FileIOController fileIO;
	LCSalgorithms LCS;
	
	
	CompareCode(FileIOController fileIO){
		this.fileIO = fileIO;
		//this.fileIO. = fileIO.getLeftFile(); // left
		//this.right = fileIO.getRightFile(); //FXCollections.observableArrayList();
	//	this.blocks = this.fileIO.getBlocks(); //FXCollections.observableArrayList();
	}
	
	public void createBlock(){

		this.file1Lines = this.fileIO.getLeftFile().getLines();
		this.file2Lines = this.fileIO.getRightFile().getLines();
		this.fileIO.getLeftFile().setIsCompare(true); //file1 is compared
		this.fileIO.getRightFile().setIsCompare(true); // file2 is compared
		
		LCS = new LCSalgorithms(file1Lines, file2Lines); //do LCS algorithms
		fileIO.getBlocks().clear();
		int priorIndex1 = -1, priorIndex2 = -1;
		int currentIndex1, currentIndex2;
		for(int i = LCS.getLCSlength() -1 ; i >= 0 ; i--){
			currentIndex1 = LCS.X_Index.get(i);
			currentIndex2 = LCS.Y_Index.get(i);
			
			if(hasPriorBlock(priorIndex1, priorIndex2, currentIndex1, currentIndex2)){
				insertBlock(priorIndex1, priorIndex2, currentIndex1, currentIndex2);
			}
			//currentBlock insert
			insertCurrentBlock(priorIndex1, priorIndex2, currentIndex1, currentIndex2);
			
			priorIndex1 = currentIndex1;
			priorIndex2 = currentIndex2;
		}
		//insertLastblock
		insertLastBlock(priorIndex1, priorIndex2, this.file1Lines.size()-1, this.file2Lines.size()-1);
	
	}
	public boolean hasPriorBlock(int prior1, int prior2, int current1, int current2){
		if(current1 - prior1 != 1 || current2 - prior2 != 1)
			return true;
		return false;
	}
	public void insertBlock(int prior1, int prior2, int current1, int current2){
		int blockSize;
		ObservableList<Model_Block> blocks = this.fileIO.getBlocks();
		Model_Block block = new Model_Block();
<<<<<<< HEAD

=======
		block.setisSame(false);
>>>>>>> 33175c58192a13640365c46b614e3e34cff352f6
		insertLineInfo(block, prior1, current1, prior2,current2);
		//insertLineInfo(block2, prior2, current2);
		
		if(current1-prior1 >= current2-prior2)
			blockSize = current1-prior1-1;
		else
			blockSize = current2-prior2-1;
		
		block.setLeftBlank(blockSize - block.getLeftLineInfo().size());
		block.setRightBlank(blockSize - block.getRightLineInfo().size());
		
		blocks.add(block);
	}
	
	public void insertLineInfo(Model_Block block, int leftStart, int leftEnd, int rightStart, int rightEnd){
		for(int i = leftStart+1 ; i < leftEnd; i++){
			block.getLeftLineInfo().add(i);
		}
		for(int i = rightStart+1 ; i < rightEnd; i++){
			block.getRightLineInfo().add(i);
		}
	}
	public void insertCurrentBlock(int prior1, int prior2, int current1, int current2){
		
		ObservableList<Model_Block> blocks = this.fileIO.getBlocks();
		Model_Block block = new Model_Block();
		block.setisSame(true);
		
		if(!hasPriorBlock(prior1, prior2, current1, current2)){
			if(blocks.size() == 0){
				block.getLeftLineInfo().add(current1);
				block.getRightLineInfo().add(current2);
				blocks.add(block);
			}
			else{ 
				blocks.get(blocks.size()-1).getLeftLineInfo().add(current1);
				blocks.get(blocks.size()-1).getRightLineInfo().add(current2);
			}		
		}
		
		else{
			block.getLeftLineInfo().add(current1);
			block.getRightLineInfo().add(current2);
			blocks.add(block);
		}
		
		return;
	}
	public void insertLastBlock(int start1, int start2, int last1, int last2){
		if(start1 == last1 && start2 == last2) return;
		
		insertBlock(start1, start2, last1+1, last2+1);
		return;
	}
	public void foo(){
		System.out.println("foo");
	}
}
