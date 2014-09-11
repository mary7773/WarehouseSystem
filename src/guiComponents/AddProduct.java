package guiComponents;


import entity.*;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.Transaction;
import entity.Products;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;


public class AddProduct
{

    private Session s = null;
    private Transaction tx = null;

    private JDialog frameAddProduct;
    private JTextField productNameTextField;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private JComboBox<String> brandComboBox;
    private JComboBox<String> categoryComboBox;


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
                    AddProduct window = new AddProduct();
                    window.frameAddProduct.setVisible(true);
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
    public AddProduct()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     *
     * @return
     */
    public JDialog initialize()
    {
        frameAddProduct = new JDialog();
        frameAddProduct.setTitle("Add Product");
        frameAddProduct.setBounds(100, 100, 470, 507);
        frameAddProduct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{77, 89, 66, 94, 0};
        gridBagLayout.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 34, 14, 40, 23, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                                                Double.MIN_VALUE};
        frameAddProduct.getContentPane().setLayout(gridBagLayout);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(AddProduct.class.getResource("/images/1394493586_label_new.png")));

        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        frameAddProduct.getContentPane().add(label, gbc_label);

        JLabel productNameLabel = new JLabel("Product Name");
        GridBagConstraints constraintsProductNameLabel = new GridBagConstraints();
        constraintsProductNameLabel.anchor = GridBagConstraints.EAST;
        constraintsProductNameLabel.insets = new Insets(0, 0, 5, 5);
        constraintsProductNameLabel.gridx = 0;
        constraintsProductNameLabel.gridy = 1;
        frameAddProduct.getContentPane().add(productNameLabel, constraintsProductNameLabel);

        productNameTextField = new JTextField();
        productNameTextField.setColumns(10);
        GridBagConstraints constraintsProductNameTextField = new GridBagConstraints();
        constraintsProductNameTextField.ipady = 5;
        constraintsProductNameTextField.ipadx = 5;
        constraintsProductNameTextField.anchor = GridBagConstraints.SOUTH;
        constraintsProductNameTextField.fill = GridBagConstraints.HORIZONTAL;
        constraintsProductNameTextField.insets = new Insets(5, 2, 5, 0);
        constraintsProductNameTextField.gridwidth = 3;
        constraintsProductNameTextField.gridx = 1;
        constraintsProductNameTextField.gridy = 1;
        frameAddProduct.getContentPane().add(productNameTextField, constraintsProductNameTextField);

        JLabel priceLabel = new JLabel("Price");
        GridBagConstraints constraintsPriceLabel = new GridBagConstraints();
        constraintsPriceLabel.anchor = GridBagConstraints.EAST;
        constraintsPriceLabel.insets = new Insets(0, 0, 5, 5);
        constraintsPriceLabel.gridx = 0;
        constraintsPriceLabel.gridy = 2;
        frameAddProduct.getContentPane().add(priceLabel, constraintsPriceLabel);

        priceTextField = new JTextField();
        priceTextField.setColumns(10);
        GridBagConstraints constraintsPriceTextField = new GridBagConstraints();
        constraintsPriceTextField.ipady = 5;
        constraintsPriceTextField.ipadx = 1;
        constraintsPriceTextField.anchor = GridBagConstraints.NORTH;
        constraintsPriceTextField.fill = GridBagConstraints.HORIZONTAL;
        constraintsPriceTextField.insets = new Insets(5, 2, 5, 0);
        constraintsPriceTextField.gridwidth = 3;
        constraintsPriceTextField.gridx = 1;
        constraintsPriceTextField.gridy = 2;
        frameAddProduct.getContentPane().add(priceTextField, constraintsPriceTextField);

        JLabel quantityLabel = new JLabel("Quantity");
        GridBagConstraints constraintsQuantityLabel = new GridBagConstraints();
        constraintsQuantityLabel.anchor = GridBagConstraints.EAST;
        constraintsQuantityLabel.insets = new Insets(0, 0, 5, 5);
        constraintsQuantityLabel.gridx = 0;
        constraintsQuantityLabel.gridy = 3;
        frameAddProduct.getContentPane().add(quantityLabel, constraintsQuantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        GridBagConstraints constraintsQuantityTextField = new GridBagConstraints();
        constraintsQuantityTextField.ipady = 5;
        constraintsQuantityTextField.ipadx = 1;
        constraintsQuantityTextField.anchor = GridBagConstraints.NORTH;
        constraintsQuantityTextField.fill = GridBagConstraints.HORIZONTAL;
        constraintsQuantityTextField.insets = new Insets(5, 2, 5, 0);
        constraintsQuantityTextField.gridwidth = 3;
        constraintsQuantityTextField.gridx = 1;
        constraintsQuantityTextField.gridy = 3;
        frameAddProduct.getContentPane().add(quantityTextField, constraintsQuantityTextField);

        JLabel categoryLabel = new JLabel("Category");
        GridBagConstraints constraintsCategoryLabel = new GridBagConstraints();
        constraintsCategoryLabel.anchor = GridBagConstraints.EAST;
        constraintsCategoryLabel.insets = new Insets(0, 0, 5, 5);
        constraintsCategoryLabel.gridx = 0;
        constraintsCategoryLabel.gridy = 4;
        frameAddProduct.getContentPane().add(categoryLabel, constraintsCategoryLabel);

        Vector<String> categotyItems = new Vector<String>();
        Vector<String> brandItems = new Vector<String>();

        final Map<String, Categories> catMap = new HashMap<String, Categories>();
        final Map<String, Brands> brandMap = new HashMap<String, Brands>();

        try
        {

            s = GetConnectionFactory.getSessionFactory().openSession();
            tx = s.beginTransaction();

            Query query = s.createQuery("FROM Brands");
            List< ? > results = query.list();
            Iterator< ? > res = results.iterator();
            while (res.hasNext())
            {
                Brands br = (Brands)res.next();
                brandItems.add(br.getName());
                brandMap.put(br.getName(), br);

            }
            query = s.createQuery("FROM Categories");
            results = query.list();
            res = results.iterator();

            while (res.hasNext())
            {
                Categories ct = (Categories)res.next();
                categotyItems.add(ct.getName());
                catMap.put(ct.getName(), ct);

            }

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

        categoryComboBox = new JComboBox<String>();
        brandComboBox = new JComboBox<String>();

        DefaultComboBoxModel<String> catComboModel = new DefaultComboBoxModel<String>(categotyItems);
        catComboModel.setSelectedItem(null);
        categoryComboBox = new JComboBox<String>(catComboModel);

        DefaultComboBoxModel<String> brandComboModel = new DefaultComboBoxModel<String>(brandItems);
        brandComboModel.setSelectedItem(null);
        brandComboBox = new JComboBox<String>(brandComboModel);

        GridBagConstraints constraintsCategoryComboBox = new GridBagConstraints();
        constraintsCategoryComboBox.anchor = GridBagConstraints.NORTH;
        constraintsCategoryComboBox.fill = GridBagConstraints.HORIZONTAL;
        constraintsCategoryComboBox.insets = new Insets(5, 2, 5, 0);
        constraintsCategoryComboBox.gridwidth = 3;
        constraintsCategoryComboBox.gridx = 1;
        constraintsCategoryComboBox.gridy = 4;
        frameAddProduct.getContentPane().add(categoryComboBox, constraintsCategoryComboBox);

        JLabel brandLabel = new JLabel("Brand");
        GridBagConstraints constraintsBrandLabel = new GridBagConstraints();
        constraintsBrandLabel.anchor = GridBagConstraints.EAST;
        constraintsBrandLabel.insets = new Insets(0, 0, 5, 5);
        constraintsBrandLabel.gridx = 0;
        constraintsBrandLabel.gridy = 5;
        frameAddProduct.getContentPane().add(brandLabel, constraintsBrandLabel);

        GridBagConstraints constraintsBrandComboBox = new GridBagConstraints();
        constraintsBrandComboBox.anchor = GridBagConstraints.NORTH;
        constraintsBrandComboBox.fill = GridBagConstraints.HORIZONTAL;
        constraintsBrandComboBox.insets = new Insets(5, 2, 5, 0);
        constraintsBrandComboBox.gridwidth = 3;
        constraintsBrandComboBox.gridx = 1;
        constraintsBrandComboBox.gridy = 5;
        frameAddProduct.getContentPane().add(brandComboBox, constraintsBrandComboBox);

        JLabel imageLabel = new JLabel("Image");
        GridBagConstraints constraintsImageLabel = new GridBagConstraints();
        constraintsImageLabel.anchor = GridBagConstraints.EAST;
        constraintsImageLabel.insets = new Insets(0, 0, 5, 5);
        constraintsImageLabel.gridx = 0;
        constraintsImageLabel.gridy = 7;
        frameAddProduct.getContentPane().add(imageLabel, constraintsImageLabel);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setIcon(new ImageIcon(AddProduct.class.getResource("/images/cancel_20.png")));
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                frameAddProduct.dispose();
            }
        });

        JButton btnSave = new JButton("Save");
        btnSave.setIcon(new ImageIcon(AddProduct.class.getResource("/images/addi_20.png")));
        btnSave.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                saveProduct();
            }


            private void saveProduct()
            {

                final Products newProduct = new Products();

                if (checkCorrectInfo())
                {
                    newProduct.setName(productNameTextField.getText());
                    newProduct.setPrice(Double.parseDouble(priceTextField.getText()));
                    newProduct.setQuantity(Double.parseDouble(quantityTextField.getText()));

                    Categories cat = new Categories();

                    if (categoryComboBox.getItemCount() != 0 && categoryComboBox.getSelectedItem()!=null )
                    {

                        cat.setCategoryId(catMap.get(categoryComboBox.getSelectedItem()).getCategoryId());
                        newProduct.setCategories(cat);

                    }
                    // add brand
                    Brands brand = new Brands();

                    if (brandComboBox.getItemCount() != 0 && brandComboBox.getSelectedItem()!=null)
                    {

                        brand.setBrandId(brandMap.get(brandComboBox.getSelectedItem()).getBrandId());
                        newProduct.setBrands(brand);

                    }

                    try
                    {
                        s = GetConnectionFactory.getSessionFactory().openSession();

                        tx = s.beginTransaction();
                        s.save(newProduct);

                        okMeesage("Save successful");
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
                if (!checkCorrectInfo())
                {
                    errorMessage("Product is not saved in database!");
                }

            }


            private void okMeesage(String message)
            {

                JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog("Successfully added product in database!");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);

            }


            public void errorMessage(String message)
            {
                JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }


            private boolean checkCorrectInfo()
            {

                try
                {

                    Double.parseDouble(priceTextField.getText());
                    Double.parseDouble(quantityTextField.getText());

                }
                catch (NumberFormatException ex)
                {

                    JOptionPane.showMessageDialog(null,
                                                  "Fields price and/or quantity do not have correct data format!");
                    return false;

                }

                try
                {

                    if (productNameTextField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "You did not enter the name of the product!");
                        return false;
                    }
                    if (priceTextField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "You did not enter any price of the product!");
                        return false;
                    }

                    if (quantityTextField.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "You did not enter any quantity of the product!");
                        return false;
                    }
                    // if (categoryComboBox.getSelectedItem().equals("")) {
                    // JOptionPane.showMessageDialog(null,
                    // "You did not choose any category!");
                    // return false;
                    // }
                    // if (brandComboBox.getSelectedItem().equals("")) {
                    // JOptionPane.showMessageDialog(null,
                    // "You did not choose any brand!");
                    // return false;
                    // }
                }
                catch (NullPointerException ex)
                {
                    JOptionPane optionPane = new JOptionPane("Fields cannot be empty!",
                                                             JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
            }
        });

        GridBagConstraints constraintsButtonSave = new GridBagConstraints();
        constraintsButtonSave.anchor = GridBagConstraints.NORTH;
        constraintsButtonSave.fill = GridBagConstraints.HORIZONTAL;
        constraintsButtonSave.insets = new Insets(0, 0, 5, 5);
        constraintsButtonSave.gridx = 2;
        constraintsButtonSave.gridy = 9;
        frameAddProduct.getContentPane().add(btnSave, constraintsButtonSave);

        GridBagConstraints constraintsButtonCancel = new GridBagConstraints();
        constraintsButtonCancel.insets = new Insets(0, 0, 5, 0);
        constraintsButtonCancel.anchor = GridBagConstraints.NORTH;
        constraintsButtonCancel.fill = GridBagConstraints.HORIZONTAL;
        constraintsButtonCancel.gridx = 3;
        constraintsButtonCancel.gridy = 9;
        frameAddProduct.getContentPane().add(btnCancel, constraintsButtonCancel);
        return frameAddProduct;
    }
}
