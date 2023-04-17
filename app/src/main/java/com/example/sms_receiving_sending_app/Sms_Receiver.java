package com.example.sms_receiving_sending_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

// WE HVA TO MENTION IT INSIDE THE MANIFEST
// ALSO WE HAVE TO GIVE THE USER PERMISON FOR RECEIVING AND READING THE MSG
// ALSO WE HAVE TO MENTION THE INTENT FILTER FOR RECEIVED THE PARTICULAR TYPE OF BROADCAST
// BECOZ WE HAVE MULTIPLE BROADCAST
public class Sms_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
  // BUNDLE IS USED TO EXTRACT OR GET THE EXTRA
        Bundle bundle = intent.getExtras();
      // PDUS ARRAY OF BYTE    is is a object kind of things it is a static data memorised it
        // FETCHING OF THE MSG
         Object [] sms_object =  (Object[]) bundle.get("pdus");
         // NOW RECEIVE ALL THE MSG FROM THE OBJECT ARRAY
        for (Object obj : sms_object){
            SmsMessage msg = SmsMessage.createFromPdu((byte[]) obj);
            // THROUGH THIS WE GET THE ORIGIN OF MSG MEAN FROM WHERE THE MSG HAS RECEIVED
            String mobileNo = msg.getDisplayOriginatingAddress();
            // DISPLAY THE MSG
            String displmsg =  msg.getDisplayMessageBody();
            // FRO RECEIVE THE BROADCAST WE MUST HAVE ALLOW PERMISSON FROM OUR REALTIME DEVICES SETTINGS
            Log.d("Msg_Details" , "Mobile no :"+ mobileNo +"Message :"+ msg );
            // FOR SEND THE MESSAGE
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("9026420905" , null , "hello ", null , null);
        }

    }
}
