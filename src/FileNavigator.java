import java.util.*;

public class FileNavigator {

    private Map<String, List<FileData>> filesByPath = new HashMap<>();

    public void add(FileData fileData) {
        String filePath = fileData.getFilePath();
        String key = getKeyFromPath(filePath);
        if (!filesByPath.containsKey(key)) {
            filesByPath.put(key, new ArrayList<>());
        }
        List<FileData> files = filesByPath.get(key);
        if (filePath.startsWith(key)) {
            files.add(fileData);
        } else {
            System.out.println("Error: the path value for " + fileData.getFileName() + " does not match the key " + key);
        }
    }

    private String getKeyFromPath(String path) {

        return path.substring(0, path.lastIndexOf("/"));
    }

    public List<FileData> find(String path) {

        String key = getKeyFromPath(path);
        if (!filesByPath.containsKey(key)) {
            System.out.println("Key not found: " + key);
            return new ArrayList<>();
        }
        List<FileData> files = filesByPath.get(key);
        List<FileData> result = new ArrayList<>();
        for (FileData file : files) {
            if (file.getFilePath().startsWith(path)) {
                result.add(file);
            }
        }
        return result;
    }

    public List<FileData> filterBySize(int maxSize) {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> fileList : filesByPath.values()) {
            allFiles.addAll(fileList);
        }
        List<FileData> filteredFiles = new ArrayList<>();
        for (FileData fileData : allFiles) {
            if (fileData.getSizeInBytes() <= maxSize) {
                filteredFiles.add(fileData);
            }
        }
        return filteredFiles;
    }

    public void remove(String path) {
        if (filesByPath.containsKey(path)) {
            filesByPath.remove(path);
        } else {
            String key = getKeyFromPath(path);
            if (filesByPath.containsKey(key)) {
                List<FileData> files = filesByPath.get(key);
                files.removeIf(fileData -> fileData.getFilePath().equals(path));
                if (files.isEmpty()) {
                    filesByPath.remove(key);
                }
            }
        }
    }

    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> fileList : filesByPath.values()) {
            allFiles.addAll(fileList);
        }
        Collections.sort(allFiles);
        return allFiles;
    }
}
