package net.blix.theclaimmaster.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import net.blix.theclaimmaster.Beans.Claim;
import net.blix.theclaimmaster.Database.ClaimDbSchema.ClaimTable;

import java.util.Date;
import java.util.UUID;

public class ClaimCursorWrapper extends CursorWrapper {
    public ClaimCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Claim getClaim(){
        String uuidString = getString(getColumnIndex(ClaimTable.Cols.UUID));
        String tagString = getString(getColumnIndex(ClaimTable.Cols.TAG));

        String headstringString = getString(getColumnIndex(ClaimTable.Cols.HEADSTRING));
        String numerosiniestroString = getString(getColumnIndex(ClaimTable.Cols.NUMEROSINIESTRO));
        long fechasiniestroDate = getLong(getColumnIndex(ClaimTable.Cols.FECHASINIESTRO));
        long fechaavisoaaseguradoraDate = getLong(getColumnIndex(ClaimTable.Cols.FECHAAVISOAASEGURADORA));
        long fechaencargoaajustadorDate = getLong(getColumnIndex(ClaimTable.Cols.FECHAENCARGOAAJUSTADOR));
        long fechasolicdocsDate = getLong(getColumnIndex(ClaimTable.Cols.FECHASOLICDOCS));
        long fechadoccompDate = getLong(getColumnIndex(ClaimTable.Cols.FECHADOCCOMP));
        long fechacoordinspeccionDate = getLong(getColumnIndex(ClaimTable.Cols.FECHACOORDINSPECCION));
        long fecharealizacioninspeccionDate = getLong(getColumnIndex(ClaimTable.Cols.FECHAREALIZACIONINSPECCION));
        long fechaentregaliqaaseguradoDate = getLong(getColumnIndex(ClaimTable.Cols.FECHAENTREGALIQAASEGURADO));
        long fechadevolucionliqaajustadorDate = getLong(getColumnIndex(ClaimTable.Cols.FECHADEVOLUCIONLIQAAJUSTADOR));
        long fechaenvioliqaaseguradoraDate = getLong(getColumnIndex(ClaimTable.Cols.FECHAENVIOLIQAASEGURADORA));
        String tipopolizaString = getString(getColumnIndex(ClaimTable.Cols.TIPOPOLIZA));
        String numeropolizaaString = getString(getColumnIndex(ClaimTable.Cols.NUMEROPOLIZA));
        String monedasumaaseguradaedificacionString = getString(getColumnIndex(ClaimTable.Cols.MONEDASUMAASEGURADAEDIFICACION));
        float sumaaseguradaedificacionString = getFloat(getColumnIndex(ClaimTable.Cols.SUMAASEGURADAEDIFICACION));
        String monedasumaaseguradacontenidoString = getString(getColumnIndex(ClaimTable.Cols.MONEDASUMAASEGURADACONTENIDO));
        float sumaaseguradacontenidoFloat = getFloat(getColumnIndex(ClaimTable.Cols.SUMAASEGURADACONTENIDO));
        String direccionaseguradaString = getString(getColumnIndex(ClaimTable.Cols.DIRECCIONASEGURADA));
        String longitudubicacionString = getString(getColumnIndex(ClaimTable.Cols.LONGITUDUBICACION));
        String latitudubicacionString = getString(getColumnIndex(ClaimTable.Cols.LATITUDUBICACION));
        String nombreaseguradoString = getString(getColumnIndex(ClaimTable.Cols.NOMBREASEGURADO));
        String dniaseguradoString = getString(getColumnIndex(ClaimTable.Cols.DNIASEGURADO));
        String celularaseguradoString = getString(getColumnIndex(ClaimTable.Cols.CELULARASEGURADO));
        String emailaseguradoString = getString(getColumnIndex(ClaimTable.Cols.EMAILASEGURADO));
        // OJO SE PODRIA INCLUIR MONEDA SUMA RECLAMADA AQUIIIII
        float sumareclamadaFloat = getFloat(getColumnIndex(ClaimTable.Cols.SUMARECLAMADA));
        String direcciontasacionString = getString(getColumnIndex(ClaimTable.Cols.DIRECCIONTASACION));
        String areaterrenotasacionString = getString(getColumnIndex(ClaimTable.Cols.AREATERRENOTASACION));
        String valorterrenotasacionString = getString(getColumnIndex(ClaimTable.Cols.VALORTERRENOTASACION));
        String valorreconstruccionedificaciontasacionString = getString(getColumnIndex(ClaimTable.Cols.VALORRECONSTRUCCIONEDIFICACIONTASACION));
        String valorcomercialedificacionString = getString(getColumnIndex(ClaimTable.Cols.VALORCOMERCIALEDIFICACION));
        String descripcionedificaciontasacionString = getString(getColumnIndex(ClaimTable.Cols.DESCRIPCIONEDIFICACIONTASACION));
        long fechaconstruccionedificaciontasacionDate = getLong(getColumnIndex(ClaimTable.Cols.FECHACONSTRUCCIONEDIFICACIONTASACION));
        long fecharealizaciontasacionDate = getLong(getColumnIndex(ClaimTable.Cols.FECHAREALIZACIONTASACION));
        //String contactoasegurado = getString(getColumnIndex(ClaimTable.Cols.CONTACTOASEGURADO));


        Claim claim = new Claim(UUID.fromString(uuidString));
        claim.setTag(tagString);

        claim.setHeadString(headstringString);
        claim.setNumeroSiniestro(numerosiniestroString);
        claim.setFechaSiniestro(new Date(fechasiniestroDate));
        claim.setFechaAvisoAAseguradora(new Date(fechaavisoaaseguradoraDate));
        claim.setFechaEncargoAAjustador(new Date(fechaencargoaajustadorDate));
        claim.setFechaSolicDocs(new Date(fechasolicdocsDate));
        claim.setFechaDocComp(new Date(fechadoccompDate));
        claim.setFechaCoordInspeccion(new Date(fechacoordinspeccionDate));
        claim.setFechaRealizacionInspeccion(new Date(fecharealizacioninspeccionDate));
        claim.setFechaEntregaLiqAAsegurado(new Date(fechaentregaliqaaseguradoDate));
        claim.setFechaDevolucionLiqAAjustador(new Date(fechadevolucionliqaajustadorDate));
        claim.setFechaEnvioLiqAAseguradora(new Date(fechaenvioliqaaseguradoraDate));
        claim.setTipoPoliza(tipopolizaString);
        claim.setNumeroPoliza(numeropolizaaString);
        claim.setMonedaSumaAseguradaEdificacion (monedasumaaseguradaedificacionString);
        claim.setSumaAseguradaEdificacion(sumaaseguradaedificacionString);
        claim.setMonedaSumaAseguradaContenido(monedasumaaseguradacontenidoString);
        claim.setSumaAseguradaContenido(sumaaseguradacontenidoFloat);
        claim.setDireccionAsegurada(direccionaseguradaString);
        claim.setLongitudUbicacion(longitudubicacionString);
        claim.setLatitudUbicacion(latitudubicacionString);
        claim.setNombreAsegurado(nombreaseguradoString);
        claim.setDNIAsegurado(dniaseguradoString);
        claim.setCelularAsegurado(celularaseguradoString);
        claim.setEmailAsegurado(emailaseguradoString);
        // OJO SE PODRIA INCLUIR MONEDA SUMA RECLAMADA AQUIIIII
        claim.setSumaReclamada(sumareclamadaFloat);
        claim.setDireccionTasacion(direcciontasacionString);
        claim.setAreaTerrenoTasacion(areaterrenotasacionString);
        claim.setValorTerrenoTasacion(valorterrenotasacionString);
        claim.setValorReconstruccionEdificacionTasacion(valorreconstruccionedificaciontasacionString);
        claim.setValorComercialEdificacion(valorcomercialedificacionString);
        claim.setDescripcionEdificacionTasacion(descripcionedificaciontasacionString);
        claim.setFechaConstruccionEdificacionTasacion(new Date(fechaconstruccionedificaciontasacionDate));
        claim.setFechaRealizacionTasacion(new Date(fecharealizaciontasacionDate));
        //claim.setContactoAsegurado(contactoasegurado);

        return claim;

        //return null;
    }
}




