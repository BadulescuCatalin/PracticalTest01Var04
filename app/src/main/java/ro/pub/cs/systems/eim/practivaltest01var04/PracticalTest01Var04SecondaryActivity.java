package ro.pub.cs.systems.eim.practivaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    private TextView textViewNume;
    private TextView textViewGrupa;
    private Button okButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        textViewNume = (TextView) findViewById(R.id.text_view_scond_nume);
        textViewGrupa = (TextView) findViewById(R.id.text_view_scond_grupa);
        okButton = (Button) findViewById(R.id.button_scond_ok);

        cancelButton = (Button) findViewById(R.id.button_scond_cancel);
        Intent intent = getIntent();
        String nume = intent.getStringExtra("nume");
        String grupa = intent.getStringExtra("grupa");
        textViewNume.setText(nume);
        textViewGrupa.setText(grupa);
        okButton
                .setOnClickListener(v -> {
                    setResult(RESULT_OK, null);
                    finish();
                });
        cancelButton
                .setOnClickListener(v -> {
                    setResult(RESULT_CANCELED, null);
                    finish();
                });
    }


}