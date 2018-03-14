package com.paintpad.view;

import java.awt.Canvas;
import java.awt.Color;

public class MyCanvas extends Canvas {	
		

	public MyCanvas() {
				
		
		this.setBounds(50, 50, MyFrame.x, MyFrame.y);
		this.setBackground(Color.white);		
		this.setVisible(true);
	}
}
