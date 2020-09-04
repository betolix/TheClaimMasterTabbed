package net.blix.theclaimmaster.Beans;

import java.util.Date;
import java.util.UUID;

public class Claim {
    private UUID mId;

    private String mNumeroSiniestro;
    private String mAseguradoraShort;
    private String mPaisSiniestro;
    private String mDepartamentoSiniestro;
    private String mZonaSiniestro;


    //FECHAS
    private Date mFechaSiniestro;
    private Date mFechaAvisoAAseguradora;
    private Date mFechaEncargoAAjustador;
    private Date mFechaSolicDocs;
    private Date mFechaDocComp;
    private Date mFechaCoordInspeccion;
    private Date mFechaRealizacionInspeccion;
    private Date mFechaEntregaLiqAAsegurado;
    private Date mFechaDevolucionLiqAAjustador;
    private Date mFechaEnvioLiqAAseguradora;


    //POLIZA
    private String mTipoPoliza;
    private String mNumeroPoliza;
    private String mMonedaSumaAseguradaEdificacion;
    private Float mSumaAseguradaEdificacion;
    private String mMonedaSumaAseguradaContenido;
    private Float mSumaAseguradaContenido;
    private String mDireccionAsegurada;

    //SINIESTRO
    //private String mNumeroSiniestro ="Siniestro: ";
    private String mLongitudUbicacion;
    private String mLatitudUbicacion;

    //ASEGURADO
    private String mNombreAsegurado;
    private String mDNIAsegurado;
    private String mCelularAsegurado;
    private String mEmailAsegurado;
    // OJO SE PUEDE CONIDERAR MONEDA DE SUMA RECLAMADA AQUIIIIIIII
    private Float mSumaReclamada;

    //TASACION
    private String mDireccionTasacion;
    private String mAreaTerrenoTasacion;
    private String mValorTerrenoTasacion;
    private String mValorReconstruccionEdificacionTasacion;
    private String mValorComercialEdificacion;
    private String mDescripcionEdificacionTasacion;
    private Date mFechaConstruccionEdificacionTasacion;
    private Date mFechaRealizacionTasacion;

    //DESCRIPTORS
    private String mHeadString;
    private String mTag="sil xxx";

    //CONTACTO ASEGURADO
    private String mContactoAsegurado;


    public Claim(){
        this(UUID.randomUUID());
        /*
        mId = UUID.randomUUID();
        mFechaSiniestro = new Date(0L);
        mFechaAvisoAAseguradora= new Date(0L);
        mFechaEncargoAAjustador= new Date(0L);
        mFechaSolicDocs= new Date(0L);
        mFechaDocComp= new Date(0L);
        mFechaCoordInspeccion= new Date(0L);
        mFechaRealizacionInspeccion= new Date(0L);
        mFechaEntregaLiqAAsegurado= new Date(0L);
        mFechaDevolucionLiqAAjustador= new Date(0L);
        mFechaEnvioLiqAAseguradora= new Date(0L);
        */
    }

