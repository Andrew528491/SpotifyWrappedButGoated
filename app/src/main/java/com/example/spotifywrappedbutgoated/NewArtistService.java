package com.example.spotifywrappedbutgoated;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotifywrappedbutgoated.ui.ArtistAdapter;
import com.example.spotifywrappedbutgoated.ui.ArtistData;
import com.example.spotifywrappedbutgoated.ui.WrappedFilter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewArtistService {

    private ArrayList<ArtistData> newArtists = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private RequestQueue queue;
    private ArtistService artistService;
    private ArrayList<ArtistData> artistList;
    ListView listView;

    public NewArtistService(Context context) {
        sharedPreferences = context.getSharedPreferences("SPOTIFY", 0);
        queue = Volley.newRequestQueue(context);
        artistService = new ArtistService(context);
    }

    public ArrayList<ArtistData> getArtists() {
        return newArtists;
    }

    public void getNewArtists(final VolleyCallBack callBack) {
        artistService.getTopArtists(() -> {
            artistList = artistService.getArtists();
            System.out.println(artistList);
            String artistURIs="";
            int count = 0;
            for (int i = 0; i < artistList.size(); i++) {
                String tempURI = artistList.get(i).getArtistURI().substring(15);
                artistURIs += tempURI;
                System.out.println(tempURI);
                if (i != artistList.size() - 1) {
                    artistURIs += "%2C";
                }
            }
            String endpoint = "https://api.spotify.com/v1/recommendations?limit=5&seed_artists="+artistURIs;
            System.out.println(endpoint);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, endpoint, null, response -> {
                        JSONArray jsonArray = response.optJSONArray("tracks");
                        for (int n = 0; n < Objects.requireNonNull(jsonArray).length(); n++) {
                            try {
                                JSONObject object = jsonArray.getJSONObject(n);
                                JSONObject artist = object.getJSONArray("artists").getJSONObject(0);
                                System.out.println(artist);
                                String name = artist.getString("name");
                                //String artistPic = artist.getJSONArray("images").getJSONObject(0).getString("url");
                                String artistPic = "fuck";
                                String artistURI = artist.getString("uri");
                                ArtistData newArtist = new ArtistData(name, artistPic, artistURI);
                                System.out.println(newArtist.getArtist());
                                newArtists.add(newArtist);
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
        }, WrappedFilter.getTimespan());
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
