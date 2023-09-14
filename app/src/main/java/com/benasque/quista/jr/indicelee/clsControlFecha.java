package com.benasque.quista.jr.indicelee;

import android.app.AlertDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by JR on 01/10/2015.
 * **********************************************************************************************
 * Esta clase tiene un metodo compartido que permite comprobar si un
 * programa está pasado de fecha respecto a una fecha concreta y la fecha actual
 * ********************************************************************************************
 */
class clsControlFecha {

    private static final String CcformatoFecha = "dd/MM/yyyy";
    private static final String Cctexto1 ="Atención";
    private static final String Cctexto2 ="Programa pasado de fecha\nContactar con el programador";

    private static final String CcAceptar = "Aceptar";


    static void comprueba_fecha(final Context ctx, String fecha){


        SimpleDateFormat df = new SimpleDateFormat(CcformatoFecha, Locale.getDefault());
        Date today = Calendar.getInstance().getTime();
        Date mifecha = Calendar.getInstance().getTime();
        try {

            mifecha = df.parse(fecha);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(today.after(mifecha)){

            AlertDialog.Builder miAlerta = new AlertDialog.Builder(ctx);
            miAlerta.setTitle(Cctexto1)
                .setMessage(Cctexto2)
                .setIcon(R.mipmap.ic_launcher);
            miAlerta.setPositiveButton(CcAceptar, (dialog, which) -> {

                ((MainActivity)ctx).finish();
                dialog.cancel();
            });
            miAlerta.show();
        }
    }
}
