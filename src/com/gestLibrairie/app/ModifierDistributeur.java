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
import javax.swing.ComboBoxModel;
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



public class ModifierDistributeur extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(ModifierDistributeur.class);
	
	private JLabel   AdresseLab, VilleLab, WilayaLab, TelephoneLab, Code_PostalLab;
	private JTextField  Rue, Ville, Tel_Dist, Cposte_Dist ;
	private static String  Rue1=null, Ville1=null, Wilaya1=null, Tel_Dist1=null, Cposte_Dist1=null ;
	private static int Code_Dist = 0;
	private JComboBox Wilaya;
	
	
	
	public ModifierDistributeur(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	
	public ModifierDistributeur(String Rue, String Ville, String Wilaya, String Tel_Dist, String Cpost_Dist, int Code_Dist) {
		// TODO Auto-generated constructor stub
		
		Rue1 = Rue;
		Ville1 = Ville;
		Wilaya1=Wilaya;
		System.out.println(Wilaya1);
		Tel_Dist1=Tel_Dist;
		Cposte_Dist1=Cpost_Dist;
		this.Code_Dist=Code_Dist;
	}
	
	
	
	
	
	
	
	public static String getWilaya1() {
		
		return Wilaya1;
	}


	public static void setWilaya1(String wilaya1) {
		Wilaya1 = wilaya1;
	}


	public static String getRue1() {
		return Rue1;
	}


	public static void setRue1(String rue1) {
		Rue1 = rue1;
	}


	public static String getVille1() {
		return Ville1;
	}


	public static void setVille1(String ville1) {
		Ville1 = ville1;
	}


	public static String getTel_Dist1() {
		return Tel_Dist1;
	}


	public static void setTel_Dist1(String tel_Dist1) {
		Tel_Dist1 = tel_Dist1;
	}


	public static String getCposte_Dist1() {
		return Cposte_Dist1;
	}


	public static void setCposte_Dist1(String cposte_Dist1) {
		Cposte_Dist1 = cposte_Dist1;
	}


	private void information(){

		
			
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
			
			

	        String requete = "SELECT Nom_W FROM wilaya ORDER BY Code_W ";

	        try {
	    		Statement state = Connect.getInstance().createStatement();

	         ResultSet res = state.executeQuery(requete); 
	           while(res.next()) {
	        	   Wilaya.addItem(res.getString("Nom_W"));
	   
	                             }
	            }catch(SQLException e ){}
			
	        Wilaya.setSelectedItem(getWilaya1());
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
			
			content.add(panAdresse);
			content.add(panVille);
			content.add(panWilaya);
			content.add(panTelephone);
			content.add(panCodePostal);
			
		
			
			JPanel control = new JPanel();
			control.setBackground(Color.BLUE);
			JButton okBouton = new JButton("Valider");
		
			okBouton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {				

				
						try {
						
							
							Statement state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							String query = "UPDATE distributeur SET Rue = '"+Rue.getText()+"', Ville = '"+Ville.getText()+"', Wilaya = '"+(String)Wilaya.getSelectedItem()+"', Tel_Dist='"+Tel_Dist.getText()+"', Cpost_Dist='"+Cposte_Dist.getText()+"' WHERE Code_Dist = '"+Code_Dist+"'";
							
							log.debug(query.toString());
						
							state.executeUpdate(query);

				                        state.close();
				                        log.info("distributeur "+Code_Dist+ " a été modifié");
				                        JOptionPane confirmation = new JOptionPane();
										confirmation.showMessageDialog(null, "Le distributeur a été modifier ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

							
								
						
						}  catch(SQLException e2 ){
							
							log.error("Erreur de maj : "+e2.getMessage());
							JOptionPane.showMessageDialog(null, e2.getMessage(),
									"Erreur BDD", JOptionPane.ERROR_MESSAGE);
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
	
	
	
	public ModifierDistributeur() throws SQLException {
		
		Statement state = Connect.getInstance()
		.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
		);
		ResultSet res = state.executeQuery("SELECT Code_Dist,Rs_Dist FROM distributeur");
		
	
	

		res.last();
		int rowCount = res.getRow();
		//System.out.println(rowCount);

		res.beforeFirst();
		Object[] data = new Object[rowCount];
	
		int i=0;
		while(res.next()){
			
				data[i] = res.getString("Code_Dist")+" > "+res.getString("Rs_Dist");
	i++;
		
		}
		
		
		

		

		
		
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	ImageIcon img = new ImageIcon (ModifierDistributeur.class.getResource("/distributeur.jpg"));
	String modele = (String)jop.showInputDialog(null, 
									"Veuillez choisir le Distributeur !",
									"Modification d'un Distributeur !",
									JOptionPane.QUESTION_MESSAGE,
									img,
									 data,
									null);
	
	//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");
try{
    String[] str = modele.split("\\ > ");
    
	res = state.executeQuery("SELECT * FROM distributeur WHERE Rs_Dist = '"+str[1]+"'");
	
	
	res.first();
	String rue = res.getString("Rue");
	String ville = res.getString("Ville");
	String wilaya = res.getString("Wilaya");
	//System.out.println(wilaya);
	
	String tel = res.getString("Tel_Dist");
	String codepostal = res.getString("Cpost_Dist");
	int id = res.getInt("Code_Dist");
	log.debug("DISTRIBUTEUR trouvé");
	
	
	ModifierDistributeur m = new ModifierDistributeur(rue,ville,wilaya,tel, codepostal,id);
	ModifierDistributeur md = new ModifierDistributeur(null, "Modifier un Distributeur", true);

}catch(NullPointerException e3) {
	log.error("DISTRIBUTEUR non trouvé :"+e3.getMessage());
}
	
	res.close();
	state.close();
	

	
	}
	
	
}
