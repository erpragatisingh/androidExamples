package chart.com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ChartTestActivity extends Activity {
 List<PieDetailsItem> piedata = new ArrayList<PieDetailsItem>(0);

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);

  PieDetailsItem item;
  int maxCount = 0;
  int itemCount = 0;
  int items[] = { 20, 40, 10, 15, 5 };
  int colors[] = { -6777216, -16776961, -16711681, -12303292, -7829368 };
  String itemslabel[] = { " vauesr ur 100", " vauesr ur 200",
    " vauesr ur 300", " vauesr ur 400", " vauesr ur 500" };
  for (int i = 0; i < items.length; i++) {
   itemCount = items[i];
   item = new PieDetailsItem();
   item.count = itemCount;
   item.label = itemslabel[i];
   item.color = colors[i];
   piedata.add(item);
   maxCount = maxCount + itemCount;
  }
  int size = 155;
  int BgColor = 0xffa11b1;
  Bitmap mBaggroundImage = Bitmap.createBitmap(size, size,
    Bitmap.Config.ARGB_8888);
  View_PieChart piechart = new View_PieChart(this);
  piechart.setLayoutParams(new LayoutParams(size, size));
  piechart.setGeometry(size, size, 2, 2, 2, 2,             2130837504      );
  piechart.setSkinparams(BgColor);
  piechart.setData(piedata, maxCount);
  piechart.invalidate();
  piechart.draw(new Canvas(mBaggroundImage));
  piechart = null;
  ImageView mImageView = new ImageView(this);
  mImageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
    LayoutParams.WRAP_CONTENT));
  mImageView.setBackgroundColor(BgColor);
  mImageView.setImageBitmap(mBaggroundImage);
  LinearLayout finalLayout = (LinearLayout) findViewById(R.id.pie_container);
  finalLayout.addView(mImageView);
 }
}
