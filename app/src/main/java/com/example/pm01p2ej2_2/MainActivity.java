package com.example.pm01p2ej2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pm01p2ej2_2.Model.JsonPlaceHolderAPI;
import com.example.pm01p2ej2_2.Model.Posts;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView Txtlista;
    Button btnpost1,btnpost2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Txtlista = findViewById(R.id.Txtlista);
        btnpost1 = findViewById(R.id.btnposts);
        btnpost2 = findViewById(R.id.btnpostsind);

        btnpost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Txtlista.setText("");
                getPosts();
            }
        });
        btnpost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Txtlista.setText("");
                getPostsIndividual();
            }
        });

    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Posts>> call = jsonPlaceHolderAPI.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()){
                    Txtlista.setText("Codigo: "+response.code());
                }

                List<Posts> postsList = response.body();

                for (Posts post: postsList){
                    String content = "";
                    content += "userId:" + post.getUserId() +"\n";
                    content += "id:" + post.getId() +"\n";
                    content += "title:" + post.getTitle() +"\n";
                    content += "body:" + post.getBody() +"\n\n";
                    Txtlista.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Txtlista.setText(t.getMessage());
            }
        });
    }

    private void getPostsIndividual(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<Posts> call = jsonPlaceHolderAPI.getPosts1();

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                String content = "";
                content += "userId:" + response.body().getUserId() +"\n";
                content += "id:" + response.body().getId() +"\n";
                content += "title:" + response.body().getTitle() +"\n";
                content += "body:" + response.body().getBody() +"\n";
                Txtlista.append(content);

            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Txtlista.setText(t.getMessage());
            }
        });
    }

}


