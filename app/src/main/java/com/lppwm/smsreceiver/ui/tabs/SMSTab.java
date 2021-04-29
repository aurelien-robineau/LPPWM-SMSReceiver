package com.lppwm.smsreceiver.ui.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lppwm.smsreceiver.MainActivity;
import com.lppwm.smsreceiver.R;
import com.lppwm.smsreceiver.adapters.SMSAdapter;
import com.lppwm.smsreceiver.models.SMSModel;

import java.util.ArrayList;
import java.util.List;

public class SMSTab extends Fragment {
    private static SMSTab instance = null;

    private final MainActivity mActivity;
    private RecyclerView SMSRecyclerView;
    private SMSAdapter SMSAdapter;

    private List<SMSModel> SMSList;

    public SMSTab(MainActivity activity) {
        super();
        mActivity = activity;
    }

    public static void setInstance(SMSTab smsTab) {
        instance = smsTab;
    }

    public static SMSTab getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.activity_sms, container, false);

        SMSList = new ArrayList<>();

        SMSRecyclerView = root.findViewById(R.id.SMSRecyclerView);
        SMSRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        SMSAdapter = new SMSAdapter(mActivity);
        SMSRecyclerView.setAdapter(SMSAdapter);

        return root;
    }

    public void addSMS(SMSModel SMS) {
        SMSList.add(0, SMS);
        SMSAdapter.setSMSs(SMSList);
    }
}
