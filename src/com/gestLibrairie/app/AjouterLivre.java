package com.gestLibrairie.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;



public class AjouterLivre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel codLivLab,  titreLab, auteurLab, editeurLab, seuilsecLab, distriLab;
	private JTextField Cod_Liv, Titre_Liv, Auteurs, Editeurs, Seuil_sec;
	private static JComboBox nomDist ;
	private static  String  Titre_Liv1=null, Auteurs1=null, Editeurs1=null ;
	private static	int Seuil_sec1=0 ;
	private static	int Cod_Liv1, Code_Dist1;
	private static String rq1;
	private int result;
	
	static Logger log = Logger.getLogger(AjouterLivre.class);
	
	/**
	 * @wbp.parser.constructor
	 */
	public AjouterLivre(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}

	
	public AjouterLivre(int Cod_Liv, String Titre_Liv, String Auteurs, String Editeurs, int Seuil_sec) {
		// TODO Auto-generated constructor stub
		Cod_Liv1=Cod_Liv;
		Titre_Liv1 = Titre_Liv;
		Auteurs1 = Auteurs;
		Editeurs1 = Editeurs;
		Seuil_sec1=Seuil_sec;
			
	}

	public AjouterLivre(int Cod_Liv, int Code_Dist ) {
		// TODO Auto-generated constructor stub
		Cod_Liv1=Cod_Liv;
		Code_Dist1=Code_Dist;
			
	}
	
	
	public static int getCod_Liv1() {
		return Cod_Liv1;
	}
	
	public static String getTitre_Liv1() {
		return Titre_Liv1;
	}


	public static String getAuteurs1() {
		return Auteurs1;
	}


	public static String getEditeurs1() {
		return Editeurs1;
	}


	public static int getSeuil_sec1() {
		return Seuil_sec1;
	}
	
	
	
	
	public static int getCode_Dist1() {
		
		rq1 =  "SELECT Code_Dist  FROM distributeur WHERE Rs_Dist ='"+ nomDist.getSelectedItem()+"'";
		
		 try {
				Statement state3 = Connect.getInstance().createStatement();

		  ResultSet res3 = state3.executeQuery(rq1);
		  
		  res3.next();
		  Code_Dist1 = res3.getInt(1);
		  //System.out.println(Code_Dist1);
		     
		 }catch(SQLException e ){}
		 
		
		return Code_Dist1;
	}


	


	private void information(){
		
		// Code Livre
		JPanel panCodeLivre = new JPanel();
		panCodeLivre.setBackground(Color.white);
		panCodeLivre.setPreferredSize(new Dimension(240, 60));
		panCodeLivre.setBorder(BorderFactory.createTitledBorder("Code du Livre"));
		codLivLab = new JLabel("Code : ");
		Cod_Liv = new JTextField(getCod_Liv1());
		Cod_Liv.setPreferredSize(new Dimension(90, 25));
		panCodeLivre.add(codLivLab);
		panCodeLivre.add(Cod_Liv);
		
		
		
		// Titre Livre
	JPanel panTitreLivre = new JPanel();
	panTitreLivre.setBackground(Color.white);
	panTitreLivre.setPreferredSize(new Dimension(240, 60));
	panTitreLivre.setBorder(BorderFactory.createTitledBorder("Titre du Livre"));
	titreLab = new JLabel("Titre : ");
	Titre_Liv = new JTextField(getTitre_Liv1());
	Titre_Liv.setPreferredSize(new Dimension(90, 25));
	panTitreLivre.add(titreLab);
	panTitreLivre.add(Titre_Liv);
	
	// L'Auteur
	JPanel panAuteur = new JPanel();
	panAuteur.setBackground(Color.white);
	panAuteur.setPreferredSize(new Dimension(240, 60));
	panAuteur.setBorder(BorderFactory.createTitledBorder("Auteur du Livre"));
	auteurLab = new JLabel(" Auteur : ");
	Auteurs = new JTextField(getAuteurs1());
	Auteurs.setPreferredSize(new Dimension(90, 25));
	panAuteur.add(auteurLab);
	panAuteur.add(Auteurs);
	
	//L'Editeur
	JPanel panEditeur = new JPanel();
	panEditeur.setBackground(Color.white);
	panEditeur.setPreferredSize(new Dimension(240, 60));
	panEditeur.setBorder(BorderFactory.createTitledBorder("Editeur Du Livre"));
	editeurLab = new JLabel("Editeur : ");
	Editeurs = new JTextField(getEditeurs1());
	Editeurs.setPreferredSize(new Dimension(90, 25));
	panEditeur.add(editeurLab);
	panEditeur.add(Editeurs);

	
	// Le Seuil de Securité
	
	JPanel panSeuilSec = new JPanel();
	panSeuilSec.setBackground(Color.white);
	panSeuilSec.setPreferredSize(new Dimension(240, 60));
	panSeuilSec.setBorder(BorderFactory.createTitledBorder("Seuil de Securite du Livre"));
	seuilsecLab = new JLabel("Seuil de Securite : ");
	Seuil_sec= new JTextField(getSeuil_sec1());
	Seuil_sec.setPreferredSize(new Dimension(90, 25));
	panSeuilSec.add(seuilsecLab);
	panSeuilSec.add(Seuil_sec);
	
	// Disibuteur
	
	JPanel panDistri = new JPanel();
	panDistri.setBackground(Color.white);
	panDistri.setPreferredSize(new Dimension(240, 60));
	panDistri.setBorder(BorderFactory.createTitledBorder("Distributeur du Livre"));
	nomDist = new JComboBox();
	nomDist.addItem("Choisir");

	
	
	String requete = "SELECT Rs_Dist FROM distributeur ORDER BY Rs_Dist ";

    try {
    	
    	
    	
		Statement state = Connect.getInstance().createStatement();

     ResultSet res = state.executeQuery(requete); 
       while(res.next()) {
    	   nomDist.addItem(res.getString("Rs_Dist"));

                         }
        }catch(SQLException e ){}
	
    
    distriLab = new JLabel("Distributeur : ");
    panDistri.add(distriLab);
    panDistri.add(nomDist);
    
 
 
	
	JPanel contentL = new JPanel();
	
	contentL.setBackground(Color.white);
	contentL.add(panCodeLivre);
	contentL.add(panTitreLivre);
	contentL.add(panAuteur);
	contentL.add(panEditeur);
	contentL.add(panSeuilSec);
	contentL.add(panDistri);
	
	
	

	
	JPanel controlL = new JPanel();
	JButton okBouton = new JButton("Valider");
	okBouton.setIcon(new ImageIcon (AjouterLivre.class.getResource("/valider2.png")));
	
	
	okBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {				

		
			try {
			
				
				Statement state = Connect.getInstance()
				.createStatement();
				
			String requete2 = "INSERT INTO livre(Cod_Liv, Titre_Liv, Auteurs, Editeurs, Seuil_sec ) VALUES ('"+Cod_Liv.getText()+"', '"+Titre_Liv.getText()+"', '"+Auteurs.getText()+"', '"+Editeurs.getText()+"','"+ Seuil_sec.getText()+"')";
			
			log.debug(requete2.toString());
			
			int resultat = state.executeUpdate(requete2);
						
			Cod_Liv1=0; Titre_Liv1=null; Auteurs1=null; Editeurs1=null; Seuil_sec1=0; 
				 
				 AjouterLivre al = new AjouterLivre(Cod_Liv1,Titre_Liv1, Auteurs1, Editeurs1, Seuil_sec1);
				 JOptionPane confirmation = new JOptionPane();
					confirmation.showMessageDialog(null, "Le livre a été ajouté ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);
					log.info("Nouveau livre ajouté à la BDD");
					
		
		 state.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Erreur d'insertion",
						"Erreur BDD", JOptionPane.ERROR_MESSAGE);
				log.error("Erreur lors de l insertion pour cause" + e1.getMessage());
			}
			
			result = new Integer(getCode_Dist1());
			System.out.println(result);
			
			try {
			
				
				Statement state2 = Connect.getInstance()
				.createStatement();
				
			
			String requete2 = "INSERT INTO distribuer(Code_Dist, Cod_Liv) VALUES ('"+result+"', '"+Cod_Liv.getText()+"')";	 
			
			
			int resultat2 = state2.executeUpdate(requete2);
						
			Cod_Liv1=0; Code_Dist1=0; 
				 
				 AjouterLivre al2 = new AjouterLivre(Cod_Liv1, Code_Dist1);
				 

					
		
		 state2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	controlL.add(okBouton);
	controlL.add(cancelBouton);
	JLabel icon = new JLabel(new ImageIcon (AjouterLivre.class.getResource("/livre.jpg")));
	JPanel panIcon = new JPanel();
	panIcon.setBackground(Color.white);
	panIcon.setLayout(new BorderLayout());
	panIcon.add(icon);

	this.getContentPane().add(panIcon, BorderLayout.WEST);
	this.getContentPane().add(contentL, BorderLayout.CENTER);
	this.getContentPane().add(controlL, BorderLayout.SOUTH);
	

}
	
	
	
}
