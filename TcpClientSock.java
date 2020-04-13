import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClientSock {
	
	public static void main(String args[]) {
		
		Socket client = new Socket();
		try {
			client.connect(new  InetSocketAddress ("127.0.0.1",8888));
		
			
			 DataOutputStream out = new DataOutputStream(client.getOutputStream());
			 out.writeUTF(" Mohan \n");
			 
			 DataInputStream in = new DataInputStream(client.getInputStream());
			  
			  String msg = in.readUTF(); System.out.println("Receive msg: " + msg );
			  
			  out.close();
			  in.close();
			  client.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}