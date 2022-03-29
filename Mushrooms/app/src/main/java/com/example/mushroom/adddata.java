package com.example.mushroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity {

    EditText MushroomName,highttemp,lowtemp,highthum,lowhum;
    Button submit,back;
    TextView stdate,enddate,sttime,endtime;
    ImageButton sdate,edate,stime,etime;
    int id =0;
    private int mYear,mMounth,mDay,mHour,mMinute;
    int a,b,c,d,e,f,g,h,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_adddata);
        MushroomName = (EditText) findViewById(R.id.add_name);
        highttemp = (EditText) findViewById(R.id.add_hight_temp);
        lowtemp = (EditText) findViewById(R.id.add_low_temp);
        highthum = (EditText) findViewById(R.id.add_hight_hum);
        lowhum = (EditText) findViewById(R.id.add_low_hum);
        back = (Button) findViewById(R.id.add_back);

        stdate = (TextView)findViewById(R.id.tv_date_start);
        sttime = (TextView)findViewById(R.id.tv_time_start);
        enddate = (TextView)findViewById(R.id.tv_date_end);
        endtime= (TextView)findViewById(R.id.tv_time_end);

        sdate=(ImageButton)findViewById(R.id.ib_date_start);
        stime=(ImageButton)findViewById(R.id.ib_time_start);
        edate=(ImageButton)findViewById(R.id.ib_date_end);
        etime=(ImageButton)findViewById(R.id.ib_time_end);



        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMounth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                fileList();
            }
        });

        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(adddata.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        stdate.setText(String.format("%04d-%02d-%02d",year,month + 1, dayOfMonth));
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(adddata.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sttime.setText(String.format("%02d:%02d",hourOfDay,minute));
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(adddata.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(adddata.this, new TimePickerDialog.OnTimeSetListener() {
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

        submit = (Button) findViewById(R.id.add_submit);
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
                if (stdate.length() == 0) {
                    f = 1;
                }
                if (sttime.length() == 0) {
                    g = 1;
                }
                if (enddate.length() == 0) {
                    h = 1;
                }
                if (endtime.length() == 0) {
                    i = 1;
                }
                if (a != 0 || b != 0 || c != 0 || d != 0 || e != 0 || f != 0 || g != 0 || h != 0 || i != 0) {

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
                        stdate.setError("กรุณากรอกข้อมูล");
                    }
                    if (g== 1) {
                        sttime.setError("กรุณากรอกข้อมูล");
                    }
                    if (h == 1) {
                        enddate.setError("กรุณากรอกข้อมูล");
                    }
                    if (i == 1) {
                        endtime.setError("กรุณากรอกข้อมูล");
                    }
                    return;
                }
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Profile");
                databaseReference.orderByChild("MushroomName").equalTo(MushroomName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                AlertDialog.Builder builder=new AlertDialog.Builder(MushroomName.getContext());
                                builder.setTitle("ไม่สามารถเพิ่มข้อมูลได้ !!");
                                builder.setMessage("โปรไฟล์นี้มีอยู่แล้ว !!");
                                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                                builder.show();
                            }
                        }
                             else{
                                processinsert();
                            }
                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                    });


            }
        });


   }
    private void processinsert()
    {

        Map<String,Object> map=new HashMap<>();
        map.put("MushroomName",MushroomName.getText().toString());
        map.put("highttemp",highttemp.getText().toString());
        map.put("lowtemp",lowtemp.getText().toString());
        map.put("highthum",highthum.getText().toString());
        map.put("lowhum",lowhum.getText().toString());
        map.put("startdate",stdate.getText().toString());
        map.put("enddate",enddate.getText().toString());
        map.put("starttime",sttime.getText().toString());
        map.put("endtime",endtime.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Profile").push()
                .setValue(map)
               .addOnSuccessListener(new OnSuccessListener<Void>() {

                   @Override
                   public void onSuccess(Void aVoid) {
                       MushroomName.setText("");
                       highttemp.setText("");
                       lowtemp.setText("");
                       highthum.setText("");
                       lowhum.setText("");
                       stdate.setText("");
                       sttime.setText("");
                       enddate.setText("");
                       endtime.setText("");
                       Toast.makeText(getApplicationContext(),"เพิ่มข้อมูลโปรไฟล์สำเร็จ !",Toast.LENGTH_LONG ).show();
                   }
               })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"เพิ่มข้อมูลโปรไฟล์ไม่สำเร็จ !",Toast.LENGTH_LONG ).show();
                    }
});
        FirebaseDatabase.getInstance().getReference("status").child("Process").setValue(1);
    }

}