package my.fazreil.app;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.Toast;

public class SirenAlarmAsyncTask<Result> extends AsyncTask<Object, Integer, Long> {

	@Override
	protected Long doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		Context appContext = (Context)arg0[0];
		AssetFileDescriptor afd = (AssetFileDescriptor)arg0[1];
		
		Toast.makeText(appContext, "start()", Toast.LENGTH_SHORT).show();
		MediaPlayer player = new MediaPlayer();
		try {
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
		return null;
	}

	protected void onProgressUpdate(Integer... progress) {

    }
	
	protected void onPostExecute(Long result) {
        
    }

	
}
