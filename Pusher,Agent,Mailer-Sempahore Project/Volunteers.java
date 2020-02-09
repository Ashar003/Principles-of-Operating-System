import java.util.concurrent.Semaphore;


public class Volunteers{
  private static Semaphore envelopeSem;
  private static Semaphore flyerSem;
  private static Semaphore stampSem; 
  private static Semaphore agentSem;
  private static Thread[] volunteerThreads = new Thread[6];
  private Thread firstVolunteer; 
  private Thread secondVolunteer; 
  private Thread thirdVolunteer; 
  private Thread fourthVolunteer; 
  private Thread fifthVolunteer; 
  private Thread sixthVolunteer; 
  
    

     public void mailing() {
         System.out.println("Volunteer put together a mailing");
      try {
        System.out.println("Volunteer took the mailer to the mailbox");
        Thread.sleep(50);
      } catch (InterruptedException ex) {
      }
     };

     public Thread[] volunteersArray(){
      return volunteerThreads;
    }

    public void initVolunteers() {
            envelopeSem = Pushers.provideEnvelope();
            flyerSem = Pushers.provideFlyer();
            stampSem = Pushers.provideStamp();
            agentSem = Agents.provideAgent();
            volunteerThreads[0] = firstVolunteer;
            volunteerThreads[1] = secondVolunteer;
            volunteerThreads[2] = thirdVolunteer;
            volunteerThreads[3] = fourthVolunteer;
            volunteerThreads[4] = fifthVolunteer;
            volunteerThreads[5] = sixthVolunteer;
            
        
        
  firstVolunteer = new Thread() {//es
    private volatile boolean exit1 = false;
    int numbMailings1 = 0;
   
   public void run() {
     
    while(exit1 !=true) {
     try {
      envelopeSem.acquire();
      agentSem.release();
      stampSem.acquire();
      agentSem.release();
      mailing();
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     if(numbMailings1++ == 9) {exit1 = true; }
    }
    Thread.currentThread().interrupt();
   }
  };

  
  secondVolunteer = new Thread() {//sf
    private volatile boolean exit2 = false;
    int numbMailings2 = 0;
    
   public void run() {
   
    while(exit2 !=true) {
     try {
      stampSem.acquire();
      agentSem.release();
      flyerSem.acquire();
      agentSem.release();
      mailing();
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     if(numbMailings2++ == 9) {exit2 = true; }
    }
    Thread.currentThread().interrupt();
   }
  };
  
   thirdVolunteer = new Thread() {//fe
    private volatile boolean exit3 = false;
    int numbMailings3 = 0;

   public void run() {
    
    while(exit3 !=true) {
     try {
      flyerSem.acquire();
      agentSem.release(); 
      envelopeSem.acquire();
      agentSem.release();
      mailing();
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     if(numbMailings3++ == 9) {exit3 = true; }
    }
    
    Thread.currentThread().interrupt();
   }
  };
   fourthVolunteer = new Thread() {//ef
    private volatile boolean exit4 = false;
    int numbMailings4 = 0;

    public void run() {
        
     while(exit4 !=true) {
      try {
       envelopeSem.acquire();
       agentSem.release();
       flyerSem.acquire();
        agentSem.release();
        mailing();
      } catch (InterruptedException e) {
       e.printStackTrace();
      }
      if(numbMailings4++ == 9){ exit4 = true;}
     }
     Thread.currentThread().interrupt();
    }
   };
   fifthVolunteer = new Thread() {//fs
    private volatile boolean exit5 = false;
    int numbMailings5 = 0;

    public void run() {
     while(exit5 !=true) {
      try {
      flyerSem.acquire();
       agentSem.release();
       stampSem.acquire();
       agentSem.release();
       mailing();
      } catch (InterruptedException e) {
       e.printStackTrace();
      }
      if(numbMailings5++ == 9) {exit5 = true; }
     }
     Thread.currentThread().interrupt();
    }
  
   };
  
   sixthVolunteer = new Thread() {//se
    private volatile boolean exit6 = false;
    int numbMailings6 = 0;
  
    public void run() {
     while(exit6 !=true) {
      try {
       stampSem.acquire();
       agentSem.release();
       envelopeSem.acquire();
       agentSem.release();
       mailing();
      } catch (InterruptedException e) {
       e.printStackTrace();
      }
      if(numbMailings6++ == 9) {exit6 = true; }
     }
     Thread.currentThread().interrupt();
    }

  
   };
   
  
  firstVolunteer.start();
  secondVolunteer.start();
  thirdVolunteer.start();
  fourthVolunteer.start();
  fifthVolunteer.start();
  sixthVolunteer.start();
 }
 
}