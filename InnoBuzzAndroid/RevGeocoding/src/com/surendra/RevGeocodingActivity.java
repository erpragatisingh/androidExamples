package com.surendra;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class RevGeocodingActivity extends MapActivity {
    /** Called when the activity is first created. */
	
	MapView mapView;
	MapController mc;
	GeoPoint p;

	class MapOverlay extends com.google.android.maps.Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,long when) {
			super.draw(canvas, mapView, shadow);
			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);
			// ---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.puchpin);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
			return true;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			// ---when user lifts his finger---
			if (event.getAction() == 1) {
				GeoPoint p = mapView.getProjection().fromPixels((int) event.getX(), (int) event.getY());
		
			Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
					try {
					List<Address> addresses = geoCoder.getFromLocation(p.getLatitudeE6() / 1E6,p.getLongitudeE6() / 1E6, 1);
					String add = "";
					if (addresses.size() > 0)
					{
					for (int i=0; i<addresses.get(0).getMaxAddressLineIndex();i++)
					add += addresses.get(0).getAddressLine(i) + "\n";
					}
					Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
					}
					catch (IOException e) {
					e.printStackTrace();
					}
					return true;
			}
			return false;
		}
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		mc = mapView.getController();
		// ---navigate to a point first---
		String coordinates[] = { "28.613459", "77.167969" };
		double lat = Double.parseDouble(coordinates[0]);
		double lng = Double.parseDouble(coordinates[1]);
		p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		// ---Add a location marker---
		MapOverlay mapOverlay = new MapOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(mapOverlay);
		mc.animateTo(p);
		mc.setZoom(13);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}