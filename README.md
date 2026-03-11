# Study Tracker App

The Student Tracker is a console-based C++ application designed to help students log, review, and analyze their daily study sessions efficiently. It serves as a digital study diary that enables better time management and productivity tracking.

Project Name: Student Tracker Using Java

##📌 Project Overview

Study Tracker is a Java console-based application designed to help students track their daily study activities.

The application allows users to record study sessions, view logs, generate summaries, and export data to a CSV file.

This project demonstrates the use of Object-Oriented Programming (OOP), Collections Framework, File Handling, and Java Time API.

---

##🚀 Features

➕ Add new study logs

📋 View all study logs

📊 Generate summaries

By Date

By Subject

By Status (Done/Pending)

📁 Export study logs to CSV file

🧠 Simple menu-driven interface
---

##🛠 Technologies Used

- Java
- OOP (Classes & Objects)
-ArrayList
-TreeMap
-File Handling (CSV Export)
-LocalDate API

##📂 Project Structure
```
StudyTracker/
│
|-- StudyTracker.java
|--StudyTracker.csv
|-- README.md
```
## The project provides :

### 1. Insert Study Log Management  

Users can insert new study records with the following details:  

        Date (fetched automatically from system)  
        Subject  
        Duration (in hours)  
        Description of the study activity 
        Status (Done / Pending)

### 2. Display Logs

Displays all study records in a structured manner.  
Each entry shows:  

        Date  
        Subject  
        Time spent  
        Description  
        Status

### 3. Summary Reports  

Summary by Date: Caluculates and displays total study hours per day.  
Summary by Subject : Displays Subject and time spent on it per day. 
Summary by Status : Display Status and time soend on it per day.

### 4. Menu-Driven Interface  

User-friendly console menu with numbered options:  

        Insert Log  
        View Logs  
        Summaries  
        Export to CSV  
        Exit  

### 5. Key Benefits

Helps students identify and analyze study patterns.  
Improves time management and productivity.  
Provides structured data for academic tracking through CSV export.  

### 6.📁 CSV Export Feature  

Exports all study logs into a CSV file (Study.csv).  
The file can be opened in Excel, Google Sheets, or any spreadsheet tool.  

### 7. Scope for Future Enhancements  

Retain previous data from .csv file.  
Update realtime data in csv for every log entry
