package net.blix.theclaimmaster.Database;

public class ClaimDbSchema {

    public static final class ClaimTable{

        public static final String NAME = "claims";

        public static final class Cols {
            public static final String UUID = "uuid";

            public static final String TAG = "tag";
            public static final String HEADSTRING = "headString";
            public static final String NUMEROSINIESTRO = "numeroSiniestro";
            public static final String FECHASINIESTRO = "fechaSiniestro";
            public static final String FECHAAVISOAASEGURADORA = "fechaAvisoALaAseguradora";
            public static final String FECHAENCARGOAAJUSTADOR = "fechaEncragoAAjustador";
            public static final String FECHASOLICDOCS = "fechaSolicitudDeDocumentos";
            public static final String FECHADOCCOMP = "fechaDeDocumentaionCompleta";
            public static final String FECHACOORDINSPECCION = "fechaDeCoordinacionDeInspeccion";
            public static final String FECHAREALIZACIONINSPECCION = "fechaReRealizacionDeInspeccion";
            public static final String FECHAENTREGALIQAASEGURADO = "fechaEntregaLiquidacionAAsegurado";
            public static final String FECHADEVOLUCIONLIQAAJUSTADOR = "fechaDeDevolucionLiquidacionAAjustador";
            public static final String FECHAENVIOLIQAASEGURADORA = "fechaEnvioDeLiquidacionAAseguradota";
            public static final String TIPOPOLIZA = "tipoPoliza";
            public static final String NUMEROPOLIZA = "numeroPoliza";
            public static final String MONEDASUMAASEGURADAEDIFICACION = "monedaSumaAseguradaEdificacion";
            public static final String SUMAASEGURADAEDIFICACION = "sumaAseguradaEdificacion";
            public static final String MONEDASUMAASEGURADACONTENIDO = "monedaSumaAseguradaContenido";
            public static final String SUMAASEGURADACONTENIDO = "sumaAseguradaContenido";
            public static final String DIRECCIONASEGURADA = "direccionAsegurada";
            public static final String LONGITUDUBICACION = "longitudUbicacion";
            public static final String LATITUDUBICACION = "latitudUbicacion";
            public static final String NOMBREASEGURADO = "nombreAsegurado";
            public static final String DNIASEGURADO = "dniAsegurado";
            public static final String CELULARASEGURADO = "celularAsegurado";
            public static final String EMAILASEGURADO = "emailAsegurado";
            public static final String SUMARECLAMADA = "sumaAeclamada";
            public static final String DIRECCIONTASACION = "direccionTasacion";
            public static final String AREATERRENOTASACION = "areaTerrenoTasacion";
            public static final String VALORTERRENOTASACION = "valorTerrenoTasacion";
            public static final String VALORRECONSTRUCCIONEDIFICACIONTASACION = "valorReconstruccionTasacion";
            public static final String VALORCOMERCIALEDIFICACION = "valorComercialEdificacion";
            public static final String DESCRIPCIONEDIFICACIONTASACION = "descripcionEdificacionTasacion";
            public static final String FECHACONSTRUCCIONEDIFICACIONTASACION = "fechaConstruccionEdificacion";
            public static final String FECHAREALIZACIONTASACION = "fechaRealizacionTasacion";
            //public static final String CONTACTOASEGURADO = "contactoAsegurado";
        }
    }
}
