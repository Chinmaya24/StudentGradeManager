import java.util.*;
import java.text.DecimalFormat;

public class StudentGradeManager {
    private static final int MAX_STUDENTS = 100;
    private static Student[] students = new Student[MAX_STUDENTS];
    private static int studentCount = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat df = new DecimalFormat("#.##");
    
    public static void main(String[] args) {
        System.out.println("=== STUDENT GRADE MANAGEMENT SYSTEM ===");
        System.out.println("Welcome to the Student Grade Manager!");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getValidChoice(1, 7);
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrades();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    calculateStatistics();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using Student Grade Manager!");
                    break;
            }
        }
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Student");
        System.out.println("2. Add/Update Grades");
        System.out.println("3. View All Students");
        System.out.println("4. Calculate Statistics");
        System.out.println("5. Search Student");
        System.out.println("6. Generate Summary Report");
        System.out.println("7. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-7): ");
    }
    
    private static int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Maximum number of students reached!");
            return;
        }
        
        System.out.println("\n--- ADD NEW STUDENT ---");
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        
        // Check if student ID already exists
        if (findStudentById(id) != null) {
            System.out.println("Student with ID " + id + " already exists!");
            return;
        }
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter student age: ");
        int age = getValidAge();
        
        students[studentCount] = new Student(id, name, age);
        studentCount++;
        
        System.out.println("Student " + name + " added successfully!");
    }
    
    private static int getValidAge() {
        while (true) {
            try {
                int age = Integer.parseInt(scanner.nextLine().trim());
                if (age > 0 && age < 150) {
                    return age;
                } else {
                    System.out.println("Please enter a valid age (1-149).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age.");
            }
        }
    }
    
    private static void addGrades() {
        if (studentCount == 0) {
            System.out.println("No students to add grades for!");
            return;
        }
        
        System.out.println("\n--- ADD/UPDATE GRADES ---");
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("Adding grades for: " + student.getName());
        System.out.print("Enter number of subjects: ");
        int subjectCount = getValidSubjectCount();
        
        String[] subjects = new String[subjectCount];
        double[] grades = new double[subjectCount];
        
        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Enter subject " + (i + 1) + " name: ");
            subjects[i] = scanner.nextLine().trim();
            
            System.out.print("Enter grade for " + subjects[i] + " (0-100): ");
            grades[i] = getValidGrade();
        }
        
        student.setGrades(subjects, grades);
        System.out.println("Grades added successfully!");
    }
    
    private static int getValidSubjectCount() {
        while (true) {
            try {
                int count = Integer.parseInt(scanner.nextLine().trim());
                if (count > 0 && count <= 10) {
                    return count;
                } else {
                    System.out.println("Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static double getValidGrade() {
        while (true) {
            try {
                double grade = Double.parseDouble(scanner.nextLine().trim());
                if (grade >= 0 && grade <= 100) {
                    return grade;
                } else {
                    System.out.println("Please enter a grade between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid grade.");
            }
        }
    }
    
    private static void viewAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display!");
            return;
        }
        
        System.out.println("\n--- ALL STUDENTS ---");
        System.out.printf("%-15s %-20s %-10s %-15s %-15s%n", 
                         "ID", "Name", "Age", "Average Grade", "Status");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            double avg = student.getAverageGrade();
            String status = getGradeStatus(avg);
            
            System.out.printf("%-15s %-20s %-10d %-15s %-15s%n",
                             student.getId(), 
                             student.getName(), 
                             student.getAge(),
                             avg > 0 ? df.format(avg) + "%" : "No grades",
                             avg > 0 ? status : "No grades");
        }
    }
    
    private static String getGradeStatus(double average) {
        if (average >= 90) return "Excellent";
        if (average >= 80) return "Very Good";
        if (average >= 70) return "Good";
        if (average >= 60) return "Satisfactory";
        if (average >= 50) return "Needs Improvement";
        return "Failing";
    }
    
    private static void calculateStatistics() {
        if (studentCount == 0) {
            System.out.println("No students to calculate statistics for!");
            return;
        }
        
        System.out.println("\n--- STATISTICS ---");
        
        // Calculate overall statistics
        double totalAverage = 0;
        double highestGrade = -1;
        double lowestGrade = 101;
        int studentsWithGrades = 0;
        
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student.hasGrades()) {
                double avg = student.getAverageGrade();
                totalAverage += avg;
                studentsWithGrades++;
                
                if (avg > highestGrade) highestGrade = avg;
                if (avg < lowestGrade) lowestGrade = avg;
            }
        }
        
        if (studentsWithGrades > 0) {
            totalAverage /= studentsWithGrades;
            
            System.out.println("Overall Statistics:");
            System.out.println("Total Students: " + studentCount);
            System.out.println("Students with Grades: " + studentsWithGrades);
            System.out.println("Overall Average: " + df.format(totalAverage) + "%");
            System.out.println("Highest Average: " + df.format(highestGrade) + "%");
            System.out.println("Lowest Average: " + df.format(lowestGrade) + "%");
            
            // Grade distribution
            System.out.println("\nGrade Distribution:");
            int[] gradeRanges = new int[5]; // A, B, C, D, F
            
            for (int i = 0; i < studentCount; i++) {
                Student student = students[i];
                if (student.hasGrades()) {
                    double avg = student.getAverageGrade();
                    if (avg >= 90) gradeRanges[0]++;
                    else if (avg >= 80) gradeRanges[1]++;
                    else if (avg >= 70) gradeRanges[2]++;
                    else if (avg >= 60) gradeRanges[3]++;
                    else gradeRanges[4]++;
                }
            }
            
            System.out.println("A (90-100%): " + gradeRanges[0] + " students");
            System.out.println("B (80-89%): " + gradeRanges[1] + " students");
            System.out.println("C (70-79%): " + gradeRanges[2] + " students");
            System.out.println("D (60-69%): " + gradeRanges[3] + " students");
            System.out.println("F (0-59%): " + gradeRanges[4] + " students");
        } else {
            System.out.println("No students have grades yet!");
        }
    }
    
    private static void searchStudent() {
        if (studentCount == 0) {
            System.out.println("No students to search!");
            return;
        }
        
        System.out.println("\n--- SEARCH STUDENT ---");
        System.out.println("Search by:");
        System.out.println("1. Student ID");
        System.out.println("2. Student Name");
        System.out.print("Enter choice (1-2): ");
        
        int choice = getValidChoice(1, 2);
        
        if (choice == 1) {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine().trim();
            Student student = findStudentById(id);
            displayStudentDetails(student);
        } else {
            System.out.print("Enter student name (or part of name): ");
            String name = scanner.nextLine().trim().toLowerCase();
            searchStudentsByName(name);
        }
    }
    
    private static void searchStudentsByName(String searchName) {
        System.out.println("\nSearch results for: " + searchName);
        System.out.println("-".repeat(60));
        
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student.getName().toLowerCase().contains(searchName)) {
                displayStudentDetails(student);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No students found matching: " + searchName);
        }
    }
    
    private static void displayStudentDetails(Student student) {
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n--- STUDENT DETAILS ---");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        
        if (student.hasGrades()) {
            System.out.println("Grades:");
            String[] subjects = student.getSubjects();
            double[] grades = student.getGrades();
            
            for (int i = 0; i < subjects.length; i++) {
                System.out.printf("  %-15s: %s%%%n", subjects[i], df.format(grades[i]));
            }
            
            System.out.println("Average Grade: " + df.format(student.getAverageGrade()) + "%");
            System.out.println("Status: " + getGradeStatus(student.getAverageGrade()));
        } else {
            System.out.println("No grades recorded yet.");
        }
    }
    
    private static void generateReport() {
        if (studentCount == 0) {
            System.out.println("No students to generate report for!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("STUDENT GRADE SUMMARY REPORT");
        System.out.println("Generated on: " + new Date());
        System.out.println("=".repeat(80));
        
        // Summary header
        System.out.println("Total Students: " + studentCount);
        int studentsWithGrades = 0;
        double totalAverage = 0;
        
        for (int i = 0; i < studentCount; i++) {
            if (students[i].hasGrades()) {
                studentsWithGrades++;
                totalAverage += students[i].getAverageGrade();
            }
        }
        
        if (studentsWithGrades > 0) {
            totalAverage /= studentsWithGrades;
            System.out.println("Students with Grades: " + studentsWithGrades);
            System.out.println("Overall Class Average: " + df.format(totalAverage) + "%");
        }
        
        System.out.println("\n" + "-".repeat(80));
        System.out.printf("%-15s %-20s %-10s %-15s %-15s%n", 
                         "ID", "Name", "Age", "Average Grade", "Status");
        System.out.println("-".repeat(80));
        
        // Sort students by average grade (highest first)
        Student[] sortedStudents = Arrays.copyOf(students, studentCount);
        Arrays.sort(sortedStudents, 0, studentCount, 
                   (s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade()));
        
        for (int i = 0; i < studentCount; i++) {
            Student student = sortedStudents[i];
            double avg = student.getAverageGrade();
            String status = getGradeStatus(avg);
            
            System.out.printf("%-15s %-20s %-10d %-15s %-15s%n",
                             student.getId(), 
                             student.getName(), 
                             student.getAge(),
                             avg > 0 ? df.format(avg) + "%" : "No grades",
                             avg > 0 ? status : "No grades");
        }
        
        System.out.println("=".repeat(80));
        System.out.println("Report completed successfully!");
    }
    
    private static Student findStudentById(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }
}

class Student {
    private String id;
    private String name;
    private int age;
    private String[] subjects;
    private double[] grades;
    private boolean hasGrades;
    
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hasGrades = false;
    }
    
    public void setGrades(String[] subjects, double[] grades) {
        this.subjects = subjects.clone();
        this.grades = grades.clone();
        this.hasGrades = true;
    }
    
    public double getAverageGrade() {
        if (!hasGrades || grades.length == 0) {
            return 0;
        }
        
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
    
    public boolean hasGrades() {
        return hasGrades;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String[] getSubjects() { return subjects; }
    public double[] getGrades() { return grades; }
}
