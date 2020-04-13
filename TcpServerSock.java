import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerSock {
	public static void main(String args[]) {
		
		try {
			ServerSocket server=new ServerSocket(); // create a new TCP server socket
			
			server.bind( new InetSocketAddress ("127.0.0.1",8888));  //binds to IP and port. Now socket is in the listen state
			System.out.println ("Socket binds to "+server.getLocalSocketAddress()+ " Waiting for clients");
			
            Socket socket = server.accept(); //accepts client request
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			
			DataInputStream in = new DataInputStream(socket.getInputStream()); //reading data from the incoming channel
			String msg = in.readUTF(); 
			System.out.println("Receive msg from client " + msg );
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());//writing data to the client
			out.writeUTF("Thank you for connecting " + msg+ " , I am "+ server.getLocalSocketAddress());
			in.close();
			out.close(); 
			socket.close();
			server.close();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			
}