package src.helpers;

import java.io.*;
import java.util.*;

public class Deserialization {
    private FileInputStream fileRead;
    private ObjectInputStream readStream;

    public void openFile(String fileName) {
        try {
            this.fileRead = new FileInputStream(fileName);
            this.readStream = new ObjectInputStream(fileRead);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File/File directory not found, unable to load file.");
        } catch (SecurityException e) {
            System.err.println("Error: No permission to access file/directory, unable to load file.");
        } catch (StreamCorruptedException e) {
            System.err.println("Error: Corrupt stream instance, unable to load file.");
        } catch (IOException e) {
            System.err.println("Error: I/O opening file, unable to load file.");
        } catch (NullPointerException e) {
            System.err.println(
                    "Error: FileInputStream not instantiated for ObjectInputStream (Null pointer), file could not be loaded.");
        }
    }

    public void closeFile() {
        try {
            this.readStream.close();
            this.fileRead.close();
        } catch (IOException e) {
            System.err.println("Error: I/O failure, unable to close file.");
        }
    }

    public List<Object> deserialize() {
        List<Object> objList = new ArrayList<Object>();

        boolean cont = true;
        try {
            while (cont) {
                objList.add(readStream.readObject());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Failed to cast class instantiated in file, could not read file.");
        } catch (InvalidClassException e) {
            System.err.println("Error: Class used in serialization is in error, the file could not be read.");
        } catch (StreamCorruptedException e) {
            System.err.println("Error: Stream instantiated is corrupt, could not read file.");
        } catch (OptionalDataException e) {
            System.err.println("Error: Primitive data found in stream instead of objects, unable to read file.");
        } catch (EOFException e) {
            cont = false;
        } catch (IOException e) {
            System.err.println("Error: I/O failure, unable to read file.");
        }

        return objList;
    }
}