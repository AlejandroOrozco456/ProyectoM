package com.example.proyectom;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class Nuevo_Mueble extends Fragment {


    EditText edtId, edtNombre;
    Button  btnGuardar;
    RequestQueue requestQueue;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.nuevo__mueble_fragment, container, false);
        CargarControles(root);
        CargarEventos();
        return root;

    }

    private void CargarEventos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarImagen("http://192.168.0.22/ControlProyectoBD/insertar_mueble.php");
            }
        });
    }

    private void GuardarImagen(String URL) {

            if(!edtId.getText().toString().isEmpty() &&
                    !edtNombre.getText().toString().isEmpty()){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();
                        Limpiar();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros=new HashMap<String, String>();
                        parametros.put("id_Mueble",edtId.getText().toString());
                        parametros.put("Nombre",edtNombre.getText().toString());
                        return parametros;
                    }
                };
                requestQueue= Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);

            }
        }

    private void Limpiar() {
        edtId.setText("");
        edtNombre.setText("");
    }


    private void CargarControles(View root) {
        edtId = root.findViewById(R.id.nu_edt_Id);
        edtNombre = root.findViewById(R.id.nu_edt_Nombre);
        btnGuardar = root.findViewById(R.id.nu_btn_Guardar);
    }


}