package com.example.bookshopfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowBooks extends AppCompatActivity {
    DatabaseReference databasePlayers;
    BookImage productInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);
        databasePlayers= FirebaseDatabase.getInstance().getReference().child("bookimages");
        Resources r = getResources();
        float pxLeftMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics());
        float pxTopMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics());
        float pxRightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        float pxBottomMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, r.getDisplayMetrics());

        final LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(Math.round(pxLeftMargin), Math.round(pxTopMargin), Math.round(pxRightMargin), Math.round(pxBottomMargin));
        linearLayout.setLayoutParams(params);
        setContentView(linearLayout);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    productInfo = postSnapshot.getValue(BookImage.class);
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(productInfo.getImagePath());
                    int width = 360;
                    int height = 360;
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(width,height));
                    linearLayout.addView(imageView);

                    TextView valueTV = new TextView(getApplicationContext());
                    valueTV.setText(productInfo.getImageName());
                    valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    valueTV.setTextColor(Color.parseColor("#000000"));
                    linearLayout.addView(valueTV);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(getApplicationContext(),SingleProductView.class);
                            i.putExtra("personImage", productInfo.getImagePath());
                            i.putExtra("personName",productInfo.getImageName());
                            startActivity(i);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        databasePlayers.addListenerForSingleValueEvent(eventListener);
    }
}