import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.*;

public class INSERTC {
    JFrame frame2 = new JFrame();
    JPanel jp2 = new JPanel();

    public INSERTC(GUI gui) {

        frame2.setSize(600, 400);
        frame2.setLayout(new FlowLayout());

        logoICreate();

        JLabel lbl2 = new JLabel();
        lbl2.setText("INSERT CLIENTI: ");
        jp2.add(lbl2);
        frame2.add(jp2);

        JTextField idcl2 = idclCreate();

        JTextField nume2 = numeCreate();

        JTextField prenume2 = prenumeCreate();

        JTextField tel2 = telCreate();
        JTextField s2 = sCreate();

        JTextField idm2 = idmCreate();

        doneCreate(idcl2, nume2, prenume2, tel2, s2, idm2, gui);

        backCCreate(gui);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void afisare() {
        frame2.setVisible(true);
    }

    public void logoICreate() {
        ImageIcon logoicon = new ImageIcon("logo.jpg");
        Image logo = logoicon.getImage();
        frame2.setIconImage(logo);
        frame2.getContentPane().setBackground(new Color(204, 229, 255));
    }

    public JTextField idclCreate() {
        JPanel jpidc = new JPanel();
        jpidc.setLayout(new BoxLayout(jpidc, BoxLayout.X_AXIS));
        jpidc.setSize(100, 50);

        JLabel idcLabel = new JLabel("ID_client : ");
        idcLabel.setBounds(100, 40, 220, 40);
        jpidc.add(idcLabel);

        JTextField idcText = new JTextField(20);
        idcText.setHorizontalAlignment(SwingConstants.CENTER);
        idcText.setFont(new Font("Calibri", Font.PLAIN, 18));
        idcText.setBounds(120, 40, 220, 40);
        jpidc.add(idcText);
        frame2.add(jpidc);
        return idcText;
    }

    public JTextField numeCreate() {
        JPanel jpnume = new JPanel();
        jpnume.setLayout(new BoxLayout(jpnume, BoxLayout.X_AXIS));
        jpnume.setSize(100, 50);

        JLabel numeLabel = new JLabel("Nume : ");
        numeLabel.setBounds(170, 900, 220, 40);
        jpnume.add(numeLabel);

        JTextField numeText = new JTextField(20);
        numeText.setHorizontalAlignment(SwingConstants.CENTER);
        numeText.setFont(new Font("Calibri", Font.PLAIN, 18));
        numeText.setBounds(120, 100, 220, 40);
        jpnume.add(numeText);
        frame2.add(jpnume);
        return numeText;
    }

    public JTextField prenumeCreate() {
        JPanel jppren = new JPanel();
        jppren.setLayout(new BoxLayout(jppren, BoxLayout.X_AXIS));
        jppren.setSize(100, 50);

        JLabel prenumeLabel = new JLabel("Prenume : ");
        prenumeLabel.setBounds(100, 60, 100, 50);
        jppren.add(prenumeLabel);

        JTextField prenumeText = new JTextField(20);
        prenumeText.setHorizontalAlignment(SwingConstants.CENTER);
        prenumeText.setFont(new Font("Calibri", Font.PLAIN, 18));
        prenumeText.setBounds(120, 40, 220, 40);
        jppren.add(prenumeText);
        frame2.add(jppren);
        return prenumeText;
    }

    public JTextField telCreate() {
        JPanel jpt = new JPanel();
        jpt.setLayout(new BoxLayout(jpt, BoxLayout.X_AXIS));
        jpt.setSize(100, 50);

        JLabel telLabel = new JLabel("Telefon : ");
        telLabel.setBounds(80, 70, 130, 40);
        jpt.add(telLabel);

        JTextField telText = new JTextField(20);
        telText.setHorizontalAlignment(SwingConstants.CENTER);
        telText.setFont(new Font("Calibri", Font.PLAIN, 18));
        telText.setBounds(120, 40, 220, 40);
        jpt.add(telText);
        frame2.add(jpt);
        return telText;
    }

    public JTextField sCreate() {
        JPanel jps = new JPanel();
        jps.setLayout(new BoxLayout(jps, BoxLayout.X_AXIS));
        jps.setSize(100, 50);

        JLabel sLabel = new JLabel("Sex : ");
        sLabel.setBounds(30, 40, 220, 40);
        jps.add(sLabel);

        JTextField sText = new JTextField(20);
        sText.setHorizontalAlignment(SwingConstants.CENTER);
        sText.setFont(new Font("Calibri", Font.PLAIN, 18));
        sText.setBounds(120, 40, 220, 40);
        jps.add(sText);
        frame2.add(jps);
        return sText;
    }

    public JTextField idmCreate() {
        JPanel jpidm = new JPanel();
        jpidm.setLayout(new BoxLayout(jpidm, BoxLayout.X_AXIS));
        jpidm.setSize(100, 50);

        JLabel idmLabel = new JLabel("ID_meniu : ");
        idmLabel.setBounds(30, 40, 220, 40);
        jpidm.add(idmLabel);

        JTextField idmText = new JTextField(20);
        idmText.setHorizontalAlignment(SwingConstants.CENTER);
        idmText.setFont(new Font("Calibri", Font.PLAIN, 18));
        idmText.setBounds(120, 40, 220, 40);
        jpidm.add(idmText);
        frame2.add(jpidm);

        return idmText;
    }

    public void doneCreate(JTextField idcl2, JTextField nume2, JTextField prenume2, JTextField tel2, JTextField s2, JTextField idm2, GUI gui) {
        JPanel jpdone = new JPanel();
        jpdone.setLayout(new BoxLayout(jpdone, BoxLayout.X_AXIS));
        jpdone.setSize(100, 50);

        JButton done = new JButton("DONE");
        done.setBounds(700, 40, 130, 40);

        ActionListener d = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int i1 = Integer.parseInt(idcl2.getText());
                String i2 = nume2.getText();
                String i3 = prenume2.getText();
                String i4 = tel2.getText();
                String i5 = s2.getText();
                int i6 = Integer.parseInt(idm2.getText());
                System.out.println(i1 + " , " + i2 + " , " + i3 + " , " + i4 + " , " + i5 + " , " + i6);

                Connection con;
                try {
                    con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Statement stmt = null;
                try {
                    stmt = con.createStatement();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String sql2 = "INSERT INTO Client(ID_client,Nume,Prenume,Telefon,Sex,ID_meniu) " + "VALUES (?,?,?,?,?,?)";
                try (PreparedStatement pstm = con.prepareStatement(sql2)) {
                    pstm.setString(1, idcl2.getText());
                    pstm.setString(2, nume2.getText());
                    pstm.setString(3, prenume2.getText());
                    pstm.setString(4, tel2.getText());
                    pstm.setString(5, s2.getText());
                    pstm.setString(6, idm2.getText());
                    pstm.execute();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    con.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    gui.c.tableupdatedCreate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                gui.insertclientHide();

                gui.clientsShow();

            }
        };

        done.addActionListener(d);
        jpdone.add(done);
        frame2.add(jpdone);
    }

    public void backCCreate(GUI gui) {
        JPanel jpback = new JPanel();
        jpback.setLayout(new BoxLayout(jpback, BoxLayout.X_AXIS));
        jpback.setSize(100, 50);

        JButton back = new JButton("Back");
        back.setBounds(700, 40, 130, 40);
        ActionListener b = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gui.clientsShow();
                gui.insertclientHide();

            }
        };

        back.addActionListener(b);
        jpback.add(back);
        frame2.add(jpback);
    }

}
