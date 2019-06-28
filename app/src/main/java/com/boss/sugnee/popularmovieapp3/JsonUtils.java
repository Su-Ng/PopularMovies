package com.boss.sugnee.popularmovieapp3;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Su Gnee on 9/27/2018.
 */

public class JsonUtils {

    public static String parseTrailerJson(String json)
    {
        try{
            JSONObject jsonObj=new JSONObject(json);
            JSONArray resultsArray=jsonObj.getJSONArray("results");
            ArrayList<String> ID=new ArrayList<String>();
            for(int i=0;i<resultsArray.length();i++) {
                ID.add(resultsArray.getJSONObject(i).getString("key"));
            }
            System.out.println("Trailer key"+ID.get(0));
            return ID.get(0);
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }


    }

    public static Movies parseMovieJson(String json)
    {
        try{
            JSONObject jsonObj=new JSONObject(json);
            String id=jsonObj.getString("id");
            String poster_path=jsonObj.getString("poster_path");
            String movie_title=jsonObj.getString("original_title");
            String plot_synopsis=jsonObj.getString("overview");
            String user_rating=jsonObj.getString("vote_average");
            String release_date=jsonObj.getString("release_date");


            Log.d("TAG here",movie_title);
            Log.d("TAG here",plot_synopsis);
            Log.d("TAG here",user_rating);
            Log.d("TAG here",release_date);



            Movies movie =new Movies(id,poster_path,movie_title,plot_synopsis,user_rating,release_date);
            return movie;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Movies> parsePopularMovieJson(String json)
    {
        try{
            JSONObject jsonObj=new JSONObject(json);

            int total=jsonObj.getInt("total_results");
            String strInt=Integer.toString(total);
            JSONArray resultsArray=jsonObj.getJSONArray("results");


            ArrayList<Integer> ID=new ArrayList<Integer>();
            Movies movies[]=new Movies[resultsArray.length()];

            ArrayList<Movies> moviesArray=new ArrayList<Movies>();
            //  String strInt="2";


            for(int i=0;i<resultsArray.length();i++) {
                ID.add(resultsArray.getJSONObject(i).getInt("id"));
                movies[i]=new Movies(resultsArray.getJSONObject(i).getString("id"), resultsArray.getJSONObject(i).getString("poster_path"),
                        resultsArray.getJSONObject(i).getString("original_title"),
                        resultsArray.getJSONObject(i).getString("overview"),
                        resultsArray.getJSONObject(i).getString("vote_average"),
                        resultsArray.getJSONObject(i).getString("release_date"));
                Movies m=new Movies(resultsArray.getJSONObject(i).getString("id"),resultsArray.getJSONObject(i).getString("poster_path"),
                        resultsArray.getJSONObject(i).getString("original_title"),
                        resultsArray.getJSONObject(i).getString("overview"),
                        resultsArray.getJSONObject(i).getString("vote_average"),
                        resultsArray.getJSONObject(i).getString("release_date"));
                moviesArray.add(m);
            }
            for(int i=0;i<resultsArray.length();i++) {
                strInt = strInt+" , " + Integer.toString(ID.get(i));
            }


            return moviesArray;


        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }
}
