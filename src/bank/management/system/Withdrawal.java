package bank.management.system;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField textField;

    JButton withdraw, back;

    String pin;

    Withdrawal(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);

        JLabel l1 = new JLabel("Maximum Withdrawal is Rs.10,000");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(460,180,700,35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        JLabel l2 = new JLabel("Please Enter Your Amount");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(460,220,400,35);
        l2.setForeground(Color.WHITE);
        image.add(l2);

        textField = new JTextField();
        textField.setBackground(new Color(56, 107, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(textField);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(700,362,150,35);
        withdraw.setBackground(new Color(56, 107, 128));
        withdraw.setForeground(Color.WHITE);
        withdraw.addActionListener(this);
        image.add(withdraw);

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
        if(e.getSource() == withdraw){
            try{
                String amount = textField.getText();
                Date date = new Date();
                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                }else {
                    Conn c = new Conn();
                    String q = "select * from bank where pin = '"+pin+"'";
                    ResultSet resultSet = c.statement.executeQuery(q);
                    int balance = 0;
                    while (resultSet.next()){
                        if(resultSet.getString("type").equals("Deposit")){
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        }else{
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdraw', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    setVisible(false);
                    new Main_Class(pin);
                }

            }catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_Class(pin);
        }


    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}
