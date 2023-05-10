package com.example.loginsharedpreferences.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginsharedpreferences.model.Usuario;
import com.example.loginsharedpreferences.request.ApiClient;
import com.example.loginsharedpreferences.ui.login.MainActivity;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;
    MutableLiveData<Usuario>usuario;
    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario= new MutableLiveData<>();
        }
        return usuario;
    }
    public void obtenerDatos(Intent intent){

        Usuario usr= ApiClient.leer(context);
        usuario.setValue(usr);
    }

    public void guardarRegistro(Usuario usuario) {
        Log.d("Usuario: " , usuario+"");
        ApiClient.guardar(context,usuario);
    }
}
