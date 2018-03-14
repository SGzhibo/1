package com.test;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int green=30;
		int red=33;
		int yellow=3;
		int count=0;
		for(int i=0;i<4;i++) {
			if(i==3) {i=0;}
			if(i==0) {System.out.println("ºìµÆ");Thread.sleep(3*1000);}
			if(i==1) {System.out.println("»ÆµÆ");Thread.sleep(3*1000);}
			if(i==2) {System.out.println("ÂÌµÆ");Thread.sleep(3*1000);}
			count++;
			if(count==100) {break;}
		}

	}

}
