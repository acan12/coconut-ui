package app.coconut.ui.com.beelabs.ui.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ViewPersonaCoco extends LinearLayout {

    private static int marginTop = 0;

    public static int getMarginTop() {
        return marginTop;
    }

    public static void setMarginTop(int marginTop) {
        ViewPersonaCoco.marginTop = marginTop;
    }

    public ViewPersonaCoco(Context context) {
        super(context);
    }

    public ViewPersonaCoco(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPersonaCoco(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        // draw oval
        drawOval(canvas);

        // draw rectangle
//        drawRectangle(canvas);

    }

    private void drawOval(Canvas canvas) {
        int viewportMargin = 32;
        float width = (float) getWidth() - viewportMargin;
        float marginLeftRight = (float) getWidth() / 5f;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8.5f);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        RectF oval3 = new RectF(viewportMargin + marginLeftRight, (float) marginTop / 3.8f, (float) width - marginLeftRight, (float) getHeight() / 2f);
        canvas.drawOval(oval3, paint);
    }

    private void drawRectangle(Canvas canvas) {
        int viewportMargin = 45;
        int viewportCornerRadius = 8;
        Paint eraser = new Paint();
        eraser.setAntiAlias(true);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        float width = (float) getWidth() - viewportMargin;
        float height = width * (float) 0.5;
        RectF rect = new RectF((float) viewportMargin, ((float) viewportMargin + marginTop) / 1f, width, height + marginTop);
//        RectF rect = new RectF((float) viewportMargin, (float) viewportMargin + marginTop, width, height + marginTop);
        canvas.drawRoundRect(rect, (float) viewportCornerRadius, (float) viewportCornerRadius, eraser);
    }
}
