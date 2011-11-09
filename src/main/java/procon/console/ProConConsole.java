package procon.console;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import procon.core.PRoConApplication;

public class ProConConsole {
    private static final Logger LOG = LoggerFactory.getLogger(ProConConsole.class);

    public static void main(String[] args) {

        String appId = "ProConConsole";
        boolean alreadyRunning;
        try {
            JUnique.acquireLock(appId);
            alreadyRunning = false;
        } catch (AlreadyLockedException e) {
            alreadyRunning = true;
            LOG.error("Application is already running");
        }
        if (!alreadyRunning) {/*
            // Start sequence here
        }

        if (PRoConApplication.isProcessOpen() == false) {
            try {*/
                PRoConApplication application = new PRoConApplication(true, args);

                /*TODO this maybe obsolete
                System.out.println("PRoCon Frostbite");
                System.out.println("================");
                System.out.println("This is a cut down version of PRoCon.exe to be used by GSPs and PRoCon Hosts.");
                System.out.println("Executing this file is the same as \"PRoCon.exe -console 1\"");
                System.out.println("No output is given.  This is as cut down as we're gunno get..");
                System.out.println("\nExecuting procon...");
                application.execute();

                System.out.println("Running... (Press any key to shutdown)");
                System.Console.ReadKey();
            } catch (Exception e) {
                FrostbiteConnection.LogError("PRoCon.Console.exe", "", e);
            } finally {
                if (application != null) {
                    application.Shutdown();
                }
            }*/

        } /*
        TODO delete
        else {
            // Possible prevention of a cpu consumption bug I can see at the time of writing.
            // TCAdmin: Start procon.exe
            // procon.exe has an update to install
            // procon.exe loads proconupdater.exe
            // procon.exe unloads
            // proconupdater.exe begins update
            // TCAdmin detects procon.exe shutdown - attempts to reload
            System.out.println("Already running - shutting down");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

    }
}
