package app.coconut.ui.com.beelabs.ui.component.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.applandeo.materialcalendarview.extensions.CalendarViewPager;

/**
 * Created by arysuryawan on 1/29/18.
 */
public class CalendarScheduleViewPager extends CalendarViewPager {

    public CalendarScheduleViewPager(Context context) {
        super(context);
    }

    public CalendarScheduleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //This method is needed to get wrap_content height for ViewPager
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            int h = child.getMeasuredHeight();

            if (h > height) {
                height = h;
            }
        }

        if (height != 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
