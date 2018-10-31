# 滚动动画 AnimationScrollView

![](https://github.com/Golabe/AnimationScrollView/blob/master/images/a1.png?raw=true "width:200px" "height:250px")



## 使用


```xml
<?xml version="1.0" encoding="utf-8"?>
<top.golabe.library.AnimationScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:scroller="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <top.golabe.library.AnimationContentView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_horizontal">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="600dp"
            android:background="@android:color/white"
            android:fontFamily="serif"
            android:gravity="center"
            android:padding="25dp"
            android:text="Flutter是谷歌的移动UI框架，可以快速在iOS和Android上构建高质量的原生用户界面。 Flutter可以与现有的代码一起工作。在全世界，Flutter正在被越来越多的开发者和组织使用，并且Flutter是完全免费、开源的。"
            android:textColor="@android:color/black"
            android:textSize="25sp" />

        <ImageView
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:scaleType="centerCrop"
            android:src="@drawable/bg"
            scroller:scroller_alpha="true" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/android_logo1"
            scroller:scroller_alpha="true"
            scroller:scroller_translation="fromLeft|fromBottom" />

        <View
            android:layout_width="match_parent"
            android:layout_height="200dp"
            scroller:scroller_from_color="#e362bc"
            scroller:scroller_to_color="#76de53" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/android_logo2"
            scroller:scroller_translation="fromRight" />

        <ImageView
            android:id="@+id/blur"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:scaleType="centerCrop"
            scroller:scroller_blur="20"
            scroller:scroller_blur_res="@drawable/bg" />
        <ImageView
            android:layout_width="match_parent"
            android:layout_height="500dp"
            android:scaleType="centerCrop"
            scroller:scroller_blur="12"
            scroller:scroller_blur_res="@drawable/bg1" />
    </top.golabe.library.AnimationContentView>
</top.golabe.library.AnimationScrollView>

```

### Attrs

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="AnimationContentView">
        <!--颜色渐变开始-->
        <attr name="scroller_from_color" format="color" />
        
        <!--颜色渐变结束-->
        <attr name="scroller_to_color" format="color" />
        <!--是否开启透明-->
        <attr name="scroller_alpha" format="boolean" />
        <!--缩放X-->
        <attr name="scroller_scaleX" format="boolean" />
        <!--缩放Y-->
        <attr name="scroller_scaleY" format="boolean" />
        
        <!--贝斯模糊  scroller_blur 0-25F-->
        <attr name="scroller_blur" format="integer"/>
        <!--模糊图片资源-->
        <attr name="scroller_blur_res" format="reference"/>
        
        <!--从哪里开始移动-->
        <attr name="scroller_translation" format="flags" >
            <!--从上开始-->
            <flag name="fromTop" value="0x01" />
            <!--从下开始-->
            <flag name="fromBottom" value="0x02" />
            <!--从左开始-->
            <flag name="fromLeft" value="0x04" />
            <!--从右开始-->
            <flag name="fromRight" value="0x08" />
        </attr>
    </declare-styleable>
</resources>

```






