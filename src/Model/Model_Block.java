package Model;

public class Model_Block {
	/*
	 * startIndex : ���� �����ϴ� index
	 * lastIndex : ���� ������ Index;
	 * blank : ���� ���� ��  ��
	 * modified: merge�� ���� ������ �ƴ���
	 * */
	private int startIndex;
	private int lastIndex;
	private int blank;
	private boolean modified;
	private boolean isSame = false;
	
	public Model_Block(int startIndex,int lastIndex,int blank){
		this.startIndex = startIndex;
		this.lastIndex = lastIndex;
		this.blank = blank;
	}
	//getter
	public int getStartIndex(){
		return startIndex;
	}
	public int getLastIndex(){
		return lastIndex;
	}
	public int getBlank(){
		return blank;
	}
	public boolean isModified(){
		return modified;
	}
	public boolean isSame(){
		return isSame;
	}
	//setter
	public void setStartIndex(int startIndex){
		this.startIndex = startIndex;
	}
	public void setLastIndex(int lastIndex){
		this.lastIndex = lastIndex;
	}
	public void setBlank(int blank){
		this.blank = blank;
	}
	public void setisModified(boolean modified){
		this.modified = modified;
	}
	public void setisSame(boolean isSame){
		this.isSame = isSame;
	}
}
