package com.example.mushroom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImageAdapter extends PagerAdapter {

    Context mContext;
    ImageAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return sliderImageId.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView)object);

    }

    public int[] sliderImageId = new int[]{
            R.drawable.u1,R.drawable.u2,R.drawable.u3
    };

  @Override
    public Object instantiateItem(ViewGroup container,int position){
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager)container).addView(imageView,0);
                return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        ((ViewPager)container).removeView((ImageView)object);
    }
}
