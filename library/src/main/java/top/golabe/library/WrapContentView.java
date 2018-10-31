package top.golabe.library;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class WrapContentView extends FrameLayout implements WrapInterface {
    private static final String TAG = "WrapContentView";
    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_FROM_LEFT = 0x04;
    private static final int TRANSLATION_FROM_RIGHT = 0x08;
    private int fromColor;
    private int toColor;
    private int translation;
    private boolean alpha;
    private boolean scaleX;
    private boolean scaleY;
    private int blur;
    private int width;
    private int height;

    private ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
    private Bitmap decodeResource;
    private BitmapDrawable blurBitmap;

    public void setBlur(int blur) {
        this.blur = blur;
    }


    public void setBlurResId(int blurResId) {
        if (decodeResource == null) {
            decodeResource = BitmapFactory.decodeResource(getResources(), blurResId);

        }
        if (decodeResource != null) {
            blurBitmap = new BitmapDrawable(BlurBitmap.blur(getContext(), decodeResource, blur));
        }


    }

    public void setFromColor(int fromColor) {
        this.fromColor = fromColor;
    }


    public void setToColor(int toColor) {
        this.toColor = toColor;
    }

    public void setTranslation(int translation) {
        this.translation = translation;
    }


    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }


    public void setScaleX(boolean scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(boolean scaleY) {
        this.scaleY = scaleY;
    }

    public WrapContentView(Context context) {
        super(context);
    }

    public WrapContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        onResetScroll(null);
    }

    @Override
    public void onScroll(WrapContentView child, float ratio) {


        if (blur != -1 && blurBitmap != null) {
            View childAt = child.getChildAt(0);
            if (childAt instanceof ImageView) {
                ImageView imageView = (ImageView) childAt;

                imageView.setBackground(blurBitmap);
            }
        }
        if (alpha) {
            setAlpha(ratio);
            Log.d(TAG, "onScroll: alpha");
        }
        if (scaleX) {
            setScaleX(ratio);
            Log.d(TAG, "onScroll: scaleX");
        }
        if (scaleY) {
            setScaleY(ratio);
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_BOTTOM)) {
            setTranslationY(height * (1 - ratio));//mHeight-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_TOP)) {
            setTranslationY(-height * (1 - ratio));//-mHeight-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_LEFT)) {
            setTranslationX(-width * (1 - ratio));//-width-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_RIGHT)) {
            setTranslationX(width * (1 - ratio));//width-->0(代表原来的位置)
        }
        if (fromColor != -1 && toColor != -1) {
            setBackgroundColor((Integer) sArgbEvaluator.evaluate(ratio, fromColor, toColor));
        }
    }

    private boolean isScrollTranslationFrom(int translationMask) {
        if (translation == -1) {
            return false;
        }
        //fromLeft|fromBottom & fromBottom = fromBottom
        return (translation & translationMask) == translationMask;
    }

    @Override
    public void onResetScroll(WrapContentView child) {
        if (alpha) {
            setAlpha(0);
        }
        if (scaleY) {
            setScaleY(0);
        }
        if (scaleX) {
            setScaleX(0);
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_BOTTOM)) {
            setTranslationY(height);//mHeight-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_TOP)) {
            setTranslationY(-height);//-mHeight-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_LEFT)) {
            setTranslationX(-width);//-width-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_RIGHT)) {
            setTranslationX(width);//width-->0(代表原来的位置)
        }
        if (blur != -1 && child != null && blurBitmap != null) {
            View childAt = child.getChildAt(0);
            if (childAt instanceof ImageView) {
                ImageView imageView = (ImageView) childAt;
                imageView.setBackground(blurBitmap);
            }
        }

    }
}
