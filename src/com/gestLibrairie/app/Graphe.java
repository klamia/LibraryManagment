package com.gestLibrairie.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;



public class Graphe extends JFrame {
	
	static Logger log = Logger.getLogger(Graphe.class);
	
	Connection connexion;
	JFreeChart pieChart = null;
	
	public Graphe() {
		setSize(new Dimension(400, 300));
		setTitle("Statistiques Livre");
		setLocationRelativeTo(null);
		
		log.info("Database connection details");
		
		try {
			
			log.info("Ouverture du fichier de paramétrage");
	        Properties params = new Properties();
		    params.load(Graphe.class.getResourceAsStream("jdbc-config.properties"));
		    
		    log.debug("Chargement du pilote");	
			Class.forName(params.getProperty("nomDuDriver"));
			
			log.info("Ouverture de la connexion");
			connexion = DriverManager.getConnection(
			params.getProperty("ChaineDeConnexion"));
			
			
			String QUERY_NOMBRE_LIVRES_PAR_DISTRIBUTEUR = 
					"SELECT distributeur.Rs_Dist, count(*) AS qte_Livre_Distribue FROM distributeur, distribuer, livre WHERE distributeur.Code_Dist=distribuer.Code_Dist and distribuer.Cod_Liv=livre.Cod_Liv GROUP BY distributeur.Rs_Dist";
	      
			log.debug(QUERY_NOMBRE_LIVRES_PAR_DISTRIBUTEUR.toString());
			
			/*
	      Statement statement;
		
			statement = connexion.createStatement( );
		
	     
			ResultSet resultSet = statement.executeQuery(QUERY_NOMBRE_LIVRES_PAR_DISTRIBUTEUR) ;
			
			*/
			
			final String TITRE_LIVRE_PAR_DISTRIBUTER = "Livres Par Distributeur";
			
			
			log.info("Créer un DataSet a partir de la requette de la BDD");
			PieDataset pieDataset =
		            new JDBCPieDataset( connexion, QUERY_NOMBRE_LIVRES_PAR_DISTRIBUTEUR);
			
			log.info("Créer un graphe");
			pieChart =
		            ChartFactory.createPieChart( TITRE_LIVRE_PAR_DISTRIBUTER, // chart title
		                                         pieDataset,
		                                         true,      // legend displayed
		                                         true,      // tooltips displayed
		                                         false );   // no URLs
			
			log.info("Afficher le graphe");
			ChartPanel panel = new ChartPanel(pieChart);
			getContentPane().add(panel);
			
		}catch (ClassNotFoundException e) {
			log.fatal("Pilote non trouvé");
			JOptionPane.showMessageDialog(this, "Impossible de lancer l'application",
					"Erreur Fatale", JOptionPane.ERROR_MESSAGE);
			
			System.exit(1);
		} catch (SQLException e5) {
			log.error("Erreur SQL : "+ e5.getMessage());
			JOptionPane.showMessageDialog(this, e5.getMessage(),
					"Erreur BDD", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e6) {
			log.fatal("Impossible de trouver le fichier de configuration :"+ e6.getMessage());
			System.exit(2);
		}			
		
	
	}
	
	

}
