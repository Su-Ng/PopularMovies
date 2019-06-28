package com.boss.sugnee.popularmovieapp3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Su Gnee on 10/2/2018.
 */


public class DisplayMovieDetail extends AppCompatActivity {

    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    ArrayList<String> mTrailerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_movie_detail_layout);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String movie_title = extras.getString("MOVIE_TITLE");
        String user_rating = extras.getString("USER_RATING");
        String release_date = extras.getString("RELEASE_DATE");
        String synopsis = extras.getString("SYNOPSIS");
        String strPosterPath = extras.getString("POSTERPATH");
        String strMovieIDs = extras.getString("ID");

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(movie_title);

        TextView tvUserRating = (TextView) findViewById(R.id.tv_user_rating);
        tvUserRating.setText("User Rating : " + user_rating);

        TextView tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvReleaseDate.setText("Release Date : " + release_date);

        TextView tvSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        tvSynopsis.setText(synopsis);



        ImageView imageView2 = (ImageView) findViewById(R.id.imgView2);

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/"+strPosterPath)
                .into(imageView2);


    }

    void searchMovieVideo() {
        String movieId = "";
        URL searchUrl = NetworkUtils.buildUrl3(movieId);
        new DisplayMovieDetail.SearchQueryTaskTrailer().execute(searchUrl);

    }

    public class SearchQueryTaskTrailer extends AsyncTask<URL, Void, ArrayList<String>>
    {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected ArrayList<String> doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (githubSearchResults != null && !githubSearchResults.equals("")) {


                String json = githubSearchResults;
                Log.d("TAG here",json);
                System.out.println("debug"+githubSearchResults);

                String trailer = JsonUtils.parseTrailerJson(json);



            }
            return mTrailerList;
        }
        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(ArrayList<String> trailer_list) {
            System.out.println("debugging");
            Toast.makeText(getApplicationContext(), "postexecute", Toast.LENGTH_LONG).show();

        }

    }

    public void getMovieTrailer(View view)
    {

        Intent intent = new Intent(this, DisplayMovieDetail.class);

        startActivity(intent);
    }
}
