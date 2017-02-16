package com.senseluxury.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by Jay_zhao
 * on 2017/1/9.
 */

public class Mycircle extends View {
    private int mWidth, mHeight; //获取高宽
    private float r; //圆的直径
    private Paint mPaint; //画笔
    private Path mPath;   // 路径
    private ScaleAnimation mAnimation_in, mAnimation_out;
    private AlphaAnimation mAlphaAnimation_in, mAlphaAnimation_out;
    private AnimationSet mSet;
    private AnimationSet mSet1;
    private final View mView = this;

    private void initAll() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPath = new Path();
    }

    public Mycircle(Context context) {
        super(context);
    }

    public Mycircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Mycircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initAll();
        mWidth = w;
        mHeight = h;
        r = Math.min(mWidth, mHeight) * 1f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, r / 2, mPaint);
    }

    public void startBreathe() {
        mAnimation_in = new ScaleAnimation(1.4f, 1f, 1.4f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation_in.setDuration(1000);
        mAnimation_out = new ScaleAnimation(1f, 1.4f, 1f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation_out.setDuration(1000);
        mAlphaAnimation_in = new AlphaAnimation(1f, 0f);
        mAlphaAnimation_in.setDuration(1000);
        mAlphaAnimation_out = new AlphaAnimation(0f, 1f);
        mAlphaAnimation_out.setDuration(1000);
        mAnimation_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mView.startAnimation(mSet1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mAnimation_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mView.startAnimation(mSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mSet = new AnimationSet(false);
        mSet1 = new AnimationSet(false);
        mSet1.addAnimation(mAlphaAnimation_in);
        mSet1.addAnimation(mAnimation_in);
        mSet.addAnimation(mAnimation_out);
        mSet.addAnimation(mAlphaAnimation_out);
        mView.startAnimation(mSet);
    }
}
