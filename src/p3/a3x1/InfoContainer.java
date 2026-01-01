package p3.a3x1;

public class InfoContainer {

    private FileEntry[] container;
    private int max;
    
    public InfoContainer(int max) {
        this.max = max; 
        this.container = new FileEntry[max];
        for (int i = 0; i < max; i++) {
            container[i] = new FileEntry(null, -1);
        }
    }
    
    public void putFile(FileEntry file) {
        if (container[max - 1].size >= file.size) {
            return;
        }

        for (int i = 0; i < max; i++) {
            if (container[i].size < file.size) {

                for (int j = max - 1; j > i; j--) {
                    container[j] = container[j - 1];

                }

                container[i] = file;
           
                return;
            }
        }
    }
    /*
    public void putFile(FileEntry file) {
        if (container[max-1].size >= file.size) {
            return;
        }
        int pos = findPos(file);
        if (pos == -1) {
            return;
        }
        for (int i = max - 1; i > pos; i--) {
            container[i] = container[i-1];
        }
        container[pos]=file;
    }
    */
    public int findPos(FileEntry file) {
        for (int i = 0; i < max; i++) {
            if (container[i].size < file.size) {
                return i;
            }
        }
        return -1;
    }
    
    public FileEntry getFile(int pos) {
        return container[pos];
    }
}
