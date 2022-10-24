package com.example.pmdm_05_recyclerviewyalertdialog;

import android.os.Bundle;

import com.example.pmdm_05_recyclerviewyalertdialog.adapters.ToDoAdapter;
import com.example.pmdm_05_recyclerviewyalertdialog.modelos.ToDo;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


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
        creaTareas();

        //creamos le adapter
        adapter = new ToDoAdapter(todoList, R.layout.todo_view_model, MainActivity.this);
        binding.contentMain.contenedor.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void creaTareas() {
        for (int i = 0; i < 1000000; i++) {
            todoList.add(new ToDo("Titulo "+i, "Contenido "+i));


        }
    }


}