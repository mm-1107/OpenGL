/**
 * Model of a car
 */

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;


public class MyGround {

  double vertex5[][] = {
			{ -20.0, 0.0, -10.0},
			{ -20.0, 0.0, 10.0},
			{ 20.0, 0.0, 10.0},
			{ 20.0, 0.0, -10.0},
	};

	float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };//拡反射成分

	// 床描画
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, color, 0);
		// gl.glTranslated(0.0, 0.0, 0.0);
    // gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    // gl.glLoadIdentity();
		gl.glBegin(GL2.GL_POLYGON);//多角形(POLIGON)を描画する
		for (int i = 0; i < 4; ++i) {
      gl.glColor3f(1.0f, 0.0f, 0.3f);
		    gl.glVertex3dv(vertex5[i], 0);
		}
		gl.glEnd();

	}



}
