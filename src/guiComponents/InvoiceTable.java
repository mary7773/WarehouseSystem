package guiComponents;

import java.util.ArrayList;
import java.util.List;

import entity.*;

import javax.swing.table.AbstractTableModel;

public class InvoiceTable extends AbstractTableModel {

	List<Invoice> rowData = null;
	String[] columnNames = { "Date", "Name", "Qty", "Price", "Total" };

	public InvoiceTable() {
		super();
		rowData = new ArrayList<Invoice>();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public int getRowCount() {
		
		return rowData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		Invoice invoice = rowData.get(row);
		
		switch (col) {
		case 0:			
			return invoice.getDateInvoice();
		case 1:			
			return invoice.getProduct().getName();
		case 2:			
			return invoice.getQuanity();
		case 3:			
			return invoice.getProduct().getPrice();
		case 4:			
			return invoice.getTotal();

		}		
		return null;
	}
	
	  public void updateRow(List<Invoice> in)
	    {
		 this.rowData = in;
//	        List<Invoice> newData = new ArrayList<Invoice>();
//	        for (Invoice invoice : in)
//	        {
//	            newData.add(invoice);
////	            System.out.println(invoice.getInvoiceId() + " " +invoice.getInvoiceNumber()+ " " + invoice.getCompany()+ " " + invoice.getCustomer()+ " " + invoice.getQuantity());
//	        }
//	        rowData = newData;
	    }

}
