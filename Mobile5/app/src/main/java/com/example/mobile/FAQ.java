package com.example.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
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
import java.util.HashMap;
import java.util.List;

//TODO make scrollview usable
public class FAQ extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button aboutus_btn,view_btn,postQuestion,viewReply;
    NavigationView nv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public ExpandableListView discussionBoard;
    ExpandableListViewAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);

        discussionBoard = findViewById(R.id.discussionBoard);
        postQuestion = findViewById(R.id.postQuestion);


        drawerLayout = findViewById(R.id.my_drawer_layout);
        //view_btn = findViewById(R.id.viewReplyBtn);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);

        RequestQueue VolleyQueue = Volley.newRequestQueue(FAQ.this);
        obtainTopics(VolleyQueue);

        postQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTopic(VolleyQueue);
            }
        });

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

    private void obtainTopics(RequestQueue VolleyQueue){
        String url = "http://114.132.199.139:8080/get/topics";
        JsonObjectRequest obtainTopicsReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = "-1";
                        JSONObject data = null;
                        JSONArray retrievedTopics = null;
                        try {
                            status = response.getString("code");

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                        }
                        if(status.equals("0")){
                            try {
                                data = response.getJSONObject("data");
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                            }
                            if(data != null){
                                try {
                                    retrievedTopics = data.getJSONArray("TopicUser");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                List<HashMap<String, Object>> topics = new ArrayList<>();
                                ArrayList<ArrayList<String>> posts = new ArrayList<>();
                                for(int i = 0; i < retrievedTopics.length(); i++){
                                    JSONObject topic = null;
                                    try {
                                        topic = retrievedTopics.getJSONObject(i);
                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                                    }
                                    if(topic != null){
                                        try {
                                            String topicId = topic.getString("topic_id");
                                            String usernameRetrieved = topic.getString("username");
                                            String titleRetrieved = topic.getString("title");
                                            String contentRetrieved = topic.getString("content");
                                            Button viewReply = new Button(FAQ.this);
//                                            SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
//                                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                                            editor.putString("userId",topicId);
//                                            editor.commit();
                                            //viewReply.setText(R.string.buttonText);
                                            HashMap<String, Object> topicBlock = new HashMap<>();
                                            topicBlock.put("author",usernameRetrieved);
                                            topicBlock.put("title",titleRetrieved);
                                            topicBlock.put("content",contentRetrieved);
                                            topicBlock.put("postButton",viewReply);
                                            topics.add(topicBlock);
                                            posts.add(obtainPostLists(VolleyQueue,topicId));
                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                                        }
                                        //setTextView(topic,VolleyQueue);
                                        expandableListAdapter = new ExpandableListViewAdapter(topics,posts, FAQ.this);
                                        discussionBoard.setAdapter(expandableListAdapter);
                                        discussionBoard.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                                            @Override
                                            public void onGroupExpand(int i) {

                                            }
                                        });
                                        discussionBoard.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                                            @Override
                                            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long id) {
                                                addPost(VolleyQueue,String.valueOf(groupPosition + 1));
                                                return false;
                                            }
                                        });
                                    }
                                }
                            }
                        }
                        else if(status.equals("-1")){
                            Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();
                    }
                });
        VolleyQueue.add(obtainTopicsReq);
    }

    private void addTopic(RequestQueue VolleyQueue){

        AlertDialog.Builder addTopic = new AlertDialog.Builder(FAQ.this);
        final View dialogView = LayoutInflater.from(FAQ.this)
                .inflate(R.layout.dialog,null);
        addTopic.setView(dialogView);
        addTopic.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText titleEdit = dialogView.findViewById(R.id.topic_title);
                String title = titleEdit.getText().toString();
                EditText contentEdit = dialogView.findViewById(R.id.topic_content);
                String content = contentEdit.getText().toString();
                if(title.length() == 0 || content.length() == 0){
                    Toast.makeText(getApplicationContext(),"title or content is empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    sendTopic(VolleyQueue,title,content);
                }
            }
        });
        addTopic.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        addTopic.show();
    }

    private void addPost(RequestQueue VolleyQueue, String topicId){
        AlertDialog.Builder addTopic = new AlertDialog.Builder(FAQ.this);
        final View dialogView = LayoutInflater.from(FAQ.this)
                .inflate(R.layout.reply_dialog,null);
        addTopic.setView(dialogView);
        addTopic.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText replyEdit = dialogView.findViewById(R.id.input_reply);
                String reply = replyEdit.getText().toString();
                if(reply.length() == 0){
                    Toast.makeText(getApplicationContext(),"title or content is empty",Toast.LENGTH_SHORT).show();
                }
                sendReply(VolleyQueue,reply,topicId);
            }
        });
        addTopic.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        addTopic.show();
    }


    private ArrayList<String> obtainPostLists(RequestQueue VolleyQueue, String topicId){
        ArrayList<String> obtainedPosts = new ArrayList<>();
        String url = "http://114.132.199.139:8080/get/post/" + topicId;
        JsonObjectRequest obtainPostsList = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String status = "-1";
                JSONObject data = null;
                JSONArray postList = null;

                try {
                    status = response.getString("code");
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                }
                if(status.equals("-1")){
                    Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                }
                else if(status.equals("0")){
                    try {
                        data = response.getJSONObject("data");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                    }
                }
                if(data != null){
                    try {
                        postList = data.getJSONArray("Post");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                    }
                    if(postList != null){
                        for(int i = 0; i < postList.length(); i++){
                            try {
                                JSONObject post = postList.getJSONObject(i);
                                String replyTitleRetrieved = post.getString("content");
                                obtainedPosts.add("reply: " + replyTitleRetrieved);
                                //discussionBoard.addView(oneReply);
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyQueue.add(obtainPostsList);
        return obtainedPosts;
    }

    private void sendTopic(RequestQueue VolleyQueue, String title, String content){
        title = title.trim();
        content = content.trim();
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("id","");
        title = title.replace(" ","%20");
        content = content.replace(" ","%20");
        String url = "http://114.132.199.139:8080/topic/do?" + "uid=" + userId + "&title=" + title + "&content=" + content;
        JsonObjectRequest postTopicRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = "-1";
                        String msg = "null";
                        try {
                            status = response.getString("code");
                            msg = response.getString("msg");
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"response",Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                        //discussionBoard.removeAllViews();
                        obtainTopics(VolleyQueue);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();
                    }
                });
        VolleyQueue.add(postTopicRequest);
    }

    private void sendReply(RequestQueue VolleyQueue, String reply, String topicId){
        reply = reply.trim();
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("id","");
        reply = reply.replace(" ","%20");
        String url = "http://114.132.199.139:8080/post/do?uid=" + userId + "&content=" + reply + "&topic_id=" + topicId;
        JsonObjectRequest postReplyRequest = new JsonObjectRequest(Request.Method.POST, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = "-1";
                        String msg = "null";
                        try {
                            status = response.getString("code");
                            msg = response.getString("msg");
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"response error",Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                        obtainTopics(VolleyQueue);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        VolleyQueue.add(postReplyRequest);
    }

}