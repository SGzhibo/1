package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ServerSocket ss=new ServerSocket(8080);
		Socket s=ss.accept();
		
		OutputStream os=s.getOutputStream();
		BufferedReader br=new BufferedReader(new FileReader("E://helllo.html"));
		String buf="";
		while((buf=br.readLine())!=null) {
			os.write(buf.getBytes());
		}

		br.close();
		os.close();
		s.close();
	}

}
