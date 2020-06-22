package app.coconut.ui.com.beelabs.ui.component;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import java.text.NumberFormat;
import java.util.Locale;

import app.coconut.ui.com.beelabs.R;

public class UIMoneyCurrencyEditText extends AppCompatEditText {
    private static final int MAX_LENGTH = 15;
    private CurrencyTextWatcher currencyTextWatcher = new CurrencyTextWatcher(this);


    public UIMoneyCurrencyEditText(Context context) {
        this(context, null);
    }

    public UIMoneyCurrencyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public UIMoneyCurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH)});
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            this.addTextChangedListener(currencyTextWatcher);
        } else {
            this.removeTextChangedListener(currencyTextWatcher);
        }
    }

    private class CurrencyTextWatcher implements TextWatcher {
        private final EditText editText;
        private String current;

        CurrencyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // do nothing
        }

        @Override
        public void afterTextChanged(Editable editable) {

            String str = editable.toString();

            if (!str.equals(current)) {
                removeTextChangedListener(this);

                Locale local = new Locale("id", "id");
                String replaceable = String.format("[Rp,.\\s]",
                        NumberFormat.getCurrencyInstance().getCurrency()
                                .getSymbol(local));
                String cleanString = str.replaceAll(replaceable,
                        "");

                double parsed;
                try {
                    parsed = Double.parseDouble(cleanString);
                } catch (NumberFormatException e) {
                    parsed = 0.00;
                }

                NumberFormat formatter = NumberFormat
                        .getCurrencyInstance(local);
                formatter.setMaximumFractionDigits(0);
                formatter.setParseIntegerOnly(true);
                String formatted = formatter.format((parsed));

                String replace = String.format("[Rp\\s]",
                        NumberFormat.getCurrencyInstance().getCurrency()
                                .getSymbol(local));
                String clean = formatted.replaceAll(replace, "");

                current = formatted;
                editText.setText(clean);
                editText.setTag(parsed);
                editText.setSelection(clean.length());
                editText.addTextChangedListener(this);
            }

        }
    }

}
