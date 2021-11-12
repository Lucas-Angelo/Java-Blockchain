package src.models;

import java.io.Serializable;

/** Class for Block type objects.
 * Implements Serializable.
* @author Lucas Ângelo O. M. Rocha.
* @version 1.0
* @since Release 01
*/
public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Unique identifier of each block */
    private final int id;

    /** Identifier of the miner who discovered the block. */
    private final int minerIdentifier;
    
    /** Unix time the block was discovered */
    private final long timeStamp;

    /** Random number generated by miner */
    private final long randomNumber;

    /** Hash of the previous block that made it possible to find the current block */
    private final String previousHash;

    /** Hash of the block, generated by SHA512 encryption using the previousHash, id, timestamp and randomNumber. */
    private final String hash;

    /** Time taken to find the block */
    private final long timeWorked;

    /** To define whether to increase, decrease or continue at the same hash value found, generated by time worked */
    private final String N;

    public Block(int minerName, int id, long timeStamp, long randomNumber, String previousHash, String hash, long timeWorked, String N) {
        this.minerIdentifier = minerName;
        this.id = id;
        this.timeStamp = timeStamp;
        this.randomNumber = randomNumber;
        this.previousHash = previousHash;
        this.hash = hash;
        this.timeWorked = timeWorked;
        this.N = N;
    }

    public int getId() {
        return this.id;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public long getRandomNumber() {
        return this.randomNumber;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public String getHash() {
        return this.hash;
    }

    public int getN() {
        return Integer.parseInt(this.N.replaceAll("[^0-9]", ""));
    }

    public long getTimeWorked() {
        return this.timeWorked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nBlock:");
        sb.append("\nFound by miner: " + this.minerIdentifier + ".");
        sb.append("\nId: " + this.id + ".");
        sb.append("\nHash of the block: \n" + this.hash + ".");
        sb.append("\nRandom number: " + this.randomNumber + ".");
        sb.append("\nHash of the previous block: \n" + this.previousHash + ".");
        sb.append("\nTimestamp it was discovered: " + this.timeStamp + ".");
        sb.append("\nBlock took " + this.timeWorked + " milliseconds to be generated.");
        sb.append("\n" + this.N);
        return sb.toString();
    }
}
