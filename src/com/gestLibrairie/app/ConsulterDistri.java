package com.gestLibrairie.app;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;



public class ConsulterDistri extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSplitPane split;
	private JPanel result = new JPanel();
	private String requete = "SELECT *  FROM distributeur";
	
	
	public ConsulterDistri() {
		setSize(500, 300);
		setTitle("Consulter les Distibuteurs");
		setLocationRelativeTo(null);

		
		
		initContent();
		initTable();
	}


	private void initContent() {
		// TODO Auto-generated method stub
		
		result.setLayout(new BorderLayout());
		JLabel jlb = new JLabel(new ImageIcon (ConsulterDistri.class.getResource("/distibuteurs.jpg")));
		jlb.setPreferredSize(new Dimension(600, 200));
		JScrollPane dd = new JScrollPane(jlb);
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jlb , result);
		split.setDividerLocation(100);
		getContentPane().add(split, BorderLayout.CENTER);
	}
	
	private void initTable() {
		// TODO Auto-generated method stub
		
       try {
			
			long start = System.currentTimeMillis();
			Statement state = Connect.getInstance()
											.createStatement(
														ResultSet.TYPE_SCROLL_INSENSITIVE, 
														ResultSet.CONCUR_READ_ONLY
											);
			
			
			ResultSet res = state.executeQuery(requete);
			ResultSetMetaData meta = res.getMetaData();
			Object[] column = new Object[meta.getColumnCount()];
			
			for(int i = 1 ; i <= meta.getColumnCount(); i++){
				column[i-1] = meta.getColumnName(i);
			}
			res.last();
			int rowCount = res.getRow();
			Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

			res.beforeFirst();
			int j = 1;

			while(res.next()){
				for(int i = 1 ; i <= meta.getColumnCount(); i++)
					data[j-1][i-1] = res.getObject(i);
				
				j++;
			}
			
                                     
			res.close();
			state.close();

			long totalTime = System.currentTimeMillis() - start;
			
	
			result.removeAll();
			result.add(new JScrollPane(new JTable(data, column)), BorderLayout.CENTER);
			result.add(new JLabel("La demande à été exécuter en " + totalTime + " ms et a retourné " + rowCount + " ligne(s)"), BorderLayout.SOUTH);
			result.revalidate();
			
		} catch (SQLException e) {		
			result.removeAll();
			result.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
			result.revalidate();
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);
		}	
		
	}
		
	

}
