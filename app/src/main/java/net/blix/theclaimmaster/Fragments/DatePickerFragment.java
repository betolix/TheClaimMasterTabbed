package net.blix.theclaimmaster.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import net.blix.theclaimmaster.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.support.v4.os.LocaleListCompat.create;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "net.blix.theclaimmaster.date";

    public static final String EXTRA_DATE_TYPE = "net.blix.theclaimmaster.date_type";



    private static final String ARG_DATE = "date";

    private static final String ARG_DATE_TYPE = "date_type";


    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(Date date, String date_type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putString(ARG_DATE_TYPE, date_type);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        final String date_type = (String) getArguments().getString(ARG_DATE_TYPE);
        String str = "";

        Calendar calendar = Calendar.getInstance();
        if(date.getTime() - (new Date(0L).getTime())==0 )
            date = calendar.getTime();

        calendar.setTime(date);


        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        switch (date_type){
            case "FechaSiniestro":
                str=getString(R.string.fecha01);
                break;
            case "FechaAvisoAAseguradora":
                str=getString(R.string.fecha02);
                break;
            case "FechaEncargoAAjustador":
                str=getString(R.string.fecha03);
                break;
            case "FechaSolicDocs":
                str=getString(R.string.fecha04);
                break;
            case "FechaDocComp":
                str=getString(R.string.fecha05);
                break;
            case "FechaCoordInspeccion":
                str=getString(R.string.fecha06);
                break;
            case "FechaRealizacionInspeccion":
                str=getString(R.string.fecha07);
                break;
            case "FechaEntregaLiqAAsegurado":
                str=getString(R.string.fecha08);
                break;
            case "FechaDevolucionLiqAAjustador":
                str=getString(R.string.fecha09);
                break;
            case "FechaEnvioLiqAAseguradora":
                str=getString(R.string.fecha10);
                break;
        }


        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle(str)
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(Activity.RESULT_OK, date, date_type);
                    }
                }).create();

        //return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_fecha_siniestro).setPositiveButton(android.R.string.ok, null).create();

        //return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_fecha_siniestro).setPositiveButton(android.R.string.ok, null).create();
    }

    private void sendResult(int resultCode, Date date, String date_type) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        intent.putExtra(EXTRA_DATE_TYPE, date_type);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
