package com.benasque.quista.jr.indicelee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.benasque.quista.jr.indicelee.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    //<editor-fold desc="Menu">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle presses on the action bar items
        Toast toast = Toast.makeText(MainActivity.this, R.string.AcercaJoseRa, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        return false;
    }
    //</editor-fold>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        String mifecha = ("1/10/2030");
        clsControlFecha.comprueba_fecha(this, mifecha);

        //InicializaControles();

        binding.btnaceptar.setOnClickListener(v -> {
            int miR = contador(binding.checkBoxC.isChecked(), binding.checkBoxCI.isChecked(), binding.checkBoxIC.isChecked(),
                    binding.checkBoxACV.isChecked(), binding.checkBoxInsulina.isChecked(), binding.checkBoxCreatinina.isChecked());
            String miSR = Riesgo(miR);
            String message = getString(R.string.eventos) + " " + miSR;
            //String message = "Riesgo de eventos";
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
            alertdialog.setTitle(R.string.tituloAlerta);
            alertdialog.setIcon(R.mipmap.ic_launcher);
            alertdialog.setMessage(message);
            alertdialog.setPositiveButton(R.string.aceptar, (dialog, which) -> {
                dialog.cancel();
                //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
            });
            alertdialog.create().show();
        });

       binding.btnlimpiar.setOnClickListener(v -> {
           int miR = contador(binding.checkBoxC.isChecked(), binding.checkBoxCI.isChecked(), binding.checkBoxIC.isChecked(),
                   binding.checkBoxACV.isChecked(), binding.checkBoxInsulina.isChecked(), binding.checkBoxCreatinina.isChecked());
           if (miR>0){
               binding.checkBoxC.setChecked(false);
               binding.checkBoxCI.setChecked(false);
               binding.checkBoxIC.setChecked(false);
               binding.checkBoxACV.setChecked(false);
               binding.checkBoxInsulina.setChecked(false);
               binding.checkBoxCreatinina.setChecked(false);
           }
       });
    }

    private int contador(boolean c, boolean ci,boolean ic,boolean acv, boolean I, boolean Cr ){
        int miCuenta =0;
        if (c) miCuenta = miCuenta+1;
        if (ci) miCuenta = miCuenta+1;
        if (ic) miCuenta = miCuenta+1;
        if (acv) miCuenta = miCuenta+1;
        if (I) miCuenta = miCuenta+1;
        if (Cr) miCuenta = miCuenta+1;

        return miCuenta;
    }

    private String Riesgo(int Rie){
        String miR;
        switch (Rie){
            case 0: miR = getString(R.string.claseI);
                break;
            case 1: miR = getString(R.string.claseII);
                break;
            case 2: miR = getString(R.string.claseIII);
                break;
            default: miR = getString(R.string.claseIV);
                break;
        }
        return miR;
    }

}
