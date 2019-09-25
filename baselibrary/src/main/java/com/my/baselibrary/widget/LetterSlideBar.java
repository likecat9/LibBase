package com.my.baselibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author 徐康
 * 2019/5/13
 * 描述：
 */
public class LetterSlideBar extends View {
    private LetterSlideBar.OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    public static String[] b = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int choose = -1;
    private Paint paint = new Paint();
    private TextView mTextDialog;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    public LetterSlideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LetterSlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LetterSlideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = this.getHeight();
        int width = this.getWidth();
        float singleHeight = (float) height * 1.0F / (float) b.length;
        singleHeight = ((float) height * 1.0F - singleHeight / 2.0F) / (float) b.length;

        for (int i = 0; i < b.length; ++i) {
            this.paint.setColor(Color.rgb(86, 86, 86));
            this.paint.setTypeface(Typeface.DEFAULT);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(width/2);
            float xPos = (float) (width / 2) - this.paint.measureText(b[i]) / 2.0F;
            float yPos = singleHeight * (float) i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, this.paint);
            this.paint.reset();
        }

    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        int oldChoose = this.choose;
        LetterSlideBar.OnTouchingLetterChangedListener listener = this.onTouchingLetterChangedListener;
        int c = (int) (y / (float) this.getHeight() * (float) b.length);
        if (action == 1) {
            this.setBackground(new ColorDrawable(0));
            this.choose = -1;
            this.invalidate();
            if (this.mTextDialog != null) {
                this.mTextDialog.setVisibility(GONE);
            }
        } else {
            this.setBackground(new ColorDrawable(320213782));
            if (oldChoose != c && c >= 0 && c < b.length) {
                if (listener != null) {
                    listener.onTouchingLetterChanged(b[c]);
                }

                if (this.mTextDialog != null) {
                    this.mTextDialog.setText(b[c]);
                    this.mTextDialog.setVisibility(VISIBLE);
                }

                this.choose = c;
                this.invalidate();
            }
        }

        return true;
    }

    public void setOnTouchingLetterChangedListener(LetterSlideBar.OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String letter);
    }
}
