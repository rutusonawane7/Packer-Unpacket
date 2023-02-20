import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.*;



class MarvellousLogin extends Template implements Runnable
{
    JButton SUBMIT;
    JLabel label1 , label2 ,label3 ,TopLabel;
    final JTextField text1,text2;
    private static int attempt =3;
    MarvellousLogin()
    {
        TopLabel =new JLabel();
        TopLabel.setHorizontalAlignment (SwingConstants.CENTER);
        TopLabel.setText("Marvellous Packer Unpacker :Login");
        TopLabel.setForeground(Color.ORANGE);

        Dimension topsize =TopLabel.getPreferredSize();
        TopLabel.setBounds(50,40,topsize.width,topsize.height);
        _header.add(TopLabel);
        
        label1 = new JLabel();
        label1.setText("Username :");
        label1.setForeground(Color.white);
        
        Dimension size = label1.getPreferredSize();

        label1.setBounds(50,135,size.width,size.height);
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        text1 = new  JTextField(15);
        Dimension tsize= text1.getPreferredSize();
        text1.setBounds(200,135,tsize.width,tsize.height);
        
        text1.setToolTipText("Enter Username:");
        
        label2= new JLabel();
        label2.setText("Password:");
        label2.setBounds(50,175,size.width,size.height);
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        
        text2 = new JPasswordField(15);
        text2.setBounds(200,175,tsize.width,tsize.height);

        text2.setToolTipText("Enter Password");

        text2.addFocusListener(new FocusListener()
        {
            public void focusGained(FocusEvent e)
            {

            }
            public void focusLost(FocusEvent e)
            {
                label3.setText("");
            }
        });
        SUBMIT = new JButton ("SUBMIT");
        SUBMIT.setHorizontalAlignment(SwingConstants.CENTER);

        Dimension ssize = SUBMIT.getPreferredSize();

        SUBMIT.setBounds(50,220,ssize.width,ssize.height);
        label3 =new JLabel();
        label3.setText(" ");
        Dimension L3Size = label3.getPreferredSize();
        label3.setForeground(Color.RED);
        label3.setBounds(50,250,L3Size.width,L3Size.height);

        Thread t = new  Thread(this);
        t.start();
        _content.add(label1);
        _content.add(text1);
        
        _content.add(label2);
        _content.add(text2);

        _content.add(label3);
        _content.add(SUBMIT);

        pack();
        validate();

        ClockHome();
        setVisible(true);
        this.setSize(500,500);
        this.setResizable(false);
        setLocationRelativeTo(null);
        SUBMIT.addActionListener(this);
    }

    public boolean validate(String Username ,String Password)
    {
        if((Username.length()<8) || (Password.length()<8))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String value1 = text1.getText();
        String value2 = text2.getText();

        if( ae.getSource() == exit )
        {
            this.setVisible(false);
            System.exit(0);
        }

        if( ae.getSource() ==  minimize)
        {
            this.setState(Frame.ICONIFIED);
        }

        if(ae.getSource() == SUBMIT)
        {
            if(validate(value1,value2)==false)
            {
                text1.setText("");
                text2.setText("");
            JOptionPane.showMessageDialog(this,"Short Username","Marvellous packer unpacker",JOptionPane.ERROR_MESSAGE);
            }

            if(value1.equals("MarvellousAdmin") &&  value2.equals("MarvellousAdmin"))
            {
                NextPage page = new NextPage(value1);
                this.setVisible(false);
                page.pack();
                page.setVisible(true);
                page.setSize(500,500);
            }
            else
            {
                attempt --;
                if(attempt == 0)
                {
                    JOptionPane.showMessageDialog(this, "Number  of Attempts finished","Marvellous Packer Unpacker",JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    System.exit(0);
                }
                JOptionPane.showMessageDialog(this,"Incorrect Login or Password","Error",JOptionPane.ERROR_MESSAGE);
            }
        }   
    }
    public void run()
    {
        for(;;)
        {
            if(Toolkit.getDefaultToolkit().getLockingKeyState((KeyEvent.VK_CAPS_LOCK)))
            {
                text2.setToolTipText("Warning : CAPS LOCK is on");
            }
            else
            {
                text2.setToolTipText("");
            }
            if((text2.getText()).length()<8)
            {
                label3.setText("Weak Password");
            }
            else
            {
                label3.setText("");
            }
        }
    }
}

class MarvellousMain
{
    public static void main(String arg[])
    {
        try
        {
            MarvellousLogin frame = new MarvellousLogin();
            frame.setVisible(true);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}