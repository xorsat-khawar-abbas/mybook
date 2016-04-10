package net.xorsat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.xorsat.service.GCMService;

/**
 * Created by xorsat on 4/9/16.
 */
public class GCMReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setClass(context, GCMService.class);
        context.startService(intent);
        // call service(intent)
        // toast
        //llog
        // notify
    }
}
