package com.gestLibrairie.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;



import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import sun.util.calendar.JulianCalendar;

public class TicketDeCaisse2 extends JDialog {

	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	
	private JComboBox combotitreLivre;
	private JTextField Num_T_Caisse, Qte_V, Total_T_Caisse;
	private JLabel lblTicketDeCaisse, lblDateTicketCaisse, lblQuantitVendue, lblTitreLivre, lblNum_T_Caisse, lblTotal; 
	private JDateChooser Date_T_Caisse;
	private JButton btnNewButton, btnSupprimer, btnAnnuler, btnEnregistrer ;
	
	private static Date Date_T_Caisse1;
	private static String  Code_Liv1=null; 
	private static int Num_T_Caisse1, Qte_V1;
	private static float Total_T_Caisse1, Prix_V1, Montant1;
	
	
	
	private ModeleModifiable modele = new ModeleModifiable();
    private JTable tableau;
	
	/**
	 * @wbp.parser.constructor
	 */
	public TicketDeCaisse2(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(840, 625);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
	}
		
	public TicketDeCaisse2(int Num_T_Caisse, Date Date_T_Caisse , float Total_T_Caisse) {
		
		Num_T_Caisse1=Num_T_Caisse;
		Date_T_Caisse1= Date_T_Caisse;
		Total_T_Caisse1 = Total_T_Caisse;	
	}
	
		
	private void information(){
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 767, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblTicketDeCaisse = new JLabel("Ticket de Caisse");
		lblTicketDeCaisse.setVerticalAlignment(SwingConstants.TOP);
		lblTicketDeCaisse.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTicketDeCaisse.setBounds(279, 13, 210, 34);
		panel.add(lblTicketDeCaisse);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 65, 767, 119);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblDateTicketCaisse = new JLabel("Date Ticket de Caisse :");
		lblDateTicketCaisse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateTicketCaisse.setBounds(12, 13, 168, 27);
		panel_1.add(lblDateTicketCaisse);
		
		lblQuantitVendue = new JLabel("Quantit\u00E9 Vendue :");
		lblQuantitVendue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantitVendue.setBounds(408, 78, 145, 27);
		panel_1.add(lblQuantitVendue);
		
		lblTitreLivre = new JLabel("Titre Livre :");
		lblTitreLivre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitreLivre.setBounds(12, 78, 97, 27);
		panel_1.add(lblTitreLivre);
		
		lblNum_T_Caisse = new JLabel("Num_T_Caisse");
		lblNum_T_Caisse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNum_T_Caisse.setBounds(408, 13, 145, 27);
		panel_1.add(lblNum_T_Caisse);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		Date_T_Caisse = new JDateChooser();
		Date_T_Caisse.setBounds(167, 18, 96, 22);
		panel_1.add(Date_T_Caisse);
		
	    
	
		
		Num_T_Caisse = new JTextField();
		Num_T_Caisse.setBounds(560, 15, 116, 22);
		panel_1.add(Num_T_Caisse);
		Num_T_Caisse.setColumns(10);
		
		Qte_V = new JTextField(getQte_V1());
		Qte_V.setColumns(10);
		Qte_V.setBounds(560, 80, 116, 22);
		panel_1.add(Qte_V);
		
		combotitreLivre = new JComboBox();
		combotitreLivre.addItem("");

        String requete = "SELECT Titre_Liv FROM livre ORDER BY Cod_Liv ";

