package com.example.mushroom;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class myBackgroudProcess extends BroadcastReceiver {
    public DatabaseReference myRef;
    public int timediff,datediff ;
    @Override
    public void onReceive(final Context context, Intent intent) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map map = (Map) dataSnapshot.getValue();
                assert map != null;

                Map map9 = (Map) dataSnapshot.child("Alert").getValue();
                final String value9 = String.valueOf(map9.get("dhtSensor"));

                Map map10 = (Map) dataSnapshot.child("status").getValue();
                final String value10 = String.valueOf(map10.get("Process"));

                Map map11 = (Map) dataSnapshot.child("Alert").getValue();
                final String value11 = String.valueOf(map11.get("HighTemp"));


                Map map12 = (Map) dataSnapshot.child("Alert").getValue();
                final String value12 = String.valueOf(map12.get("LowTemp"));

                Map map13 = (Map) dataSnapshot.child("Alert").getValue();
                final String value13 = String.valueOf(map13.get("HighHumidity"));

                Map map14 = (Map) dataSnapshot.child("Alert").getValue();
                final String value14 = String.valueOf(map14.get("LowHumidity"));

                Map map15 = (Map) dataSnapshot.child("Alert").getValue();
                final String value15 = String.valueOf(map15.get("WaterLevel"));

                Map map16 = (Map) dataSnapshot.child("Alert").getValue();
                final String value16 = String.valueOf(map15.get("Connection"));

                //   Map map17 = (Map) dataSnapshot.child("Alert").getValue();
                //    final String value17 = String.valueOf(map15.get("LastUpdate"));

                Map map18 = (Map) dataSnapshot.child("Alert").getValue();
                final String value18 = String.valueOf(map15.get("CheckUpdate"));

             /*   Timer t = new Timer();

                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        if (value18.equals("1")) {
                            FirebaseDatabase.getInstance().getReference("Alert").child("CheckUpdate").setValue(0);
                        } else {
                         FirebaseDatabase.getInstance().getReference("Alert").child("CheckUpdate").setValue(1);
                        }
                    }
                };
                t.schedule(tt, 60000, 60000);


              */
                if (value9.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("ไม่สามารถอ่านค่าจากเซ็นเซอร์ได้");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(1, builder.build());
                    }
                }

                if (value10.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("จบการเพาะปลูก !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

                        managerCompat.notify(2, builder.build());

                    }
                }

                if (value11.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("อุณหภูมิสูงกว่าเกณฑ์ที่กำหนด !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

                        managerCompat.notify(3, builder.build());

                    }
                }

                if (value12.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("อุณหภูมิต่ำกว่าเกณฑ์ที่กำหนด !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

                        managerCompat.notify(4, builder.build());


                    }
                }

                if (value13.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("ความชื้นสูงกว่าเกณฑ์ที่กำหนด !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(5, builder.build());
                    }
                }

                if (value14.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("ความชื้นต่ำกว่าเกณฑ์ที่กำหนด !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(6, builder.build());
                    }
                }

                if (value15.equals("True")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("ระดับน้ำของตู้ต่ำเกินไป !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(7, builder.build());
                    }
                }


                Map map17 = (Map) dataSnapshot.child("Alert").getValue();
                final String value17 = String.valueOf(map15.get("LastUpdate"));

                Timer updateTimer = new Timer();
                updateTimer.schedule(new TimerTask() {
                    public void run() {
                        try {
                            Date dateAndTime = Calendar.getInstance().getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.getDefault());
                            String date = dateFormat.format(dateAndTime);

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                            Date date2 = format.parse(value17); //date database
                            Date date1 = format.parse(date); //date mobile
                            long mills = date1.getTime() - date2.getTime();
                            long seconds = mills / 1000;
                            long minutes = seconds / 60;
                            int mill = (int) (minutes);
                            timediff = mill;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, 0, 100);


                FirebaseDatabase.getInstance().getReference("Alert").child("timediff").setValue(timediff);
                if (value16.equals("True") && timediff > 4) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("ตู้ขาดการเชื่อมต่อกับอินเทอร์เน็ต !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(8, builder.build());
                    }
                }

                if (value16.equals("True")) {
                    FirebaseDatabase.getInstance().getReference("Alert").child("Connection").setValue("False");
                }

                Map map20 = (Map) dataSnapshot.child("Setting").getValue();
                final String value20 = String.valueOf(map20.get("enddate"));
                Map map21 = (Map) dataSnapshot.child("Setting").getValue();
                final String value21 = String.valueOf(map21.get("endtime"));

                Timer updateTimer2 = new Timer();
                updateTimer2.schedule(new TimerTask() {
                    public void run() {
                        try {
                            Date dateAndTime = Calendar.getInstance().getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.getDefault());
                            String date = dateFormat.format(dateAndTime);

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");
                            Date date1 = format.parse(date); //date mobile
                            Date date2 = format.parse(value20 + " " + value21); //date database
                            long difference = (date2.getTime() - date1.getTime());
                            long differenceDates = difference / (24 * 60 * 60 * 1000);
                            int days = (int) (differenceDates);
                            datediff = days;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, 0, 100);

                Map map22 = (Map) dataSnapshot.child("status").getValue();
                String value22 = String.valueOf(map22.get("days"));
                FirebaseDatabase.getInstance().getReference("status").child("days").setValue(datediff);

                if (value22.equals("0")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("My Notification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = context.getSystemService(NotificationManager.class);
                        Intent intent = new Intent(context, MainActivity.class);
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        manager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                        builder.setContentTitle("มีการแจ้งเตือนใหม่จากตู้เห็ด !");
                        builder.setContentText("ครบเวลาเก็บเกี่ยวเห็ดแล้ว !");
                        builder.setContentIntent(pendingIntent);
                        builder.setSmallIcon(R.drawable.ic_bell);
                        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
                        builder.setSound(alarmSound);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(9, builder.build());
                    }
                }
                if( ( datediff <= 0 )){
                    FirebaseDatabase.getInstance().getReference("status").child("days").setValue("0");
                }
                new CountDownTimer(60000, 60000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if (value18.equals("1")) {
                            FirebaseDatabase.getInstance().getReference("Alert").child("CheckUpdate").setValue(0);
                        } else {
                            FirebaseDatabase.getInstance().getReference("Alert").child("CheckUpdate").setValue(1);
                        }
                    }
                }.start();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
