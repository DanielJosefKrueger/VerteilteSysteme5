package de.darktech.vs5;

import static de.darktech.vs5.ProcessRegister.VERBOSE;

public class Prozess {

    private boolean active;
    private int processID;
    private boolean gotOK = false;
    private boolean sendElect = false;

    public Prozess(boolean active, int processID) {
        this.active = active;
        this.processID = processID;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }


    public void elect(){
        sendElect=true;
        for (Prozess prozess : ProcessRegister.getProcesses()) {
            if(prozess.processID> this.processID){
                prozess.receiveElect(this);
            }
        }
    }

    public void receiveOk(Prozess sender){

        if(VERBOSE)System.out.println("Process with id" + this.processID + " received OK from Process with id" + sender.processID);

        gotOK=true;
    }


    public void testForElection(){
        if(!active){
            System.out.println("The process " + this.processID + " is currently dead");
            gotOK=false;
            sendElect=false;
            return;
        }


        if(sendElect&&!gotOK){
            gotOK=false;
            sendElect=false;

            System.out.println("The process " + this.processID + " won the Election!");
            //gewonnen
            for (Prozess prozess : ProcessRegister.getProcesses()) {
                prozess.receiveCoord(this);
            }
        }else{
            gotOK=false;
            sendElect=false;
            System.out.println("The process " + this.processID + " lost!");
        }


    }


    public void receiveElect(Prozess sender){
        if(active){
            if(VERBOSE)System.out.println("Process with id" + this.processID + " received elect from Process with id" + sender.processID);
            sender.receiveOk(this);
            elect();
        }
    }

    public void receiveCoord(Prozess sender){
        if(active){
            if(VERBOSE)System.out.println("Process with id" + this.processID + " received Cord from Process with id" + sender.processID);
        }
    }




}
