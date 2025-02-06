// Custom data type for easy file handling
// Created by Adam Ossip

package oss.aossip;

import java.io.*;
import java.util.ArrayList;

public class EasyFile {

    private final File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    // Constructor: Creates a new file if it doesn't exist and initializes readers and writers
    public EasyFile(String fileName) throws IOException {
        this.file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        newReader();
        newWriter(false);
    }

    // Constructor: Creates a new file if necessary and initializes readers and writers, with optional append mode
    public EasyFile(String fileName, boolean append) throws IOException {
        this.file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        newReader();
        newWriter(append);
    }

    // Closes the current reader (if open) and creates a new BufferedReader for reading the file
    public void newReader() throws IOException {
        try {
            reader.close();
        } catch (Exception _) {}
        this.reader = new BufferedReader(new FileReader(file));
    }

    // Calls newWriter(false), setting up a writer in overwrite mode
    public void newWriter() throws IOException {
        newWriter(false);
    }

    // Closes the current writer (if open) and creates a new BufferedWriter with optional append mode
    public void newWriter(boolean append) throws IOException {
        try {
            writer.close();
        } catch (Exception _) {
        }
        this.writer = new BufferedWriter(new FileWriter(file, append));
    }

    // Retrieves a specific line from the file by index
    public String getLine(int index) throws IOException {
        newReader();
        int lineCount = 0;
        String line;
        while ((line = nextLine()) != null) {
            if (lineCount == index) {
                return line;
            }
            lineCount++;
        }
        return null;
    }

    // Retrieves the nth non-numeric string from the file
    public String getString(int index) throws IOException {
        newReader();
        int stringCount = 0;
        String line;
        while ((line = nextLine()) != null) {
            try {
                Double.parseDouble(line);
            } catch (Exception e) {
                if (stringCount == index) {
                    return line;
                }
                stringCount++;
            }
        }
        return null;
    }

    // Retrieves the nth integer from the file
    public int getInt(int index) throws IOException {
        newReader();
        int intCount = 0;
        String line;
        while ((line = nextLine()) != null) {
            try {
                int intLine = Integer.parseInt(line);
                if (intCount == index) {
                    return intLine;
                }
                intCount++;
            } catch (Exception _) {
            }
        }
        throw new IOException("No integer with index " + index);
    }

    // Retrieves the nth double from the file
    public double getDouble(int index) throws IOException {
        newReader();
        int doubleCount = 0;
        String line;
        while ((line = nextLine()) != null) {
            try {
                double doubleLine = Double.parseDouble(line);
                if (doubleCount == index) {
                    return doubleLine;
                }
                doubleCount++;
            } catch (Exception _) {
            }
        }
        throw new IOException("No double with index " + index);
    }

    // Reads the entire file into an ArrayList<String>, or returns null if empty
    public ArrayList<String> toArrayList() throws IOException {
        newReader();
        String line;
        boolean empty = true;
        ArrayList<String> lineList = new ArrayList<>();
        while ((line = nextLine()) != null) {
            lineList.add(line);
            empty = false;
        }
        if (!empty)
            return lineList;
        return null;
    }

    // Reads the entire file into a String[] array, or returns null if empty
    public String[] toArray() throws IOException {
        newReader();
        String line;
        ArrayList<String> lineHold = new ArrayList<>();
        boolean isEmpty = true;
        while ((line = nextLine()) != null) {
            lineHold.add(line);
            isEmpty = false;
        }
        if (!isEmpty)
            return lineHold.toArray(new String[0]);
        return null;
    }

    // Writes a string to the file without a newline
    public void write(String content) throws IOException {
        writer.write(content);
        writer.flush();
    }

    // Writes a string to the file with a newline
    public void writeln(String content) throws IOException {
        writer.write(content);
        writer.newLine();
        writer.flush();
    }

    // Reads and returns the next line from the file
    public String nextLine() throws IOException {
        return reader.readLine();
    }

    // Reads and returns the next available integer in the file
    public int nextInt() throws IOException {
        String line;
        while ((line = nextLine()) != null) {
            try {
                return Integer.parseInt(line);
            } catch (Exception _) {
            }
        }
        throw new IOException("No following integer");
    }

    // Reads and returns the next available double in the file
    public double nextDouble() throws IOException {
        String line;
        while ((line = nextLine()) != null) {
            try {
                return Double.parseDouble(line);
            } catch (Exception _) {
            }
        }
        throw new IOException("No following doubles");
    }

    // Reads and returns the next available non-numeric string in the file
    public String nextString() throws IOException {
        String line;
        while ((line = nextLine()) != null) {
            try {
                Double.parseDouble(line);
            } catch (Exception _) {
                return line;
            }
        }
        return null;
    }

    // Skips a specified number of lines in the file
    public void skip(int index) throws IOException {
        for (int i = 0; i < index; i++) {
            nextLine();
        }
    }

    // Resets the reader and skips to a specific line index
    public void skipTo(int index) throws IOException {
        newReader();
        skip(index);
    }

    // Copies the contents of the current file to another EasyFile
    public void copyTo(EasyFile EasyFile) throws IOException {
        newReader();
        String line;
        while ((line = nextLine()) != null) {
            EasyFile.writeln(line);
        }
    }

    // Copies the contents of the current file to a given destination file
    public void copyTo(String filename) throws IOException {
        EasyFile destination = new EasyFile(filename);
        copyTo(destination);
        destination.close();
    }

    // Counts and returns the total number of lines in the file
    public int length() throws IOException {
        newReader();
        int lineCount = 0;
        while (nextLine() != null) {
            lineCount++;
        }
        return lineCount;
    }

    // Returns file size in bytes
    public long getFileSize() {
        return file.length();
    }

    // Returns filepath
    public String getFilePath() {
        return file.getAbsolutePath();
    }

    // Clears file - same as newWriter but more clear (ba dum ts)
    public void clear() throws IOException {
        newWriter();
    }

    // Closes both the reader and writer to free resources
    public void close() throws IOException {
        reader.close();
        writer.close();
    }
}
