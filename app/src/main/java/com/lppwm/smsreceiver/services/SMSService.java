package com.lppwm.smsreceiver.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.SmsMessage;

import androidx.annotation.Nullable;

import com.lppwm.smsreceiver.models.SMSModel;
import com.lppwm.smsreceiver.models.StatModel;
import com.lppwm.smsreceiver.receivers.SMSReceiver;
import com.lppwm.smsreceiver.ui.tabs.SMSTab;
import com.lppwm.smsreceiver.ui.tabs.StatsTab;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SMSService extends Service {
    SMSReceiver smsReceiver;

    static int nbSmsReceived = 0;
    static TreeMap<String, Integer> wordsReceived = new TreeMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        smsReceiver = new SMSReceiver();
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void smsReceived(SmsMessage sms)
    {
        // Update number of SMS received
        nbSmsReceived++;

        SMSModel SMS = new SMSModel(sms.getMessageBody());
        SMSTab.getInstance().addSMS(SMS);

        StatsTab.getInstance().updateState(0, Integer.toString(nbSmsReceived));

        String[] words = SMS.getBody().split(" ");

        // Update words received
        ArrayList<String> countedWords = new ArrayList<>();
        for (String word: words) {
            if (!countedWords.contains(word)) {
                countedWords.add(word);
                Integer wordCount = wordsReceived.get(word);
                wordsReceived.put(word, wordCount == null ? 1 : wordCount + 1);
            }
        }

        updateStats();
    }

    public static int getNbSmsReceived()
    {
        return nbSmsReceived;
    }

    public static void updateStats()
    {
        for (Map.Entry<String, Integer> entry: wordsReceived.entrySet()) {
            int percentage = (int) (100 * ((float) entry.getValue() / nbSmsReceived));
            String value = "Mot présent dans " + percentage + "% des SMS";
            StatsTab.getInstance().addOrUpdateStat(new StatModel(entry.getKey(), value));
        }
    }
}
