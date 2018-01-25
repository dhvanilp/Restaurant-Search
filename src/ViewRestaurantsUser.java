import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRestaurantsUser extends JFrame {
    static ViewRestaurantsUser frame;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ViewRestaurantsUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewRestaurantsUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 433);
        contentPane = new JPanel();
        contentPane.setForeground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblViewRestaurants = new JLabel("View Restaurants");
        lblViewRestaurants.setFont(new Font("Tahoma", Font.PLAIN, 22));

        JButton btnAll = new JButton("All");
        btnAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewRestaurants.main(new String[]{}, RestaurantDao.view());
            }
        });
        btnAll.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnFilterCategory = new JButton("Filter by Category");
        btnFilterCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FilterByCategory.main(new String[]{});
                frame.dispose();
            }
        });
        btnFilterCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnFilterRegion = new JButton("Filter by Region");
        btnFilterRegion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FilterByRegion.main(new String[]{});
                frame.dispose();
            }
        });
        btnFilterRegion.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                UserSuccess.main(new String[]{});
                frame.dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(20, Short.MAX_VALUE)
                                .addComponent(lblViewRestaurants)
                                .addGap(122))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(120)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnFilterRegion, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnFilterCategory, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(101, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblViewRestaurants)
                                .addGap(18)
                                .addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnFilterCategory, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnFilterRegion, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

}

