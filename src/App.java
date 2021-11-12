package src;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import src.helpers.Deserialization;
import src.helpers.Serialization;
import src.models.Block;
import src.mining.Miner;

// import src.blockchain.Chain; // Need to compile
import build.src.blockchain.*;

public class App {

    public static String fileName = "blockchainObjects.data";
    public static File file = new File(fileName);

    public static void main(String[] args) {
        initializeBlockchain();

        var sc = new Scanner(System.in);
        System.out.print("Enter how many miners you want to use: ");
        var threadsQuantity = sc.nextInt();
        sc.close();
        var minerList = new ArrayList<Miner>(threadsQuantity);

        // Create miners
        for (var i = 0; i < threadsQuantity; i++) {
            minerList.add(new Miner(i));
        }

        // Create threads to blockchain mining
        var threads = new ArrayList<Thread>();
        minerList.stream().forEach((miner) -> {
            var thread = new Thread(miner);
            thread.start(); // Call run() method from Miner class
            threads.add(thread);
        });

        // Wait used threads die
        threads.stream().forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        finalizeBlockchain();
    }

    // To continue where blockchain left off
    private static void initializeBlockchain() {
        if (file.length() != 0) {
            var read = new Deserialization();
            read.openFile(fileName);
            var blocks = new LinkedList<Block>();
            read.deserialize().stream().forEach((object) -> {
                try {
                    blocks.add((Block) object);
                } catch (ClassCastException e) {
                    System.err.println("Error: Failed to cast objects saved in file to Block, unable to load blocks.");
                }
            });
            read.closeFile();
            Chain.getBlockChain().blocksList = blocks;
        }
    }

    // To save where blockchain left off
    private static void finalizeBlockchain() {
        var write = new Serialization<Block>();
        write.openFile(fileName);
        write.serialize(Chain.getBlockChain().blocksList);
        write.closeFile();
    }
}
