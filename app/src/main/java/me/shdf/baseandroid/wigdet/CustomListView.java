package me.shdf.baseandroid.wigdet;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by shdf on 17/4/15.
 * wechat：zcm656025633
 * exp：
 **/

public class CustomListView extends ListView {
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}


