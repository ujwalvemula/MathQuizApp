package com.vemula.mathplus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.*;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Level extends Activity{
	String key1,key2;
	int lev,scoreVal=0,temp,flag=0,cf=0,ic=0;
	String s="",prevOp="";
	int answer,wrong1,wrong2,wrong3;
	TextView tv,score,time,qg,timer;
	long startTime=10010,interval=1000,elapsedTime,currentTime,tim;
	boolean timerHasStarted;
	MyTimer cdTimer;
	Button o1,o2,o3,o4;
	List<String> options = new ArrayList<String>();
	LinearLayout layout;
	String color[]={"#55bbeb","#ff3e3f","#faa818","#367d7d","#ffc900","#36ce45","#37526d"};
	ImageView ib;
//	private InterstitialAd interstitial;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
/*		AdView adView = (AdView)this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		interstitial = new InterstitialAd(this);
	    interstitial.setAdUnitId("ca-app-pub-6479911927086716/9184374981");
	    AdRequest adRequest2 = new AdRequest.Builder().build();
	    interstitial.loadAd(adRequest2);
	    */
	/*	interstitial.setAdListener(new AdListener() { 
			@Override public void onAdLoaded() { 
				// TODO Auto-generated method stub 
				super.onAdLoaded(); 
				interstitial.show(); 
				}
		});*/

		
		tv=(TextView) findViewById(R.id.statement);
		score=(TextView) findViewById(R.id.scoretext);
//		time=(TextView) findViewById(R.id.timetext);
		qg=(TextView) findViewById(R.id.quittext);
		ib=(ImageView)findViewById(R.id.imageButton1);
		o1=(Button)findViewById(R.id.button1);
		o2=(Button)findViewById(R.id.button2);
		o3=(Button)findViewById(R.id.button3);
		o4=(Button)findViewById(R.id.button4);
		layout=(LinearLayout) findViewById(R.id.rel);
		
		timer = (TextView)findViewById(R.id.timetext);
 //       cdTimer = new MyTimer(startTime, 1000);
        
        
        Bundle extras = getIntent().getExtras();

		if (extras != null) {
			key1=extras.getString("key");
			lev=Integer.parseInt(key1);
			key2=extras.getString("time");
			tim=Integer.parseInt(key2);
		}
		cdTimer = new MyTimer(tim+10, 1000);
		
		qg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				timerHasStarted=false;
				cdTimer.cancel();
				endGame();
			}
		});
		
		ib.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				timerHasStarted=false;
				cdTimer.cancel();
				endGame();
			}
		});
		
		if(lev<6 && lev>0)
		{
			switch(lev)
			{
			case 1:statementGen(1,0,5,3);
				break;
				
			case 2:statementGen(2,0,10,3);
				break;
				
			case 3:statementGen(3,0,10,5);
				break;
			
			case 4:statementGen(4,0,20,3);
				break;
			
			case 5:statementGen(5,0,20,5);
				break;
				
			default:Log.d("switch case","default");
			}
		
		}
	}
	
	
	void  statementGen(final int level,int start,int end,int operandNo)
	{
		s="";
		options.clear();
		Log.d("inside","statementgen");
		for(int i=0;i<operandNo;i++)
		{
			temp=randInt(start,end);
			if(prevOp=="/")
			{
				while(temp==0)
				{
					temp=randInt(start,end);
				}
			
			}
			s+=temp;
			prevOp=operator();
			s+=prevOp;
			
		}
		s=s.substring(0,s.length()-1);
		answer=evaluate(s);
		Log.d("ans",String.valueOf(answer));
		
		if(level==1 || level==2)
		{
				wrong1=randInt(answer-10,answer+10);
				wrong2=randInt(answer-10,answer+10);
				wrong3=randInt(answer-10,answer+10);
				while(wrong1==answer)
				{
					wrong1=randInt(answer-10,answer+10);
				}
				while(wrong2==answer || wrong2==wrong1 )
				{
					wrong2=randInt(answer-10,answer+10);
				}
				while(wrong3==answer || wrong3==wrong1 || wrong3==wrong2)
				{
					wrong3=randInt(answer-10,answer+10);
				}
		}
		
		if(level==3 || level==4)
		{
				wrong1=randInt(answer-20,answer+20);
				wrong2=randInt(answer-20,answer+20);
				wrong3=randInt(answer-20,answer+20);
				while(wrong1==answer)
				{
					wrong1=randInt(answer-20,answer+20);
				}
				while(wrong2==answer || wrong2==wrong1 )
				{
					wrong2=randInt(answer-20,answer+20);
				}
				while(wrong3==answer || wrong3==wrong1 || wrong3==wrong2)
				{
					wrong3=randInt(answer-20,answer+20);
				}
		}
		
		if(level==5)
		{
				wrong1=randInt(answer-30,answer+30);
				wrong2=randInt(answer-30,answer+30);
				wrong3=randInt(answer-30,answer+30);
				while(wrong1==answer)
				{
					wrong1=randInt(answer-30,answer+30);
				}
				while(wrong2==answer || wrong2==wrong1 )
				{
					wrong2=randInt(answer-30,answer+30);
				}
				while(wrong3==answer || wrong3==wrong1 || wrong3==wrong2)
				{
					wrong3=randInt(answer-30,answer+30);
				}
		}
		
		options.add(String.valueOf(answer));
		options.add(String.valueOf(wrong1));
		options.add(String.valueOf(wrong2));
		options.add(String.valueOf(wrong3));
		Collections.shuffle(options);
		
		if(!timerHasStarted) {
			if(flag==0)
			{
				flag=10;
				cdTimer.start();
			}
			else if(flag==20)
			{
				flag=10;
				cdTimer.userDidWrong();
			}
			else
				cdTimer.userDidRight();
            timerHasStarted = true;
        } else {
            cdTimer.cancel();
            timerHasStarted = false;
        }
		
		ic=Color.parseColor(color[cf]);
		layout.setBackgroundColor(ic);
		
		tv.setText(s);
		score.setText("Score\n"+Integer.toString(scoreVal));
		o1.setTextColor(ic);
		o2.setTextColor(ic);
		o3.setTextColor(ic);
		o4.setTextColor(ic);
		o1.setText(options.get(0));
		o2.setText(options.get(1));
		o3.setText(options.get(2));
		o4.setText(options.get(3));
		cf=(cf+1)%color.length;
		
		
		o1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(answer==Integer.parseInt(o1.getText().toString()))
				{
					scoreVal++;
					timerHasStarted=false;
					nextQuestion(level);
				}
				else
				{
					flag=20;
					if(scoreVal<=0)
					{
						timerHasStarted=false;
						cdTimer.cancel();
						endGame();
					}
					else
					{
						scoreVal--;
						if(scoreVal==0)
						{
							timerHasStarted=false;
							cdTimer.cancel();
							endGame();
						}
						else
						{
							timerHasStarted=false;
							nextQuestion(level);
						}
					}
				}
			}
		});
		
		o2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(answer==Integer.parseInt(o2.getText().toString()))
				{
					scoreVal++;
					timerHasStarted=false;
					nextQuestion(level);
				}
				else
				{
					flag=20;
					if(scoreVal<=0)
					{
						timerHasStarted=false;
						cdTimer.cancel();
						endGame();
					}
					else
					{
						scoreVal--;
						if(scoreVal==0)
						{
							timerHasStarted=false;
							cdTimer.cancel();
							endGame();
						}
						else
						{
							timerHasStarted=false;
							nextQuestion(level);
						}
					}
				}
			}
		});
		
		o3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(answer==Integer.parseInt(o3.getText().toString()))
				{
					scoreVal++;
					timerHasStarted=false;
					nextQuestion(level);
				}
				else
				{
					flag=20;
					if(scoreVal<=0)
					{
						timerHasStarted=false;
						cdTimer.cancel();
						endGame();
					}
					else
					{
						scoreVal--;
						if(scoreVal==0)
						{
							timerHasStarted=false;
							cdTimer.cancel();
							endGame();
						}
						else
						{
							timerHasStarted=false;
							nextQuestion(level);
						}
					}	
				}
			}
		});

		o4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(answer==Integer.parseInt(o4.getText().toString()))
				{
					scoreVal++;
					timerHasStarted=false;
					nextQuestion(level);
				}
				else
				{
					flag=20;
					if(scoreVal<=0)
					{
						timerHasStarted=false;
						cdTimer.cancel();
						endGame();
					}
					else
					{
						scoreVal--;
						if(scoreVal==0)
						{
							timerHasStarted=false;
							cdTimer.cancel();
							endGame();
						}
						else
						{
							timerHasStarted=false;
							nextQuestion(level);
						}
					}
				}
			}
		});
	
	}
	
	
	void endGame()
	{
		Intent i=new Intent(getApplicationContext(),GameOver.class);
		i.putExtra("level", lev);
		i.putExtra("score", scoreVal);
		startActivity(i);
//		interstitial.show();
	}
	
	void nextQuestion(int level)
	{
		if(level==1)
			statementGen(1, 0,5,3);
		if(level==2)
			statementGen(2, 0,10,3);
		if(level==3)
			statementGen(3, 0,10,5);
		if(level==4)
			statementGen(4, 0,20,3);
		if(level==5)
			statementGen(5, 0,20,5);
	}
	
	public static int randInt(int min,int max)
	{
		Random rand=new Random();
		int randomNum=rand.nextInt((max-min)+1)+min;
		return randomNum;
	}
	
	public static String operator()
	{
		String ch="+";
		int j=randInt(1,4);
		switch(j)
		{
		case 1:ch="+";
		break;
		case 2:ch="-";
		break;
		case 3:ch="*";
		break;
		case 4:ch="/";
		break;
		}
		return ch;
	}

	
	
	
	
