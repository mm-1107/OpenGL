/**
 * Model of a car
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.TextureIO;

public class MyBall {
	long initTime = 0;
	float r = 0.1f;	// ボールの半径
	float h = 6.0f;	// ボールを落とす高さ
	float x = 0.0f;
	float y = 0.0f;
	float z = 0.0f;
	float e = 0.9f;	// 反発係数
	float g = 9.8f;	// 重力加速度
	float v_0 = (float)Math.sqrt(2.0 * g * h);	// 初速度
	private int bound_flg = 0;
	// Colors
	float color[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	float shininess[] = {100.f};
	// Distance from the center of the orbit
	float init_h = 0.0f;
	/**
	 * Set color
	 */
	 public void setColor(double r, double g, double b) {
		color[0] = (float)r;
		color[1] = (float)g;
		color[2] = (float)b;
		color[3] = 1.0f;
	}

	public void setTransform(double x_,double h_,double z_, double r_) {
		x = (float)x_;
		h = (float)h_;	// ボールの高さ
		y = h;
		z = (float)z_;	// z座標
		init_h = h;
		r = (float)r_;	// 球の半径
	}

	public void resetMovement() {
		h = init_h;
		bound_flg = 0;
		v_0 = (float)Math.sqrt(2.0 * g * h);
		initTime = 0;
	}

	public float[] make_shadow_matrix(float ball_x, float ball_y, float ball_z, float[] light0pos){
		float[] shadow_matrix = new float[16];
		float ex, ey, ez;//光源の方向
		float s; //ボールの中心から光源までの距離
 		float x, y, z;	// ボールと光源を結ぶベクトル
		x = light0pos[0] - ball_x;
		y = light0pos[1] - ball_y;
		z = light0pos[2] - ball_z;
		s = (float)Math.sqrt(x * x + y * y + z * z);
		ex = x / s;
  	ey = y / s;
  	ez = z / s;
		shadow_matrix[0] = ey;
  	shadow_matrix[1] = 0.0f;
  	shadow_matrix[2] = 0.0f;
  	shadow_matrix[3] = 0.0f;
  	shadow_matrix[4] = -ex;
  	shadow_matrix[5] = 0.0f;
  	shadow_matrix[6] = -ez;
  	shadow_matrix[7] = 0.0f;
  	shadow_matrix[8] = 0.0f;
  	shadow_matrix[9] = 0.0f;
  	shadow_matrix[10] = ey;
  	shadow_matrix[11] = 0.0f;
  	shadow_matrix[12] = 0.0f;
  	shadow_matrix[13] = 0.001f * ey;
  	shadow_matrix[14] = 0.0f;
  	shadow_matrix[15] = ey;

		return shadow_matrix;
  }

	// ボールのy座標を計算
	public void calculatehigh(){
		// ボールのy座標を計算
		if (initTime == 0) {
		 // 初期時間が設定されてなければ，
		 // 現在時間にセットする
		 initTime = System.currentTimeMillis();
	 }
	 	else {
		 long t = System.currentTimeMillis() - initTime;

		 if (bound_flg == 0){
			 // 自由落下 tはミリ秒なので0.001をかける
			 y = (float)(h - (0.5 * g * 0.001 * t * 0.001 * t))+r;
		 }
		 else if (bound_flg == 1) {
			 // 一度バウンドすると初速度が変わる
		 		y = (float)(v_0 * t * 0.001 - 0.5 * g * 0.001 * t * 0.001 * t)+r;
		 }
		 // 一番下までボールがついたら，時間をリセット
		 if (y-r < 0.0f) {
			 	bound_flg = 1;
				y = r;
				v_0 = (float)e * v_0;
				initTime = System.currentTimeMillis();
		 }
	 }
	}

	// ボール描画
	public void draw(GLAutoDrawable drawable,float[] light0pos) {
		GL2 gl = drawable.getGL().getGL2();
		GLUT glut = new GLUT();
		float[] shadow_matrix = new float[16];	// 影の行列

		// ボールのy座標を計算
		calculatehigh();
		// 影行列を計算
	 	shadow_matrix = make_shadow_matrix(x,y,z, light0pos);

		// Texture texture = TextureIO.newTexture("./red.png", true);
    // texture.enable();
    // texture.bind();
		
		// ボールの影をつける
		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_LIGHT0);
		gl.glColor3d(0.1f,0.1f,0.1f);

		gl.glPushMatrix();
		gl.glMultMatrixf(shadow_matrix, 0);
		gl.glTranslatef(x, y, z);
		glut.glutSolidSphere(r, 40, 40);
		gl.glPopMatrix();

		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);

		// ボール描画
		gl.glPushMatrix();
		// 平行移動
		gl.glTranslatef(x, y, z);
		// 球体を描画
		// glut.glutSolidCube(r);
		glut.glutSolidSphere(r, 40, 40);
		// ボールの質感と色を設定
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);	// 図形の色
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, specular, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess, 0);
		gl.glPopMatrix();
	}



}
