package com.gestLibrairie.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import com.toedter.calendar.JCalendar;

import sun.util.calendar.JulianCalendar;

public class TicketDeCaisse extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel numTCaisselab,  dateTCaisselab, titreLivrelab, qteVenduelab, prixventelab, totalab;
	private JTextField Num_T_Caisse, Qte_V ;
	private JFormattedTextField Prix_V, Total_V ;
	private JCalendar Date_T_Caisse;
	private  static JComboBox TitreLiv  ;
	private JButton nouveauBouton, enregistrerBouton, annulerBouton, imprimerBouton ;
	private JPanel panTable = new JPanel();
	
	private static	int Num_T_Caisse1=0, Qte_V1=0, Code_liv1  ;
	private static float Prix_V1=0, Total_V1=0, Montant1=0, mont;
	private static	Date Date_T_Caisse1;
	private static String rq0, rq1, requeteTC;
	private int result1;
	
	/**
	 * @wbp.parser.constructor
	 */
	public TicketDeCaisse(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.initTable();
		this.setVisible(true);
		
	}
	
	

	public TicketDeCaisse(int Num_T_Caisse, Date Date_T_Caisse){
		
		Num_T_Caisse1 = Num_T_Caisse ;
		Date_T_Caisse1= Date_T_Caisse;
				
	}
	
	
     public TicketDeCaisse(int Num_T_Caisse, int Code_liv, int Qte_V, float Prix_V, float Montant){
		
		Num_T_Caisse1 = Num_T_Caisse ;
		Code_liv1= Code_liv;
		Qte_V1=Qte_V; 
		Prix_V1=Prix_V;
		Montant1 =Montant;
	}

	

	public static int getNum_T_Caisse1() {
		return Num_T_Caisse1;
	}

	public static int getQte_V1() {
		return Qte_V1;
	}

	public static int getCode_liv1() {
		rq0 =  "SELECT Code_liv  FROM livre WHERE Titre_Liv ='"+ TitreLiv.getSelectedItem()+"'";
		
		 try {
				Statement state3 = Connect.getInstance().createStatement();

		  ResultSet res3 = state3.executeQuery(rq0);
		  
		  res3.next();
		  Code_liv1 = res3.getInt(1);
		  System.out.println(Code_liv1);
		     
		 }catch(SQLException e ){}
		
		
		return Code_liv1;
	}

	public static float getPrix_V1() {
		
		return Prix_V1;
	}


	public static float getMontant1() {
		
		Montant1= ( getPrix_V1() * getQte_V1());
		return Montant1;
	}

