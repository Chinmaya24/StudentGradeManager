# Student Grade Management System

A comprehensive Java console application for managing student grades with advanced features for calculations, reporting, and data management.

## Features

✅ **Student Management**
- Add new students with ID, name, and age
- Support for up to 100 students
- Duplicate ID prevention

✅ **Grade Management**
- Add grades for multiple subjects (up to 10 subjects per student)
- Grade validation (0-100 range)
- Support for decimal grades

✅ **Calculations & Statistics**
- Individual student average grades
- Overall class average
- Highest and lowest scores
- Grade distribution (A, B, C, D, F ranges)

✅ **Reporting & Display**
- View all students in a formatted table
- Search students by ID or name
- Generate comprehensive summary reports
- Grade status indicators (Excellent, Very Good, Good, etc.)

✅ **Data Management**
- Arrays for efficient data storage
- Input validation and error handling
- User-friendly console interface

## Requirements

- Java 8 or higher
- No external dependencies required

## How to Compile and Run

### Step 1: Compile the Program
```bash
javac StudentGradeManager.java
```

### Step 2: Run the Program
```bash
java StudentGradeManager
```

## Usage Instructions

### Main Menu Options

1. **Add New Student** - Enter student ID, name, and age
2. **Add/Update Grades** - Add grades for existing students
3. **View All Students** - Display all students in a table format
4. **Calculate Statistics** - Show class-wide statistics and grade distribution
5. **Search Student** - Find students by ID or name
6. **Generate Summary Report** - Create a comprehensive report sorted by performance
7. **Exit** - Close the application

### Sample Workflow

1. **Add Students**: Use option 1 to add several students
2. **Add Grades**: Use option 2 to add grades for each student
3. **View Results**: Use option 3 to see all students and their grades
4. **Check Statistics**: Use option 4 to see class performance metrics
5. **Generate Report**: Use option 6 for a comprehensive summary

### Input Examples

**Student ID**: S001, S002, 2024001
**Student Name**: John Doe, Jane Smith
**Age**: 18, 19, 20
**Subjects**: Math, Science, English, History
**Grades**: 85.5, 92.0, 78.5, 88.0

## Program Structure

- **StudentGradeManager.java** - Main application class with menu system
- **Student class** - Data model for student information and grades
- **Arrays** - Used for storing students, subjects, and grades
- **Input validation** - Ensures data integrity and user-friendly experience

## Features in Detail

### Grade Calculations
- Individual averages calculated per student
- Overall class average
- Highest and lowest performing students
- Grade distribution analysis

### Data Validation
- Student ID uniqueness checking
- Age range validation (1-149)
- Grade range validation (0-100)
- Subject count limits (1-10)

### User Interface
- Clear menu system with numbered options
- Formatted table displays
- Error messages for invalid inputs
- Confirmation messages for successful operations

## Technical Details

- **Maximum Students**: 100
- **Maximum Subjects**: 10 per student
- **Grade Precision**: 2 decimal places
- **Memory Efficient**: Uses arrays instead of ArrayLists for simplicity
- **Input Handling**: Robust error handling with try-catch blocks

## Sample Output

```
=== STUDENT GRADE MANAGEMENT SYSTEM ===
Welcome to the Student Grade Manager!

==================================================
MAIN MENU
==================================================
1. Add New Student
2. Add/Update Grades
3. View All Students
4. Calculate Statistics
5. Search Student
6. Generate Summary Report
7. Exit
==================================================
Enter your choice (1-7): 
```

## Troubleshooting

- **Compilation Error**: Ensure Java is installed and in your PATH
- **Runtime Error**: Check that you're in the correct directory
- **Input Issues**: Follow the prompts exactly as shown

## Future Enhancements

- File I/O for data persistence
- GUI interface using Swing/JavaFX
- Database integration
- Export to CSV/PDF
- Grade weighting system
- Attendance tracking

---

**Note**: This is a console-based application. For GUI version, consider using Java Swing or JavaFX frameworks.
