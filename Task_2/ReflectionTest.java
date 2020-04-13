import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*;
import java.lang.reflect.Method;
import java.lang.reflect.Method;


public class ReflectionTest {


		public int[] localbubbleSort(int randValue, int sizeArray){
			int[] num = new int[sizeArray];
	        Random r = new Random();
	        for (int i = 0; i < num.length; i++) {
	                num[i] = r.nextInt(randValue);
	        } 
	        int j;
	        boolean flag = true; 
	        int temp; 

	        while (flag) {
	                flag = false; 
	                for (j = 0; j < num.length - 1; j++) {
	                        if (num[j] < num[j + 1]) 
	                        {
	                                temp = num[j]; 
	                                num[j] = num[j + 1];
	                                num[j + 1] = temp;
	                                flag = true; 
	                        }
	                }
	        }

	       return num;
		}


		public int [] bubbleSort(int randValue, int sizeArray) 
		{		
		Method toExecute; 
		Class<?>[] paramTypes = {int.class, int.class};
		Object[] paramValues = {randValue, sizeArray}; 
		int [] result = null; 

		try{

			String className = this.getClass().getName();
			Class cls = Class.forName(className);

			toExecute = cls.getDeclaredMethod("localbubbleSort", paramTypes);


			result = (int[]) toExecute.invoke(this, paramValues);


	       	if(result != null){
	       		System.out.println("Method was executed");

			}else{		
				System.out.println("Method was not executed");
				result = localbubbleSort(randValue, sizeArray);


			}


		}  catch (SecurityException se){
		} catch (NoSuchMethodException ns){
		}catch (Throwable th){
		}


		System.out.println(result);

		return result;
		}


	/*	public static void main(String[] args) {
			ReflectionTest test = new ReflectionTest();
			test.bubbleSort(1000000, 9999);
		}
	}
	
	*/
	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 8989 
		ServerSocket ss = new ServerSocket(8989); 
		ReflectionTest test = new ReflectionTest();
		test.bubbleSort(1000000, 8989);
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{ 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				
				System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Assigning new thread for this client"); 

				// create a new thread object 
				Thread t = new ClientHandler(s, dis, dos); 

				// Invoking the start() method 
				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 

// ClientHandler class 
class ClientHandler extends Thread 
{ 
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 
	

	// Constructor 
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
	} 

	@Override
	public void run() 
	{ 
		String received; 
		String toreturn; 
		while (true) 
		{ 
			try { 
				Scanner scn = new Scanner(System.in);

				// Ask user what he wants 
				dos.writeUTF("What do you want?[Date | Time]..\n"+ 
							"Type Exit to terminate connection."); 
				
				// receive the answer from client 
				received = dis.readUTF(); 
				System.out.println("Receive msg from client:: " + received );
				String tosend = scn.nextLine(); 
				dos.writeUTF(tosend);
				if(received.equals("Exit"))
				{ 
					System.out.println("Client " + this.s + " sends exit..."); 
					System.out.println("Closing this connection."); 
					this.s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
				
				// creating Date object 
				Date date = new Date(); 
				
				// write on output stream based on the 
				// answer from the client 
				switch (received) { 
				
					case "Date" : 
						toreturn = fordate.format(date); 
						dos.writeUTF(toreturn); 
						break; 
						
					case "Time" : 
						toreturn = fortime.format(date); 
						dos.writeUTF(toreturn); 
						break; 
						
					default: 
						dos.writeUTF("Invalid input"); 
						break; 
				} 
				scn.close();
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		
		try
		{ 
			// closing resources 
			this.dis.close(); 
			this.dos.close(); 
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
		
	} 
} 
