package p3.a3x1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dateirecherche {

    InfoContainer foundFiles;
    InfoContainer foundDirs;
    String path;
    File userFile;
    int nod;
    int nof;
    private static final String DATEI = "a3x1_out.txt";

    public static void main(String[] args) {

        Dateirecherche app = new Dateirecherche();

        app.start();

    }

    private void start() {

        inputData();
        analyzeDirectory(userFile);
        printResults();
    }

    private void inputData() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Bitte geben Sie das Startverzeichnis (Pfad) ein:");
            path = sc.nextLine();
            userFile = new File(path);

            if (!userFile.exists() || !userFile.isDirectory()) {
                System.out.println(
                        "Fehler! Das Verzeichnis existiert nicht oder ist eine Datei. Versuchen sie es erneut");
            }

        } while (!userFile.exists() || !userFile.isDirectory());

        System.out.println("Anzahl der gesuchten Verzeichnisse (nod):");
        nod = sc.nextInt();
        foundDirs = new InfoContainer(nod);
        
        System.out.println("Anzahl der gesuchten Dateien (nof):");
        nof = sc.nextInt();

        foundFiles = new InfoContainer(nof);
    }

    private long analyzeDirectory(File currentDir) {

        long size = 0;
        long temp = 0;

        File[] files = currentDir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    temp = analyzeDirectory(f);
                    foundDirs.putFile(new FileEntry(f, temp));
                    size += temp;

                } else if (f.isFile()) {
                    if (hasToBeConsidered(f) == true) {
                        temp = f.length();
                        foundFiles.putFile(new FileEntry(f, temp));
                        size += temp;
                    }
                }
            }
        }
        foundDirs.putFile(new FileEntry(currentDir, size));
        return size;
    }

    private void printResults() {

        try (PrintWriter pw = new PrintWriter(

                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DATEI))))) {
            
            String analyzing = String.format("analyzing: %s", userFile.getAbsolutePath());
            System.out.println(analyzing);
            pw.format("%s%n", analyzing);

            String topFiles = String.format("The top %d files %n", nof);
            System.out.print(topFiles);
            pw.format("%n%s", topFiles);

            String space = "==================";
            System.out.println(space);
            pw.format("%s", space);

            for (int i = 0; i < nof; i++) {
                String output = String.format("%,33d  %s %n", foundFiles.getFile(i).size,
                        foundFiles.getFile(i).file.getAbsoluteFile());
                System.out.println(output);
                pw.format("%n%s", output);

            }


            String topDir = String.format("The top %d directories %n", nod);
            System.out.print(topDir);
            pw.format("%n%s", topDir);

            System.out.println(space);
            pw.format("%s", space);
            for (int i = 0; i < nod; i++) {
                String output = String.format("%,33d  %s %n", foundDirs.getFile(i).size,
                        foundDirs.getFile(i).file.getAbsolutePath());
                System.out.println(output);
                pw.format("%n%s", output);
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }

    private boolean hasToBeConsidered(final File file) {
        final Pattern p1 = Pattern.compile(".*\\.lnk$");
        final Matcher m1 = p1.matcher(file.getName());
        final boolean matched1 = m1.find();
        final boolean notConsideredAsActualFile = Files.isSymbolicLink(file.toPath()) || matched1;
        return !notConsideredAsActualFile;
    }

}
