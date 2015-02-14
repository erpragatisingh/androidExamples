package com.image.ngma;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageWithBitmapActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        ImageView imageView = (ImageView) findViewById(R.id.my_image);
        
        Bitmap bmp =BitmapFactory.decodeResource(getResources(), R.drawable.my_image);
        Matrix matrix = new Matrix();
        matrix.setRotate(120);
        bmp =Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
        bmp.getHeight(), matrix, false);
        imageView.setImageBitmap(bmp);
        
        
        
        }
    }
