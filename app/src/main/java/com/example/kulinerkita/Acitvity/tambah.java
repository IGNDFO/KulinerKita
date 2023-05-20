package com.example.kulinerkita.Acitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kulinerkita.API.APIRequestData;
import com.example.kulinerkita.API.RetroServer;
import com.example.kulinerkita.Model.ModelResponse;
import com.example.kulinerkita.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tambah extends AppCompatActivity {
private EditText etnama,etasal,etdeskripsiSingkat;
private Button btntambah;
private  String nama,asal,deskrisi_singkat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        etdeskripsiSingkat=findViewById(R.id.et_deskripsi_singkat);
        etnama=findViewById(R.id.et_nama);
        etasal=findViewById(R.id.et_asal);
        btntambah=findViewById(R.id.btn_tambah);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama=etnama.getText().toString();
                asal=etasal.getText().toString();
                deskrisi_singkat=etdeskripsiSingkat.getText().toString();
                
                if(nama.trim().isEmpty()){
                    etnama.setError("Nama Tidak Boleh Kosong");
                } else if (asal.trim().isEmpty()) {
                    etasal.setError("Asal Tidak Boleh Kosong");
                }else if (deskrisi_singkat.trim().isEmpty()) {
                    etdeskripsiSingkat.setError("Deskripsi Singkat Tidak Boleh Kosong");
                }
                else{
tambahkuliner();
                }

            }
        });

    }
    private void tambahkuliner(){
        APIRequestData ARD= new RetroServer().konekretro().create(APIRequestData.class);
        Call<ModelResponse> proses= ARD.ardcreate(nama,asal,deskrisi_singkat);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode= response.body().getKode();
                String pesan= response.body().getPesan();
                Toast.makeText(tambah.this, "Kode" + kode +"pesan"+pesan, Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(tambah.this, "GAGAL MENGHUBUNGI SERVER :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}