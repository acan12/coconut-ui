package app.coconut.ui.com.beelabs.ui.component;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UIDateTimePicker {

    public static void onPicker(final TextView target, Context context) {
        DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                int month = monthOfYear + 1;
                String yyyyMMdd = year + "-" + month + "-" + dayOfMonth;

                SimpleDateFormat formatDateIn = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatDateOut = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date date = formatDateIn.parse(yyyyMMdd);
                    String finalDay = formatDateOut.format(date);


                    target.setTag(formatString(date, "dd-MM-yyyy"));
                    target.setText(finalDay);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };

        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog d = new DatePickerDialog(context, dpd, mYear, mMonth, mDay);
        c2.add(Calendar.YEAR, 15 * -1);
        d.getDatePicker().setMaxDate(c2.getTimeInMillis());

        d.show();
    }

    private static String formatString(Date date, String pattern) {
        SimpleDateFormat form = new SimpleDateFormat(pattern);
        String format = form.format(date);

        return format;
    }
}
