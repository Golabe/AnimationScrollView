package top.golabe.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

public class AnimationScrollView extends NestedScrollView {
    private AnimationContentView mContent;

    public AnimationScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View content = getChildAt(0);
        mContent = (AnimationContentView) content;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        View first = mContent.getChildAt(0);
        first.getLayoutParams().height = getHeight();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int scrollViewHeight = getHeight();
        for (int i = 0; i < mContent.getChildCount(); i++) {
            View child = mContent.getChildAt(i);
            if (!(child instanceof WrapInterface)) {
                continue;
            }

            WrapInterface wrapInterface = (WrapInterface) child;
            int top = child.getTop();
            int height = child.getHeight();

            int newTop = top - t;
            if (newTop <= scrollViewHeight) {
                int visibleGap = scrollViewHeight - newTop;
                wrapInterface.onScroll((WrapContentView) child,clamp(visibleGap /(float) height, 1F, 0F));
            } else {
                wrapInterface.onResetScroll((WrapContentView) child);
            }
        }
    }

    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, max), min);
    }
}
