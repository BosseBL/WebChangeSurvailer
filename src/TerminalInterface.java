import java.io.PrintStream;

public class TerminalInterface extends Thread {
  private int request;
  private String lastMessage;
  private boolean quit;
  
  public TerminalInterface() {}
  
  public void run() { quit = false;
    java.util.Scanner in = new java.util.Scanner(System.in);
    String command = "";
    try
    {
      sleep(500L);
      while (!quit)
      {
        System.out.print(">>");
        command = in.nextLine();
        if (command.equals("requests")) { System.out.println(request + " has been made");
        } else if (command.equals("last")) { System.out.println(lastMessage);
        } else if (command.equals("running")) {
          if (!quit) System.out.println("system is still running"); else {
            System.out.println("system is down");
          }
        } else if (command.equals("quit")) {
          System.out.println("\nshuting down...");
          quit = true;
        } else {
          System.out.println("requests, last and running is valid keywords");
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("in TI thread: " + e.toString());
    }
    
    System.out.println("\nti thread shut down!");
  }
  
  public void madeRequest(int r) {
    request = r;
  }
  
  public void setLastMessage(String message) { lastMessage = message; }
  
  public boolean isRunning() {
    return !quit;
  }
  
  public void quit() { quit = true; }
}
