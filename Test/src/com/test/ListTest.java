package com.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	
	List l;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListTest lt=new ListTest();

	}
	
	public ListTest() {
		this.l=new ArrayList();
		int i = 0,j=1;
		l.add(i);
		l.add(j);
		System.out.println(l.get(0));
	}

}
