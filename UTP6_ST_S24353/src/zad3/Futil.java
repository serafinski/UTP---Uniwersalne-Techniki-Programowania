package zad3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * It traverses a directory tree and copies all the files it finds to a single file
 */
public class Futil {
    private static final String SRC_FILE_ENCODING = "Windows-1250";
    private static final Charset DEST_FILE_ENCODING = StandardCharsets.UTF_8;

    public static void processDir(String dirName, String resultFileName) {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(Files.newOutputStream(new File(resultFileName).toPath()), DEST_FILE_ENCODING))) {
            FileVisitor<Path> simpleFileVisitor = new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path visitedFilePath, BasicFileAttributes fileAttributes)
                        throws IOException {
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(Files.newInputStream(visitedFilePath.toFile().toPath()), SRC_FILE_ENCODING))) {
                        String str;
                        while ((str = in.readLine()) != null) {
                            out.write(str + "\n");
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            };
            FileSystem fileSystem = FileSystems.getDefault();
            Path rootPath = fileSystem.getPath(dirName);
            Files.walkFileTree(rootPath, simpleFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}