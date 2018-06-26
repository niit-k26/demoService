package vn.com.demo8.demo8;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStartService,btnStopService,btnNextActivity;
    MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lăng nghe services tra ve
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.ACTION);
        intentFilter.addAction(MyService.ACTION_STOP);
        myReceiver = new MyReceiver();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(myReceiver,intentFilter);

        Intent intent = new Intent(getBaseContext(),MyService.class);
        startService(intent);


    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equalsIgnoreCase(MyService.ACTION)){
                Toast.makeText(getApplicationContext(),"Services Start",Toast.LENGTH_SHORT).show();
            }

            if(intent.getAction().equalsIgnoreCase(MyService.ACTION_STOP)){
                Toast.makeText(getApplicationContext(),"Services Stop",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(myReceiver);
    }
}
