//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Project Name : Study Tracker (Console Based)
//  Language     : Java
//  Description  : This application allows users to track their daily study activities.
//                  Users can:
//                      1. Insert study logs
//                      2. Display all study logs
//                      3. Export logs to a CSV file
//                      4. View summaries based on Date, Subject, or Status
//
//  Author      : Rekha Shankarlal Kumawat
//  Date        : 06 / 03 / 2026
//
//////////////////////////////////////////////////////////////////////////////////////////////////////

// Required Packages
import java.util.*;
import java.time.LocalDate;
import java.io.*;

//////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Class Name  : StudyLog
// Description : This class represents a single study record.
//               Each log stores information about:
//                  - Date of study
//                  - Subject studied
//                  - Duration in hours
//                  - Description of the study
//                  - Status (Done / Pending)
//
//////////////////////////////////////////////////////////////////////////////////////////////////////

class StudyLog
{
    private LocalDate Date;
    private String Subject;
    private double Duration;
    private String Description;
    private String Status;

    // Parameterized constructor
    public StudyLog(LocalDate a , String b , double c , String d , String e)
    {
        this.Date = a ;
        this.Subject = b;
        this.Duration = c;
        this.Description = d;
        this.Status = e ;
    }

    // Getter methods
    public LocalDate getDate()
    {
        return this.Date;
    }

    public String getSubject()
    {
        return this.Subject;
    }

    public double getDuration()
    {
        return this.Duration;
    }

    public String getDescription()
    {
        return this.Description;
    }

    public String getStatus()
    {
        return this.Status;
    }

    // Convert object data into readable string format
    @Override
    public String toString()
    {
        return this.Date+" | "+this.Subject +" | "+this.Duration+" | "+this.Description+" | "+this.Status;
    }
}
// End of StudyLog Class

//////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Class Name : StudyTrackerFunctionality
//
// Description : This class contains all the core operations of the Study Tracker system.
//               It manages study logs using an ArrayList and provides functions to:
//                  - Insert logs
//                  - Display logs
//                  - Export logs to CSV
//                  - Generate summaries
//
//  Author     : Rekha Shankarlal Kumawat
//  Date       : 06 / 03 / 2026
//
//////////////////////////////////////////////////////////////////////////////////////////////////////

