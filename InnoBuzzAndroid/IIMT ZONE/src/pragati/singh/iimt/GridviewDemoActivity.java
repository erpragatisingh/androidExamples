package pragati.singh.iimt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GridviewDemoActivity extends Activity {
    /** Called when the activity is first created. */
	Integer[] mThumbIds = {R.drawable.iimii, R.drawable.iimta,R.drawable.iimtsa,R.drawable.iimtb,R.drawable.iimtc,
    		R.drawable.iimtg,R.drawable.imtd,R.drawable.iimtms,R.drawable.iimts,
    		R.drawable.iimtg};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galary);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
                Toast.makeText(GridviewDemoActivity.this, "u hav selected"+ position, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(), imagedisplay.class);
                i.putExtra("posi",position);
	          /*   String imageId = (String)parent.getAdapter().getItem(position).toString();
	             Intent intent = new Intent(getApplicationContext(), imagedisplay.class);
	             intent.putExtra("img", imageId);
	             startActivity(intent);    */                           
	             startActivity(i);                              

			}
        });

    }
    class ImageAdapter extends BaseAdapter{
    	private Context context;
    	ImageAdapter(Context c){
    		context=c;
    	}

    	@Override
    	public int getCount() {
    		// TODO Auto-generated method stub
    		return mThumbIds.length;
    	}

    	@Override
    	public Object getItem(int position) {
    		// TODO Auto-generated method stub
    		return null;
    	}

    	@Override
    	public long getItemId(int position) {
    		// TODO Auto-generated method stub
    		return 0;
    	}

    	@Override
    	public View getView(int position, View v, ViewGroup arg2) {
    		// TODO Auto-generated method stub
            ImageView imageView;
          
                imageView = new ImageView(context);
               // imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
               // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
               // imageView.setPadding(8, 8, 8, 8);
           
            imageView.setImageResource(mThumbIds[position]);
            return imageView;

    	}

        }
    }

