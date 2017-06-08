package Controller;

import Model.Model_Block;

public class MergeCode {
	FileIOController fileIO;
	int blockIndex;
	
	public MergeCode(FileIOController fileIO){
		this.fileIO = fileIO;
		this.blockIndex = fileIO.getSelectedBlockIndex();
		
	}
		
	public void copyRightToleft(){
		this.fileIO.getBlocks().get(this.blockIndex).setFlag(1);
		this.fileIO.getBlocks().get(this.blockIndex).setLeftLineInfo(this.fileIO.getBlocks().get(this.blockIndex).getRightLineInfo());
		this.fileIO.getBlocks().get(this.blockIndex).setLeftBlank(this.fileIO.getBlocks().get(this.blockIndex).getRightBlank());
		this.fileIO.getBlocks().get(this.blockIndex).setisModified(true);
		this.fileIO.getBlocks().set(this.blockIndex, this.fileIO.getBlocks().get(this.blockIndex));
		if(this.fileIO.getBlocks().get(this.blockIndex).getRightLineInfo().isEmpty()||this.fileIO.getBlocks().get(this.blockIndex).getLeftLineInfo().isEmpty()){
			deleteUselessBlock();
		}
	} 
	
	public void copyLeftToRight(){
		this.fileIO.getBlocks().get(this.blockIndex).setFlag(2);
		this.fileIO.getBlocks().get(this.blockIndex).setRightLineInfo(this.fileIO.getBlocks().get(this.blockIndex).getLeftLineInfo());
		this.fileIO.getBlocks().get(this.blockIndex).setRightBlank(this.fileIO.getBlocks().get(this.blockIndex).getLeftBlank());
		this.fileIO.getBlocks().get(this.blockIndex).setisModified(true);
		this.fileIO.getBlocks().set(this.blockIndex, this.fileIO.getBlocks().get(this.blockIndex));
		if(this.fileIO.getBlocks().get(this.blockIndex).getRightLineInfo().isEmpty()||this.fileIO.getBlocks().get(this.blockIndex).getLeftLineInfo().isEmpty()){
			deleteUselessBlock();
		}
	} 
	
	public void deleteUselessBlock(){
		if(blockIndex==0||blockIndex==this.fileIO.getBlocks().size()-1){
			this.fileIO.getBlocks().remove(blockIndex);
		}else{
			Model_Block block = new Model_Block();
			block.setLeftLineInfo(fileIO.getBlocks().get(this.blockIndex-1).getLeftLineInfo());
			for(int i = 0 ; i < this.fileIO.getBlocks().get(this.blockIndex+1).getLeftLineInfo().size() ; i++){
				block.getLeftLineInfo().add(this.fileIO.getBlocks().get(this.blockIndex+1).getLeftLineInfo().get(i));
			}
			block.setLeftBlank(fileIO.getBlocks().get(this.blockIndex-1).getLeftBlank()+fileIO.getBlocks().get(this.blockIndex+1).getLeftBlank());
			
			block.setRightLineInfo(fileIO.getBlocks().get(this.blockIndex-1).getRightLineInfo());
			for(int i = 0 ; i < this.fileIO.getBlocks().get(this.blockIndex+1).getRightLineInfo().size() ; i++){
				block.getRightLineInfo().add(this.fileIO.getBlocks().get(this.blockIndex+1).getRightLineInfo().get(i));
			}
			block.setRightBlank(fileIO.getBlocks().get(this.blockIndex-1).getRightBlank()+fileIO.getBlocks().get(this.blockIndex+1).getRightBlank());
			
			fileIO.getBlocks().set(blockIndex-1, block);
			fileIO.getBlocks().remove(blockIndex);
			fileIO.getBlocks().remove(blockIndex);
		}
	}
}
