package com.example.user.books;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> imagePaths;
    public ImageAdapter(Context context, ArrayList<String> imagePaths) {
        this.context=context;
        this.imagePaths=imagePaths;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ImageView)object;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Bitmap image;
        image=BitmapFactory.decodeFile(imagePaths.get(position));
        imageView.setImageBitmap(image);
        ((ViewPager)container).addView(imageView,0);
        return imageView;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((ImageView)object);
    }
}
