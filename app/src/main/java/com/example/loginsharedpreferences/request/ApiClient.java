package com.example.loginsharedpreferences.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.loginsharedpreferences.model.Usuario;

import java.io.*;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar (Context context) {

        if (sp == null) {
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(context.getFilesDir(), "datos.dat")));
            oos.writeObject(usuario);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Usuario leer(Context context) {
        Usuario usuario = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(context.getFilesDir(), "datos.dat")));
            usuario = (Usuario) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static Usuario login(Context context, String mail, String pass) {
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String email = sp.getString("mail", "-1");
        String passw = sp.getString("pass", "-1");

        if (email.equals(mail) && passw.equals(pass)) {
            usuario = new Usuario(dni, apellido, nombre, email, pass);
        }
        return usuario;
    }
}