/*
        UUID,
        TAG,
        HEADSTRING,
        NUMEROSINIESTRO,
        FECHASINIESTRO,
        FECHAAVISOAASEGURADORA,
        FECHAENCARGOAAJUSTADOR,
        FECHASOLICDOCS,
        FECHADOCCOMP,
        FECHACOORDINSPECCION,
        FECHAREALIZACIONINSPECCION,
        FECHAENTREGALIQAASEGURADO,
        FECHADEVOLUCIONLIQAAJUSTADOR,
        FECHAENVIOLIQAASEGURADORA,
        TIPOPOLIZA,
        NUMEROPOLIZA,
        MONEDASUMAASEGURADAEDIFICACION,
        SUMAASEGURADAEDIFICACION,
        MONEDASUMAASEGURADACONTENIDO,
        SUMAASEGURADACONTENIDO,
        DIRECCIONASEGURADA,
        LONGITUDUBICACION,
        LATITUDUBICACION,
        NOMBREASEGURADO,
        DNIASEGURADO,
        CELULARASEGURADO,
        EMAILASEGURADO,
        SUMARECLAMADA,
        DIRECCIONTASACION ,
        AREATERRENOTASACION ,
        VALORTERRENOTASACION,
        VALORRECONSTRUCCIONEDIFICACIONTASACION,
        VALORCOMERCIALEDIFICACION,
        DESCRIPCIONEDIFICACIONTASACION,
        FECHACONSTRUCCIONEDIFICACIONTASACION,
        FECHAREALIZACIONTASACION,

        */