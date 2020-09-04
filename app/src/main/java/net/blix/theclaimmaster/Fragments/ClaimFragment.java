package net.blix.theclaimmaster.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;



import net.blix.theclaimmaster.ClaimLab;
import net.blix.theclaimmaster.PictureUtils;
import net.blix.theclaimmaster.R;
import net.blix.theclaimmaster.Beans.Claim;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;

public class ClaimFragment extends Fragment {

    public static final String ARG_CLAIM_ID = "claim_id";
    private static final String DIALOG_DATE = "Dialog Date";

    private static final int REQUEST_DATE = 0;

    //BUTTON GET FROM CONTACTS
    private static final int REQUEST_CONTACT= 1;

    //CAMERA INTENT RELATED
    private static final int REQUEST_PHOTO= 2;

    //GOOGLE API CLIENT
    private GoogleApiClient mClient;


    private Claim mClaim;

    //EL ARCHIVO DE LA FOTO
    private File mPhotoFile;

    private ImageButton mCallButton;

    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    //OBJETOS DE LA CLASE CRIME
    private UUID mId;
    private TextView mTag;
    private TextView mHeadString;

    //POLIZA
    private TextView mTipoPoliza;
    private TextView mNumeroPoliza;
    private TextView mSumaAseguradaEdificacion;
    private TextView mSumaAseguradaContenido;
    private TextView mDireccionAsegurada;

    //SINIESTRO
    private TextView mNumeroSiniestro;
    private TextView mLongitudUbicacion;
    private TextView mLatitudUbicacion;

    //ASEGURADO
    private TextView mNombreAsegurado;
    private TextView mDNIAsegurado;
    private TextView mCelularAsegurado;
    private TextView mEmailAsegurado;
    private TextView mSumaReclamada;

    //TASACION
    private EditText mDireccionTasacion;
    private TextView mAreaTerrenoTasacion;
    private TextView mValorTerrenoTasacion;
    private TextView mValorReconstruccionEdificacionTasacion;
    private TextView mValorComercialEdificacion;
    private TextView mDescripcionEdificacionTasacion;
    private TextView mFechaConstruccionInmuebleTasacion;
    private TextView mFechaRealizacionTasacion;

    //SEND CLAIM REPORT BUTTON
    private Button mReportButton;

    //GET CONTACT BUTTON
    private Button mGetFromContactsButton;

    //GET GPS BUTTON
    private Button mButtonGetGps;



    //GET CLAIM REPORT
    private String getClaimReport(){
        String solvedString = null;
        //if(mClaim){
            solvedString = getString(R.string.claim_report_solved);
        //}else{
        //    solvedString = getString(R.string.claim_report_unsolved);
        //}

        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat, mClaim.getFechaSiniestro()).toString();

        String suspect = mClaim.getNombreAsegurado();

        String report = getString(R.string.claim_report, mClaim.getHeadString(), dateString, suspect);


