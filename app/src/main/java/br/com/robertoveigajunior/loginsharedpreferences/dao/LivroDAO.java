package br.com.robertoveigajunior.loginsharedpreferences.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.robertoveigajunior.loginsharedpreferences.model.Livro;

/**
 * Created by LamborghiniLeptop on 12/12/2016.
 */

public class LivroDAO {
    private SQLiteDatabase db;

    public static String createTable() {
        return "CREATE TABLE " + Livro.NOME_TABELA + " ( "
                + Livro.ID + " integer primary key autoincrement, "
                + Livro.TITULO + " text, "
                + Livro.AUTOR + " text, "
                + Livro.EDITORA + " text)";
    }

    public String insereDado(Livro livro) {
        ContentValues valores;
        long resultado;

        db = DatabaseManager.getInstance().openDatabase(false);
        valores = new ContentValues();
        valores.put(Livro.TITULO, livro.getTitulo());
        valores.put(Livro.AUTOR, livro.getAutor());
        valores.put(Livro.EDITORA, livro.getEditora());
        resultado = db.insert(Livro.NOME_TABELA, null, valores);

        DatabaseManager.getInstance().closeDatabase();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public List<Livro> carregaDados() {
        List<Livro> livros = new ArrayList<>();

        Cursor cursor;
        String[] campos = {Livro.ID, Livro.TITULO};
        db = DatabaseManager.getInstance().openDatabase(true);
        //cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);
        cursor = db.query(Livro.NOME_TABELA, null, null, null, null, null, null, null);

        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    Livro livro = new Livro();
                    livro.setId(cursor.getInt(cursor.getColumnIndex(Livro.ID)));
                    livro.setEditora(cursor.getString(cursor.getColumnIndex(Livro.EDITORA)));
                    livro.setTitulo(cursor.getString(cursor.getColumnIndex(Livro.TITULO)));
                    livro.setAutor(cursor.getString(cursor.getColumnIndex(Livro.EDITORA)));
                    livros.add(livro);
                } while(cursor.moveToNext());
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return livros;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {Livro.ID,Livro.TITULO,Livro.AUTOR,Livro.EDITORA};
        String where = Livro.ID + "=" + id;
        db = DatabaseManager.getInstance().openDatabase(true);
        cursor = db.query(Livro.NOME_TABELA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        DatabaseManager.getInstance().closeDatabase();
        return cursor;
    }

    public void alteraRegistro(Livro livro){
        ContentValues valores;
        String where;

        db = DatabaseManager.getInstance().openDatabase(false);

        where = Livro.ID + "=" + livro.getId();

        valores = new ContentValues();
        valores.put(Livro.TITULO, livro.getTitulo());
        valores.put(Livro.AUTOR, livro.getAutor());
        valores.put(Livro.EDITORA, livro.getEditora());

        db.update(Livro.NOME_TABELA, valores,where,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void deletaRegistro(int id){
        String where = Livro.ID + "=" + id;
        db = DatabaseManager.getInstance().openDatabase(false);
        db.delete(Livro.NOME_TABELA,where,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
