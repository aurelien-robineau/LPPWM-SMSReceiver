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
import com.lppwm.smsreceiver.adapters.StatAdapter;
import com.lppwm.smsreceiver.models.StatModel;

import java.util.ArrayList;
import java.util.List;

public class StatsTab extends Fragment {
    private static StatsTab instance = null;

    private final MainActivity mActivity;
    private RecyclerView SMSRecyclerView;
    private StatAdapter statAdapter;

    private List<StatModel> statList;

    public StatsTab(MainActivity activity) {
        super();
        mActivity = activity;
    }

    public static void setInstance(StatsTab statsTab) {
        instance = statsTab;
    }

    public static StatsTab getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.activity_stats, container, false);

        statList = new ArrayList<>();

        SMSRecyclerView = root.findViewById(R.id.StatsRecyclerView);
        SMSRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        statAdapter = new StatAdapter(mActivity);
        SMSRecyclerView.setAdapter(statAdapter);

        StatModel nbSMSReceivedStat = new StatModel("Nombre de SMS reçus", "0");
        addOrUpdateStat(nbSMSReceivedStat);

        return root;
    }

    public void addOrUpdateStat(StatModel stat) {
        StatModel existing = getStatByName(stat.getName());

        if (existing != null) {
            existing.setValue(stat.getValue());
        }
        else {
            statList.add(stat);
        }

        statAdapter.setStats(statList);
    }

    public StatModel getStatByName(String name) {
        for (StatModel stat: statList) {
            if (stat.getName().equals(name)) {
                return stat;
            }
        }

        return null;
    }

    public void updateState(int statId, String value) {
        for (StatModel stat: statList) {
            if (stat.getId() == statId) {
                stat.setValue(value);
                break;
            }
        }
        statAdapter.setStats(statList);
    }
}
