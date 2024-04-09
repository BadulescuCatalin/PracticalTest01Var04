package ro.pub.cs.systems.eim.practivaltest01var04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private Button navigateToSecondaryActivityButton;
    private Button displayInfoButton;
    private EditText editTextNume;
    private EditText editTextGrupa;
    private CheckBox checkBoxNume;
    private CheckBox checkBoxGrupa;
    private TextView textViewInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigateToSecondaryActivityButton);
        displayInfoButton = (Button)findViewById(R.id.displayInformationsButton);
        editTextNume = (EditText)findViewById(R.id.editTextNume);
        editTextGrupa = (EditText)findViewById(R.id.editTextGrupa);
        checkBoxNume = (CheckBox)findViewById(R.id.checkBoxNume);
        checkBoxGrupa = (CheckBox)findViewById(R.id.checkBoxGrupa);
        textViewInfo = (TextView)findViewById(R.id.textViewInfo);

        displayInfoButton.setOnClickListener(v -> {
            String res = "";

            if (checkBoxNume.isChecked()) {
                if (editTextNume.getText().toString().isEmpty() || "".equals(editTextNume.getText().toString()))
                    Toast.makeText(this, "Campul Nume este gol", Toast.LENGTH_SHORT).show();

                else
                    res += editTextNume.getText().toString() + " ";

            }
            if (checkBoxGrupa.isChecked()) {
                if (editTextGrupa.getText().toString().isEmpty() || "".equals(editTextGrupa.getText().toString()))
                    Toast.makeText(this, "Campul Grupa este gol", Toast.LENGTH_SHORT).show();
                else
                    res += editTextGrupa.getText().toString();
            }
            textViewInfo.setText(res);

        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("nume", editTextNume.getText().toString());
        outState.putString("grupa", editTextGrupa.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("nume"))
            editTextNume.setText(savedInstanceState.getString("nume"));
        if (savedInstanceState.containsKey("grupa"))
            editTextGrupa.setText(savedInstanceState.getString("grupa"));
        super.onRestoreInstanceState(savedInstanceState);
    }
}