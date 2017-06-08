package Controller;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Model_Block;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MergeCodeTest {

	FileIOController mock_controller;
	MergeCode mock_merge;
	Model_Block block;
	
	@Before
	public void setUp(){
		this.mock_controller = EasyMock.createMock(FileIOController.class);
		this.block = new Model_Block();
		this.block.setLeftBlank(3);
		this.block.setRightBlank(0);
		this.block.getLeftLineInfo().add(1);
		this.block.getLeftLineInfo().add(2);
		this.block.getLeftLineInfo().add(3);
		this.block.getRightLineInfo().add(4);
		this.block.getRightLineInfo().add(5);
		this.block.getRightLineInfo().add(6);
	}
	@After
	public void tearDown(){
		EasyMock.verify(this.mock_controller);
	}
	
	@Test
	public void testCopyRightToleft() {
		ObservableList<Model_Block> blocks = FXCollections.observableArrayList();
		blocks.add(this.block);
		EasyMock.expect(this.mock_controller.getSelectedBlockIndex()).andReturn(0);
		EasyMock.expect(this.mock_controller.getBlocks()).andReturn(blocks).times(8);
		EasyMock.replay(this.mock_controller);
		this.mock_merge = new MergeCode(this.mock_controller);
		this.mock_merge.copyRightToleft();
		
		assertTrue(blocks.get(0).getFlag()==1);
		assertTrue(blocks.get(0).getLeftLineInfo()==blocks.get(0).getRightLineInfo());
		assertTrue(blocks.get(0).getLeftLineInfo().get(0)==4);
	}

	@Test
	public void testCopyLeftToRight() {
		ObservableList<Model_Block> blocks = FXCollections.observableArrayList();
		blocks.add(this.block);
		EasyMock.expect(this.mock_controller.getSelectedBlockIndex()).andReturn(0);
		EasyMock.expect(this.mock_controller.getBlocks()).andReturn(blocks).times(8);
		EasyMock.replay(this.mock_controller);
		this.mock_merge = new MergeCode(this.mock_controller);
		this.mock_merge.copyLeftToRight();
		
		assertTrue(blocks.get(0).getFlag()==2);
		assertTrue(blocks.get(0).getRightLineInfo()==blocks.get(0).getLeftLineInfo());
		assertTrue(blocks.get(0).getRightLineInfo().get(0)==1);
	}
}
