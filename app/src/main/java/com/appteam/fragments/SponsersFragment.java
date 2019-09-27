package com.appteam.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.appteam.adapters.TeamAdapter;
import com.appteam.hillfair2k19.R;
import com.appteam.hillfair2k19.model.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SponsersFragment extends Fragment {

    ProgressBar loadwall;
    private Activity activity;
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private List<Team> teamList = new ArrayList<>();

    public SponsersFragment() {
    }

    public SponsersFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsers, container, false);
        recyclerView = view.findViewById(R.id.fourthRec);
        loadwall = view.findViewById(R.id.loadwall);
        teamAdapter = new TeamAdapter(teamList, activity);
        Toast.makeText(activity,"SponserFragment",Toast.LENGTH_SHORT).show();
//        Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(teamAdapter);
        getData();
        Log.e("SponsorFragment", "onCreateView: ");
        return view;
    }

    public void getData() {
        teamList.clear();
//  teamList.add(new Team("Captaion Marvel", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "Chief"));
//  teamList.add(new Team("Thanos", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "Villan"));
//  teamList.add(new Team("Iron Mam", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "Hero"));
        loadwall.setVisibility(View.VISIBLE);
       AndroidNetworking.get(activity.getString(R.string.baseUrl) + "sponsors")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                       try {

                           Log.e("Rare",response.toString());
                            loadwall.setVisibility(View.GONE);
                            int users = response.length();
                            for (int i = 0; i < users; i++) {
                                JSONObject json = response.getJSONObject(i);
                                String sponsorName = json.getString("sponsor_name");
                                String info = json.getString("sponsor_info");
                                String sponsor_logo = json.getString("sponsor_logo");
                                String id= json.getString("id");
                                teamList.add(new Team(sponsorName, sponsor_logo,info,id));
                            }
                            teamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                   @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

        teamAdapter.notifyDataSetChanged();
    }
}


