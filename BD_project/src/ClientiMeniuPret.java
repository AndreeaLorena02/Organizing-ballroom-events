import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ClientiMeniuPret {
    JFrame frame = new JFrame();
    JPanel jp = new JPanel();

    JTextField nr_meniuri = nrmeniuriBCreate();

    JTextField pret_meniuri = pretmeniuriBCreate();

    JButton done = doneCreate(nr_meniuri, pret_meniuri, GUI.gui);
    JTable table = tableCreate();

    public ClientiMeniuPret(GUI gui) throws SQLException {
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        jp.setSize(1000, 300);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        frame.add(jp);

        logoC();

        JLabel lbl = new JLabel();
        lbl.setText("MENIURI CU NUMAR SI PRET INDICATE : ");
        jp.add(lbl);


        jp.add(table.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(table, BorderLayout.CENTER);
        frame.add(jp);

        JButton all = allCreate(gui);

        backBCCreate(gui);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JTextField nrmeniuriBCreate() {
        JPanel jpnr = new JPanel();
        jpnr.setLayout(new BoxLayout(jpnr, BoxLayout.X_AXIS));
        jpnr.setSize(100, 50);

        JLabel nrLabel = new JLabel("Nr Minim Meniuri: ");
        nrLabel.setBounds(170, 900, 220, 40);
        jpnr.add(nrLabel);

        JTextField nrText = new JTextField(20);
        nrText.setHorizontalAlignment(SwingConstants.CENTER);
        nrText.setFont(new Font("Calibri", Font.PLAIN, 18));
        nrText.setBounds(120, 100, 220, 40);
        jpnr.add(nrText);
        frame.add(jpnr);
        return nrText;
    }

    public JTextField pretmeniuriBCreate() {
        JPanel jppret = new JPanel();
        jppret.setLayout(new BoxLayout(jppret, BoxLayout.X_AXIS));
        jppret.setSize(100, 50);

        JLabel pretLabel = new JLabel("Pret Minim Meniuri: ");
        pretLabel.setBounds(170, 900, 220, 40);
        jppret.add(pretLabel);

        JTextField pretText = new JTextField(20);
        pretText.setHorizontalAlignment(SwingConstants.CENTER);
        pretText.setFont(new Font("Calibri", Font.PLAIN, 18));
        pretText.setBounds(120, 100, 220, 40);
        jppret.add(pretText);
        frame.add(jppret);
        return pretText;
    }


    public JTable tableCreate() throws SQLException {
        String[] column = {"Nume", "Prenume", "Descriere Preparat", "Tip Meniu", "Nr Meniuri", "Meniu Pret"};
        String[][] data = new String[8][8]; // [rows][columns]


        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT C.Nume,C.Prenume,M.Descriere_Preparat,M.Tip,ME.NrMeniuri,M.Pret FROM Client C INNER JOIN Legatura_MeniuEveniment ME ON (C.ID_meniu=ME.ID_meniu) INNER JOIN Meniu M ON (M.ID_meniu=ME.ID_meniu)";
        ResultSet res = stmt.executeQuery(sql);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String DP = res.getString("Descriere_Preparat");
            String Tip = res.getString("Tip");
            String NrM = res.getString("NrMeniuri");
            String Pret = res.getString("Pret");
            data[i][0] = Nume;
            data[i][1] = Prenume;
            data[i][2] = DP;
            data[i][3] = Tip;
            data[i][4] = NrM;
            data[i][5] = Pret;
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
                gui.clientmeniupretHide();

            }
        };

        back.addActionListener(b);
        jpback.add(back);
        frame.add(jpback);
    }

    public JButton doneCreate(JTextField nrm, JTextField pretm, GUI gui) {
        JPanel jpdone = new JPanel();
        jpdone.setLayout(new BoxLayout(jpdone, BoxLayout.X_AXIS));
        jpdone.setSize(100, 50);

        JButton done = new JButton("DONE");
        done.setBounds(700, 40, 130, 40);

        ActionListener d = new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                try {
                    gui.cmp.tableupdatedCreate(nrm, pretm);
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

    public void tableupdatedCreate(JTextField nrm, JTextField pretm) throws SQLException {
        String n2 = nrm.getText();
        String n3 = pretm.getText();
        //System.out.println(n2);

        String[] column = {"Nume", "Prenume", "Descriere Preparat", "Tip Meniu", "Nr Meniuri", "Meniu Pret"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql2 = "SELECT C.Nume,C.Prenume,M.Descriere_Preparat,M.Tip,ME.NrMeniuri,M.Pret FROM Client C INNER JOIN Legatura_MeniuEveniment ME ON (C.ID_meniu=ME.ID_meniu) INNER JOIN Meniu M ON (M.ID_meniu=ME.ID_meniu) WHERE M.Pret >=   " + "(?)" + " AND ME.NrMeniuri >= " + "(?)";
        PreparedStatement pstm = con.prepareStatement(sql2);
        pstm.setString(1, pretm.getText());
        pstm.setString(2, nrm.getText());
        pstm.execute();

        ResultSet res = pstm.executeQuery();

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String DP = res.getString("Descriere_Preparat");
            String Tip = res.getString("Tip");
            String NrM = res.getString("NrMeniuri");
            String Pret = res.getString("Pret");
            data[i][0] = Nume;
            data[i][1] = Prenume;
            data[i][2] = DP;
            data[i][3] = Tip;
            data[i][4] = NrM;
            data[i][5] = Pret;
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

        String[] column = {"Nume", "Prenume", "Descriere Preparat", "Tip Meniu", "Nr Meniuri", "Meniu Pret"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql2 = "SELECT C.Nume,C.Prenume,M.Descriere_Preparat,M.Tip,ME.NrMeniuri,M.Pret FROM Client C INNER JOIN Legatura_MeniuEveniment ME ON (C.ID_meniu=ME.ID_meniu) INNER JOIN Meniu M ON (M.ID_meniu=ME.ID_meniu) ";

        ResultSet res = stmt.executeQuery(sql2);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String DP = res.getString("Descriere_Preparat");
            String Tip = res.getString("Tip");
            String NrM = res.getString("NrMeniuri");
            String Pret = res.getString("Pret");
            data[i][0] = Nume;
            data[i][1] = Prenume;
            data[i][2] = DP;
            data[i][3] = Tip;
            data[i][4] = NrM;
            data[i][5] = Pret;
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

        JButton all = new JButton("TOATE MENIURILE");
        all.setBounds(700, 40, 130, 40);

        ActionListener a = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    gui.cmp.tableallCreate();
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


