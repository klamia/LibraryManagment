package com.gestLibrairie.app;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleModifiable extends AbstractTableModel {
    private final List<Ligne_Vente> ligne_ventes = new ArrayList<Ligne_Vente>();

    private final String[] entetes = {"Code_Liv", "Qte_V", "Prix_V", "Montant"};

    public ModeleModifiable() {
        super();

        ligne_ventes.add(new Ligne_Vente("", "","",""));
        ligne_ventes.add(new Ligne_Vente("", "","",""));
        ligne_ventes.add(new Ligne_Vente("", "","",""));
        ligne_ventes.add(new Ligne_Vente("", "","",""));
    }

    public int getRowCount() {
        return ligne_ventes.size();
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ligne_ventes.get(rowIndex).getCode_Liv();
            case 1:
                return ligne_ventes.get(rowIndex).getQte_V();
            case 2:
                return ligne_ventes.get(rowIndex).getPrix_V();
            case 3:
                return ligne_ventes.get(rowIndex).getMontant();
              
            default:
                return null; //Ne devrait jamais arriver
        }
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //Toutes les cellules éditables
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
        	Ligne_Vente lv = ligne_ventes.get(rowIndex);

            switch(columnIndex){
                case 0:
                    lv.setCode_Liv((String)aValue);
                    break;
                case 1:
                    lv.setQte_V((String)aValue);
                    break;
                case 2:
                    lv.setPrix_V((String)aValue);
                    break;
                case 3:
                    lv.setMontant((String)aValue);
                    break;
                
            }
        }
    }

    public void add_LigneVente(Ligne_Vente lv) {
    	ligne_ventes.add(lv);

        fireTableRowsInserted(ligne_ventes.size() -1, ligne_ventes.size() -1);
    }

    public void remove_LigneVente(int rowIndex) {
    	ligne_ventes.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}