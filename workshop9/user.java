package workshop9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class user extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea msg = new JTextArea();
	private PrintWriter toServer;
	private JTextField newName = new JTextField();
	private JTextField newMsg = new JTextField();
	
	class newMessage implements Runnable {
		  private Socket socket;
	
		  public newMessage(Socket s) {
		   socket = s;
		   Thread thread = new Thread(this);
		   thread.start();
		  }
		  
		  public void run() {
			  try {
				  try (Scanner getText = new Scanner(socket.getInputStream())) {
					while(true) {
						  msg.append(getText.nextLine() + "\n");
					  }
				}
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
			  catch (NoSuchElementException e) {
				  System.out.println("Server is closed");
			  }

		  }
		  
	}
	 
	 public user() throws ConnectException {
		 try {
			 Socket socket = new Socket("localhost", 8000); 
			 if (socket.isConnected()) 
				 msg.append("Connected succesfully, running on: "+ socket + "\n");
			 toServer = new PrintWriter(socket.getOutputStream());
			 new newMessage(socket);
		 } catch (IOException e) {
			 msg.append("Problem to connect to server / server not found!\n");
		 } 		 
		 
		 setLayout(new BorderLayout(10, 10));
		 JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		 mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		 msg.setEditable(false);
		 
		 JScrollPane pane = new JScrollPane(msg);
		 mainPanel.add(pane, BorderLayout.CENTER);
		 
		 JButton btn = new JButton("Send");
		 btn.setBounds(10,10,10,10);
		 
		 JPanel panel = new JPanel(new GridLayout(3, 5, 5, 5));
		 
		 JPanel name = new JPanel(new BorderLayout(10, 10));
		 name.add(new JLabel("Name "), BorderLayout.WEST);     
		 name.add(newName, BorderLayout.CENTER);
		 
		 JPanel text = new JPanel(new BorderLayout(5, 5));
		 text.add(new JLabel("Text "), BorderLayout.WEST);
		 text.add(newMsg, BorderLayout.CENTER);
		 
		 panel.add(name);
		 panel.add(text);
		 panel.add(btn);
		 
		 mainPanel.add(panel, BorderLayout.SOUTH);
		 add(mainPanel, BorderLayout.CENTER);
		 setVisible(true);
		 setSize(600, 400);
		 setTitle("Client");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLocationRelativeTo(null);
		 
		 ActionListener sendMsg = new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				 try {
					 
					 if (!newMsg.getText().equals("") && !newName.getText().equals("") ) {
						 toServer.println(newName.getText() + ": " + newMsg.getText());
						 newMsg.setText("");
						 toServer.flush();
						 newMsg.requestFocus();
					 }
					 else {
						 JOptionPane.showMessageDialog(null, "Please put a Name and a Text", "Warning", JOptionPane.WARNING_MESSAGE);
					 }
				 } catch (NullPointerException n) {JOptionPane.showMessageDialog(null, "Server is not running, client closing..."); System.exit(0);};
			 }
			 
		 };
		 btn.addActionListener(sendMsg);
		 newMsg.addActionListener(sendMsg);
		  	
	 }
 

	 public static void main(String[] args) throws ConnectException{
		 new user();
	 }
}