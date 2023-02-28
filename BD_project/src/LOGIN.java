import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LOGIN {
    private static JTextField userText;

    private static JPasswordField passwordText;
    private static JLabel success;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public LOGIN(GUI gui) {


        frame.setSize(650, 500);
        frame.add(panel);

        logo_background();

        userCreate();

        passwordCreate();

        loginCreate(gui);

        resetCreate();

        success = new JLabel("");
        success.setBounds(20, 220, 600, 50);
        panel.add(success);

        //Tell the program to stop when the X button is selected
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void afisare() {
        frame.setVisible(true);
    }

    public void logo_background() {
        ImageIcon logoicon = new ImageIcon("logo.jpg");
        Image logo = logoicon.getImage();
        frame.setIconImage(logo);

        panel.setBackground(new Color(204, 229, 255));
        panel.setLayout(null);
    }

    public void userCreate() {
        JLabel userLabel = new JLabel("Username : ");

        userLabel.setBounds(30, 40, 220, 40);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setHorizontalAlignment(SwingConstants.CENTER);
        userText.setFont(new Font("Calibri", Font.PLAIN, 18));
        userText.setBounds(120, 40, 220, 40);
        panel.add(userText);

        JButton clear1 = new JButton("Clear Username");
        clear1.setBounds(350, 40, 130, 40);
        clear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                if (e1.getSource().equals(clear1)) {
                    userText.setText("");
                }

            }
        });
        panel.add(clear1);
    }

    public void passwordCreate() {
        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(30, 100, 1600, 40);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setHorizontalAlignment(SwingConstants.CENTER);
        passwordText.setFont(new Font("Calibri", Font.PLAIN, 18));
        passwordText.setBounds(120, 100, 220, 40);
        panel.add(passwordText);

        JButton clear2 = new JButton("Clear Password");
        clear2.setBounds(350, 100, 130, 40);
        clear2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                if (e2.getSource().equals(clear2)) {
                    passwordText.setText("");
                }

            }
        });
        panel.add(clear2);
    }

    public void loginCreate(GUI gui) {
        JButton button = new JButton("Login");
        button.setBounds(120, 180, 180, 50);
        ActionListener Login = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //System.out.println("Button clicked");
                String user = userText.getText();
                String password = passwordText.getText();
                //System.out.println(user + " , " + password);

                if (user.equals("l") && password.equals("l")) {
                    success.setText("Login succeessful!");
                    //bon.afisare();
                    gui.bonShow();
                    //frame.setVisible(false);
                    gui.loginHide();


                } else {
                    success.setText("Parola sau username gresit!");
                }
            }

        };

        button.addActionListener(Login);
        panel.add(button);
    }


    public void resetCreate() {
        JButton reset = new JButton("Reset");
        reset.setBounds(300, 180, 180, 50);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3) {

                if (e3.getSource().equals(reset)) {
                    userText.setText("");
                    passwordText.setText("");
                }
            }
        });
        panel.add(reset);
    }


}



