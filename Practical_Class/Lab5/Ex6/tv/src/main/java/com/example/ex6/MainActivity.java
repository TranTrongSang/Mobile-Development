package com.example.ex6;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.app.RowsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // replace with your TMDB API key
    private static final String API_KEY = "https://developers.themoviedb.org/3";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMDBApiService apiService = retrofit.create(TMDBApiService.class);

        Call<MovieResponse> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getMovies();
                    displayMovies(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("MainActivity", "Error fetching movies", t);
            }
        });
    }

    private void displayMovies(List<Movie> movies) {
        RowsFragment rowsFragment = new RowsFragment();
        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        HeaderItem header = new HeaderItem(0, "Popular Movies");

        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());
        for (Movie movie : movies) {
            listRowAdapter.add(movie);
        }

        rowsAdapter.add(new ListRow(header, listRowAdapter));
        rowsFragment.setAdapter(rowsAdapter);

        getFragmentManager().beginTransaction().replace(R.id.main_browse_fragment, rowsFragment).commit();
    }

    private class CardPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            ImageCardView cardView = new ImageCardView(parent.getContext());
            return new ViewHolder(cardView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            Movie movie = (Movie) item;
            ImageCardView cardView = (ImageCardView) viewHolder.view;

            cardView.setTitleText(movie.getTitle());
            Glide.with(MainActivity.this)
                    .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                    .into(cardView.getMainImageView());
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
            // Clean up if needed
        }
    }
}
