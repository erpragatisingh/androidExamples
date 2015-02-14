package com.ngma;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;

public class GooglemapoverlayActivity extends MapActivity{
	MapView mv;
	MapController mc;
	
 class mol extends com.google.android.maps.Overlay
	{

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,long when) {
			// TODO Auto-generated method stub
			   super.draw(canvas, mapView, shadow);
			   double lat =51.50773;
		       double lon=-0.16582;
		        
 
		       mv.setBuiltInZoomControls(true); 
		       mv.setSatellite(true); 

		       GeoPoint gpoint=new GeoPoint((int)(lat*1E6),(int)(lon*1E6));
		       
		       Point p=new Point();
		       
		       mv.getProjection().toPixels(gpoint, p);
		       
		       Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.imgres);
		       
		      canvas.drawBitmap(bmp,p.x ,p.y , null);
		      
			 
			 return true;
		}
		
	}
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
        mv=(MapView)findViewById(R.id.mp1);
        List listofoverlays= mv.getOverlays();
        listofoverlays.clear();
        listofoverlays.add(new mol());
        
        mv.invalidate();
    
       
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}