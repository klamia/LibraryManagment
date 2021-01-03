package com.gestLibrairie.app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import org.apache.log4j.Logger;

import java.awt.Font;


public class Accueil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean ok =false;
	
	static Logger log = Logger.getLogger(Accueil.class);
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnConfiguration = new JMenu("Param\u00E8tres");
	private final JMenuItem mntmBaseDeDonne = new JMenuItem("Base de donn\u00E9e");
	private final JMenuItem mntmFermer = new JMenuItem("Fermer");
	private JMenu mnFichier = new JMenu("Fichier");
	private JMenu mntmLivre = new JMenu("Livre");
	private JMenu mntmDistributeur = new JMenu("Distributeur");
	private JMenu mnMouvement = new JMenu("Mouvement");
	private final JMenuItem mntmEntre = new JMenuItem("Entr\u00E9e");
	private final JMenuItem mntmAchat = new JMenuItem("Achat");
	private final JMenuItem mntmBonDeCommande = new JMenuItem("Bon de Commande");
	private final JMenuItem mntmSortie = new JMenuItem("Sortie");
	private final JMenuItem mntmVente = new JMenuItem("Vente");
	private final JMenuItem mntmTicketDeCaisse = new JMenuItem("Ticket de Caisse");
	private JMenu mnEdition = new JMenu("Edition");
	private final JMenuItem mntmEtatDuStock = new JMenuItem("Etat du Stock");
	private JMenu mnAide = new JMenu("Aide");
	private final JMenuItem mntmAPropos = new JMenuItem("A propos");
	
	private final JMenuItem ajouterDist = new JMenuItem("Ajouter Distributeur");
	private final JMenuItem modifierDist = new JMenuItem("Modifier Distributeur");
	private final JMenuItem consulterDist = new JMenuItem("Consulter Distributeur");
	
	
	private final JMenuItem ajouterLivre = new JMenuItem("Ajouter Livre");
	private final JMenuItem mntmBonDeCommande_1 = new JMenuItem("Bon de Commande");
	private final JMenuItem mntmModifierLivre = new JMenuItem("Modifier Livre");
	private final JMenuItem mntmSupprimerDistributeur = new JMenuItem("Supprimer Distributeur");
	private final JMenuItem mntmSupprimerLivre = new JMenuItem("Supprimer Livre");
	private final JMenuItem mntmConsulterLivre = new JMenuItem("Consulter Livre");
	private final JMenu mnStatistiques = new JMenu("Statistiques");
	private final JMenuItem mntmNombreLivrePar = new JMenuItem("Nombre Livre par Distributeur");
	
	
	
	public Accueil(boolean ok){
		this.ok=ok;
		
	}
	
	public Accueil(){
		
		this.setTitle("Librairie El Maarifa");
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		menuBar.setMinimumSize(new Dimension(0, 2));
		menuBar.setMaximumSize(new Dimension(0, 2));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		setJMenuBar(menuBar);
		
		
		menuBar.add(mnConfiguration);
		mntmBaseDeDonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bdd zd = new Bdd(null, "Paramètres : Informations base de donnée", true);
			}
		});
		
		
		mntmBaseDeDonne.setName("BaseDeDonnee1");
		mnConfiguration.add(mntmBaseDeDonne);
		mntmFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		mntmFermer.setName("Fermer1");
		mnConfiguration.add(mntmFermer);
		
		
		mnFichier.setName("Fichier1");
		menuBar.add(mnFichier);
		
		
		mntmLivre.setName("Livre1");
		mnFichier.add(mntmLivre);
		
		
		mntmDistributeur.setName("Distributeur1");
		mnFichier.add(mntmDistributeur);
		
		
		mnMouvement.setName("Mouvements1");
		menuBar.add(mnMouvement);
		
		
		mntmEntre.setName("Entre1");
		mnMouvement.add(mntmEntre);
		
		
		mntmAchat.setName("Achat1");
		mnMouvement.add(mntmAchat);
		
		
		mntmBonDeCommande.setName("Bondecommande1");
		mnMouvement.add(mntmBonDeCommande);
		
		
		mntmSortie.setName("Sortie1");
		mnMouvement.add(mntmSortie);
		
		
		mntmVente.setName("Vents1");
		mnMouvement.add(mntmVente);
		
		
		mntmTicketDeCaisse.setName("Ticketdecaisse");
		mnMouvement.add(mntmTicketDeCaisse);
		
		
		mnEdition.setName("Edition1");
		menuBar.add(mnEdition);
		
		
		mntmEtatDuStock.setName("EtatduStock1");
		mnEdition.add(mntmEtatDuStock);
		
		mnEdition.add(mntmBonDeCommande_1);
		
		menuBar.add(mnStatistiques);
		mntmNombreLivrePar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Graphe g = new Graphe();
				g.setVisible(true);
			}
		});
		
		mnStatistiques.add(mntmNombreLivrePar);
		
		
		mnAide.setName("Aide1");
		menuBar.add(mnAide);
		
		
		mntmAPropos.setName("Apropos1");
		mnAide.add(mntmAPropos);
		
		mntmDistributeur.add(ajouterDist);
		mntmDistributeur.add(modifierDist);
		mntmSupprimerDistributeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.debug("Afficher la confirmation de suppression");
					SupprimerDistributeur s = new SupprimerDistributeur();
				} catch (SQLException e4) {
					log.error("Erreur SQL :" + e4.getMessage());
					JOptionPane.showMessageDialog(null, e4.getMessage(),
							"Erreur BDD", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		mntmDistributeur.add(mntmSupprimerDistributeur);
		mntmDistributeur.add(consulterDist);
		
		
		mntmLivre.add(ajouterLivre);
		
		mntmLivre.add(mntmModifierLivre);
		
		mntmLivre.add(mntmSupprimerLivre);
		mntmConsulterLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsulterLivre c1 = new ConsulterLivre();
				c1.setVisible(true);
			}
		});
		
		mntmLivre.add(mntmConsulterLivre);
		
		
		ajouterDist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				log.debug("Afficher la fenêtre d insertion");
				AjouterDistributeur ad = new AjouterDistributeur(null, "Ajouter un Distributeur", true);
			}				
		});
		
		
		modifierDist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					log.debug("Afficher la fenêtre de MAJ");
					ModifierDistributeur m = new ModifierDistributeur();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}				
		});

		
		consulterDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.debug("Afficher la fenêtre de consultation");
				ConsulterDistri c = new ConsulterDistri();
				c.setVisible(true);
			}
		});
		
	// Livre 	
		ajouterLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.debug("Afficher la fenêtre d insertion");
				AjouterLivre al = new AjouterLivre(null, "Ajouter un Livre", true);
			}
		});
		
		
		mntmTicketDeCaisse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			TicketDeCaisse2 tc = new TicketDeCaisse2(null, "Ticket de Caisee", true);
			}
		});
		
		
		this.setVisible(true);
		
	}
	
	
	
	public static void main(String[] args) throws UnknownHostException, MalformedURLException, RemoteException, NotBoundException, InterruptedException{
		
		log.info("Chargement de l application ...");
		
		new Bdd(null, "Etape 1/2 : Configuration de la base de donnée", true);
		
		new Client(null, "Etape 2/2 : Informations utilisateur", true);
		if(true){
	Chargement wind = new Chargement();
		wind.setVisible(true);
		Thread.sleep(4500);
		wind.setVisible(false);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Accueil();
				log.info("Fin de chargement");
			}
		});
		}
	}
	  
	 }
	

