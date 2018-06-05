package com.base.draw;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXF on 2018/5/24.
 */

public class LineeLayout extends ViewGroup {

    private List<List<View>> views = new ArrayList<>();


    private List<Integer> integerList = new ArrayList<>();

    public LineeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width = 0, height = 0, lineHeight = 0, lineWidth = 0, count = getChildCount();

        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);//系统自动测量，否则child.getMeasuredWidth()=0；
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int paddingLeft, paddingRight, paddingBottom, paddingTop;
            paddingLeft = child.getPaddingLeft();
            paddingRight = child.getPaddingRight();
            paddingTop = child.getPaddingTop();
            paddingBottom = child.getPaddingBottom();
            int childWidth = paddingLeft + paddingRight + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + child.getMeasuredWidth();
            int childHeight = paddingTop + paddingBottom + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + child.getMeasuredHeight();


            if (childWidth > sizeWidth) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(width, lineWidth);
            }
            if (i == count - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }


        }
        int measureWidth = modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width;
        int measureHeight = modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height;
        setMeasuredDimension(measureWidth, measureHeight);

    }

    @Override
    protected void onLayout(boolean is, int l, int t, int r, int b) {
        views.clear();
        integerList.clear();
        int lineWidth = 0, lineHeight = 0, width = getWidth(), count = getChildCount();

        List<View> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();

            int paddingLeft, paddingRight, paddingBottom, paddingTop;
            paddingLeft = child.getPaddingLeft();
            paddingRight = child.getPaddingRight();
            paddingTop = child.getPaddingTop();
            paddingBottom = child.getPaddingBottom();


            int childWidth = paddingLeft + paddingRight + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + child.getMeasuredWidth();
            int childHeight = paddingBottom + paddingTop + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + child.getMeasuredHeight();

            if (childWidth + lineWidth > width) {

                views.add(list);
                integerList.add(lineHeight);
                lineWidth = 0;
                lineHeight = childHeight;
                list = new ArrayList<>();
            }
            list.add(child);
            lineWidth += childWidth;
            lineHeight = Math.max(childHeight, lineHeight);

        }

        views.add(list);
        integerList.add(lineHeight);
        int left = 0;
        int top = 0;
        int size = views.size();
        for (int i = 0; i < size; i++) {
            list = views.get(i);
            lineHeight = integerList.get(i);
            for (int j = 0; j < list.size(); j++) {
                View child = list.get(j);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();

                int paddingLeft, paddingRight, paddingBottom, paddingTop;
                paddingLeft = child.getPaddingLeft();
                paddingRight = child.getPaddingRight();
                paddingTop = child.getPaddingTop();
                paddingBottom = child.getPaddingBottom();
             //   Log.e("onMeasure ", "paddingLeft : " + paddingLeft + " paddingRight:  " + paddingRight + " paddingTop:" + paddingTop + "  paddingBottom:" + paddingBottom);

                int ltemp =paddingLeft+ left + marginLayoutParams.leftMargin;
                int ttemp =paddingTop+ top + marginLayoutParams.topMargin;
                int rtemp =paddingRight+ ltemp + child.getMeasuredWidth();
                int btemp =paddingBottom+ ttemp + child.getMeasuredHeight();
                child.layout(ltemp, ttemp, rtemp, btemp);
                left +=paddingLeft+paddingRight+ child.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
            top += lineHeight;
            left = 0;
        }
    }
}