        try {
    		Statement state = Connect.getInstance().createStatement();

         ResultSet res = state.executeQuery(requete); 
           while(res.next()) {
        	   combotitreLivre.addItem(res.getString("Titre_Liv"));
   
                             }
            }catch(SQLException e ){}
		combotitreLivre.setBounds(157, 80, 116, 22);
		panel_1.add(combotitreLivre);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 415, 767, 40);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(583, 13, 56, 16);
		panel_2.add(lblTotal);
		
		Total_T_Caisse = new JTextField();
		Total_T_Caisse.setBounds(626, 10, 129, 22);
		panel_2.add(Total_T_Caisse);
		Total_T_Caisse.setColumns(10);
		
		btnNewButton = new JButton("Nouveau");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.add_LigneVente(new Ligne_Vente("", "", "", "")); 
			}
		});
		btnNewButton.setBounds(12, 9, 81, 25);
		panel_2.add(btnNewButton);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selection = tableau.getSelectedRows();

	            for(int i = selection.length - 1; i >= 0; i--){
	                modele.remove_LigneVente(selection[i]);
	            }
			}
		});
		btnSupprimer.setBounds(105, 9, 98, 25);
		panel_2.add(btnSupprimer);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 455, 767, 75);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 462, 769, 105);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					setVisible(false);
				}catch(NullPointerException n) {
					System.exit(0);	
					
				}
			}
		});
		btnAnnuler.setBounds(471, 48, 77, 25);
		panel_4.add(btnAnnuler);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				

		    AjouterTicketCaisse(Num_T_Caisse.getText(), new Date(Date_T_Caisse.getDate().getTime()), Total_T_Caisse.getText());
						
			
			 setVisible(false);
				
			   				
					}
		});
		btnEnregistrer.setBounds(267, 48, 95, 25);
		panel_4.add(btnEnregistrer);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setBounds(374, 48, 85, 25);
		panel_4.add(btnImprimer);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		scrollPane.setBounds(12, 190, 767, 217);
		
		tableau = new JTable(modele);
		scrollPane.setViewportView(tableau);
		
		
	}


	void AjouterTicketCaisse(String Num_T_Caisse, Date Date_T_Caisse , String Total_T_Caisse){
				
		String sql = "insert into vente values (?, ?, ?);";
		try {
			
			//Statement state = Connect.getInstance().createStatement();
			PreparedStatement requete = (PreparedStatement) Connect.getInstance().createStatement().executeQuery(sql);
			
			requete.setString(1,Num_T_Caisse);
			requete.setDate(2, Date_T_Caisse);
			requete.setString(3, Total_T_Caisse);
			
			
			//log.debug(requete.toString());			
			
			if (requete.executeUpdate() == 1) {
				
				
				JOptionPane.showMessageDialog(null, "Patient Ajouté !");
				//log.info("Nouveau Patient ajouté à la BDD");
			}
			
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erreur d'insertion",
					"Erreur BDD", JOptionPane.ERROR_MESSAGE);
			//log.error("Erreur lors de l insertion pour cause" + e1.getMessage());
		}
		
	}
	
	
	public static float getPrix_V1() {
		return Prix_V1;
	}



	public static void setPrix_V1(float  prix_V1) {
		Prix_V1 = prix_V1;
	}



	public static int getQte_V1() {
		return Qte_V1;
	}



	public static void setQte_V1(int qte_V1) {
		Qte_V1 = qte_V1;
	}



	public static float  getTotal_T_Caisse1() {
		return Total_T_Caisse1;
	}

	public static void setTotal_T_Caisse1(float  total_T_Caisse1) {
		Total_T_Caisse1 = total_T_Caisse1;
	}

	public static Date getDate_T_Caisse1() {
		
		
		  
		
		return Date_T_Caisse1;
	}



	public static void setDate_T_Caisse1(Date date_T_Caisse1) {
	
		
		Date_T_Caisse1 = date_T_Caisse1;
	}

	public static int getNum_T_Caisse1() {
		return Num_T_Caisse1;
	}

	public static void setNum_T_Caisse1(int num_T_Caisse1) {
		Num_T_Caisse1 = num_T_Caisse1;
	}

	public static String getCode_Liv1() {
		return Code_Liv1;
	}

	public static void setCode_Liv1(String code_Liv1) {
		Code_Liv1 = code_Liv1;
	}

	public static float  getMontant1() {
		return Montant1;
	}

	public static void setMontant1(float  montant1) {
		Montant1 = montant1;
	}
						
		
	}
	





	
	

