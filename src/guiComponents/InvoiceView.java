package guiComponents;

import entity.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.InvoiceController;

public class InvoiceView extends JPanel {
	Session s = null;
	Transaction tx = null;
	Configuration cfg = null;
	SessionFactory factory = null;

	private InvoiceTable model;
	private List<Invoice> data;
	Map<String, Users> user;
	Map<String, Products> pr;

	JFrame frmInoice;
	private JTable tableInvoice;
	private JTextField textFieldID;
	private JTextField ftTotal;
	private JComboBox cmbCompany;
	private List<Invoice> invoiceList;
	private JTextField tfQuantity;
	private JTextField tfTax;
	JDateChooser dateChooser = null;
	JComboBox cmbCustomer = null;
	JComboBox cmbProduct = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceView window = new InvoiceView();
					window.frmInoice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InvoiceView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmInoice = new JFrame();
		data = new ArrayList<Invoice>();
		frmInoice.setIconImage(Toolkit.getDefaultToolkit().getImage(
				InvoiceView.class.getResource("/Icons/Invoice16.png")));
		frmInoice.setTitle("Inoice");
		frmInoice.setBounds(100, 100, 767, 510);
		frmInoice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 40, 40,
				0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0,
				1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		frmInoice.getContentPane().setLayout(gridBagLayout);

		model = new InvoiceTable();
		data = new ArrayList<Invoice>();

		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridwidth = 11;
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.gridx = 23;
		gbc_toolBar.gridy = 0;
		frmInoice.getContentPane().add(toolBar, gbc_toolBar);

