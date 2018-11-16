/**
 * Listener for mouse events
 */


import java.awt.event.*;
import com.jogamp.opengl.util.Animator;


public class CgMouseListener implements MouseListener, MouseMotionListener {
	CgCanvas canvas;
	Animator animator;	
	
	public CgMouseListener(CgCanvas c, Animator a) {
		canvas = c;
		animator = a;
	}
    
    /**
     * Called when cursor is moved
     */
    public void mouseMoved(MouseEvent e) {
    	
    }
    
    /**
     * Called when cursor is entered
     */
    public void mouseEntered(MouseEvent e) {
    	
    }
    
    /**
     * Called when cursor is exited
     */
    public void mouseExited(MouseEvent e) {
    	
    }

    /**
     * Called when cursor is clicked
     */
    public void mouseClicked(MouseEvent e) {
    	
    }
    
    /**
     * Called when cursor is dragged
     */
    public void mouseDragged(MouseEvent e) {
    	
    }
    
    /**
     * Called when cursor is pressed
     */
    public void mousePressed(MouseEvent e) {
    	animator.start();
    }
    
    /**
     * Called when cursor is released
     */
    public void mouseReleased(MouseEvent e) {
    	animator.stop();
    }

    
}
