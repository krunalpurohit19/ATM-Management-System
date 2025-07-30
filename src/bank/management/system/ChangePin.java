package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePin extends JFrame implements ActionListener {

    JPasswordField p1, p2;

    JButton change, back;

    String pin;

    ChangePin(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);

        JLabel l1 = new JLabel("Change Your PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(430,180,400,35);
        l1.setForeground(Color.WHITE);
        image.add(l1);

        JLabel l2 = new JLabel("Enter New PIN");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(430,220,150,35);
        l2.setForeground(Color.WHITE);
        image.add(l2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(56, 107, 128));
        p1.setFont(new Font("Raleway", Font.BOLD, 16));
        p1.setBounds(600,220,180,25);
        p1.setForeground(Color.WHITE);
        image.add(p1);

        JLabel l3 = new JLabel("Re-Enter New PIN");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setBounds(430,250,150,35);
        l3.setForeground(Color.WHITE);
        image.add(l3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(56, 107, 128));
        p2.setFont(new Font("Raleway", Font.BOLD, 16));
        p2.setBounds(600,250,180,25);
        p2.setForeground(Color.WHITE);
        image.add(p2);


        change = new JButton("CHANGE");
        change.setBounds(700,362,150,35);
        change.setBackground(new Color(56, 107, 128));
        change.setForeground(Color.WHITE);
        change.addActionListener(this);
        image.add(change);

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
            String pin1 = p1.getText();
            String pin2 = p2.getText();
            if(!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null, "PIN doesn't match");
                return;
            }
            if(e.getSource() == change){
                if(p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Enter New PIN");
                    return;
                }
                if(p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Re-Enter New PIN");
                    return;
                }
                Conn c = new Conn();
                String q1 = "update bank set pin = '"+pin1+"' where pin = '"+pin+"'";
                String q2 = "update login set pin = '"+pin1+"' where pin = '"+pin+"'";
                String q3 = "update signupthree set pin = '"+pin1+"' where pin = '"+pin+"'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully");
                setVisible(false);
                new Main_Class(pin);
            } else if (e.getSource() == back) {
                new Main_Class(pin);
                setVisible(false);
            }

        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ChangePin("");
    }
}
