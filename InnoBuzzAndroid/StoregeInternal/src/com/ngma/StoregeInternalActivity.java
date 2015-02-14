package com.ngma;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StoregeInternalActivity extends Activity {

	private EditText textBox;
	private static final int READ_BLOCK_SIZE = 100;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textBox = (EditText) findViewById(R.id.editText1);

		Button saveBtn = (Button) findViewById(R.id.btnSave);
		Button loadBtn = (Button) findViewById(R.id.btnLoad);

		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = textBox.getText().toString();

				try {
					FileOutputStream fOut = openFileOutput("textfile.txt",
							MODE_WORLD_READABLE);
					OutputStreamWriter osw = new OutputStreamWriter(fOut);
					// ---write the string to the file---
					osw.write(str);
					osw.flush();
					osw.close();
					// ---display file saved message---
					Toast.makeText(getBaseContext(),
							"File saved successfully!", Toast.LENGTH_SHORT)
							.show();
					// ---clears the EditText---
					textBox.setText("");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}
		});

		loadBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					FileInputStream fIn = openFileInput("textfile.txt");
					InputStreamReader isr = new InputStreamReader(fIn);
					char[] inputBuffer = new char[READ_BLOCK_SIZE];
					String s = "";
					int charRead;
					while ((charRead = isr.read(inputBuffer)) > 0) {
						// ---convert the chars to a String---
						String readString = String.copyValueOf(inputBuffer, 0,
								charRead);
						s += readString;
						inputBuffer = new char[READ_BLOCK_SIZE];

					}
					// ---set the EditText to the text that has been
					// read---
					textBox.setText(s);
					Toast.makeText(getBaseContext(),
							"File loaded successfully!", Toast.LENGTH_SHORT)
							.show();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});

	}
}