package sg.mirobotic.robot.config;

public class Request {

    /**
     * voice features
     */

    public static final String SPEECH_START_MULTI_RECOG_REQ = "SPEECH_ISR_START_REQ";
    public static final String SPEECH_START_MULTI_RECOG_RSP = "SPEECH_ISR_START_RSP";
    public static final String SPEECH_STOP_MULTI_RECOG_REQ = "SPEECH_ISR_STOP_REQ";
    public static final String SPEECH_STOP_MULTI_RECOG_RSP = "SPEECH_ISR_STOP_RSP";
    public static final String SPEECH_TO_TEXT_NOTIFICATION = "SPEECH_ISR_ONLY_RESULT_NTF";
    public static final String SPEECH_ANSWERE_LAST_RSP = "SPEECH_ISR_LAST_RESULT_NTF";
    public static final String SPEECH_FROM_TEXT_REQ = "SPEECH_TTS_REQ";
    public static final String SPEECH_FROM_TEXT_RSP = "SPEECH_TTS_RSP";
    public static final String SPEECH_SETTING_LANG_REQ = "SPEECH_SET_ISR_PARAM_CMD";
    public static final String SPEECH_SETTING_TTS = "SPEECH_SET_TTS_PARAM_CMD";
    public static final String SPEECH_STOP_RECOG_REQ = "SPEECH_READ_STOP_REQ";
    public static final String SPEECH_STOP_RECOG_RSP = "SPEECH_READ_STOP_RSP";
    public static final String SPEECH_NOTIFICATION_READ_END = "SPEECH_READ_OVER_NTF";
    public static final String SPEECH_NOTIFICATION_WAKEUP = "SPEECH_ISR_WAKEUP_NTF";
    public static final String SPEECH_NOTIFICATION_VOICE_ERROR = "SPEECH_ISR_ERROR_NTF";
    public static final String SPEECH_SEARCH_SEMANTICS_REQ = "CUSTSERVICE_GET_RESULT_REQ";
    public static final String SPEECH_SEARCH_SEMANTICS_RSP = "CUSTSERVICE_GET_RESULT_RSP";
    public static final String SPECH_SET_KEYWORD_REQ = "SPEECH_SET_USERWORDS_REQ";
    public static final String SPECH_SET_KEYWORD_RSP = "SPEECH_SET_USERWORDS_RSP";
    public static final String SPECH_GET_KEYWORD_REQ = "SPEECH_GET_USERWORDS_REQ";
    public static final String SPECH_GET_KEYWORD_RSP = "SPEECH_GET_USERWORDS_RSP";


    /**
     * VIDEO FEATURES
     */

    public static final String VIDEO_DETECT_OPEN_REQ = "FACE_DETECT_OPEN_VIDEO_REQ";
    public static final String VIDEO_DETECT_OPEN_RSP = "FACE_DETECT_OPEN_VIDEO_RSP";
    public static final String VIDEO_DETECT_CLOSE_REQ = "FACE_DETECT_CLOSE_VIDEO_REQ";
    public static final String VIDEO_DETECT_CLOSE_RSP = "FACE_DETECT_CLOSE_VIDEO_RSP";

    /**
     * FACE RECOGNITION FEATURES
     */

    public static final String PHOTO_CAPTURE_REQ = "FACE_SNAPSHOT_REQ";
    public static final String PHOTO_CAPTURE_RSP = "FACE_SNAPSHOT_RESULT_RSP";

    public static final String FACE_SAVE_REQ = "FACE_SAVE_REQ";
    public static final String FACE_SAVE_RSP = "FACE_SAVE_RSP";
    public static final String FACE_DELETE_REQ = "FACE_DATA_DEL_REQ";
    public static final String FACE_DELETE_RSP = "FACE_DATA_DEL_RSP";
    public static final String FACE_DETECT_NOTIFICATION = "FACE_DETECT_PERSON_NEAR_NTF";
    public static final String FACE_RECOG_NOTIFICATION = "FACE_DETECT_FACE_LIST_NTF";
    public static final String FACE_GET_SAVED_REQ = "FACE_DATABASE_REQ";
    public static final String FACE_GET_SAVED_RSP = "FACE_DATABASE_RSP";

    public static final String FACE_START_FOLLOW_REQ = "FACE_FOLLOW_START_REQ";
    public static final String FACE_START_FOLLOW_RSP = "FACE_FOLLOW_START_RSP";
    public static final String FACE_STOP_FOLLOW_REQ = "FACE_FOLLOW_CLOSE_REQ";
    public static final String FACE_STOP_FOLLOW_RSP = "FACE_FOLLOW_CLOSE_RSP";

    public static final String TEMP_OPEN_RSP = "TEMPERATURE_OPEN_RSP";
    public static final String TEMP_INFO_RSP = "TEMPERATURE_INFO_RSP";
    public static final String TEMP_CLOSE_RSP = "TEMPERATURE_CLOSE_RSP";


    public static final String DANCE_START_REQ = "ROBOT_DANCE_START_REQ";
    public static final String DANCE_START_RSP = "ROBOT_DANCE_START_RSP";
    public static final String DANCE_STOP_REQ = "ROBOT_DANCE_STOP_REQ";
    public static final String DANCE_STOP_RSP = "ROBOT_DANCE_STOP_RSP";


    public static final String NAV_LIST_REQ = "NAVI_GET_MAPLIST_REQ";
    public static final String NAV_LIST_RSP = "NAVI_GET_MAPLIST_RSP";

    public static final String NAV_CURRUNT_POS_REQ = "NAVI_GET_CURPOS_REQ";
    public static final String NAV_CURRUNT_POS_RSP = "NAVI_GET_CURPOS_RSP";

    public static final String NAV_CANCEL_REQ = "NAVI_ROBOT_CANCEL_REQ";
    public static final String NAV_CANCEL_RES = "NAVI_ROBOT_CANCEL_RSP";

    public static final String NAV_GO_HOME_REQ = "NAVI_GO_HOME_REQ";
    public static final String NAV_GO_HOME_RSP = "NAVI_GO_HOME_RSP";

    public static final String NAV_GO_TO_REQ = "NAVI_ROBOT_MOVE_TO_REQ";
    public static final String NAV_GO_TO_RSP = "NAVI_ROBOT_MOVE_TO_RSP";

    public static final String NAV_SET_MAP_REQ = "NAVI_SET_MAP_REQ";
    public static final String NAV_SET_MAP_RSP = "NAVI_SET_MAP_RSP";

    public static final String NAV_GET_STATUS_REQ = "NAVI_GET_STATUS_REQ";
    public static final String NAV_GET_STATUS_RSP = "NAVI_GET_STATUS_RSP";

    public static final String NAV_SAVE_MAP_REQ = "NAVI_GET_MAP_REQ";
    public static final String NAV_SAVE_MAP_RSP = "NAVI_GET_MAP_RSP";

    public static final String NAV_GET_MAP_STATUS_REQ = "NAVI_GET_MAPSTATUS_REQ";
    public static final String NAV_GET_MAP_STATUS_RSP = "NAVI_GET_MAPSTATUS_RSP";

    public static final String NAV_DESTINATION_REACHED_NOTIFICATION = "NAVI_ROBOT_RFID_NTF";


}
