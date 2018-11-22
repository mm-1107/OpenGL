/**
 * Model of a car
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;


public class MyGround {

  double vertex5[][] = {
			{ -5.0, 0.0, -5.0},
			{ -5.0, 0.0, 5.0},
			{ 5.0, 0.0, 5.0},
			{ 5.0, 0.0, -5.0},
	};

	float lightgreen[] = { 0.8f, 0.3f, 0.2f, 1.0f };//拡反射成分

	// 床描画
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		// gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, lightgreen, 0);
		// gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, lightgreen, 0);
		gl.glTranslated(0.0, 0.0, 0.0);

		gl.glBegin(GL2.GL_POLYGON);//多角形(POLIGON)を描画する
		for (int i = 0; i < 4; ++i) {
		    gl.glVertex3dv(vertex5[i], 0);
		}
		gl.glEnd();

	}



}
