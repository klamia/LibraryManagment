package com.gestLibrairie.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;





public class AjouterDistributeur extends JDialog {

	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(AjouterDistributeur.class);

	private JLabel  Raison_SocialLab, AdresseLab, VilleLab, WilayaLab, TelephoneLab, Code_PostalLab;
	private JTextField Rs_Dist, Rue, Ville, Tel_Dist, Cposte_Dist ;
	private static String Rs_Dist1=null, Rue1=null, Ville1=null, Tel_Dist1=null, Cposte_Dist1=null ;
	private static int Code_Dist; 
	private JComboBox Wilaya;
	
	/**
	 * @wbp.parser.constructor
	 */
	public AjouterDistributeur(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	
	public AjouterDistributeur(String Rue, String Ville, String Tel_Dist, String Cpost_Dist, int Code_Dist) {
		// TODO Auto-generated constructor stub
		
		Rue1 = Rue;
		Ville1 = Ville;
		Tel_Dist1=Tel_Dist;
		Cposte_Dist1=Cpost_Dist;
		this.Code_Dist=Code_Dist;
	}
	
	
	
	
	public AjouterDistributeur(String Rs_Dist, String Rue, String Ville, String Tel_Dist, String Cpost_Dist) {
		// TODO Auto-generated constructor stub
		Rs_Dist1 = Rs_Dist;
		Rue1 = Rue;
		Ville1 = Ville;
		
		Tel_Dist1=Tel_Dist;
		Cposte_Dist1=Cpost_Dist;
		
	}



	public static String getRs_Dist1() {
		return Rs_Dist1;
	}



	public static String getRue1() {
		return Rue1;
	}



	public static String getVille1() {
		return Ville1;
	}




	public static String getTel_Dist1() {
		return Tel_Dist1;
	}



	public static String getCposte_Dist1() {
		return Cposte_Dist1;
	}


	
	public static int getCode_Dist() {
		return Code_Dist;
	}


	private void information(){

		
//	System.out.println(getMarque1());
		// La Raison Social
		JPanel panRaison_Social = new JPanel();
		panRaison_Social.setBackground(Color.white);
		panRaison_Social.setPreferredSize(new Dimension(220, 60));
		panRaison_Social.setBorder(BorderFactory.createTitledBorder("Raison Social du Distributeur"));
		Raison_SocialLab = new JLabel("Raison Social : ");
		Rs_Dist = new JTextField(getRs_Dist1());
		Rs_Dist.setPreferredSize(new Dimension(90, 25));
		panRaison_Social.add(Raison_SocialLab);
		panRaison_Social.add(Rs_Dist);
		
		//L'Adresse 
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(220, 60));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse du Distributeur"));
		AdresseLab = new JLabel(" Adresse : ");
		Rue = new JTextField(getRue1());
		Rue.setPreferredSize(new Dimension(90, 25));
		panAdresse.add(AdresseLab);
		panAdresse.add(Rue);
		
		//La Ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(220, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville du Distributeur"));
		VilleLab = new JLabel("Ville : ");
		Ville = new JTextField(getVille1());
		Ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(VilleLab);
		panVille.add(Ville);

		
		// La Wilaya
		JPanel panWilaya = new JPanel();
		panWilaya.setBackground(Color.white);
		panWilaya.setPreferredSize(new Dimension(220, 60));
		panWilaya.setBorder(BorderFactory.createTitledBorder("Wilaya du Distributeur"));
		Wilaya = new JComboBox();
		Wilaya.addItem("Choisir");

        String requete = "SELECT Nom_W FROM wilaya ORDER BY Code_W ";

        try {
    		Statement state = Connect.getInstance().createStatement();

         ResultSet res = state.executeQuery(requete); 
           while(res.next()) {
        	   Wilaya.addItem(res.getString("Nom_W"));
   
                             }
            }catch(SQLException e ){}
		
        
        WilayaLab = new JLabel("Wilaya : ");
		panWilaya.add(WilayaLab);
		panWilaya.add(Wilaya);
		
		
		// Le téléphone 
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(220, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone du Distributeur"));
		TelephoneLab = new JLabel("Telephone : ");
		Tel_Dist= new JTextField(getTel_Dist1());
		Tel_Dist.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(TelephoneLab);
		panTelephone.add(Tel_Dist);
		
		
		//Le Code Postal
		JPanel panCodePostal = new JPanel();
		panCodePostal.setBackground(Color.white);
		panCodePostal.setPreferredSize(new Dimension(220, 60));
		panCodePostal.setBorder(BorderFactory.createTitledBorder("Code Postal du Distributeur"));
		Code_PostalLab = new JLabel("Code Postal : ");
		Cposte_Dist= new JTextField(getCposte_Dist1());
		Cposte_Dist.setPreferredSize(new Dimension(90, 25));
		panCodePostal.add(Code_PostalLab);
		panCodePostal.add(Cposte_Dist);
		
		
		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panRaison_Social);
		content.add(panAdresse);
		content.add(panVille);
		content.add(panWilaya);
		content.add(panTelephone);
		content.add(panCodePostal);
		
	
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		okBouton.setIcon(new ImageIcon (AjouterDistributeur.class.getResource("/valider2.png")));
		
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				

			
				try {
				
					
					Statement state = Connect.getInstance()
					.createStatement();
				String requete2 = "INSERT INTO distributeur(Rs_Dist, Rue, Ville, Wilaya, Tel_Dist, Cpost_Dist) VALUES ('"+Rs_Dist.getText()+"', '"+Rue.getText()+"', '"+Ville.getText()+"','"+Wilaya.getSelectedItem()+"','"+Tel_Dist.getText()+"', '"+Cposte_Dist.getText()+"')";
					 log.debug(requete2.toString());
				int resultat = state.executeUpdate(requete2);
							
					 Rs_Dist1=null; Rue1=null; Ville1=null; Tel_Dist1=null; Cposte_Dist1=null;
					 AjouterDistributeur a = new AjouterDistributeur(Rs_Dist1,Rue1,Ville1,Tel_Dist1,Cposte_Dist1);
					 log.info("Nouveau Distributeur ajouté à la BDD");
					 JOptionPane confirmation = new JOptionPane();
						confirmation.showMessageDialog(null, "Le distributeur a été ajouté ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);
						
						
			
			 state.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erreur d'insertion",
							"Erreur BDD", JOptionPane.ERROR_MESSAGE);
					log.error("Erreur lors de l insertion pour cause" + e1.getMessage());
				}
			 catch (Exception e) {
				e.printStackTrace();
			}
			 setVisible(false);
				
			   				
					}
		
					
			}
		
			
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
		JLabel icon = new JLabel(new ImageIcon (AjouterDistributeur.class.getResource("/distributeur.jpg")));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}
	
	
}
