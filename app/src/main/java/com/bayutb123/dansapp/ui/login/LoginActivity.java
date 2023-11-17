package com.bayutb123.dansapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bayutb123.dansapp.R;
import com.bayutb123.dansapp.data.local.SharedPreference;
import com.bayutb123.dansapp.databinding.ActivityLoginBinding;
import com.bayutb123.dansapp.ui.home.MainActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(view -> login(
                Objects.requireNonNull(binding.username.getText()).toString(),
                Objects.requireNonNull(binding.password.getText()).toString()
        ));
    }

    private void login(String username, String password) {
        sharedPreference = new SharedPreference(this);
        if (username.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(this, MainActivity.class);
            sharedPreference.saveUser(username, password);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this,
                    "Masukkan 'admin' sebagai username \ndan password", Toast.LENGTH_SHORT).show();
        }
    }
}