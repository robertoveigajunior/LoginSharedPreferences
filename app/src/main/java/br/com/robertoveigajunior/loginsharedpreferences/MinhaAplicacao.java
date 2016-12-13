package br.com.robertoveigajunior.loginsharedpreferences;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import br.com.robertoveigajunior.loginsharedpreferences.dao.DBHelper;
import br.com.robertoveigajunior.loginsharedpreferences.dao.DatabaseManager;

/**
 * Created by LamborghiniLeptop on 12/12/2016.
 */

public class MinhaAplicacao extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        this.context = getApplicationContext();
        DatabaseManager.initializeInstance(new DBHelper());
    }

    public static Context getContext() {
        return context;
    }
}
