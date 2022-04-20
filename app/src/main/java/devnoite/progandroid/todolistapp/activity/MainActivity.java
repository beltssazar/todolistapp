package devnoite.progandroid.todolistapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import devnoite.progandroid.todolistapp.R;
import devnoite.progandroid.todolistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // instancia o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // seta na contentView a raiz (root) do binding
        setContentView(binding.getRoot());
    }
}