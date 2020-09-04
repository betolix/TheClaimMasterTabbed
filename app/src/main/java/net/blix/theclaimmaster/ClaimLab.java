package net.blix.theclaimmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.blix.theclaimmaster.Beans.Claim;
import net.blix.theclaimmaster.Database.ClaimBaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.blix.theclaimmaster.Database.ClaimCursorWrapper;

import net.blix.theclaimmaster.Database.ClaimDbSchema.ClaimTable;
import net.blix.theclaimmaster.Database.ClaimDbSchema.ClaimTable.Cols;


public class ClaimLab {
    private static ClaimLab sClaimLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //private List <Claim> mClaims;

    public static ClaimLab get(Context context){
        if(sClaimLab == null){
            sClaimLab = new ClaimLab(context);
        }
        return sClaimLab;
    }

    private ClaimLab(Context context){

        //DATABASE STUFF
        mContext = context.getApplicationContext();
        mDatabase = new ClaimBaseHelper(mContext).getWritableDatabase();

        //mClaims = new ArrayList<>();

        /*
        for(int i = 0; i < 100; i++){
            Claim claim = new Claim();
            claim.setNumeroSiniestro("Siniestro # " + i);
            claim.setTag("PPS CAT SIL");
            claim.setHeadString("Re: Siniestro: 1000277490 | Pól: 2001253404 | BCP / MARIELLA EDITH CRUZADO SAUCEDO | Fecha del siniestro: 27/03/2017 || (G570-00010815)\n");
            mClaims.add(claim);
        }
        */
    }

    public void addClaim( Claim c){
        //mClaims.add(c);
        ContentValues values = getContentValues(c);
        mDatabase.insert(ClaimTable.NAME, null, values);
    }

    public void deleteClaim(UUID claimId){
        String uuidString = claimId.toString();
        mDatabase.delete(ClaimTable.NAME, ClaimTable.Cols.UUID + " = ?", new String[] {uuidString});

    }


    public List<Claim> getClaims(){
        //return mClaims;
        //return new ArrayList<>();

        List<Claim> claims = new ArrayList<>();

        ClaimCursorWrapper cursor = queryClaims(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                claims.add(cursor.getClaim());
                cursor.moveToNext();
            }
            cursor.close();
        } finally{
            //cursor.close();
        }

        return claims;


