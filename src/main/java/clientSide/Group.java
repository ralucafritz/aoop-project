package clientSide;

import extras.Util;
import interfaces.GetNameInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Group implements GetNameInterface {
    private int currentID;
    private ArrayList<Student> studentsList = new ArrayList<Student>();
    private ArrayList<Professor> professorsList = new ArrayList<Professor>();
    private Set<Course> coursesList = new HashSet<>();

    private static int ID =  100;
    // constructors

    public Group() {
        this.currentID = ID;
        ID ++;
    }

    public Group( ArrayList<Student> students) {
        this.studentsList = students;
        this.currentID = ID;
        ID ++;
    }

    // mutators

    public void addCourse(Course courseToBeAdded) throws Exception {
        if(!coursesList.contains(courseToBeAdded)) {
            this.coursesList.add(courseToBeAdded);
            this.professorsList.add(courseToBeAdded.getProfessor());
            for(Student student : studentsList){
                student.addCourse(courseToBeAdded);
            }
        }
        else {
            throw new Exception("This group is already enrolled in that course.");
        }


    }

    public void removeCourse(Course courseToBeRemoved) throws Exception {
        if(coursesList.contains(courseToBeRemoved)) {
            this.coursesList.remove(courseToBeRemoved);
            this.coursesList.remove(courseToBeRemoved.getProfessor());
            for (Student student : studentsList) {
                student.removeCourse(courseToBeRemoved);
            }
        }
        else {
            throw new Exception("This group is not enrolled in that course.");
        }
    }

    public void addStudent(Student studentToBeAdded) {
        if(!studentsList.contains(studentToBeAdded)) {
            this.studentsList.add(studentToBeAdded);
        }
    }

    public void removeStudent(Student studentToBeRemoved) {
        if(studentsList.contains(studentToBeRemoved)) {
            this.studentsList.remove(studentToBeRemoved);
        }
    }

    // accessors

    public String getName() {
        return "" + currentID;
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public Set<Course> getCoursesList() {
        return coursesList;
    }

    public ArrayList<Professor> getProfessorsList() {
        return professorsList;
    }

    // extra methods:

    public String printStudentsList(String sep1, String sep2) {
        this.sortStudentsByName();
       return Util.arrayListToString(this.studentsList, sep1, sep2).toString();
    }

    public String printProfessorList(String sep1, String sep2) {
        this.sortProfessorsByName();
        return Util.arrayListToString(this.professorsList, sep1, sep2).toString();
    }

    public String printCoursesList(String sep1, String sep2) {
        return Util.setToString(this.coursesList, sep1,sep2).toString();
    }

    public void sortStudentsByName() {
        this.studentsList.sort(Comparator.comparing(Student::getName));
    }

    public void sortProfessorsByName() {
        this.professorsList.sort(Comparator.comparing(Professor::getName));
    }

}