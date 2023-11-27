package com.example.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity{


    Button login, register;
    EditText email,password;

    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();

    Model model = new Model();
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        login = findViewById(R.id.login_button);
        register = findViewById(R.id.signUp_button);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.pwd_input);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString().trim();
                String pwdInput = password.getText().toString().trim();
                loginVerification(emailInput,pwdInput);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
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

    private void loginVerification(String emailInput, String pwdInput){
        RequestQueue VolleyQueue = Volley.newRequestQueue(Login.this);
        String url = "http://114.132.199.139:8080/login/" + emailInput + "/" + pwdInput;
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = "null";
                        JSONObject data = null;
                        String code = "-1";
                        JSONObject user = null;
                        try {
                            code = response.getString("code");
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                        }
                        if(code.equals("-1")){
                            Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_SHORT).show();
                        }
                        else if(code.equals("0")){
                            try {
                                data = response.getJSONObject("data");
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                            }
                            if(data != null){
                                try {
                                    status = data.getString("status");
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                                }
                                if(status.equals("0")){
                                    Toast.makeText(getApplicationContext(),"incorrect password",Toast.LENGTH_SHORT).show();
                                }
                                else if(status.equals("1")){
                                    try {
                                        user = data.getJSONObject("User");
                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                                    }
                                    if(user != null){
                                        try {
                                            String id = user.getString("id");
                                            String email = user.getString("email");
                                            String username = user.getString("username");
                                            int score = user.getInt("score");
                                            model.getNewUser().set_userName(username);
                                            model.getNewUser().setUserId(id);
                                            model.getNewUser().setEmail(email);
                                            //TODO change when get highest score
                                            model.getNewUser().setHighest_Score(score);
                                            SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("username", username);
                                            editor.putString("id",id);
                                            editor.putString("email",email);
                                            //TODO change when get highest score
                                            editor.putInt("score",score);
                                            editor.commit();
                                            Intent i = new Intent(getApplicationContext(),Home.class);
                                            startActivity(i);
                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else if(status.equals("-1")){
                                    Toast.makeText(getApplicationContext(),"You have not registered.",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_LONG).show();
            }
        }
        );
        VolleyQueue.add(loginRequest);
    }

}
