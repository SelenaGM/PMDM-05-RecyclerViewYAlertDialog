package com.example.pmdm_05_recyclerviewyalertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.pmdm_05_recyclerviewyalertdialog.adapters.ToDoAdapter;
import com.example.pmdm_05_recyclerviewyalertdialog.modelos.ToDo;
import com.google.android.material.snackbar.Snackbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.pmdm_05_recyclerviewyalertdialog.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<ToDo> todoList;
    //toda actividad que quiera instanciar necesita un todoAdapter
    private ToDoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        todoList = new ArrayList<>();
        //creaTareas();

        //creamos le adapter
        adapter = new ToDoAdapter(todoList, R.layout.todo_view_model, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);
        //layoutManager = new LinearLayoutManager(MainActivity.this);
        //otra forma de exponerlo sería con el grid layout manager
        layoutManager = new GridLayoutManager(this,2);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //metodo para crear el desplegable que nos deje agregar elementos a la lista y enseñarlos
                createToDo().show();
            }
        });
    }

    private AlertDialog createToDo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CREATE TO DO");
        builder.setCancelable(false);

        View todoAlert = LayoutInflater.from(this).inflate(R.layout.todo_model_alert, null);
        EditText txtTitulo = todoAlert.findViewById(R.id.txtTituloToDoModelAlert);
        EditText txtContenido = todoAlert.findViewById(R.id.txtContenidoToDoModelAlert);
        builder.setView(todoAlert);

        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!txtTitulo.getText().toString().isEmpty() && !txtContenido.getText().toString().isEmpty()){
                    todoList.add(new ToDo(txtTitulo.getText().toString(),txtContenido.getText().toString()));
                    //notificamos para que refresque y dibuje el elemento
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return builder.create();
    }

    private void creaTareas() {
        for (int i = 0; i < 1000000; i++) {
            todoList.add(new ToDo("Titulo "+i, "Contenido "+i));


        }
    }


}