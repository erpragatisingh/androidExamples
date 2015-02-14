package test.chart;

import org.stockchart.StockChartView;
import org.stockchart.core.Area;
import org.stockchart.core.Axis;
import org.stockchart.core.Axis.ILabelFormatProvider;
import org.stockchart.core.Axis.Side;
import org.stockchart.core.AxisRange;
import org.stockchart.points.CustomPoint;
import org.stockchart.points.StockPoint;
import org.stockchart.series.AbstractSeries;
import org.stockchart.series.BarSeries;
import org.stockchart.series.LinearSeries;
import org.stockchart.series.StockSeries;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;

public class TestChartActivity extends Activity {
	
	private Handler mHandler = new Handler();
	private StockSeries s1 = new StockSeries();
	private BarSeries s2 = new BarSeries();    
    private LinearSeries s3 = new LinearSeries();
    
    private static long tttt = 0;
    StockChartView s;
    
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {

	        	double high = 1000 + Math.random()*500; // 1000 - 1500
	        	double low = 500+Math.random()*100; // 500 - 6000 
	        	double open = 700 + Math.random()*200;
	        	double close = 700 + Math.random()*200;
	        	
	        	StockPoint ss = new StockPoint(++tttt);
	        	ss.setLow(low);
	        	ss.setHigh(high);
	        	ss.setClose(close);
	        	ss.setOpen(open);
	        	
	        	s1.addPoint(ss);
	        	s2.addPoint(new CustomPoint(-tttt,new double[] {0, Math.random()*20000} ));	        	
	        	s3.addPoint(new CustomPoint(tttt, new double[] { close } ));
	        	
	        	s.invalidate();
	        	
	        	mHandler.postDelayed(this, 1000);
	        
		   }
		};
	
    /** Called when the activity is first created. */
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        s = new StockChartView(this);
        
        AxisRange ar = new AxisRange();
        ar.setMovable(true);
        ar.setZoomable(true);
        
        s.enableGlobalAxisRange(Axis.Side.BOTTOM, ar);
        
        Area a1 = new Area();
        Area a2 = new Area();
        a1.getRightAxis().setLabelFormatProvider(new ILabelFormatProvider()
        {

			@Override
			public String getAxisLabel(Axis sender, double value) {
				return String.valueOf(value);
			}
        	
        });
        
        a2.setAutoHeight(false);
        a2.setHeightInPercents(0.2f);
        a2.getBottomAxis().setLabelFormatProvider(new ILabelFormatProvider() 
        {
			@Override
			public String getAxisLabel(Axis sender, double value) 
			{			
				Area a = sender.getParent();
				
				for(int i=0;i<a.getSeriesCount();i++)
				{
					AbstractSeries s = a.getSeriesAt(i);
					
					int index = s.convertToArrayIndex(value);
					if(index >=0 && index < s.getPointCount())
					{
						Object id = s.getPointAt(index).getID();
						
						if(null != id)
							return id.toString();
					}
				}
				
				return null;
			}
        	
        });
        
                
        s2.setYAxisSide(Side.LEFT);
        s2.setBarColor(Color.GRAY);
        s3.setYAxisSide(Side.RIGHT);        
        
        a1.getLeftAxis().setVisible(false);
        a1.getTopAxis().setVisible(false);
        
        a2.getLeftAxis().setVisible(false);
        a2.getTopAxis().setVisible(false);
        
        a1.addSeries(s2);
        a1.addSeries(s1);

        a2.addSeries(s3);

        s.addArea(a1);
        s.addArea(a2);

        setContentView(s, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
       
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }
}