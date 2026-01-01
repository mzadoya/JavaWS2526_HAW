package p3.a3x1;

import java.io.File;

public class FileEntry implements Comparable<FileEntry> {

    File file;
    long size;
    
    public FileEntry(File file, long size) {
        this.file = file;
        this.size = size;
    }

    @Override
    public int compareTo(FileEntry o) {
        return Long.compare(o.size, this.size);
    }
}