    public Claim(UUID id){
        mId = id;
        mFechaSiniestro = new Date(0l);
        mNumeroSiniestro = "0000000";

        mPaisSiniestro = "Perú";
        mDepartamentoSiniestro = "Piura";
        mZonaSiniestro = "Santa María";
        mAseguradoraShort = "PPS";

        mPaisSiniestro = "Perú";
        mDepartamentoSiniestro = "Piura";
        mZonaSiniestro = "Santa María";


        //FECHAS
        mFechaSiniestro = new Date(0l);
        mFechaAvisoAAseguradora = new Date(0l);
        mFechaEncargoAAjustador = new Date(0l);
        mFechaSolicDocs = new Date(0l);
        mFechaDocComp = new Date(0l);
        mFechaCoordInspeccion = new Date(0l);
        mFechaRealizacionInspeccion = new Date(0l);
        mFechaEntregaLiqAAsegurado = new Date(0l);
        mFechaDevolucionLiqAAjustador = new Date(0l);
        mFechaEnvioLiqAAseguradora = new Date(0l);


        //POLIZA
        mTipoPoliza = "PRE1: ";;
        mNumeroPoliza = "123456";
        mMonedaSumaAseguradaEdificacion = "USD";
        mSumaAseguradaEdificacion = 0.0f;
        mMonedaSumaAseguradaContenido = "USD: ";
        mSumaAseguradaContenido = 0.0f;
        mDireccionAsegurada = "Pasaje juan 23, Numero 154 Dpto 707";

        //SINIESTRO
        //private String mNumeroSiniestro ="Siniestro: ";
        mLongitudUbicacion = "LO12345";
        mLatitudUbicacion = "LA123345";

        //ASEGURADO
        mNombreAsegurado = "";
        mDNIAsegurado = "0045454087: ";
        mCelularAsegurado = "959371998";
        mEmailAsegurado = "Siniestro: ";
        // OJO SE PUEDE CONIDERAR MONEDA DE SUMA RECLAMADA AQUIIIIIIII
        mSumaReclamada  = 0.0f;

        //TASACION
        mDireccionTasacion = "Pasaje juan 23, Numero 154 Dpto 707";
        mAreaTerrenoTasacion = "Siniestro: ";
        mValorTerrenoTasacion = "Siniestro: ";
        mValorReconstruccionEdificacionTasacion = "Siniestro: ";;
        mValorComercialEdificacion = "Siniestro: ";
        mDescripcionEdificacionTasacion = "Siniestro: ";
        mFechaConstruccionEdificacionTasacion= new Date(0l);
        mFechaRealizacionTasacion= new Date(0l);

        //CONTACTO ASEGURADO
        mContactoAsegurado= "BETOOO";


        //PPS | PIURA - SULLANA | SINIESTRO: 13434376 | BCP / CALLE ACHA YULI YDANIA | POLIZA PV02 : 14466153

        //ASEGURADORA SHORT
        //DEPARTAMENTO SINIESTRO
        //ZONA SINIESTRO
        //NUMERO DE SINIESTRO
        //NOMBRE ASEGURADO
        //TIPO POLIZA
        //NUMERO POLIZA


        UpdateHeadString();

        mTag="TAG1 TAG2 TAG3";

    }

    // OJO DEBERIA SE PRIVATE MUUUCHO OJO SEGURIDAD IMPORTANTE
    public void UpdateHeadString() {
        mHeadString =
                mAseguradoraShort + " | " +
                        mDepartamentoSiniestro + " | " +
                        mZonaSiniestro + " | Siniestro: " +
                        mNumeroSiniestro + "  | Asegurado: " +
                        mNombreAsegurado +" | Poliza " +
                        mTipoPoliza +" " + mNumeroPoliza;
    }

    ////////
    public UUID getId() {
        return mId;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    /// GET HEADSTRING

    public String getHeadString() {

        UpdateHeadString();
        return mHeadString;
    }

    //GET PHOTO FILENAME
    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }





    public void setHeadString(String headString) {
        mHeadString = headString;
    }

    public String getTipoPoliza() {
        return mTipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        mTipoPoliza = tipoPoliza;
    }

    public String getNumeroPoliza() {
        return mNumeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        mNumeroPoliza = numeroPoliza;
    }

    public String getAseguradoraShort() {
        return mAseguradoraShort;
    }

    public void setAseguradoraShort(String aseguradoraShort) {
        mAseguradoraShort = aseguradoraShort;
    }

    public Float getSumaAseguradaEdificacion() {
        return mSumaAseguradaEdificacion;
    }

    public void setSumaAseguradaEdificacion(Float sumaAseguradaEdificacion) {
        mSumaAseguradaEdificacion = sumaAseguradaEdificacion;
    }

