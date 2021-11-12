package src.blockchain;

import java.util.*;

import src.helpers.Sha512;
import src.models.Block;

/**
 * Class for blockchain list, the list of found blocks. Implements Serializable.
 * 
 * @author Lucas Ângelo O. M. Rocha.
 * @version 1.0
 * @since Release 01
 */
public class Chain {

    /** A blockchain can have another blockchain inside */
    private static Chain blockChain;

    /** The list of blocks */
    public List<Block> blocksList = new LinkedList<Block>();

    private Chain() {
    }

    // Return the static blockchain or create and return a new blockchain
    public static Chain getBlockChain() {
        if (blockChain == null) {
            blockChain = new Chain();
        }
        return blockChain;
    }

    // Used in hash generation by miner
    public String getZeros() {
        var n = this.blocksList.size() == 0 ? 0 : this.blocksList.get(this.blocksList.size() - 1).getN();
        var stringBuilder = new StringBuilder();
        return String.valueOf(stringBuilder.append("0".repeat(n)));
    }

    // Used to check if timeWorked by miner raise, descrease or no change actual N
    public String createNbyChangeZeros(long timeWorked) {
        var n = this.blocksList.size() == 0 ? 0 : this.blocksList.get(this.blocksList.size() - 1).getN();

        if (timeWorked < 500)
            return "N has been raised to  " + ++n;
        else if (timeWorked > 2000)
            return "N has been decreased to  " + --n;
        else
            return "N no change - " + n;
    }

    // Check if the generated block by miner is valid
    public boolean isValidBlock(Block block) {

        if (block.getHash().startsWith(getZeros()) || this.blocksList.size() == 0) {
            this.blocksList.add(block);

            if (this.blocksList.size() > 1) {
                for (var i = this.blocksList.size() - 1; i > 0; i--) {
                    if (!this.blocksList.get(i).getHash()
                            .equals(Sha512.applySha512(this.blocksList.get(i - 1).getHash()
                                    + this.blocksList.get(i).getId() + this.blocksList.get(i).getTimeStamp()
                                    + this.blocksList.get(i).getRandomNumber()))) {
                        System.out.println("Block " + this.blocksList.get(i).getId() + " is invalid!");
                        if (this.blocksList.size() > i)
                            this.blocksList.subList(i, this.blocksList.size()).clear();
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
