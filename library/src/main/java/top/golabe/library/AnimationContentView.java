package top.golabe.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class AnimationContentView extends LinearLayout {
    public AnimationContentView(Context context) {
        this(context, null);
    }

    public AnimationContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ContentLayoutParams(getContext(), attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        ContentLayoutParams lp = (ContentLayoutParams) params;
        if (isAddParams(lp)) {
            WrapContentView view = new WrapContentView(getContext());

            view.setBlur(lp.blur);
            view.setBlurResId(lp.blurResId);
            view.setAlpha(lp.alpha);
            view.setScaleX(lp.scaleX);
            view.setScaleY(lp.scaleY);
            view.setTranslation(lp.translation);
            view.setFromColor(lp.fromColor);
            view.setToColor(lp.toColor);
            view.addView(child);

            super.addView(view, index, params);
        } else {
            super.addView(child, index, params);
        }
    }

    private boolean isAddParams(ContentLayoutParams lp) {
        return lp.alpha || lp.scaleX || lp.scaleY || lp.translation != -1 || (lp.fromColor != -1 && lp.toColor != -1) || lp.blur != -1;
    }

    public static class ContentLayoutParams extends LayoutParams {
        private int fromColor;
        private int toColor;
        private int translation;
        private boolean alpha;
        private boolean scaleX;
        private boolean scaleY;
        private int blur;
        private int blurResId;

        public ContentLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            if (attrs != null) {
                TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.AnimationContentView);
                fromColor = a.getColor(R.styleable.AnimationContentView_scroller_from_color, -1);
                toColor = a.getColor(R.styleable.AnimationContentView_scroller_to_color, -1);
                translation = a.getInt(R.styleable.AnimationContentView_scroller_translation, -1);
                alpha = a.getBoolean(R.styleable.AnimationContentView_scroller_alpha, false);
                scaleX = a.getBoolean(R.styleable.AnimationContentView_scroller_scaleX, false);
                scaleY = a.getBoolean(R.styleable.AnimationContentView_scroller_scaleY, false);
                blur = a.getInt(R.styleable.AnimationContentView_scroller_blur, -1);
                blurResId = a.getResourceId(R.styleable.AnimationContentView_scroller_blur_res, -1);
                a.recycle();
            }
        }
    }

}
