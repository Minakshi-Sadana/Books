package com.example.user.books;

import android.Manifest;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean isAvailable;
    boolean isReadable;
    FileInputStream fileInputStream;
    ListView books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   books=findViewById(R.id.booksList);
        Intent intent=new Intent(MainActivity.this,BookActivty.class);
        startActivity(intent);

       // books.setAdapter(new CustomAdapter(MainActivity.this,readPDFs()));
    }

    private ArrayList<PDFData> readPDFs() {
        ArrayList<PDFData> pdfDocs=new ArrayList<>();
        File downloadFolder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        PDFData pdfData;
        if(downloadFolder.exists()){
            File[] files=downloadFolder.listFiles();
            for(int i=0;i<files.length;i++){
                File file=files[i];
                //PdfReader
                if(file.getPath().endsWith("pdf")){
                    pdfData=new PDFData();
                    pdfData.setName(file.getName());
                    pdfData.setPath(file.getAbsolutePath());
                   // pdfData.setPages();
                    pdfDocs.add(pdfData);
                }
            }
        }
        return pdfDocs;
    }

    private void readData() {
      //  File folder=Environment.getExternalStorageDirectory();
        File folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile=new File(folder,"100 Questions.pdf");
        try{
            if(myFile.exists()) {
                fileInputStream = new FileInputStream(myFile);
                StringBuffer buffer = new StringBuffer();
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    buffer.append((char) i);
                }
                fileInputStream.close();
                String details = buffer.toString();
                Log.e("TAG", details);
                Toast.makeText(getApplicationContext(),details,Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    /** private boolean isExternalStorageAvailable() {
        String extStorageState=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
            return true;
        }
        return false;
    }

    private boolean isExternalStorageReadOnly() {
        String extStorageState=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
            return true;
        }
        return false;
    }**/
}
