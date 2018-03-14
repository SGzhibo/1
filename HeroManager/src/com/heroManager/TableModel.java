package com.heroManager;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	
	static Vector rowData,columnNames;
	ConDB con=null;
	
	public TableModel(String sql) {
		
		con=new ConDB();
		con.queryDB(sql);
		rowData=con.getRowData();
		columnNames=con.getColumnNames();
		con.ConClose();
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(row)).get(column);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

}
