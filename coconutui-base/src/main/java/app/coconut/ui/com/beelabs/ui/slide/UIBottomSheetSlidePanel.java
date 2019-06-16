package app.coconut.ui.com.beelabs.ui.slide;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;

import app.coconut.ui.com.beelabs.R;

public class UIBottomSheetSlidePanel {

    public static void showSlidePanel(int layoutSlideID, Context context, SlideCallbackListener callback){
        View view = LayoutInflater.from(context).inflate(layoutSlideID, null);
        BottomSheetDialog dialog = new BottomSheetDialog(context, R.style.SheetDialog);
        dialog.setContentView(view);

        dialog.show();

        callback.onCall(dialog, view);
    }

    public static class SlideCallbackListener {

        public void onCall(BottomSheetDialog dialog, View view) {

        }
    }
}
