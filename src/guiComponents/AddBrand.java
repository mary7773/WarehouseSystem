package guiComponents;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.hibernate.Session;

import org.hibernate.Transaction;

import entity.Brands;


public class AddBrand
{

    private Session s = null;
    private Transaction tx = null;

    private JDialog frmAddBrand;
    private JTextField brandTextField;


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
                    AddBrand window = new AddBrand();
                    window.frmAddBrand.setVisible(true);
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
    public AddBrand()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    public JDialog initialize()
    {
        frmAddBrand = new JDialog();
        frmAddBrand.setTitle("Add brand");
        frmAddBrand.setBounds(100, 100, 389, 271);
        frmAddBrand.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmAddBrand.getContentPane().setLayout(gridBagLayout);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(AddBrand.class.getResource("/images/1394493384_brands.png")));
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 1;
        gbc_label.gridy = 1;
        frmAddBrand.getContentPane().add(label, gbc_label);

        JLabel lblEnterANew = new JLabel("Enter a new brand:");
        lblEnterANew.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        GridBagConstraints gbc_lblEnterANew = new GridBagConstraints();
        gbc_lblEnterANew.insets = new Insets(0, 0, 5, 5);
        gbc_lblEnterANew.anchor = GridBagConstraints.SOUTHEAST;
        gbc_lblEnterANew.gridx = 1;
        gbc_lblEnterANew.gridy = 2;
        frmAddBrand.getContentPane().add(lblEnterANew, gbc_lblEnterANew);

        brandTextField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 2;
        frmAddBrand.getContentPane().add(brandTextField, gbc_textField);
        brandTextField.setColumns(20);

        JButton btnSave = new JButton("Save");
        btnSave.setIcon(new ImageIcon(AddBrand.class.getResource("/images/1394491324_OK.png")));
        GridBagConstraints gbc_btnSave = new GridBagConstraints();
        gbc_btnSave.anchor = GridBagConstraints.EAST;
        gbc_btnSave.insets = new Insets(0, 0, 5, 5);
        gbc_btnSave.gridx = 1;
        gbc_btnSave.gridy = 4;
        frmAddBrand.getContentPane().add(btnSave, gbc_btnSave);

        btnSave.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {

                saveBrand();
                CreateUsers.okMeesage("Save OK");
                frmAddBrand.dispose();

            }

        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setIcon(new ImageIcon(AddBrand.class.getResource("/images/1394491235_No.png")));
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.anchor = GridBagConstraints.EAST;
        gbc_btnCancel.gridx = 2;
        gbc_btnCancel.gridy = 4;
        frmAddBrand.getContentPane().add(btnCancel, gbc_btnCancel);

        btnCancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {

                frmAddBrand.dispose();

            }

        });
        return frmAddBrand;
    }


    private void saveBrand()
    {

        Brands brand = new Brands();
        String br = brandTextField.getText().trim();
        if (br.equals(""))
        {

            JOptionPane.showMessageDialog(null,
                                          "You did not enter a brand name!",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);

        }
        else
        {
            brand.setName(br);
            brandTextField.setText("");
            try
            {

                s = GetConnectionFactory.getSessionFactory().openSession();
                tx = s.beginTransaction();
                s.save(brand);
                JOptionPane.showMessageDialog(null,
                                              "The brand is added successfully",
                                              "Information",
                                              JOptionPane.INFORMATION_MESSAGE);

            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
            finally
            {
                tx.commit();
                s.close();
            }

        }

    }

}
