package at.gotzi.api;

import at.gotzi.api.ano.Comment;


import java.io.File;
import java.net.URI;

public class GDir extends File {

    @Comment.Constructor
    public GDir(String pathname) {
        super(pathname);
        create();
    }

    @Comment.Constructor
    public GDir(String parent, String child) {
        super(parent, child);
        create();
    }

    @Comment.Constructor
    public GDir(File parent, String child) {
        super(parent, child);
        create();
    }

    @Comment.Constructor
    public GDir(URI uri) {
        super(uri);

        create();
    }

    public boolean create() {
        return super.mkdirs();
    }
}
