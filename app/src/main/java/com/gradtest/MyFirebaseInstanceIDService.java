package com.gradtest;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by SM-PC on 2018-10-02.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("1510215"+token);
        sendRegistrationToServer(token);
    }
    private void sendRegistrationToServer(String token){

    }
}
