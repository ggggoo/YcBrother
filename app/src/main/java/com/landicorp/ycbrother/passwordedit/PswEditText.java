package com.landicorp.ycbrother.passwordedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by 杨大哥 on 2017/5/18.
 */

public class PswEditText extends EditText {

    /**
     * 竖直分割线的画笔
     */
    private Paint divideLinePaint;
    /**
     * 圆的画笔
     */
    private Paint circlePaint;
    /**
     * 底部线的画笔
     */
    private Paint bottomLinePaint;
    /**
     * 边界的画笔
     */
    private Paint borderPaint;

    private int divideLineColor = Color.BLACK;
    private int circleColor = Color.RED;
    private int bottomLineColor = Color.BLUE;
    private int borderColor = Color.GRAY;

    private int mWidth, mHeight, mStartOX, mStartOY, mDivideStartW;
    private int textLength;

    private String mPassword;
    private pswListener mPswListener;

    private RectF rectF = new RectF();

    public PswEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCursorVisible(false);
        this.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
    }

    private void initPaint() {
        divideLinePaint = getPaint(2, divideLineColor, Paint.Style.FILL);
        circlePaint = getPaint(2, circleColor, Paint.Style.FILL);
        borderPaint = getPaint(2, borderColor, Paint.Style.STROKE);
        bottomLinePaint = getPaint(2, bottomLineColor, Paint.Style.FILL);
    }

    private Paint getPaint(int width, int color, Paint.Style style) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mStartOX = w / 6 / 2;
        mStartOY = h / 2;
        mDivideStartW = w / 6;
        rectF.set(0, 0, mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(rectF, 10, 10, borderPaint);

        for (int i = 0; i < 6 - 1; i++) {
            canvas.drawLine((i + 1) * mDivideStartW,
                    0,
                    (i + 1) * mDivideStartW,
                    mHeight,
                    divideLinePaint);
        }

        drawPswCircle(canvas);
    }

    private void drawPswCircle(Canvas canvas) {
        for (int i = 0; i < textLength; i++) {
            canvas.drawCircle(mStartOX + i * 2 * mStartOX, mStartOY, 8, circlePaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        textLength = text.toString().length();
        if(textLength == 6){
            if(text.toString().equals(mPassword)){
                if(mPswListener!=null)
                    mPswListener.success();
            }else{
                if(mPswListener!=null)
                    mPswListener.error("密码错误");
            }
        }
        invalidate();
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);

        //保证光标始终在最后
        if (selStart == selEnd) {
            setSelection(getText().length());
        }
    }

    public void setPsw(String psw,pswListener pswListener){
        this.mPassword = psw;
        this.mPswListener = pswListener;
    }

    public interface pswListener{
        void success();
        void error(String str);
    }
}
