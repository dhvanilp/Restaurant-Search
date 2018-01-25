import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Restaurant extends JFrame {
    static Restaurant frame;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame= new Restaurant();
                    frame.setVisible(true);
                    Connection con=DB.getConnection();
                    PreparedStatement ps = con.prepareStatement("SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\";");
                    ps.execute();
                    ps = con.prepareStatement("SET time_zone = \"+00:00\";");
                    ps.execute();
                    ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS `users` (\n" +
                            "  `u_id` INT(5) NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(100) NOT NULL,\n" +
                            "  `password` VARCHAR(100) NOT NULL,\n" +
                            "  `email` VARCHAR(100) NOT NULL,\n" +
                            "  `address` VARCHAR(200) NOT NULL,\n" +
                            "  `contact` VARCHAR(20) NOT NULL,\n" +
                            "  PRIMARY KEY (`u_id`)\n" +
                            ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
                    ps.execute();
                    ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS `restaurants` (\n" +
                            "  `r_id` INT(10) NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(100) NOT NULL,\n" +
                            "  `address` VARCHAR(100) NOT NULL,\n" +
                            "  `pincode` INT(6) NOT NULL,\n" +
                            "  `category` VARCHAR(50) NOT NULL,\n" +
                            "  `rating` FLOAT NOT NULL DEFAULT 0,\n" +
                            "  `no_of_ratings` INT(10) NOT NULL DEFAULT 0,\n" +
                            "  PRIMARY KEY (`r_id`),\n" +
                            "  UNIQUE KEY `r_id` (`r_id`)\n" +
                            ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;\n");
                    ps.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Restaurant() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblRestaurantManagement = new JLabel("Restaurant Management");
        lblRestaurantManagement.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblRestaurantManagement.setForeground(Color.GRAY);

        JButton btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminLogin.main(new String[]{});
                frame.dispose();
            }
        });
        btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JButton btnUserLogin = new JButton("User Login");
        btnUserLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                UserLogin.main(new String[]{});
                frame.dispose();
            }
        });
        btnUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JButton btnUserRegister = new JButton("User Register");
        btnUserRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                UserForm.main(new String[]{});
                frame.dispose();
            }
        });
        btnUserRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(114)
                                                .addComponent(lblRestaurantManagement))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(140)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(btnUserLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnAdminLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                                        .addComponent(btnUserRegister, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(95, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRestaurantManagement)
                                .addGap(32)
                                .addComponent(btnAdminLogin, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnUserLogin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnUserRegister, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(70, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
