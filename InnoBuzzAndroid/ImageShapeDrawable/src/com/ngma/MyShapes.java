package com.ngma;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.view.View;


public class MyShapes extends View {
	private final ShapeDrawable mShape;
	
	
	public MyShapes(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Paint paint = new Paint();
		//setting the color 
		//paint.setARGB(255, 255,255, 0);  
		paint.setARGB(255, 0, 0, 255);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4.0f);
		mShape = new ShapeDrawable(new ArcShape(0, 180));
		mShape.getPaint().set(paint);
		mShape.setBounds(0, 0, 300, 200);
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		mShape.draw(canvas);
	}
	
	}
