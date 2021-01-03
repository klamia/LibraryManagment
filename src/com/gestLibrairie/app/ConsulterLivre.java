package com.gestLibrairie.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;



public class ConsulterLivre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(ConsulterLivre.class);
	
	private JTable table;	
	private JTextField textField;
	Connection connexion;
	
	
	public ConsulterLivre(){
		
		setSize(new Dimension(600, 400));
		setTitle("Consulter Livre");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 572, 186);
		getContentPane().add(scrollPane);
		
		/*
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (table.getSelectedRow() != -1) {
					int id = Integer.parseInt(((DefaultTableModel)table.getModel()).getValueAt(
							table.getSelectedRow(), 0).toString());
					
					
					try {
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}													
				
				}				
			}
		});*/
		
		table = new JTable();
		table.setModel(new LivreTableModel());
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(184);
		
		table.addKeyListener(new KeyAdapter() {			
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
					VoirDetail();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VoirDetail();
			}
		});
		
		scrollPane.setViewportView(table); 
		try {
			
			log.info("Ouverture du fichier de paramétrage");
			Properties params = new Properties();
			params.load(ConsulterLivre.class.getResourceAsStream("jdbc-config.properties"));
			
			log.debug("Chargement du pilote");
			Class.forName(params.getProperty("nomDuDriver"));
			
			log.info("Ouverture de la connexion");
			connexion = DriverManager.getConnection(
					params.getProperty("ChaineDeConnexion"));
			
			String sql = "select Cod_Liv, Titre_Liv from Livre";
			log.debug(sql.toString());
			
			Statement requete = connexion.createStatement();
			
			ResultSet resultat = requete.executeQuery(sql);
			
			table.setModel(new LivreTableModel());
			
			
		
			
			
			JButton btnImprimer = new JButton("Imprimer Liste Livre");
			
			btnImprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					try {	
						// - Execution du rapport et affichage de l'aperçu
						log.info("Lancement de l'impression ");
						JasperPrint jasperPrint = JasperFillManager.fillReport(
								ConsulterLivre.class.getResourceAsStream("listeLivres.jasper"), 
														null, connexion);
						JasperViewer.viewReport(jasperPrint, false);
						
						// - Si vous voulez générer automatiquement un rapport PDF
						//JasperExportManager.exportReportToPdfFile(jasperPrint,"etudiants2.pdf");
						
						// lancer directement l'impression 
						// 										true : afficher PrinterDialog
						//										false : utiliser l'imprimante par défaut
						
						//log.debug("Impression directe");
					//	JasperPrintManager.printReport(jasperPrint, false);
						
					} catch (JRException ex) {
						ex.printStackTrace();
					}
				}
			}); 
			btnImprimer.setMnemonic('I');
			//btnImprimer.setIcon(new ImageIcon(FichePatient.class.getResource("/etm/javance/bdd/printer1.png")));
			btnImprimer.setBounds(10, 227, 131, 41);
			getContentPane().add(btnImprimer);
			
			JButton btnApercuLivre = new JButton("Aperçu Livre");
			
			btnApercuLivre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {						
						Map params = new HashMap();
						params.put("numero_livre", new Integer(textField.getText()));
						
						log.info("Lancement de l'apercu ");
						JasperPrint jasperPrint = JasperFillManager.fillReport(
								ConsulterLivre.class.getResourceAsStream("apercuLivre.jasper"), 
														params, connexion);
						
					
						
						JasperViewer.viewReport(jasperPrint, false);
						
					} catch (JRException ex) {
						ex.printStackTrace();
					}
				}
			}); 
			//btnOrdonnance.setIcon(new ImageIcon(FichePatient.class.getResource("/etm/javance/bdd/printer1.png")));
			btnApercuLivre.setMnemonic('O');
			btnApercuLivre.setBounds(136, 279, 131, 41);
			getContentPane().add(btnApercuLivre);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField.setBounds(10, 279, 116, 41);
			getContentPane().add(textField);
			textField.setColumns(10);
			
			
			System.gc(); // retirer l'ancien model de la mémoire
			while (resultat.next()) {
				((DefaultTableModel)table.getModel()).addRow(
						new Object[] {
								resultat.getInt(1), 
								resultat.getString(2)
						}
						);			
		
			}			
		}  catch (ClassNotFoundException e) {
			log.fatal("Pilote non trouvé");
			JOptionPane.showMessageDialog(this, "Impossible de lancer l'application",
					"Erreur Fatale", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (SQLException e) {
			log.error("Erreur SQL : "+ e.getMessage());
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Erreur BDD", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			log.fatal("Impossible de trouver le fichier de configuration");
			System.exit(2);
		}			
		
		
	}


	public class LivreTableModel extends DefaultTableModel {
		
		public LivreTableModel() {
			super( null,
					new String[] {"Code", "Titre"}
				);
		}			
		
		public boolean isCellEditable(int row, int column) {
				return false;
			}
		}
		
	private void VoirDetail() {
		if (table.getSelectedRow() != -1) {
			Object o = ((DefaultTableModel)table.getModel()).getValueAt(
					table.getSelectedRow(), 0);
			if (o != null)
				textField.setText(o.toString());
			else textField.setText("");
		}
	}
		
	
}
	
	
	
	
	

