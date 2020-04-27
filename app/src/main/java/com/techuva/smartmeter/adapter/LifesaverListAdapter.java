package com.techuva.smartmeter.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techuva.smartmeter.R;
import com.techuva.smartmeter.responseModel.LifesaverDropDownResultObject;

import java.util.List;

public class LifesaverListAdapter extends ArrayAdapter<LifesaverDropDownResultObject> {

    private int selectedPosition = -1;
    LayoutInflater flater;
    List<LifesaverDropDownResultObject> list;

    public LifesaverListAdapter(Activity context, int resouceId, int textviewId, List<LifesaverDropDownResultObject> list){
        super(context, resouceId, textviewId, list);
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LifesaverDropDownResultObject rowItem = getItem(position);

        View rowview = flater.inflate(R.layout.item_lifesaver_list,null,true);

        selectedPosition = rowItem.getLIFESAVERID();
        TextView txtTitle =  rowview.findViewById(R.id.tv_state_name);
        txtTitle.setText(String.valueOf(rowItem.getAMOUNT()));
        Typeface faceLight = Typeface.createFromAsset(rowview.getResources().getAssets(),
                "fonts/Roboto-Regular.ttf");
        txtTitle.setTypeface(faceLight);
        return rowview;
    }


    @Override
    public LifesaverDropDownResultObject getItem(int position) {
        return super.getItem(position);
    }

    public int ReturnPosition( String value)
    {
        return selectedPosition;
    }
}