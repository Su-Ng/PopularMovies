package com.boss.sugnee.popularmovieapp3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String idsPopularMovies;
    ArrayList<Movies> arrayListMovies;

    ArrayList<Movies> mPopularList;
    ArrayList<Movies> mTopTopRatedList;
    ArrayList<String> mTrailerList;

    private MovieAdapter movieAdapter;
    private Movies movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchPopularMovies();





       GridView gridView = (GridView)findViewById(R.id.gridview);
        movieAdapter = new MovieAdapter(this,new ArrayList<Movies>());
        gridView.setAdapter(movieAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                movie = mPopularList.get(position);
                getMovieDetail(view);
               // searchMovieVideo();
            }
        });


    }

    //Make API call to retrieve JSON list Movie ids sorted by popularity stored in a string as
    //idsPopularMovies
    void searchPopularMovies()
    {
        String query="";
        URL searchUrl=NetworkUtils.buildUrl2(query);

        new SearchQueryTask().execute(searchUrl);
    }

    //Make API call to retrieve arraylist of trailer youtube address store in a string
    void searchMovieVideo() {
        String movieId = "";
        URL searchUrl = NetworkUtils.buildUrl3(movieId);
        new SearchQueryTaskTrailer().execute(searchUrl);

    }

    public void getMovieDetail(View view)
    {

        Bundle extras= new Bundle();

        extras.putString("MOVIE_TITLE",movie.getOriginalTitle());
        extras.putString("USER_RATING",movie.getVoteAverage());
        extras.putString("RELEASE_DATE",movie.getReleaseDate());
        extras.putString("SYNOPSIS",movie.getOverview());
        extras.putString("POSTERPATH",movie.getPosterPath());
        extras.putString("ID",movie.getId());

        Intent intent = new Intent(this, DisplayMovieDetail.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    public class SearchQueryTask extends AsyncTask<URL, Void, ArrayList<Movies>>
    {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected ArrayList<Movies> doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                //   mSearchResults.setText(githubSearchResults);

                String json = githubSearchResults;

                mPopularList = JsonUtils.parsePopularMovieJson(json);
                mTopTopRatedList = JsonUtils.parsePopularMovieJson(json);
                Log.d("onPostExecute: ", mPopularList.get(0).getOriginalTitle());




            }
            return mPopularList;
        }
    // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(ArrayList<Movies> movie_list) {

            movieAdapter.updateMovies(movie_list);

            // This tells the GridView to redraw itself
            // in turn calling your movieAdapter's getView method again for each cell
            movieAdapter.notifyDataSetChanged();
        }

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
                Toast.makeText(getApplicationContext(), "cliecked here", Toast.LENGTH_LONG).show();

            }
            return mTrailerList;
        }
        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(ArrayList<String> trailer_list) {


           // movieAdapter.updateMovies(movie_list);

            // This tells the GridView to redraw itself
            // in turn calling your movieAdapter's getView method again for each cell
           //movieAdapter.notifyDataSetChanged();
        }

    }
}



