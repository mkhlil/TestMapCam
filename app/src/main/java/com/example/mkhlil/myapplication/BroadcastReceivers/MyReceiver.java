package com.example.mkhlil.myapplication.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private final String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";
    private final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast. if (intent.getAction().equals(ACTION_RECEIVE_SMS))
        // {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");

            final SmsMessage[] messages = new SmsMessage[pdus.length];

            Log.i(TAG, "Messages : " + messages);
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            if (messages.length > -1) {
                final String messageBody = messages[0].getMessageBody();
                final String phoneNumber = messages[0].getDisplayOriginatingAddress();

                Toast.makeText(context, "Expediteur : " + phoneNumber, Toast.LENGTH_LONG).show();
                Log.i(TAG, "Expediteur : " + phoneNumber);
                Toast.makeText(context, "Message : " + messageBody, Toast.LENGTH_LONG).show();
                Log.i(TAG, "Message : " + messageBody);

            }
        }
    }

}