class StudyTrackerFunctionality
{
    // ArrayList acting as in-memory database
    public ArrayList<StudyLog> Database = new ArrayList<StudyLog>();

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Method Name : InsertLog
    // Description : Accepts study details from user and stores them in the database
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void InsertLog()
    {
        Scanner sobj = new Scanner(System.in);

        System.out.println("---------------------------------------------------");
        System.out.println("---------Enter Valid Details of Your Study---------");
        System.out.println("---------------------------------------------------");

        // Current system date
        LocalDate Dateobj = LocalDate.now();

        System.out.println("Please Enter the Name of Subject like (C/C++/Java/Python): " );
        String sub = sobj.nextLine();

        System.out.println("Enter the time period of your study in hours : ");
        double dur = sobj.nextDouble();
        sobj.nextLine();

        System.out.println("Please provide Short Description of Your Study: ");
        String desc= sobj.nextLine();

        System.out.println("Enter the Status (Done/Pending) :- ");
        String stat = sobj.nextLine();

        // Creating new StudyLog object
        StudyLog studyobj = new StudyLog(Dateobj, sub, dur, desc , stat);

        // Adding log into database
        Database.add(studyobj);

        System.out.println("Study log gets stored successfully");
        System.out.println("---------------------------------------------------");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Method Name : DisplayLog
    // Description : Displays all stored study logs
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void DisplayLog()
    {
        System.out.println("----------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("-------------------Nothing to display---------------");
            System.out.println("----------------------------------------------------");
            return ;
        }

        System.out.println("-------Log Report of Study Tracker-------");
        System.out.println("----------------------------------------------------");

        // Printing each log
        for(StudyLog s : Database)
        {
            System.out.println(s);
        }

        System.out.println("---------------------------------------------------");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Method Name : ExportCSV
    // Description : Exports all logs to a CSV file
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("----------------------------------------------------");
            System.out.println("-------------------Nothing to Export---------------");
            System.out.println("----------------------------------------------------");
            return ;
        }

        String FileName = "StudyTracker.csv";

        try(FileWriter fwobj = new FileWriter(FileName))
        {
            // CSV Header
            fwobj.write("Date , Subject , Duration , Description , Status \n");

            // Writing log data
            for(StudyLog s : Database)
            {
                fwobj.write(s.getDate()+","+
                            s.getSubject().replace(",", "/")+","+
                            s.getDuration()+","+
                            s.getDescription().replace(",", "/")+","+
                            s.getStatus()+"\n");
            }

            System.out.println("----------------------------------------------\n");
            System.out.println("Data gets Exported in CSV : "+FileName);
            System.out.println("----------------------------------------------\n");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception Occurred in CSV handling");
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Method Name : SummaryByDate
    // Description : Shows total study hours grouped by Date
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void SummaryByDate()
    {
        System.out.println("----------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("------Nothing To Display as Database is Empty-------");
            System.out.println("----------------------------------------------------");
            return ;
        }

        System.out.println("---------Summary By Date From StudyTracker-----------");
        System.out.println("----------------------------------------------------");

        TreeMap<LocalDate , Double> tobj = new TreeMap<>();

        for(StudyLog sobj :Database)
        {
            LocalDate lobj = sobj.getDate();
            double d = sobj.getDuration();

            if(tobj.containsKey(lobj))
            {
                double old = tobj.get(lobj);
                tobj.put(lobj, d+old);
            }
            else
            {
                tobj.put(lobj, d);
            }
        }

        for(LocalDate l : tobj.keySet())
        {
            System.out.println("Date : "+l+" || Total study duration : "+tobj.get(l));
        }

        System.out.println("----------------------------------------------------");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Method Name : SummaryBySubject
    // Description : Shows total study hours grouped by Subject
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void SummaryBySubject()
    {
        System.out.println("----------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("------Nothing To Display as Database is Empty-------");
            System.out.println("----------------------------------------------------");
            return ;
        }

        System.out.println("---------Summary By Subject From StudyTracker--------");
        System.out.println("----------------------------------------------------");

        TreeMap<String , Double> tobj = new TreeMap<>();

        for(StudyLog sobj :Database)
        {
            String s = sobj.getSubject();
            double d = sobj.getDuration();

            if(tobj.containsKey(s))
            {
                double old = tobj.get(s);
                tobj.put(s, d+old);
            }
            else
            {
                tobj.put(s, d);
            }
        }

        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str+" || Total study duration : "+tobj.get(str));
        }

        System.out.println("----------------------------------------------------");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Method Name : SummaryByStatus
    // Description : Shows total study hours grouped by Status (Done / Pending)
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void SummaryByStatus()
    {
        System.out.println("----------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("------Nothing To Display as Database is Empty-------");
            System.out.println("----------------------------------------------------");
            return ;
        }

        System.out.println("---------Summary By Status From StudyTracker--------");
        System.out.println("----------------------------------------------------");

        TreeMap<String , Double> tobj = new TreeMap<>();

        for(StudyLog sobj :Database)
        {
            String s = sobj.getStatus();
            double d = sobj.getDuration();

            if(tobj.containsKey(s))
            {
                double old = tobj.get(s);
                tobj.put(s, d+old);
            }
            else
            {
                tobj.put(s, d);
            }
        }

        for(String str : tobj.keySet())
        {
            System.out.println("Status : "+str+" || Total study duration : "+tobj.get(str));
        }

        System.out.println("----------------------------------------------------");
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Class Name : StudyTracker
//
// Description : This is the main driver class containing the main() method.
//               It provides a menu-driven interface for users to interact with the system.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////

class StudyTracker
{
    public static void main(String A[])
    {
        Scanner sobj = new Scanner(System.in);
        StudyTrackerFunctionality stobj = new StudyTrackerFunctionality();

        System.out.println("----------------------------------------------------");
        System.out.println("--------Welcome to Study Tracker --------");
        System.out.println("----------------------------------------------------");

        int iChoice = 0;

        do
        {
            System.out.println("Please Select Appropriate Option");
            System.out.println("1 : Insert New Study Log");
            System.out.println("2 : View all Study Log");
            System.out.println("3 : Export study Log to CSV file");
            System.out.println("4 : Summary of the Study Log by Date");
            System.out.println("5 : Summary of the Study Log by Subject");
            System.out.println("6 : Summary of the Study Log by Status");
            System.out.println("7 : Exit the Application");

            System.out.println("Enter Your Choice :");
            iChoice = sobj.nextInt();

            switch (iChoice) 
            {
                case 1:
                    stobj.InsertLog();
                    break;

                case 2:
                    stobj.DisplayLog();
                    break;

                case 3:
                    stobj.ExportCSV();
                    break;

                case 4:
                    stobj.SummaryByDate();
                    break;

                case 5:
                    stobj.SummaryBySubject();
                    break;

                case 6:
                    stobj.SummaryByStatus();
                    break;

                case 7 :
                    System.out.println("----------------------------------------------------");
                    System.out.println("--------Thanks For Using StudyTracker---------------");
                    System.out.println("----------------------------------------------------");
                    break;

                default:
                    System.out.println("Please enter Valid Option");
                    break;
            }

        }while(iChoice != 7);
    }
}
// End of Application