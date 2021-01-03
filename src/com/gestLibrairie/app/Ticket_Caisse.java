package com.gestLibrairie.app;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ticket_Caisse extends JDialog {

	private static final long serialVersionUID = 1L;
	
	
	private JComboBox combotitreLivre;
	private JTable table;
	private JTextField Prix_V, Qte_V, total;
	private JLabel lblTicketDeCaisse, lblDateTicketCaisse, lblQuantitVendue, lblTitreLivre, lblPrixVente, lblTotal; 
	private JDateChooser Date_T_Caisse;
	private JButton btnNewButton, btnSupprimer, btnAnnuler, btnEnregistrer ;
	private static String Prix_V1=null, Qte_V1=null, total1=null, Date_T_Caisse1=null;
	private static String Num_T_Caisse1=null, Code_Liv1=null, Montant1=null; 
	
	private ModeleModifiable modele = new ModeleModifiable();
  //  private JTable tableau;
	
	/**
	 * @wbp.parser.constructor
	 */
	public Ticket_Caisse(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(840, 625);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
	}
		
	public Ticket_Caisse(String Date_T_Caisse) {
		Date_T_Caisse1= Date_T_Caisse;
	}
	
	public Ticket_Caisse(String Num_T_Caisse, String Code_Liv, String Qte_V, String Prix_V, String Montant ) {
		Num_T_Caisse1 = Num_T_Caisse;
		Code_Liv1=Code_Liv;
		Qte_V1=Qte_V;
		Prix_V1=Prix_V;
		Montant1=Montant;
		
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
		
		lblPrixVente = new JLabel("Prix Vente :");
		lblPrixVente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrixVente.setBounds(408, 13, 145, 27);
		panel_1.add(lblPrixVente);
		
		Date_T_Caisse = new JDateChooser();
		Date_T_Caisse.setBounds(167, 18, 96, 22);
		panel_1.add(Date_T_Caisse);
		
		Prix_V = new JTextField(getPrix_V1());
		Prix_V.setBounds(560, 15, 116, 22);
		panel_1.add(Prix_V);
		Prix_V.setColumns(10);
		
		Qte_V = new JTextField(getQte_V1());
		Qte_V.setColumns(10);
		Qte_V.setBounds(560, 80, 116, 22);
		panel_1.add(Qte_V);
		
		combotitreLivre = new JComboBox();
		combotitreLivre.addItem("");

        String requete = "SELECT Titre_Liv FROM livre ORDER BY Code_Liv ";

        try {
    		Statement state = Connect.getInstance().createStatement();

         ResultSet res = state.executeQuery(requete); 
           while(res.next()) {
        	   combotitreLivre.addItem(res.getString("Titre_Liv"));
   
                             }
            }catch(SQLException e ){}
		combotitreLivre.setBounds(157, 80, 116, 22);
		panel_1.add(combotitreLivre);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 197, 767, 205);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Titre_Liv", "Code_Liv", "Qte_V", "Prix_V", "Montant"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(191);
		table.getColumnModel().getColumn(2).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 415, 767, 40);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(583, 13, 56, 16);
		panel_2.add(lblTotal);
		
		total = new JTextField();
		total.setBounds(626, 10, 129, 22);
		panel_2.add(total);
		total.setColumns(10);
		
		btnNewButton = new JButton("Nouveau");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modele.add_LigneVente(new Ligne_Vente("", "", "", ""));
			}
		});
		btnNewButton.setBounds(12, 9, 81, 25);
		panel_2.add(btnNewButton);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selection = table.getSelectedRows();

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
		btnEnregistrer.setBounds(267, 48, 95, 25);
		panel_4.add(btnEnregistrer);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setBounds(374, 48, 85, 25);
		panel_4.add(btnImprimer);
		
		
	}



	public static String getPrix_V1() {
		return Prix_V1;
	}



	public static void setPrix_V1(String prix_V1) {
		Prix_V1 = prix_V1;
	}



	public static String getQte_V1() {
		return Qte_V1;
	}



	public static void setQte_V1(String qte_V1) {
		Qte_V1 = qte_V1;
	}



	public static String getTotal1() {
		return total1;
	}



	public static void setTotal1(String total1) {
		Ticket_Caisse.total1 = total1;
	}



	public static String getDate_T_Caisse1() {
		return Date_T_Caisse1;
	}



	public static void setDate_T_Caisse1(String date_T_Caisse1) {
		Date_T_Caisse1 = date_T_Caisse1;
	}

	public static String getNum_T_Caisse1() {
		return Num_T_Caisse1;
	}

	public static void setNum_T_Caisse1(String num_T_Caisse1) {
		Num_T_Caisse1 = num_T_Caisse1;
	}

	public static String getCode_Liv1() {
		return Code_Liv1;
	}

	public static void setCode_Liv1(String code_Liv1) {
		Code_Liv1 = code_Liv1;
	}

	public static String getMontant1() {
		return Montant1;
	}

	public static void setMontant1(String montant1) {
		Montant1 = montant1;
	}
}
