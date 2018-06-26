package vn.com.demo8.demo8;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG="MyService";
    public static final String ACTION ="vn.com.demo8.demo8.SEND_HELLO";
    public static final String ACTION_STOP ="vn.com.demo8.demo8.SERVICE_STOP";
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
        Intent intent1 = new Intent(ACTION_STOP);
        sendBroadcast(intent1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        mediaPlayer.start();
       // String data = intent.getStringExtra("data");
       // Log.e(TAG,data);

        Intent intent1 = new Intent(ACTION);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent1);

        return START_STICKY;
    }
}
