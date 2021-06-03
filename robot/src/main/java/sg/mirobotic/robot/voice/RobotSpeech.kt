package sg.mirobotic.robot.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import android.util.Log
import java.util.*
import kotlin.collections.HashSet


class RobotSpeech(
    context: Context,
    onSpeakListener: UtteranceProgressListener
) {

    companion object {

        private const val VOICE = "en-au-x-afh-local"

        private const val TAG = "RobotSpeech"
        var robotSpeech: RobotSpeech? = null

        @JvmStatic
        fun getInstance(
            context: Context,
            onSpeakListener: UtteranceProgressListener
        ): RobotSpeech? {

            if (robotSpeech == null) {

                robotSpeech = RobotSpeech(context, onSpeakListener)

            }

            return robotSpeech
        }

    }

    var text2Speech: TextToSpeech? = null


    init {


        text2Speech = TextToSpeech(context,
            TextToSpeech.OnInitListener {

                if(it != TextToSpeech.ERROR) {
                    text2Speech?.setOnUtteranceProgressListener(onSpeakListener)

                    val features = HashSet<String>()
                    features.add("female")

                    var voice: Voice? = null

                    try {
                        val list = text2Speech?.voices

                        if (list != null && list.size > 0) {

                            for (lang in list) {
                                Log.d(TAG,"voice: lang: $lang")

                                if (lang.name == VOICE) {
                                    voice = lang
                                    Log.e(TAG,"voice: ${voice.name}")
                                    break
                                }

                            }

                        }else {
                            Log.e(TAG,"voice: list is empty")
                        }
                    }catch (e: Exception) {
                        e.printStackTrace()
                    }


                    if (voice == null) {

                        voice = Voice(voice, Locale.ENGLISH
                            , 400, 200, false, features )
                        Log.e(TAG,"voice: def voice")

                    }
                    Log.e(TAG,"voice: final >> ${voice.name}")
                    text2Speech?.voice = voice

                }

            })
    }

    fun speak(text: String) {
        Log.e(TAG,"SPEAK >> $text")
        text2Speech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun stop(){
        text2Speech?.stop()
    }

    fun speakAdd(text: String) {
        text2Speech?.speak(text, TextToSpeech.QUEUE_ADD, null, "")
    }
}