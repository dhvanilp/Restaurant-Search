import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

interface UserDaoInterface {
    boolean login(String name, String password);
    boolean register(String name, String password, String email, String address, String contact);
}

public class UserDao implements UserDaoInterface{

    public static int save(String name, String password, String email, String address, String contact) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name,password,email,address,contact) VALUES(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, contact);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public boolean login(String name, String password){
        boolean loggedIn = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants WHERE category=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, name);
            ps.execute();
            loggedIn = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return loggedIn;
    }

    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE name=? AND password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public boolean register(String name, String password, String email, String address, String contact){
        boolean registered = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.execute();
            registered = true;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return registered;
    }

}
