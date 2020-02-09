import java.util.Random;

public class MultiThread extends Thread{
    private int process;
    private Random r; 
    private boolean exit;
    private static pid p1 = new pid();
    private boolean visit;
    
    public void run(){
        try{
            visit = false;
            exit = false;
            initializePid(p1);
        //    System.out.println("Thread Number "+ Thread.currentThread().getId() + " is now started");
            goSleep(Thread.currentThread());
          
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public boolean visited(){
        return visit;
    }
    public void setVisit(){
        visit = true;
    }
     
    public void goSleep(Thread thread){
        r = new Random();
		int sleepTime = r.ints(60, (300)).limit(1).findFirst().getAsInt();
        // System.out.println(sleepTime +"sleepTime");
        SleepUtilities.nap(sleepTime );
        System.out.println("My PID is: " + this.process);
        releasePid();
        terminateThread(Thread.currentThread());
        
    }
   
    public void releasePid(){
        // System.out.println("About to release process id " + this.process);
        pid.releasePid(this.process);

    }
    public void initializePid(pid p1){
        p1.allocatePid(1);
        process = p1.getPidNumber(Thread.currentThread());

    }
    public void terminateThread(Thread thread){
            // System.out.println("ending now");
            exit = true;
    }
}

