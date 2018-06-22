package vn.com.demo8.demo8;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG="MyService";
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate");
        mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.file);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
        mediaPlayer.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        Log.e(TAG,"onStartCommand");
        mediaPlayer.start();
        String data = intent.getStringExtra("data");
        Log.e(TAG,data);
        return START_STICKY;
    }
}
