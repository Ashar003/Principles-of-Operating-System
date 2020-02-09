import java.util.*;
import java.util.Map.Entry;

public class pid{
    private static int minNumber;
    private static int numberAssigned;
    private int id;
    private static Map< Integer,Thread> pid; 
    
    public pid(){
        minNumber = 300;
        numberAssigned =0;
       
        try{
            // allocateMap();
            System.out.println(allocateMap() + " means that map allocation was successful.");
        }
        catch(Exception e){
            System.out.println("Unable to allocate map for pid.");
        }
        // System.out.println("pid intilization");
    }

    public int allocateMap(){
        try{
             pid = new HashMap<Integer,Thread>(); 
            return 1;
        }catch(Exception e){
            return 0;
        }
    }
    public int getPidNumber(Thread value){
            for (Entry<Integer, Thread> entry : pid.entrySet()) {
                if (Objects.equals(value, entry.getValue())) {
                    return entry.getKey();
                }
            }
            return 0;
    }
    public int allocatePid(int pidsNeed){
        if(pidsNeed + numberAssigned + minNumber >= 5000){
            throw new ArithmeticException("No more pids can be assigned.");
        }else{
            try{
                for(int i=1; i<= pidsNeed; i++){
                    id = i + numberAssigned + minNumber;
                pid.put(id,Thread.currentThread());
                numberAssigned++;
                // System.out.println(id+"here's the id");
                }
                return 0;
                
            }catch(Exception e){
                System.out.println("it was not successful.");
                return 1;
            }
    }
}
    public static void releasePid(int pidNum){
        try{
            pid.remove(pidNum);
        // System.out.println(pid.remove(pidNum) + " was removed");
        }catch(Exception e){
            System.out.println("Could not be removed.");
        }
    }
}