import java.util.concurrent.Semaphore;


public class Agents{
    private static Thread[] agentThreads = new Thread[3];
    private static Semaphore envelope = new Semaphore(0,true);
    private static Semaphore flyer = new Semaphore(0,true);
    private static Semaphore stamp = new Semaphore(0,true);
    private static Semaphore agentSem = new Semaphore(1,true);

    private static Thread agentA; 
    private static Thread agentB; 
    private static Thread agentC; 

    public void sleepT() {
      try { 
        Thread.sleep(200);
      } catch (InterruptedException ex) {
      }
     };

    public Semaphore provideagent(){
        return agentSem;
    }
    public static Semaphore  provideEnvelope(){
        return envelope;
    }
    public static Semaphore  provideFlyer(){
        return flyer;
    }
    public static Semaphore  provideStamp(){
        return stamp;
    }
    public static Semaphore  provideAgent(){
        return agentSem;
    }
  

    public Thread[] agentArray(){
      return agentThreads;
    }

    public void initAgents() {
      agentThreads[0] = agentA;
      agentThreads[1] = agentB;
      agentThreads[2] = agentC;

      agentA = new Thread() {
      private volatile boolean exit1 = false;
      int numbMailings1 = 0;
   
   public void run() {
    while(exit1 !=true) {
        if(numbMailings1++ == 18) {exit1 = true; }
        
     try {
      sleepT();
      agentSem.acquire();
      System.out.println("Agent A has pupt an envelope and flyer on the table.");
      envelope.release();
      flyer.release();
     
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
    }
    Thread.currentThread().interrupt();
   }

  };
  agentB = new Thread() {
    int numbMailings2 = 0;
    private volatile boolean exit2 = false;
   
   public void run() {
    while(exit2 !=true) {
        if(numbMailings2++ == 18) {exit2 = true; Thread.currentThread().interrupt();}
     try {
      sleepT();
      agentSem.acquire();
      System.out.println("Agent B has put a stamp and flyer on the table.");
      stamp.release();
      flyer.release();
      
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
    }
   }

  };
   agentC = new Thread() {
    private volatile boolean exit3 = false;
    int numbMailings3 = 0;
  
   public void run() {
    while(exit3 !=true) {
        if(numbMailings3++ == 18) {exit3 = true;}
     try {
      sleepT();
      agentSem.acquire();
      System.out.println("Agent C has put an envelope and stamp on the table.");
      envelope.release();
      stamp.release();
      
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
    }
    Thread.currentThread().interrupt();
   }

  };
  agentA.start();
  agentB.start();
  agentC.start();
 }

}