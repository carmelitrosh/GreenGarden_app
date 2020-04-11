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


public class humedad extends AppCompatActivity {

    Button regresarPrin;

    private TextView mostrarhumedad;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humedad);

        mostrarhumedad = (TextView) findViewById(R.id.humedad);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    float actualhumedad = Float.parseFloat(dataSnapshot.child("humidity").getValue().toString());
                    mostrarhumedad.setText("La humedad actual es: " + actualhumedad);




                    //Metodo para regresar al activity principal
                    regresarPrin =(Button) findViewById(R.id.regresarPrin);
                    regresarPrin.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick (View v){
                            Intent siguiente = new Intent(humedad.this, riego.class);
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
