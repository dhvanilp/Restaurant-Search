import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterByRegion extends JFrame {
    static FilterByRegion frame;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new FilterByRegion();
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
    public FilterByRegion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblEnterRegion = new JLabel("Enter PIN:");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pin = textField.getText();
                if (pin == null || pin.trim().equals("")) {
                    JOptionPane.showMessageDialog(FilterByRegion.this, "Region can't be blank");
                } else {

                    try {
                        int p = Integer.parseInt(pin);
                        ViewRestaurants.main(new String[]{}, RestaurantDao.view(p));
                    }
                    catch (NumberFormatException exp){
                        JOptionPane.showMessageDialog(FilterByRegion.this, "PIN should be an Integer");
                    }

                }
            }
        });
        btnView.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewRestaurantsUser.main(new String[]{});
                frame.dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(39)
                                .addComponent(lblEnterRegion)
                                .addGap(57)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(107, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(175, Short.MAX_VALUE)
                                .addComponent(btnView, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                .addGap(140))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(322, Short.MAX_VALUE)
                                .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(19)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEnterRegion))
                                .addGap(33)
                                .addComponent(btnView, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(43)
                                .addComponent(btnBack)
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