/*	public class Timer extends CountDownTimer {     
        public Timer(long startTime, long interval) {
            super(startTime, interval);
        }
        
        public void onFinish() {
        	endGame();
        }

        public void onTick(long millisUntilFinished) {
            timer.setText("Time remain: " + Long.toString(millisUntilFinished/1000));
        }
    }*/
	
	
	
	
	public class MyTimer {
	    private MyCountDownTimer mTimer;

	    public MyTimer(long deadline, long interval1)
	    {
	        startTime = deadline;
	        interval = interval1; 

	        mTimer = new MyCountDownTimer(startTime, interval);

	    }

	    public void cancel() {
			mTimer.cancel();
			
		}

		public synchronized void start() {

	       mTimer.start();

	    }

	    public void onTick(long time)
	    {
	    	mTimer.onTick(time);
	    }
	    public void onFinish()
	    {
	    	mTimer.onFinish();
	    }

	    public synchronized void userDidRight() 
	    {
	        mTimer.cancel();
	        if(lev==5)
	        {
	        	mTimer = new MyCountDownTimer( currentTime+20000, interval );
	        }
	        if(lev==3 || lev==4)
	        {
	        	mTimer = new MyCountDownTimer( currentTime+15000, interval );
	        }
	        else
	        {
	        	mTimer = new MyCountDownTimer( currentTime+5000, interval );
	        }
	        mTimer.start();
	    }
	    
	    public synchronized void userDidWrong() 
	    {
	        mTimer.cancel();
	        mTimer = new MyCountDownTimer( currentTime, interval );
	        mTimer.start();
	    }


	    private class MyCountDownTimer extends CountDownTimer {
	    	
	    	MyCountDownTimer(long startTime,long interval)
	    	{
	    		super(startTime, interval);
	    	}

	        @Override
			public void onFinish() {
	        	cdTimer.cancel();
	            endGame();
	        }

	        @Override
			public void onTick(long millisUntilFinished) {
	        	currentTime=millisUntilFinished;
	            timer.setText("Time Left\n" + Long.toString(millisUntilFinished/1000));
	        }
	    }

	}
	
	
	
	public static int evaluate(String expression)
    {
        Stack<Integer> op  = new Stack<Integer>();

        Stack<Double> val = new Stack<Double>();

        Stack<Integer> optmp  = new Stack<Integer>();

        Stack<Double> valtmp = new Stack<Double>();

        System.out.println("Evaluation Of Arithmetic Expression Using Stacks Test\n");

        System.out.println("Enter expression\n");

        String input = "0" + expression;

        input = input.replaceAll("-","+-");

        String temp = "";

        for (int i = 0;i < input.length();i++)

        {

            char ch = input.charAt(i);

            if (ch == '-')

                temp = "-" + temp;

            else if (ch != '+' &&  ch != '*' && ch != '/')

               temp = temp + ch;

            else

            {

                val.push(Double.parseDouble(temp));

                op.push((int)ch);

                temp = "";

            }

        }

        val.push(Double.parseDouble(temp));

        char operators[] = {'/','*','+'};

        for (int i = 0; i < 3; i++)

        {

            boolean it = false;

            while (!op.isEmpty())

            {

                int optr = op.pop();

                double v1 = val.pop();

                double v2 = val.pop();

                if (optr == operators[i])

                {

                    if (i == 0)

                    {

                        valtmp.push(v2 / v1);

                        it = true;

                        break;

                    }

                    else if (i == 1)

                    {

                        valtmp.push(v2 * v1);

                        it = true;

                        break;

                    }

                    else if (i == 2)

                    {

                        valtmp.push(v2 + v1);

                        it = true;

                        break;

                    }                                        

                }

                else

                {

                    valtmp.push(v1);

                    val.push(v2);

                    optmp.push(optr);

                }                

            }      

            while (!valtmp.isEmpty())

                val.push(valtmp.pop());

            while (!optmp.isEmpty())

                op.push(optmp.pop());

            if (it)

                i--;                            

        }      
		
        return (int)Math.floor(val.pop() + 0.5d);              //original
        
        
        //     return (int)Math.floor(val.pop() );
		
		
    }


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		timerHasStarted=false;
		cdTimer.cancel();
		endGame();
	}
	
	

  
} 
		
