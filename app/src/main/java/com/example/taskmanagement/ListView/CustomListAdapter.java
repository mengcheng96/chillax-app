package com.example.taskmanagement.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import com.example.taskmanagement.Object_transaction.Object;

import com.example.taskmanagement.R;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private Context mContext;
    Context c;
    ArrayList<Object> mObjects;
    LayoutInflater inflater;
    Object object;
    OnOptionSelectedListener myListener;

    public void setOnMasterSelectedListener(OnOptionSelectedListener listener){
        myListener=listener;
    }

    public CustomListAdapter(Context c,  ArrayList<Object> mObjects) {
        this.c = c;
        this.mObjects= mObjects;
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.listview,parent,false);
        }

        //BIND DATA
        holder_text_view holder=new holder_text_view(convertView);
        holder.ActivityNameTxt.setText(mObjects.get(position).getAcName());

       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(c, mObjects.get(position).getAcName(), Toast.LENGTH_SHORT).show();
                myListener.onOptionSelected( mObjects.get(position).getId());


            }
        });

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {
                object= (Object) getItem(position);
            }
        });

        return convertView;

    }

public interface OnOptionSelectedListener{
        public void onOptionSelected(int message);

}
    //EXPOSE NAME AND ID
    public int getSelectedItemID()
    {
        return object.getId();
    }
    public String getSelectedItemName()
    {
        return object.getAcName();
    }



}

