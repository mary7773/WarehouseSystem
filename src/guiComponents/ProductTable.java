package guiComponents;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Products;


public class ProductTable
    extends AbstractTableModel
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    List<Products> rowData = null;
    String[] columnNames = {"ID", "Name", "Price", "Qty", "Category", "Brand"};


    public ProductTable()
    {

        super();

    }


    @Override
    public int getColumnCount()
    {
        return this.columnNames.length;
    }


    public String getColumnName(int column)
    {
        return this.columnNames[column];
    }


    @Override
    public int getRowCount()
    {
        return this.rowData.size();
    }


    @Override
    public Object getValueAt(int row, int column)
    {
        Products product = this.rowData.get(row);

        switch (column)
        {
            case 0:
                return product.getProductId();
            case 1:
                return product.getName();
            case 2:
                return product.getPrice();
            case 3:
                return product.getQuantity();
            case 4:
                return product.getCategories().getName();
            case 5:
                return product.getBrands().getName();

        }
        return null;
    }


    public void updateRow(List<Products> pr)
    {
        List<Products> newData = new ArrayList<Products>();
        for (Products product : pr)
        {
            newData.add(product);
        }
        rowData = newData;
    }

}
