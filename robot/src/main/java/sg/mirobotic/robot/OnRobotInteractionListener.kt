package sg.mirobotic.robot

interface OnRobotInteractionListener {

    fun speak(msg: String)

    fun stopSpeech()

}