/**
 * Drawing functions
 */


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.gl2.GLUgl2;
import com.jogamp.opengl.util.gl2.GLUT;


public class CgDrawer implements GLEventListener {
	GLAutoDrawable glAD;

	// Positions of light sources
	static float light0pos[] = { 5.0f, 20.0f, 10.0f, 1.0f };
	// static float light1pos[] = { 4.0f, 6.0f, 5.0f, 1.0f };

		// CgDrawerをオブジェクト化した時に勝手に呼ばれる関数
    public void init(GLAutoDrawable drawable) {
        float light_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};	// 拡散光(白)

        this.glAD = drawable;

        GL2 gl= drawable.getGL().getGL2();

        // Initialization of OpenGL setting
				gl.glEnable(GL.GL_RGBA);
				gl.glEnable(GL2.GL_DEPTH);
        gl.glEnable(GL2.GL_DOUBLE);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_NORMALIZE);
        gl.glEnable(GL.GL_CULL_FACE);
        gl.glCullFace(GL.GL_BACK);

        // Initialization of light sources
				gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);

				// 照明の色を設定
				gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_diffuse,0);
				gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, light_diffuse,0);

        // 背景色を設定
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

	}

    /**
     * Called when the shape of the window is modified
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLUgl2 glu = new GLUgl2();

        if (height <= 0)
            height = 1;
				float fovy = 60;	// 縦の視野角度
        float aspect = (float) width / (float) height;	// 縦に対する横方向の視野角の倍率
				float zNear = 1;	// 一番近いZ位置
				float zFar = 100;	// 一番遠いZ位置

        // ウィンドウ全体に表示
        gl.glViewport(0, 0, width, height);

        // 投影変換モードに入る
        gl.glMatrixMode(GL2.GL_PROJECTION);
				// 投影変換の変換行列を単位行列で初期化
        gl.glLoadIdentity();
				// 透視投影を行う
				// https://atelier-yoka.com/dev_android/p_main.php?file=apiglugluperspective
        glu.gluPerspective(fovy, aspect, zNear, zFar);

        // 視野変換・モデリング変換モードに入る
        gl.glMatrixMode(GL2.GL_MODELVIEW);
				// 視野変換・モデリング変換の変換行列を単位行列で初期化
        gl.glLoadIdentity();


    }


    // 開始時に呼ばれる関数
    public void display(GLAutoDrawable drawable) {
        draw(drawable);
    }


    /**
     * Dummy function (DO NOT remove)
     */
    public void displayChanged(
    	GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    	;
    }



	/**
	 * Called when redraw is needed
	 */
	public void draw(GLAutoDrawable drawable) {
		 GL2 gl = drawable.getGL().getGL2();
		 GLUgl2 glu = new GLUgl2();
		 double ex = 0.0;	// 視点の位置
		 double ey = 10.0;
		 double ez = 20.0;
		 double cx = 0.0;	// 目標の位置
		 double cy = 0.0;
		 double cz = 0.0;
		 double ux = 0.0;	// 画像の上方向を表す
		 double uy = 1.0;
		 double uz = 0.0;
		 // Clear the scene
		 gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		 // Set the viewpoint
	   gl.glLoadIdentity();

		 glu.gluLookAt(ex, ey, ez, cx, cy, cz, ux, uy, uz);

		 // Set the positions of light sources
		 gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0pos, 0);
		 // gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, light1pos, 0);

		 // Ballの描画
		 MyScene.draw(drawable, light0pos);

	}


	public GLAutoDrawable getGLAutoDrawable() {
		return glAD;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

}
