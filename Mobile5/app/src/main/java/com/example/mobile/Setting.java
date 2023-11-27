package com.example.mobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Setting extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Switch switch_bgm, switch_sfx;
    Button save_btn, au_btn, privacy_btn;
    MediaPlayer bgm_player;

    private static Boolean bgm_status = true;
    private static Boolean effect_status = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);

        au_btn = findViewById(R.id.au_btn);

        privacy_btn = findViewById(R.id.privacy_btn);

        save_btn = findViewById(R.id.save_btn);
        switch_bgm = (Switch) findViewById(R.id.switch_bgm);
        switch_sfx = (Switch) findViewById(R.id.switch_sfx);

        if(getBgm_status() == true){
            play();
            switch_bgm.setChecked(true);
        }else{
            stop();
            switch_bgm.setChecked(false);
        }
        if(getEffect_status() == true){
            save_btn.setSoundEffectsEnabled(true);
            switch_bgm.setSoundEffectsEnabled(true);
            switch_sfx.setSoundEffectsEnabled(true);
            switch_sfx.setChecked(true);
        }else{
            save_btn.setSoundEffectsEnabled(false);
            switch_bgm.setSoundEffectsEnabled(false);
            switch_sfx.setSoundEffectsEnabled(false);
            switch_sfx.setChecked(false);
        }

        switch_bgm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( switch_bgm.isChecked()){
                    Toast.makeText(Setting.this, switch_bgm.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    setBGM_status(true);
                }else{
                    Toast.makeText(Setting.this, switch_bgm.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    setBGM_status(false);
                }
            }
        });
        switch_sfx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( switch_sfx.isChecked()){
                    Toast.makeText(Setting.this, switch_sfx.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    setEffect_status(true);
                }else{
                    Toast.makeText(Setting.this, switch_sfx.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    setEffect_status(false);
                }
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                stop();
                Intent i = new Intent(Setting.this, Home.class);
                startActivity(i);
            }
        });
        au_btn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Setting.this, AboutUs.class);
                startActivity(i);
            }
        });

        privacy_btn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Setting.this, Privacy.class);
                startActivity(i);
            }
        });

    }

    public static boolean getBgm_status(){
        return bgm_status;
    }
    public static void setBGM_status(boolean BGM_status){
        bgm_status = BGM_status;
    }
    public static boolean getEffect_status(){
        return effect_status;
    }
    public static void setEffect_status(boolean Effect_status){
        effect_status = Effect_status;
    }
    public void play(){
        if(bgm_player == null){
            bgm_player = MediaPlayer.create(this,R.raw.normalbgm);
        }
        bgm_player.start();
        bgm_player.setLooping(true);
    }

    public void pause(){
        if(bgm_player !=null){
            bgm_player.pause();
        }
    }

    public void stop(){
        stopPlayer();
    }

    private void stopPlayer(){
        if(bgm_player != null){
            bgm_player.release();
            bgm_player = null;
        }
    }

    protected void onStop(){
        super.onStop();
        stopPlayer();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), Home.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_quiz) {
            Intent i = new Intent(getApplicationContext(), Quiz.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_faq) {
            Intent i = new Intent(getApplicationContext(), FAQ.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_aboutus) {
            Intent i = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_learn) {
            Intent i = new Intent(getApplicationContext(), LearningMenu.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.nav_setting) {
            Intent i = new Intent(getApplicationContext(), Setting.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.nav_privacy) {
            Intent i = new Intent(getApplicationContext(), Privacy.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.nav_profile){
            Intent i = new Intent(getApplicationContext(),Profile.class);
            startActivity(i);
        } else if(item.getItemId() == R.id.nav_leaderboard) {
            Intent i = new Intent(getApplicationContext(), Leaderboard.class);
            startActivity(i);
        }
        drawerLayout.closeDrawer(nv);
        return true;
    }
}