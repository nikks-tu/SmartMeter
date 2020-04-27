package com.techuva.smartmeter.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.responseModel.CurrentDataValueObject;
import com.techuva.smartmeter.view.TextViewIcomoon;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GridAdapterDashboard extends ArrayAdapter<CurrentDataValueObject> {
    private final int resource;
    private Context mContext;
    ArrayList<CurrentDataValueObject> list;

    public GridAdapterDashboard(@NonNull Context context, int resource, @NonNull ArrayList<CurrentDataValueObject> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.mContext = context;
        this.list = objects;
    }

    // Constructor


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CurrentDataValueObject getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getChannelNumber(int position)
    {
        return list.get(position).getChannelNumber();
    }

    // create a new ImageView for each item referenced by the Adapter
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        RecordHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            holder = new RecordHolder();
            holder.tv_channel_icon = row.findViewById(R.id.tv_channel_icon);
            holder.tv_channel_name = row.findViewById(R.id.tv_channel_name);
            holder.tv_channel_value = row.findViewById(R.id.tv_channel_value);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        CurrentDataValueObject item = list.get(position);


        String s2;

        if(item.getIcon()== null)
        {
            //Toast.makeText(mContext, "Null", Toast.LENGTH_SHORT).show();
            s2="";
        }
        else {
          s2 = item.getIcon();
        }

        if(!s2.equals(""))
        {
            String s1 = s2.replaceAll("&#x", "");

            s1 = s1.replaceAll(";", "");

            String icon = new String(Character.toChars(Integer.parseInt(
                    s1, 16)));

            //String temp = capitalize(item.getLabel());
            if (!icon.equals(""))
            {
                holder.tv_channel_icon.setText(icon);
            }else {
                holder.tv_channel_icon.setVisibility(View.GONE);
            }
        }


        Typeface faceLight = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/AvenirLTStd-Light.otf");
        holder.tv_channel_name.setTypeface(faceLight);
        //holder.tv_channel_icon.setTypeface(faceLight);
        String s = item.getLabel();
        s = s.replaceAll("_", " ");
        holder.tv_channel_name.setText(s);
        holder.tv_channel_name.setTypeface(faceLight);
        String value;
        Double value_d=0.0;
        try {
            value = String.valueOf(item.getValue());
            value_d = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // p did not contain a valid double
        }
        holder.tv_channel_value.setText(String.format("%.2f", value_d));
        holder.tv_channel_value.setTypeface(faceLight);
        return row;
    }
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    static class RecordHolder {
        TextViewIcomoon tv_channel_icon;
        TextView tv_channel_name;
        TextView tv_channel_value;
    }
    // Keep all Images in array

}
