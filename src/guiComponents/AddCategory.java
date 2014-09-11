package guiComponents;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.hibernate.Session;

import org.hibernate.Transaction;import entity.Categories;

public class AddCategory {

	private Session s = null;
	private Transaction tx = null;


	private JDialog frmAddCategory;
	private JTextField categoryTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCategory window = new AddCategory();
					window.frmAddCategory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddCategory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JDialog initialize() {
		frmAddCategory = new JDialog();
		frmAddCategory.setResizable(false);
		frmAddCategory.setTitle("Add Category");
		frmAddCategory.setBounds(100, 100, 443, 276);
		frmAddCategory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAddCategory.getContentPane().setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddCategory.class.getResource("/images/product_documentation.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		frmAddCategory.getContentPane().add(label, gbc_label);

		JLabel lblEnterTheCategory = new JLabel("Enter the category name:");
		lblEnterTheCategory.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblEnterTheCategory = new GridBagConstraints();
		gbc_lblEnterTheCategory.anchor = GridBagConstraints.SOUTH;
		gbc_lblEnterTheCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterTheCategory.gridx = 1;
		gbc_lblEnterTheCategory.gridy = 3;
		frmAddCategory.getContentPane().add(lblEnterTheCategory, gbc_lblEnterTheCategory);

		categoryTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 15);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		frmAddCategory.getContentPane().add(categoryTextField, gbc_textField);
		categoryTextField.setColumns(30);

		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(AddCategory.class.getResource("/images/1394491324_OK.png")));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 5;
		frmAddCategory.getContentPane().add(btnSave, gbc_btnSave);

		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				saveCategory();

			}

		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(AddCategory.class.getResource("/images/1394491235_No.png")));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 15);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 5;
		frmAddCategory.getContentPane().add(btnCancel, gbc_btnCancel);

		btnCancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

			    frmAddCategory.dispose();

			}

		});
        return frmAddCategory;
	}



	private void saveCategory() {

		Categories category = new Categories();
		String cat = categoryTextField.getText().trim();
		if(cat.equals("")){

			JOptionPane.showMessageDialog(null, "You did not enter a name!", "Error", JOptionPane.ERROR_MESSAGE);

		}
		else{
		category.setName(cat);
		categoryTextField.setText("");
		try {

			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();
			s.save(category);
			JOptionPane.showMessageDialog(null, "The category is added successfully", "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}
		}
	}

}
