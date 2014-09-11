package guiComponents;

import java.awt.Dialog;
import java.awt.GridBagConstraints;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



public class MainWindow {

	private JFrame frmWarehouse;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow window = new MainWindow();
//					window.frmWarehouse.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MainWindow() {
		//initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JFrame initialize(Boolean role) {
		frmWarehouse = new JFrame();

		if(role)
		{
		    frmWarehouse.setTitle("Warehouse system - You are ADMINISTRATOR");
		}else
		{
		    frmWarehouse.setTitle("Warehouse system");
		}

		frmWarehouse.setBounds(100, 100, 739, 457);
		frmWarehouse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frmWarehouse.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/images/warehouse1.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frmWarehouse.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		frmWarehouse.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open file");
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save file");
		mnFile.add(mntmSave);

		JMenuItem mntmEdit = new JMenuItem("Edit file");
		mnFile.add(mntmEdit);

		JMenuItem mntmClose = new JMenuItem("Exit system");
		mnFile.add(mntmClose);

		JMenu mnNewMenu = new JMenu("Operations");
		menuBar.add(mnNewMenu);

		JMenu mnAdministrationUsers = new JMenu("Administration Users");
		mnAdministrationUsers.setEnabled(role);
		mnNewMenu.add(mnAdministrationUsers);

		JMenuItem mntmAddNewUser = new JMenuItem("Add New User");
		mnAdministrationUsers.setEnabled(role);
		mntmAddNewUser.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {

                CreateUsers s = new CreateUsers();
                JDialog d = s.initialize();
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.pack();
                d.setVisible(true);

            }
        });
		mnAdministrationUsers.add(mntmAddNewUser);

		JMenuItem mntmEditUser = new JMenuItem("Edit User");
		mnAdministrationUsers.add(mntmEditUser);

		JMenuItem mntmDdeleteUser = new JMenuItem("Ddelete User");
		mnAdministrationUsers.add(mntmDdeleteUser);

		JMenu mnSalesManager = new JMenu("Sales Manager");
		mnSalesManager.setEnabled(role);
		mnNewMenu.add(mnSalesManager);

		JMenuItem mntmViewSales = new JMenuItem("View sales");
		mnSalesManager.add(mntmViewSales);

		JMenuItem mntmViewProfits = new JMenuItem("View profits");
		mnSalesManager.add(mntmViewProfits);

		JMenu mnOperationsWithBrands = new JMenu("Operations with Brands");
		mnOperationsWithBrands.setEnabled(role);
		mnNewMenu.add(mnOperationsWithBrands);

		JMenuItem mntmAddBrand = new JMenuItem("Add Brand");
		mntmAddBrand.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddBrand brand = new AddBrand();
                JDialog d = brand.initialize();
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.pack();
                d.setVisible(true);

            }
        });
		mnOperationsWithBrands.add(mntmAddBrand);

		JMenuItem mntmEditBrand = new JMenuItem("Edit Brand");
		mnOperationsWithBrands.add(mntmEditBrand);

		JMenuItem mntmDeleteBrand = new JMenuItem("Delete Brand");
		mnOperationsWithBrands.add(mntmDeleteBrand);

		JMenuItem mntmSearchBrand = new JMenuItem("Search Brand");
		mnOperationsWithBrands.add(mntmSearchBrand);

		JMenu mnOperationsWithCategories = new JMenu("Operations with Categories");
		mnOperationsWithCategories.setEnabled(role);
		mnNewMenu.add(mnOperationsWithCategories);

		JMenuItem mntmAddCategory = new JMenuItem("Add category");
		mntmAddCategory.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                AddCategory cat = new AddCategory();
                JDialog d = cat.initialize();
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.pack();
                d.setVisible(true);

            }
        });
		mnOperationsWithCategories.add(mntmAddCategory);
		JMenuItem mntmEditCategory = new JMenuItem("Edit category");
		mnOperationsWithCategories.add(mntmEditCategory);

		JMenuItem mntmDeleteCategory = new JMenuItem("Delete category");
		mnOperationsWithCategories.add(mntmDeleteCategory);

		JMenuItem mntmSearchcategory = new JMenuItem("SearchCategory");
		mnOperationsWithCategories.add(mntmSearchcategory);

		JMenu mnEditProducts = new JMenu("Operations with Products");
		mnNewMenu.add(mnEditProducts);

		JMenuItem mntmAddProduct = new JMenuItem("Add Product");
		mntmAddProduct.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                AddProduct prod = new AddProduct();
                JDialog d = prod.initialize();
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.pack();
                d.setVisible(true);

            }
        });
		mnEditProducts.add(mntmAddProduct);

		JMenuItem mntmEditProduct = new JMenuItem("Edit Product");
		mnEditProducts.add(mntmEditProduct);

		JMenuItem mntmDeleteProduct = new JMenuItem("Delete Product");
		mnEditProducts.add(mntmDeleteProduct);

		JMenuItem mntmSearchProduct = new JMenuItem("Search Product");
		mntmSearchProduct.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                SearchProduct prod = new SearchProduct();
                JDialog d = prod.initialize();
                d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                d.pack();
                d.setVisible(true);

            }
        });
		mnEditProducts.add(mntmSearchProduct);

		JMenu mnInvoices = new JMenu("Invoices");
		menuBar.add(mnInvoices);

		JMenuItem mntmCreateInvoice = new JMenuItem("Create invoice");
		mnInvoices.add(mntmCreateInvoice);

		JMenuItem mntmEditInvoice = new JMenuItem("Edit invoice");
		mnInvoices.add(mntmEditInvoice);

		JMenuItem mntmSearchInvoice = new JMenuItem("Search invoice");
		mnInvoices.add(mntmSearchInvoice);

		JMenuItem mntmAnulateInvoice = new JMenuItem("Anulate invoice");
		mntmAnulateInvoice.setEnabled(role);
		mnInvoices.add(mntmAnulateInvoice);

		JMenu mnOrders = new JMenu("Orders");
		menuBar.add(mnOrders);

		JMenuItem mntmCreateNewOrder = new JMenuItem("Create New Order");
		mnOrders.add(mntmCreateNewOrder);

		JMenuItem mntmEditOrder = new JMenuItem("Edit Order");
		mnOrders.add(mntmEditOrder);

		JMenuItem mntmDeleteOrder = new JMenuItem("Delete Order");
		mnOrders.add(mntmDeleteOrder);

		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frmWarehouse.setVisible(true);

        return frmWarehouse;
	}

}
