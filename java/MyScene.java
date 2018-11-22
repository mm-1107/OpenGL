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
		 // x,y,zと半径を指定
		 ball1.setTransform(-3.0, 5.0, 0.0, 1.0);

		 // ボールの初期化
		 // ball2 = new MyBall();
		 // ball2.setColor(1.0, 1.0, 0.0);
		 // ball2.setTransform(-2.0, 3.0, 0.0, 1.0);
		 // ball2.make_shadow_matrix(plane,light);

		 // ball3 = new MyBall();
		 // ball3.setColor(1.0, 0.0, 0.0);
		 // ball3.setTransform(1.0, 5.0, 0.0, 0.3);
		 // ball3.make_shadow_matrix(plane,light);

	}

	/**
	 * Draw the scene
	 */
	public static void draw(GLAutoDrawable drawable,float[] light0pos) {
			if(drawable == null) return;

			GL2 gl = drawable.getGL().getGL2();
		 	gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE);

			// 地面を描画
			// ポリゴンオフセット
			gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
			gl.glPushMatrix();
			if(ground1 != null)
				gl.glPolygonOffset(1.0f, 0.0f);
				ground1.draw(drawable);
			gl.glPopMatrix();
			gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);

			// gl.glPushMatrix();
	    if(ball1 != null)
	    	ball1.draw(drawable, light0pos);
	    // gl.glPopMatrix();

			// gl.glPushMatrix();
	    if(ball2 != null)
	    	ball2.draw(drawable, light0pos);
	    // gl.glPopMatrix();

			// gl.glPushMatrix();
	    if(ball3 != null)
	    	ball3.draw(drawable, light0pos);
	    // gl.glPopMatrix();


	}

	// 初期状態に戻すメソッド
	public static void resetMovement() {

		// Reset the position of the car
		ball1.resetMovement();
		// ball2.resetMovement();
		// ball3.resetMovement();
	}

}
