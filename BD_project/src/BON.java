import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BON {
    JFrame frame = new JFrame();
    JPanel jp = new JPanel();
    JTable table = tableCreate();

    public BON(GUI gui) throws SQLException {
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        jp.setSize(1000, 300);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        logoB();

        JLabel lbl = new JLabel();
        // lbl.setSize(50,50);
        // lbl.setText("BONURI : ");
        jp.add(lbl);

        insertTable(table);

        frame.add(jp);

        deleteBCreate();

        updateBCreate();

        insertBCreate(gui);

        searchbonCreate(gui);

        clientCreate(gui);

        dataemitereCreate(gui);

        listaclientiCreate(gui);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public JTable tableCreate() throws SQLException {
        String[] column = {"ID_bon", "Pret", "Data", "Ora", "Nr_persoane", "ID_client"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM Bon";
        ResultSet res = stmt.executeQuery(sql);
        int i = 0;


        while (res.next()) {

            String ID_bon = res.getString("ID_bon");
            String Pret = res.getString("Pret");
            String Data = res.getString("Data");
            String Ora = res.getString("Ora");
            String Nr_persoane = res.getString("Nr_persoane");
            String ID_client = res.getString("ID_client");
            data[i][0] = ID_bon + "";
            data[i][1] = Pret;
            data[i][2] = Data;
            data[i][3] = Ora;
            data[i][4] = Nr_persoane;
            data[i][5] = ID_client;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setSize(980, 980);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.setRowHeight(table.getRowHeight() + 10);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);

        //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        con.close();
        return table;


    }

    public void afisare() {
        frame.setVisible(true);
    }

    public void logoB() {
        ImageIcon logoicon = new ImageIcon("logo.jpg");
        Image logo = logoicon.getImage();
        frame.setIconImage(logo);
        frame.getContentPane().setBackground(new Color(204, 229, 255));

    }

    public void insertBCreate(GUI gui) {

        JPanel jpinsert = new JPanel();
        jpinsert.setLayout(new BoxLayout(jpinsert, BoxLayout.Y_AXIS));
        jpinsert.setSize(300, 100);

        JButton insert = new JButton("INSERT");
        insert.setBounds(20, 20, 20, 40);

        ActionListener ins = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //in.afisare();
                gui.insertShow();
                //frame.setVisible(false);
                gui.bonHide();

            }
        };

        insert.addActionListener(ins);
        jpinsert.add(insert);
        frame.add(jpinsert);
    }

    public void deleteBCreate() {

        JPanel jpdelete = new JPanel();
        jpdelete.setLayout(new BoxLayout(jpdelete, BoxLayout.X_AXIS));
        jpdelete.setSize(300, 100);


        JLabel delLabel = new JLabel("ID_bon : ");
        delLabel.setBounds(100, 60, 100, 50);
        jpdelete.add(delLabel);

        JTextField delText = new JTextField(20);
        delText.setHorizontalAlignment(SwingConstants.CENTER);
        delText.setFont(new Font("Calibri", Font.PLAIN, 18));
        delText.setBounds(100, 40, 100, 40);
        jpdelete.add(delText);

        JButton delete = new JButton("DELETE");
        delete.setBounds(20, 20, 20, 40);
        ActionListener del = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int id_del = Integer.parseInt(delText.getText());
                System.out.println(id_del);

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
                String sql2 = "DELETE FROM Bon WHERE ID_bon=" + id_del;

                try {
                    stmt.execute(sql2);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    con.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    tableupdatedCreate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };

        delete.addActionListener(del);

        jpdelete.add(delete);
        frame.add(jpdelete);
    }

    public void updateBCreate() {
        JPanel jpupdate = new JPanel();
        jpupdate.setLayout(new BoxLayout(jpupdate, BoxLayout.X_AXIS));
        jpupdate.setSize(300, 100);

        JLabel updateLabel = new JLabel(" ID_bon de modificat : ");
        updateLabel.setBounds(100, 60, 100, 50);
        jpupdate.add(updateLabel);

        JTextField updateText = new JTextField(20);
        updateText.setHorizontalAlignment(SwingConstants.CENTER);
        updateText.setFont(new Font("Calibri", Font.PLAIN, 18));
        updateText.setBounds(120, 40, 220, 40);
        jpupdate.add(updateText);

        JLabel updateLabel2 = new JLabel("   ID_bon nou : ");
        updateLabel2.setBounds(100, 60, 100, 50);
        jpupdate.add(updateLabel2);

        JTextField updateText2 = new JTextField(20);
        updateText2.setHorizontalAlignment(SwingConstants.CENTER);
        updateText2.setFont(new Font("Calibri", Font.PLAIN, 18));
        updateText2.setBounds(120, 40, 220, 40);
        jpupdate.add(updateText2);

        JButton update = new JButton("UPDATE");
        update.setBounds(20, 20, 20, 40);
        ActionListener up = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int id_up = Integer.parseInt(updateText.getText());
                int id_up2 = Integer.parseInt(updateText2.getText());
                //System.out.println(id_up + " , " + id_up2);

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
                String sql3 = "UPDATE Bon SET ID_bon = " + id_up2 + "WHERE ID_bon =" + id_up;
                try {
                    stmt.execute(sql3);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    con.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    tableupdatedCreate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        };

        update.addActionListener(up);

        jpupdate.add(update);
        frame.add(jpupdate);
    }

    public void clientCreate(GUI gui) {
        JPanel jpclient = new JPanel();
        jpclient.setLayout(new BoxLayout(jpclient, BoxLayout.X_AXIS));
        jpclient.setSize(300, 100);

        JButton client = new JButton("CLIENTI");
        client.setBounds(350, 300, 300, 900);

        ActionListener cl = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //c.afisare();
                gui.clientsShow();
                //frame.setVisible(false);
                gui.bonHide();

            }
        };

        client.addActionListener(cl);
        jpclient.add(client);
        frame.add(jpclient);
    }

    public void tableupdatedCreate() throws SQLException {
        String[] column = {"ID_bon", "Pret", "Data", "Ora", "Nr_persoane", "ID_client"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM Bon";
        ResultSet res = stmt.executeQuery(sql);
        int i = 0;


        while (res.next()) {

            String ID_bon = res.getString("ID_bon");
            String Pret = res.getString("Pret");
            String Data = res.getString("Data");
            String Ora = res.getString("Ora");
            String Nr_persoane = res.getString("Nr_persoane");
            String ID_client = res.getString("ID_client");
            data[i][0] = ID_bon + "";
            data[i][1] = Pret;
            data[i][2] = Data;
            data[i][3] = Ora;
            data[i][4] = Nr_persoane;
            data[i][5] = ID_client;
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

    public void insertTable(JTable tab) {
        jp.add(tab.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(tab, BorderLayout.CENTER);
    }

    public void searchbonCreate(GUI gui) {
        JPanel jpsearch = new JPanel();
        jpsearch.setLayout(new BoxLayout(jpsearch, BoxLayout.X_AXIS));
        jpsearch.setSize(300, 100);

        JButton search = new JButton("SEARCH BON");
        search.setBounds(350, 300, 300, 900);

        ActionListener cl = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.bonclientShow();
                gui.bonHide();

            }
        };

        search.addActionListener(cl);
        jpsearch.add(search);
        frame.add(jpsearch);
    }

    public void dataemitereCreate(GUI gui) {
        JPanel jpfm = new JPanel();
        jpfm.setLayout(new BoxLayout(jpfm, BoxLayout.X_AXIS));
        jpfm.setSize(300, 100);

        JButton fm = new JButton("Ordonare Clienti");
        fm.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.ordonareShow();
                gui.bonHide();

            }
        };

        fm.addActionListener(fa);
        jpfm.add(fm);
        frame.add(jpfm);
    }

    public void listaclientiCreate(GUI gui) {
        JPanel jpfm = new JPanel();
        jpfm.setLayout(new BoxLayout(jpfm, BoxLayout.X_AXIS));
        jpfm.setSize(300, 100);

        JButton fm = new JButton("Lista Clienti Bonuri");
        fm.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.listaCShow();
                gui.bonHide();

            }
        };

        fm.addActionListener(fa);
        jpfm.add(fm);
        frame.add(jpfm);
    }

}







