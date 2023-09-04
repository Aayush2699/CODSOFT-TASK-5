/*                                    ***STUDENT MANAGEMENT SYSTEM***
1. Create a Student class to represent individual students. Include attributes such as name, roll number, grade, and
   any other relevant details.
2. Implement a StudentManagementSystem class to manage the collection of students. Include methods to add a student,
   remove a student, search for a student, and display all students.
3. Design the user interface for the Student Management System. This can be a console-based interface or a graphical
   user interface (GUI) using libraries like Swing or JavaFX.
4. Implement methods to read and write student data to a storage medium, such as a file or a database.
5. Allow users to interact with the Student Management System by providing options such as adding a new student,editing
   an existing student's information, searching for a student, displaying all students, and exiting the application.
6. Implement input validation to ensure that required fields are not left empty and that the student data is in the
   correct format*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Student extends JFrame implements ActionListener {
    String name;
    String roll_no;
    String c;
    String Percentage;
    public Student(String name,String roll_no,String c,String percentage)
    {
        this.Percentage=percentage;
        this.roll_no=roll_no;
        this.c=c.toUpperCase();
        this.name=name.toUpperCase();
    }
    public String getName()
    {
        return name;
    }
    public String getC()
    {
        return c;
    }
    public String getRoll_no()
    {
        return roll_no;
    }
    public String getPercentage()
    {
        return Percentage;
    }
    JLabel l1, l2, l3, l4;
    JTextField t1, t2, t3, t4;
    JTextArea a;
    ArrayList<Student> student=new ArrayList<>();

    public Student() {
        setTitle("STUDENT MANAGEMENT SYSTEM");
        setSize(500, 500);
        setLayout(new BorderLayout());

        JPanel p = new JPanel(new GridLayout(6, 3, 5, 5));
        p.setBorder(new EmptyBorder(10, 20, 15, 15));
        p.setBackground(Color.LIGHT_GRAY);

        l1 = new JLabel("Name :");
        l2 = new JLabel("Roll No :");
        l3 = new JLabel("Class :");
        l4 = new JLabel("Percentage :");

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();

        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
        p.add(l3);
        p.add(t3);
        p.add(l4);
        p.add(t4);

        JButton button1 = new JButton("Add");
        JButton button2 = new JButton("Remove");
        JButton button3 = new JButton("Search");
        JButton button4 = new JButton("Display");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        //Adding button on the panel-
        p.add(button1);
        p.add(button2);
        p.add(button3);
        p.add(button4);

        add(p, BorderLayout.NORTH);

        a = new JTextArea();
        a.setEditable(false);
        p.add(a);

        JScrollPane sc = new JScrollPane(a);
        add(sc, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add")) {
            String name = t1.getText().trim();
            String roll = t2.getText().trim();
            String c = t3.getText().trim();
            String per = t4.getText().trim();

            if (!name.isEmpty() && !roll.isEmpty() && !c.isEmpty() && !per.isEmpty()) {
                Student st = new Student(name,roll,c,per);
                student.add(st);
                JOptionPane.showMessageDialog(this, "Inserted Successfully");
                clearField();
                clearArea();
            }
            else {
                JOptionPane.showMessageDialog(this, "Please fill all the details");
            }
        }

        if (e.getActionCommand().equals("Remove")) {
            clearArea();
            String roll = t2.getText().toUpperCase().trim();

                for (Student s : student) {
                    if (Objects.equals(s.getRoll_no(), roll)) {
                        student.remove(s);
                        JOptionPane.showMessageDialog(this, "Details removed");
                        clearField();
                        return;
                    }
                }
            if (roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter roll no to remove the details");
                clearField();
            }
            if(!roll.isEmpty()) {
                if (!Objects.equals(getRoll_no(), roll)) {
                    JOptionPane.showMessageDialog(this, "No record found");
                    clearField();
                }
            }
        }

        if (e.getActionCommand().equals("Search")) {
            clearArea();
            String roll = t2.getText().toUpperCase().trim();

            for (Student s : student) {
                if (Objects.equals(s.getRoll_no(), roll)) {
                    a.append("Details of the student are: ");
                    a.append("\n");
                    a.append("Name: " + s.getName() + "\n");
                    a.append("Roll no: " + s.getRoll_no() + "\n");
                    a.append("Class: " + s.getC() + "\n");
                    a.append("Percentage: " + s.getPercentage() + "\n");
                    clearField();
                    return;
                }
            }
            if(roll.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Enter roll no to search the details");
                clearField();
            }
            if(!roll.isEmpty()) {
                if (!Objects.equals(getRoll_no(), roll)) {
                    JOptionPane.showMessageDialog(this, "No record found");
                    clearField();
                }
            }
        }

        if(e.getActionCommand().equals("Display"))
        {
            a.setText("");
            for (Student s : student) {
                a.append("Name: " + s.getName() + "\n");
                a.append("Roll no: " + s.getRoll_no() + "\n");
                a.append("Class: " + s.getC() + "\n");
                a.append("Percentage: " + s.getPercentage() + "\n");
                a.append("\n");
            }
        }
    }
    void clearField ()
        {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
        }

    void clearArea()
    {
        a.setText("");
    }
    public static void main (String[]args){
            SwingUtilities.invokeLater(() -> new Student().setVisible(true));
        }
}
