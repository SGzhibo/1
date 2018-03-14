package com.heroManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateDialog extends JDialog implements ActionListener{

	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	ConDB con=null;
	TableModel tm=null;
	Vector columnNames;
	JTextField[] jtf;
	public UpdateDialog(String title,String[] data) {
		
		super(new HeroManager(),title,true);
		
		con=new ConDB();
		con.queryDB("select * from heroes");
		columnNames=con.getColumnNames();
		con.ConClose();
		
		jp3=new JPanel(new GridLayout(columnNames.size(),1));
		jtf=new JTextField[columnNames.size()];
		for(int i=0;i<columnNames.size();i++) { 
			if(title.equals("添加")) {
				jtf[i]=new JTextField();
			}else if(title.equals("修改")) {
				jtf[i]=new JTextField(data[i].trim());
				jtf[0].setEditable(false);
				//System.out.println(data[i]+"1");
			}
		    jp3.add(jtf[i]);
		}
		
		jp2=new JPanel(new GridLayout(columnNames.size(),1));
		for(int i=0;i<columnNames.size();i++) {
			JLabel jl=new JLabel(columnNames.get(i).toString());
			jp2.add(jl);
		}
		
		jp1=new JPanel();
		jb1=new JButton(title);
		jb1.addActionListener(this);
		jb1.setActionCommand(title);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jb2.setActionCommand("取消");
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1,BorderLayout.SOUTH);
		this.add(jp2, BorderLayout.WEST);
		this.add(jp3, BorderLayout.CENTER);
		
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)screensize.getWidth()/2-150, (int)screensize.getHeight()/2-110);
		
		this.setSize(300,225);
		//this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("添加")) {
			con=new ConDB();
			StringBuffer str=new StringBuffer("insert into heroes values(");
			for(int i=0;i<jtf.length;i++) {
				if(i<jtf.length-1) {
					str.append("'"+jtf[i].getText().trim()+"'"+",");
				}else {
					str.append("'"+jtf[i].getText().trim()+"'"+")");
				}
			}
			//System.out.println(str);
			if(con.updateDB(str.toString())==1) {
				this.dispose();
				JOptionPane.showMessageDialog(this,"添加成功");
			}else {
				JOptionPane.showMessageDialog(this,"添加失败");
			}	
			con.ConClose();
		}else if(arg0.getActionCommand().equals("取消")) {
			this.dispose();
		}else if(arg0.getActionCommand().equals("修改")) {
			con=new ConDB();
			StringBuffer str=new StringBuffer("update heroes set ");
			for(int i=jtf.length-1;i>-1;i--) {
				if(i==0) {
					str.append("where "+this.columnNames.get(i)+"='"+jtf[i].getText().trim()+"'");
				}else if(i==1){
					str.append(this.columnNames.get(i)+"='"+jtf[i].getText().trim()+"' ");
				}else {
				str.append(this.columnNames.get(i)+"='"+jtf[i].getText().trim()+"'"+",");
			    }
			}
			System.out.println(str);
			if(con.updateDB(str.toString())==1) {
				this.dispose();
				JOptionPane.showMessageDialog(this,"修改成功");
			}else {
				JOptionPane.showMessageDialog(this,"修改失败");
			}	
			con.ConClose();
		}
	}
}
