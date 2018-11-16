/**
 * Model of a scene
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;


public class MyScene {

	// 描画する物体の定義
	static MyFlag flag1 = null;
	static MyCar car1 = null;

	/**
	 * Initialization
	 */
	public static void init() {

		 // Allocate a flag
		 flag1 = new MyFlag();

		 // Allocate a car
		 car1 = new MyCar();
		 car1.setColor(1.0, 0.0, 0.0);
		 car1.setVelocity(5);
		 car1.setTransform(0);

	}

	/**
	 * Draw the scene
	 */
	public static void draw(GLAutoDrawable drawable) {
			if(drawable == null) return;

			GL2 gl = drawable.getGL().getGL2();
		 	gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE);

	    // フラグを描画
	    gl.glPushMatrix();
	    if(flag1 != null)
	    	flag1.draw(drawable);
	    gl.glPopMatrix();

	    // 車を描画
			gl.glPushMatrix();
	    if(car1 != null)
	    	car1.draw(drawable);
	    gl.glPopMatrix();

	}

	// 初期状態に戻すメソッド
	public static void resetMovement() {

		// Reset the position of the car
		car1.resetMovement();
	}

}
