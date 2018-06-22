package vn.com.demo8.demo8;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityControl extends AppCompatActivity {
    Button btnStart,btnStop,btnSeekTo,btnBindToService;
    EditText edtMil;
    BoundService boundService=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        btnBindToService= (Button)findViewById(R.id.btnBindService);
        btnStart=(Button)findViewById(R.id.btnStartMusic);
        btnStop=(Button)findViewById(R.id.btnStopMusic);
        btnSeekTo=(Button)findViewById(R.id.btnSeekTo);

        btnBindToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),BoundService.class);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(boundService!=null) {
                    boundService.play();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(boundService!=null) {
                    boundService.pause();
                }
            }
        });


    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder binder = (BoundService.MyBinder)iBinder;
            boundService=binder.getService();
            Log.e("ActivityControl","connect service successully");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}
