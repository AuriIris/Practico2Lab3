package com.example.loginsharedpreferences.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginsharedpreferences.R;
import com.example.loginsharedpreferences.databinding.ActivityRegistroBinding;
import com.example.loginsharedpreferences.model.Usuario;
import com.example.loginsharedpreferences.ui.login.MainActivity;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    private RegistroViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        vm.getUsuario().observe(this, usuario -> {
            try {
            binding.editTextDni.setText(usuario.getDni() + "");
            binding.editTextNombre.setText(usuario.getNombre());
            binding.editTextApellido.setText(usuario.getApellido());
            binding.editTextEmail.setText(usuario.getEmail());
            binding.editTextPassword.setText(usuario.getPass());
            } catch (Exception e) {
                Toast.makeText(this, "Puede registrarse", Toast.LENGTH_SHORT).show();
            }
        });
        vm.obtenerDatos(getIntent());

        binding.buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long dni = Long.parseLong(binding.editTextDni.getText().toString());
                String nombre = binding.editTextNombre.getText().toString();
                String apellido = binding.editTextApellido.getText().toString();
                String email = binding.editTextEmail.getText().toString();
                String pass = binding.editTextPassword.getText().toString();

                Usuario usuario = new Usuario(dni, nombre, apellido, email, pass);
                vm.guardarRegistro(usuario);
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}