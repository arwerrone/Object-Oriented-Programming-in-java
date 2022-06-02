package workshop9;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class server extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea res = new JTextArea();
	private ArrayList<Client> clients = new ArrayList<>();
 
	public server() {
		setLayout(new BorderLayout());
		
		res.append("MultiThreadServer Started at " + DateTimeFormatter.ofPattern("EEEE MMMM d - kk:mm:ss z u").format(ZonedDateTime.now(ZoneId.systemDefault())) +"\n");
		
		res.setEditable(false);
		JScrollPane pane = new JScrollPane(res);
    
		add(pane, BorderLayout.CENTER);
		setTitle("Multi-thread Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
		setLocationRelativeTo(null);

		try {

			try (ServerSocket serverSocket = new ServerSocket(8000)) {
				while(true) {
					Socket socket = serverSocket.accept();
					Client newClient = new Client(socket);
					clients.add(newClient);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 
	class Client implements Runnable {
		private Socket socket;
		private PrintWriter toClient;
  
		public Client(Socket socket) {
			this.socket = socket;
			Thread thread = new Thread(this);
			thread.start();
			
		}
  
		public void run() {
			try {

				try (Scanner fromClient = new Scanner(socket.getInputStream())) {
					toClient = new PrintWriter(socket.getOutputStream());
					res.append("\nConnection from  " + socket + " at " + DateTimeFormatter.ofPattern("EEEE MMMM d - kk:mm:ss z u").format(ZonedDateTime.now(ZoneId.systemDefault())) + "\n");
					
					while(true) {
						String text = fromClient.nextLine();
						display(text, 's');
						for (Client newClient : clients) 
							newClient.display(text, 'c');
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {
				System.out.println("User exit");
			};
			
		}
  
		public void display(String text, char c) {
			
			if (c == 'c') {
				toClient.println("\n" + text);
				toClient.flush();
				
			} else if (c == 's') {
				res.append("\n" + text + "\n");
			}
		}
  
	}

	public static void main(String[] args) {
		new server();
	}
}