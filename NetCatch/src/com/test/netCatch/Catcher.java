package com.test.netCatch;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Catcher implements Runnable{

	private static String savepath="E:/netcatch/";
	private static List<String> alloverurl=new ArrayList<>();
	private static List<String> allwaiturl=new ArrayList<>();
	//private static int maxDepth=2;
	private static int maxThread=5;
	private static Object obj=new Object();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rooturl="http://v.hao123.baidu.com/dianying/";
		//workurl(strurl);
		allwaiturl.add(rooturl);
		for(int i=0;i<maxThread;i++) {
			new Thread(new Catcher()).start();
		}

	}	
	public static  void workurl(String rooturl) {
		BufferedReader br = null;
		InputStream is=null;
		URLConnection con=null;
		PrintWriter pw=null;
		if(!(alloverurl.contains(rooturl))) {
			System.out.println(Thread.currentThread().getName()+"正在准备爬取："+rooturl);
			try {
				URL url=new URL(rooturl);
				con=url.openConnection();				
				is=con.getInputStream();
				// System.out.println(con.getContentEncoding());
				br=new BufferedReader(new InputStreamReader(is,"utf-8"));
				String line=null;
				Pattern p=Pattern.compile("<.*href=.+>");
				pw=new PrintWriter(new File(savepath+System.currentTimeMillis()+".txt"));
				while((line=br.readLine())!=null) {
					if(MovieData.DataSelect(line)!=null) {
						pw.println(MovieData.DataSelect(line)+"\n");
					}					
					Matcher m=p.matcher(line);
					while(m.find()) {
						String href=m.group();
						href=href.substring(href.indexOf("href="));
						if(href.charAt(5)=='\"') {
							href=href.substring(6);
						}else {
							href=href.substring(5);
						}						
						int i=0;
//						if((i=href.indexOf("\""))!=-1) {
//							if(href.indexOf(" ")!=-1&&href.indexOf(" ")<i) {
//								i=href.indexOf(" ");
//								if(href.indexOf(">")!=-1&&href.indexOf(">")<i) {
//									i=href.indexOf(">");
//								}
//							}else {
//								if(href.indexOf(">")!=-1&&href.indexOf(">")<i) {
//									i=href.indexOf(">");
//								}
//							}
//							href=href.substring(0, i);
//						}
//						
						  try{
			                    href=href.substring(0,href.indexOf("\""));
			                }catch(Exception e){
			                    try{
			                        href=href.substring(0,href.indexOf(" "));
			                    }catch(Exception e1){
			                        href=href.substring(0,href.indexOf(">"));
			                    }
			                }
						if((href.startsWith("http:")||href.startsWith("https:"))&&!allwaiturl.contains(href)) {
							allwaiturl.add(href);
						}
						
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					pw.close();
					br.close();
					is.close();				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			alloverurl.add(rooturl);
			System.out.println(rooturl+"爬取完成   "+"完成数量："+alloverurl.size()+"  待爬取数量："+allwaiturl.size());
		}
		if(allwaiturl.size()>0) {
			String nexturl=allwaiturl.get(0);
			allwaiturl.remove(0);
			//workurl(nexturl);
		}
	}

	@Override
	public  void run() {
		// TODO Auto-generated method stub
		 while(true) {
			 synchronized(obj) {	
				 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 if(allwaiturl.size()>0) {
					 
					 String url=allwaiturl.get(0);
						workurl(url);
				 }
				 
			 }										
		}
		
	}

}
