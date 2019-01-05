package com.example.user.books;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> imagePaths;
    LayoutInflater inflater;
    ImageView img;
    static Button btnAudio;
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
        return view==(RelativeLayout)object;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View viewLayout=inflater.inflate(R.layout.fullscreen_image,container,false);
        img=viewLayout.findViewById(R.id.page);
        btnAudio=viewLayout.findViewById(R.id.audio);

      /**  ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);**/
     /**   Bitmap image;
        image=BitmapFactory.decodeFile(imagePaths.get(position));
        img.setImageBitmap(image);**/
     /**   imageView.setImageBitmap(image);
           ((ViewPager)container).addView(imageView,0);**/
       // ((ViewPager)container).addView(img,0);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPreferredConfig=Bitmap.Config.ARGB_8888;
        Bitmap bitmap=BitmapFactory.decodeFile(imagePaths.get(position),options);
        img.setImageBitmap(bitmap);

        ((ViewPager)container).addView(viewLayout);
        return viewLayout;
     //   return imageView;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((RelativeLayout)object);
    }
}
