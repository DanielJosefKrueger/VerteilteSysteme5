package de.darktech.vs5;

import java.util.ArrayList;
import java.util.Random;


public class ProcessRegister {


    static boolean VERBOSE = true;

    private static final ArrayList<Prozess> processes = new ArrayList<>();
    static{
        for(int i =0; i < 10;i++){
            processes.add(new Prozess(true, i));
        }
    }

    public static ArrayList<Prozess> getProcesses() {
        return processes;
    }

    public static void main(String[] args) {

        Random rand = new Random();


        System.out.println(Art.ROUND_1);
        processes.get(9).setActive(false);
        int merker = 2;
        processes.get(merker).elect();
        for(Prozess process :processes){
            process.testForElection();
        }

        System.out.println(Art.ROUND_2);
        processes.get(8).setActive(false);
        processes.get(rand.nextInt(8)).elect();
        for(Prozess process :processes){
            process.testForElection();
        }

        System.out.println(Art.ROUND_3);
        processes.get(9).setActive(true);
        processes.get(7).setActive(false);
        processes.get(rand.nextInt(7)).elect();
        for(Prozess process :processes){
            process.testForElection();
        }

    }
}
