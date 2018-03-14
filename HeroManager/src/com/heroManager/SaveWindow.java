package com.heroManager;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveWindow extends JFileChooser {

	
	File f=null;
	FileOutputStream fos=null;
	//ConDB con=null;
	public SaveWindow() {
		
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		FileNameExtensionFilter filter=new FileNameExtensionFilter("文本文件(*.txt)",".txt");
		this.setFileFilter(filter);		 
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("图像文件(*.jpg)",".jpg");
		this.setFileFilter(filter1);	
		
		int option = this.showSaveDialog(null);  
		
		if(option==JFileChooser.APPROVE_OPTION) {
			f=this.getSelectedFile();
			if(!this.getSelectedFile().exists()) {
				System.out.println("1");
			}
			if(f.getName().indexOf('.')==-1||f.getName().indexOf('.')==f.getName().length()-1) {
				String fname=f.getName()+((FileNameExtensionFilter) this.getFileFilter()).getExtensions()[0];
//				String str[]=((FileNameExtensionFilter) this.getFileFilter()).getExtensions();
//				System.out.println(fname);
				f=new File(this.getCurrentDirectory(),fname);
			}
//			System.out.println(f.getName());			
			try {
				fos=new FileOutputStream(f);
				String s=null;
				//con=new ConDB();
				//con.queryDB("select * from heroes");
				for(int i=0;i<TableModel.rowData.size();i++) {
					s=TableModel.rowData.get(i).toString();
					fos.write(s.getBytes());
					fos.write("\r\n".getBytes());
				}
				//byte[] buff=new byte[1024];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					//con.ConClose();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}
	

}
