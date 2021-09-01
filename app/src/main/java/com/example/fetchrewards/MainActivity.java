package com.example.fetchrewards;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("fetch Rewards");
        setSupportActionBar(toolbar);
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