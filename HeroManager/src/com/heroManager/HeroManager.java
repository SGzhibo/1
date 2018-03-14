package com.heroManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class HeroManager extends JFrame implements ActionListener{
	
	JTextField jtf1;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JPanel jp1,jp2;
	JTable jt1;
	JScrollPane jsp1;
	JMenuBar jmb;
	JMenu jm;
	JMenuItem jmi;
	TableModel tm=null;
	ConDB con=null;
	String sql="select * from heroes";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroManager manager=new HeroManager();

	}
	
	public HeroManager() {
		
		//jp1
		jl1=new JLabel("����");
		jtf1=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jb1.setActionCommand("��ѯ");
		jp1=new JPanel();
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);
		
	    //jt1
		tm=new TableModel(sql);
		jt1=new JTable(tm);
		jsp1=new JScrollPane(jt1);
		
		//jp2
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb2.setActionCommand("���");
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb3.setActionCommand("�޸�");
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		jb4.setActionCommand("ɾ��");
		jp2=new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		jmb=new JMenuBar();
		jm=new JMenu("����");
		jmi=new JMenuItem("�����ļ�");
		jmi.addActionListener(this);
		jmi.setActionCommand("�����ļ�");
		jm.add(jmi);
		jmb.add(jm);
		
		this.setJMenuBar(jmb);
		this.add(jp1,BorderLayout.NORTH);
		this.add(jsp1,BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);
		
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)screensize.getWidth()/2-250, (int)screensize.getHeight()/2-150);
		this.setSize(500,300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("��ѯ")) {
			if(!jtf1.getText().equals("")) {				
				sql="select * from heroes where Name='"+jtf1.getText().trim()+"'";				
			}else {
				sql="select * from heroes";
			}
			tm=new TableModel(sql);
			jt1.setModel(tm);
		}else if(arg0.getActionCommand().equals("���")) {
			UpdateDialog add=new UpdateDialog("���",new String[0]);
		}else if(arg0.getActionCommand().equals("�޸�")) {
			if(jt1.getSelectedRowCount()==1) {
			    String[] data=new String[jt1.getColumnCount()];
				for(int i=0;i<jt1.getColumnCount();i++) {
					data[i]=jt1.getValueAt(jt1.getSelectedRow(), i).toString();
					//System.out.println(i);
				}
				UpdateDialog add=new UpdateDialog("�޸�",data);
			}else {
				JOptionPane.showMessageDialog(this,"�������ٶ�ֻ��ѡ��һ��" );
			}
		}else if(arg0.getActionCommand().equals("ɾ��")) {
			int[] rows=jt1.getSelectedRows();
			if(rows.length==0) {
				JOptionPane.showMessageDialog(this,"����ѡ��һ��");
			}else {
				con=new ConDB();
				int j=0;
				for(int i=0;i<rows.length;i++) {
					j+=con.updateDB("delete from heroes where seating='"+jt1.getValueAt(rows[i], 0)+"'");
				}
				con.ConClose();
				JOptionPane.showMessageDialog(this,"�ɹ�ɾ��"+j+"������");
			}
		}else if(arg0.getActionCommand().equals("�����ļ�")) {
//			System.out.println("1");
//			JFileChooser jfc=new JFileChooser();
//			  jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
//		        jfc.showDialog(new JLabel(), "ѡ��");
//		        File file=jfc.getSelectedFile();
//		        if(file.isDirectory()){
//		            System.out.println("�ļ���:"+file.getAbsolutePath());
//		        }else if(file.isFile()){
//		            System.out.println("�ļ�:"+file.getAbsolutePath());
//		        }
//		        System.out.println(jfc.getSelectedFile().getName());
			SaveWindow sw=new SaveWindow();
		}
	}

}
