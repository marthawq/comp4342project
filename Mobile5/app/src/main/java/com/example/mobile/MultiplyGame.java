package com.example.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MultiplyGame extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    TextView ans_left, ans_right, tv_question, result;
    ImageView iv_alien, ufo_right, ufo_left;
    public static String[] Q;
    public static String[] A;
    int num = 1;
    int random_Q, rqlocation, correctlocation, ans_a, ans_b;

    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);


        ans_left = findViewById(R.id.ans_left);
        ans_right = findViewById(R.id.ans_right);
        tv_question = findViewById(R.id.tv_question);
        result = findViewById(R.id.result);

        iv_alien = findViewById(R.id.iv_alien);
        ufo_right = findViewById(R.id.ufo_right);
        ufo_left = findViewById(R.id.ufo_left);


        Q = getResources().getStringArray(R.array.questions_game_multiply);
        A = getResources().getStringArray(R.array.answers_game_multiply);
        ArrayList<Integer> rqlist = new ArrayList<Integer>(1000);

        //set question
        for (int i = 0; i < Q.length; i++) rqlist.add(i);
        Collections.shuffle(rqlist);
        random_Q = rqlist.get(0);

        //shuffle the question list as random
        ArrayList<Integer> list = new ArrayList<Integer>(1000);
        for (int i = 0; i < Q.length; i++) list.add(i);
        Collections.shuffle(list);

        //
        rqlocation = list.indexOf(random_Q);
        list.remove(rqlocation);

        //
        ArrayList<Integer> anslist = new ArrayList<Integer>(1000);
        for (int i = 1; i <= 1; i++) anslist.add(list.get(i));
        anslist.add(random_Q);
        Collections.shuffle(anslist);

        tv_question.setText(Q[random_Q]);

        ans_a = anslist.get(0);
        ans_b = anslist.get(1);

        ans_left.setText(A[ans_a]);
        ans_right.setText(A[ans_b]);

        correctlocation = anslist.indexOf(random_Q);
        //ufo_left.setOnDragListener(dragListener);
        //ufo_right.setOnDragListener(dragListener);


        ufo_left.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                if (action == DragEvent.ACTION_DROP) {
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    if (view.getId() == R.id.iv_alien) {
                        if (ans_a == random_Q){
                            result.setText("correct!!");

                            if (num == Q.length) {
                                //result.setText("The end. Press to ")
                                Intent i = new Intent(MultiplyGame.this, GameEnd.class);
                                startActivity(i);
                            }

                            if(num != Q.length){
                            //set question
                            rqlist.remove(0);
                            random_Q = rqlist.get(0);
                            //gen list random place
                            ArrayList<Integer> list = new ArrayList<Integer>(1000);
                            for (int i = 0; i < Q.length; i++) list.add(i);
                            Collections.shuffle(list);
                            //remove ans in list
                            rqlocation = list.indexOf(random_Q);
                            list.remove(rqlocation);
                            //gen list 3 random + 1 ans
                            ArrayList<Integer> anslist = new ArrayList<Integer>(1000);
                            for (int i = 1; i <= 1; i++) anslist.add(list.get(i));
                            anslist.add(random_Q);
                            Collections.shuffle(anslist);
                            //set qtext = question
                            tv_question.setText(Q[random_Q]);
                            //set ansplace
                            ans_a = anslist.get(0);
                            ans_b = anslist.get(1);
                            //set anstext - ans
                            ans_left.setText(A[ans_a]);
                            ans_right.setText(A[ans_b]);
                            //set correct ans location
                            correctlocation = anslist.indexOf(random_Q);}
                            num+=1;
                        }else{
                            result.setText("Oh no!  Incorrect!\nDrag the alien again!");
                        }


                    }

                }
                return true;
            }
        });



        ufo_right.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                if (action == DragEvent.ACTION_DROP) {
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    if (view.getId() == R.id.iv_alien) {
                        if (ans_b == random_Q){
                            result.setText("correct!!");

                            if (num == Q.length) {
                                Intent i = new Intent(MultiplyGame.this, GameEnd.class);
                                startActivity(i);
                            }

                            if(num != Q.length){
                            //set question
                            rqlist.remove(0);
                            random_Q = rqlist.get(0);
                            //gen list random place
                            ArrayList<Integer> list = new ArrayList<Integer>(1000);
                            for (int i = 0; i < Q.length; i++) list.add(i);
                            Collections.shuffle(list);
                            //remove ans in list
                            rqlocation = list.indexOf(random_Q);
                            list.remove(rqlocation);
                            //gen list 3 random + 1 ans
                            ArrayList<Integer> anslist = new ArrayList<Integer>(1000);
                            for (int i = 1; i <=1; i++) anslist.add(list.get(i));
                            anslist.add(random_Q);
                            Collections.shuffle(anslist);
                            //set qtext = question
                            tv_question.setText(Q[random_Q]);
                            //set ansplace
                            ans_a = anslist.get(0);
                            ans_b = anslist.get(1);
                            //set anstext - ans
                            ans_left.setText(A[ans_a]);
                            ans_right.setText(A[ans_b]);
                            //set correct ans location
                            correctlocation = anslist.indexOf(random_Q);}
                            num+=1;
                        }else{
                            result.setText("Oh no!  Incorrect!\nDrag the alien again!");
                        }


                    }

                }
                return true;
            }
        });



        iv_alien.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDragAndDrop(null, shadowBuilder, v, 0);

                    //v.setVisibility(View.INVISIBLE);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
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
        } else if(item.getItemId() == R.id.nav_leaderboard) {
            Intent i = new Intent(getApplicationContext(), Leaderboard.class);
            startActivity(i);
        }
        drawerLayout.closeDrawer(nv);
        return true;
    }
}