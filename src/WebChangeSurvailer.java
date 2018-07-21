import java.io.PrintStream;

public class WebChangeSurvailer { public WebChangeSurvailer() {}
  public static void main(String[] args) throws Exception { if (args.length != 2) System.exit(2);
    boolean quit = false;
    int frequency = 0;
    String website = args[1];
    try {
      frequency = Integer.parseInt(args[0]);
    }
    catch (NumberFormatException e) {
      System.err.println("Number in milli secounds is required as argument");
      System.exit(1);
    }
    
    Survailer omm = new Survailer(frequency, website);
    TerminalInterface ti = new TerminalInterface();
    omm.start();
    ti.start();
    

    while (!quit) {
      if ((ti.isRunning()) && (omm.isRunning())) {
        ti.setLastMessage(omm.lastRequest());
        ti.madeRequest(omm.requests());
      }
      else if (!ti.isRunning()) {
        omm.quit();
        if (omm.getState() == Thread.State.TIMED_WAITING) {
          omm.interrupt();
        }
        
        quit = true;
      }
      else if (!omm.isRunning()) {
        ti.quit();
        quit = true;
      }
      else {
        quit = true;
      }
      Thread.sleep(100L);
    }
    System.out.println("main thread shut down!");
  }
}
