package devnoite.progandroid.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import devnoite.progandroid.todolistapp.R;
import devnoite.progandroid.todolistapp.adapter.TarefaAdapter;
import devnoite.progandroid.todolistapp.database.AppDatabase;
import devnoite.progandroid.todolistapp.databinding.FragmentPrincipalBinding;
import devnoite.progandroid.todolistapp.model.Tarefa;


public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;
    // variável apara a lista
    private List<Tarefa> tarefas;
    // variável para o adapter
    private TarefaAdapter adapter;
    // variável para a database
    private AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);

        binding.btNovaTarefa.setOnClickListener(v ->{
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });
        // retorna a View raiz (root) do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>>{
        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            return null;
        }
    }
}