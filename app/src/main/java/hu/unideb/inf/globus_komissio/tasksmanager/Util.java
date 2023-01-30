package hu.unideb.inf.globus_komissio.tasksmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static final String LOG_TAG = "BackgroundThread";
    public static final int TASKS_CANCELLED = 1;
    public static final int ROOM_CREATE_FAIL = 2;
    public static final int PROCESS_FINISH_1 = 3;
    public static final int ROOM_SEND_FAIL = 4;
    public static final int SQL_READ_FAIL = 5;
    public static final int PROCESS_FINISH_2 = 6;
    public static final int PROCESS_FINISH_3 = 7;
    public static final String MESSAGE_BODY = "MESSAGE_BODY";

    public static String getReadableTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.format(new Date());
    }

    public static Message createMessage(int id, String dataString) {
        Bundle bundle = new Bundle();
        bundle.putString(Util.MESSAGE_BODY, dataString);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }
}
