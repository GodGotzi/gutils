package at.gotzi.api;

import at.gotzi.api.ano.Comment;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * `GFile` is a `File` that creates a new file if it doesn't exist
 */
public class GFile extends File {

    @Comment.Constructor
    public GFile(String pathname) {
        super(pathname);
        createNewFile();
    }

    @Comment.Constructor
    public GFile(String parent, String child) {
        super(parent, child);
        createNewFile();
    }

    @Comment.Constructor
    public GFile(File parent, String child) {
        super(parent, child);
        createNewFile();
    }

    @Comment.Constructor
    public GFile(URI uri) {
        super(uri);
        createNewFile();
    }

    @Override
    public boolean createNewFile() {
        try {
            super.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
