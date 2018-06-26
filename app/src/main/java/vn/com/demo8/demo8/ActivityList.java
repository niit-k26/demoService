package vn.com.demo8.demo8;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity{
    ArrayList<ModelMusic> modelMusics = new ArrayList<ModelMusic>();
    ListView lstView;
    BoundService boundService=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = new Intent(getBaseContext(),BoundService.class);
        startService(intent);

        lstView=(ListView) findViewById(R.id.lstView);

        final ModelMusic modelMusic = new ModelMusic();
        modelMusic.setName("Bai 1");
        modelMusic.setResources(R.raw.file);
        modelMusics.add(modelMusic);

        ModelMusic modelMusic2 = new ModelMusic();
        modelMusic2.setName("Bai 2");
        modelMusic2.setResources(R.raw.file_1);
        modelMusics.add(modelMusic2);

        ModelMusic modelMusic3 = new ModelMusic();
        modelMusic3.setName("Bai 3");
        modelMusic3.setResources(R.raw.file_2);
        modelMusics.add(modelMusic3);

        ModelMusic modelMusic4 = new ModelMusic();
        modelMusic4.setName("Bai 4");
        modelMusic4.setResources(R.raw.file_3);
        modelMusics.add(modelMusic4);

        ModelMusic modelMusic5 = new ModelMusic();
        modelMusic5.setName("Bai 5");
        modelMusic5.setResources(R.raw.file_4);
        modelMusics.add(modelMusic5);

        AdapterMusic adapterMusic = new AdapterMusic(getBaseContext(),R.layout.row_item,modelMusics);
        lstView.setAdapter(adapterMusic);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(boundService!=null){
                    boundService.play(modelMusics.get(i));
                }
            }
        });



        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }



    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder binder = (BoundService.MyBinder)iBinder;
            boundService=binder.getService();
            Log.e("ActivityControl","Connect service successully");

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
