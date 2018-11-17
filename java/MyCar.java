/**
 * Model of a car
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;


public class MyCar {
	private long initTime = 0;
	private float y = 650;
	// Colors
	float color[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	float silver[] = { 0.5f, 0.5f, 0.5f, 1.0f };

	// Positions of vertices
	// Rotation angle
	int r = 0;

	// Speed
	int velocity = 5;

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
	 * Set velocity
	 */
	public void setVelocity(int v) {
		velocity = v;
	}

	/**
	 * 中心からの位置
	 */
	public void setTransform(double t) {
		transform = t;
	}

	/**
	 * Calculate the movement (rotation angle)
	 */
	public void calculateMovement() {
		r += velocity;
	    if (r >= 3600) {
	    	r = 0;
	    }
	}


	/**
	 * Reset the movement (reset the rotation angle)
	 */
	public void resetMovement() {
		r = 0;
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

		// ベクトル(0.0,1.0,0.0)を中心にr*0.1度回転
		// gl.glRotated(((double)r * 0.1), 0.0, 1.0, 0.0);
		// ベクトル(0.0, 0.0, transform)だけ平行移動
		gl.glTranslated(0.0, y, transform);
		glut.glutSolidSphere(0.5f, 20, 20);
		// gl.glTranslatef(0.0f, y, 0.0f);
		// Set reflection coefficients
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);

		// // Draw a box
		// for (int j = 0; j < 6; ++j) {
		// 	gl.glBegin(GL2.GL_POLYGON);
		// 	gl.glNormal3dv(normal[j], 0);
		// 	for (int i = 0; i < 4; ++i) {
		// 		gl.glVertex3dv(vertex1[face[j][i]], 0);
		// 	}
		// 	gl.glEnd();
		// }
		//
		// // Draw another box
		// for (int j = 0; j < 6; ++j) {
		// 	gl.glBegin(GL2.GL_POLYGON);
		// 	gl.glNormal3dv(normal[j], 0);
		// 	for (int i = 0; i < 4; ++i) {
		// 		gl.glVertex3dv(vertex2[face[j][i]], 0);
		// 	}
		// 	gl.glEnd();
		// }
		//
		// // 拡散反射と鏡面反射を設定
		// gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, silver, 0);
		// gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);
		//
		// // タイヤの描画
		// // 平行移動 左後ろのタイヤ
		// gl.glTranslated(0.2, 0.0, 0.05);
		// // 球体を描画 半径=0.1 第２、第３引数を大きくすると滑らかな球になる
		// glut.glutSolidSphere(0.1, 30, 20);
		//
		// // 左前のタイヤ
		// gl.glTranslated(-0.4, 0.0, 0.0);
		// glut.glutSolidSphere(0.1, 30, 20);
		//
		// // 右前のタイヤ
		// gl.glTranslated(0.0, 0.0, 0.4);
		// glut.glutSolidSphere(0.1, 30, 20);
		//
		// // 右後ろのタイヤ
		// gl.glTranslated(0.4, 0.0, 0.0);
		// glut.glutSolidSphere(0.1, 30, 20);
		//
		// // 描画し終わるたびにr+1していく(マウスを押しているときは車が回転する)
		// calculateMovement();
	}



}
