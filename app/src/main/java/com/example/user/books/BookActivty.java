package com.example.user.books;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class BookActivty extends AppCompatActivity {
    ImageView imageView;
    ArrayList<String> imagePaths=new ArrayList<>();
    private static final int MY_PERMSSIONS=1;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_activty);
       // imageView=findViewById(R.id.bookImage);
        viewPager=findViewById(R.id.pager);
        if(ContextCompat.checkSelfPermission(BookActivty.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            getBitmapPublicFile();
            ImageAdapter adapter=new ImageAdapter(this,imagePaths);
            viewPager.setAdapter(adapter);
        }else{
            //Toast.makeText(getApplicationContext(),"Permission not granted",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(BookActivty.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMSSIONS);
        }


    //    Intent intent=new Intent(BookActivty.this,MP3.class);
    //    startActivity(intent);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case MY_PERMSSIONS:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getBitmapPublicFile();
                    ImageAdapter adapter=new ImageAdapter(this,imagePaths);
                    viewPager.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Permission denied",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }

    }
    private void getBitmapPublicFile() {
        String MEDIA_MOUNTED="mounted";
        String diskState=Environment.getExternalStorageState();
        if(diskState.equals(MEDIA_MOUNTED)){
            File pictureFolder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File[] files=pictureFolder.listFiles();
            imagePaths=new ArrayList<>();
            for(int i=0;i<files.length;i++) {
                File file = files[i];
                if (file.getPath().endsWith("jpg")) {
                    String path = file.getAbsolutePath();
                    Log.e("TAG", path);
                    //  File filePicture=new File(pictureFolder,"")
                    imagePaths.add(file.getPath());
                }
            }
        /**    File filePicture=new File(pictureFolder,"1525509702766.jpg");
            Bitmap bitmapToDisplay=null;
            if(filePicture.exists()){
                bitmapToDisplay=BitmapFactory.decodeFile(filePicture.toString());
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmapToDisplay);
            }
            else {
                Toast.makeText(this, "File does not exist", Toast.LENGTH_LONG).show();
            }**/
        }else {
            Toast.makeText(this, "External disk is not mounted", Toast.LENGTH_LONG).show();
        }
    }
}
