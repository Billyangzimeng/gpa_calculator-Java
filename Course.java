package Homework.HW_05;


import java.util.InputMismatchException;

/**
 * Homework #5
 * Name: Bill Yang 
 * UVa email: zy5nj
 * Sub-Class for Courses and GPA Calculations
 */


public class Course {

	// Fields 
	private String name;
	private double numGrade;
	private String letterGrade;
	private double credithours;
	
	// Constructors
	/**This method creates a course with given course name, credit hours, and grade */
	public Course (String courseName, double creditHours, double courseGrade) {
		name = courseName;
		credithours = creditHours;
		numGrade = courseGrade;
		letterGrade = "";
	} //Field initialization
	
	/**This method creates a course with given credit hours only, with a default grade and name */
	public Course (double creditHours) {
		name = "A New Course";
		numGrade = 0;
		credithours = creditHours;
		letterGrade = "";
	} //Field initialization
	
	
	// Some Useful Methods
	/**This method turns an number GPA into a letter grade */
	public void gpaConverter() {
		double numGPA = this.getNumGrade();
		try {
			if (numGPA >= 4.0) {
				this.letterGrade = "A"; }
			if (3.7 <= numGPA && numGPA < 4.0) {
				this.letterGrade = "A-"; 
			}
			if (3.3 <= numGPA && numGPA < 3.7) {
				this.letterGrade = "B+";
			}
			if (3.0 <= numGPA && numGPA < 3.3) {
				this.letterGrade = "B";
			}
			if (2.7 <= numGPA && numGPA < 3.0) {
				this.letterGrade = "B-";
			}
			if (2.3 <= numGPA && numGPA < 2.7) {
				this.letterGrade = "C+";
			}
			if (2.0 <= numGPA && numGPA < 2.3) {
				this.letterGrade = "C";
			}
			if (1.7 <= numGPA && numGPA < 2.0) {
				this.letterGrade = "C-";
			}
			if (1.3 <= numGPA && numGPA < 1.7) {
				this.letterGrade  = "D+";
			}
			if (1.0 <= numGPA && numGPA < 1.3) {
				this.letterGrade = "D";
			}
			if (0.7 <= numGPA && numGPA < 1.0) {
				this.letterGrade = "D-";
			}
			if (0.0 <= numGPA && numGPA < 0.7) {
				this.letterGrade = "F";
			} 
		}
		catch (InputMismatchException e) {
			System.out.print("Input Mismatch Exception ");
		}
		catch (NumberFormatException e) {
			System.out.print("Number Format Exception ");
		}
		catch (Exception e) {}
	}
	
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public double getNumGrade() {
		return numGrade;
	}

	public double getCredithours() {
		return credithours;
	}
	
	public String getLetterGrade() {
		return letterGrade;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumGrade(double grade) {
		this.numGrade = grade;
	}
	
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	
	public void setCredithours(int credithours) {
		this.credithours = credithours;
	}

	// toString() and equals() Methods
	@Override
	/**The equals(Object o) method for the Course class */
	public boolean equals(Object objcourse) {
		if (objcourse == null) {
			return false; 
		} //if objcourse is null then return false
		if( objcourse instanceof Course) {
			Course objcourse_confirmed = (Course) objcourse; //Cast objcourse to a course
			return (this.getName() == objcourse_confirmed.getName() 
					&& this.getCredithours() == objcourse_confirmed.getCredithours()); 
					//Comparing Course's Name and credit hours
		} //Return true if the incoming courses have the same course name
		else {
			return false;
		} //objcourse is not a Course... 
	}

	/**The toString() method for the Course class */
	public String toString() {
		this.gpaConverter();
		return ("Course Name: " + this.name + " Credits: " + this.credithours + " GPA: " + this.letterGrade);
	} //Return the Course's attributes 
	
	
	// Main Method Testing
	public static void main(String[] args) {
		Course la = new Course("CS 2110", 3.0, 4.0);
		Course laa = new Course(3.0);
		laa.setName("CS 2110");
		la.gpaConverter();
		System.out.println(la);
		System.out.println(la.equals(laa));
	}

	
}
