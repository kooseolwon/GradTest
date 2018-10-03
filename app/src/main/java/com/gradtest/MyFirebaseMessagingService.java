package com.gradtest;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by SM-PC on 2018-10-02.
 */

public class MyFirebaseMessagingService extends  com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}
