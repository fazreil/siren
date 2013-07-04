package my.fazreil.app;

import java.io.FileDescriptor;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.widget.Toast;

public class SirenSound implements Runnable {

	Context appContext;
	AssetFileDescriptor asd;
	
	public SirenSound(Context appContext, AssetFileDescriptor asd)
	{
		appContext = this.appContext;
		asd = this.asd;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Toast.makeText(appContext, "start()", Toast.LENGTH_SHORT).show();
		MediaPlayer player = new MediaPlayer();
		try {
			AssetFileDescriptor afd = asd;
			player.setDataSource(afd.getFileDescriptor());
			player.prepare();
			player.start();
		} catch (IllegalArgumentException e) {
			Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
		} catch (SecurityException e) {
			Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
		} catch (IllegalStateException e) {
			Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

}