    public Float getSumaAseguradaContenido() {
        return mSumaAseguradaContenido;
    }

    public void setSumaAseguradaContenido(Float sumaAseguradaContenido) {
        mSumaAseguradaContenido = sumaAseguradaContenido;
    }

    public String getDireccionAsegurada() {
        return mDireccionAsegurada;
    }

    public void setDireccionAsegurada(String direccionAsegurada) {
        mDireccionAsegurada = direccionAsegurada;
    }

    public String getNumeroSiniestro() {
        return mNumeroSiniestro;
    }

    public void setNumeroSiniestro(String numeroSiniestro) {
        mNumeroSiniestro = numeroSiniestro;
    }

    public String getLongitudUbicacion() {
        return mLongitudUbicacion;
    }

    public void setLongitudUbicacion(String longitudUbicacion) {
        mLongitudUbicacion = longitudUbicacion;
    }

    public String getLatitudUbicacion() {
        return mLatitudUbicacion;
    }

    public void setLatitudUbicacion(String latitudUbicacion) {
        mLatitudUbicacion = latitudUbicacion;
    }

    public String getNombreAsegurado() {

        UpdateHeadString();
        return mNombreAsegurado;
    }

    public void setNombreAsegurado(String nombreAsegurado) {
        mNombreAsegurado = nombreAsegurado;
    }

    public String getDNIAsegurado() {
        return mDNIAsegurado;
    }

    public void setDNIAsegurado(String DNIAsegurado) {
        mDNIAsegurado = DNIAsegurado;
    }

    public String getCelularAsegurado() {
        return mCelularAsegurado;
    }

    public void setCelularAsegurado(String celularAsegurado) {
        mCelularAsegurado = celularAsegurado;
    }

    public String getEmailAsegurado() {
        return mEmailAsegurado;
    }

    public void setEmailAsegurado(String emailAsegurado) {
        mEmailAsegurado = emailAsegurado;
    }

    public Float getSumaReclamada() {
        return mSumaReclamada;
    }

    public void setSumaReclamada(Float sumaReclamada) {
        mSumaReclamada = sumaReclamada;
    }

    public String getDireccionTasacion() {
        return mDireccionTasacion;
    }

    public void setDireccionTasacion(String direccionTasacion) {
        mDireccionTasacion = direccionTasacion;
    }

    public String getAreaTerrenoTasacion() {
        return mAreaTerrenoTasacion;
    }

    public void setAreaTerrenoTasacion(String areaTerrenoTasacion) {
        mAreaTerrenoTasacion = areaTerrenoTasacion;
    }

    public String getValorTerrenoTasacion() {
        return mValorTerrenoTasacion;
    }

    public void setValorTerrenoTasacion(String valorTerrenoTasacion) {
        mValorTerrenoTasacion = valorTerrenoTasacion;
    }

    public String getValorReconstruccionEdificacionTasacion() {
        return mValorReconstruccionEdificacionTasacion;
    }

    public void setValorReconstruccionEdificacionTasacion(String valorReconstruccionEdificacionTasacion) {
        mValorReconstruccionEdificacionTasacion = valorReconstruccionEdificacionTasacion;
    }

    public String getValorComercialEdificacion() {
        return mValorComercialEdificacion;
    }

    public void setValorComercialEdificacion(String valorComercialEdificacion) {
        mValorComercialEdificacion = valorComercialEdificacion;
    }

    public String getDescripcionEdificacionTasacion() {
        return mDescripcionEdificacionTasacion;
    }

    public void setDescripcionEdificacionTasacion(String descripcionEdificacionTasacion) {
        mDescripcionEdificacionTasacion = descripcionEdificacionTasacion;
    }

    public Date getFechaConstruccionEdificacionTasacion() {
        return mFechaConstruccionEdificacionTasacion;
    }

    public void setFechaConstruccionEdificacionTasacion(Date fechaConstruccionEdificacionTasacion) {
        mFechaConstruccionEdificacionTasacion = fechaConstruccionEdificacionTasacion;
    }

