import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class RateRestaurants extends JFrame {
    static RateRestaurants frame;
    private JPanel contentPane;
    private JPanel starsPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new RateRestaurants();
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
    public RateRestaurants() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        starsPane = new JPanel(new FlowLayout());
        contentPane.setForeground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblRateRestaurants = new JLabel("Rate Restaurants");
        lblRateRestaurants.setFont(new Font("Tahoma", Font.PLAIN, 22));

        JLabel lblEnterRID = new JLabel("Enter R_ID:");

        textField = new JTextField();
        textField.setColumns(10);
        int rating[] = {3};
        ArrayList<JButton> stars = new ArrayList<JButton>();
        for (int i=0; i<5; i++) {
            final int j = i;
            JButton star = new JButton(String.valueOf("\u2605"));

            star.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateStars(stars, j, rating);
                }
            });
//            star.setFont(new Font("Tahoma", Font.PLAIN, 13));
            stars.add(star);
            starsPane.add(star);
        }

        updateStars(stars, 2, rating);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rid = textField.getText();
                if (rid == null || rid.trim().equals("")) {
                    JOptionPane.showMessageDialog(RateRestaurants.this, "R_ID can't be blank");
                } else {
                    try {
                        int r = Integer.parseInt(rid);
                        int i = RestaurantDao.rate(r, rating[0]);
                        if(i>0){
                            JOptionPane.showMessageDialog(RateRestaurants.this,"Thank you for rating!");
                            UserSuccess.main(new String[]{});
                            frame.dispose();
                        }else{
                            JOptionPane.showMessageDialog(RateRestaurants.this,"Sorry, unable to save!");
                        }
                    }
                    catch (NumberFormatException exp){
                        JOptionPane.showMessageDialog(RateRestaurants.this, "R_ID should be an Integer");
                    }

                }
            }
        });
        btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSuccess.main(new String[]{});
                frame.dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(20, Short.MAX_VALUE)
                                .addComponent(lblRateRestaurants)
                                .addGap(124))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(38)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(lblEnterRID, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                .addGap(40)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(125, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(62)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
                                        .addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(starsPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblRateRestaurants, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(101, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(359, Short.MAX_VALUE)
                                .addComponent(btnBack)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRateRestaurants)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblEnterRID)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addComponent(starsPane, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBack)
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

    public static void updateStars(ArrayList<JButton> stars, int k, int[] rating){
        rating[0] = k + 1;
        for (int i=0; i<=k; i++){
            stars.get(i).setForeground(Color.RED);
//            stars.get(i).setEnabled(true);
        }
        for (int i=k+1; i<5; i++){
            stars.get(i).setForeground(Color.BLACK);
//            stars.get(i).setEnabled(true);
        }
    }

}