		JButton btnMain = new JButton("Main Window");
		btnMain.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/RightRight24.png")));
		btnMain.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMain.setForeground(Color.BLUE);
		toolBar.add(btnMain);

		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame main = new MainFrame();
				main.initialize(true);
				frmInoice.setVisible(false);

			}
		});

		JLabel lblInvoice = new JLabel("INVOICE");
		lblInvoice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_lblInvoice = new GridBagConstraints();
		gbc_lblInvoice.gridwidth = 6;
		gbc_lblInvoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInvoice.insets = new Insets(0, 0, 5, 5);
		gbc_lblInvoice.gridx = 17;
		gbc_lblInvoice.gridy = 1;
		frmInoice.getContentPane().add(lblInvoice, gbc_lblInvoice);

		// set invoice id
		// String invoiceId = InvoiceController.invoiceIDGenerator();

		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 2;
		gbc_lblDate.gridy = 3;
		frmInoice.getContentPane().add(lblDate, gbc_lblDate);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 10;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 3;
		frmInoice.getContentPane().add(dateChooser, gbc_dateChooser);

		JLabel lblInvoiceId = new JLabel("Invoice ID");
		GridBagConstraints gbc_lblInvoiceId = new GridBagConstraints();
		gbc_lblInvoiceId.anchor = GridBagConstraints.EAST;
		gbc_lblInvoiceId.insets = new Insets(0, 0, 5, 5);
		gbc_lblInvoiceId.gridx = 28;
		gbc_lblInvoiceId.gridy = 3;
		frmInoice.getContentPane().add(lblInvoiceId, gbc_lblInvoiceId);

		textFieldID = new JTextField();
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.gridwidth = 3;
		gbc_textFieldID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 29;
		gbc_textFieldID.gridy = 3;
		frmInoice.getContentPane().add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(20);
		// Invoice in = new Invoice();
		//
		// int number = 1;
		// in.setInvoiceId(number);
		// number = in.getInvoiceId() + 1;
		// textFieldID.setText(String.valueOf(in.getInvoiceId()));

		JLabel lblCompany = new JLabel("Customer");
		GridBagConstraints gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.anchor = GridBagConstraints.EAST;
		gbc_lblCompany.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompany.gridx = 2;
		gbc_lblCompany.gridy = 4;
		frmInoice.getContentPane().add(lblCompany, gbc_lblCompany);

		cmbCustomer = new JComboBox();
		GridBagConstraints gbc_cmbCustomer = new GridBagConstraints();
		gbc_cmbCustomer.gridwidth = 10;
		gbc_cmbCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCustomer.gridx = 3;
		gbc_cmbCustomer.gridy = 4;
		frmInoice.getContentPane().add(cmbCustomer, gbc_cmbCustomer);

		JLabel lblCompany_1 = new JLabel("Company");
		GridBagConstraints gbc_lblCompany_1 = new GridBagConstraints();
		gbc_lblCompany_1.anchor = GridBagConstraints.EAST;
		gbc_lblCompany_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompany_1.gridx = 28;
		gbc_lblCompany_1.gridy = 4;
		frmInoice.getContentPane().add(lblCompany_1, gbc_lblCompany_1);

		cmbCompany = new JComboBox();
		GridBagConstraints gbc_cmbCompany = new GridBagConstraints();
		gbc_cmbCompany.gridwidth = 3;
		gbc_cmbCompany.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCompany.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCompany.gridx = 29;
		gbc_cmbCompany.gridy = 4;
		frmInoice.getContentPane().add(cmbCompany, gbc_cmbCompany);

		JLabel lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.anchor = GridBagConstraints.EAST;
		gbc_lblProduct.gridx = 2;
		gbc_lblProduct.gridy = 5;
		frmInoice.getContentPane().add(lblProduct, gbc_lblProduct);

		cmbProduct = new JComboBox();
		GridBagConstraints gbc_cmbProduct = new GridBagConstraints();
		gbc_cmbProduct.gridwidth = 10;
		gbc_cmbProduct.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProduct.gridx = 3;
		gbc_cmbProduct.gridy = 5;
		frmInoice.getContentPane().add(cmbProduct, gbc_cmbProduct);

		JLabel lblQty = new JLabel("QTY");
		GridBagConstraints gbc_lblQty = new GridBagConstraints();
		gbc_lblQty.anchor = GridBagConstraints.EAST;
		gbc_lblQty.insets = new Insets(0, 0, 5, 5);
		gbc_lblQty.gridx = 28;
		gbc_lblQty.gridy = 5;
		frmInoice.getContentPane().add(lblQty, gbc_lblQty);

		tfQuantity = new JTextField();
		GridBagConstraints gbc_tfQuantity = new GridBagConstraints();
		gbc_tfQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_tfQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQuantity.gridx = 29;
		gbc_tfQuantity.gridy = 5;
		frmInoice.getContentPane().add(tfQuantity, gbc_tfQuantity);
		tfQuantity.setColumns(10);

		JButton btnAdd = new JButton("Add ");
		btnAdd.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/Add16.png")));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridwidth = 3;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 29;
		gbc_btnAdd.gridy = 6;
		frmInoice.getContentPane().add(btnAdd, gbc_btnAdd);

		loadData();

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Date date = dateChooser.getDate();

				int id = Integer.parseInt(textFieldID.getText());
				String product = (String) cmbProduct.getSelectedItem();
				Products p = pr.get(product);
				String com = (String) cmbCompany.getSelectedItem();
				String c = (String) cmbCustomer.getSelectedItem();
				Users company = user.get(com);
				System.out.println(company.getFirstName());
				Users customer = user.get(c);
				int quantity = Integer.parseInt(tfQuantity.getText());
				System.out.println(quantity);

				Invoice in = new Invoice();
				in.setDateInvoice(date);
				in.setCompany(company);
				in.setCustomer(customer);
				in.setInvoiceNumber(id);
				in.setProduct(p);
				in.setQuanity(quantity);
				data.add(in);
				insertIntoTable(data);

			}
		});
		createTable();
		JCheckBox chckbxVat = new JCheckBox("VAT");
		GridBagConstraints gbc_chckbxVat = new GridBagConstraints();
		gbc_chckbxVat.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxVat.gridx = 30;
		gbc_chckbxVat.gridy = 12;
		frmInoice.getContentPane().add(chckbxVat, gbc_chckbxVat);

		JLabel lblTaxValue = new JLabel("Tax value (%)");
		GridBagConstraints gbc_lblTaxValue = new GridBagConstraints();
		gbc_lblTaxValue.anchor = GridBagConstraints.EAST;
		gbc_lblTaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblTaxValue.gridx = 29;
		gbc_lblTaxValue.gridy = 13;
		frmInoice.getContentPane().add(lblTaxValue, gbc_lblTaxValue);

		tfTax = new JTextField();
		GridBagConstraints gbc_tfTax = new GridBagConstraints();
		gbc_tfTax.insets = new Insets(0, 0, 5, 5);
		gbc_tfTax.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTax.gridx = 30;
		gbc_tfTax.gridy = 13;
		frmInoice.getContentPane().add(tfTax, gbc_tfTax);
		tfTax.setColumns(10);

		JButton btnPrint = new JButton("Print");
		btnPrint.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/Print32.png")));
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrint.gridx = 8;
		gbc_btnPrint.gridy = 14;
		frmInoice.getContentPane().add(btnPrint, gbc_btnPrint);

		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		JButton btnCalculateTotal = new JButton("Calculate total");
		btnCalculateTotal.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/Coin24.png")));
		GridBagConstraints gbc_btnCalculateTotal = new GridBagConstraints();
		gbc_btnCalculateTotal.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculateTotal.gridx = 29;
		gbc_btnCalculateTotal.gridy = 14;
		frmInoice.getContentPane()
				.add(btnCalculateTotal, gbc_btnCalculateTotal);

		ftTotal = new JTextField();
		GridBagConstraints gbc_ftTotal = new GridBagConstraints();
		gbc_ftTotal.insets = new Insets(0, 0, 5, 5);
		gbc_ftTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftTotal.gridx = 30;
		gbc_ftTotal.gridy = 14;
		frmInoice.getContentPane().add(ftTotal, gbc_ftTotal);
		ftTotal.setColumns(10);
	}

	private void loadData() {
		InvoiceTable model = new InvoiceTable();

		Vector<String> productItems = new Vector<String>();
		pr = new HashMap<String, Products>();

		factory = GetConnectionFactory.getSessionFactory();
		s = factory.openSession();
		tx = s.beginTransaction();

		Query query = s.createQuery("FROM Products");
		List<?> results = query.list();
		Iterator<?> res = results.iterator();
		while (res.hasNext()) {
			Products prod = (Products) res.next();
			productItems.add(prod.getName());
			pr.put(prod.getName(), prod);

		}
		DefaultComboBoxModel<String> modelProduct = new DefaultComboBoxModel<String>(
				productItems);
		cmbProduct.setModel(modelProduct);
		cmbProduct.setSelectedIndex(0);

		Vector<String> userItems = new Vector<String>();
		user = new HashMap<String, Users>();

		query = s.createQuery("FROM Users");
		results = query.list();
		res = results.iterator();
		while (res.hasNext()) {
			Users u = (Users) res.next();
			userItems.add(u.getFirstName() + " " + u.getLastName());
			user.put(u.getFirstName() + " " + u.getLastName(), u);

		}

		DefaultComboBoxModel<String> modelUser = new DefaultComboBoxModel<String>(
				userItems);
		cmbCustomer.setModel(modelUser);
		cmbCustomer.setSelectedIndex(0);
		DefaultComboBoxModel<String> modelCompany = new DefaultComboBoxModel<String>(
				userItems);
		cmbCompany.setModel(modelCompany);
		cmbCompany.setSelectedIndex(2);

	}

	private void createTable() {

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 30;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 7;
		frmInoice.getContentPane().add(scrollPane, gbc_scrollPane);

		tableInvoice = new JTable(model);
		scrollPane.setViewportView(tableInvoice);
		// tableInvoice.setModel(model);

	}

	private void insertIntoTable(List<Invoice> invoice) {

		// InvoiceTable model = new InvoiceTable();

		model.updateRow(data);
		model.fireTableDataChanged();

	}

}
