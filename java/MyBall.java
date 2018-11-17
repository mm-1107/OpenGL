/**
 * Model of a car
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;


public class MyBall {
	private long initTime = 0;
	private float y = 650;
	// Colors
	float color[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	float silver[] = { 0.5f, 0.5f, 0.5f, 1.0f };

	// Distance from the center of the orbit
	double transform = 4.0;

	/**
	 * Set color
	 */
	public void setColor(double r, double g, double b) {
		color[0] = (float)r;
		color[1] = (float)g;
		color[2] = (float)b;
		color[3] = 1.0f;
	}

	/**
	 * 中心からの位置
	 */
	public void setTransform(double t) {
		transform = t;
	}

	/**
	 * Draw the car
	 */
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		GLUT glut = new GLUT();

		if (initTime == 0) {
		 // 初期時間が設定されてなければ，
		 // 現在時間にセットする
		 initTime = System.currentTimeMillis();
		 // y = 0;
	 } else {
		 long t = System.currentTimeMillis() - initTime;

		 // 軌跡は2次式で決まる
		 y = -0.000005f * t * t + 0.01f * t - 2.0f;

		 // 一番下までボールがついたら，時間をリセット
		 if (y <= -2.0f) {
				 initTime = System.currentTimeMillis();
				 
		 }
	 }

		// ベクトル(0.0, 0.0, transform)だけ平行移動
		gl.glTranslated(0.0, y, transform);
		glut.glutSolidSphere(0.5f, 20, 20);

		// Set reflection coefficients
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);

		// // 描画し終わるたびにr+1していく(マウスを押しているときは車が回転する)
		// calculateMovement();
	}



}
