package aplicacion.greengardens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    ImageButton siguienteIlum; //Variable para redireccionar a la activity iluminacion.
    ImageButton siguienteVenti; //Variable para redireccionar a la activity ventilador.
    ImageButton siguientePuerta; //Variable para redireccionar a la activity puerta.
    ImageButton siguienteRiego; //Variable para redireccionar a la activity riego.
    ImageButton siguienteInfo; //Variable para redireccionar a la activity informacion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Metodo para redireccionar al activity informacion
        siguienteInfo = (ImageButton) findViewById(R.id.btnSiguienteInfo);
        siguienteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguienteinf = new Intent(MainActivity.this, info.class);
                startActivity(siguienteinf);
            }
        });


        //Metodo para redireccionar al activity iluminación
        siguienteIlum = (ImageButton) findViewById(R.id.btnSiguienteIlum);
        siguienteIlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguienteilu = new Intent(MainActivity.this, iluminacion.class);
                startActivity(siguienteilu);
            }
        });

        //Metodo para redireccionar al activity puerta
        siguientePuerta = (ImageButton) findViewById(R.id.btnSiguientePuerta);
        siguientePuerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguientepuerta = new Intent(MainActivity.this, puerta.class);
                startActivity(siguientepuerta);
            }
        });


        //Metodo para redireccionar al activity riego
        siguienteRiego =(ImageButton)findViewById(R.id.btnSiguienteRiego);
        siguienteRiego.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent siguienteriego = new Intent (MainActivity.this, riego.class);
                startActivity(siguienteriego);
            }
        });

        //Metodo para redireccionar al activity ventilador
        siguienteVenti = (ImageButton) findViewById(R.id.btnSiguienteVenti);
        siguienteVenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguienteVenti.setVisibility(GONE);
                FragmentManager fm = getSupportFragmentManager();
                FanFragment fragment = new FanFragment();
                fm.beginTransaction().replace(R.id.container, fragment).commit();
            }


        });

    }
}
