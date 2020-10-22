package com.vemula.mathplus;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOver extends Activity{
	TextView tv;
	String c,score;
	int level;
	ImageView play,share,lb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover_main);
		
	//	AdView adView = (AdView)this.findViewById(R.id.adView);
	//	AdRequest adRequest = new AdRequest.Builder().build();
	//	adView.loadAd(adRequest);
		
		Bundle extras = getIntent().getExtras();
		play=(ImageView)findViewById(R.id.imageView1);
		share=(ImageView)findViewById(R.id.imageView2);
		tv=(TextView) findViewById(R.id.finalscore);
		score=String.valueOf(extras.getInt("score"));
		c="Score: "+score;
		level=extras.getInt("level");
		tv.setText(c);
		
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Level.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("key", String.valueOf(level));
				if(level==1 || level==2)
					i.putExtra("time", "10000");
				else if(level==3 || level==4)
					i.putExtra("time", "15000");
				else
					i.putExtra("time", "20000");
				startActivity(i);
			}
		});
		
		share.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
	            sharingIntent.setType("text/plain");
	            String shareBody = "I just scored "+score+" in level "+String.valueOf(level)+". Check it out - MathPlus on Google Play.";
	            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject");
	            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
	            startActivity(Intent.createChooser(sharingIntent, "Share via"));
				
				
			}
		});

	/*	lb.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
			}
		});*/
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i=new Intent(getApplicationContext(),MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

	
}

