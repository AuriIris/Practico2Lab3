package com.example.loginsharedpreferences.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.loginsharedpreferences.model.Usuario;
import com.example.loginsharedpreferences.request.ApiClient;
import com.example.loginsharedpreferences.ui.registro.RegistroActivity;

public class MainViewModel extends AndroidViewModel {
    private Context context;
    public MainViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }
    public void login(String mail, String pass) {

        Usuario usuario = ApiClient.login(context, mail, pass);
        if (usuario == null) {
            Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context, RegistroActivity.class);
        intent .putExtra("logueado", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void registro() {
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.putExtra("logueado", false);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}