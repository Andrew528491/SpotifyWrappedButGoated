package com.example.spotifywrappedbutgoated;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotifywrappedbutgoated.ui.SongData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SongService {
    private ArrayList<SongData> songs = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    String timeRange;
    private RequestQueue queue;

    public SongService(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("SPOTIFY", 0);

        queue = Volley.newRequestQueue(context);
    }

    public ArrayList<SongData> getSongs() {
        return songs;
    }

    public void getTopTracks(final VolleyCallBack callBack, String timeSpan) {
        timeRange = timeSpan;
        String endpoint = "https://api.spotify.com/v1/me/top/tracks?time_range="+timeRange+"&limit=5";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, response -> {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.optJSONArray("items");
                    for (int n = 0; n < Objects.requireNonNull(jsonArray).length(); n++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(n);
                            String name = object.getString("name");
                            String id = object.getString("id");
                            String albumArt = object.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url");
                            String player = object.getString("preview_url");
                            SongData song = new SongData(name, albumArt, player);
                            songs.add(song);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.onSuccess();
                }, error -> {
                    displayExceptionMessage("Error", "The API call done got messed up bro.", new Activity());
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void displayExceptionMessage(String title, String msg, Activity reportActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(reportActivity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNeutralButton("I understand",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
