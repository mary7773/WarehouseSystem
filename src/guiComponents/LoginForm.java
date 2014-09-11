package guiComponents;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Users;


public class LoginForm
{

    Session session;
    Transaction tx;
    Configuration cfg;
    SessionFactory factory;
    private JFrame frmTitle;
    private JLabel label;
    private JLabel lblNewLabel;
    private JPasswordField passPasswordField;
    private JTextField userTextField;
    private JButton btnLogin;
    private JLabel lblLoginForm;


    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    LoginForm window = new LoginForm();
                    window.frmTitle.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the application.
     */
    public LoginForm()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frmTitle = new JFrame();
        frmTitle.setTitle("Login");
        frmTitle.setBounds(100, 100, 391, 261);
        frmTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{1, 58, 123, 52, 0};
        gridBagLayout.rowHeights = new int[]{1, 34, 64, 14, 14, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmTitle.getContentPane().setLayout(gridBagLayout);

        label = new JLabel("");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        frmTitle.getContentPane().add(label, gbc_label);

        lblLoginForm = new JLabel("Login Form");
        lblLoginForm.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblLoginForm.setIcon(new ImageIcon(LoginForm.class.getResource("/images/icon.png")));
        GridBagConstraints gbc_lblLoginForm = new GridBagConstraints();
        gbc_lblLoginForm.anchor = GridBagConstraints.WEST;
        gbc_lblLoginForm.insets = new Insets(10, 0, 5, 5);
        gbc_lblLoginForm.gridx = 2;
        gbc_lblLoginForm.gridy = 1;
        frmTitle.getContentPane().add(lblLoginForm, gbc_lblLoginForm);

        lblNewLabel = new JLabel("Sign Up");
        lblNewLabel.setForeground(new Color(244, 164, 96));
        lblNewLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
        lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource("/images/1393622690_Login.png")));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 2;
        frmTitle.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblUserName = new JLabel("User Name");
        GridBagConstraints gbc_lblUserName = new GridBagConstraints();
        gbc_lblUserName.anchor = GridBagConstraints.EAST;
        gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
        gbc_lblUserName.gridx = 1;
        gbc_lblUserName.gridy = 3;
        frmTitle.getContentPane().add(lblUserName, gbc_lblUserName);

        userTextField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.anchor = GridBagConstraints.WEST;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 3;
        frmTitle.getContentPane().add(userTextField, gbc_textField);
        userTextField.setColumns(20);

        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 4;
        frmTitle.getContentPane().add(lblPassword, gbc_lblPassword);

        passPasswordField = new JPasswordField();
        passPasswordField.setColumns(20);
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.anchor = GridBagConstraints.WEST;
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 4;
        frmTitle.getContentPane().add(passPasswordField, gbc_passwordField);

        btnLogin = new JButton("Login");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.anchor = GridBagConstraints.EAST;
        gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
        gbc_btnLogin.gridx = 2;
        gbc_btnLogin.gridy = 5;
        frmTitle.getContentPane().add(btnLogin, gbc_btnLogin);
        btnLogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                String userName = userTextField.getText();
                char[] passF = passPasswordField.getPassword();
                String pass = new String(passF);

                session = GetConnectionFactory.getSessionFactory().openSession();
                tx = null;

                try
                {
                    tx = session.beginTransaction();
                    Query query = session.createQuery("from Users u where u.userName =:username");
                    query.setParameter("username", userName);

                    List< ? > userList = query.list();
                    Users user = null;
                    try
                    {
                        user = (Users)userList.get(0);
                    }
                    catch (Exception e)
                    {

                        JOptionPane.showMessageDialog(null,
                                                      "User name and/or password is not correct!",
                                                      "Error",
                                                      JOptionPane.ERROR_MESSAGE);
                        userTextField.setText("");
                        passPasswordField.setText("");

                    }
                    if (user.getPassword().equals(pass))

                    {
                        Boolean admin = false;
                        if (1 == (user.getUsersGroups().getUsersGroupId()))
                        {
                            admin = true;
                        }
                        MainWindow mainWindow = new MainWindow();
                        mainWindow.initialize(admin);
                        frmTitle.dispose();
                    }
                }
                catch (HibernateException e)
                {
                    if (tx != null)
                        tx.rollback();
                    e.printStackTrace();
                }
                finally
                {
                    session.close();
                }

            }
        });

    }
}
