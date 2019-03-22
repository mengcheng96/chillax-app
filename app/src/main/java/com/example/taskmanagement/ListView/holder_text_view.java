package com.example.taskmanagement.ListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

//import com.example.asus.assignment2.R;
import com.example.taskmanagement.R;

public class holder_text_view implements View.OnLongClickListener,View.OnCreateContextMenuListener {
    TextView ActivityNameTxt;
    MyLongClickListener longClickListener;
    public holder_text_view(View v) {
        ActivityNameTxt= (TextView) v.findViewById(R.id.textview);

        v.setOnLongClickListener(this);
        v.setOnCreateContextMenuListener(this);
    }
    @Override
    public boolean onLongClick(View v) {
        this.longClickListener.onItemLongClick();
        return false;
    }

    public void setLongClickListener(MyLongClickListener longClickListener)
    {
        this.longClickListener=longClickListener;


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Index : ");
        menu.add(0, 0, 0, "Add New Item");
        menu.add(0,1,0,"EDIT");
        menu.add(0,2,0,"DELETE");


    }
}
