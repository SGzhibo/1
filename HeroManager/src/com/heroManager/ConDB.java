package com.heroManager;

import java.sql.*;
import java.util.Vector;

public class ConDB {
	
	Connection ct=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	ResultSetMetaData rsmd=null;
	int columnCount;
	Vector row;
	Vector columnNames,rowData;
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://localhost:1433;DatabaseName=Heroes";
	String user="sa";
	String password="123456";
	
	public ConDB() {
		
		try {
			
			Class.forName(driverName);
			ct=DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(Vector columnNames) {
		this.columnNames = columnNames;
	}

	public Vector getRowData() {
		return rowData;
	}

	public void setRowData(Vector rowData) {
		this.rowData = rowData;
	}
	
	public void ConClose() {
		try {
			if(rs!=null) {
			    rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(ct!=null) {
			    ct.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public  void queryDB(String sql) {
		
		try {			

//			System.out.println(sql);
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			rsmd=rs.getMetaData();
			
			columnNames=new Vector();
			rowData=new Vector();
			
			columnCount=rsmd.getColumnCount();
			for(int i=1;i<columnCount+1;i++) {
				columnNames.add(rsmd.getColumnName(i));				
			}
			while(rs.next()) {
				row=new Vector();
				for(int i=0;i<columnNames.size();i++) {
					row.add(rs.getString((String)columnNames.get(i)));
//					System.out.println(row.get(i));
//					System.out.print(columnNames.get(i)+" ");
//					System.out.println();
//					System.out.print(rs.getString((String)columnNames.get(i))+" ");
				}
				rowData.add(row);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int updateDB(String sql) {
		int i = 0;
		try {
			
			ps=ct.prepareStatement(sql);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
	









