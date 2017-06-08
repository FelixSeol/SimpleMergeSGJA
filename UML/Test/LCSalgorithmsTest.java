package Controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LCSalgorithmsTest {
   private ObservableList<StringProperty> X;
   private ObservableList<StringProperty> Y;
   private ObservableList<StringProperty> X2;
   private ObservableList<StringProperty> Y2;
   
   @Before
   public void setUpLCSalgorithms(){
      X = FXCollections.observableArrayList();
      Y = FXCollections.observableArrayList();
      X2 = FXCollections.observableArrayList();
      Y2 = FXCollections.observableArrayList();
   }
   
   @After
   public void tearDownLCSalgorithms(){
      this.X = null;
      this.Y = null;
      this.X2 = null;
      this.Y2 = null;
   }
   /*
    * same stringArray
    * same result..
    * */
   @Test
   public void testLCSalgorithms() {
      X.add(new SimpleStringProperty("a"));
      Y.add(new SimpleStringProperty("a"));
      X2.add(new SimpleStringProperty("a"));
      Y2.add(new SimpleStringProperty("a"));
      LCSalgorithms case1 = new LCSalgorithms(X,Y);
      LCSalgorithms case2 = new LCSalgorithms(X2,Y2);
      
      assertTrue(case1.equals(case2));
   }
   
   /*
    * different stringArray
    * different result..
    * */
   @Test
   public void testLCSalgorithms2() {
      X.add(new SimpleStringProperty("a"));
      Y.add(new SimpleStringProperty("a"));
      X2.add(new SimpleStringProperty("a"));
      Y2.add(new SimpleStringProperty("b"));
      
      LCSalgorithms case1 = new LCSalgorithms(X,Y);
      LCSalgorithms case2 = new LCSalgorithms(X2,Y2);
      
      assertFalse(case1.equals(case2));
   }
   /*
    * [a,b,c] and [a,a,b]
    * have LCS length 2;
    * X index 0, 1
    * and Y index 0, 2
    * */
   @Test
   public void testLCSalgorithms3() {
      X.add(new SimpleStringProperty("a"));
      X.add(new SimpleStringProperty("b"));
      X.add(new SimpleStringProperty("c"));
      Y.add(new SimpleStringProperty("a"));
      Y.add(new SimpleStringProperty("a"));
      Y.add(new SimpleStringProperty("b"));
      
      LCSalgorithms case1 = new LCSalgorithms(X,Y);
            
      assertTrue(case1.getLCSlength()== 2);
      assertTrue(case1.X_Index.get(0) == 1);
      assertTrue(case1.X_Index.get(1) == 0);
      assertTrue(case1.Y_Index.get(0) == 2);
      assertTrue(case1.Y_Index.get(1) == 1);
   }
   /*
    * [a] and [b]
    * have LCS length 0;
    * */
   @Test
   public void testLCSalgorithms4() {
      X.add(new SimpleStringProperty("a"));
      Y.add(new SimpleStringProperty("b"));
      
      LCSalgorithms case1 = new LCSalgorithms(X,Y);
            
      assertTrue(case1.getLCSlength()== 0);
   }
   
}