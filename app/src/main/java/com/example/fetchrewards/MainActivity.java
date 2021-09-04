package com.example.fetchrewards;

import com.example.fetchrewards.Models.Result;
import com.example.fetchrewards.Retrofit.RetrofitClient;
import com.example.fetchrewards.Utils.ResultAdapter;
import com.example.fetchrewards.Utils.SortByListId;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {


   ListView listView;
   Button retrieveModifiedList;
   View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("fetch Rewards");
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.list_data);


        retrieveModifiedList = (Button) findViewById(R.id.button);
        retrieveModifiedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
    }

    private void getData(){
        Call<List<Result>> call = RetrofitClient.getInstance().getMyAPi().getRequestData();
        call.enqueue((new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {

                List<Result> data = response.body();
                ArrayList<Result> results = new ArrayList<>();

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getName() != null)
                        if (!data.get(i).getName().equals("")){
                        results.add(new Result(data.get(i).getId(), data.get(i).getItemId(), data.get(i).getName()));
                    }
                }
                Collections.sort(results, new SortByListId().thenComparing(new SortByListName()));
                ResultAdapter adapter = new ResultAdapter((Application) getApplicationContext(), results);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An Error has Occurred", Toast.LENGTH_LONG).show();
            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getData();
            Toast.makeText(getApplicationContext(), "potential settings menu", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_search) {
            getData();
            Toast.makeText(getApplicationContext(), "Potential Search List", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}