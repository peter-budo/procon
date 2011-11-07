package procon.core;

public class AsyncStyleSetting {

    /*// TO DO: I hate this class so much, rewrite it some time.
    public class AsyncStyleSetting
    {
        // Animation

        public static int INT_ANIMATEDSETTING_SHOWRESULT_TICKS = 10;
        public static int INT_ANIMATEDSETTING_TIMEOUT_TICKS = 40; // IF after 10 seconds we have heard nothing, timeout.
        public static int INT_ICON_ANIMATEDSETTING_SET_FAILURE = 2;
        public static int INT_ICON_ANIMATEDSETTING_SET_SUCCESS = 1;
        public static int INT_ICON_ANIMATEDSETTING_START = 0;
        public bool m_blIgnoreEvent;
        public bool m_blReEnableControls;
        public bool m_blSuccess; // Set to false or unknown
        public Control m_ctrlResponseTarget;
        public int m_iTimeout;
        public object m_objOriginalValue;
        public PictureBox m_picStatus;
        public Control[] ma_ctrlEnabledInputs;

        public AsyncStyleSetting(PictureBox picStatus, Control ctrlResponseTarget, Control[] a_ctrlEnabledInputs,
                                  bool blReEnableControls)
        {
            this.m_picStatus = picStatus;
            this.m_picStatus.Tag = this;
            this.m_iTimeout = -1;
            this.m_ctrlResponseTarget = ctrlResponseTarget;
            this.ma_ctrlEnabledInputs = a_ctrlEnabledInputs;
            this.m_blReEnableControls = blReEnableControls;
            // Set to true so the first time does not trigger another.
            //this.m_blIgnoreEvent = true;
        }

        public bool IgnoreEvent
        {
            get
            {
                bool blOriginal = this.m_blIgnoreEvent;

                this.m_blIgnoreEvent = false;

                return blOriginal;
            }
            set { this.m_blIgnoreEvent = value; }
        }
    }*/
}
