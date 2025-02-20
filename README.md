The EasyFile class simplifies file handling in Java, supporting operations like reading and writing data (strings, integers, doubles), appending, overwriting, and managing file metadata (size, path). 
It automatically creates files if they don't exist and offers flexible reading and writing options.
The class efficiently manages file readers and writers, ensuring proper resource handling, and can count lines, copy content, and clear files when needed.
It also includes error handling for common I/O issues, providing a reliable solution for file manipulation in Java.

Included methods:

EasyFile(String fileName): Constructor that creates a new file (if it doesn't exist) and initializes readers and writers.

EasyFile(String fileName, boolean append): Constructor that creates a new file (if necessary) and initializes readers and writers, with an optional append mode.

newReader(): Closes the current reader (if open) and creates a new BufferedReader for reading the file.

newWriter(): Sets up a writer in overwrite mode by calling newWriter(false).

newWriter(boolean append): Closes the current writer (if open) and creates a new BufferedWriter, with an option for append mode.

getLine(int index): Retrieves a specific line from the file by its index.

getString(int index): Retrieves the nth non-numeric string from the file.

getInt(int index): Retrieves the nth integer from the file.

getDouble(int index): Retrieves the nth double from the file.

getLong(int index): Retrieves the nth long from the file.

getByte(int index): Retrieves the nth byte from the file.

toArrayList(): Reads the entire file into an ArrayList<String>, or returns null if empty.

toArray(): Reads the entire file into a String[] array, or returns null if empty.

write(String content): Writes a string to the file without a newline.

writeln(String content): Writes a string to the file with a newline.

nextLine(): Reads and returns the next line from the file.

nextInt(): Reads and returns the next available integer in the file.

nextDouble(): Reads and returns the next available double in the file.

nextLong(): Reads and returns the next available long in the file.

nextByte(): Reads and returns the next available byte in the file.

nextString(): Reads and returns the next available non-numeric string in the file.

skip(int index): Skips a specified number of lines in the file.

skipTo(int index): Resets the reader and skips to a specific line index.

copyTo(EasyFile EasyFile): Copies the contents of the current file to another EasyFile.

copyTo(String filename): Copies the contents of the current file to a given destination file.

length(): Counts and returns the total number of lines in the file.

getFileSize(): Returns the file size in bytes.

getFileName(): Returns the filename of the file.

getFilePath(): Returns the filepath of the file.

clear(): Clears the file (same as newWriter() but with clearer intent).

close(): Closes both the reader and writer to free resources.