    public Date getFechaRealizacionTasacion() {
        return mFechaRealizacionTasacion;
    }

    public void setFechaRealizacionTasacion(Date fechaRealizacionTasacion) {
        mFechaRealizacionTasacion = fechaRealizacionTasacion;
    }

    public Date getFechaSiniestro() {
        return mFechaSiniestro;
    }

    public void setFechaSiniestro(Date fechaSiniestro) {
        mFechaSiniestro = fechaSiniestro;
    }

    public Date getFechaAvisoAAseguradora() {
        return mFechaAvisoAAseguradora;
    }

    public void setFechaAvisoAAseguradora(Date fechaAvisoAAseguradora) {
        mFechaAvisoAAseguradora = fechaAvisoAAseguradora;
    }

    public Date getFechaEncargoAAjustador() {
        return mFechaEncargoAAjustador;
    }

    public void setFechaEncargoAAjustador(Date fechaEncargoAAjustador) {
        mFechaEncargoAAjustador = fechaEncargoAAjustador;
    }

    public Date getFechaSolicDocs() {
        return mFechaSolicDocs;
    }

    public void setFechaSolicDocs(Date fechaSolicDocs) {
        mFechaSolicDocs = fechaSolicDocs;
    }

    public Date getFechaDocComp() {
        return mFechaDocComp;
    }

    public void setFechaDocComp(Date fechaDocComp) {
        mFechaDocComp = fechaDocComp;
    }

    public Date getFechaCoordInspeccion() {
        return mFechaCoordInspeccion;
    }

    public void setFechaCoordInspeccion(Date fechaCoordInspeccion) {
        mFechaCoordInspeccion = fechaCoordInspeccion;
    }

    public Date getFechaRealizacionInspeccion() {
        return mFechaRealizacionInspeccion;
    }

    public void setFechaRealizacionInspeccion(Date fechaRealizacionInspeccion) {
        mFechaRealizacionInspeccion = fechaRealizacionInspeccion;
    }

    public Date getFechaEntregaLiqAAsegurado() {
        return mFechaEntregaLiqAAsegurado;
    }

    public void setFechaEntregaLiqAAsegurado(Date fechaEntregaLiqAAsegurado) {
        mFechaEntregaLiqAAsegurado = fechaEntregaLiqAAsegurado;
    }

    public Date getFechaDevolucionLiqAAjustador() {
        return mFechaDevolucionLiqAAjustador;
    }

    public void setFechaDevolucionLiqAAjustador(Date fechaDevolucionLiqAAjustador) {
        mFechaDevolucionLiqAAjustador = fechaDevolucionLiqAAjustador;
    }

    public Date getFechaEnvioLiqAAseguradora() {
        return mFechaEnvioLiqAAseguradora;
    }

    public void setFechaEnvioLiqAAseguradora(Date fechaEnvioLiqAAseguradora) {
        mFechaEnvioLiqAAseguradora = fechaEnvioLiqAAseguradora;
    }

    public String getMonedaSumaAseguradaEdificacion() {
        return mMonedaSumaAseguradaEdificacion;
    }

    public void setMonedaSumaAseguradaEdificacion(String monedaSumaAseguradaEdificacion) {
        mMonedaSumaAseguradaEdificacion = monedaSumaAseguradaEdificacion;
    }

    public String getMonedaSumaAseguradaContenido() {
        return mMonedaSumaAseguradaContenido;
    }

    public void setMonedaSumaAseguradaContenido(String monedaSumaAseguradaContenido) {
        mMonedaSumaAseguradaContenido = monedaSumaAseguradaContenido;
    }

    public String getContactoAsegurado() {
        return mContactoAsegurado;
    }

    public void setContactoAsegurado(String contactoAsegurado) {
        mContactoAsegurado = contactoAsegurado;
    }

}
