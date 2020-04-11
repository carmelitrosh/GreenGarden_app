package aplicacion.greengardens;

import androidx.appcompat.app.AppCompatActivity;
import me.tankery.lib.circularseekbar.CircularSeekBar;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class puerta extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puerta);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Write a message to the database
        database= FirebaseDatabase.getInstance();
        myRef= database.getReference("puerta");

        Button regresarPrin;

        CircularSeekBar seekBar = (CircularSeekBar) findViewById(R.id.servomotorCircularSeekBar);
        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {

                int data= (int) progress;

                myRef.setValue(data);
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        //Metodo para regresar al activity principal
        regresarPrin =(Button) findViewById(R.id.regresarPrin);
        regresarPrin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent siguiente = new Intent(puerta.this, MainActivity.class);
                startActivity(siguiente);
            }
        });

    }
}
