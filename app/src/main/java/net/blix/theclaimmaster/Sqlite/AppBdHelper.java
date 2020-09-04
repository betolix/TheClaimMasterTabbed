package net.blix.theclaimmaster.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.blix.theclaimmaster.Beans.UserBean;
import net.blix.theclaimmaster.Contract.UserContract;

import static android.provider.Contacts.SettingsColumns.KEY;

public class AppBdHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "tienda.db";



    public AppBdHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + "("
                + UserContract.UserEntry._ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.UserEntry.IDCLIENTE + "TEXT NOT NULL,"
                + UserContract.UserEntry.NOMBRE + "TEXT NOT NULL,"
                + UserContract.UserEntry.APELLIDOPATERNO + "TEXT NOT NULL,"
                + UserContract.UserEntry.APELLIDOMATERNO + "TEXT NOT NULL,"
                + UserContract.UserEntry.DNI + "TEXT NOT NULL,"
                + UserContract.UserEntry.USUARIO + "TEXT NOT NULL,"
                + UserContract.UserEntry.PASSWORD + "TEXT NOT NULL,"
                + "UNIQUE (" + UserContract.UserEntry._ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long agregarUsuario(UserBean userBean){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(UserContract.UserEntry.TABLE_NAME, null,userBean.contentValues());
    }
}
