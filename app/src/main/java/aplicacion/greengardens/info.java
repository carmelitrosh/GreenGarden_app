package aplicacion.greengardens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class info extends AppCompatActivity {

    Button regresarPrin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Metodo para regresar al activity principal
        regresarPrin =(Button) findViewById(R.id.regresarPrin);
        regresarPrin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent siguiente = new Intent(info.this, MainActivity.class);
                startActivity(siguiente);
            }
        });
    }
}
