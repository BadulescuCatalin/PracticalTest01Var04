package ro.pub.cs.systems.eim.practivaltest01var04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practivaltest01var04.service.PracticalTest01Var04Service;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private Button navigateToSecondaryActivityButton;
    private Button displayInfoButton;
    private EditText editTextNume;
    private EditText editTextGrupa;
    private CheckBox checkBoxNume;
    private CheckBox checkBoxGrupa;
    private TextView textViewInfo;

    private IntentFilter intentFilter = new IntentFilter();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

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

        navigateToSecondaryActivityButton.setOnClickListener(v -> {
            String nume = editTextNume.getText().toString();
            String grupa = editTextGrupa.getText().toString();
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra("nume", nume);
            intent.putExtra("grupa", grupa);
            startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        });

        displayInfoButton.setOnClickListener(v -> {
            String nume = editTextNume.getText().toString();
            String grupa = editTextGrupa.getText().toString();
            if ("".equals(nume) || "".equals(grupa)) {
                Toast.makeText(this, "Ambele camputi trebuiesc completate", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, PracticalTest01Var04Service.class);
            intent.putExtra("nume", nume);
            intent.putExtra("grupa", grupa);
            startService(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(intent);
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }
}