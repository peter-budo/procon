package procon;

public class PRoCon {


    /*public static PRoConApplication m_application;
    public static string[] m_Args;

    /// <summary>
    /// The main entry point for the application.
    /// </summary>
    [STAThread]

    public static void main(String[] args) {

        Program.m_Args = args;

        if (PRoConApplication.IsProcessOpen() == false) {

            if (Directory.Exists(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "Updates")) == true) {
                AutoUpdater.m_strArgs = args;
                AutoUpdater.BeginUpdateProcess(null);
            } else {
                //PRoConApplication paProcon = null;

                try {

                    boolean blBasicConsole = false;
                    boolean blGspUpdater = false;

                    int iValue;
                    if (args != null && args.Length >= 2) {
                        for (int i = 0; i < args.Length; i = i + 2) {
                            if (String.Compare("-console", args[i], true) == 0 &&int.
                            TryParse(args[i + 1], out iValue) == true && iValue == 1){
                                blBasicConsole = true;
                            }
                            if (String.Compare("-gspupdater", args[i], true) == 0 &&int.
                            TryParse(args[i + 1], out iValue) == true && iValue == 1){
                                blGspUpdater = true;
                            }
                        }
                    }

                    //Thread.Sleep(5000);
                    Application.EnableVisualStyles();
                    Application.SetCompatibleTextRenderingDefault(false);

                    if (blGspUpdater == true) {
                        Application.Run(new GspUpdater());
                    } else {

                        if (blBasicConsole == true) {
                            BasicConsole basicWindow = new BasicConsole();
                            basicWindow.WindowLoaded += new BasicConsole.WindowLoadedHandler(procon_WindowLoaded);

                            Application.Run(basicWindow);

                        } else {
                            frmMain mainWindow = new frmMain(args);
                            mainWindow.WindowLoaded += new frmMain.WindowLoadedHandler(procon_WindowLoaded);
                            Application.Run(mainWindow);
                        }

                    }
                } catch (Exception e) {

                    FrostbiteConnection.LogError("Application error", String.Empty, e);
                    MessageBox.Show("PRoCon ran into a critical error, but hopefully it logged that error in DEBUG.txt.  Please post/pm/email this to phogue at www.phogue.net.");
                } finally {
                    if (Program.m_application != null) {
                        Program.m_application.Shutdown();
                    }
                }
            }
        } else {
            // Possible prevention of a cpu consumption bug I can see at the time of writing.
            // TCAdmin: Start procon.exe
            // procon.exe has an update to install
            // procon.exe loads proconupdater.exe
            // procon.exe unloads
            // proconupdater.exe begins update
            // TCAdmin detects procon.exe shutdown - attempts to reload
            Thread.Sleep(50);
        }
    }

    static PRoConApplication procon_WindowLoaded(bool execute) {
        Program.m_application = new PRoConApplication(false, Program.m_Args);

        if (execute == true) {
            Program.m_application.Execute();
        }

        return Program.m_application;
    }*/

}
