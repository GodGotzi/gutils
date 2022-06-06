package at.gotzi.api;

import at.gotzi.api.ano.Comment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * It creates a file in the error folder with the name of the class that threw the error, a comma, and a number. The number
 * is incremented until the file doesn't exist
 */
public class GErrorHandler {

    private final File errorFolder;

    @Comment.Constructor
    public GErrorHandler(String errorFolderPath) {
        this.errorFolder = new GFile(errorFolderPath);
    }

    /**
     * It creates a file in the error folder with the name of the class that threw the error, a comma, and a number. The
     * number is incremented until the file doesn't exist
     *
     * @param cls The class that the error occurred in.
     * @param e The exception that was thrown
     */
    public void registerError(Class<?> cls, Exception e) {
        int i = 0;
        File file;

        do {
            file = new GFile(this.errorFolder.getPath() + "//" + cls.getCanonicalName() + "," + i + ".yml");
            i++;
        } while(file.exists());

        writeError(file, e);
    }

    /**
     * Write the stack trace of an exception to a file.
     *
     * @param file The file to write the error to.
     * @param e The exception that was thrown.
     */
    private void writeError(File file, Exception e) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            e.printStackTrace(printWriter);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Comment.Getter
    public File getErrorFolder() {
        return errorFolder;
    }
}
