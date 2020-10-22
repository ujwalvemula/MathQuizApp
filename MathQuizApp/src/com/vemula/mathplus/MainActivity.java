package com.vemula.mathplus;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	TextView l1,l2,l3,l4,l5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	/*	AdView adView = (AdView)this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);*/
		
		l1=(TextView)findViewById(R.id.textView1);
		l2=(TextView)findViewById(R.id.textView2);
		l3=(TextView)findViewById(R.id.textView3);
		l4=(TextView)findViewById(R.id.textView4);
		l5=(TextView)findViewById(R.id.textView5);
		
		l1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Level.class);
				i.putExtra("key", "1");
				i.putExtra("time", "10000");
				startActivity(i);
				
			}
		});
		
		l2.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Level.class);
				i.putExtra("key", "2");
				i.putExtra("time", "10000");
				startActivity(i);
			}
		});
		
		l3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Level.class);
				i.putExtra("key", "3");
				i.putExtra("time", "15000");
				startActivity(i);
			}
		});
		
		l4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Level.class);
				i.putExtra("key", "4");
				i.putExtra("time", "15000");
				startActivity(i);
			}
		});
		
		l5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Level.class);
				i.putExtra("key", "5");
				i.putExtra("time", "20000");
				startActivity(i);
			}
		});

		
	}
}
