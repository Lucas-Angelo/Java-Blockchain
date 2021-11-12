package src.helpers;

import java.io.*;
import java.util.*;

public class Serialization<T> {
    private FileOutputStream fileWrite;
    private ObjectOutputStream writeStream;

    public void openFile(String fileName) {
        try {
            this.fileWrite = new FileOutputStream(fileName);
            this.writeStream = new ObjectOutputStream(fileWrite);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File/File directory not found, unable to load file.");
        } catch (SecurityException e) {
            System.err.println("Error: No permission to access file/directory, unable to load file.");
        } catch (IOException e) {
            System.err.println("Error: I/O opening file, unable to load file.");
        } catch (NullPointerException e) {
            System.err.println(
                    "Error: FileInputStream not instantiated for ObjectInputStream (Null pointer), file could not be loaded.");
        }
    }

    public void closeFile() {
        try {
            this.writeStream.close();
            this.fileWrite.close();
        } catch (IOException e) {
            System.err.println("Error: I/O failure, unable to close file.");
        }
    }

    public void serialize(List<T> objects) {
        objects.stream().forEach((t) -> {
            try {
                this.writeStream.writeObject(t);
            } catch (InvalidClassException e) {
                System.err.println("Error: Object class to save invalid, could not write to file.");
            } catch (NotSerializableException e) {
                System.err.println(
                        "Error: Non-serializeable class cannot be saved with serialization, could not write to file.");
            } catch (IOException e) {
                System.err.println("Error: I/O to file, could not write to file.");
            }
        });
    }
}
