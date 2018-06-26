package vn.com.demo8.demo8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMusic extends ArrayAdapter<ModelMusic>{
    Context context;
    ArrayList<ModelMusic> modelMusics;
    int resources;

    public AdapterMusic(@NonNull Context context,
                        int resource,
                        @NonNull ArrayList<ModelMusic> objects) {
        super(context, resource, objects);
        this.context=context;
        this.modelMusics=objects;
        this.resources=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView==null){
           convertView= LayoutInflater.from(context).inflate(resources,null);
       }
       TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
       ModelMusic modelMusic = getItem(position);
       tvName.setText(modelMusic.getName());
       return convertView;
    }

}
