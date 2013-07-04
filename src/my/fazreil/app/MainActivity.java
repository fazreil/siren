package my.fazreil.app;

import java.io.IOException;
import java.util.Timer;

import com.sonyericsson.illumination.IlluminationIntent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener{

	Button sirenButton = null;
	Boolean toggle = false;
	Intent intentStartLed = new Intent(IlluminationIntent.ACTION_START_LED_PULSE);
	Intent intentStartLedPulse = new Intent(IlluminationIntent.ACTION_START_LED_PULSE);
	Intent intentStopLed = new Intent(IlluminationIntent.ACTION_STOP_LED);
	Boolean run = true;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sirenButton = (Button)findViewById(R.id.sirenButton);
        sirenButton.setOnClickListener(this);
        sirenButton.setOnLongClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		String buttonText = sirenButton.getText().toString();
		
		sirenButton.setText(getResources().getString(R.string.siren_on));
		
//		try{
//			new SirenAlarmAsyncTask().execute(getApplicationContext(),getAssets().openFd("siren.mp3"));
//		}
//		catch(IOException ioeX)
//		{
//			Toast.makeText(getApplicationContext(), ioeX.getMessage(), Toast.LENGTH_SHORT).show();
//		}
//		catch(Exception ex)
//		{
//			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
//		}
		
		long duration = 1000;
		int count = 10;	
		while(count>0)
		{
			try{
				intentStartLed.putExtra(IlluminationIntent.EXTRA_LED_ID, IlluminationIntent.VALUE_BUTTON_RGB);
				intentStartLed.putExtra(IlluminationIntent.EXTRA_PACKAGE_NAME, "my.fazreil.app");
				intentStartLed.putExtra(IlluminationIntent.EXTRA_LED_PULSE_ON_TIME, 500);
				intentStartLed.putExtra(IlluminationIntent.EXTRA_LED_PULSE_OFF_TIME, 500);
				intentStartLed.putExtra(IlluminationIntent.EXTRA_LED_NO_OF_PULSES, 4);
	
				intentStopLed.putExtra(IlluminationIntent.EXTRA_LED_ID, IlluminationIntent.VALUE_BUTTON_RGB);
				intentStopLed.putExtra(IlluminationIntent.EXTRA_PACKAGE_NAME, "my.fazreil.app");
				
				if(toggle)
				{
					intentStartLed.putExtra(IlluminationIntent.EXTRA_LED_COLOR, 0xFFFF0000);
					
				}
				else
				{
					intentStartLed.putExtra(IlluminationIntent.EXTRA_LED_COLOR, 0xFF0000FF);
					
				}
				
				startService(intentStartLed);
				
				toggle=!toggle;
				count=count-1;
				Thread.currentThread().sleep(800);
				startService(intentStopLed);
			}
			catch(InterruptedException iex)
			{
				
			}
		}
		
	}


	@Override
	public boolean onLongClick(View v) {
		Toast.makeText(getApplicationContext(), sirenButton.getText().toString(), Toast.LENGTH_SHORT).show();
		
		sirenButton.setText(getResources().getString(R.string.siren_off));
		
		intentStopLed.putExtra(IlluminationIntent.EXTRA_LED_ID, IlluminationIntent.VALUE_BUTTON_RGB);
		intentStopLed.putExtra(IlluminationIntent.EXTRA_PACKAGE_NAME, "my.fazreil.app");
		startService(intentStopLed);
		run=false;
		return false;
	}
    
}
