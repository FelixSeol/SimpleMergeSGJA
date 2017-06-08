package Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Model_Block;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompareCodeTest {

	private FileIOController mock_controller;
	private Model_Block mock_block;
	private CompareCode compareCodeCase1;
	
	@Before
	public void setUp(){
		
		this.mock_block = EasyMock.createMock(Model_Block.class);
		this.mock_controller = EasyMock.createMock(FileIOController.class);
		
	}
	@After
	public void tearDown(){
		
	}
	@Test
	public void testHasPriorBlock() {

		EasyMock.replay(this.mock_controller);
		this.compareCodeCase1 = new CompareCode(this.mock_controller);

		assertTrue(this.compareCodeCase1.hasPriorBlock(0, 2, 0, 2));
		EasyMock.verify(this.mock_controller);
	}

	@Test
	public void testInsertBlock() {
		
		int blockSize = 3;
		
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		ObservableList<Model_Block> blocks = FXCollections.observableArrayList();

		this.mock_block = EasyMock.createMock(Model_Block.class);
		this.mock_controller = EasyMock.createMock(FileIOController.class);
		EasyMock.expect(this.mock_block.getLeftLineInfo()).andReturn(left).times(3);
		EasyMock.expect(this.mock_block.getRightLineInfo()).andReturn(right).times(3);
		EasyMock.expect(this.mock_controller.getBlocks()).andReturn(blocks);
		EasyMock.expect(this.mock_block.getLeftBlank()).andReturn(0);
		EasyMock.expect(this.mock_block.getRightBlank()).andReturn(0);
		EasyMock.expectLastCall().andAnswer(new IAnswer<Object>(){
			public Object answer() throws Throwable{
				mock_block.setLeftBlank(0);
				return null;
			}
		});
		this.mock_block.setRightBlank(blockSize - 1);
		EasyMock.expectLastCall().andAnswer(new IAnswer<Object>(){
			public Object answer() throws Throwable{
				mock_block.setRightBlank(2);
				return null;
			}
		});
		EasyMock.replay(this.mock_block);
		EasyMock.replay(this.mock_controller);
		
		this.compareCodeCase1 = new CompareCode(this.mock_controller);
		this.compareCodeCase1.insertBlock(0, 0, 2, 2);
		
		assertTrue(this.mock_block.getLeftBlank() == 0);
		assertTrue(this.mock_block.getRightBlank() == 0);
		
	}	
	@Test
	public void testInsertLineInfo() {

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		EasyMock.expect(this.mock_block.getLeftLineInfo()).andReturn(left).times(3);
		EasyMock.expect(this.mock_block.getRightLineInfo()).andReturn(right).times(3);
		EasyMock.replay(this.mock_block);
		this.compareCodeCase1 = new CompareCode(this.mock_controller);
		this.compareCodeCase1.insertLineInfo(mock_block, 0, 2, 0, 2);
		
		assertTrue(this.mock_block.getLeftLineInfo().get(0) == 1);
		assertTrue(this.mock_block.getRightLineInfo().get(0) == 1);
		assertEquals(this.mock_block.getLeftLineInfo(), this.mock_block.getRightLineInfo());
		EasyMock.verify(this.mock_block);
	}
	

	@Test
	public void testInsertCurrentBlock() {

		ObservableList<Model_Block> blocks = FXCollections.observableArrayList();
		EasyMock.expect(this.mock_controller.getBlocks()).andReturn(blocks);
		this.compareCodeCase1 = new CompareCode(this.mock_controller);
		EasyMock.replay(this.mock_controller);
		
		this.compareCodeCase1.insertCurrentBlock(0, 0, 2, 2);
		
		assertTrue(blocks.get(0).getLeftLineInfo().size() == 1);
		assertTrue(blocks.get(0).getRightLineInfo().size() == 1);
	}

	@Test(expected = RuntimeException.class)
	public void testInsertLastBlock() {
		
		this.compareCodeCase1 = new CompareCode(this.mock_controller);
		this.compareCodeCase1.insertLastBlock(0, 2, 0, 3);
		EasyMock.replay();
		
		
	}

}
