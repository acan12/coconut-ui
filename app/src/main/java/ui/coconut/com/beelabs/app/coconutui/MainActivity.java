package ui.coconut.com.beelabs.app.coconutui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import app.coconut.ui.com.beelabs.ui.UIDateTimePicker;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView demoButton = findViewById(R.id.demo_button);
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIDateTimePicker.onPicker(demoButton, MainActivity.this);
            }
        });
    }

}
