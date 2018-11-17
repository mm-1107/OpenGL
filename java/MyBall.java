/**
 * Model of a car
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;


public class MyBall {
	long initTime = 0;
	double radius = 0.1;	// ボールの半径
	int h = 6;	// ボールを落とす高さ
	double y = 0.0;
	double e = 0.8;	// 反発係数
	double g = 9.8;	// 重力加速度
	double v_0 = Math.sqrt(2.0 * g * h);	// 初速度
	private int bound_flg = 0;
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

	public void setTransform(double z, double r) {
		transform = z;	// z座標
		radius = r;
	}

	// ボール描画
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		GLUT glut = new GLUT();

		if (initTime == 0) {
		 // 初期時間が設定されてなければ，
		 // 現在時間にセットする
		 initTime = System.currentTimeMillis();
	 }
	 	else {
		 long t = System.currentTimeMillis() - initTime;
		 // System.out.println("t = " + t);
		 if (bound_flg == 0){
			 // 自由落下 tはミリ秒なので0.001をかける
			 y = h - (0.5 * g * 0.001 * t * 0.001 * t);
		 }
		 else if (bound_flg == 1) {
		 		y = v_0 * t * 0.001 - 0.5 * g * 0.001 * t * 0.001 * t;
		 }
		 // 一番下までボールがついたら，時間をリセット
		 if (y <= 0.0f) {
			 	System.out.println("v_0は" + v_0);
			 	bound_flg = 1;
				System.out.println("bound!");
				v_0 = e * v_0;
				System.out.println("v_0 = " + v_0);
				initTime = System.currentTimeMillis();
		 }
	 }

	 // System.out.println("y = " + y);
		// ベクトル(0.0, 0.0, transform)だけ平行移動
		gl.glTranslated(0.0, y, transform);
		// 球体を描画
		glut.glutSolidSphere(radius, 20, 20);
		// Set reflection coefficients
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);

		// // 描画し終わるたびにr+1していく(マウスを押しているときは車が回転する)
		// calculateMovement();
	}



}
