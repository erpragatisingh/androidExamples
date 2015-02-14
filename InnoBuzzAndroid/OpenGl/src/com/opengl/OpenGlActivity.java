package com.opengl;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGlActivity extends Activity {
    /** Called when the activity is first created. */
	GLSurfaceView glView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        glView =new GLSurfaceView(this);
        setContentView(glView);
        	
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	
   //glView.onPause();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	//glView.onResume();
    }
    
    
    
    class HelloSurfaceView extends GLSurfaceView{

		public HelloSurfaceView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			setEGLContextClientVersion(2);
			setRenderer(new HelloRenderer());

			
		}
    	
    	
    }
}