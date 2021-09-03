package com.example.fetchrewards;

import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {


   ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("fetch Rewards");
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.list_data);

        getData();

    }

    private void getData(){
        Call<List<Results>> call = RetrofitClient.getInstance().getMyAPi().getRequestData();
        call.enqueue((new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> data = response.body();
                String [] oneList = new String[data.size()];

                for (int i = 0; i < data.size(); i++){
                    oneList[i] = String.valueOf(data.get(i).getId());
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, oneList));
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An Error has Occured", Toast.LENGTH_LONG).show();
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
        if (id == R.id.action_filter) {
            Toast.makeText(getApplicationContext(), "Filtered List", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_sort) {
            Toast.makeText(getApplicationContext(), "Sorted List", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "List by Search Value", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}