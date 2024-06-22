package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;
public class AddEmployee extends JFrame implements ActionListener {
    JTextField tfname, tfage, tfsal, tfphone, tfemail, tfaadhar;
    JRadioButton rbmale, rbfemale;
    JComboBox cbjob; 
    AddEmployee(){
        setLayout(null);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 30, 120, 30);
        lblname.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,30,150,30);
        add(tfname);
        
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60, 80, 120, 30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,80,150,30);
        add(tfage);
        
        JLabel lblgender = new JLabel("GENDER");
        lblgender.setBounds(60, 130, 120, 30);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblgender);
        
        rbmale = new JRadioButton("MALE");
        rbmale.setBounds(200, 130, 70, 30);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
        rbfemale = new JRadioButton("FEMALE");
        rbfemale.setBounds(280, 130, 70, 30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        JLabel lbljob = new JLabel("JOB");
        lbljob.setBounds(60, 180, 120, 30);
        lbljob.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbljob);
        
        String str[] = {"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Chefs", "Waiter/Waitress", "Manager", "Accountant","Chef"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(200, 180, 150, 30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
        
        JLabel lblsal = new JLabel("SALARY");
        lblsal.setBounds(60, 230, 120, 30);
        lblsal.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblsal);
        
        tfsal = new JTextField();
        tfsal.setBounds(200, 230,150,30);
        add(tfsal);
        
        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(60, 280, 120, 30);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200,280,150,30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60, 330, 120, 30);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 330, 150, 30);
        add(tfemail);
        
        JLabel lblaadhar = new JLabel("AADHAR");
        lblaadhar.setBounds(60, 380, 120, 30);
        lblaadhar.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(lblaadhar);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(200, 380, 150, 30);
        add(tfaadhar);
        
        JButton submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(200, 430, 150, 30);
        add(submit);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 500,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel image = new JLabel(i2);
        image.setBounds(410,80,480,410);
        add(image);
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 850, 540);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        String name = tfname.getText();
        String age = tfage.getText();
        String salary = tfsal.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String aadhar = tfaadhar.getText();
        
        String gender = null;
        
        if(rbmale.isSelected()){
            gender = "Male";            
        }else if(rbmale.isSelected()){
            gender = "Female";
        }
        String job = (String) cbjob.getSelectedItem();
        if(name.isEmpty() || age.isEmpty() || gender.isEmpty() || job.isEmpty() || salary.isEmpty() || phone.isEmpty() || email.isEmpty() || aadhar.isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields must be filled out!!!");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Phone number must be 10 digits");
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format");
            return;
        }

        if (!aadhar.matches("\\d{12}")) {
            JOptionPane.showMessageDialog(null, "Aadhar number must be 12 digits");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "insert into employee values('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "', '" + aadhar + "')";
            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Employee added successfully");

            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    
    public static void main(String[] args){
        new AddEmployee();
    }
}
