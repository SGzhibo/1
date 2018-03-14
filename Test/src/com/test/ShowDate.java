package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowDate implements Runnable{


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShowDate sd=new ShowDate();
		sd.run();


	}
	
	public ShowDate() {
		
	}



	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
								
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

			System.out.println(new Date());
			
		}
	}

}
