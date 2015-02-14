package com.Innobuzz;


import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class Map extends MapActivity  {
	MapView view;
	LocationManager manager;
	LocationListener listener;
	private GestureDetector dedector;
	MapController control;
	GeoPoint Innobuzz;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		 view=(MapView)findViewById(R.id.mapview);
		 
		LinearLayout lay=(LinearLayout)findViewById(R.id.zoom);

		lay.addView(view.getZoomControls());
	    view.setStreetView(false);
	    view.setSatellite(false);
	    view.setClickable(true);
	    view.setEnabled(true);
	  
	    
	    //------for Zoom on double click--------
	    
	    dedector=new GestureDetector(new GestureReactor());
	    
	    view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return dedector.onTouchEvent(event);

			}
		});
	
	     control = view.getController();
	     control.setZoom(18);
	     
	     
		//-----for GPS------------- 
	     
	     
	     
		// manager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		// Criteria criteria = new Criteria();
		// criteria.setAccuracy(criteria.ACCURACY_COARSE);
		// String provider=manager.getBestProvider(criteria,true);  
		
		/* GeoPoint initGeoPoint = new GeoPoint((int)(manager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude()*1000000),
			           (int)(manager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()*1000000));
		          control.animateTo(initGeoPoint);
		          control.setCenter(initGeoPoint);
		 
		 
		 listener = new LocationListener()
		 {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
				// Toast.makeText(getBaseContext(),"GPS is not Active ",Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
			   
			   GeoPoint test = new GeoPoint((int)location.getLatitude()*1000000, (int)location.getAltitude()*1000000);
			 //  Toast.makeText(getBaseContext(),
		     //          "New location latitude [" +location.getLatitude() +
		     //          "] longitude [" + location.getLongitude()+"]",
		     //          Toast.LENGTH_SHORT).show();
			  
			   control.setCenter(test);
	           control.animateTo(test);
	           

		
				
			}
		};  
		
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, listener);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
		
      */


	    Drawable marker=getResources().getDrawable(R.drawable.currentlocation);
	    marker.setBounds(0,0,marker.getIntrinsicWidth(),marker.getIntrinsicHeight());
	    view.getOverlays().add(new InsertLocation(marker));

	} 

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
private class GestureReactor extends SimpleOnGestureListener implements OnGestureListener {
	    public boolean onDoubleTap(MotionEvent e) {
	        System.out.println("inside onDouble");
	        control.zoomInFixing((int) e.getX(), (int) e.getY());
	        return super.onDoubleTap(e);
	    }
	}





class InsertLocation extends ItemizedOverlay {
		private List<OverlayItem> locations = new ArrayList<OverlayItem>();
	
		@SuppressWarnings("unused")
		private Drawable marker;	
		
		public InsertLocation(Drawable marker) {
			super(marker);
			this.marker=marker;
		    Innobuzz = new
					GeoPoint((int)(28.697964*1000000),(int)(77.1405204*1000000));
			locations.add(new OverlayItem(Innobuzz ,"Innobuzz", "Innobuzz"));
			
			
			populate();

		}
		
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
			// TODO Auto-generated method stub
			super.draw(canvas, mapView, shadow);
			Paint paint=new Paint();
			//---translate the GeoPoint to screen pixels---

			Point screenPts = new Point();
            mapView.getProjection().toPixels(Innobuzz, screenPts);
            
            paint.setStrokeWidth(2);
            paint.setColor(Color.WHITE);
            paint.setARGB(255, 255, 255, 255);
            paint.setStyle(Paint.Style.FILL);
            //paint.setColor(android.R.color.primary_text_dark_nodisable);
            
            

            
          //---add the image on map point---
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mapadd);            
            canvas.drawBitmap(bmp, screenPts.x-8, screenPts.y-23, null); 
           
            canvas.drawText("Innobuzz Knowledge Solutions", screenPts.x-50, screenPts.y-25,paint);
           
            return;

			
			//boundCenterBottom(marker);
		}

		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return locations.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return locations.size();
		}

	}

}
