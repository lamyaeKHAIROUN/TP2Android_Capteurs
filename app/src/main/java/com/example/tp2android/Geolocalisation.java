package com.example.tp2android;

import java.io.IOException;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.tp2android.R;

public class Geolocalisation extends Activity implements OnClickListener, LocationListener {
    private LocationManager lManager;
    private Location location;
    private String choix_source = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.geolocalisation);

        //On récupère le service de localisation
        lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Initialisation de l'écran
        reinitialisationEcran();

        findViewById(R.id.choix_source).setOnClickListener(this);
        findViewById(R.id.obtenir_position).setOnClickListener(this);
        findViewById(R.id.afficherAdresse).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choix_source:
                choisirSource();
                break;
            case R.id.obtenir_position:
                obtenirPosition();
                break;
            case R.id.afficherAdresse:
                afficherAdresse();
                break;
            default:
                break;
        }
    }

    private void reinitialisationEcran() {
        ((TextView) findViewById(R.id.latitude)).setText("0.0");
        ((TextView) findViewById(R.id.longitude)).setText("0.0");
        ((TextView) findViewById(R.id.altitude)).setText("0.0");
        ((TextView) findViewById(R.id.adresse)).setText("");

        findViewById(R.id.obtenir_position).setEnabled(false);
        findViewById(R.id.afficherAdresse).setEnabled(false);
    }

    private void choisirSource() {
        reinitialisationEcran();
        List<String> providers = lManager.getProviders(true);
        final String[] sources = new String[providers.size()];
        int i = 0;
        //on stock le nom de ces source dans un tableau de string
        for (String provider : providers)
            sources[i++] = provider;

        //On affiche la liste des sources dans une fenêtre de dialog
        new AlertDialog.Builder(Geolocalisation.this)
                .setItems(sources, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        findViewById(R.id.obtenir_position).setEnabled(true);
                        //on stock le choix de la source choisi
                        choix_source = sources[i];
                        //on ajoute dans la barre de titre de l'application le nom de la source utilisé
                        setTitle(String.format("%s - %s", getString(R.string.app_name),
                                choix_source));
                    }
                })
                .create().show();
    }

    private void obtenirPosition() {


        //On demande au service de localisation de nous notifier tout changement de position
        //sur la source (le provider) choisie, toute les 30 secondes (30000millisecondes).
        //Le paramètre this spécifie que notre classe implémente LocationListener et recevra
        //les notifications.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lManager.requestLocationUpdates(choix_source, 30000, 0, this);
    }

    private void afficherLocation() {
        ((TextView)findViewById(R.id.latitude)).setText(String.valueOf(location.getLatitude()));
        ((TextView)findViewById(R.id.longitude)).setText(String.valueOf(location.getLongitude()));
        ((TextView)findViewById(R.id.altitude)).setText(String.valueOf(location.getAltitude()));
    }

    private void afficherAdresse() {


        //Le geocoder permet de récupérer ou chercher des adresses
        Geocoder geo = new Geocoder(Geolocalisation.this);
        try {
            List
                    <Address> adresses = geo.getFromLocation(location.getLatitude(),
                    location.getLongitude(),1);

            if(adresses != null && adresses.size() == 1){
                Address adresse = adresses.get(0);
                ((TextView)findViewById(R.id.adresse)).setText(String.format("%s, %s %s",
                        adresse.getAddressLine(0),
                        adresse.getPostalCode(),
                        adresse.getLocality()));
            }
            else {
                ((TextView)findViewById(R.id.adresse)).setText("L'adresse n'a pu être déterminée");
            }
        } catch (IOException e) {
            e.printStackTrace();
            ((TextView)findViewById(R.id.adresse)).setText("L'adresse n'a pu être déterminée");
        }

    }

    public void onLocationChanged(Location location) {
        Log.i(" géolocalisation", "La position a changé.");

        findViewById(R.id.afficherAdresse).setEnabled(true);
        this.location = location;
        afficherLocation();
        lManager.removeUpdates(this);
    }

    public void onProviderDisabled(String provider) {
        Log.i("géolocalisation", "La source a été désactivé");
        Toast.makeText(Geolocalisation.this,
                String.format("La source \"%s\" a été désactivé", provider),
                Toast.LENGTH_SHORT).show();
        lManager.removeUpdates(this);

    }

    public void onProviderEnabled(String provider) {
        Log.i("géolocalisation", "La source a été activé.");
    }
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("géolocalisation", "Le statut de la source a changé.");
    }

}