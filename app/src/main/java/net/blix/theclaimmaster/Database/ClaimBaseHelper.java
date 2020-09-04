package net.blix.theclaimmaster.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.blix.theclaimmaster.Database.ClaimDbSchema.ClaimTable;

public class ClaimBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "claimBase.db";

    public ClaimBaseHelper(Context context){

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ClaimTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ClaimTable.Cols.UUID + ", " +
                ClaimTable.Cols.TAG + ", " +
                ClaimTable.Cols.HEADSTRING + ", " +
                ClaimTable.Cols.NUMEROSINIESTRO + ", " +
                ClaimTable.Cols.FECHASINIESTRO + ", " +
                ClaimTable.Cols.FECHAAVISOAASEGURADORA + ", " +
                ClaimTable.Cols.FECHAENCARGOAAJUSTADOR + ", " +
                ClaimTable.Cols.FECHASOLICDOCS + ", " +
                ClaimTable.Cols.FECHADOCCOMP + ", " +
                ClaimTable.Cols.FECHACOORDINSPECCION + ", " +
                ClaimTable.Cols.FECHAREALIZACIONINSPECCION + ", " +
                ClaimTable.Cols.FECHAENTREGALIQAASEGURADO + ", " +
                ClaimTable.Cols.FECHADEVOLUCIONLIQAAJUSTADOR + ", " +
                ClaimTable.Cols.FECHAENVIOLIQAASEGURADORA + ", " +
                ClaimTable.Cols.TIPOPOLIZA + ", " +
                ClaimTable.Cols.NUMEROPOLIZA + ", " +
                ClaimTable.Cols.MONEDASUMAASEGURADAEDIFICACION + ", " +
                ClaimTable.Cols.SUMAASEGURADAEDIFICACION + ", " +
                ClaimTable.Cols.MONEDASUMAASEGURADACONTENIDO + ", " +
                ClaimTable.Cols.SUMAASEGURADACONTENIDO + ", " +
                ClaimTable.Cols.DIRECCIONASEGURADA + ", " +
                ClaimTable.Cols.LONGITUDUBICACION + ", " +
                ClaimTable.Cols.LATITUDUBICACION + ", " +
                ClaimTable.Cols.NOMBREASEGURADO + ", " +
                ClaimTable.Cols.DNIASEGURADO + ", " +
                ClaimTable.Cols.CELULARASEGURADO + ", " +
                ClaimTable.Cols.EMAILASEGURADO + ", " +
                ClaimTable.Cols.SUMARECLAMADA + ", " +
                ClaimTable.Cols.DIRECCIONTASACION + ", " +
                ClaimTable.Cols.AREATERRENOTASACION + ", " +
                ClaimTable.Cols.VALORTERRENOTASACION + ", " +
                ClaimTable.Cols.VALORRECONSTRUCCIONEDIFICACIONTASACION + ", " +
                ClaimTable.Cols.VALORCOMERCIALEDIFICACION + ", " +
                ClaimTable.Cols.DESCRIPCIONEDIFICACIONTASACION + ", " +
                ClaimTable.Cols.FECHACONSTRUCCIONEDIFICACIONTASACION + ", " +
                ClaimTable.Cols.FECHAREALIZACIONTASACION + //", " +
                //ClaimTable.Cols.CONTACTOASEGURADO +
                ")"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}