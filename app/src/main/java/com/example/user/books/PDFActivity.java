package com.example.user.books;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;

public class PDFActivity extends AppCompatActivity {
   // Scroll
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView=findViewById(R.id.pdfView);

       // pdfView.useBestQuality(true);

        Intent i=this.getIntent();
        String path=i.getExtras().getString("PATH");

        File file=new File(path);
        if(file.canRead()){
            pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PDFActivity.this,"No. of pages: "+String.valueOf(nbPages),Toast.LENGTH_LONG).show();
                }
            }).load();
        }
    }
}
