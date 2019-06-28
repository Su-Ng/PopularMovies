package com.boss.sugnee.popularmovieapp3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Su Gnee on 9/26/2018.
 */

public class MovieAdapter extends BaseAdapter{
    private final Context mContext;

    private  ArrayList<Movies> movie_list;



   public MovieAdapter(Context context, ArrayList<Movies> list)
    {
        this.mContext=context;
        this.movie_list=list;
    }


    // 2
    @Override
    public int getCount() {
            return movie_list.size();
       // return movies.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
            return 0;
            }

    // 4
    @Override
    public Object getItem(int position) {
            return null;
            }

    // 5

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(600, 600));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500/" + movie_list.get(position).getPosterPath()).into(imageView);
        } else {
            imageView = (ImageView) convertView;
        }
        return imageView;
    }

  

    public  void updateMovies(ArrayList<Movies> movie_list) {
            this.movie_list=movie_list;
            
    }

}





