import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipFile;

public class Main {
    /*
    1. Вывести список файлов текущей директории
    2*. Вывести список файлов текущей директории рекурсивно (только файлов без директорий)
    3*. Подсчитать количество файлов в src.zip который находится в директории JDK
     */
    public static void main(String[] args) {
        //Task 1
        File folder = new File(".");
        System.out.println("Task 1 ==================");
        printListOfFiles(folder);

        //Task 2
        System.out.println("Task 2 ==================");
        printListOfFilesRecursively(folder.getPath());

        //Task 3
        File fileZip = new File("./JDK_for_Task/src.zip");
        System.out.println("Task 3 ==================");
        System.out.println(countFilesInZip(fileZip));

    }

    private static void printListOfFiles(File folder) {
        File[] files = folder.listFiles();

        if (folder.isDirectory() && files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }

    private static void printListOfFilesRecursively(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    printListOfFilesRecursively(file.getAbsolutePath());
                }
            }
        }

    }

    private static int countFilesInZip(File fileZip) {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileZip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        Enumeration zipEntries = null;
        if (zipFile != null) {
            zipEntries = zipFile.entries();
        }
        if (zipEntries != null) {
            while (zipEntries.hasMoreElements()) {
                zipEntries.nextElement();
                count++;
            }
        }
        return count;
    }

}
