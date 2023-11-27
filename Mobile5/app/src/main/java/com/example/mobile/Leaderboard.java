package com.example.mobile;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView  name1, name2, name3, name4, name5,name6, name7, name8, mark1, mark2, mark3, mark4, mark5, mark6,mark7,mark8;
    private RecyclerView usersView;
    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);

        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);
        name6 = (TextView) findViewById(R.id.name6);
        name7 = (TextView) findViewById(R.id.name7);
        name8 = (TextView) findViewById(R.id.name8);
        mark1 = (TextView) findViewById(R.id.mark1);
        mark2 = (TextView) findViewById(R.id.mark2);
        mark3 = (TextView) findViewById(R.id.mark3);
        mark4 = (TextView) findViewById(R.id.mark4);
        mark5 = (TextView) findViewById(R.id.mark5);
        mark6 = (TextView) findViewById(R.id.mark6);
        mark7 = (TextView) findViewById(R.id.mark7);
        mark8 = (TextView) findViewById(R.id.mark8);

        TextView[] nameTV = {name1, name2, name3, name4, name5, name6, name7, name8};
        TextView[] markTV = {mark1, mark2, mark3, mark4, mark5, mark6, mark7, mark8};
        getRanks(nameTV,markTV);


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


    private void getRanks(TextView[] nameTV, TextView[] markTV){
        RequestQueue VolleyQueue = Volley.newRequestQueue(Leaderboard.this);
        String url = "http://114.132.199.139:8080/get/users";
        JsonObjectRequest obtaiAllUsers = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String code = "-1";
                        String msg = "null";
                        JSONObject data = null;
                        JSONArray rankList = null;
                        ArrayList<String> usernames = new ArrayList<>();
                        ArrayList<String> userRanks = new ArrayList<>();
                        try {
                            code = response.getString("code");
                            msg = response.getString("msg");
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"Load Data failed",Toast.LENGTH_SHORT).show();
                        }
                        if(code.equals("0")){
                            try {
                                data = response.getJSONObject("data");
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"Load Data failed",Toast.LENGTH_SHORT).show();
                            }
                            if(data != null){
                                try {
                                    rankList = data.getJSONArray("User");
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(),"Load Data failed",Toast.LENGTH_SHORT).show();
                                }
                                if(rankList != null){
                                    JSONObject userData = null;
                                    for(int i = 0; i < 8; i++){
                                        try {
                                            String username = rankList.getJSONObject(i).getString("username");
                                            String score = rankList.getJSONObject(i).getString("score");
                                            usernames.add(username);
                                            userRanks.add(score);
                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(),"Load Data failed",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    setRanks(nameTV,markTV,usernames,userRanks);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"No User Data",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
                    }
                });
        VolleyQueue.add(obtaiAllUsers);
    }

    private void setRanks(TextView[] nameTV, TextView[] markTV,ArrayList<String> usernames, ArrayList<String> userRanks){
        for(int i = 0; i < usernames.size(); i++){
            nameTV[i].setText(usernames.get(i));
            markTV[i].setText(userRanks.get(i));
        }
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
