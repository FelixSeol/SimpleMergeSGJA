package Controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;

/**
 * Created by JRD on 03/03/2015.
 * reference : http://www.dskims.com/is-there-any-way-to-sync-the-scrollbars-in-a-javafx-1-2-listview/
 */

/*
 * Compare 이후 Edit등에의해 파일상태로 돌아올떄 해제
 */
public class ScrollbarBinding {

    /**
     * The constant BIND_BIDIRECTIONAL.
     */
    public static final int BIND_BIDIRECTIONAL = 3;
    /**
     * The constant BIND_RIGHT_TO_LEFT.
     */
    public static final int BIND_RIGHT_TO_LEFT = 1;
    /**
     * The constant BIND_LEFT_TO_RIGHT.
     */
    public static final int BIND_LEFT_TO_RIGHT = 2;

    /**
     * Bidirectional binding of 2 ListView's scrollbars.
     *
     * @param lv1 the lv 1
     * @param lv2 the lv 2
     */
    public static void sbind(ListView lv1, ListView lv2, boolean bindorunbind) {
    	System.out.println("hi");
        if(bindorunbind)
        	bind(lv1, lv2, BIND_BIDIRECTIONAL);
        else
        	unbind(lv1, lv2, BIND_BIDIRECTIONAL);
    }
    public static void unbind(ListView lv1, ListView lv2, int bindType) {
    	ScrollBar bar1 = null;
        ScrollBar bar2 = null;
        for (Node node : lv1.lookupAll(".scroll-bar")) {
        	if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
        		bar1 = (ScrollBar)node;
            }
        }
        for (Node node : lv2.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar2 = (ScrollBar)node;
            }
        }
        if (bar1 == null || bar2 == null) return;

        final ScrollBar fbar1 = bar1;
        final ScrollBar fbar2 = bar2;
            
        if (fbar1 != null && (bindType & BIND_RIGHT_TO_LEFT) != 0) {
            fbar1.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    fbar2.setValue(newValue.doubleValue());
                    }
                });
            }
        if (fbar2 != null && (bindType & BIND_LEFT_TO_RIGHT) != 0) {
            fbar2.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   fbar1.setValue(newValue.doubleValue());
                }
            });
        }
        fbar1.valueProperty().unbind();
        fbar2.valueProperty().unbind();
        lv1.getSelectionModel().selectedIndexProperty().addListener(
        		new ChangeListener<Number>() {
        			@Override
            	    public void changed(ObservableValue<? extends Number> observable,
            	                                Number oldVal , Number newVal) {
            	    	   lv2.getSelectionModel().clearAndSelect(newVal.intValue());
            	       }
            	  }
            	);
        
        lv2.getSelectionModel().selectedIndexProperty().addListener(
        		new ChangeListener<Number>() {
        			@Override
        			public void changed(ObservableValue<? extends Number> observable,
        										Number oldVal , Number newVal) {
        				lv1.getSelectionModel().clearAndSelect(newVal.intValue());
          	        	   
          	           }
          	      }
          	);
        lv1.focusModelProperty().unbind();
        lv2.focusModelProperty().unbind();
    }
    /**
     * Allows binding of 2 ListView's scrollbars.
     *
     * @param lv1      left list view
     * @param lv2      right list view
     * @param bindType 3 binding possibilities : BIND_BIDIRECTIONAL=bidirectional, BIND_RIGHT_TO_LEFT and BIND_LEFT_TO_RIGHT
     */
    
    public static void bind(ListView lv1, ListView lv2, int bindType) {
        ScrollBar bar1 = null;
        ScrollBar bar2 = null;
        for (Node node : lv1.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar1 = (ScrollBar)node;
            }
        }
        for (Node node : lv2.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar && ((ScrollBar)node).getOrientation().equals(Orientation.VERTICAL)) {
                bar2 = (ScrollBar)node;
            }
        }
        if (bar1 == null || bar2 == null) return;

        final ScrollBar fbar1 = bar1;
        final ScrollBar fbar2 = bar2;
        
        if (fbar1 != null && (bindType & BIND_RIGHT_TO_LEFT) != 0) {
            fbar1.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    fbar2.setValue(newValue.doubleValue());
                }
            });
        }
        if (fbar2 != null && (bindType & BIND_LEFT_TO_RIGHT) != 0) {
            fbar2.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    fbar1.setValue(newValue.doubleValue());
                }
            });
        }
        
        lv1.getSelectionModel().selectedIndexProperty().addListener(
        	      new ChangeListener<Number>() {
        	           @Override
        	           public void changed(ObservableValue<? extends Number> observable,
        	                                    Number oldVal , Number newVal) {
        	        	   lv2.getSelectionModel().clearAndSelect(newVal.intValue());
        	           }
        	      }
        	);
        lv2.getSelectionModel().selectedIndexProperty().addListener(
      	      new ChangeListener<Number>() {
      	           @Override
      	           public void changed(ObservableValue<? extends Number> observable,
      	                                    Number oldVal , Number newVal) {
      	        	   lv1.getSelectionModel().clearAndSelect(newVal.intValue());
      	        	   
      	           }
      	      }
      	);
        
        
    }
    
    
}