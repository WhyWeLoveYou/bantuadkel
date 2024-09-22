package com.defan.freechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.defan.freechat.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listener();
        setter();
    }

    private void setter() {
        String pesannya = getIntent().getStringExtra("pesan");
        String pesanku = getIntent().getStringExtra("pesanbalas");
        if (pesannya != null) {
            binding.tvPesan1.setText(pesanku);
            binding.tvPesan2.setText(pesannya);
        }
        else {
            binding.tvBalasanmu.setVisibility(View.GONE);
            binding.tvPesan2.setVisibility(View.GONE);
            binding.tvPesan1.setVisibility(View.GONE);
        }
    }

    private void listener() {
        binding.buttonKirim.setOnClickListener(v-> {
            if(validate()) {
                signup();
            }
        });
    }

    private void signup() {
        String pesan = binding.Pesan.getText().toString();
        String balasan = getIntent().getStringExtra("pesan");
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra("pesan", pesan);
        intent.putExtra("pesanbalas", balasan);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean validate() {
        String pesan = binding.Pesan.getText().toString();
        if(pesan.isEmpty()) {
            Toast.makeText(this, "pesan perlu diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}