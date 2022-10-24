package com.example.pmdm_05_recyclerviewyalertdialog.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_05_recyclerviewyalertdialog.R;
import com.example.pmdm_05_recyclerviewyalertdialog.modelos.ToDo;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoVH> {

    //necesitaremos la lista
    private List<ToDo> objects;
    //la fila (no pasamos el objeto, sino el id, que es un entero)
    private int resource;
    //necesitamos el context para poder inflar los elementos
    private Context context;

    public ToDoAdapter(List<ToDo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    /**
     * Instanciar tantos elementos como me quepan en la pantalla
     * @param parent
     * @param viewType
     * @return
     */

    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //solo se van a inflar tantos elementos como quepan
        View toDoView = LayoutInflater.from(context).inflate(resource, null);
        //para que se expandan los views porque el recycler se los jala.
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        toDoView.setLayoutParams(lp);

        return new ToDoVH(toDoView);
    }

    /**
     * Es llamado por el adapter para modificar el contenido de un view holder ya creado
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {

        ToDo toDo = objects.get(position);
        holder.lblTitulo.setText(toDo.getTitulo());
        holder.lblContenido.setText(toDo.getContenido());
        holder.lblFecha.setText(toDo.getFecha().toString());
        //vamos a comprobar si esta marcado y dependiendo de esto le cambiaremos la imagen.
        if(toDo.isCompletado())
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_on_background);
        else
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);

        holder.btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmUpdate("SEGURO, SEGURO?", toDo).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        //importante cambiarlo porque sino pintará 0 elementos.
        return objects.size();
    }

    private AlertDialog confirmUpdate (String titulo, ToDo toDo){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setCancelable(false);

        //si le d as a no, no cambiará, si le  das a sí, cumplirá la función
        builder.setNegativeButton("NO",null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //invierte el valor de un boolean
                toDo.setCompletado(!toDo.isCompletado());
                //avisa al recyclerView para que vuelva a redibujar
                notifyDataSetChanged();
            }
        });

        return builder.create();
    }

    public class ToDoVH extends RecyclerView.ViewHolder {

        TextView lblTitulo, lblContenido, lblFecha;
        ImageButton btnCompletado;

        public ToDoVH(@NonNull View itemView) {
            super(itemView);
            lblTitulo = itemView.findViewById(R.id.lblTituloTodoViewModel);
            lblContenido= itemView.findViewById(R.id.lblContenidoTodoViewModel);
            lblFecha = itemView.findViewById(R.id.lblFechaTodoViewModel);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoTodoViewModel);
        }
    }
}
