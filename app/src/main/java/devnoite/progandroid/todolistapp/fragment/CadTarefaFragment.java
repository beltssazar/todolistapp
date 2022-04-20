package devnoite.progandroid.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import devnoite.progandroid.todolistapp.R;
import devnoite.progandroid.todolistapp.databinding.FragmentCadTarefaBinding;


public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefaBinding binding;

    // variável para o datePicker
    private DatePickerDialog datePicker;

    // variáveis para ano, mês e dia
    int year, month, day;
    //variável para obter a data atual
    Calendar dataAtual;
    // variável para a data formatada
    String dataFormatada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        // inatanciar a data atual
        dataAtual = Calendar.getInstance();
        // obter ano, mês e dia da daa atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        // instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia) ->{
            // ao escolher uma data bo datePicker, cai aqui
        }, year, month, day); 

        //ação do click do botão de seleção da data
        binding.btData.setOnClickListener(v ->{
            datePicker.show();
        });
        // retorna a View raiz (root) do binding
        return binding.getRoot();
    }
}