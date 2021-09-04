package com.example.fetchrewards;

import com.example.fetchrewards.Models.Result;
import com.example.fetchrewards.Retrofit.RetrofitClient;
import com.example.fetchrewards.Utils.ListViewAdapter;
import com.example.fetchrewards.Utils.SortByListName;
import android.app.Application;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ListViewAdapter listViewAdapter;
    Button retrieveModifiedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("fetch Rewards");
        setSupportActionBar(toolbar);

//        listView = findViewById(R.id.list_data);
        expandableListView = (ExpandableListView) findViewById(R.id.expandable);

        retrieveModifiedList = (Button) findViewById(R.id.button);
        retrieveModifiedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
    }

    // Retrieve JSON Data from URL and Display
    private void getData() {
        // Utilize retrofit client to handle fetch of Data
        Call<List<Result>> call = RetrofitClient.getInstance().getMyAPi().getRequestData();
        call.enqueue((new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                // store data in list
                List<Result> data = response.body();

                // initialized array lists to hold items belonging to that listID
                ArrayList<Result> listIdOne = new ArrayList<>();
                ArrayList<Result> listIdTwo = new ArrayList<>();
                ArrayList<Result> listIdThree = new ArrayList<>();
                ArrayList<Result> listIdFour = new ArrayList<>();


                // iterate over data to store in proper arrayList filtering based on ListId
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getName() != null)
                        if (!data.get(i).getName().equals("")) {
                            switch (data.get(i).getListId()) {
                                case 1:
                                    listIdOne.add(new Result(data.get(i).getId(), data.get(i).getListId(), data.get(i).getName()));
                                    break;
                                case 2:
                                    listIdTwo.add(new Result(data.get(i).getId(), data.get(i).getListId(), data.get(i).getName()));
                                    break;
                                case 3:
                                    listIdThree.add(new Result(data.get(i).getId(), data.get(i).getListId(), data.get(i).getName()));
                                    break;
                                case 4:
                                    listIdFour.add(new Result(data.get(i).getId(), data.get(i).getListId(), data.get(i).getName()));
                                    break;
                            }
                        }
                }
                // arrayList of unique ListIDs (hardcoded for now, ideally will be addressed through filter)
                ArrayList<String> listIdGroup = new ArrayList<>();
                listIdGroup.add(String.valueOf(1));
                listIdGroup.add(String.valueOf(2));
                listIdGroup.add(String.valueOf(3));
                listIdGroup.add(String.valueOf(4));

                // sorting by listName accounting for numbers
                Collections.sort(listIdOne, new SortByListName());
                Collections.sort(listIdTwo, new SortByListName());
                Collections.sort(listIdThree, new SortByListName());
                Collections.sort(listIdFour, new SortByListName());

                final HashMap<String, List<Result>> results = new HashMap<>();

                // ArrayList added to hash map for use in custom adapter that will display expanded list view
                results.put(listIdGroup.get(0), listIdOne);
                results.put(listIdGroup.get(1), listIdTwo);
                results.put(listIdGroup.get(2), listIdThree);
                results.put(listIdGroup.get(3), listIdFour);

                listViewAdapter = new ListViewAdapter((Application) getApplicationContext(), listIdGroup, results);

                expandableListView.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An Error has Occurred", Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public void onBackPressed() {
        boolean groupsCollapsed = false;
        for (int i = 0; i < expandableListView.getCount(); i++) {
            if (expandableListView.isGroupExpanded(i)) {
                expandableListView.collapseGroup(i);
                groupsCollapsed = true;
            }
        }
        if (!groupsCollapsed) {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getData();
            Toast.makeText(getApplicationContext(), "potential settings menu", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_search) {
            getData();
            Toast.makeText(getApplicationContext(), "Potential Search List", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}