        //MUCHO JO AQUI
        //return new ArrayList<>();
    }

    public Claim getClaim(UUID id){
        /*
        for(Claim claim : mClaims){
            if(claim.getId().equals(id)){
                return claim;
            }
        }
        */
        //return null;

        ClaimCursorWrapper cursor = queryClaims(ClaimTable.Cols.UUID + " =  ?", new String[] {id.toString()});

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getClaim();
        } finally{
            cursor.close();
        }
    }

    //GET PHOTO FILE
    public File getPhotoFile(Claim claim){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, claim.getPhotoFilename());
    }

    public void updateClaim(Claim claim){
        String uuidString = claim.getId().toString();
        ContentValues values = getContentValues(claim);

        mDatabase.update(ClaimTable.NAME, values, ClaimTable.Cols.UUID + " = ?", new String[] {uuidString});

    }

    //private Cursor queryClaims (String whereClause, String[] whereArgs){
    private ClaimCursorWrapper queryClaims(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
          ClaimTable.NAME,
          null, // columns - null selects all columns
          whereClause,
          whereArgs,
          null, // groupBy
          null, // having
          null //orderBy
        );

        //return cursor;
        return new ClaimCursorWrapper(cursor);
    }




    private static ContentValues getContentValues (Claim claim){
        ContentValues values = new ContentValues();
        values.put(ClaimTable.Cols.UUID, claim.getId().toString());
        values.put(ClaimTable.Cols.TAG, claim.getTag());
        values.put(ClaimTable.Cols.HEADSTRING, claim.getHeadString());
        values.put(ClaimTable.Cols.NUMEROSINIESTRO, claim.getNumeroSiniestro());
        values.put(ClaimTable.Cols.FECHASINIESTRO, claim.getFechaSiniestro().getTime());
        values.put(ClaimTable.Cols.FECHAAVISOAASEGURADORA, claim.getFechaAvisoAAseguradora().getTime());
        values.put(ClaimTable.Cols.FECHAENCARGOAAJUSTADOR, claim.getFechaEncargoAAjustador().getTime());
        values.put(ClaimTable.Cols.FECHASOLICDOCS, claim.getFechaSolicDocs().getTime());
        values.put(ClaimTable.Cols.FECHADOCCOMP, claim.getFechaDocComp().getTime());
        values.put(ClaimTable.Cols.FECHACOORDINSPECCION, claim.getFechaCoordInspeccion().getTime());
        values.put(ClaimTable.Cols.FECHAREALIZACIONINSPECCION, claim.getFechaRealizacionInspeccion().getTime());
        values.put(ClaimTable.Cols.FECHAENTREGALIQAASEGURADO, claim.getFechaEntregaLiqAAsegurado().getTime());
        values.put(ClaimTable.Cols.FECHADEVOLUCIONLIQAAJUSTADOR, claim.getFechaDevolucionLiqAAjustador().getTime());
        values.put(ClaimTable.Cols.FECHAENVIOLIQAASEGURADORA, claim.getFechaEnvioLiqAAseguradora().getTime());
        values.put(ClaimTable.Cols.TIPOPOLIZA, claim.getTipoPoliza());
        values.put(ClaimTable.Cols.NUMEROPOLIZA, claim.getNumeroPoliza());
        values.put(ClaimTable.Cols.MONEDASUMAASEGURADAEDIFICACION, claim.getMonedaSumaAseguradaEdificacion());
        values.put(ClaimTable.Cols.SUMAASEGURADAEDIFICACION, claim.getSumaAseguradaEdificacion().toString());
        values.put(ClaimTable.Cols.MONEDASUMAASEGURADACONTENIDO, claim.getMonedaSumaAseguradaContenido());
        values.put(ClaimTable.Cols.SUMAASEGURADACONTENIDO, claim.getSumaAseguradaContenido().toString());
        values.put(ClaimTable.Cols.DIRECCIONASEGURADA, claim.getDireccionAsegurada());
        values.put(ClaimTable.Cols.LONGITUDUBICACION, claim.getLongitudUbicacion());
        values.put(ClaimTable.Cols.LATITUDUBICACION, claim.getLatitudUbicacion());
        values.put(ClaimTable.Cols.NOMBREASEGURADO, claim.getNombreAsegurado());
        values.put(ClaimTable.Cols.DNIASEGURADO, claim.getDNIAsegurado());
        values.put(ClaimTable.Cols.CELULARASEGURADO, claim.getCelularAsegurado());
        values.put(ClaimTable.Cols.EMAILASEGURADO, claim.getEmailAsegurado());
        values.put(ClaimTable.Cols.SUMARECLAMADA, claim.getSumaReclamada().toString());
        values.put(ClaimTable.Cols.DIRECCIONTASACION, claim.getDireccionTasacion());
        values.put(ClaimTable.Cols.AREATERRENOTASACION, claim.getAreaTerrenoTasacion());
        values.put(ClaimTable.Cols.VALORTERRENOTASACION, claim.getValorTerrenoTasacion());
        values.put(ClaimTable.Cols.VALORRECONSTRUCCIONEDIFICACIONTASACION, claim.getValorReconstruccionEdificacionTasacion());
        values.put(ClaimTable.Cols.VALORCOMERCIALEDIFICACION, claim.getValorComercialEdificacion());
        values.put(ClaimTable.Cols.DESCRIPCIONEDIFICACIONTASACION, claim.getDescripcionEdificacionTasacion());
        values.put(ClaimTable.Cols.FECHACONSTRUCCIONEDIFICACIONTASACION, claim.getFechaConstruccionEdificacionTasacion().getTime());
        values.put(ClaimTable.Cols.FECHAREALIZACIONTASACION, claim.getFechaRealizacionTasacion().getTime());
        //values.put(ClaimTable.Cols.CONTACTOASEGURADO, claim.getContactoAsegurado());

        return values;
    }



}
