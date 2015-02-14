package com.image.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyShape extends View implements OnTouchListener{
	public MyShape(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint.setColor(Color.YELLOW);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4.0f);
		paint.setAntiAlias(false);
		this.setOnTouchListener(this);
			
	}
	float y;
	float x;
	Paint paint = new Paint();
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x = event.getX();
		y = event.getY();
		invalidate();
		return true;
		
	}	
	@Override
	public void onDraw(Canvas canvas) {
	canvas.drawCircle(x, y, 20, paint);
	}
	

}
