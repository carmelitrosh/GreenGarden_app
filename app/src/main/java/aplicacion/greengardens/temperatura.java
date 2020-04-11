package aplicacion.greengardens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class temperatura extends AppCompatActivity {

    Button regresarPrin;

    private TextView mostrartemperatura;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        mostrartemperatura = (TextView) findViewById(R.id.temperatura);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    float actualhumedad = Float.parseFloat(dataSnapshot.child("temperature").getValue().toString());

                    mostrartemperatura.setText("La temperatura actual es: " + actualhumedad);



                    //Metodo para regresar al activity principal
                    regresarPrin =(Button) findViewById(R.id.regresarPrin);
                    regresarPrin.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick (View v){
                            Intent siguiente = new Intent(temperatura.this, riego.class);
                            startActivity(siguiente);
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
