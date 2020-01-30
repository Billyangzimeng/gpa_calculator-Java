package Homework.HW_05;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Homework #5
 * Name: Bill Yang 
 * UVa email: zy5nj
 * Main Class for GPA/Course Planning
 */


public class CoursePlanner extends JFrame {

	// Fields 
	//JFrame and JPanel
	JFrame userInterface;
	JPanel mainPanel;
    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
	
    //Buttons
	JButton addCourse;
	JButton removeCourse;
	JButton removeAllcourse;
	JButton planGPA;
	
	//Text Areas
	JTextArea enterCourseCredit;
	JTextArea enterCourseName;
	JTextArea enterCourseGrade;
	JTextArea enterRemoveCourse;
	JTextArea enterTargetGrade;
	
	//Labels
	JLabel courseName;
	JLabel courseGrade;
	JLabel courseCredit;
	JLabel cumulativeGPA;
	JLabel targetGPA;
	JLabel requiredGPA;
	JLabel courseSummary;

	//A Nice Table..
	JTable courseTable;

	//Global Variables
	ArrayList<Course> listOfcourses;
	double total_credits = 0;
	double credits_taken = 0;
	double cumulativegpa = 0;
	double targetgpa = 0;
	double requiredgpa = 0; 
	
	
	// GUI Constructor
	/**This method is the main method that drives the GUI */
	public CoursePlanner() {
		
		//Creating a Frame 
		userInterface = new JFrame ("Course Planner");
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		userInterface.setSize(width, height); //Using screen resolution size, adjustable
		
		//Creating Panels
		///Main panel using a three part BoxLayout format
		mainPanel = new JPanel(); 
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		//Three main section sub-panel, using FridLayout and BorderLayout
		JPanel calculations = new JPanel(new GridLayout(1, 2, 5, 5));
		calculations.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calculations.setBorder(new TitledBorder(new EtchedBorder(),"Course & GPA Calculator"));		
		JPanel course = new JPanel(new BorderLayout());
		course.setBorder(new TitledBorder(new EtchedBorder(),"Course Summary"));
		JPanel summary = new JPanel(new BorderLayout());
		summary.setBorder(new TitledBorder(new EtchedBorder(),"Planner Summary"));
		
		//Four mini-panel inside the sub-panels, using GridLayout
		JPanel calc1 = new JPanel(new GridLayout(3, 2, 5, 5));
		JPanel calc2 = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel calc2_top = new JPanel(new GridLayout(1, 4, 5, 5));
		JPanel calc2_bottom = new JPanel(new GridLayout(1, 4, 5, 5));

		
		//Setting Items
		//All components inside each panel initialized below
		addCourse = new JButton("Add Course");
		removeCourse = new JButton("Remove Selected Course");
		removeAllcourse = new JButton("Remove All Courses");
		planGPA = new JButton("Calculate Required GPA");
		enterCourseCredit = new JTextArea();
		enterCourseName = new JTextArea();
		enterCourseGrade = new JTextArea();
		enterRemoveCourse = new JTextArea();
		enterTargetGrade = new JTextArea();
		courseName = new JLabel("Enter Course Name: ");
		courseGrade = new JLabel("Enter Course Grade: ");
		courseCredit = new JLabel("Enter Course Credit: ");
		cumulativeGPA = new JLabel("Cumulative GPA: ");
		targetGPA = new JLabel("Enter Target GPA: ");
		requiredGPA = new JLabel("Required GPA: ");
		courseSummary = new JLabel();
		listOfcourses = new ArrayList<Course>();
	
		
		//Making a JTable
		String column_title[] = {"Course Title", "Credit Hours", "Grade"};
		DefaultTableModel model = new DefaultTableModel(column_title, 0);
		courseTable = new JTable(model);
		
		//Adding button listeners
		addCourse.addActionListener(new addCourseButtonListener());
		removeCourse.addActionListener(new removeCourseButtonListener());
		removeAllcourse.addActionListener(new removeAllcourseButtonListener());
		planGPA.addActionListener(new planGPAButtonListener());
		
		//Giving borders to all components adding aestheticism
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		enterCourseName.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		enterCourseCredit.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		enterCourseGrade.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		enterTargetGrade.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		addCourse.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		removeCourse.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		removeAllcourse.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		planGPA.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		courseTable.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		courseName.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		courseCredit.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		courseGrade.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		cumulativeGPA.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		targetGPA.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		requiredGPA.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		courseSummary.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		//Assembling the panels
		//Adding components into each mini-panels
		calc1.add(courseName);
		calc1.add(enterCourseName);
		calc1.add(courseCredit);
		calc1.add(enterCourseCredit);
		calc1.add(courseGrade);
		calc1.add(enterCourseGrade);
		calc2_top.add(addCourse);
		calc2_top.add(removeCourse);
		calc2_top.add(removeAllcourse);
		calc2_top.add(planGPA);
		calc2_bottom.add(cumulativeGPA);
		calc2_bottom.add(requiredGPA);
		calc2_bottom.add(targetGPA);
		calc2_bottom.add(enterTargetGrade);
		
		//Adding mini-panels into sub-panels
		calc2.add(calc2_top);
		calc2.add(calc2_bottom);
		calculations.add(calc1);
		calculations.add(calc2);
		course.add(new JScrollPane(courseTable));
		summary.add(courseSummary);
		
		//Adding sub-panels into the main panel, and the main panel into the frame
		mainPanel.add(calculations);
		mainPanel.add(course);
		mainPanel.add(summary);
		userInterface.add(mainPanel);

		//Concluding Touches
		userInterface.setDefaultCloseOperation(EXIT_ON_CLOSE); //Close it by default
        userInterface.setLocationRelativeTo(null); // Center it on the screen 
        userInterface.pack();
		userInterface.setVisible(true); //Set the frame to visible
	}


