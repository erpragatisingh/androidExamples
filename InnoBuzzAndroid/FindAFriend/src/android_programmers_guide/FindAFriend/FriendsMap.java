package android_programmers_guide.FindAFriend;

import android_programmers_guide.FindAFriend.Friends;
import android.os.Bundle;
import android.location.LocationManager;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Button;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Paint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Point;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayController;

public class FriendsMap extends MapActivity {
   
	
    private static final String[] PROJECTION = new String[] {
        Friends.Friend.NAME, Friends.Friend.LOCATION};
    public  Cursor mCursor;
    
    DrawFriendsOverlay drawFriendsOverlay = new DrawFriendsOverlay(); 
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.friendsmap);
 
        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(Friends.Friend.CONTENT_URI);
        }
        mCursor = managedQuery(getIntent().getData(), PROJECTION, null, null);
        
        final MapView myMap = (MapView) findViewById(R.id.myMap);
        final MapController myMapController = myMap.getController();
        LoadFriends(myMap, myMapController, mCursor);
        OverlayController myOverlayController = myMap.createOverlayController();
        myOverlayController.add(drawFriendsOverlay, true);
        final Button zoomIn = (Button) findViewById(R.id.buttonZoomIn);
                zoomIn.setOnClickListener(new Button.OnClickListener() {
       	  public void onClick(View v){
       		  ZoomIn(myMap,myMapController);
       	  }});
        final Button zoomOut = (Button) findViewById(R.id.buttonZoomOut);
        zoomOut.setOnClickListener(new Button.OnClickListener() {
       	  public void onClick(View v){
       		  ZoomOut(myMap,myMapController);
       	  }});
       final Button viewMap = (Button) findViewById(R.id.buttonMapView);
       viewMap.setOnClickListener(new Button.OnClickListener() {
      	  public void onClick(View v){
      		  ShowMap(myMap,myMapController);
      	  }});
       final Button viewSat = (Button) findViewById(R.id.buttonSatView);
       viewSat.setOnClickListener(new Button.OnClickListener() {
      	  public void onClick(View v){
      		  ShowSat(myMap,myMapController);
      	  }});
 
    }

    public void LoadFriends(MapView mv, MapController mc, Cursor c){
    	Point myLocation = null;
    	Double latPoint = null;
    	Double lngPoint = null;
    	c.first();
    	do{
    		if (c.getString(c.getColumnIndex("location")) != null) { 
                final String geoPattern = "(geo:[\\-]?[0-9]{1,3}\\.[0-9]{1,6}\\,[\\-]?[0-9]{1,3}\\.[0-9]{1,6}\\#)"; 
                Pattern pattern = Pattern.compile(geoPattern); 

                CharSequence inputStr = c.getString(c.getColumnIndex("location")); 
                Matcher matcher = pattern.matcher(inputStr); 

                boolean matchFound = matcher.find(); 
                if (matchFound) { 
                     String groupStr = matcher.group(0); 
                     latPoint = Double.valueOf(groupStr.substring(groupStr.indexOf(":") + 1, 
                               groupStr.indexOf(","))) ; 
                     lngPoint = Double.valueOf(groupStr.substring(groupStr.indexOf(",") + 1, 
                               groupStr.indexOf("#"))) ; 
                     Point friendLocation = new Point(latPoint.intValue(),lngPoint.intValue());
                     drawFriendsOverlay.addNewFriend(c.getString(c.getColumnIndex("name")), friendLocation);
                } 
           } 

	
     	}while(c.next());
    	LocationManager myManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	Double myLatPoint = myManager.getCurrentLocation("gps").getLatitude()*1E6;
        Double myLngPoint = myManager.getCurrentLocation("gps").getLongitude()*1E6;
    	myLocation = new Point(myLatPoint.intValue(),myLngPoint.intValue());
        drawFriendsOverlay.addNewFriend("Me", myLocation);
        
     	mc.centerMapTo(myLocation, false);
     	mc.zoomTo(9);
       mv = null;
    }

    public void ZoomIn(MapView mv, MapController mc){
    	if(mv.getZoomLevel()!=21){
    	mc.zoomTo(mv.getZoomLevel()+ 1);
    	}
    }
    public void ZoomOut(MapView mv, MapController mc){
    	if(mv.getZoomLevel()!=1){
        	mc.zoomTo(mv.getZoomLevel()- 1);
        	}
    }
    public void ShowMap(MapView mv, MapController mc){
    		if (mv.isSatellite()){
    			mv.toggleSatellite();
    		}
    }
    public void ShowSat(MapView mv, MapController mc){
		if (!mv.isSatellite()){
			mv.toggleSatellite();
		}
    }
    protected class DrawFriendsOverlay extends Overlay{
    	public String[] friendName = new String[0];
    	public Point[] friendPoint = new Point[0];
    	final Paint paint = new Paint(); 
        
    	@Override
    	public void draw(Canvas canvas, PixelCalculator calculator, boolean shadow){
    		for(int x=0;x<friendPoint.length; x++){
    			int[] coords = new int[2];
    			calculator.getPointXY(friendPoint[x], coords);
    	    RectF oval = new RectF(coords[0] - 7, coords[1] + 7, 
                          coords[0] + 7, coords[1] - 7);
    	    paint.setTextSize(14);
            canvas.drawText(friendName[x], 
                    coords[0] +9, coords[1], paint);
            canvas.drawOval(oval, paint);
    	    
    		}
    	}
    	public void addNewFriend(String name,Point point ){
    		int x = friendPoint.length;
    		
    		String[] friendNameB = new String[x + 1];
    		Point[] friendPointB = new Point[x + 1];
    		
    		System.arraycopy(friendName, 0, friendNameB, 0, x );
    		System.arraycopy(friendPoint, 0, friendPointB, 0, x);
    		
    		friendNameB[x] = name;
    		friendPointB[x]= point;
    		
    		friendName = new String[x + 1];
    		friendPoint = new Point[x + 1];
    		System.arraycopy(friendNameB, 0, friendName, 0, x + 1 );
    		System.arraycopy(friendPointB, 0, friendPoint, 0, x + 1 );   		
    		
    	}

    }
}
