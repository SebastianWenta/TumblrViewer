package app.tumblrviewer;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class PostView extends ActionBarActivity {


    private TextView userTextView;
    private TextView currentPost;
    private TextView allPostsNumber;
    private ListView onePostListView;



    private int currentPostPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        Intent intent = getIntent();

        onePostListView = (ListView) findViewById(R.id.one_post_list);

        currentPost = (TextView) findViewById(R.id.currentPost);

        allPostsNumber = (TextView) findViewById(R.id.allPostsNumber);

        userTextView = (TextView) findViewById(R.id.post_view_user);

        userTextView.setText(intent.getStringExtra("user"));



        String postID = intent.getStringExtra("id");

        for (Post post : PostListView.lastXML.getFullPostList()) {

            if (post.id.equals(postID)) {

                currentPostPosition = PostListView.lastXML.getFullPostList().indexOf(post);

                displayPost(post.getDataToDisplay());

            }

        }

        currentPost.setText(String.valueOf(currentPostPosition+1));

        allPostsNumber.setText(String.valueOf(PostListView.lastXML.getFullPostList().size()));

        onePostListView.setOnTouchListener(new OnSwipeTouchListener(PostView.this) {

            public void onSwipeRight() {
                previousPost();
            }
            public void onSwipeLeft() {
                nextPost();
            }
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

    public void displayPost(ArrayList<ShortObjectTumblr> dataToDisplay){

        ListView postView = (ListView) findViewById(R.id.one_post_list);

        PostAdapter postAdapter = new PostAdapter(this, dataToDisplay);

        postView.setAdapter(postAdapter);


    }

    public void nextPostByArrow(View view){
        nextPost();
    }



    public void previousPostByArrow(View view){
        previousPost();

    }

    public void nextPost(){

        if (currentPostPosition != (PostListView.postNumber-1)) {

            ListView postView = (ListView) findViewById(R.id.one_post_list);

            PostAdapter postAdapter = new PostAdapter(this, PostListView.lastXML.getFullPostList().get(currentPostPosition + 1).getDataToDisplay());

            currentPostPosition++;

            currentPost.setText(String.valueOf(currentPostPosition+1));

            postView.setAdapter(postAdapter);

        }

    }

    public void previousPost(){

        if (currentPostPosition > 0) {

            ListView postView = (ListView) findViewById(R.id.one_post_list);

            PostAdapter postAdapter = new PostAdapter(this, PostListView.lastXML.getFullPostList().get(currentPostPosition - 1).getDataToDisplay());

            currentPostPosition--;

            currentPost.setText(String.valueOf(currentPostPosition+1));

            postView.setAdapter(postAdapter);

        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        ListView postView = (ListView) findViewById(R.id.one_post_list);

        PostAdapter postAdapter = new PostAdapter(this, PostListView.lastXML.getFullPostList().get(currentPostPosition).getDataToDisplay());

        currentPost.setText(String.valueOf(currentPostPosition+1));

        postView.setAdapter(postAdapter);


    }

    public void getMeToPostOnTumblr(View view){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PostListView.lastXML.getShortPostList().get(currentPostPosition).getUrl()));
        startActivity(browserIntent);

    }
}