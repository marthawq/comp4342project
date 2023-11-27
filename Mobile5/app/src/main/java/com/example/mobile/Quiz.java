
package com.example.mobile;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    TextView tv_question_num, tv_question, tv_A, tv_B, tv_C, tv_D, tv_correct,tv_score;
    Button btn_tohome, btn_next;
    int num = 1;
    public static int mark=0;
    int random_Q, rqlocation,correctlocation,ans_a, ans_b, ans_c, ans_d;
    boolean answered=false;
    LinearLayout layout_A, layout_B, layout_C, layout_D;
    public static String[] Q;
    public static String[] A;

    static MediaPlayer bgm_player;
    boolean bgm_status = Setting.getBgm_status();
    boolean sfx_status = Setting.getEffect_status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);

        tv_question_num = findViewById(R.id.tv_question_num);
        tv_question = findViewById(R.id.tv_question);
        tv_A = findViewById(R.id.tv_A);
        tv_B = findViewById(R.id.tv_B);
        tv_C = findViewById(R.id.tv_C);
        tv_D = findViewById(R.id.tv_D);
        tv_correct = findViewById(R.id.tv_correct);
        tv_score=findViewById(R.id.tv_score);

        tv_score.setText(mark+"/10");

        btn_tohome = findViewById(R.id.btn_tohome);
        btn_next = findViewById(R.id.btn_next);
        layout_A = findViewById(R.id.layout_A);
        layout_B = findViewById(R.id.layout_B);
        layout_C = findViewById(R.id.layout_C);
        layout_D = findViewById(R.id.layout_D);

        mark=0;
        if(answered==false){
            btn_next.setVisibility(INVISIBLE);
        }

        //save questions and answers in xml into string
        Q = getResources().getStringArray(R.array.questions);
        A = getResources().getStringArray(R.array.answers);
        ArrayList<Integer> rqlist = new ArrayList<Integer>(1000);

        //set question
        for (int i=0; i< Q.length; i++) rqlist.add(i);
        Collections.shuffle(rqlist);
        random_Q = rqlist.get(0);

        //shuffle the question list as random
        ArrayList<Integer> list = new ArrayList<Integer>(1000);
        for (int i=0; i< Q.length; i++) list.add(i);
        Collections.shuffle(list);

        //
        rqlocation = list.indexOf(random_Q);
        list.remove(rqlocation);

        //
        ArrayList<Integer> anslist = new ArrayList<Integer>(1000);
        for (int i=1; i<=3; i++) anslist.add(list.get(i));
        anslist.add(random_Q);
        Collections.shuffle(anslist);
        //
        tv_question_num.setText("Question "+String.valueOf(num));
        tv_question.setText(Q[random_Q]);

        //set ansplace
        ans_a = anslist.get(0);
        ans_b = anslist.get(1);
        ans_c = anslist.get(2);
        ans_d = anslist.get(3);

        tv_A.setText(A[ans_a]);
        tv_B.setText(A[ans_b]);
        tv_C.setText(A[ans_c]);
        tv_D.setText(A[ans_d]);

        correctlocation = anslist.indexOf(random_Q);

        layout_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans_a == random_Q) {
                    tv_correct.setText("Correct! 1 mark is added. Please click NEXT to the next question!");
                    answered = true;

                    btn_next.setVisibility(VISIBLE);
                    layout_A.setBackgroundColor(getResources().getColor(R.color.green));
                    mark += 1;
                    tv_score.setText(mark + "/10");
                    layout_B.setClickable(false);
                    layout_C.setClickable(false);
                    layout_D.setClickable(false);

                    } else {
                        tv_correct.setText("INCORRECT! No mark is added. Please click NEXT to the next question!");
                        layout_A.setBackgroundColor(getResources().getColor(R.color.pink));
                        answered = true;
                        btn_next.setVisibility(VISIBLE);
                        layout_B.setClickable(false);
                        layout_C.setClickable(false);
                        layout_D.setClickable(false);
                        //correct=false;
                        tv_score.setText(mark + "/10");

                        }
                    }
        });

        layout_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans_b == random_Q) {
                    tv_correct.setText("Correct! 1 mark is added. Please click NEXT to the next question!");
                    answered = true;

                    btn_next.setVisibility(VISIBLE);
                    layout_B.setBackgroundColor(getResources().getColor(R.color.green));
                    mark += 1;
                    tv_score.setText(mark + "/10");
                    layout_A.setClickable(false);
                    layout_C.setClickable(false);
                    layout_D.setClickable(false);

                    } else {
                    tv_correct.setText("INCORRECT! No mark is added. Please click NEXT to the next question!");
                    layout_B.setBackgroundColor(getResources().getColor(R.color.pink));
                    answered = true;
                    btn_next.setVisibility(VISIBLE);

                    tv_score.setText(mark + "/10");
                    layout_A.setClickable(false);
                    layout_C.setClickable(false);
                    layout_D.setClickable(false);}

                    }
        });

        layout_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans_c == random_Q) {
                    tv_correct.setText("Correct! 1 mark is added. Please click NEXT to the next question!");
                    answered = true;

                    btn_next.setVisibility(VISIBLE);
                    layout_C.setBackgroundColor(getResources().getColor(R.color.green));
                    mark += 1;
                    tv_score.setText(mark + "/10");
                    layout_B.setClickable(false);
                    layout_A.setClickable(false);
                    layout_D.setClickable(false);

                    } else {
                    tv_correct.setText("INCORRECT! No mark is added. Please click NEXT to the next question!");
                    layout_C.setBackgroundColor(getResources().getColor(R.color.pink));
                    answered = true;

                    btn_next.setVisibility(VISIBLE);
                    tv_score.setText(mark + "/10");
                    layout_B.setClickable(false);
                    layout_A.setClickable(false);
                    layout_D.setClickable(false);

                }
                    }
        });

        layout_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans_d == random_Q) {
                    tv_correct.setText("Correct! 1 mark is added. Please click NEXT to the next question!");
                    answered = true;

                    btn_next.setVisibility(VISIBLE);
                    layout_D.setBackgroundColor(getResources().getColor(R.color.green));
                    mark += 1;
                    tv_score.setText(mark + "/10");
                    layout_B.setClickable(false);
                    layout_C.setClickable(false);
                    layout_A.setClickable(false);

                } else {
                    tv_correct.setText("INCORRECT! No mark is added. Please click NEXT to the next question!");
                    layout_D.setBackgroundColor(getResources().getColor(R.color.pink));
                    btn_next.setVisibility(VISIBLE);
                    answered = true;
                    tv_score.setText(mark + "/10");
                    layout_B.setClickable(false);
                    layout_C.setClickable(false);
                    layout_A.setClickable(false);

                }
            }
        });

        btn_tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark=0;
                Intent i = new Intent(Quiz.this, Home.class);
                startActivity(i);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (num == Q.length) {
                    answered=false;
                    Intent i = new Intent(Quiz.this, QuizResult.class);
                    startActivity(i);
                }
                if(answered==true&&num != Q.length){
                    //init loop
                        answered = false;
                        btn_next.setVisibility(INVISIBLE);
                        layout_A.setVisibility(View.VISIBLE);
                        layout_B.setVisibility(View.VISIBLE);
                        layout_C.setVisibility(View.VISIBLE);
                        layout_D.setVisibility(View.VISIBLE);
                        layout_A.setBackgroundColor(getResources().getColor(R.color.orange));
                        layout_B.setBackgroundColor(getResources().getColor(R.color.orange));
                        layout_C.setBackgroundColor(getResources().getColor(R.color.orange));
                        layout_D.setBackgroundColor(getResources().getColor(R.color.orange));
                        layout_A.setClickable(true);
                        layout_B.setClickable(true);
                        layout_C.setClickable(true);
                        layout_D.setClickable(true);
                        tv_correct.setText("");
                        num += 1;
                    if (num == Q.length) {
                        btn_next.setText("Result");}

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
                        for (int i = 1; i <= 3; i++) anslist.add(list.get(i));
                        anslist.add(random_Q);
                        Collections.shuffle(anslist);
                        //set qtext = question
                        tv_question_num.setText("Question " + String.valueOf(num));
                        tv_question.setText(Q[random_Q]);
                        //set ansplace
                        ans_a = anslist.get(0);
                        ans_b = anslist.get(1);
                        ans_c = anslist.get(2);
                        ans_d = anslist.get(3);
                        //set anstext - ans
                        tv_A.setText(A[ans_a]);
                        tv_B.setText(A[ans_b]);
                        tv_C.setText(A[ans_c]);
                        tv_D.setText(A[ans_d]);
                        //set correct ans location
                        correctlocation = anslist.indexOf(random_Q);
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
    public boolean onKeyDown(int keyCode, KeyEvent e){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
            builder.setMessage("You will lose the progress");
            builder.setTitle("Do you want to back?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Quiz.this, Home.class);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        }else{
            return super.onKeyDown(keyCode,e);
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


