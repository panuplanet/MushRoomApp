package com.example.mushroom;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

import javax.xml.transform.Result;

public class Usermanual extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanual);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("คู่มือผู้ใช้งาน");
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setCheckedItem(R.id.nav_profile);
        navigationView.setCheckedItem(R.id.nav_manual);
        navigationView.setItemIconTintList(null);

        ViewPager mViewPager = (ViewPager)findViewById(R.id.viewPagger);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);

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
                Intent intent3 = new Intent(Usermanual.this,MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_profile:
                Intent intent = new Intent(Usermanual.this,Profile.class);
                startActivity(intent);
                break;
            case R.id.nav_manual:
                break;
            case R.id.nav_end:

                AlertDialog.Builder builder = new AlertDialog.Builder(Usermanual.this);
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


