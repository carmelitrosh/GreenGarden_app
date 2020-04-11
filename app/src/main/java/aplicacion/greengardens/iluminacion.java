package aplicacion.greengardens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class iluminacion extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    Button regresarPrin;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iluminacion);


        Switch led = (Switch) findViewById(R.id.led); //Declaracion de la variable que contiene el id del boton switch

        // Conexion a la base de datos.
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("led");

        led.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        createNotificacion();
                        createNotificacionChannel();
                        myRef.setValue(0);
                    }else{
                        myRef.setValue(1);
                    }
            }
        });

        //Metodo para regresar al activity principal
        regresarPrin =(Button) findViewById(R.id.regresarPrin);
        regresarPrin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent siguiente = new Intent(iluminacion.this, MainActivity.class);
                startActivity(siguiente);
            }
        });

    }

    private void createNotificacionChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Iluminación Encendida";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotificacion(){
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_wb_incandescent_black_24dp);
        builder.setContentTitle("Iluminación Encendida");
        builder.setContentText("La iluminación principal del invernadero esta encedida...");
        builder.setColor(Color.GREEN);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,100});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

}
