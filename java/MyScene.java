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
	static MyBall ball4 = null;
	static MyBall ball5 = null;
	/**
	 * Initialization
	 */
	public static void init() {

		 ground1 = new MyGround();

		 // ボールの初期化
		 ball1 = new MyBall();
		 // x,y,zと半径を指定
		 ball1.init(-2.0, 6.0, 4.0, 1.5,"./image/orange.jpg");

		 // ボールの初期化
		 ball2 = new MyBall();
		 ball2.init(1.0, 8.0, 0.0, 1.5,"./image/blue.jpg");

		 ball3 = new MyBall();
		 ball3.init(5.0, 5.0, 5.0, 1.5,"./image/pika.jpg");

		 ball4 = new MyBall();
		 ball4.init(7.0, 7.0, -1.0, 1.5,"./image/msball.jpg");

		 ball5 = new MyBall();
		 ball5.init(-6.0, 6.0, -2.0, 1.5,"./image/green.jpg");


	}

	/**
	 * Draw the scene
	 */
	public static void draw(GLAutoDrawable drawable,float[] light0pos) {
			if(drawable == null) return;

			GL2 gl = drawable.getGL().getGL2();
		 	gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE);

	    if(ball1 != null)
	    	ball1.draw(drawable, light0pos);

	    if(ball2 != null)
	    	ball2.draw(drawable, light0pos);

	    if(ball3 != null)
	    	ball3.draw(drawable, light0pos);

			if(ball4 != null)
	    	ball4.draw(drawable, light0pos);

			if(ball5 != null)
	    	ball5.draw(drawable, light0pos);

			// 地面を描画
			// ポリゴンオフセット
			// gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
			gl.glPushMatrix();
			if(ground1 != null)
				// gl.glPolygonOffset(1.0f, 0.0f);
				ground1.draw(drawable);
			gl.glPopMatrix();
			// gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);



	}

	// 初期状態に戻すメソッド
	public static void resetMovement() {

		// Reset the position of the car
		ball1.resetMovement();
		ball2.resetMovement();
		ball3.resetMovement();
		ball4.resetMovement();
		ball5.resetMovement();
	}

}
