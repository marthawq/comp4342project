package com.example.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout learn_layout, quiz_layout, faq_layout, aboutus_layout, layout_leaderboard, layout_profile, layout_setting;

    public boolean log_in = false;
    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);


        learn_layout = findViewById(R.id.learn_layout);
        quiz_layout = findViewById(R.id.quiz_layout);
        aboutus_layout = findViewById(R.id.aboutus_layout);
        faq_layout = findViewById(R.id.faq_layout);
        layout_leaderboard = findViewById(R.id.layout_leaderboard);
        layout_profile = findViewById(R.id.layout_profile);
        layout_setting = findViewById(R.id.layout_setting);


        aboutus_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, AboutUs.class);
                startActivity(i);
            }
        });

        faq_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, FAQ.class);
                startActivity(i);
            }
        });

        quiz_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Quiz.class);
                startActivity(i);
            }
        });

        learn_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, LearningMenu.class);
                startActivity(i);
            }
        });

        layout_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Setting.class);
                startActivity(i);
            }
        });

        layout_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Profile.class);
                startActivity(i);
            }
        });

        layout_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Leaderboard.class);
                startActivity(i);
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