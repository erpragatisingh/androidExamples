package com.geocoding.googlemap;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.sartaj.googlemap.R;

public class GoogleMap extends MapActivity {
	/** Called when the activity is first created. */
	MapView mapView;
	MapController mc;
	GeoPoint p;

	class MapOverlay extends com.google.android.maps.Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);
			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);
			// ---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.puchpin);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
			return true;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			// ---when user lifts his finger---
			if (event.getAction() == 1) {
				GeoPoint p = mapView.getProjection().fromPixels(
						(int) event.getX(), (int) event.getY());
				Toast.makeText(
						getBaseContext(),
						"Location: " + p.getLatitudeE6() / 1E6 + ","
								+ p.getLongitudeE6() / 1E6, Toast.LENGTH_SHORT)
						.show();
			}
			return false;
		}
	}

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
