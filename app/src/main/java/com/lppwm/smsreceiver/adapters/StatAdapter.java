package com.lppwm.smsreceiver.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lppwm.smsreceiver.MainActivity;
import com.lppwm.smsreceiver.R;
import com.lppwm.smsreceiver.models.SMSModel;
import com.lppwm.smsreceiver.models.StatModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {
    private List<StatModel> statList = new ArrayList<>();

    private final MainActivity activity;

    public StatAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statistic_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        StatModel item = statList.get(position);
        holder.name.setText(item.getName());
        holder.value.setText(String.valueOf(item.getValue()));
    }

    public int getItemCount() {
        return statList.size();
    }

    public void setStats(List<StatModel> statList) {
        this.statList = statList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView value;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.statisticName);
            value = view.findViewById(R.id.statisticValue);
        }
    }
}
