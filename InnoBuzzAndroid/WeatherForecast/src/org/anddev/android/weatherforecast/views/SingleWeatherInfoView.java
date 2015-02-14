package org.anddev.android.weatherforecast.views;

import org.anddev.android.weatherforecast.R;

/**
 * The View capable of showing a WeatehrIcon + a Temperature-TextView.
 */
public class SingleWeatherInfoView extends LinearLayout {

	// ===========================================================
	// Fields
	// ===========================================================

	private ImageView myWeatherImageView = null;
	private TextView myTempTextView = null;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SingleWeatherInfoView(Context context) {
		super(context);
	}

	public SingleWeatherInfoView(Context context, AttributeSet attrs,
			Map inflateParams) {
		super(context, attrs, inflateParams);
		/* Setup the ImageView that will show weather-icon. */
		this.myWeatherImageView = new ImageView(context);
		this.myWeatherImageView.setImageDrawable(getResources().getDrawable(
				R.drawable.dunno));

		/* Setup the textView that will show the temperature. */
		this.myTempTextView = new TextView(context);
		this.myTempTextView.setText("? �C");
		this.myTempTextView.setTextSize(16);
		this.myTempTextView.setTypeface(Typeface
				.create("Tahoma", Typeface.BOLD));

		/* Add child views to this object. */
		this.addView(this.myWeatherImageView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.addView(this.myTempTextView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void reset() {
		this.myWeatherImageView.setImageDrawable(getResources().getDrawable(
				R.drawable.dunno));
		this.myTempTextView.setText("? �C");
	}

	/** Sets the Child-ImageView of this to the URL passed. */
	public void setRemoteImage(URL aURL) {
		try {
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			Bitmap bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
			this.myWeatherImageView.setImageBitmap(bm);
		} catch (IOException e) {
			/* Reset to 'Dunno' on any error. */
			this.myWeatherImageView.setImageDrawable(getResources()
					.getDrawable(R.drawable.dunno));
		}
	}

	public void setTempCelcius(int aTemp) {
		this.myTempTextView.setText("" + aTemp + " �C");
	}

	public void setTempFahrenheit(int aTemp) {
		this.myTempTextView.setText("" + aTemp + " �F");
	}

	public void setTempFahrenheitMinMax(int aMinTemp, int aMaxTemp) {
		this.myTempTextView.setText("" + aMinTemp + "/" + aMaxTemp + " �F");
	}

	public void setTempCelciusMinMax(int aMinTemp, int aMaxTemp) {
		this.myTempTextView.setText("" + aMinTemp + "/" + aMaxTemp + " �C");
	}

	public void setTempString(String aTempString) {
		this.myTempTextView.setText(aTempString);
	}
}
