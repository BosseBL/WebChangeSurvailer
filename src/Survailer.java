import java.io.PrintStream;

public class Survailer extends Thread
{
  private int requests;
  private boolean quit;
  private String html;
  private int update_ms;
  private String website;
  
  public Survailer(int frequency, String ws)
  {
    update_ms = frequency;
    website = ws;
  }
  
  private String getPage() {
    try {
      java.net.URL surv = new java.net.URL(website);
      java.net.URLConnection yc = surv.openConnection();
      java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(yc.getInputStream()));
      String buff = "";
      String inputLine = "";
      while ((buff = in.readLine()) != null) {
        inputLine = inputLine + buff + "\n";
      }
      requests += 1;
      return inputLine;
    }
    catch (Exception e) {
      System.out.println("in OMM.getPassword():\n" + e.toString()); }
    return "";
  }
  
  public void run()
  {
    quit = false;
    requests = 0;
    String lastPage = getPage();
    System.out.println(backlater + "\n");
    html = lastPage;
    
    while (!quit) {
      try
      {
        sleep(update_ms);
        html = getPage();
        if (!lastPage.equals(html)) {
          System.out.println(html);
          lastPage = html;
        }
        

      }
      catch (InterruptedException localInterruptedException) {}catch (Exception e)
      {

        System.out.println("in OMM thread:\n" + e.toString());
      }
    }
    
    System.out.println("OMM thread shut down!");
  }
  
  public void quit()
  {
    quit = true;
  }
  
  public boolean isRunning() { return !quit; }
  
  public int requests() {
    return requests;
  }
  
  public String lastRequest() { return html; }
}
