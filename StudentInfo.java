

import java.time.LocalDate;
 import java.time.Period; 
 import java.time.format.DateTimeFormatter;
  import java.time.format.DateTimeParseException;
import java.util.*;

class DateUtil {

/**

*  Parses a date string in DD-MM-YYYY or YYYY-MM-DD format and returns a LocalDate.

*  Returns null if format is invalid.

*/ public static LocalDate parseDate(String dateStr) { if (dateStr == null) return null; dateStr = dateStr.trim();

DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy"); DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

// Try dd-MM-yyyy

try { return LocalDate.parse(dateStr, formatter1);

} catch (DateTimeParseException e) {

// Ignore and try next

}

// Try yyyy-MM-dd

try { return LocalDate.parse(dateStr, formatter2); } catch (DateTimeParseException e) {

// Ignore and report failure below

}

return null;

}

/**

* Calculates age in years given date of birth.

*/ public static int calculateAge(LocalDate dob) { if (dob == null) return -1; LocalDate today = LocalDate.now(); if (dob.isAfter(today)) return -1; return Period.between(dob, today).getYears();

}

}

class Student { private String name; private LocalDate dob;

public Student(String name, String dobString) { this.name = name; this.dob = DateUtil.parseDate(dobString); if (this.dob == null) {

throw new IllegalArgumentException("Invalid date format for Date of Birth. Must be DD-MMYYYY or YYYY-MM-DD");

}

}

public void displayStudentInfo() { int age = DateUtil.calculateAge(dob); if (age < 0) {

System.out.println("Invalid Date of Birth.");

} else {

System.out.println("Student Name: " + name);

System.out.println("Age: " + age + " years");

}

}

public String getName() { return name;

}

public LocalDate getDob() { return dob;

}

}

class StudentCourseInformation {
  System.out.println("Student details");

// Map semester number -> list of courses and marks private Map<Integer, List<CourseMark>> semesterCourses;

public StudentCourseInformation() { semesterCourses = new HashMap<>();

}

public void addCourse(int semesterNumber, String courseName, int marks) { semesterCourses.computeIfAbsent(semesterNumber, k -> new ArrayList<>())

.add(new CourseMark(courseName, marks));

}

public void displayCourseInformation() { if (semesterCourses.isEmpty()) {

System.out.println("No course information available."); return;

}

for (Map.Entry<Integer, List<CourseMark>> entry : semesterCourses.entrySet()) { int semester = entry.getKey();

List<CourseMark> courses = entry.getValue();

System.out.println("Semester " + semester + ":");

System.out.printf("%-25s %s\n", "Course", "Marks Obtained");

System.out.println("--------------------------------------");

for (CourseMark cm : courses) {

System.out.printf("%-25s %d\n", cm.courseName, cm.marks);

}

System.out.println();

}

}

private static class CourseMark { String courseName; int marks;

public CourseMark(String courseName, int marks) {

this.courseName = courseName; this.marks = marks;

}

}

}

public class StudentInfo { public static void main(String[] args) { try {

Student student = new Student("Alice Johnson", "15-05-2000"); student.displayStudentInfo();

System.out.println();

StudentCourseInformation courses = new StudentCourseInformation(); courses.addCourse(1, "Mathematics", 87); courses.addCourse(1, "Physics", 92); courses.addCourse(1, "Chemistry", 85); courses.addCourse(2, "Data Structures", 90); courses.addCourse(2, "Algorithms", 88); courses.addCourse(2, "Operating Systems", 93);

courses.displayCourseInformation();

} catch (IllegalArgumentException e) {

System.err.println("Error: " + e.getMessage());

}

}

}



