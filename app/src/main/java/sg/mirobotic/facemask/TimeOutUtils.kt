package sg.mirobotic.facemask

import android.os.Handler
import android.os.Looper
import android.util.Log

class TimeOutUtils(private val timeoutListener: OnTimeoutListener) {

    companion object {
        const val DISCONNECT_TIMEOUT: Long = 10000
    }

    private val disconnectHandler = Handler(Looper.getMainLooper()) {
        true
    }

    private val disconnectCallback = Runnable {
        timeoutListener.onTimeout()
    }

    fun resetDisconnectTimer() {
        Log.e("timeout","Reset Disconnect Timer")
        disconnectHandler.removeCallbacks(disconnectCallback)
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT)
    }

    fun setTimeout(){
        timeoutListener.onTimeout()
    }

    fun stopDisconnectTimer() {
        Log.e("timeout","Stop Disconnect Timer")
        disconnectHandler.removeCallbacks(disconnectCallback)
    }

    interface OnTimeoutListener {

        fun onTimeout()

    }


}