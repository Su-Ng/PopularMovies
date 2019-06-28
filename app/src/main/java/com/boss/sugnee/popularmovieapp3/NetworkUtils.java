package com.boss.sugnee.popularmovieapp3;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Su Gnee on 9/27/2018.
 */

public class NetworkUtils
{
    public static URL buildUrl2(String githubSearchQuery) {

        //queries sent to themoviedb.org api_key replaced by XXX
        Uri builtUri= Uri.parse("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=XXX");
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildUrl3(String githubSearchQuery) {
        //queries sent to themoviedb.org api_key replaced by XXX
        Uri builtUri= Uri.parse("https://api.themoviedb.org/3/movie/439079/videos?&api_key=XXX");
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
