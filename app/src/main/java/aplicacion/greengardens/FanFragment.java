package aplicacion.greengardens;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.MessageFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

//Icons made by itim2101 from www.flaticon.com
//Icons made by Freepik from www.flaticon.com
//Icons made by Cursor Creative from www.flaticon.com

public class FanFragment extends Fragment {

    private FanViewModel fanViewModel;
    private TextView mPercentTextView;
    private SeekBar percentSeekBar;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Switch mFanSwitch;
    private int value;

    Button botonregresar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fanViewModel =
                ViewModelProviders.of(this).get(FanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fan, container, false);


        //Metodo pra regresar al activity principal
        botonregresar = (Button)root.findViewById(R.id.regresarPrin);
        botonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        mPercentTextView= root.findViewById(R.id.percentTextView);
        percentSeekBar=(SeekBar)root.findViewById(R.id.percentSeekBar);
        mFanSwitch=(Switch)root.findViewById(R.id.fanSwitch);

        database    = FirebaseDatabase.getInstance();
        myRef       = database.getReference("ventilador");

        fanViewModel.getText().observe(this, new Observer<Integer>() {

            @Override
            public void onChanged(@Nullable Integer s) {

                percentSeekBar.setProgress(s);
                value=s;

                int percent= (int)(s / 10.24);
                mPercentTextView.setText(MessageFormat.format("{0}%", percent));

                if(s==0){
                    mFanSwitch.setChecked(false);
                    value=1024;
                }else{
                    mFanSwitch.setChecked(true);
                }
            }
        });

        mFanSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myRef.setValue(value);
                }else{
                    myRef.setValue(0);
                }
            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    myRef.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return root;
    }
}