	// Button Listeners
	/**This method answers to the call when "addCourse" button is pressed */
	private class addCourseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
			if (!(enterCourseCredit.getText().equals(""))) {
				//If credit hour is given, add the new course
				String name = "";
				double numGrade = 0;
				double credithours = 0;
				String chInput = enterCourseCredit.getText();
				credithours = Double.parseDouble(chInput);
				
				//Add a new course with the given name and grade
				if (!(enterCourseGrade.getText().equals("")) && !(enterCourseName.getText().equals(""))) {
					String courseInput = enterCourseName.getText();
					String gradeInput = enterCourseGrade.getText();
					model.addRow(new Object[] { courseInput, chInput, gradeInput });
					name = courseInput;
					numGrade = Double.parseDouble(gradeInput);
					Course new_course = new Course(name, credithours, numGrade);
					listOfcourses.add(new_course);
				}
				
				//Add a new course with the given grade with an unknown name
				else if (!(enterCourseGrade.getText().equals(""))) {
					name = "Unknown Course";
					String courseInput = name;
					String gradeInput = enterCourseGrade.getText();
					numGrade = Double.parseDouble(gradeInput);
					model.addRow(new Object[] { courseInput, chInput, gradeInput });
					Course new_course = new Course(name, credithours, numGrade);
					listOfcourses.add(new_course);
				}
				
				//Add a new course with the given name with the default grade, considered as "a new course"
				else if (!(enterCourseName.getText().equals(""))) {
					String courseInput = enterCourseName.getText();
					String gradeInput = Double.toString(numGrade);
					model.addRow(new Object[] { courseInput, chInput, gradeInput });
					name = courseInput;
					Course new_course = new Course(name, credithours, numGrade);
					listOfcourses.add(new_course);
				}
				
				//Add a new course with the given credit hour with default name and grade, considered as "a new course"
				else {
					name = "A New Course";
					String courseInput = name;
					String gradeInput = Double.toString(numGrade);
					model.addRow(new Object[] { courseInput, chInput, gradeInput });
					Course new_course = new Course(name, credithours, numGrade);
					listOfcourses.add(new_course);
				}
			}
			else { //Add a general course with default credit hour, name, and grade, if not otherwise specified
				Course general_course = new Course(3.0);
				String courseInput = general_course.getName();
				String chInput = Double.toString(general_course.getCredithours());
				String gradeInput = Double.toString(general_course.getNumGrade());
				model.addRow(new Object[] { courseInput, chInput, gradeInput });
				listOfcourses.add(general_course);
			} 
			enterCourseName.setText(null);
			enterCourseCredit.setText(null);
			enterCourseGrade.setText(null);
		}
	}
	
	/**This method answers to the call when "removeCourse" button is pressed */
	private class removeCourseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Looping through the JTable removing all selected rows
			DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
			int[] selected_course = courseTable.getSelectedRows();
			//System.out.println(Arrays.toString(selected_course));
			for (int i = selected_course.length - 1; i >= 0; i--) {
				model.removeRow(selected_course[i]);
				listOfcourses.remove(selected_course[i]); 
			}
			cumulativeGPA.setText("Cumulative GPA: ");
			requiredGPA.setText("Required GPA: ");
			enterTargetGrade.setText(null);
		}
	}
	
	/**This method answers to the call when "removeAllcourse" button is pressed */
	private class removeAllcourseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Removing everything inside the JTable and Course ArrayList
			DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			} 
			for (int i = rowCount - 1; i >= 0; i--) {
				listOfcourses.remove(i);
			} 
			total_credits = 0;
			credits_taken = 0;
			cumulativegpa = 0;
			targetgpa = 0;
			requiredgpa = 0;
			courseSummary.setText("");
			cumulativeGPA.setText("Cumulative GPA: ");
			requiredGPA.setText("Required GPA: ");
			enterTargetGrade.setText(null);
		} //Resetting all related fields
	}
	
	/**This method answers to the call when "planGPA" button is pressed */
	private class planGPAButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!(enterTargetGrade.getText().equals("")) && (listOfcourses.size() != 0)) {
				//Clearing fields from most recent input
				cumulativeGPA.setText("Cumulative GPA: ");
				requiredGPA.setText("Required GPA: ");
				total_credits = 0;
				credits_taken = 0;
				cumulativegpa = 0;
				targetgpa = 0;
				requiredgpa = 0;
				
				//Extracting the input and setting up for the operation
				Course letterGPA = new Course(3);
				targetgpa = Double.parseDouble(enterTargetGrade.getText());
				System.out.println("Target GPA: " + targetgpa);
				
				//GPA Compiler
				for (Course course : listOfcourses) {
					//System.out.println(course.getName());
					if (!(course.getName().equals("A New Course"))) { //To if the course has been taken
						cumulativegpa += course.getNumGrade() * course.getCredithours(); //Cumulative GPA per credit hours
						credits_taken += course.getCredithours(); //Determine the total credits taken
					}
					total_credits += course.getCredithours(); //Get total credit hours planned
				}
				//System.out.println(listOfcourses);
				if (cumulativegpa == 0) {
				}
				else {
					cumulativegpa = cumulativegpa / credits_taken; 
				} //Current cumulative GPA calculated
				System.out.println("Cumulative GPA: " + cumulativegpa);

				
				//Converting into a letter grade
				letterGPA.setNumGrade(cumulativegpa);
				letterGPA.gpaConverter();
				cumulativeGPA.setText("Cumulative GPA: " + letterGPA.getLetterGrade());
				
				//GPA Planner
				double total_grade_taken = cumulativegpa * credits_taken;
				double total_grade_target = targetgpa * total_credits;
				if ((total_grade_target - total_grade_taken) == 0) {
					requiredgpa = 0; //If cumulative and target GPA are the same, set required GPA to 0
				} //Not default "NaN" returned by "0" division
				else {
					requiredgpa = (total_grade_target - total_grade_taken) / (total_credits - credits_taken);
				} //Required GPA calculated using weighted GPA
				System.out.println("Required GPA: " + requiredgpa);


				//Converting into a letter grade
				letterGPA.setNumGrade(requiredgpa);
				letterGPA.gpaConverter();
				requiredGPA.setText("Required GPA: " + letterGPA.getLetterGrade());
				enterTargetGrade.setText(null);
				
				//Warnings based on required GPA 
				courseSummary.setText("");
				if (requiredgpa > 4.0) {
					courseSummary.setText('\n' + "Required GPA is above 4.0, try to add more credit hours");
				} 
				else if (requiredgpa < 2.0) {
					courseSummary.setText('\n' + "Required GPA is below 2.0, try to take less credit hours");
				}
			}
		}
	}
	

	// Main Method Testing
	public static void main(String[] args) {
		new CoursePlanner(); //Making the Course Planner GUI
	}

}
