package devnoite.progandroid.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devnoite.progandroid.todolistapp.R;
import devnoite.progandroid.todolistapp.databinding.FragmentCadTarefaBinding;
import devnoite.progandroid.todolistapp.databinding.FragmentDetalheTarefaBinding;


public class DetalheTarefaFragment extends Fragment {
    private FragmentDetalheTarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(), container,false);
        // retorna a View raiz (root) do binding
        return binding.getRoot();
    }
}