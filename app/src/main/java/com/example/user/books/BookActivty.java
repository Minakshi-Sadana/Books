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
import java.util.Arrays;

public class BookActivty extends AppCompatActivity {
    ImageView imageView;
    ArrayList<String> imagePaths=new ArrayList<>();
    private static final int MY_PERMSSIONS=1;
    ViewPager viewPager;
    String booksFolder="Book";
    ArrayList<String> audioPaths=new ArrayList<>();

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
            getAudioOnPages();
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
    private void getAudioOnPages() {
        String imagePath, audioPath;
        String MEDIA_MOUNTED="mounted";
        String diskState=Environment.getExternalStorageState();
        if(diskState.equals(MEDIA_MOUNTED)){
            File audioFolder=new File(Environment.getExternalStorageDirectory(),booksFolder+"/38689/audio");
            File[] audioFiles=audioFolder.listFiles();
            Arrays.sort(audioFiles);
            audioPaths=new ArrayList<>();
            for(int j=0;j<audioFiles.length;j++){
                File file=audioFiles[j];
                String path=file.getAbsolutePath();
                audioPaths.add(path);
                for(int k=0;k<imagePaths.size();k++){
                    Log.e("Tag",imagePaths.get(k));
                    imagePath=imagePaths.get(k);
                    String nameImage=imagePath.substring(imagePath.lastIndexOf('/'));
                    String nameImg=nameImage.substring(1,3);
                    audioPath=audioPaths.get(j);
                    String nameAudio=audioPath.substring(audioPath.lastIndexOf('/'));
                    String nameAud=nameAudio.substring(1,3);
                    if(nameImg.equals(nameAud)){
                        Log.e("Tag","Attach audio here");
                    }
                    else {
                        Log.e("Tag","Don't attach audio here");
                    }
                }
            }

        }
    }

    private void getBitmapPublicFile() {
        String MEDIA_MOUNTED="mounted";
        String diskState=Environment.getExternalStorageState();
        if(diskState.equals(MEDIA_MOUNTED)){
      //      File pictureFolder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File pictureFolder=new File(Environment.getExternalStorageDirectory(),booksFolder+"/38689/pages");
            File[] files=pictureFolder.listFiles();
            Arrays.sort(files);
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