public static float getTotal_V1() {
		
		rq1 =  "SELECT Prix_V, Montant  FROM ligne_vente";
		
		try {
			Statement state1 = Connect.getInstance().createStatement();

	     ResultSet res1 = state1.executeQuery(rq1); 
	       while(res1.next()) {
	    	   
	    	   Total_V1=getTotal_V1()+ getMontant1();

	                         }
	        }catch(SQLException e ){}
		
		
		
		
		return Total_V1;
	}
	
	
	public static Date getDate_T_Caisse1() {
		return Date_T_Caisse1;
	}
	
	private void information() {
		
		// Numero Ticket de Caisse
		JPanel panTicketCaisse= new JPanel();
		panTicketCaisse.setBackground(Color.white);
		panTicketCaisse.setPreferredSize(new Dimension(240, 60));
		panTicketCaisse.setBorder(BorderFactory.createTitledBorder("Numero Ticket De Caisse "));
		numTCaisselab = new JLabel("Numero : ");
		Num_T_Caisse = new JTextField(getNum_T_Caisse1());
		Num_T_Caisse.setPreferredSize(new Dimension(90, 25));
		panTicketCaisse.add(numTCaisselab);
		panTicketCaisse.add(Num_T_Caisse);
		
		
		
		// Date Ticket de Caisse
	JPanel panDateTicketCaisse = new JPanel();
	panDateTicketCaisse.setBackground(Color.white);
	panDateTicketCaisse.setPreferredSize(new Dimension(240, 60));
	panDateTicketCaisse.setBorder(BorderFactory.createTitledBorder("Date Ticket De Caisse"));
	dateTCaisselab = new JLabel("Date : ");
	Date_T_Caisse = new JCalendar(getDate_T_Caisse1());
	Date_T_Caisse.setPreferredSize(new Dimension(90, 25));
	panDateTicketCaisse.add(dateTCaisselab);
	panDateTicketCaisse.add(Date_T_Caisse);
	
	
	// Titre Du Livre
	
		JPanel panTitreLivre = new JPanel();
		panTitreLivre.setBackground(Color.white);
		panTitreLivre.setPreferredSize(new Dimension(240, 60));
		panTitreLivre.setBorder(BorderFactory.createTitledBorder("Titre du Livre"));
		titreLivrelab = new JLabel("Titre Du Livre : ");
		TitreLiv = new JComboBox();
		TitreLiv.addItem("Choisir");
		String requete = "SELECT Titre_Liv FROM livre ORDER BY Titre_Liv ";

	    try {
			Statement state = Connect.getInstance().createStatement();

	     ResultSet res = state.executeQuery(requete); 
	       while(res.next()) {
	    	   TitreLiv.addItem(res.getString("Titre_Liv"));

	                         }
	        }catch(SQLException e ){}
	
	    panTitreLivre.add(titreLivrelab);
	    panTitreLivre.add(TitreLiv);
	
	// Qte Vendue
	JPanel panQteVendue = new JPanel();
	panQteVendue.setBackground(Color.white);
	panQteVendue.setPreferredSize(new Dimension(240, 60));
	panQteVendue.setBorder(BorderFactory.createTitledBorder("Quantité Vendue"));
	qteVenduelab = new JLabel(" Quantité : ");
	Qte_V = new JTextField(getQte_V1());
	Qte_V.setPreferredSize(new Dimension(90, 25));
	panQteVendue.add(qteVenduelab);
	panQteVendue.add(Qte_V);
	
	// prix Vente
	JPanel panPrixVente = new JPanel();
	panPrixVente.setBackground(Color.white);
	panPrixVente.setPreferredSize(new Dimension(240, 60));
	panPrixVente.setBorder(BorderFactory.createTitledBorder("Prix de vente"));
	prixventelab = new JLabel("Prix de vente : ");
	Prix_V = new JFormattedTextField(getPrix_V1());
	Prix_V.setPreferredSize(new Dimension(90, 25));
	panPrixVente.add(prixventelab);
	panPrixVente.add(Prix_V);

	
	// Le Total
	
	JPanel panTotal = new JPanel();
	panTotal.setBackground(Color.white);
	panTotal.setPreferredSize(new Dimension(240, 60));
	panTotal.setBorder(BorderFactory.createTitledBorder("Total Vente"));
	totalab = new JLabel("Total : ");
	Total_V= new JFormattedTextField();
	Total_V.setPreferredSize(new Dimension(90, 25));
	panTotal.add(totalab);
	panTotal.add(Total_V);
	
	
		
    JPanel contentT = new JPanel();
	
	contentT.setBackground(Color.white);
	contentT.add(panTicketCaisse);
	contentT.add(panDateTicketCaisse);
	contentT.add(panTitreLivre);
	contentT.add(panQteVendue);
	contentT.add(panPrixVente);
	//contentT.add(panTotal);
		
		

	
	JPanel controlL = new JPanel();
	
// Bouton Nouveau
	JButton nouveauBouton = new JButton("Nouveau Article");
         //	nouveauBouton.setIcon(new ImageIcon (TicketDeCaisse.class.getResource("/valider2.png")));
	
	
	nouveauBouton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {				

			
			result1 = new Integer(getCode_liv1());
			System.out.println(result1);
			mont= getMontant1();
			System.out.println(mont);
		
			try {
			
				
				Statement state = Connect.getInstance()
				.createStatement();
				
			String requete = "INSERT INTO ligne_vente(Num_T_Caisse, Code_liv, Qte_V, Prix_V, Montant ) VALUES ('"+Num_T_Caisse.getText()+"', '"+result1+"', '"+Qte_V.getText()+"', '"+Prix_V.getText()+"','"+mont+"')";
			
			
			
			int resultat = state.executeUpdate(requete);
			
						
			Num_T_Caisse1=0; Code_liv1=0; Qte_V1=0; Prix_V1=0; Montant1=0; 
				 
				 TicketDeCaisse tc= new TicketDeCaisse(Num_T_Caisse1, Code_liv1, Qte_V1, Prix_V1, Montant1);
				 JOptionPane confirmation = new JOptionPane();
					confirmation.showMessageDialog(null, "Article bien ajouté ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

		
					
					
		initTable();			
		//Total_V= new JFormattedTextField(getTotal_V1());
					
		 state.close();
		
		 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			/*
			try {
			
				
				Statement state2 = Connect.getInstance()
				.createStatement();
				
			
			String requete2 = "INSERT INTO distribuer(Code_Dist, Cod_Liv) VALUES ('"+result+"', '"+Cod_Liv.getText()+"')";	 
			
			
			int resultat2 = state2.executeUpdate(requete2);
						
			Cod_Liv1=0; Code_Dist1=null; 
				 
				 AjouterLivre al2 = new AjouterLivre(Cod_Liv1, Code_Dist1);
				 

					
		
		 state2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
			
		 catch (Exception e) {
			e.printStackTrace();
		}
		 
		 */
		 
		 
		 setVisible(false);
			
		   				
				}
	
				
		}
	
		
	);
	
	
	
	
	
	/*
	
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
	*/
	controlL.add(nouveauBouton);
//	controlL.add(cancelBouton);
	/*
	JLabel icon = new JLabel(new ImageIcon (AjouterLivre.class.getResource("/livre.jpg")));
	JPanel panIcon = new JPanel();
	panIcon.setBackground(Color.white);
	panIcon.setLayout(new BorderLayout());
	panIcon.add(icon);
    */
	
	
	panTable.setBackground(Color.white);
	//panTable.setLayout(new BorderLayout());
	
	
	JPanel panLigneVente = new JPanel();
	panLigneVente.setLayout(new BorderLayout());
	panLigneVente.add(panTable);
	panLigneVente.add(panTotal);
	
	this.getContentPane().add(panLigneVente, BorderLayout.WEST);
	this.getContentPane().add(contentT, BorderLayout.CENTER);
	this.getContentPane().add(controlL, BorderLayout.SOUTH);

	
	
	

}	
		
	public void initTable(){
		
		try {
				
				requeteTC = "SELECT Code_liv, Qte_V, Prix_V, Montant FROM ligne_vente WHERE Num_T_Caisse ='"+ Num_T_Caisse.getText()+"'";
				Statement state2 = Connect.getInstance()
												.createStatement(
															ResultSet.TYPE_SCROLL_INSENSITIVE, 
															ResultSet.CONCUR_READ_ONLY
												);
				
				
				ResultSet res2 = state2.executeQuery(requeteTC);
				ResultSetMetaData meta2 = res2.getMetaData();
				Object[] column = new Object[meta2.getColumnCount()];
				
				for(int i = 1 ; i <= meta2.getColumnCount(); i++){
					column[i-1] = meta2.getColumnName(i);
				}
				res2.last();
				int rowCount = res2.getRow();
				Object[][] data2 = new Object[res2.getRow()][meta2.getColumnCount()];

				res2.beforeFirst();
				int j = 1;

				while(res2.next()){
					for(int i = 1 ; i <= meta2.getColumnCount(); i++)
						data2[j-1][i-1] = res2.getObject(i);
					
					j++;
				}
				
	                                     
				res2.close();
				state2.close();

				panTable.removeAll();
				panTable.add(new JScrollPane(new JTable(data2, column)), BorderLayout.CENTER);
				panTable.revalidate();
				
		} catch (SQLException e) {		
					panTable.removeAll();
					panTable.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
					panTable.revalidate();
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);
				}	
				
				
				
				
				
				
			} 
						
		
	}
	





	
	

