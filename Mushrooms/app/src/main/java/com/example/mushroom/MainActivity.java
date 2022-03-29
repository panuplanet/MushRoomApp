package com.example.mushroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.ServiceCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Switch Swfan,Swpump,Swtermo,Swheater,Swlamp;
    public DatabaseReference myRef;
    private TextView mfirebaseView;
    private TextView mfirebaseView2;
    private TextView mfirebaseView9;
    private TextView mfirebaseView10;
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        backgroud();

        Swfan =findViewById(R.id.swfan);
        Swpump =findViewById(R.id.swpump);
        Swtermo=findViewById(R.id.swtermo);
        Swheater=findViewById(R.id.swtheater);
        Swlamp=findViewById(R.id.swlamp);

            mfirebaseView = (TextView) findViewById(R.id.txtTemp);
            mfirebaseView2 = (TextView) findViewById(R.id.txtHum);
            mfirebaseView9 = (TextView) findViewById(R.id.txtmushroom);
            mfirebaseView10 = (TextView) findViewById(R.id.txtdays);
            // get firebase reference
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    Map map = (Map) dataSnapshot.getValue();
                    assert map != null;

                    Map map1 = (Map) dataSnapshot.child("status").getValue();
                    final String value1 = String.valueOf(map1.get("Temperature"));
                    mfirebaseView.setText(value1);

                    Map map2 = (Map) dataSnapshot.child("status").getValue();
                    String value2 = String.valueOf(map2.get("Humidity"));
                    mfirebaseView2.setText(value2);


                    Map map3 = (Map) dataSnapshot.child("status").getValue();
                    String value3 = String.valueOf(map3.get("FAN"));

                    Map map4 = (Map) dataSnapshot.child("status").getValue();
                    String value4 = String.valueOf(map4.get("Heater"));


                    Map map5 = (Map) dataSnapshot.child("status").getValue();
                    String value5 = String.valueOf(map5.get("WaterPump"));

                    Map map6 = (Map) dataSnapshot.child("status").getValue();
                    String value6 = String.valueOf(map6.get("Thermocooler"));

                    Map map15 = (Map) dataSnapshot.child("Setting").getValue();
                    String value15 = String.valueOf(map15.get("MushroomName"));
                    mfirebaseView9.setText(value15);

                    Map map9 = (Map) dataSnapshot.child("status").getValue();
                    String value9 = String.valueOf(map9.get("days"));
                    mfirebaseView10.setText(value9);


                    if (value3.equals("ON")){
                        Swfan.setChecked(true);
                    }
                    if (value3.equals("OFF")){
                        Swfan.setChecked(false);
                    }

                    if (value5.equals("ON")) {
                        Swpump.setChecked(true);

                    } if (value5.equals("OFF")) {
                        Swpump.setChecked(false);
                    }

                    if (value6.equals("ON")) {
                        Swtermo.setChecked(true);

                    } if (value6.equals("OFF")) {
                        Swtermo.setChecked(false);
                    }
                    if (value4.equals("ON")) {
                        Swheater.setChecked(true);

                    } if (value4.equals("OFF")) {
                        Swheater.setChecked(false);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
            Swfan.setChecked(sharedPreferences.getBoolean("value",true));
            Swfan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        FirebaseDatabase.getInstance().getReference("status").child("FAN").setValue("ON");

                        SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean("value",true);
                        editor.apply();
                        Swfan.setChecked(true);
                    }
                    else {
                        SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean("value",false);
                        editor.apply();
                        Swfan.setChecked(false);
                        FirebaseDatabase.getInstance().getReference("status").child("FAN").setValue("OFF");
                    }
                }
            });


        Swpump.setChecked(sharedPreferences.getBoolean("value",true));
        Swpump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseDatabase.getInstance().getReference("status").child("WaterPump").setValue("ON");

                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Swpump.setChecked(true);
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Swpump.setChecked(false);
                    FirebaseDatabase.getInstance().getReference("status").child("WaterPump").setValue("OFF");
                }
            }
        });


        Swtermo.setChecked(sharedPreferences.getBoolean("value",true));
        Swtermo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseDatabase.getInstance().getReference("status").child("Thermocooler").setValue("ON");

                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Swtermo.setChecked(true);
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Swtermo.setChecked(false);
                    FirebaseDatabase.getInstance().getReference("status").child("Thermocooler").setValue("OFF");
                }
            }
        });


        Swheater.setChecked(sharedPreferences.getBoolean("value",true));
        Swheater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseDatabase.getInstance().getReference("status").child("Heater").setValue("ON");

                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Swheater.setChecked(true);
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Swheater.setChecked(false);
                    FirebaseDatabase.getInstance().getReference("status").child("Heater").setValue("OFF");
                }
            }
        });
        Swlamp.setChecked(sharedPreferences.getBoolean("value",true));
        Swlamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseDatabase.getInstance().getReference("status").child("Lamp").setValue("ON");

                    SharedPreferences.Editor editor =getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Swlamp.setChecked(true);
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Swlamp.setChecked(false);
                    FirebaseDatabase.getInstance().getReference("status").child("Lamp").setValue("OFF");
                }
            }
        });






        // ส่วนของ navigationbar

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setCheckedItem(R.id.nav_profile);
        navigationView.setCheckedItem(R.id.nav_manual);
        navigationView.setCheckedItem(R.id.nav_end);
        navigationView.setItemIconTintList(null);

    }

    private void backgroud() {


        Intent intent = new Intent(this,myBackgroudProcess.class);
        intent.setAction("BackgroudProcess");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,0,1000,pendingIntent);

    }

    @Override
    public void onBackPressed(){
        drawerLayout.isDrawerOpen(GravityCompat.START);
        drawerLayout.closeDrawer(GravityCompat.START);
    }
    // ส่วนของ menuitem
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem  ) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
            break;
            case R.id.nav_profile:
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
                break;
            case R.id.nav_manual:
                Intent intent3 = new Intent(MainActivity.this,Usermanual.class);
                startActivity(intent3);
                break;
            case R.id.nav_end:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("คุณแน่ใจหรือไม่ที่จะสิ้นสุดการเพาะเห็ด ?").setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference("status").child("Process").setValue(0);
                    }
                }).setNegativeButton("ไม่ใช่",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
        }


    }

