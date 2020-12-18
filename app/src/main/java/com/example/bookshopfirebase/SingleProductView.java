package com.example.bookshopfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SingleProductView extends AppCompatActivity {
    TextView productname,price,type,label_price,label_type;
    ImageView imageView;
    LinearLayout l1;
    Button addCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_view);


        productname=findViewById(R.id.txt_productname);
        label_price=findViewById(R.id.txt_price);
        price=findViewById(R.id.price);
        imageView=findViewById(R.id.imageView);
        l1=findViewById(R.id.linear);
        addCart=findViewById(R.id.btn_addCart);


        Intent intent=getIntent();


        productname.setText(intent.getStringExtra("personName"));
        imageView.setImageResource(intent.getIntExtra("personImage",0));
        price.setText(intent.getStringExtra("price"));



    }
}