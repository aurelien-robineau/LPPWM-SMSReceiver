package com.lppwm.smsreceiver.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lppwm.smsreceiver.MainActivity;
import com.lppwm.smsreceiver.R;
import com.lppwm.smsreceiver.models.SMSModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.ViewHolder> {
    private List<SMSModel> SMSList = new ArrayList<>();

    private final MainActivity activity;

    public SMSAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sms_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        SMSModel item = SMSList.get(position);
        holder.body.setText(item.getBody());

        if (position == 0) {
            holder.body.setPadding(14, 14, 14, 14);
            holder.body.setTextSize(22);
        }
    }

    public int getItemCount() {
        return SMSList.size();
    }

    public void setSMSs(List<SMSModel> SMSList) {
        this.SMSList = SMSList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView body;

        ViewHolder(View view) {
            super(view);
            body = view.findViewById(R.id.SMSBody);
        }
    }
}
