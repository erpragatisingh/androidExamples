package pragati.singh.iimt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class imagedisplay extends Activity {
    Integer[] mThumbIds = {R.drawable.iimii, R.drawable.iimta,R.drawable.iimtsa,R.drawable.iimtb,R.drawable.iimtc,
    		R.drawable.iimtg,R.drawable.imtd,R.drawable.iimtms,R.drawable.iimts,
    		R.drawable.iimtg};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galaryshow);
		Intent i=getIntent();
		int j=i.getIntExtra("posi",0);
		
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	imageView.setImageResource(mThumbIds[j]);
	Log.d("ing",""+j);

	}

}
