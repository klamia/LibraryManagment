package com.gestLibrairie.app;

public class Ligne_Vente {

	
	private String Code_Liv;
	private String Qte_V;
	private String Prix_V;
	private String Montant;
	
	public Ligne_Vente(String code_Liv, String qte_V, String prix_V, String montant) {
	
		
		this.Code_Liv = code_Liv;
		this.Qte_V = qte_V;
		this.Prix_V = prix_V;
		this.Montant = montant;
	}

	

	public String getCode_Liv() {
		return Code_Liv;
	}

	public void setCode_Liv(String code_Liv) {
		Code_Liv = code_Liv;
	}

	public String getQte_V() {
		return Qte_V;
	}

	public void setQte_V(String qte_V) {
		Qte_V = qte_V;
	}

	public String getPrix_V() {
		return Prix_V;
	}

	public void setPrix_V(String prix_V) {
		Prix_V = prix_V;
	}

	public String getMontant() {
		return Montant;
	}

	public void setMontant(String montant) {
		Montant = montant;
	}

	
	
}
