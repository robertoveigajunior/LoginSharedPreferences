package br.com.robertoveigajunior.loginsharedpreferences.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

import br.com.robertoveigajunior.loginsharedpreferences.MinhaAplicacao;
import br.com.robertoveigajunior.loginsharedpreferences.model.Livro;

/**
 * Created by LamborghiniLeptop on 12/12/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String NOME_BANCO = "livros.db";
    public static int VERSAO_BANCO = 1;
    public DBHelper(){
        super(MinhaAplicacao.getContext()
                ,NOME_BANCO
                ,null
                ,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LivroDAO.createTable());
        seed();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void seed() {
        LivroDAO dao = new LivroDAO();

        Livro android = new Livro();
        android.setAutor("Ricardo Lechetta");
        android.setEditora("Novatec");
        android.setTitulo("Google Android");
        dao.insereDado(android);

        Livro torreNegra = new Livro();
        android.setAutor("A Torre Negra");
        android.setEditora("AchoSuma");
        android.setTitulo("Stephen King");
        dao.insereDado(torreNegra);
    }
}
