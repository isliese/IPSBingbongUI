package com.example.ipsbingbongui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ipsbingbongui.ImageViewModel;
import com.example.ipsbingbongui.MainActivity;
import com.example.ipsbingbongui.R;
import com.example.ipsbingbongui.SubActivity;

public class ForestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.entire_forest);

    // 버튼 초기화
    ImageButton eachtree = (ImageButton) findViewById(R.id.each_tree);

    // each_tree 버튼 클릭 이벤트 처리 => each_tree 이미지를 클릭하면 날짜별 일기 페이지로 넘어가도록
        eachtree.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 구매 링크로 이동하는 Intent 생성
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            // Intent 실행
            startActivity(intent);
        }
    });
}
