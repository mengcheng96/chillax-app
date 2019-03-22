package com.example.taskmanagement.Adapters;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskmanagement.pesonal.ListTaskActivity;
import com.example.taskmanagement.Activities.AlarmActivity;
import com.example.taskmanagement.Activities.AlarmReceiver;
import com.example.taskmanagement.Activities.HomeActivity;
import com.example.taskmanagement.Models.Post;
import com.example.taskmanagement.R;

import java.util.Calendar;
import java.util.List;

//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
//import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> /*implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener*/ {

    Context mContext;
    List<Post> mData;
    OnItemClickListener mListener;
    Calendar now = Calendar.getInstance();
   // TimePickerDialog tpd;
   // DatePickerDialog dpd;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_task_item,parent, false);

        return new MyViewHolder(row);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgTask);
        Glide.with(mContext).load(mData.get(position).getUserPhoto()).into(holder.imgTaskProfile);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

   /* @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        FragmentManager manager=((Activity)mContext).getFragmentManager();
        tpd.show(manager, "Timepickerdialog");

    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        long time;
        Calendar calendar = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
        now.set(Calendar.MINUTE,minute);
        now.set(Calendar.SECOND,second);

        //Toast.makeText(mContext, "Hello I am inside Non Activity Class+",
              //  Toast.LENGTH_SHORT).show();

        Intent dialogIntent = new Intent(mContext, AlarmReceiver.class);
        alarmManager = (AlarmManager)this.mContext.getSystemService(Context.ALARM_SERVICE);
       // alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
       // pendingIntent = PendingIntent.getBroadcast(mContext, 0, dialogIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);

        time=(now.getTimeInMillis()-(now.getTimeInMillis()%60000));

        if(System.currentTimeMillis()>time)
        {
            if (now.AM_PM == 0)
                time = time + (1000*60*60*12);
            else
                time = time + (1000*60*60*24);
        }
        Toast.makeText(mContext, "Hello I am inside Non Activity Class+",
                Toast.LENGTH_SHORT).show();

      //  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,time, 10000, pendingIntent);
     /*   Intent intent = new Intent(mContext,Home.class);
        intent.putExtra("test","I am a String");
        NotifyMe notifyMe = new NotifyMe.Builder(mContext)
                .title("hello")
                .content("Teoh Meng Cheng")
                .color(255,0,0,255)
                .led_color(255,255,255,255)
                .time(now)
                .addAction(intent,"Snooze",false)
                .key("test")
                .addAction(new Intent(),"Dismiss",true,false)
                .addAction(intent,"Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();*/

   // }

public void notifaction(){
  /*  Notification notification = new Notification.Builder(mContext)
            // Show controls on lock screen even when user hides sensitive content.
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setStyle(new Notification.MediaStyle()
                    .setShowActionsInCompactView(1)
            )
            .setContentTitle("Example for you")
            .setContentText("Example for you")

            .build();*/


}


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvTitle;
        ImageView imgTask;
        ImageView imgTaskProfile;
        Button btnNotify = itemView.findViewById(R.id.btnNotify);
        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.row_task_title);
            imgTask = itemView.findViewById(R.id.row_task_img);
            imgTaskProfile = itemView.findViewById(R.id.row_task_profile_img);
            //date and time
          /*  dpd = DatePickerDialog.newInstance(
                    PostAdapter.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );

            tpd = TimePickerDialog.newInstance(
                    PostAdapter.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    now.get(Calendar.SECOND),
                    false
            );*/
            btnNotify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, ListTaskActivity.class);
                    mContext.startActivity(i);
                  //  FragmentManager manager=((Activity)mContext).getFragmentManager();

                  //  dpd.show(manager, "Datepickerdialog");
                }
            });
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
          //  itemView.setOnCreateContextMenuListener(this);
        }




        @Override
        public void onClick(View view) {
            /*
            //Toast.makeText(view.getContext(),"Test",Toast.LENGTH_LONG).show();
            //Log.d("DEBUGTHIS","on click runs");
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }

            }
            */
        }


        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(view.getContext(),"Test",Toast.LENGTH_LONG).show();
            Log.d("DEBUGTHIS","on click runs");
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemLongClick(position);
                }

            }
            return true;
        }
    }

        /*@Override
        public boolean onLongClick(View view) {
        return false;
        }


    }
*/
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onShowItemClick(int position);
        void onDeleteItemClick(int position);
        void onItemLongClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
