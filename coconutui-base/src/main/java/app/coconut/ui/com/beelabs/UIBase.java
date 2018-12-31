package app.coconut.ui.com.beelabs;

import android.app.Activity;

public class UIBase {

    public static void restartActivityWithoutAnimation(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(0, 0);
        activity.startActivity(activity.getIntent());
        activity.overridePendingTransition(0, 0);
    }
}
