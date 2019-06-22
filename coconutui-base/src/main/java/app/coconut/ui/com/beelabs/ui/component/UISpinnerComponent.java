package app.coconut.ui.com.beelabs.ui.component;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class UISpinnerComponent {

    public static void show(int rowSpinnerLayoutId, final int rowSpinnerId, List<String> dataLabel, final Spinner spinner, final TextView target, Context context) {
        show(rowSpinnerLayoutId, rowSpinnerId, dataLabel, spinner, target, context, null);
    }

    public static void show(int rowSpinnerLayoutId, final int rowSpinnerId, List<String> dataLabel, final Spinner spinner, final TextView target, Context context, final ItemSelectedCallback callback) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, rowSpinnerLayoutId, rowSpinnerId,
                dataLabel);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinnerView = view.findViewById(rowSpinnerId);
                String res = spinnerView.getText().toString();
                target.setText(res);

                if (callback != null) callback.onCall(res);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });
    }

    public static void showWithCustomId(int rowSpinnerLayoutId, final int rowSpinnerId, List<String> dataLabel, final List<Integer> dataValue, final Spinner spinner, final TextView target, Context context, final ItemSelectedCallback callback) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, rowSpinnerLayoutId, rowSpinnerId,
                dataLabel);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinnerView = view.findViewById(rowSpinnerId);
                String res = spinnerView.getText().toString();
                target.setText(res);

                if (callback != null) callback.onCall(res, dataValue.get(position));
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });
    }


    public static void show(int rowSpinnerLayoutId, final int rowSpinnerId, final List<String> dataName, final List<int[]> dataValue, final Spinner spinner, final TextView target, Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, rowSpinnerLayoutId, rowSpinnerId,
                dataName);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinnerView = view.findViewById(rowSpinnerId);
                String res = spinnerView.getText().toString();
                target.setText(res);
                target.setTag(dataValue.get(0)[position]);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });
    }


    public static class ItemSelectedCallback {
        public void onCall(String name) {

        }

        public void onCall(String name, Object value) {

        }
    }
}
