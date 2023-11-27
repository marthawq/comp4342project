package com.example.mobile;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class AddLearn extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    TextView tv_title,tv_number, tv_0, tv_20,tv_40,tv_60,tv_80,tv_100;
    ImageView iv_learn;
    ProgressBar progressBar;
    Button btn_taptonext;
    private String[] learn_add_ans = {"4","5","6","7","8"} ;
    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_add);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);

        tv_title = findViewById(R.id.tv_title);
        tv_number = findViewById(R.id.tv_number);
        tv_0=findViewById(R.id.tv_0);
        tv_20 = findViewById(R.id.tv_20);
        tv_40 = findViewById(R.id.tv_40);
        tv_60 = findViewById(R.id.tv_60);
        tv_80 = findViewById(R.id.tv_80);
        tv_100 = findViewById(R.id.tv_100);
        iv_learn = findViewById(R.id.iv_learn);
        progressBar = findViewById(R.id.progressBar);
        btn_taptonext = findViewById(R.id.btn_taptonext);
        tv_0.setVisibility(INVISIBLE);
        tv_40.setVisibility(INVISIBLE);
        tv_60.setVisibility(INVISIBLE);
        tv_80.setVisibility(INVISIBLE);
        tv_100.setVisibility(INVISIBLE);


        btn_taptonext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(progressBar.getProgress()==20){
                    progressBar.setProgress(40);
                    iv_learn.setImageResource(R.drawable.add_1);
                    tv_20.setVisibility(INVISIBLE);
                    tv_40.setVisibility(VISIBLE);
                    tv_number.setText(learn_add_ans[1]);

                }else if(progressBar.getProgress()==40){
                    progressBar.setProgress(60);
                    iv_learn.setImageResource(R.drawable.add_2);
                    tv_40.setVisibility(INVISIBLE);
                    tv_60.setVisibility(VISIBLE);
                    tv_number.setText(learn_add_ans[2]);

                }else if(progressBar.getProgress()==60){
                    progressBar.setProgress(80);
                    iv_learn.setImageResource(R.drawable.add_3);
                    tv_60.setVisibility(INVISIBLE);
                    tv_80.setVisibility(VISIBLE);
                    tv_number.setText(learn_add_ans[3]);

                }else if(progressBar.getProgress()==80){
                    progressBar.setProgress(100);
                    iv_learn.setImageResource(R.drawable.add_4);
                    tv_80.setVisibility(INVISIBLE);
                    tv_100.setVisibility(VISIBLE);
                    tv_number.setText(learn_add_ans[4]);

                }else if(progressBar.getProgress()==100){
                    //code go back to main menu
                    Intent i = new Intent(AddLearn.this, AddCongrats.class);
                    startActivity(i);
                }



            }
        });

        if (bgm_status == true) {
            play();
        } else {
            stop();
        }
        if (sfx_status == true) {
            /*
            setting_btn.setSoundEffectsEnabled(true);
            aboutus_btn.setSoundEffectsEnabled(true);
            faq_btn.setSoundEffectsEnabled(true);
            quiz_btn.setSoundEffectsEnabled(true);
            learn_btn.setSoundEffectsEnabled(true);
            leaderboard_btn.setSoundEffectsEnabled(true);
             */
        } else {
            /*
            setting_btn.setSoundEffectsEnabled(false);
            aboutus_btn.setSoundEffectsEnabled(false);
            faq_btn.setSoundEffectsEnabled(false);
            quiz_btn.setSoundEffectsEnabled(false);
            learn_btn.setSoundEffectsEnabled(false);
            leaderboard_btn.setSoundEffectsEnabled(false);
             */
        }
        //

    }
    public void play() {
        if (bgm_player == null) {
            bgm_player = MediaPlayer.create(this, R.raw.normalbgm);
        }
        bgm_player.start();
        bgm_player.setLooping(true);
    }

    public static void pause() {
        if (bgm_player != null) {
            bgm_player.pause();
        }
    }

    public void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (bgm_player != null) {
            bgm_player.release();
            bgm_player = null;
        }
    }

    protected void onStop() {
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
        } else if(item.getItemId() == R.id.nav_leaderboard){
            Intent i = new Intent(getApplicationContext(),Leaderboard.class);
            startActivity(i);
        }
        drawerLayout.closeDrawer(nv);
        return true;
    }

}