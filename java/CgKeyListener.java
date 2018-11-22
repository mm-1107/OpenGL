/**
 * Listener for keyboard events
 */


import java.awt.event.*;
import com.jogamp.opengl.util.Animator;


// KeyListenerの継承
public class CgKeyListener implements KeyListener {
	CgCanvas canvas;
	Animator animator;

	public CgKeyListener(CgCanvas c ,Animator a) {
		canvas = c;
		animator = a;
	}


   	/**
	 * Called when a key is pressed
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		// "Q": exit
		case KeyEvent.VK_Q:
			System.exit(0);
			break;

		// "R": reset
		case KeyEvent.VK_R:
		  animator.start();
			MyScene.resetMovement();
			canvas.display();
			break;

		case KeyEvent.VK_S:
			animator.stop();
			break;
		}

	}

	/**
	 * Called when a key is released
	 */
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * Called when a key is typed
	 */
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		// "Q": exit
		case KeyEvent.VK_Q:
			System.exit(0);
		}
	}
}