        return report;

    }

    private void updatePhotoView(){
        if(mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        } else{
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }



    public static ClaimFragment newInstance(UUID claimId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLAIM_ID, claimId);

        ClaimFragment fragment = new ClaimFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //CONEXION DEL CLIENTE DE GOOGLE API
    @Override
    public void onStart() {
        super.onStart();

        getActivity().invalidateOptionsMenu();
        mClient.connect();
    }

    //DESCONEXION DEL CLIENTE DE GOOGLE API
    @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mClaim = new Claim();
        //UUID claimId = (UUID) getActivity().getIntent().getSerializableExtra(ClaimActivity.EXTRA_CLAIM_ID);
        UUID claimId = (UUID) getArguments().getSerializable(ARG_CLAIM_ID);
        mClaim = ClaimLab.get(getActivity()).getClaim(claimId);
        //FOTO FILE INIT
        mPhotoFile = ClaimLab.get(getActivity()).getPhotoFile(mClaim);
        //INICIALIZANDO EL GOOGLE API CLIENT
        mClient = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API).build();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_claim, container,false);

        //TECLADO OCULTO POR DEFECTO
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mCallButton = (ImageButton) v.findViewById(R.id.imageButtonCall);

        mTag = (TextView) v.findViewById(R.id.textViewTags);
        mTag.setText(mClaim.getTag());

        mHeadString = (TextView) v.findViewById(R.id.textViewHeadString);
        mHeadString.setText(mClaim.getHeadString());


        mTipoPoliza = (TextView) v.findViewById(R.id.textVewTipoPoliza);
        mNumeroPoliza = (TextView) v.findViewById(R.id.textViewNumeroPoliza);


        mSumaAseguradaEdificacion= (TextView) v.findViewById(R.id.textViewLabelSumaAseguradaEdificacion);
        mSumaAseguradaContenido= (TextView) v.findViewById(R.id.textViewLabelSumaAseguradaContenido);
        mDireccionAsegurada= (TextView) v.findViewById(R.id.textViewDireccion);



        //SINIESTRO
        mNumeroSiniestro= (TextView) v.findViewById(R.id.textViewSiniestro);
        mNumeroSiniestro.setText(mClaim.getNumeroSiniestro());


        mLongitudUbicacion= (TextView) v.findViewById(R.id.textViewGPSLongitud);
        mLatitudUbicacion= (TextView) v.findViewById(R.id.textViewGPSLatitud);


        //ASEGURADO
        mNombreAsegurado= (TextView) v.findViewById(R.id.textViewNombreAsegurado);
        mNombreAsegurado.setText(mClaim.getNombreAsegurado());

        mDNIAsegurado= (TextView) v.findViewById(R.id.editTextDNI);

        mCelularAsegurado= (TextView) v.findViewById(R.id.textViewCelular);
        mCelularAsegurado.setText(mClaim.getCelularAsegurado());

        mEmailAsegurado= (TextView) v.findViewById(R.id.editTextEmail);
        //mSumaReclamada= (TextView) v.findViewById(R.;

        //TASACION
        //mDireccionTasacion= (EditText) v.findViewById(R.id.textViewLabelDireccionTasacion;
        mAreaTerrenoTasacion = (TextView) v.findViewById(R.id.textViewLabelAreaTerrenoTasacion);;
        mValorTerrenoTasacion = (TextView) v.findViewById(R.id.textViewLabelValorTerrenoTasacion);;
        mValorReconstruccionEdificacionTasacion = (TextView) v.findViewById(R.id.textViewLabelValorReconstruccionEdificacionTasacion);;
        mValorComercialEdificacion= (TextView) v.findViewById(R.id.textViewLabelValorComercialEdificacionTasacion);;
        mDescripcionEdificacionTasacion= (TextView) v.findViewById(R.id.textViewLabelDescripcionEdificacionTasacion);;
        mFechaConstruccionInmuebleTasacion=(TextView) v.findViewById(R.id.textViewLabelFechaConstruccionEdificacionTasacion);;
        mFechaRealizacionTasacion= (TextView) v.findViewById(R.id.textViewLabelFechaRealizacionTasacion);
        mClaim.UpdateHeadString();

        //INICIALIZACION BOTON Y CAMARA
        mPhotoButton = (ImageButton) v.findViewById(R.id.claim_camera);
        mPhotoView = (ImageView) v.findViewById(R.id.claim_photo);
        //CALL UPDATEPHOTOVIEW
        updatePhotoView();

        //LISTENER DEL BOTON DE LLAMADA AL ASEGURADO
        mCallButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+mClaim.getCelularAsegurado()));
                startActivity(callIntent);
            }
        });

        //LISTENER DEL BOTON DE ENVIO DE REPORTE DE CLAIM - SEND BASIC REPORT
        mReportButton = (Button) v.findViewById(R.id.btnSendBasicReport) ;
        mReportButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getClaimReport());
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.claim_report_subject));

                //CREACION DEL CHOOSER DE SEND REPORT
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
            }
        });

        //LISTENER DEL BOTON OBTENER CONTACTO MUCHO OJO A ESTE CONTACTO
        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mGetFromContactsButton = (Button) v.findViewById(R.id.btnAddFromContacts);
        mGetFromContactsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });
        if(mClaim.getNombreAsegurado() != null){
            mGetFromContactsButton.setText(mClaim.getNombreAsegurado());
        }

        //LISTENER DEL BOTON GPS
        mButtonGetGps = (Button) v.findViewById(R.id.btnGetGPS);



        mNumeroSiniestro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClaim.setNumeroSiniestro(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNombreAsegurado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClaim.setNombreAsegurado(s.toString());
                mClaim.UpdateHeadString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        final FloatingActionMenu mFloatingActionMenu = (FloatingActionMenu) v.findViewById(R.id.floatingActionMenu);
        mFloatingActionMenu.setClosedOnTouchOutside(true);

        final FloatingActionButton mItem1 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem1);
        final FloatingActionButton mItem2 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem2);
        final FloatingActionButton mItem3 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem3);
        final FloatingActionButton mItem4 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem4);
        final FloatingActionButton mItem5 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem5);
        final FloatingActionButton mItem6 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem6);
        final FloatingActionButton mItem7 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem7);
        final FloatingActionButton mItem8 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem8);
        final FloatingActionButton mItem9 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem9);
        final FloatingActionButton mItem10 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem10);



        mFloatingActionMenu.setOnMenuButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mFloatingActionMenu.isOpened()) {

                    //Intent i = new Intent(getActivity(), SecondActivity.class);
                    //startActivity(i);

                    mFloatingActionMenu.close(true);
                }
                else{
                    ////TRABAJO DE CLACULO DE FECHAS

                    updateDateDeadlines();

                    mFloatingActionMenu.open(true);

                }

            }
        });



        mItem1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaSiniestro Option 1", Toast.LENGTH_SHORT).show();

                //COULD ALSO GO TO AN INTENT OR PERFORM WHATEVER ACTION YOU WANT
                //TO GO TO ANOTHER ACTIVITY IT WOULD BE SOMETHING LIKE
                //Intent goToActivity = new Intent(getApplicationContext(), NameOfActivity.class);
                //startActivity(goToActivity);
                FragmentManager manager = getFragmentManager();
                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaSiniestro(),"FechaSiniestro");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaAvisoAAseguradora Option 2", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaAvisoAAseguradora(),"FechaAvisoAAseguradora");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaEncargoAAjustador Option 3", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaEncargoAAjustador(),"FechaEncargoAAjustador");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaSolicDocs Option 4", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaSolicDocs(), "FechaSolicDocs");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaDocComp Option 5", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaDocComp(), "FechaDocComp");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaCoordInspeccion Option 6", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaCoordInspeccion(), "FechaCoordInspeccion");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaRealizacionInspeccion Option 7", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaRealizacionInspeccion(), "FechaRealizacionInspeccion");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });



        mItem8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaEntregaLiqAAsegurado Option 8", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaEntregaLiqAAsegurado(), "FechaEntregaLiqAAsegurado");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mItem9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaDevolucionLiqAAjustador Option 9", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaDevolucionLiqAAjustador(),"FechaDevolucionLiqAAjustador");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mItem10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "getFechaEnvioLiqAAseguradora Option 10", Toast.LENGTH_SHORT).show();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mClaim.getFechaEnvioLiqAAseguradora(), "FechaEnvioLiqAAseguradora");
                dialog.setTargetFragment(ClaimFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        //CHECKING THAT THERE IS CONTACTS APP INSTALLED
        PackageManager packageManager = getActivity().getPackageManager();
        if(packageManager.resolveActivity(pickContact,PackageManager.MATCH_DEFAULT_ONLY) == null){
            mGetFromContactsButton.setEnabled(false);
        }

        //FIRING THE CAMERA INTENT
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);
        mPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(), "net.blix.theclaimmaster.file-provider", mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                List<ResolveInfo> cameraActivities = getActivity().getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for(ResolveInfo activity: cameraActivities){
                    getActivity().grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });


        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        ClaimLab.get(getActivity()).updateClaim(mClaim);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            return;
        }else if (requestCode == REQUEST_PHOTO){
            Uri uri = FileProvider.getUriForFile(getActivity(), "net.blix.theclaimmaster.file-provider", mPhotoFile);
            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            String date_typ = (String) data.getStringExtra(DatePickerFragment.EXTRA_DATE_TYPE);
            Toast.makeText(getActivity().getApplicationContext(), date_typ, Toast.LENGTH_SHORT).show();


            switch (date_typ){
                case "FechaSiniestro":
                    mClaim.setFechaSiniestro(date);
                    break;
                case "FechaAvisoAAseguradora":
                    mClaim.setFechaAvisoAAseguradora(date);
                    break;
                case "FechaEncargoAAjustador":
                    mClaim.setFechaEncargoAAjustador(date);
                    break;
                case "FechaSolicDocs":
                    mClaim.setFechaSolicDocs(date);
                    break;
                case "FechaDocComp":
                    mClaim.setFechaDocComp(date);
                    break;
                case "FechaCoordInspeccion":
                    mClaim.setFechaCoordInspeccion(date);
                    break;
                case "FechaRealizacionInspeccion":
                    mClaim.setFechaRealizacionInspeccion(date);
                    break;
                case "FechaEntregaLiqAAsegurado":
                    mClaim.setFechaEntregaLiqAAsegurado(date);
                    break;
                case "FechaDevolucionLiqAAjustador":
                    mClaim.setFechaDevolucionLiqAAjustador(date);
                    break;
                case "FechaEnvioLiqAAseguradora":
                    mClaim.setFechaEnvioLiqAAseguradora(date);
                    break;
            }

            //mDateButtton.setText(mClaim.getDate().toString());

            updateDateDeadlines();
        }else if(requestCode == REQUEST_CONTACT && data != null){
            Uri contactUri =data.getData();
            //SPECIFY WHICH FIELDS YOU WANT YOUR QUERY TO RETURN VALUES FOR
            String[] queryFields = new String []{
                ContactsContract.Contacts.DISPLAY_NAME
            };
            //PERFORM YOUR QUERY - THE CONTACT URI IS LIKE A "WHERE" CLAUSE HERE
            Cursor c = getActivity().getContentResolver().query(contactUri, queryFields, null, null, null);
            try{
                //DOUBLE CHECK THAT YOU ACTUALLY GIT RESULTS
                if(c.getCount() == 0){
                    return;
                }
                //PULL OUT THE FIRST COLUMN OF THE FIRST ROW OF DATA THAT IS YOUR CONTACTS NAME
                c.moveToFirst();
                String suspect = c.getString(0);
                mClaim.setNombreAsegurado(suspect);
                mGetFromContactsButton.setText(suspect);
            }finally {
                c.close();
            }
        }
    }

    private void updateDateDeadlines() {
        View v = getView();
        final FloatingActionMenu mFloatingActionMenu = (FloatingActionMenu) v.findViewById(R.id.floatingActionMenu);

        final FloatingActionButton mItem1 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem1);
        final FloatingActionButton mItem2 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem2);
        final FloatingActionButton mItem3 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem3);
        final FloatingActionButton mItem4 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem4);
        final FloatingActionButton mItem5 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem5);
        final FloatingActionButton mItem6 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem6);
        final FloatingActionButton mItem7 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem7);
        final FloatingActionButton mItem8 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem8);
        final FloatingActionButton mItem9 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem9);
        final FloatingActionButton mItem10 = (FloatingActionButton) v.findViewById(R.id.floatingActionItem10);

        Date nowDate = Calendar.getInstance().getTime();
        Date xDate = mClaim.getFechaSiniestro();
        long diff = nowDate.getTime() - xDate.getTime();
        long xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


        if((mClaim.getFechaSiniestro().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaSiniestro();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem1.setLabelText(getText(R.string.fecha01) +" \nHan transcurrido " + xDays + " dias desde la fecha de ocurrencia del siniestro.");
        }

        if((mClaim.getFechaAvisoAAseguradora().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaAvisoAAseguradora();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem2.setLabelText(getText(R.string.fecha02) +" \nEl Asegurado dió aviso después de " + xDays+ " dias");
        }


        if((mClaim.getFechaEncargoAAjustador().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaEncargoAAjustador();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem3.setLabelText(getText(R.string.fecha03) +" \nSe encargó siniestro de hace " +xDays+ " dias");
        }

        if((mClaim.getFechaSolicDocs().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaSolicDocs();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem4.setLabelText(getText(R.string.fecha04) +" \n¿¿¿???");
        }

        if((mClaim.getFechaDocComp().getTime())-(new Date(0L).getTime())!=0)
        {

            xDate = mClaim.getFechaDocComp();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem5.setLabelText(getText(R.string.fecha05) +" \nEl Asegurado entregó documentos despues de " + xDays + " dias");
        }

        if((mClaim.getFechaCoordInspeccion().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaCoordInspeccion();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem6.setLabelText(getText(R.string.fecha06) +" \nSe coordinó despues de " + xDays + " dias");
        }

        if((mClaim.getFechaRealizacionInspeccion().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaRealizacionInspeccion();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem7.setLabelText(getText(R.string.fecha07) +" \nSe inspecciónó después de " + xDays + " dias de Enrago de siniestro");
        }

        if((mClaim.getFechaEntregaLiqAAsegurado().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaEntregaLiqAAsegurado();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem8.setLabelText(getText(R.string.fecha08) +" \nSe entregó liquidación a asegurado despues de " + xDays + " dias");
        }

        if((mClaim.getFechaDevolucionLiqAAjustador().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaDevolucionLiqAAjustador();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem9.setLabelText(getText(R.string.fecha09) + " \nAsegurado firmó liquidación después de " + xDays + " dias");
        }

        if((mClaim.getFechaEnvioLiqAAseguradora().getTime())-(new Date(0L).getTime())!=0){

            xDate = mClaim.getFechaEnvioLiqAAseguradora();
            diff = nowDate.getTime() - xDate.getTime();
            xDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            mItem10.setLabelText(getText(R.string.fecha10) +" \nSe entregó liquidación a Aseguradora después de " + xDays + " dias de recibida");
        }
    }

}
























