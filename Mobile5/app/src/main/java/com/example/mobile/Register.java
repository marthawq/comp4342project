package com.example.mobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    Button register,login_button;

    EditText username, regEmail, regPwd;

    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        username = findViewById(R.id.userNameInput);
        regEmail = findViewById(R.id.email_input);
        regPwd = findViewById(R.id.pwd_input);
        register = findViewById(R.id.register_button);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regUserName = username.getText().toString().trim();
                String newEmail = regEmail.getText().toString().trim();
                String newPwd = regPwd.getText().toString().trim();
                register(regUserName,newEmail,newPwd);
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

    private void register(String regUserName, String newEmail, String newPwd){
        String url = "http://114.132.199.139:8080/reg?" + "username=" + regUserName + "&" + "email=" + newEmail + "&" + "password=" + newPwd;
        RequestQueue VolleyQueue = Volley.newRequestQueue(Register.this);
        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String code = "-1";
                        String msg = "null";
                        try {
                            code = response.getString("code");
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                        }
                        if(code.equals("0")){
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        }
                        else if(code.equals("-1")){
                            try {
                                msg = response.getString("msg");
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"reponse error",Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();
                    }
                });
        VolleyQueue.add(registerRequest);
    }


}
