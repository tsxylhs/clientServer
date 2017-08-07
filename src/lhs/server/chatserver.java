package lhs.server;



import java.io.*;
import java.net.*;
import java.util.ArrayList;

import lhs.dao.userDao;
public class chatserver {
static ArrayList<Socket> Socket_Array = new  ArrayList<Socket>();

public static void main(String args[])throws Exception{

ServerSocket server = new ServerSocket(8520);
while(true){
Socket socket = server.accept();
Socket_Array.add(socket);
new Thread(new ProcessSessions(socket)).start();
}
}
private static class ProcessSessions implements Runnable  {
BufferedReader in;
PrintWriter out;
Socket socket;
public ProcessSessions(Socket socket){
this.socket = socket;
}
public void run()  {
try {
 in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
 
 while(true){
 String s = in.readLine();
System.out.println(s);
String ss=s.substring(5);
System.out.println(ss);
if(ss.equals("end")){
out.println("c u");
break;}
else {
for(int i=0;i<Socket_Array.size();i++){
	System.out.println(i);
Socket tempSocket = Socket_Array.get(i);
int port =tempSocket.getPort();
int id=Integer.parseInt(s.substring(0, 5));
userDao user=new userDao();
if(user.updataflagport(port, id)==1){
	out = new PrintWriter(tempSocket.getOutputStream(),true);
	out.println("你发的消息是:"+s+" 我们已经收到你是正常用户");
}
else{
	out = new PrintWriter(tempSocket.getOutputStream(),true);
	out.println("你发的消息是 :"+s+"你是非法用户");
}
}
}
}

} catch (UnsupportedEncodingException e) {
System.out.println("UnsupportedEncodingException e");
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} 



}

}
}