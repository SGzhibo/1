package com.paintpad.view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	public static int x=500;
	public static int y=400;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MyFrame();
	}
	
	public MyFrame() {
		
		this.setForeground(Color.black);
		this.setTitle("PaintPad");
		this.setBounds(500,100,574,460);
		this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-this.getSize().getWidth()/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-this.getSize().getHeight()/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		MyCanvas canvas=new MyCanvas();
		this.getContentPane().add(canvas);
	}

}
