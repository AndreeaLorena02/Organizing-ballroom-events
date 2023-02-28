import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.*;

public class INSERARE {
    JFrame frame2 = new JFrame();
    JPanel jp2 = new JPanel();

    public INSERARE(GUI gui) {

        frame2.setSize(600, 400);
        frame2.setLayout(new FlowLayout());

        jp2.setSize(1000, 300);
        logoICreate();

        JLabel lbl2 = new JLabel();
        lbl2.setText("INSERT BON : ");
        jp2.add(lbl2);
        frame2.add(jp2);

        JTextField idb2 = idCreate();

        JTextField pret2 = pretCreate();

        JTextField data2 = dataCreate();

        JTextField ora2 = oraCreate();
        JTextField nrp2 = nrpCreate();

        JTextField idc2 = idcCreate();

        doneCreate(idb2, pret2, data2, ora2, nrp2, idc2, gui);

        backBCreate(gui);

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

    public JTextField idCreate() {
        JPanel jpid = new JPanel();
        jpid.setLayout(new BoxLayout(jpid, BoxLayout.X_AXIS));
        jpid.setSize(100, 50);

        JLabel idbLabel = new JLabel("ID_bon : ");
        idbLabel.setBounds(100, 40, 50, 30);
        jpid.add(idbLabel);

        JTextField idbText = new JTextField(20);
        idbText.setHorizontalAlignment(SwingConstants.CENTER);
        idbText.setFont(new Font("Calibri", Font.PLAIN, 18));
        idbText.setBounds(100, 40, 50, 30);
        jpid.add(idbText);
        frame2.add(jpid);
        return idbText;
    }

    public JTextField pretCreate() {
        JPanel jppret = new JPanel();
        jppret.setLayout(new BoxLayout(jppret, BoxLayout.X_AXIS));
        jppret.setSize(100, 50);

        JLabel pretLabel = new JLabel("Pret : ");
        pretLabel.setBounds(170, 900, 220, 40);
        jppret.add(pretLabel);

        JTextField pretText = new JTextField(20);
        pretText.setHorizontalAlignment(SwingConstants.CENTER);
        pretText.setFont(new Font("Calibri", Font.PLAIN, 18));
        pretText.setBounds(120, 100, 220, 40);
        jppret.add(pretText);
        frame2.add(jppret);
        return pretText;
    }

    public JTextField dataCreate() {
        JPanel jpdata = new JPanel();
        jpdata.setLayout(new BoxLayout(jpdata, BoxLayout.X_AXIS));
        jpdata.setSize(100, 50);

        JLabel dataLabel = new JLabel("Data : ");
        dataLabel.setBounds(100, 60, 100, 50);
        jpdata.add(dataLabel);

        JTextField dataText = new JTextField(20);
        dataText.setHorizontalAlignment(SwingConstants.CENTER);
        dataText.setFont(new Font("Calibri", Font.PLAIN, 18));
        dataText.setBounds(120, 40, 220, 40);
        jpdata.add(dataText);
        frame2.add(jpdata);
        return dataText;
    }

    public JTextField oraCreate() {
        JPanel jpora = new JPanel();
        jpora.setLayout(new BoxLayout(jpora, BoxLayout.X_AXIS));
        jpora.setSize(100, 50);

        JLabel oraLabel = new JLabel("Ora : ");
        oraLabel.setBounds(80, 70, 130, 40);
        jpora.add(oraLabel);

        JTextField oraText = new JTextField(20);
        oraText.setHorizontalAlignment(SwingConstants.CENTER);
        oraText.setFont(new Font("Calibri", Font.PLAIN, 18));
        oraText.setBounds(120, 40, 220, 40);
        jpora.add(oraText);
        frame2.add(jpora);
        return oraText;
    }

    public JTextField nrpCreate() {
        JPanel jpnrp = new JPanel();
        jpnrp.setLayout(new BoxLayout(jpnrp, BoxLayout.X_AXIS));
        jpnrp.setSize(100, 50);

        JLabel nrpLabel = new JLabel("Nr_Pers : ");
        nrpLabel.setBounds(30, 40, 220, 40);
        jpnrp.add(nrpLabel);

        JTextField nrpText = new JTextField(20);
        nrpText.setHorizontalAlignment(SwingConstants.CENTER);
        nrpText.setFont(new Font("Calibri", Font.PLAIN, 18));
        nrpText.setBounds(120, 40, 220, 40);
        jpnrp.add(nrpText);
        frame2.add(jpnrp);
        return nrpText;
    }

    public JTextField idcCreate() {
        JPanel jpidc = new JPanel();
        jpidc.setLayout(new BoxLayout(jpidc, BoxLayout.X_AXIS));
        jpidc.setSize(100, 50);

        JLabel idcLabel = new JLabel("ID_client : ");
        idcLabel.setBounds(30, 40, 220, 40);
        jpidc.add(idcLabel);

        JTextField idcText = new JTextField(20);
        idcText.setHorizontalAlignment(SwingConstants.CENTER);
        idcText.setFont(new Font("Calibri", Font.PLAIN, 18));
        idcText.setBounds(120, 40, 220, 40);
        jpidc.add(idcText);
        frame2.add(jpidc);

        return idcText;
    }

    public void doneCreate(JTextField idb2, JTextField pret2, JTextField data2, JTextField ora2, JTextField nrp2, JTextField idc2, GUI gui) {
        JPanel jpdone = new JPanel();
        jpdone.setLayout(new BoxLayout(jpdone, BoxLayout.X_AXIS));
        jpdone.setSize(100, 50);

        JButton done = new JButton("DONE");
        done.setBounds(700, 40, 130, 40);

        ActionListener d = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int i1 = Integer.parseInt(idb2.getText());
                int i2 = Integer.parseInt(pret2.getText());
                String i3 = data2.getText();
                String i4 = ora2.getText();
                int i5 = Integer.parseInt(nrp2.getText());
                int i6 = Integer.parseInt(idc2.getText());
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
                String sql2 = "INSERT INTO Bon(ID_bon,Pret,Data,Ora,Nr_persoane,ID_client) " + "VALUES (?,?,?,?,?,?)";
                try (PreparedStatement pstm = con.prepareStatement(sql2)) {
                    pstm.setString(1, idb2.getText());
                    pstm.setString(2, pret2.getText());
                    pstm.setString(3, data2.getText());
                    pstm.setString(4, ora2.getText());
                    pstm.setString(5, nrp2.getText());
                    pstm.setString(6, idc2.getText());
                    pstm.execute();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    con.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                gui.insertHide();
                try {
                    gui.bon.tableupdatedCreate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                gui.bonShow();
            }
        };

        done.addActionListener(d);
        jpdone.add(done);
        frame2.add(jpdone);
    }

    public void backBCreate(GUI gui) {
        JPanel jpback = new JPanel();
        jpback.setLayout(new BoxLayout(jpback, BoxLayout.X_AXIS));
        jpback.setSize(100, 50);

        JButton back = new JButton("Back");
        back.setBounds(700, 40, 130, 40);
        ActionListener b = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gui.bonShow();
                gui.insertHide();

            }
        };

        back.addActionListener(b);
        jpback.add(back);
        frame2.add(jpback);
    }

}
