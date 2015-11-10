package com.cheheihome.ui.widget;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chanlevel on 15/10/29.
 */
public class LeToolbar extends Toolbar {


    public LeToolbar(Context context) {
        super(context);
    }

    public LeToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitileLayoutGravity(int gravity) {
        try {

            for (Method m : TextView.class.getDeclaredMethods())
                System.out.println(m.getName());

            Field mTitleTextView = Toolbar.class.getDeclaredField("mTitleTextView");
            mTitleTextView.setAccessible(true);
            String s = mTitleTextView.getType().getCanonicalName();
            System.out.print("----" + s);


            Method setLayoutParams = View.class.getDeclaredMethod("setLayoutParams", ViewGroup.LayoutParams.class);
            setLayoutParams.setAccessible(true);
            TextView tv = (TextView) mTitleTextView.get(this);
            Toolbar.LayoutParams lp = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = gravity;

            setLayoutParams.invoke(tv, lp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

    }

    private void addSystemView(View v, boolean b) {
        try {
            Method addSV = LeToolbar.class.getMethod("addSystemView");
            addSV.setAccessible(true);
            // invoke(this,addSV,new Object[]{v,b});
            addSV.invoke(this, new Object[]{v, b});

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }


}
