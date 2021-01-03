package com.gestLibrairie.app;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Inscription extends JDialog {


	private JLabel loginLabel, passLabel, pass2Label, mailLabel;
	private JTextField login,mail;
	private JPasswordField  pass, pass2;
	public static String adr;
	public static InetAddress ip;
	public Inscription(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	
	public Inscription(String adr){
		this.adr =adr;
	}
	private void information(){

		
	
		//Le login
		JPanel panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(220, 60));
		panLogin.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		loginLabel = new JLabel("Login : ");
		login = new JTextField();
		login.setPreferredSize(new Dimension(90, 25));
		panLogin.add(loginLabel);
		panLogin.add(login);
		
		//Le pass
		JPanel panPass = new JPanel();
		panPass.setBackground(Color.white);
		panPass.setPreferredSize(new Dimension(220, 60));
		panPass.setBorder(BorderFactory.createTitledBorder("Mot de passe"));
		passLabel = new JLabel("Passe : ");
		pass = new JPasswordField();
		pass.setPreferredSize(new Dimension(90, 25));
		panPass.add(passLabel);
		panPass.add(pass);
		
		//Le pass 2
		JPanel panPass2 = new JPanel();
		panPass2.setBackground(Color.white);
		panPass2.setPreferredSize(new Dimension(220, 60));
		panPass2.setBorder(BorderFactory.createTitledBorder("Confirmer Mot de passe"));
		pass2Label = new JLabel("Passe : ");
		pass2 = new JPasswordField();
		pass2.setPreferredSize(new Dimension(90, 25));
		panPass2.add(pass2Label);
		panPass2.add(pass2);

		/* Le mail
		JPanel panmail = new JPanel();
		panmail.setBackground(Color.white);
		panmail.setPreferredSize(new Dimension(220, 60));
		panmail.setBorder(BorderFactory.createTitledBorder("Adresse email"));
		mailLabel = new JLabel("Mail : ");
		mail = new JTextField();
		mail.setPreferredSize(new Dimension(90, 25));
		panmail.add(mailLabel);
		panmail.add(mail);*/
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panPass);
		content.add(panPass2);
		//content.add(panmail);

		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				

				
				if(pass.getText().equals(pass2.getText())){
				
					
					
					try {
						Interfaces authentification = (Interfaces) Naming.lookup("//"+adr+":1099/Authentification");
					
					if(authentification.ajouter_utilisateur(login.getText(), pass.getText())==true) {
		        
		        JOptionPane confirmation = new JOptionPane();
				ImageIcon img = new ImageIcon("succes.png");
				confirmation.showMessageDialog(null, "Vous �tes inscris !", "Informations correctes", JOptionPane.INFORMATION_MESSAGE, img);
				
				setVisible(false);
			
					}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}else {
					ImageIcon img = new ImageIcon("erreur.png");
					JOptionPane erreur = new JOptionPane();
					erreur.showMessageDialog(null, "Vos deux mots de passes ne correspondent pas !", "Inscription �chou�", JOptionPane.ERROR_MESSAGE,img);
					
				}
				
			}}
		
			
		);
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					setVisible(false);
				}catch(NullPointerException n) {
					System.exit(0);	
					
				}
			}			
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		JLabel icon = new JLabel(new ImageIcon("admin.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.EAST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}

}
