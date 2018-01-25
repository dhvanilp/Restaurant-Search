import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class RestaurantDao {
    public static int save(String name, String address, int pin, String category) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO restaurants(name,address,pincode,category) VALUES(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, pin);
            ps.setString(4, category);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int rate(int rid, int stars) {
        int status = 0;
        float rating = 0;
        int no_of_ratings = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rating,no_of_ratings FROM restaurants WHERE r_id=?");
            ps.setInt(1, rid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rating = rs.getFloat("rating");
                no_of_ratings = rs.getInt("no_of_ratings");
            }
            PreparedStatement ps2 = con.prepareStatement("UPDATE restaurants SET rating=?,no_of_ratings=? WHERE r_id=?");
            rating = (rating * no_of_ratings + stars) / (no_of_ratings + 1);
            ps2.setFloat(1, rating);
            ps2.setInt(2, no_of_ratings + 1);
            ps2.setInt(3, rid);
            status = ps2.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static String[][][] view(String category) {
        ResultSet rs = null;
        String data[][][] = null;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants WHERE category=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, category);
            rs = ps.executeQuery();
            data = makeArray(rs);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public static String[][][] view(int pin) {
        ResultSet rs = null;
        String data[][][] = null;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants WHERE pincode=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, pin);
            rs = ps.executeQuery();
            data = makeArray(rs);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public static String[][][] view() {
        ResultSet rs = null;
        String data[][][] = null;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            data = makeArray(rs);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public static String[][][] makeArray(ResultSet rs) {
        String data[][] = null;
        String column[] = null;
        String d[][][] = null;
        try {
            Connection con = DB.getConnection();
//            ResultSet rs = RestaurantDao.view();

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            column = new String[cols];
            for (int i = 1; i <= cols; i++) {
                column[i - 1] = rsmd.getColumnName(i);
            }

            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();

            data = new String[rows][cols];
            int count = 0;
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    data[count][i - 1] = rs.getString(i);
                }
                count++;
            }
            String c[][] = new String[1][cols];
            c[0] = column;
            d = new String[2][rows][cols];
            d[0] = c;
            d[1] = data;

        } catch (Exception e) {
            System.out.println(e);
        }
        return d;
    }
}
