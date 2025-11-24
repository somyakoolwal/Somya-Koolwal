package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataIOService {

    private static final Path DATA_DIRECTORY = Paths.get("data");

    public void exportData(List<Student> students, List<Course> courses) throws IOException {
        if (Files.notExists(DATA_DIRECTORY)) {
            Files.createDirectories(DATA_DIRECTORY);
        }

        Path studentFile = DATA_DIRECTORY.resolve("students.csv");
        List<String> studentLines = students.stream()
                .map(s -> s.getId() + "," + s.getRegNo() + "," + s.getFullName() + "," + s.getEmail())
                .collect(Collectors.toList());
        Files.write(studentFile, studentLines);
        System.out.println("Successfully exported " + students.size() + " students to " + studentFile.toAbsolutePath());

        Path courseFile = DATA_DIRECTORY.resolve("courses.csv");
        List<String> courseLines = courses.stream()
                .map(c -> c.getCode() + "," + c.getTitle() + "," + c.getCredits() + "," + c.getDepartment())
                .collect(Collectors.toList());
        Files.write(courseFile, courseLines);
        System.out.println("Successfully exported " + courses.size() + " courses to " + courseFile.toAbsolutePath());
    }

    public long backupData() throws IOException {
        Path sourceDir = DATA_DIRECTORY;
        if (Files.notExists(sourceDir)) {
            System.out.println("Backup failed: Data directory does not exist. Please export data first.");
            return 0;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path backupDir = Paths.get("backup", "backup_" + timestamp);
        Files.createDirectories(backupDir);

        try (Stream<Path> stream = Files.walk(sourceDir)) {
            stream.forEach(source -> {
                try {
                    Path destination = backupDir.resolve(sourceDir.relativize(source));
                    if (Files.isDirectory(source)) {
                        if (Files.notExists(destination)) {
                            Files.createDirectories(destination);
                        }
                    } else {
                        Files.copy(source, destination);
                    }
                } catch (IOException e) {
                    System.err.println("Could not copy file: " + e.getMessage());
                }
            });
        }

        System.out.println("Backup created successfully at: " + backupDir.toAbsolutePath());
        return calculateDirectorySize(backupDir);
    }

    private long calculateDirectorySize(Path path) throws IOException {
        if (Files.isRegularFile(path)) {
            return Files.size(path);
        }

        long size = 0;
        try (Stream<Path> stream = Files.list(path)) {
            for (Path entry : stream.collect(Collectors.toList())) {
                size += calculateDirectorySize(entry); // Recursive call
            }
        }
        return size;
    }
}