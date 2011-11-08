package procon.console;

public class Program {
    /*class Program {

        static void Main(string[] args) {

            PRoConApplication application = null;

            if (PRoConApplication.IsProcessOpen() == false) {
                try {
                    application = new PRoConApplication(true, args);

                    System.Console.WriteLine("PRoCon Frostbite");
                    System.Console.WriteLine("================");
                    System.Console.WriteLine("This is a cut down version of PRoCon.exe to be used by GSPs and PRoCon Hosts.");
                    System.Console.WriteLine("Executing this file is the same as \"PRoCon.exe -console 1\"");
                    System.Console.WriteLine("No output is given.  This is as cut down as we're gunno get..");
                    System.Console.WriteLine("\nExecuting procon...");
                    application.Execute();

                    GC.Collect();
                    System.Console.WriteLine("Running... (Press any key to shutdown)");
                    System.Console.ReadKey();
                }
                catch (Exception e) {
                    FrostbiteConnection.LogError("PRoCon.Console.exe", "", e);
                }
                finally {
                    if (application != null) {
                        application.Shutdown();
                    }
                }

            }
            else {
                // Possible prevention of a cpu consumption bug I can see at the time of writing.
                // TCAdmin: Start procon.exe
                // procon.exe has an update to install
                // procon.exe loads proconupdater.exe
                // procon.exe unloads
                // proconupdater.exe begins update
                // TCAdmin detects procon.exe shutdown - attempts to reload
                System.Console.WriteLine("Already running - shutting down");
                Thread.Sleep(50);
            }

        }
    }*/
}
