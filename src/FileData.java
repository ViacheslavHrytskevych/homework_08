public class FileData  implements Comparable<FileData> {


    private String fileName;
    private int sizeInBytes;
    private String filePath;

    public FileData(String fileName, int sizeInBytes, String filePath) {
        this.fileName = fileName;
        this.sizeInBytes = sizeInBytes;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public String getFilePath() {
        return filePath;
    }


    @Override
    public int compareTo(FileData o) {
        if (this.sizeInBytes < o.sizeInBytes) {
            return -1;
        } else if (this.sizeInBytes > o.sizeInBytes) {
            return 1;
        } else {
            return 0;
        }
    }
}