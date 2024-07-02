package model;

import context.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MrEnd
 */
public class MagazinesDAO {

    public MagazinesDAO() {
    }

    public ArrayList<Magazine> getAll(String Id) {
        try {
            // Connect database
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "SELECT * FROM Magazine_YourID";
            if (!Id.isEmpty()) {
                sql = sql + " where ID='" + Id + "'";
            }
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Magazine> list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("ID");
                String title = rs.getString("Title");
                String publisher = rs.getString("Publisher");
                double price = rs.getDouble("Price");
                Magazine m = new Magazine(id, title, publisher, price);
                list.add(m);
            }
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@" + list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean newMagazine(Magazine m) throws Exception {
        String sql = "Insert into Magazine_YourID values(?,?,?,?)";
        try {
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, m.getID());
            pstmt.setString(2, m.getTitle());
            pstmt.setString(3, m.getPublisher());
            pstmt.setDouble(4, m.getPrice());
            return pstmt.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
}
