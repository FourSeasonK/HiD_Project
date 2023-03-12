package com.example.hid.dforum;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.dforum.adapter.ShareForumAdapter;
import com.example.hid.databinding.ActivityShareforumBinding;
import com.example.hid.model.Shareforum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ShareForumActivity extends NavigationActivity {

    private static final String TAG = ShareForumActivity.class.getSimpleName();
    ActivityShareforumBinding activityShareforumBinding;

    Date today;
    SimpleDateFormat dateFormat;
    String todayDate;
    ArrayList<Shareforum> sfList = new ArrayList<>();

    RecyclerView recyclerView;
    ShareForumAdapter adapter;
    Button btnUploadD;

    List<String> sfNumbers = new ArrayList<>(Arrays.asList("1", "2", "3"));
    List<String> sfTitles = new ArrayList<>(Arrays.asList("Title1", "Title2", "Title3"));
    List<String> sfNames = new ArrayList<>(Arrays.asList("Name1", "Name2", "Name3"));
    List<Integer> sfHearts = new ArrayList<>(Arrays.asList(R.drawable.heartline, R.drawable.heartfull));
    List<Integer> sfStars = new ArrayList<>(Arrays.asList(R.drawable.starline, R.drawable.startfull));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shareforum);

        activityShareforumBinding = ActivityShareforumBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_shareforum, frameLayout);

        recyclerView = rootView.findViewById(R.id.SBrecyclerview);
        btnUploadD = rootView.findViewById(R.id.btnSBUpload);

        today = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("MM/dd/yy");
        todayDate = dateFormat.format(today);

        for(int i = 0; i < sfNumbers.size(); i++){
            Shareforum shareforum = new Shareforum(sfNumbers.get(i), sfTitles.get(i), sfNames.get(i), todayDate, sfHearts.get(0), sfStars.get(0));
            sfList.add(shareforum);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new ShareForumAdapter(this, sfList);
        recyclerView.setAdapter(adapter);

        btnUploadD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShareForumActivity.this, UploadDForumActivity.class);
                startActivity(intent);
            }
        });

    }
}