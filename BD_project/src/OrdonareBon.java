import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OrdonareBon {
    JFrame frame = new JFrame();
    JPanel jp = new JPanel();
    JTable table = tableCreate();

    public OrdonareBon(GUI gui) throws SQLException {
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        jp.setSize(1000, 300);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        frame.add(jp);

        logoC();

        JLabel lbl = new JLabel();
        lbl.setText("Lista clientilor ordonata descrescator in functie de data emiterii bonului: ");
        jp.add(lbl);


        jp.add(table.getTableHeader(), BorderLayout.PAGE_START);
        jp.add(table, BorderLayout.CENTER);
        frame.add(jp);


        backBCCreate(gui);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public JTable tableCreate() throws SQLException {
        String[] column = {"Nume Prenume Client", "Data","Ora"};
        String[][] data = new String[8][8]; // [rows][columns]


        Connection con;
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-LORENA0\\SQLEXPRESS;database=Ballroom;encrypt=true;trustServerCertificate=true", "sa", "ED308");
        Statement stmt = con.createStatement();

        String sql = "SELECT C.Nume+ ' ' +C.Prenume AS Client,B.Data,B.Ora FROM Client C INNER JOIN Bon B ON B.ID_client = C.ID_client ORDER BY (SELECT B.Data FROM Bon B WHERE B.ID_client = C.ID_client) DESC ";
        ResultSet res = stmt.executeQuery(sql);

        int i = 0;


        while (res.next()) {

            String Nume = res.getString("Client");
            String Data = res.getString("Data");
            String Ora = res.getString("Ora");
            data[i][0] = Nume;
            data[i][1] = Data;
            data[i][2] = Ora;
            i++;

        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        //JScrollPane pane = new JScrollPane(table);
        table.setRowHeight(table.getRowHeight() + 10);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);

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
                gui.ordonareHide();

            }
        };

        back.addActionListener(b);
        jpback.add(back);
        frame.add(jpback);
    }

}

