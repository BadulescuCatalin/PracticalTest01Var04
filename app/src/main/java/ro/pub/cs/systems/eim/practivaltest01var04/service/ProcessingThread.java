package ro.pub.cs.systems.eim.practivaltest01var04.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

import ro.pub.cs.systems.eim.practivaltest01var04.Constants;

public class ProcessingThread extends Thread {

    private Context context;
    private boolean isRunning = true;
    Random random = new Random();
    private String nume;
    private String grupa;
    public ProcessingThread(Context context, String nume, String grupa) {
        this.context = context;
        this.nume = nume;
        this.grupa = grupa;
    }

    @Override
    public void run() {
        Log.d("Thread", "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while(isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + nume + " " + grupa);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            Log.e("Thread", Objects.requireNonNull(interruptedException.getMessage()));
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}