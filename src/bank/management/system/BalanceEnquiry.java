package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pin;

    JLabel l2;

    JButton back;

    BalanceEnquiry(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);

        JLabel l1 = new JLabel("Your Current Balance is Rs ");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(430,180,700,35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        l2 = new JLabel();
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(430,220,400,35);
        l2.setForeground(Color.WHITE);
        image.add(l2);

        back = new JButton("BACK");
        back.setBounds(700,406,150,35);
        back.setBackground(new Color(56, 107, 128));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        image.add(back);

        int balance = 0;
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                if(resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else{
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        l2.setText(""+balance);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
