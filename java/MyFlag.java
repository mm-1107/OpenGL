/**
 * Model of a flag
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class MyFlag {

	// 立法体の各点の定義
	double vertex3[][] = {
		  { -0.05, 0.0, -0.05 },
		  {  0.05, 0.0, -0.05 },
		  {  0.05, 1.0, -0.05 },
		  { -0.05, 1.0, -0.05 },
		  { -0.05, 0.0,  0.05 },
		  {  0.05, 0.0,  0.05 },
		  {  0.05, 1.0,  0.05 },
		  { -0.05, 1.0,  0.05 }
	};

	// Positions of vertices
	double vertex4[][] = {
			{ 0.5, 0.8, -0.5},
			{ 0.0, 1.0, 0.0},
			{ 0.0, 0.6, 0.0}
	};

	// 座標を結ぶ順番({ 0, 3, 2, 1 }と書くことで
	// vertex3なら0番目の座標->3番目の座標->2番目の座標->1番目の座標がプロットされた四角形になる)
	int face[][] = {
		  { 0, 3, 2, 1 },
		  { 1, 2, 6, 5 },
		  { 5, 6, 7, 4 },
		  { 4, 7, 3, 0 },
		  { 4, 0, 1, 5 },
		  { 3, 7, 6, 2 }
	};

	// Normal vector of vertices
	double normal[][] = {
		  { 0.0, 0.0, 1.0 },
		  {-1.0, 0.0, 0.0 },
		  { 0.0, 0.0,-1.0 },
		  { 1.0, 0.0, 0.0 },
		  { 0.0, 1.0, 0.0 },
		  { 0.0,-1.0, 0.0 }
	};


	// Colors
	float red[] = { 0.8f, 0.0f, 0.0f, 1.0f };
	float blue[] = { 0.2f, 0.2f, 0.8f, 1.0f };
	float silver[] = { 0.5f, 0.5f, 0.5f, 1.0f };

	/**
	 * Draw the car
	 */
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		// GL2.GL_AMBIENT_AND_DIFFUSEは拡散反射のRGBをblueに指定
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);
		// GL2.GL_SPECULARは鏡面反射のRGBをsilfverに指定
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, silver, 0);

		// Draw a 6 box
		for (int j = 0; j < 6; ++j) {
			gl.glBegin(GL2.GL_POLYGON);	// 多角形を描画する宣言
			gl.glNormal3dv(normal[j], 0);
			for (int i = 0; i < 4; ++i) {
				// vertex3の座標をfaceに宣言した順番で四角形を描画
				gl.glVertex3dv(vertex3[face[j][i]], 0);
			}
			gl.glEnd();
		}

		// Set reflection coefficients
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, red, 0);

		// Draw a triangle
		gl.glBegin(GL2.GL_POLYGON);
		for (int i = 0; i < 3; ++i) {
			gl.glVertex3dv(vertex4[i], 0);
		}
		gl.glEnd();
	}

}
