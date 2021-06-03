package sg.mirobotic.robot

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.csjbot.cosclient.constant.ClientConstant
import com.csjbot.cosclient.entity.CommonPacket
import com.csjbot.cosclient.entity.MessagePacket
import com.csjbot.cosclient.listener.EventListener
import org.json.JSONObject
import sg.mirobotic.face.csjsdk.R
import sg.mirobotic.robot.config.MyEvent
import sg.mirobotic.robot.config.Request
import sg.mirobotic.robot.services.RobotService
import sg.mirobotic.robot.voice.RobotSpeech

class Robot (private val activity: AppCompatActivity,
             private val onRobotResponseListener: OnRobotResponseListener,
             private val onVoiceRecognizeListener: OnVoiceRecognizeListener) {


    private val tag = Robot::class.java.simpleName

    private var robotSpeech: RobotSpeech? = null
    private var robotService: RobotService? = null

    private var eventListener: EventListener? = null
    private var failedListener: RobotService.OnEventFailedListener? = null

    init {
        wakeupWords = activity.resources.getStringArray(R.array.wakeup_words)
    }

    companion object {

        var wakeupWords: Array<String> = arrayOf()

        var robot: Robot? = null

        fun getInstance(activity: AppCompatActivity, onRobotResponseListener: OnRobotResponseListener, onVoiceRecognizeListener: OnVoiceRecognizeListener): Robot? {

            Log.e("Robot","Old Robot")
            if (robot == null) {
                robot = Robot(activity, onRobotResponseListener, onVoiceRecognizeListener)
                Log.e("Robot","New Robot")
            }

            return robot
        }

    }

    private val robotConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val robotServiceBinder = service as RobotService.RobotServiceBinder
            robotService = robotServiceBinder.service
            robotService?.connectRobot(eventListener, failedListener, 60002)

            Handler().postDelayed({
                try {
                    startFaceDetection()
                    onRobotResponseListener.robotConnected()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 2000)
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    fun connectRobot() {

        eventListener = EventListener { event ->
            activity.runOnUiThread {
                if (event.eventType == ClientConstant.EVENT_PACKET) {
                    val packet = event.data as MessagePacket
                    val json = (packet as CommonPacket).contentJson
                    handleResponse(json)
                }
            }
        }

        failedListener = RobotService.OnEventFailedListener {
            activity.runOnUiThread {

            }
        }


        val intent = Intent(activity, RobotService::class.java)
        intent.putExtra("url", "wwe")
        activity.bindService(intent, robotConnection, Context.BIND_AUTO_CREATE)
        activity.startService(intent)
    }


    fun startText2Speech(onSpeakListener: UtteranceProgressListener) {
        robotSpeech = RobotSpeech.getInstance(activity.applicationContext, onSpeakListener)
    }

    fun speak(text: String) {
        robotSpeech?.speak(text)
    }

    fun stopSpeech() {
        robotSpeech?.stop()
    }

    fun disconnect() {
        activity.unbindService(robotConnection)
        robotSpeech?.text2Speech?.stop()
        robotSpeech?.text2Speech?.shutdown()
    }

    private fun handleResponse(json: String) {
        try {
            val `object` = JSONObject(json)

            var errorCode = 0

            if (`object`.has("error_code")) {
                errorCode = `object`.getInt("error_code")
            } else if (`object`.has("ERROR_CODE")) {
                errorCode = `object`.getInt("ERROR_CODE")
            }

            val event = `object`.getString("msg_id")

            Log.d(tag, "RES >> $`object`")

            if (errorCode == 0) {

                when (event) {

                    Request.NAV_CANCEL_RES -> {
                        Log.e(tag, "Path Nav CANCELED")
                    }

                    Request.NAV_DESTINATION_REACHED_NOTIFICATION -> {
                        Log.e(tag, "Path Nav Complete")
                    }

                    Request.NAV_GET_STATUS_RSP -> {

                    }
                    Request.FACE_DELETE_RSP -> {
                        onRobotResponseListener.startFaceDetection()
                    }
                    Request.FACE_DETECT_NOTIFICATION -> {
                        onRobotResponseListener.faceDetect()
                    }
                }
            } else {
                Log.e(tag, "Error: $json")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun startFaceDetection() {
        val `object` = JSONObject()
        `object`.put("msg_id", Request.FACE_DELETE_REQ)
        sendData(`object`.toString())
    }

    private fun sendData(json: String) {
        try {
            Log.e(tag, "send data > $json")
            if (robotService != null) {
                robotService?.sendCommand(json)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    class RobotEvent(val event: MyEvent, val data: String)

    interface OnRobotResponseListener{

        fun robotConnected()
        fun startFaceDetection()
        fun faceDetect()

    }

    interface OnVoiceRecognizeListener {

        fun voiceResult(msg: String)
        fun onHotWord(word: String)
        fun onError(error: String)
        fun onSpeechRecognitionStarted()
        fun onSpeechRecognitionStopped()

    }



}