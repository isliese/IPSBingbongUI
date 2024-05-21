package com.example.ipsbingbongui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    private ImageViewModel imageViewModel;
    private LinearLayout imageContainer;

    // tree image의 크기 상수로 지정
    private final int imageWidth = 81;
    private final int imageHeight = 74;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forest);


        // 데이터베이스에 새로운 데이터가 추가되면 이미지 생성
        imageContainer = findViewById(R.id.imageContainer);
        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        imageViewModel.getAllImages().observe(this, new Observer<List<ImageEntity>>() {
            @Override
            public void onChanged(List<ImageEntity> images) {
                updateImages(images);
            }
        });




        // 버튼 초기화
        ImageButton entireForestButton = (ImageButton) findViewById(R.id.entire_forest_button);

        // entireForestButton 버튼 클릭 이벤트 처리
        entireForestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 구매 링크로 이동하는 Intent 생성
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                // Intent 실행
                startActivity(intent);
            }
        });


        // 나뭇잎1
        ImageView imageView1 = findViewById(R.id.leaf1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "2004.12.29", Toast.LENGTH_SHORT).show();
            }
        });
        // 나뭇잎2
        ImageView imageView2 = findViewById(R.id.leaf2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "2004.03.10", Toast.LENGTH_SHORT).show();
            }
        });
        // 나뭇잎3
        ImageView imageView3 = findViewById(R.id.leaf3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "2004.5.07", Toast.LENGTH_SHORT).show();
            }
        });

    }




    // ImageView가 클릭 됐을 때 실행되는 클릭 handler
    public void displayToast(String date) {
        Toast.makeText(getApplication(),date,Toast.LENGTH_SHORT).show();
    }
    public void showDateOrder(View view) {
        displayToast(getString(R.string.date));

    }


    // 데이터베이스에 새로운 데이터가 추가되면 이미지 생성
    private void updateImages(List<ImageEntity> images) {
        imageContainer.removeAllViews();
        for (ImageEntity image : images) {
            ImageView imageView = new ImageView(this);
            // Glide 또는 Picasso를 사용하여 이미지 로드
            Glide.with(this).load(image.getImageUrl()).into(imageView);
            imageContainer.addView(imageView);
        }
    }

    
    // 랜덤 위치에 tree 이미지 추가 (나무 이미지끼리는 겹치지 않도록 함)
    private void addTreeImage() {
        ImageButton imageButton = new ImageButton(this);
        imageButton.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));
        imageButton.setImageResource(R.drawable.entire_forest_tree); // 이미지 리소스 설정
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageButton.setBackground(null);

        // 이미지가 겹치지 않도록 배치하는 로직
        int x, y;
        Random random = new Random();
        boolean positionFound;

        do {
            positionFound = true;
            x = random.nextInt(imageContainer.getWidth() - imageWidth);
            y = random.nextInt(imageContainer.getHeight() - imageHeight);

            for (int i = 0; i < imageContainer.getChildCount(); i++) {
                ImageButton existingImage = (ImageButton) imageContainer.getChildAt(i);
                int existingX = (int) existingImage.getX();
                int existingY = (int) existingImage.getY();

                if (isOverlapping(x, y, existingX, existingY)) {
                    positionFound = false;
                    break;
                }
            }
        } while (!positionFound);

        imageButton.setX(x);
        imageButton.setY(y);
        imageContainer.addView(imageButton);
    }

    private boolean isOverlapping(int x1, int y1, int x2, int y2) {
        return x1 < x2 + imageWidth && x1 + imageWidth > x2 &&
                y1 < y2 + imageHeight && y1 + imageHeight > y2;
    }
    
    
    
}
