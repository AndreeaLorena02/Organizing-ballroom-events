import com.sun.corba.se.impl.orbutil.DenseIntMapImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EvenimenteC {
    JFrame frame = new JFrame();
    JPanel jp = new JPanel();

    JTextField nume_client = numeBCreate();

    JButton done = doneCreate(nume_client, GUI.gui);
    JTable table = tableCreate();

    public EvenimenteC(GUI gui) throws SQLException {
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        jp.setSize(1000, 300);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        frame.add(jp);

        logoC();

        JLabel lbl = new JLabel();
        lbl.setText("Evenimente Client: ");
        jp.add(lbl);


        jp.add(table.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(table, BorderLayout.CENTER);
        frame.add(jp);

        JButton all = allCreate(GUI.gui);

        backBCCreate(gui);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JTextField numeBCreate() {
        JPanel jpnume = new JPanel();
        jpnume.setLayout(new BoxLayout(jpnume, BoxLayout.X_AXIS));
        jpnume.setSize(100, 50);

        JLabel numeLabel = new JLabel("Nume Client: ");
        numeLabel.setBounds(170, 900, 220, 40);
        jpnume.add(numeLabel);

        JTextField numeText = new JTextField(20);
        numeText.setHorizontalAlignment(SwingConstants.CENTER);
        numeText.setFont(new Font("Calibri", Font.PLAIN, 18));
        numeText.setBounds(120, 100, 220, 40);
        jpnume.add(numeText);
        frame.add(jpnume);
        return numeText;
    }


    public JTable tableCreate() throws SQLException {
        String[] column = {"Nume", "Prenume", "Denumire Eveniment","Pret"};
        String[][] data = new String[8][8]; // [rows][columns]


        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT C.Nume,C.Prenume,E.Denumire,E.Pret FROM Client C INNER JOIN Eveniment E ON C.ID_client = E.ID_client";
        ResultSet res = stmt.executeQuery(sql);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String Denumire = res.getString("Denumire");
            String Pret = res.getString("Pret");
            data[i][0] = Nume;
            data[i][1] = Prenume;
            data[i][2] = Denumire;
            data[i][3] = Pret;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.setRowHeight(table.getRowHeight() + 10);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        con.close();
        return table;


    }

    public void afisare() {
        frame.setVisible(true);
    }

    public void logoC() {
        ImageIcon logoicon = new ImageIcon("logo.jpg");
        Image logo = logoicon.getImage();
        frame.setIconImage(logo);
        frame.getContentPane().setBackground(new Color(204, 229, 255));

    }

    public void insertTable(JTable tab) {
        jp.add(tab.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(tab, BorderLayout.CENTER);
    }

    public void backBCCreate(GUI gui) {
        JPanel jpback = new JPanel();
        jpback.setLayout(new BoxLayout(jpback, BoxLayout.X_AXIS));
        jpback.setSize(100, 50);

        JButton back = new JButton("Back");
        back.setBounds(700, 40, 130, 40);
        ActionListener b = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gui.clientsShow();
                gui.evenclientsHide();

            }
        };

        back.addActionListener(b);
        jpback.add(back);
        frame.add(jpback);
    }

    public JButton doneCreate(JTextField numec, GUI gui) {
        JPanel jpdone = new JPanel();
        jpdone.setLayout(new BoxLayout(jpdone, BoxLayout.X_AXIS));
        jpdone.setSize(100, 50);

        JButton done = new JButton("DONE");
        done.setBounds(700, 40, 130, 40);

        ActionListener d = new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                try {
                    gui.ec.tableupdatedCreate(numec);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };

        done.addActionListener(d);
        jpdone.add(done);
        frame.add(jpdone);
        return done;
    }

    public void tableupdatedCreate(JTextField numec) throws SQLException {
        String n2 = numec.getText();
        //System.out.println(n2);

        String[] column = {"Nume", "Prenume", "Denumire Eveniment", "Pret"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql2 = "SELECT C.Nume,C.Prenume,E.Denumire,E.Pret FROM Client C INNER JOIN Eveniment E ON C.ID_client = E.ID_client WHERE C.Nume = "+ "(?)" + "ORDER BY E.Pret ASC";
        PreparedStatement pstm = con.prepareStatement(sql2);
        pstm.setString(1, numec.getText());
        pstm.execute();

        ResultSet res = pstm.executeQuery();

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String Denumire = res.getString("Denumire");
            String Pret = res.getString("Pret");
            data[i][0] = Nume;
            data[i][1] = Prenume;
            data[i][2] = Denumire;
            data[i][3] = Pret;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);
        table.setSize(980, 980);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);

    }

    public void tableallCreate() throws SQLException {

        String[] column = {"Nume", "Prenume", "Denumire", "Pret"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql2 = "SELECT C.Nume,C.Prenume,E.Denumire,E.Pret FROM Client C INNER JOIN Eveniment E ON C.ID_client = E.ID_client";

        ResultSet res = stmt.executeQuery(sql2);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String Denumire = res.getString("Denumire");
            String Pret = res.getString("Pret");
            data[i][0] = Nume;
            data[i][1] = Prenume;
            data[i][2] = Denumire;
            data[i][3] = Pret;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);
        table.setSize(980, 980);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);

    }


    public JButton allCreate(GUI gui) {
        JPanel jpall = new JPanel();
        jpall.setLayout(new BoxLayout(jpall, BoxLayout.X_AXIS));
        jpall.setSize(100, 50);

        JButton all = new JButton("Toate Evenimentele Clintilor");
        all.setBounds(700, 40, 130, 40);

        ActionListener a = new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                try {
                    gui.ec.tableallCreate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };

        all.addActionListener(a);
        jpall.add(all);
        frame.add(jpall);
        return all;
    }

}
