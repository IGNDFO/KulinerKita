package com.example.kulinerkita.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kulinerkita.API.APIRequestData;
import com.example.kulinerkita.API.RetroServer;
import com.example.kulinerkita.Adapter.adapterKuliner;
import com.example.kulinerkita.Model.ModelKuliner;
import com.example.kulinerkita.Model.ModelResponse;
import com.example.kulinerkita.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView rvkuliner;
private FloatingActionButton fabtambah;
private ProgressBar pbkuliner;

private RecyclerView.Adapter ad_kuliner;
private RecyclerView.LayoutManager lm_kulnier;
private List<ModelKuliner> listkuliner=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvkuliner = findViewById(R.id.rv_kuliner);
        fabtambah = findViewById(R.id.fab_tambah);
        pbkuliner = findViewById(R.id.pb_kuliner);


        lm_kulnier = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvkuliner.setLayoutManager(lm_kulnier);

        fabtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,tambah.class));
            }
        });
    }




    protected void  onResume(){
        super.onResume();
        retrievekuliner();
    }

        public void retrievekuliner (){
            pbkuliner.setVisibility(View.VISIBLE);

            APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
            Call<ModelResponse> proses= ARD.ardRetrieve();
            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode =response.body().getKode();
                    String  pesan =response.body().getPesan();
                    listkuliner= response.body().getData();

                    ad_kuliner=new adapterKuliner(MainActivity.this,listkuliner);
                    rvkuliner.setAdapter(ad_kuliner);
                    ad_kuliner.notifyDataSetChanged();
                    pbkuliner.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "GAGAL MEMANGGIL SEVER", Toast.LENGTH_SHORT).show();
                    pbkuliner.setVisibility(View.GONE);

                }
            });


    }
}