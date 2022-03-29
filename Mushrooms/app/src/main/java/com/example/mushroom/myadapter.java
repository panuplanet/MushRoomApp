package com.example.mushroom;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.google.firebase.database.DataSnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    int a,b,c,d,e,f,g,h,i;
    private DatabaseReference root;
    private DatabaseReference myRef1;
    private int mYear,mMounth,mDay,mHour,mMinute;

    String ChkNameSet="";

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {

        holder.MushroomName.setText(model.getMushroomName());
        holder.highttemp.setText(model.getHighttemp());
        holder.lowtemp.setText(model.getLowtemp());
        holder.highthum.setText(model.getHighthum());
        holder.lowhum.setText(model.getLowhum());
        holder.startdate.setText(model.getStartdate());
        holder.starttime.setText(model.getStarttime());
        holder.enddate.setText(model.getEnddate());
        holder.endtime.setText(model.getEndtime());





        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMounth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);




       holder.edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.MushroomName.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                                   .setExpanded(true,850)
                                    .create();
                            View myview=dialogPlus.getHolderView();
                            final TextView MushroomName=myview.findViewById(R.id.uname);
                            final EditText highttemp=myview.findViewById(R.id.uhight_temp);
                            final EditText lowtemp=myview.findViewById(R.id.ulow_temp);
                            final EditText highthum=myview.findViewById(R.id.uhight_hum);
                            final EditText lowhum=myview.findViewById(R.id.ulow_hum);
                          final TextView startdate=myview.findViewById(R.id.u_date_start);
                            final TextView starttime=myview.findViewById(R.id.utime_start);
                            final TextView enddate=myview.findViewById(R.id.udate_end);
                            final TextView endtime=myview.findViewById(R.id.utime_end);




                            MushroomName.setText(model.getMushroomName());
                            highttemp.setText(model.getHighttemp());
                            lowtemp.setText(model.getLowtemp());
                            highthum.setText(model.getHighthum());
                            lowhum.setText(model.getLowhum());

                            Button submit =myview.findViewById(R.id.usubmit);
                            dialogPlus.show();

                             startdate.setText(model.getStartdate());
                            starttime.setText(model.getStarttime());
                            enddate.setText(model.getEnddate());
                            endtime.setText(model.getEndtime());

                            ImageButton sdate = myview.findViewById(R.id.uib_date_start);
                            ImageButton stime = myview.findViewById(R.id.uib_time_start);
                            final ImageButton edate = myview.findViewById(R.id.uib_date_end);
                            ImageButton etime = myview.findViewById(R.id.uib_time_end);

                            sdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DatePickerDialog datePickerDialog = new DatePickerDialog(holder.MushroomName.getContext(), new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                            startdate.setText(String.format("%04d-%02d-%02d",year,month + 1, dayOfMonth));
                                            mYear = year;
                                            mMounth = month;
                                            mDay = dayOfMonth;
                                        }
                                    },mYear, mMounth,mDay);
                                    datePickerDialog.show();
                                }
                            });

                            stime.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TimePickerDialog timePickerDialog = new TimePickerDialog(holder.MushroomName.getContext(), new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                            starttime.setText(String.format("%02d:%02d",hourOfDay,minute));
                                            mHour = hourOfDay;
                                            mMinute = minute;
                                        }
                                    },mHour,mMinute,true);
                                    timePickerDialog.show();
                                }
                            });

                            edate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DatePickerDialog datePickerDialog = new DatePickerDialog(holder.MushroomName.getContext(), new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                            enddate.setText(String.format("%04d-%02d-%02d",year,month + 1, dayOfMonth));
                                            mYear = year;
                                            mMounth = month;
                                            mDay = dayOfMonth;
                                        }
                                    },mYear, mMounth,mDay);
                                    datePickerDialog.show();
                                }
                            });

                            etime.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TimePickerDialog timePickerDialog = new TimePickerDialog(holder.MushroomName.getContext(), new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                            endtime.setText(String.format("%02d:%02d",hourOfDay,minute));
                                            mHour = hourOfDay;
                                            mMinute = minute;
                                        }
                                    },mHour,mMinute,true);
                                    timePickerDialog.show();
                                }
                            });



                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                     a = 0;
                                    b = 0;
                                    c = 0;
                                    d = 0;
                                    e = 0;
                                    f = 0;
                                    g = 0;
                                    h = 0;
                                    i = 0;
                                    if (MushroomName.length() == 0) {
                                        a = 1;
                                    }

                                    if (highttemp.length() == 0) {
                                        b = 1;
                                    }
                                    if (lowtemp.length() == 0) {
                                        c = 1;
                                    }
                                    if (highthum.length() == 0) {
                                        d = 1;
                                    }
                                    if (lowhum.length() == 0) {
                                        e = 1;
                                    }
                                 if (startdate.length() == 0) {
                                   f = 1;
                                     }
                                    if (enddate.length() == 0) {
                                       g = 1;
                                  }
                                 if (starttime.length() == 0) {
                                        h = 1;
                                    }
                                    if (endtime.length() == 0) {
                                        i = 1;
                                    }



                                    if (a != 0 || b != 0 || c != 0 || d != 0 || e != 0 || f !=0) {

                                        if (a == 1) {
                                            MushroomName.setError("กรุณากรอกข้อมูล");
                                        }
                                        if (b == 1) {
                                            highttemp.setError("กรุณากรอกข้อมูล");
                                        }
                                        if (c == 1) {
                                            lowtemp.setError("กรุณากรอกข้อมูล");
                                        }
                                        if (d == 1) {
                                            highthum.setError("กรุณากรอกข้อมูล");
                                        }
                                        if (e == 1) {
                                            lowhum.setError("กรุณากรอกข้อมูล");
                                        }
                                      if (f == 1) {
                                            startdate.setError("กรุณากรอกข้อมูล");
                                        }

                                       if (g== 1) {
                                            enddate.setError("กรุณากรอกข้อมูล");
                                        }
                                        if (h == 1) {
                                            starttime.setError("กรุณากรอกข้อมูล");
                                        }
                                        if (i == 1) {
                                            endtime.setError("กรุณากรอกข้อมูล");
                                        }
                                        return;
                                        }
                                    FirebaseDatabase.getInstance().getReference("status").child("Process").setValue(1);



                                    Map<String,Object> map=new HashMap<>();
                                    map.put("MushroomName",MushroomName.getText().toString());
                                    map.put("highttemp",highttemp.getText().toString());
                                    map.put("lowtemp",lowtemp.getText().toString());
                                    map.put("highthum",highthum.getText().toString());
                                    map.put("lowhum",lowhum.getText().toString());
                                    map.put("startdate",startdate.getText().toString());
                                    map.put("starttime",starttime.getText().toString());
                                    map.put("enddate",enddate.getText().toString());
                                    map.put("endtime",endtime.getText().toString());

                                    FirebaseDatabase.getInstance().getReference().child("Profile")
                                            .child(getRef(position).getKey()).updateChildren(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    dialogPlus.dismiss();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    dialogPlus.dismiss();
                                                }
                                            });

                                }
                    });


     }
        });

            holder.select.setOnClickListener(new View.OnClickListener() {
                    @Override
                         public void onClick(View v) {
                        root = FirebaseDatabase.getInstance().getReference("Setting");
                        String t = model.getMushroomName().toString();
                        int t2 = Integer.parseInt(model.getHighttemp());
                        int t3 = Integer.parseInt(model.getLowtemp());
                        int t4 = Integer.parseInt(model.getHighthum());
                        int t5 = Integer.parseInt(model.getLowhum());
                        String t6 = model.getStartdate().toString();
                        String t7 = model.getStarttime().toString();
                        String t8 = model.getEnddate().toString();
                        String t9 = model.getEndtime().toString();
                        HashMap hashMap = new HashMap();
                        hashMap.put("MushroomName", t);
                        hashMap.put("HighTemperature", t2);
                        hashMap.put("LowTemperature", t3);
                        hashMap.put("HighHumidity", t4);
                        hashMap.put("LowHumidity", t5);
                        hashMap.put("startdate",t6);
                        hashMap.put("starttime",t7);
                        hashMap.put("enddate",t8);
                        hashMap.put("endtime",t9);

                        Toast.makeText(holder.MushroomName.getContext(),"เลือกโปรไฟล์สำเร็จ !",Toast.LENGTH_LONG ).show();
                        root.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //  dialogPlus.dismiss();
                            }
                        });

                        FirebaseDatabase.getInstance().getReference("status").child("Process").setValue(1);


                  }

             });




        holder.delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (!holder.MushroomName.getText().toString().equals(ChkNameSet)) {
                                        AlertDialog.Builder builder=new AlertDialog.Builder(holder.MushroomName.getContext());
                                        builder.setTitle("ลบโปรไฟล์");
                                        builder.setMessage("คุณต้องการลบ ?");
                                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                FirebaseDatabase.getInstance().getReference().child("Profile")
                                                        .child(getRef(position).getKey()).removeValue();

                                            }
                                        });

                                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                        builder.show();
                                    } else {
                                        AlertDialog.Builder builder=new AlertDialog.Builder(holder.MushroomName.getContext());
                                        builder.setTitle("ลบโปรไฟล์");
                                        builder.setMessage("โปรไฟล์นี้ใช้งานอยู่ไม่สามารถลบได้!!");
                                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                        builder.show();
                                    }
                                }
                            });
                }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//                                     get firebase reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference();

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map map1 = (Map) dataSnapshot.child("Setting").getValue();
                ChkNameSet = String.valueOf(map1.get("MushroomName")).toString();
//                                            holder.MushroomName.setText(ChkNameSet);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView MushroomName,highttemp,lowtemp,highthum,lowhum,startdate,enddate,starttime,endtime;
        ImageView edit,delete,select;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            MushroomName=(TextView)itemView.findViewById(R.id.nametext);
            highttemp=(TextView)itemView.findViewById(R.id.hight_temp_text);
            lowtemp=(TextView)itemView.findViewById(R.id.low_temp_text);
            highthum=(TextView)itemView.findViewById(R.id.hight_hum_text);
            lowhum=(TextView)itemView.findViewById(R.id.low_hum_text);
            edit=(ImageView) itemView.findViewById(R.id.editicon);
            select=(ImageView)itemView.findViewById(R.id.select_icon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
            startdate=(TextView)itemView.findViewById(R.id.txt_sdate);
            starttime=(TextView)itemView.findViewById(R.id.txt_stime);
            enddate=(TextView)itemView.findViewById(R.id.txt_edate);
            endtime=(TextView)itemView.findViewById(R.id.txt_etime);


        }
    }
}
