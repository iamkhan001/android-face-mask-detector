package sg.mirobotic.robot.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.csjbot.cosclient.CosClientAgent;
import com.csjbot.cosclient.entity.CommonPacket;
import com.csjbot.cosclient.entity.MessagePacket;
import com.csjbot.cosclient.listener.EventListener;
import com.csjbot.cosclient.utils.CosLogger;

import org.json.JSONObject;


public class RobotService extends Service {
    private static final String TAG = RobotService.class.getSimpleName();
    private final IBinder binder = new RobotServiceBinder();
    private CosClientAgent cosClientAgent;
    private OnEventFailedListener failedListener;

    public RobotService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "on create called");

    }

    public void connectRobot(EventListener eventListener, OnEventFailedListener failedListener, int port) {
        this.failedListener = failedListener;

        cosClientAgent = CosClientAgent.createRosClientAgent(eventListener, true);
        cosClientAgent.connect("192.168.99.101", port);
        Log.i(TAG, "Connected to Server");
    }

    public void startHandMovement() {
        Log.i(TAG, "Hand Movement Started");

        JSONObject naviMoveHandReq = new JSONObject();
        try {
            naviMoveHandReq.put("msg_id", "ROBOT_ARM_LOOP_START_REQ");
            naviMoveHandReq.put("interval_time", 10000);
            String request = naviMoveHandReq.toString();
            sendCommand(request);
            Log.i(TAG, "Hand Movement Request sent to server. " + request);
        } catch (Exception ex) {
            Log.e(TAG, "Hand Movement Request failed.", ex);
        }
    }

    public void stopHandMovement() {
        Log.i(TAG, "Hand Movement Stop called");

        JSONObject naviMoveHandStopReq = new JSONObject();
        try {
            naviMoveHandStopReq.put("msg_id", "ROBOT_ARM_LOOP_STOP_REQ");
            String request = naviMoveHandStopReq.toString();
            sendCommand(request);
            Log.i(TAG, "Hand Movement Stop Request sent to server." + request);
        } catch (Exception ex) {
            Log.e(TAG, "Hand Movement Request failed.", ex);
        }
    }

    public void sendCommand(String json) {
        if (TextUtils.isEmpty(json)) return;
        try {
            CosLogger.info(json);
        } catch (Exception e) {
            e.printStackTrace();
            failedListener.onEventFailed("Failed: " + e.getMessage());
        }
        try {
            MessagePacket packet = new CommonPacket(json.getBytes());

            cosClientAgent.sendMessage(packet);
        } catch (Exception e) {
            CosLogger.error("BaseClientReq:sendReq:e:" + e.toString());
            failedListener.onEventFailed("Failed: " + e.getMessage());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        disconnectRobot();
    }

    public void disconnectRobot() {
        if (cosClientAgent != null && cosClientAgent.isConnected()) {
            cosClientAgent.disConnect();
            Log.i(TAG, "disconnected cosClientAgent.");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        String url = intent.getStringExtra("url");

        return binder;
    }

    public interface OnEventFailedListener {
        void onEventFailed(String cause);
    }

    public class RobotServiceBinder extends Binder {
        public RobotService getService() {
            return RobotService.this;
        }
    }

}
