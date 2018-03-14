package com.test;

public class CalendarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int days=31;
		int firstDay=3;
		System.out.println("Mon Tue Wed Thu Fri Sat Sun");
		for(int i=0;i<firstDay-1;i++) {
			System.out.print("    ");
		}
		for(int i=1;i<days+1;i++) {
			if(i<10) {System.out.print(i+"   ");}
			else {System.out.print(i+"  ");}
			if((i+firstDay-1)%7==0) {
				System.out.println();
			}
		}
//		int Y=18;
//		int D=32;
//		int W = (Y-1) + (Y-1)/4 - (Y-1)/100 + (Y-1)/400 + D ;
//		System.out.println(W);

	}

}
