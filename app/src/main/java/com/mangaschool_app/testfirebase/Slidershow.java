package com.mangaschool_app.testfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

public class Slidershow extends AppCompatActivity implements  ViewPagerEx.OnPageChangeListener{

    SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidershow);


        ArrayList<String> urlList  = new ArrayList();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        urlList.add("http://imageshack.com/a/img924/3366/VzjaaB.jpg");
        urlList.add("http://imageshack.com/a/img922/6719/xLjXwa.jpg");
        urlList.add("http://imageshack.com/a/img923/6757/M70jrN.jpg");
        urlList.add("http://imageshack.com/a/img922/2751/85KVWm.jpg");
        urlList.add("http://imageshack.com/a/img922/5769/WtMb5K.jpg");

        for(String name : urlList){

            DefaultSliderView imageSliderView = new DefaultSliderView(Slidershow.this);
            imageSliderView
                    .image(name)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            imageSliderView.bundle(new Bundle());
            imageSliderView.getBundle();
            sliderLayout.addSlider( imageSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.stopAutoCycle();
        sliderLayout.addOnPageChangeListener(this);
    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}



