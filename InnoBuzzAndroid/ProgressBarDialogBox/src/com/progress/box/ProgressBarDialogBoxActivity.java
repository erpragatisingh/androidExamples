package com.progress.box;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class ProgressBarDialogBoxActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("installing!!!!...");
        dialog.setCancelable(true);
        dialog.show();
        
        dialog.setProgress(25);
        dialog.incrementProgressBy(2);
    }
}