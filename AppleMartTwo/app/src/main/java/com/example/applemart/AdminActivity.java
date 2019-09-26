package com.example.applemart;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.applemart.R.color.colorPrimary;
import static com.example.applemart.R.color.colorPrimaryDark;

public class AdminActivity extends AppCompatActivity {
    TextView order,product,banner;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        order = findViewById(R.id.orders_text);
        product = findViewById(R.id.products_text);
        banner = findViewById(R.id.banners_text);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        viewPager = findViewById(R.id.fragment_container);
        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {
                onChangeTabe(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onChangeTabe(int position) {
        if (position == 0){
            order.setTextSize(17);
            order.setTextColor(getColor(colorPrimaryDark));
            order.setBackground(getDrawable(R.drawable.fragment_button));
            product.setTextSize(15);
            product.setTextColor(getColor(colorPrimary));
            banner.setTextSize(15);
            banner.setTextColor(getColor(colorPrimary));
        }if (position == 1){
            product.setTextSize(17);
            product.setTextColor(getColor(colorPrimaryDark));
            product.setBackground(getDrawable(R.drawable.fragment_button));
            order.setTextSize(15);
            order.setTextColor(getColor(colorPrimary));
            banner.setTextSize(15);
            banner.setTextColor(getColor(colorPrimary));
        }if (position == 2){
            banner.setTextSize(17);
            banner.setTextColor(getColor(colorPrimaryDark));
            banner.setBackground(getDrawable(R.drawable.fragment_button));
            product.setTextSize(15);
            product.setTextColor(getColor(colorPrimary));
            order.setTextSize(15);
            order.setTextColor(getColor(colorPrimary));
        }
    }
}
