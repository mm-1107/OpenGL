/**
 * Model of a scene
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;


public class MyScene {

	// 描画する物体の定義
	static MyGround ground1 = null;
	static MyBall ball1 = null;
	static MyBall ball2 = null;
	static MyBall ball3 = null;
	/**
	 * Initialization
	 */
	public static void init() {

		 ground1 = new MyGround();

		 // ボールの初期化
		 ball1 = new MyBall();
		 ball1.setColor(0.0, 1.0, 0.0);
		 ball1.setTransform(0.0, 5.0, 1.0, 1.0);

		 // ボールの初期化
		 ball2 = new MyBall();
		 ball2.setColor(1.0, 1.0, 0.0);
		 ball2.setTransform(2.0, 3.0, 0.0, 1.0);

		 ball3 = new MyBall();
		 ball3.setColor(1.0, 0.0, 0.0);
		 ball3.setTransform(1.0, 10.0, 3.0, 0.3);

	}

	/**
	 * Draw the scene
	 */
	public static void draw(GLAutoDrawable drawable) {
			if(drawable == null) return;

			GL2 gl = drawable.getGL().getGL2();
		 	gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE);

			// 地面を描画
			// gl.glPushMatrix();
			// if(ground1 != null)
			// 	ground1.draw(drawable);
			// gl.glPopMatrix();

			gl.glPushMatrix();
	    if(ball1 != null)
	    	ball1.draw(drawable);
	    gl.glPopMatrix();

			gl.glPushMatrix();
	    if(ball2 != null)
	    	ball2.draw(drawable);
	    gl.glPopMatrix();

			gl.glPushMatrix();
	    if(ball3 != null)
	    	ball3.draw(drawable);
	    gl.glPopMatrix();


	}

	// 初期状態に戻すメソッド
	public static void resetMovement() {

		// Reset the position of the car
		ball1.resetMovement();
		ball2.resetMovement();
		ball3.resetMovement();
	}

}
