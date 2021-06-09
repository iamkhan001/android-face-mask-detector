package sg.mirobotic.facemask

import android.os.Bundle
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.robotemi.sdk.Robot
import sg.mirobotic.facemask.TimeOutUtils.OnTimeoutListener
import sg.mirobotic.robot.voice.RobotSpeech
import sg.mirobotic.robot.voice.RobotSpeech.Companion.getInstance

class MainActivity : AppCompatActivity() {

    private var robotSpeech: RobotSpeech? = null

    private var isSpeaking = false
    private var robot: Robot? = null

    companion object {
        private const val TAG = "MainA"
    }

    private val timeoutListener: OnTimeoutListener = object : OnTimeoutListener {
        override fun onTimeout() {
            Log.e(TAG, "Timeout")
            isSpeaking = false
            resetTimer()
        }
    }

    private fun resetTimer() {
        timeOutUtils.resetDisconnectTimer()
    }

    private val timeOutUtils = TimeOutUtils(timeoutListener)

    private val onSpeakListener: UtteranceProgressListener = object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String) {
            timeOutUtils.stopDisconnectTimer()
        }

        override fun onDone(utteranceId: String) {
            timeOutUtils.resetDisconnectTimer()
        }

        override fun onError(utteranceId: String) {
            timeOutUtils.resetDisconnectTimer()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        robotSpeech = getInstance(this, onSpeakListener)


        timeOutUtils.resetDisconnectTimer()
        robot = Robot.getInstance()

        robot?.tiltAngle(100)


    }

}