package com.lppwm.smsreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.lppwm.smsreceiver.services.SMSService;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Message received", Toast.LENGTH_SHORT).show();

        Bundle message = intent.getExtras();
        Object[] pdus = (Object[]) message.get("pdus");
        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[0]);

        SMSService.smsReceived(sms);
    }
}
