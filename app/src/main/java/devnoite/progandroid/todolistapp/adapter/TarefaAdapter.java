package devnoite.progandroid.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import devnoite.progandroid.todolistapp.R;
import devnoite.progandroid.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    // variável para a lista de tarefas
    private List<Tarefa> tarefas;
    // variável para o Context
    private Context context;

    // construtor que recebe os parâmetros para o Adapter
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflar a view do viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
        // retorna uma viewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obter a tarefa através da position
        Tarefa t = tarefas.get(position);
        // transportar a informação da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());
        // formata a data e exibe no textView
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
        // verifica se a tarefa está concluida
        if (t.isConcluida()){
            holder.tvStatus.setText("Finalizada");
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else {
            holder.tvStatus.setText("Aberta");
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        if (tarefas != null){
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        // variáveis para os componentes do layout
        TextView tvTitulo, tvData, tvStatus;

        public TarefaViewHolder(View view){
            super(view);
            // passar da view para os componentes
            tvTitulo = view.findViewById(R.id.tituloTarefa);
            tvData = view.findViewById(R.id.dataTarefa);
            tvStatus = view.findViewById(R.id.statusTarefa);
        }
    }
}
