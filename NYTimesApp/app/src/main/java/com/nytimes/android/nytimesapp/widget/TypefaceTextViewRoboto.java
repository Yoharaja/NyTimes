package com.nytimes.android.nytimesapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.nytimes.android.nytimesapp.R;


public class TypefaceTextViewRoboto extends AppCompatTextView {


    public TypefaceTextViewRoboto(Context context) {
        super(context);
        Typeface face = ResourcesCompat.getFont(context, R.font.roboto_regular);
        this.setTypeface(face);
    }

    public TypefaceTextViewRoboto(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = ResourcesCompat.getFont(context, R.font.roboto_regular);
        this.setTypeface(face);
    }

    public TypefaceTextViewRoboto(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = ResourcesCompat.getFont(context, R.font.roboto_regular);
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
