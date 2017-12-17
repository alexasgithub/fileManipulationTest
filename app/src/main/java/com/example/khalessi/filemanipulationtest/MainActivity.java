package com.example.khalessi.filemanipulationtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    private TextView ausgabeText;
    private EditText eingabeText;
    private Button speichern, auslesen;
    public String daten="Eingabetext";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eingabeText = (EditText) findViewById(R.id.eingabeText);

        daten = eingabeText.getText().toString();

        ausgabeText = (TextView) findViewById(R.id.ausgabeText);
        speichern = (Button) findViewById(R.id.speichern);

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile();
            }
        });
        auslesen = (Button) findViewById(R.id.ausgabe);
        auslesen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });

final Button showFileNamePath = (Button) findViewById(R.id.showFileNamePath);
        showFileNamePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileNamePathFktn();
            }
        });


    }

    private void showFileNamePathFktn() {
        String fpath = this.getFilesDir().getAbsolutePath();
        ausgabeText.setText(fpath);

    }

    private void writeToFile (){

        try {
           // daten = "Eingabetext";

            eingabeText = (EditText) findViewById(R.id.eingabeText);

            daten = eingabeText.getText().toString();

            // Stream erzeugen
            FileOutputStream fos = openFileOutput("text.txt", Context.MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fos);

            // In Steam schreiben
            pw.println(daten);
            // Stream schließen
            pw.close();
            ausgabeText.setText("Schreiben in Datei erfolgreich");
        } catch (IOException ex) {
            Log.d("meinApp", ex.getMessage());
            ausgabeText.setText(ex.getMessage());
        }
    }

    private void readFile() {

        try {
            // File-Objekt erzeugen
            File datei = new File
                    (getFilesDir(),"text.txt");
            // Stream erzeugen
            BufferedReader br = new BufferedReader(
                    new FileReader(datei));
            // Aus Stream lesen

            String zeile="";
            String ausgabeString="";
            while ((zeile = br.readLine()) !=null )
            {
                ausgabeString +=zeile;
            }
            ausgabeText.setText(ausgabeString);
            // Stream schließen
            br.close();
        } catch (IOException ex) {
            Log.d("meinApp", ex.getMessage());
            ausgabeText.setText(ex.getMessage());
        }
    }


}
