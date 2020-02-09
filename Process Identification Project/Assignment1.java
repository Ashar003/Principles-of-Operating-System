public class Assignment1 {
    public static void main(String args[]){
         int numbThreads = 100;
         int visit = 0;
         int count = 0;
         MultiThread threadsArray[] = new MultiThread[100];
     
        for(int index=0; index< numbThreads; index++){
            MultiThread multiThreads = new MultiThread();
            multiThreads.start();
            threadsArray[index] = multiThreads; 
        }

            while(threadsArray[visit].visited() != true){
                // System.out.println("attempted");
             MultiThread t = threadsArray[visit];
            // t.goSleep(Thread.currentThread());
            
            if(!(t.isAlive())){
                count++;
                t.setVisit();
                // System.out.println("in the in loop");
                    try {
                        t.join();
                        
                    }
                    catch (InterruptedException e) {
                        // System.out.println("Problem in join");
                    }
                }else{
                    // System.out.println("else");
                       continue;
                }
                    visit++;
                    if(visit == numbThreads && count != numbThreads-1){
                        visit = 0;
                }
            }
    }
}