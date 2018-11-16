/**
 * Main method
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;


public class CgMain extends JApplet {
	static int width = 600;
	static int height = 650;
	static Animator animator;

	/**
	 * Initialization of the application
	 */
	public void init() {
		setSize(new Dimension(width, height));

		// キャンバスの確保　CgDrawerを呼び出している
		CgCanvas canvas = new CgCanvas(width, height);
		canvas.requestFocus();
		GLCanvas glc = canvas.getGLCanvas();
		animator = new Animator(glc);

		// PanelにCanvasを貼り付け
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(glc, BorderLayout.CENTER);

		// containerにPanelを貼り付け
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);

		// キーボード操作の設定
		CgKeyListener mkl = new CgKeyListener(canvas);
		canvas.addKeyListener(mkl);
		glc.addKeyListener(mkl);

		// マウスイベントの設定
		CgMouseListener mml = new CgMouseListener(canvas, animator);
		canvas.addMouseListener(mml);
		glc.addMouseListener(mml);

		// MyflgとMycarの初期化
		MyScene.init();
	}

	/**
	 * Start the application
	 */
	public void start() {
	}

	/**
	 * Stop the application
	 */
	public void stop() {
	}


	/**
	 * The main method
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("I love CG!!!");
		frame.setSize(width, height);
		CgMain cgmain = new CgMain();

		// キーボード・マウスの設定、初期化処理
		cgmain.init();
		frame.getContentPane().add(cgmain);
		frame.setVisible(true);

		cgmain.start();

	    // Listener of the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                    	animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
	}

}
