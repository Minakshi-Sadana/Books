package com.example.user.books;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<PDFData> pdfDataArrayList;

    public CustomAdapter(Context context, ArrayList<PDFData> pdfDataArrayList) {
        this.context = context;
        this.pdfDataArrayList = pdfDataArrayList;
    }

    @Override
    public int getCount() {
        return pdfDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return pdfDataArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false);
        }
        final PDFData pdfData= (PDFData) this.getItem(i);
        ImageView book=view.findViewById(R.id.bookIcon);
        TextView bookName=view.findViewById(R.id.bookTitle);
        TextView bookPages=view.findViewById(R.id.bookPages);

        bookName.setText(pdfData.getName());
        bookPages.setText("Total Pages: "+pdfData.getPages());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPDFView(pdfData.getPath());
            }
        });

        return view;
    }

    private void openPDFView(String path) {
        Intent intent=new Intent(context,PDFActivity.class);
        intent.putExtra("PATH",path);
        context.startActivity(intent);
    }
}
