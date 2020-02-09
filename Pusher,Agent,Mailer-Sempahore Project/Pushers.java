import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pushers{

  private boolean isenvelope = false;
  private boolean isflyer = false;
  private boolean isstamp = false;

  private static Semaphore envelope;
  private static Semaphore flyer;
  private static Semaphore stamp;
  private static Semaphore envelopeSem = new Semaphore(0, true);
  private static Semaphore flyerSem = new Semaphore(0, true);
  private static Semaphore stampSem = new Semaphore(0,true);
  private static Lock mutex = new ReentrantLock();

  private static Thread[] pusherThreads = new Thread[3];
  private Thread pusherA;
  private Thread pusherB;
  private Thread pusherC;
    
    public static Semaphore provideEnvelope(){
        return envelopeSem;
    }
    public static Semaphore  provideFlyer(){
        return flyerSem;
    }
    public static Semaphore  provideStamp(){
        return stampSem;
    }
    public Thread[] pusherArray(){
      return pusherThreads;
    }

    public void initPushers() {
            envelope = Agents.provideEnvelope();
            flyer = Agents.provideFlyer();
            stamp = Agents.provideStamp();

            pusherThreads[0] = pusherA;
            pusherThreads[1] = pusherB;
            pusherThreads[2] = pusherC;
            
   pusherA = new Thread() {
      private volatile boolean exit1 = false;
      int numbMailings1 = 0;
   
    public void run() {
     while(exit1 !=true) {
     try {  
        envelope.acquire();
        mutex.lock();
      try {
       if(isflyer) {
        isflyer = false;
        stampSem.release();
       } else if(isstamp) {
        isstamp = false;
        flyerSem.release();
       } else {
        isenvelope = true;
       }
      } finally {
       mutex.unlock();
      }
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     if(numbMailings1++ == 24) {exit1 = true; }
    }
    Thread.currentThread().interrupt();
   };
  };
   pusherB = new Thread() {
      private volatile boolean exit2 = false;
      int numbMailings2 = 0;
   public void run() {
    while(exit2 !=true) {  
     try { 
      flyer.acquire();
      mutex.lock();
      try {
       if(isenvelope) {
        isenvelope = false;
        stampSem.release();
       } else if(isstamp) {
        isstamp = false;
        envelopeSem.release();
       } else {
        isflyer = true;
       }
      } finally {
       mutex.unlock();
      }
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     if(numbMailings2++ == 24){ exit2 = true; }
    }
    Thread.currentThread().interrupt();
   };

  };
   pusherC = new Thread() {
      private volatile boolean exit3 = false;
      int numbMailings3 = 0;
    
  public void run() {
    while(exit3 !=true) {
     try {
      stamp.acquire();
      mutex.lock();
      try {
       if(isflyer) {
        isflyer = false;
        envelopeSem.release();
       } else if(isenvelope) {
        isenvelope = false;
        flyerSem.release();
       } else {
        isstamp = true;
       }
      } finally {
       mutex.unlock();
      }
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     if(numbMailings3++ == 24) {exit3 = true; }
    }
    Thread.currentThread().interrupt();
   };

  };
  pusherA.start();
  pusherB.start();
  pusherC.start();
 }
 
}