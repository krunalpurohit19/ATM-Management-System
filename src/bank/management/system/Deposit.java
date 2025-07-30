package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField textField;

    JButton deposit, back;

    String pin;
    Deposit(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);

        JLabel l1 = new JLabel("Enter Amount You Want To Deposit.");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(460,180,400,35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        textField = new JTextField();
        textField.setBackground(new Color(56, 107, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(textField);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(700,362,150,35);
        deposit.setBackground(new Color(56, 107, 128));
        deposit.setForeground(Color.WHITE);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("BACK");
        back.setBounds(700,406,150,35);
        back.setBackground(new Color(56, 107, 128));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        image.add(back);



        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String amount = textField.getText();
            Date date = new Date();
            if(e.getSource() == deposit){
                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
                }else {
                    Conn c = new Conn();
                    String q = "insert into bank values('"+pin+"', '"+date+"', 'Deposit', '"+amount+"')";
                    c.statement.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposit Successfully");
                    setVisible(false);
                    new Main_Class(pin);
                }
            }else if(e.getSource() == back){
                setVisible(false);
                new Main_Class(pin);
            }

        }catch(Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
