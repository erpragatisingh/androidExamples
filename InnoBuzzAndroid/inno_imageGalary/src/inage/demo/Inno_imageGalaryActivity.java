package inage.demo;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Inno_imageGalaryActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, 0);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 0)

		{

			if (resultCode == RESULT_OK)

			{

				Uri imageuriUri = data.getData();
				ImageView imageView = (ImageView) findViewById(R.id.imageView1);
				imageView.setImageURI(imageuriUri);

			}

		}

	}
}
