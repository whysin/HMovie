package com.adityadharma.hmovie;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import com.adityadharma.hmovie.API.Client;
import com.adityadharma.hmovie.API.TampilanAPI;
import com.adityadharma.hmovie.Adapter.Adapter;
import com.adityadharma.hmovie.AmbilData.Example;
import com.adityadharma.hmovie.AmbilData.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView moView;
    Adapter adapView;
    List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moView = (RecyclerView)findViewById(R.id.movieView);
        moView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

        movieLoad("top_rated");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_sort_setting, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.popular){
            movieLoad("top_rated");
        }else if (id ==R.id.setting){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void movieLoad(String value){
        TampilanAPI api = Client.getRetrofit().create(TampilanAPI.class);
        Call<Example> call= api.getRated();
//        if (value.equals("popular")){
//            call = api.getPopular();
//        }else if (value.equals("top_rated")){
//            call = api.getRated();
//        }
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example movie = response.body();
                adapView = new Adapter(results);
                adapView.setData(movie.getResults());
                moView.setAdapter(adapView);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

}
