package br.com.robertoveigajunior.loginsharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkConectado();
    }

    public void logar(View v) {
        String usuario = ((EditText) findViewById(R.id.username))
                .getText()
                .toString();
        String senha = ((EditText) findViewById(R.id.password))
                .getText()
                .toString();

        CheckBox cbManter = ((CheckBox) findViewById(R.id.checkBox));

        if(usuario.equals("admin") && senha.equals("123")) {
            salvarPreferencias(usuario, cbManter.isChecked());
            abrirTela();
        }


    }

    private void checkConectado() {
        SharedPreferences settings = getSharedPreferences("PREFERENCIAS", MODE_PRIVATE);
        if(settings.getBoolean("manterConectado", false)) {
            abrirTela();
        }

    }

    private void abrirTela() {
        startActivity(new Intent(this, LivrosActivity.class));
        finish();
    }

    private void salvarPreferencias(String usuario, Boolean conectado) {
        SharedPreferences settings = getSharedPreferences("PREFERENCIAS", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("string", usuario);
        editor.putBoolean("manterConectado", conectado);
        editor.commit();
    }
}
