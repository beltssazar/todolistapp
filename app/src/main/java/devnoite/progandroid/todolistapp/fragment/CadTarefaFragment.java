package devnoite.progandroid.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import devnoite.progandroid.todolistapp.R;
import devnoite.progandroid.todolistapp.database.AppDatabase;
import devnoite.progandroid.todolistapp.databinding.FragmentCadTarefaBinding;
import devnoite.progandroid.todolistapp.model.Tarefa;


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
    // variável para a database
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia a database
        database = AppDatabase.getDatabase(getContext());

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
            // passar para as variáveis globais
            year = ano;
            month = mes;
            day = dia;
            // formata a data
            dataFormatada = String.format("%02d/%02d/%04d", day,month+1,year);
            // aplica a data formatada no botao
            binding.btData.setText(dataFormatada);

        }, year, month, day);

        //ação do click do botão de seleção da data
        binding.btData.setOnClickListener(v ->{
            datePicker.show();
        });

        // listener do botao salvar
        binding.btSalvar.setOnClickListener(v -> {
            if (binding.tituloTarefa.getText().toString().isEmpty()){
                //Toast.makeText(getContext(),"Informe o Título da Tarefa", Toast.LENGTH_SHORT).show();
                Snackbar.make(binding.btData,"Atribua um título para a tarefa", Snackbar.LENGTH_SHORT).setAnchorView(R.id.linha).show();
            }else if (dataFormatada == null){
                //Toast.makeText(getContext(),"Informe uma data para conclusão da tarefa", Toast.LENGTH_SHORT).show();
                Snackbar.make(binding.btData,"Informe uma data para conclusão da tarefa", Snackbar.LENGTH_SHORT).setAnchorView(R.id.linha).show();
            }else {
                // criar uma tarefa
                Tarefa tarefa = new Tarefa();
                // popular a tarefa
                tarefa.setTitulo(binding.tituloTarefa.getText().toString());
                tarefa.setDescricao(binding.descTarefa.getText().toString());
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                // criar um Calendar
                Calendar dataPrevista = Calendar.getInstance();
                // muda a data para a data escolhida no datepicker
                dataPrevista.set(year, month, day);
                // passa os milisegundos da data para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
                // salvar a tarefa
                new InsertTarefa().execute(tarefa);
            }
        });

        // retorna a View raiz (root) do binding
        return binding.getRoot();
    }

    // AsyncTask para inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            // pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                // chamar o método para salvar a tarefa
                database.getTarefaDao().insert(t);
                // retornar
                return "ok";
            } catch (Exception erro){
                erro.printStackTrace();
                // retorna a mensagem de erro
                return erro.getMessage();
                }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("ok")){
                Snackbar.make(binding.btData,"Tarefa criada com sucesso", Snackbar.LENGTH_SHORT)
                        .setAnchorView(R.id.linha).setBackgroundTint(getActivity().getResources().getColor(R.color.green))
                        .setTextColor(getActivity().getResources().getColor(R.color.black)).show();
                limparCampos();
                // voltar ao fragment anterior
                //getActivity().onBackPressed();
            }else{
                Snackbar.make(binding.btData,"Não foi possível criar a tarefa", Snackbar.LENGTH_SHORT).setAnchorView(R.id.linha)
                        .setBackgroundTint(getActivity().getResources().getColor(R.color.red))
                        .setTextColor(getActivity().getResources().getColor(R.color.white)).show();
            }
        }
    }

    public void limparCampos(){
        binding.tituloTarefa.setText("");
        binding.descTarefa.setText("");
        binding.btData.setText(R.string.btData);
    }
}