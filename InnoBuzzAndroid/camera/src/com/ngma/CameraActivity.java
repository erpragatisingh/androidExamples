package com.ngma;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

public class CameraActivity extends Activity {
	Bitmap bit;
	ImageView iv;
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 if(requestCode==1)
		 {
			 bit=(Bitmap)data.getExtras().get("data");
			 iv.setImageBitmap(bit);
			 
			 File sd=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/picstorage"); 
			 
			
		 }
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        iv =(ImageView)findViewById(R.id.imageView1);
        
        Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(in, 1);
        
    }
}