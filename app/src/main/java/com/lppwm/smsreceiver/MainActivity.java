package com.lppwm.smsreceiver;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.lppwm.smsreceiver.adapters.SMSAdapter;
import com.lppwm.smsreceiver.models.SMSModel;
import com.lppwm.smsreceiver.services.SMSService;
import com.lppwm.smsreceiver.ui.SectionsPagerAdapter;
import com.lppwm.smsreceiver.ui.tabs.SMSTab;
import com.lppwm.smsreceiver.ui.tabs.StatsTab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tabs
        SMSTab smsTab = new SMSTab(this);
        SMSTab.setInstance(smsTab);

        StatsTab statsTab = new StatsTab(this);
        StatsTab.setInstance(statsTab);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // Catch SMS service
        Intent iSMS = new Intent(this, SMSService.class);
        startService(iSMS);

        requestPermission();
    }

    private void requestPermission()
    {
        String[] perm = new String[]{Manifest.permission.RECEIVE_SMS};
        ActivityCompat.requestPermissions(this, perm, 200);
    }
}