import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ListaClienti {
    JFrame frame = new JFrame();
    JPanel jp = new JPanel();

    JTextField id_client = idBCreate();

    JButton done = doneCreate(id_client, GUI.gui);
    JTable table = tableCreate();

    public ListaClienti(GUI gui) throws SQLException {
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        jp.setSize(1000, 300);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        frame.add(jp);

        logoC();

        JLabel lbl = new JLabel();
        lbl.setText("Lista clientilor ale caror bonuri au fost emise inaintea clientului cu ID-ul inserat: ");
        jp.add(lbl);


        jp.add(table.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(table, BorderLayout.CENTER);
        frame.add(jp);

        JButton all = allCreate(gui);

        backBCCreate(gui);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JTextField idBCreate() {
        JPanel jpid = new JPanel();
        jpid.setLayout(new BoxLayout(jpid, BoxLayout.X_AXIS));
        jpid.setSize(100, 50);

        JLabel idLabel = new JLabel("ID Client: ");
        idLabel.setBounds(170, 900, 220, 40);
        jpid.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setHorizontalAlignment(SwingConstants.CENTER);
        idText.setFont(new Font("Calibri", Font.PLAIN, 18));
        idText.setBounds(120, 100, 220, 40);
        jpid.add(idText);
        frame.add(jpid);
        return idText;
    }


    public JTable tableCreate() throws SQLException {
        String[] column = {"Nume Prenume", "Data", "Ora","ID_Client"};
        String[][] data = new String[8][8]; // [rows][columns]


        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT C.Nume+' '+ C.Prenume AS Client,B.Data,B.Ora,C.ID_client FROM Bon B INNER JOIN Client C ON B.ID_client = C.ID_client";
        ResultSet res = stmt.executeQuery(sql);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Client");
            String Data = res.getString("Data");
            String Ora= res.getString("Ora");
            String ID= res.getString("ID_client");
            data[i][0] = Nume;
            data[i][1] = Data;
            data[i][2] = Ora;
            data[i][3] = ID;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.setRowHeight(table.getRowHeight() + 10);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);

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

                gui.bonShow();
                gui.listaCHide();

            }
        };

        back.addActionListener(b);
        jpback.add(back);
        frame.add(jpback);
    }

    public JButton doneCreate(JTextField idclient, GUI gui) {
        JPanel jpdone = new JPanel();
        jpdone.setLayout(new BoxLayout(jpdone, BoxLayout.X_AXIS));
        jpdone.setSize(100, 50);

        JButton done = new JButton("DONE");
        done.setBounds(700, 40, 130, 40);

        ActionListener d = new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                try {
                    gui.lc.tableupdatedCreate(idclient);
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

        String[] column = {"Nume Prenume", "Data", "Ora","ID_Client"};
        String[][] data = new String[8][4]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql2 = "SELECT C.Nume+' '+ C.Prenume AS Client,B.Data,B.Ora,C.ID_client FROM Bon B INNER JOIN Client C ON B.ID_client = C.ID_client WHERE B.Data < (SELECT n.Data FROM Bon n WHERE n.ID_client = " + "(?)" + ") ORDER BY B.Data DESC;";
        PreparedStatement pstm = con.prepareStatement(sql2);
        pstm.setString(1, numec.getText());
        pstm.execute();

        ResultSet res = pstm.executeQuery();

        int i = 0;

        while (res.next()) {

            String Nume = res.getString("Client");
            String Data = res.getString("Data");
            String Ora= res.getString("Ora");
            String ID= res.getString("ID_client");
            data[i][0] = Nume;
            data[i][1] = Data;
            data[i][2] = Ora;
            data[i][3] = ID;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);
        table.setSize(980, 980);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);

    }

    public void tableallCreate() throws SQLException {

        String[] column = {"Nume Prenume", "Data", "Ora","ID_Client"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql2 = "SELECT C.Nume+' '+ C.Prenume AS Client,B.Data,B.Ora,C.ID_client FROM Bon B INNER JOIN Client C ON B.ID_client = C.ID_client";

        ResultSet res = stmt.executeQuery(sql2);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Client");
            String Data = res.getString("Data");
            String Ora= res.getString("Ora");
            String ID= res.getString("ID_client");
            data[i][0] = Nume;
            data[i][1] = Data;
            data[i][2] = Ora;
            data[i][3] = ID;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);
        table.setSize(980, 980);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);

    }


    public JButton allCreate(GUI gui) {
        JPanel jpall = new JPanel();
        jpall.setLayout(new BoxLayout(jpall, BoxLayout.X_AXIS));
        jpall.setSize(100, 50);

        JButton all = new JButton("Lista Tuturor Clientilor");
        all.setBounds(700, 40, 130, 40);

        ActionListener a = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    gui.lc.tableallCreate();
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

