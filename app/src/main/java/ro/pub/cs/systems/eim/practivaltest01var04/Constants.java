package ro.pub.cs.systems.eim.practivaltest01var04;

public class Constants {
    public static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    public static final int SERVICE_STARTED = 1;
    public static final int SERVICE_STOPPED = 0;
    public static final int SLEEP_TIME = 5000;
    final public static int THRESHOLD = 5;
    final public static String PROCESSING_THREAD_TAG = "[Processing Thread]";

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";
    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.action"
    };
}
