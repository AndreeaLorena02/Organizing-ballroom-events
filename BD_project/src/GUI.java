
import com.sun.jmx.remote.util.OrderClassLoaders;

import java.sql.*;

public class GUI {
    static GUI gui;

    static {
        try {
            gui = new GUI();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static INSERARE in = new INSERARE(gui);
    static INSERTC inc = new INSERTC(gui);
    static CLIENTI c;

    static {
        try {
            c = new CLIENTI(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static BonuriClient bc;

    static {
        try {
            bc = new BonuriClient(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static EvenimenteC ec;

    static {
        try {
            ec = new EvenimenteC(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static MeniuClienti mc;

    static {
        try {
            mc = new MeniuClienti(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static FacilitatiClient fc;

    static {
        try {
            fc = new FacilitatiClient(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static ClientiMeniuPret cmp;

    static {
        try {
            cmp = new ClientiMeniuPret(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static FacilitatiNr fnrm;

    static {
        try {
            fnrm = new FacilitatiNr(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static BON bon;

    static {
        try {
            bon = new BON(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static OrdonareBon ordbon;

    static {
        try {
            ordbon = new OrdonareBon(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static ListaClienti lc;

    static {
        try {
            lc = new ListaClienti(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static EvenimentComun evc;

    static {
        try {
            evc = new EvenimentComun(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static FacilitatiEven fev;

    static {
        try {
            fev = new FacilitatiEven(gui);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static LOGIN login = new LOGIN(gui);

    public GUI() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {


        login.afisare();


    }

    public void bonShow() {
        bon.frame.setVisible(true);
    }

    public void bonHide() {
        bon.frame.setVisible(false);
    }

    public void loginHide() {
        login.frame.setVisible(false);
    }

    public void insertShow() {
        in.frame2.setVisible(true);
    }

    public void insertHide() {
        in.frame2.setVisible(false);
    }

    public void bonclientHide() {
        bc.frame.setVisible(false);
    }

    public void bonclientShow() {
        bc.frame.setVisible(true);
    }

    public void evenclientsHide() {
        ec.frame.setVisible(false);
    }

    public void evenclientsShow() {
        ec.frame.setVisible(true);
    }

    public void menuclientsHide() {
        mc.frame.setVisible(false);
    }

    public void menuclientsShow() {
        mc.frame.setVisible(true);
    }

    public void facilitaticHide() {
        fc.frame.setVisible(false);
    }

    public void facilitaticShow() {
        fc.frame.setVisible(true);
    }

    public void clientmeniupretHide() {
        cmp.frame.setVisible(false);
    }

    public void clientmeniupretShow() {
        cmp.frame.setVisible(true);
    }

    public void facilitatinrmHide() {
        fnrm.frame.setVisible(false);
    }

    public void facilitatinrmShow() {
        fnrm.frame.setVisible(true);
    }

    public void clientsShow() {
        c.frame.setVisible(true);
    }

    public void clientHide() {
        c.frame.setVisible(false);
    }

    public void insertclientHide() {
        inc.frame2.setVisible(false);
    }

    public void insertclientShow() {
        inc.frame2.setVisible(true);
    }

    public void ordonareHide() {
        ordbon.frame.setVisible(false);
    }

    public void ordonareShow() {
        ordbon.frame.setVisible(true);
    }

    public void listaCHide() {
        lc.frame.setVisible(false);
    }

    public void listaCShow() {
        lc.frame.setVisible(true);
    }

    public void evenimentcomunHide() {
        evc.frame.setVisible(false);
    }

    public void evenimentcomunShow() {
        evc.frame.setVisible(true);
    }

    public void fevenimHide() {
        fev.frame.setVisible(false);
    }

    public void fevenimShow() {
        fev.frame.setVisible(true);
    }


    public void refreshTable() throws SQLException {
        bon.tableupdatedCreate();
    }

}







