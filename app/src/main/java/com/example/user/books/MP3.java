package com.example.user.books;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MP3 extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);
        
      //  getAllAudioFromDevice(this);
        getMediaFileList();
    }

    private void getMediaFileList() {
        ContentResolver contentResolver=getApplicationContext().getContentResolver();
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.e("Tag","Uri: "+uri.toString());
        Cursor cursor=contentResolver.query(uri,null,null,null,null);
        if(cursor==null){
            Log.e("Tag","Query failed, handle error");
            Toast.makeText(getApplicationContext(),"Query failed, handle error",Toast.LENGTH_LONG).show();
        }
        else if(!cursor.moveToFirst()){
            Log.e("Tag","No music found on sd card");
            Toast.makeText(getApplicationContext(),"No music found on sd card",Toast.LENGTH_LONG).show();
        }
        else {
          //  int extension=cursor.getColumnIndex(MediaStore.Audio.Media.)
            int title=cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int id=cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            do{
                long thisId=cursor.getLong(id);
                Log.e("Tag"," "+thisId);
            //    Toast.makeText(getApplicationContext(),""+thisId,Toast.LENGTH_LONG).show();

                String thisTitle=cursor.getString(title);
              //  if(thisTitle.contains("mp3")) {
                    Toast.makeText(getApplicationContext(), "" + thisTitle, Toast.LENGTH_LONG).show();
                }//}
            while (cursor.moveToNext());
        }
    }

    private List<AudioModel> getAllAudioFromDevice(final Context context) {
        final List<AudioModel> audios=new ArrayList<>();
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    String[] projection={MediaStore.Audio.AudioColumns.DATA,MediaStore.Audio.AudioColumns.TITLE};
        Cursor c=context.getContentResolver().query(uri,projection,MediaStore.Audio.Media.DATA+"like?",new String[]
                {"%DCIM%"},null);
        if(c!=null){
            while (c.moveToNext()){
                AudioModel audioModel=new AudioModel();
                String path=c.getString(0);
                String name=c.getString(1);
                audioModel.setName(name);
                audioModel.setPath(path);
                Log.e("Name :" ,""+ name);
                Log.e("Path :" ,""+path);

                audios.add(audioModel);

            }
            c.close();
        }
        return audios;
    }


}
