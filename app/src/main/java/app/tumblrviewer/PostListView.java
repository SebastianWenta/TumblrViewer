package app.tumblrviewer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostListView extends ActionBarActivity {


    private Button searchButton;

    private EditText tumblrUser;

    public static XMLParser lastXML;

    public static int postNumber;

    static Handler handler;

    private TextView demandedPostsNumberView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list_view);

        searchButton = (Button) findViewById(R.id.searchButton);

        tumblrUser = (EditText) findViewById(R.id.userEditText);

        demandedPostsNumberView = (TextView) findViewById(R.id.demanded_posts_number);


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

            fillListView(lastXML.getShortPostList());

            loadFullPostList();

            }

        };
    }

    public void newSearch(View button){

        String user = tumblrUser.getText().toString();

        StringBuilder urlToCheck = new StringBuilder("http://");
        urlToCheck.append(user);
        urlToCheck.append(".tumblr.com/api/read");


        AsyncGET asyncGET = new AsyncGET(this, urlToCheck.toString(), handler, howManyPostsToGet());

        asyncGET.execute();

    }



    private void fillListView(ArrayList<ShortPostTumblr> shortPostList){

        ListView shortPostsListView = (ListView) findViewById(R.id.post_list);

        ListAdapter shortPostsAdapter = new ListAdapter(this, shortPostList);

        shortPostsListView.setAdapter(shortPostsAdapter);

        shortPostsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                startPostActivity(lastXML.getShortPostList().get(position).getId());

            }
        });

    }


    public void startPostActivity(String id){

        Intent intent = new Intent(this, PostView.class);

        intent.putExtra("id", id);
        intent.putExtra("user", tumblrUser.getText().toString());

        startActivity(intent);

    }

    public void loadFullPostList() {

        Thread thread = new Thread(){
            public void run(){

                lastXML.getFullPostList();

            }
        };

        thread.start();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (lastXML!=null){
            fillListView(lastXML.getShortPostList());
        }


    }


    public void lessPosts(View view){

        int currentNumber = Integer.parseInt(demandedPostsNumberView.getText().toString());

        if (currentNumber != 1){

            demandedPostsNumberView.setText(String.valueOf(currentNumber-1));

        }

    }

    public void morePosts(View view){

        int currentNumber = Integer.parseInt(demandedPostsNumberView.getText().toString());

        if (currentNumber != 50){

            demandedPostsNumberView.setText(String.valueOf(currentNumber+1));

        }

    }

    public String howManyPostsToGet(){

        return demandedPostsNumberView.getText().toString();
    }


}
