package com.gestLibrairie.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;


public class SupprimerDistributeur {

	static Logger log = Logger.getLogger(SupprimerDistributeur.class);
	
	public SupprimerDistributeur() throws SQLException {
	
		
	Statement state = Connect.getInstance()
	.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_READ_ONLY
	);
	ResultSet res = state.executeQuery("SELECT Rs_Dist FROM distributeur");
	



	res.last();
	int rowCount = res.getRow();
	//System.out.println(rowCount);

	res.beforeFirst();
	Object[] data = new Object[rowCount];

	int i=0;
	while(res.next()){
		
			data[i] = res.getString("Rs_Dist");
i++;
	
	}
	
	
JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
ImageIcon img = new ImageIcon (SupprimerDistributeur.class.getResource("/suppression.png"));
String raisonsocial = (String)jop.showInputDialog(null, 
								"Veuillez choisir le distributeur a supprimer!",
								"Suppression Distributeur !",
								JOptionPane.QUESTION_MESSAGE,
								img,
								 data,
								null);
System.out.println(raisonsocial);

	if(raisonsocial !=null) {jop2 = new JOptionPane();	
//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");

	int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir supprimer le distributeur "+raisonsocial+" ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	

	if(option == JOptionPane.OK_OPTION)
	{
res = state.executeQuery("SELECT Code_Dist FROM distributeur WHERE Rs_Dist = '"+raisonsocial+"'");

res.first();

int id = res.getInt("Code_Dist");
System.out.println(id);

String query = "DELETE FROM distributeur WHERE Code_Dist = '"+id+"'";
log.debug(query.toString());
int resultat= state.executeUpdate(query);
JOptionPane confirmation = new JOptionPane();
confirmation.showMessageDialog(null, "Le distributeur a été bien supprimé ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);
log.warn("Distributeur " + id + " a été supprimé");
	
	}}else
		log.debug("Aucun Distributeur n a été supprimé");

 

res.close();
state.close();
		

		

}


}
