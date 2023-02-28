import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CLIENTI {
    JFrame frame = new JFrame();
    JPanel jp = new JPanel();
    JTable table = tableCreate();

    public CLIENTI(GUI gui) throws SQLException {
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        jp.setSize(1000, 300);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        frame.add(jp);

        logoC();


        JLabel lbl = new JLabel();
        //lbl.setText("Clienti : ");
        jp.add(lbl);


        jp.add(table.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(table, BorderLayout.CENTER);
        frame.add(jp);


        deleteCCreate();

        updateCCreate();

        insertCCreate(gui);

        searchevenCreate(gui);

        searchmenuCreate(gui);

        searchfacilitatiCreate(gui);

        meniupretCreate(gui);

        facminimCreate(gui);

        listaCCreate(gui);

        evenimnrCreate(gui);

        bonCreate(gui);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public JTable tableCreate() throws SQLException {
        String[] column = {"ID_client", "Nume", "Prenume", "Telefon", "Sex", "ID_meniu"};
        String[][] data = new String[8][8]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM Client";
        ResultSet res = stmt.executeQuery(sql);
        int i = 0;


        while (res.next()) {

            String ID_client = res.getString("ID_client");
            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String Telefon = res.getString("Telefon");
            String Sex = res.getString("Sex");
            String ID_meniu = res.getString("ID_meniu");
            data[i][0] = ID_client;
            data[i][1] = Nume;
            data[i][2] = Prenume;
            data[i][3] = Telefon;
            data[i][4] = Sex;
            data[i][5] = ID_meniu;
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

    public void insertCCreate(GUI gui) {

        JPanel jpinsc = new JPanel();
        jpinsc.setLayout(new BoxLayout(jpinsc, BoxLayout.X_AXIS));
        jpinsc.setSize(300, 100);

        JButton insert = new JButton("INSERT");
        insert.setBounds(350, 300, 300, 900);

        ActionListener ins = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //in.afisare();
                gui.insertclientShow();
                //frame.setVisible(false);
                gui.clientHide();

            }
        };

        insert.addActionListener(ins);
        jpinsc.add(insert);
        frame.add(jpinsc);
    }

    public void deleteCCreate() {
        JPanel jpdel = new JPanel();
        jpdel.setLayout(new BoxLayout(jpdel, BoxLayout.X_AXIS));
        jpdel.setSize(300, 100);

        JLabel delLabel = new JLabel("ID_client : ");
        delLabel.setBounds(100, 60, 100, 50);
        jpdel.add(delLabel);

        JTextField delText = new JTextField(20);
        delText.setHorizontalAlignment(SwingConstants.CENTER);
        delText.setFont(new Font("Calibri", Font.PLAIN, 18));
        delText.setBounds(120, 40, 220, 40);
        jpdel.add(delText);

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
                String sql2 = "DELETE FROM Client WHERE ID_client=" + id_del;

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

        jpdel.add(delete);
        frame.add(jpdel);
    }

    public void updateCCreate() {
        JPanel jpupd = new JPanel();
        jpupd.setLayout(new BoxLayout(jpupd, BoxLayout.X_AXIS));
        jpupd.setSize(300, 100);

        JLabel updateLabel = new JLabel("ID client de modificat : ");
        updateLabel.setBounds(100, 60, 100, 50);
        jpupd.add(updateLabel);

        JTextField updateText = new JTextField(20);
        updateText.setHorizontalAlignment(SwingConstants.CENTER);
        updateText.setFont(new Font("Calibri", Font.PLAIN, 18));
        updateText.setBounds(120, 40, 220, 40);
        jpupd.add(updateText);

        JLabel updateLabel2 = new JLabel("ID client nou : ");
        updateLabel2.setBounds(100, 60, 100, 50);
        jpupd.add(updateLabel2);

        JTextField updateText2 = new JTextField(20);
        updateText2.setHorizontalAlignment(SwingConstants.CENTER);
        updateText2.setFont(new Font("Calibri", Font.PLAIN, 18));
        updateText2.setBounds(120, 40, 220, 40);
        jpupd.add(updateText2);

        JButton update = new JButton("UPDATE");
        update.setBounds(20, 20, 20, 40);
        ActionListener up = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int id_up = Integer.parseInt(updateText.getText());
                int id_up2 = Integer.parseInt(updateText2.getText());
                System.out.println(id_up + " , " + id_up2);

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
                String sql3 = "UPDATE Client SET ID_client = " + id_up2 + "WHERE ID_client =" + id_up;
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

        jpupd.add(update);
        frame.add(jpupd);
    }

    public void bonCreate(GUI gui) {
        JPanel jpbon = new JPanel();
        jpbon.setLayout(new BoxLayout(jpbon, BoxLayout.X_AXIS));
        jpbon.setSize(300, 100);

        JButton client = new JButton("BON");
        client.setBounds(350, 300, 300, 900);

        ActionListener cl = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gui.bonShow();
                gui.clientHide();

            }
        };

        client.addActionListener(cl);
        jpbon.add(client);
        frame.add(jpbon);
    }

    public void tableupdatedCreate() throws SQLException {
        String[] column = {"ID_client", "Nume", "Prenume", "Telefon", "Sex", "ID_meniu"};
        String[][] data = new String[8][6]; // [rows][columns]

        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT * FROM Client";
        ResultSet res = stmt.executeQuery(sql);
        int i = 0;


        while (res.next()) {

            String ID_client = res.getString("ID_client");
            String Nume = res.getString("Nume");
            String Prenume = res.getString("Prenume");
            String Telefon = res.getString("Telefon");
            String Sex = res.getString("Sex");
            String ID_meniu = res.getString("ID_meniu");
            data[i][0] = ID_client;
            data[i][1] = Nume;
            data[i][2] = Prenume;
            data[i][3] = Telefon;
            data[i][4] = Sex;
            data[i][5] = ID_meniu;
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

    public void searchevenCreate(GUI gui) {
        JPanel jpsearchev = new JPanel();
        jpsearchev.setLayout(new BoxLayout(jpsearchev, BoxLayout.X_AXIS));
        jpsearchev.setSize(300, 100);

        JButton searchev = new JButton("SEARCH Eveniment");
        searchev.setBounds(350, 300, 300, 900);

        ActionListener ev = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.evenclientsShow();
                gui.clientHide();

            }
        };

        searchev.addActionListener(ev);
        jpsearchev.add(searchev);
        frame.add(jpsearchev);
    }

    public void searchmenuCreate(GUI gui) {
        JPanel jpsearchmenu = new JPanel();
        jpsearchmenu.setLayout(new BoxLayout(jpsearchmenu, BoxLayout.X_AXIS));
        jpsearchmenu.setSize(300, 100);

        JButton searchmenu = new JButton("SEARCH Menu");
        searchmenu.setBounds(350, 300, 300, 900);

        ActionListener m = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.menuclientsShow();
                gui.clientHide();

            }
        };

        searchmenu.addActionListener(m);
        jpsearchmenu.add(searchmenu);
        frame.add(jpsearchmenu);
    }

    public void searchfacilitatiCreate(GUI gui) {
        JPanel jpsearchfac = new JPanel();
        jpsearchfac.setLayout(new BoxLayout(jpsearchfac, BoxLayout.X_AXIS));
        jpsearchfac.setSize(300, 100);

        JButton searchfac = new JButton("SEARCH Facilitati");
        searchfac.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.facilitaticShow();
                gui.clientHide();

            }
        };

        searchfac.addActionListener(fa);
        jpsearchfac.add(searchfac);
        frame.add(jpsearchfac);
    }

    public void meniupretCreate(GUI gui) {
        JPanel jpmp = new JPanel();
        jpmp.setLayout(new BoxLayout(jpmp, BoxLayout.X_AXIS));
        jpmp.setSize(300, 100);

        JButton mp = new JButton("Meniuri Pret");
        mp.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.clientmeniupretShow();
                gui.clientHide();

            }
        };

        mp.addActionListener(fa);
        jpmp.add(mp);
        frame.add(jpmp);
    }

    public void facminimCreate(GUI gui) {
        JPanel jpfm = new JPanel();
        jpfm.setLayout(new BoxLayout(jpfm, BoxLayout.X_AXIS));
        jpfm.setSize(300, 100);

        JButton fm = new JButton("Facilitati Minime");
        fm.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.facilitatinrmShow();
                gui.clientHide();

            }
        };

        fm.addActionListener(fa);
        jpfm.add(fm);
        frame.add(jpfm);
    }

    public void listaCCreate(GUI gui) {
        JPanel jpfm = new JPanel();
        jpfm.setLayout(new BoxLayout(jpfm, BoxLayout.X_AXIS));
        jpfm.setSize(300, 100);

        JButton fm = new JButton("Lista Clienti Evenimente Comune");
        fm.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.evenimentcomunShow();
                gui.clientHide();

            }
        };

        fm.addActionListener(fa);
        jpfm.add(fm);
        frame.add(jpfm);
    }

    public void evenimnrCreate(GUI gui) {
        JPanel jpfm = new JPanel();
        jpfm.setLayout(new BoxLayout(jpfm, BoxLayout.X_AXIS));
        jpfm.setSize(300, 100);

        JButton fm = new JButton("Nr facilitati alese de clienti");
        fm.setBounds(350, 300, 300, 900);

        ActionListener fa = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.fevenimShow();
                gui.clientHide();

            }
        };

        fm.addActionListener(fa);
        jpfm.add(fm);
        frame.add(jpfm);
    }


}







