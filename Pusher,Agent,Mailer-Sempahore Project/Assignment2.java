public class Assignment2 {
 public static void main(String[] args) {
  
  Agents ag = new Agents();
  Volunteers vg = new Volunteers();
  Pushers pg = new Pushers();
    ag.initAgents();
    vg.initVolunteers();
    pg.initPushers();

  Thread[] agentArray = ag.agentArray();
  Thread[] volunteersArray = vg.volunteersArray();
  Thread[] pushersArray = pg.pusherArray();
  
  for(int indexA =0; indexA < agentArray.length; indexA++){
      try{
        if(!agentArray[indexA].isAlive()){ Thread.currentThread().join();}
            else{ indexA=0;}
      }
      catch(Exception e){   
  }
};
  for(int indexV =0; indexV < volunteersArray.length; indexV++){
      try{
        if(!volunteersArray[indexV].isAlive()){ Thread.currentThread().join();}
        else{ indexV=0;}
    }
catch(Exception e){
}
  };
for(int indexP =0; indexP < agentArray.length; indexP++){
    try{
        if(!pushersArray[indexP].isAlive()){ Thread.currentThread().join();}
        else{ indexP=0;}
}
catch(Exception e){
}
 };
};
};
