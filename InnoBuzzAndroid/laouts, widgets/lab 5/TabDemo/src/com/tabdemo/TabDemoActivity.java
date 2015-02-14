package com.tabdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
public class TabDemoActivity extends Activity {
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.main);

TabHost tabs=(TabHost)findViewById(R.id.tabhost);
tabs.setup();

TabHost.TabSpec spec;

spec=tabs.newTabSpec("");
spec.setContent(R.id.tab1);
spec.setIndicator("Clock");
tabs.addTab(spec);

spec=tabs.newTabSpec("");
spec.setContent(R.id.tab2);
spec.setIndicator("Button");
tabs.addTab(spec);
}
}