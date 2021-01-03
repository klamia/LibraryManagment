package com.gestLibrairie.app;

import java.io.File;   

import java.io.IOException;   

   

import noNamespace.ParcautomobileDocument;   

import noNamespace.CouleurDocument.Couleur;   

import noNamespace.MarqueDocument.Marque;   

import noNamespace.NomDocument.Nom;   

import noNamespace.ParcautomobileDocument.Parcautomobile;   

import noNamespace.PuissanceDocument.Puissance;   

import noNamespace.VehiculeDocument.Vehicule;   

import noNamespace.VehiculeDocument.Vehicule.Modele;   

   

import org.apache.xmlbeans.XmlOptions; 

public class ExporterEnXML {

	
	
	public static void main(String[] args) throws IOException {
		
		 //parc est une instance du document XML   

        ParcautomobileDocument parc = ParcautomobileDocument.Factory.newInstance();   

           

        //ajout d'un tag Parcautomobile au document XML   

        Parcautomobile parcautomobile = parc.addNewParcautomobile();   

           

        // ajout d'un Vehicule au parc automobile   

        Vehicule vehicule1 = parcautomobile.addNewVehicule();                          

        vehicule1.setModele(Modele.COUPE);   

           

        //Vehicule de modèle COUPE, 1ère marque    

        Marque marque1 = vehicule1.addNewMarque();   

        marque1.setNom(Nom.RENAULT);   

           

        Puissance puissance1 = Puissance.Factory.newInstance();            

        puissance1.setChevaux((byte)8);   

        puissance1.setVitessemax((short)280);              

        marque1.setPuissance(puissance1);      

        marque1.setNombre((byte)18);   

        marque1.setCouleur(Couleur.NOIR);   

           

        //Vehicule de modèle COUPE, 2ème marque    

        Marque marque2 = vehicule1.addNewMarque();   

        marque2.setNom(Nom.PEUGEOT);   

           

        Puissance puissance2 = Puissance.Factory.newInstance();            

        puissance2.setChevaux((byte)11);   

        puissance2.setVitessemax((short)320);              

        marque2.setPuissance(puissance2);      

        marque2.setNombre((byte)10);   

        marque2.setCouleur(Couleur.ROUGE);   

                               

        // ajout d'un Vehicule au parc automobile   

        Vehicule vehicule2 = parcautomobile.addNewVehicule();                          

        vehicule2.setModele(Modele.BERLINE);   

           

        //Vehicule de modèle COUPE, 1ère marque    

        Marque marque3 = vehicule2.addNewMarque();   

        marque3.setNom(Nom.MERCEDES);   

           

        Puissance puissance3 = Puissance.Factory.newInstance();            

        puissance3.setChevaux((byte)10);   

        puissance3.setVitessemax((short)330);              

        marque3.setPuissance(puissance3);      

        marque3.setNombre((byte)9);   

        marque3.setCouleur(Couleur.GRIS);   

           

        //Vehicule de modèle COUPE, 2ème marque    

        Marque marque4 = vehicule2.addNewMarque();   

        marque4.setNom(Nom.BMW);   

           

        Puissance puissance4 = Puissance.Factory.newInstance();            

        puissance4.setChevaux((byte)12);   

        puissance4.setVitessemax((short)300);              

        marque4.setPuissance(puissance4);      

        marque4.setNombre((byte)7);   

        marque4.setCouleur(Couleur.BLEU);   

           

        // Validation de l'instance du schema XML   

          boolean estValide = parc.validate();   

           

          if (! estValide){   

               System.err.println("Attention, cette instance du schema n'est pas valide ! ");   

               return;   

          }   

           

        XmlOptions opts = new XmlOptions();   

        //cette option permet d'indenter le code    

        opts.setSavePrettyPrint();   

           

        System.out.println(parc.xmlText(opts));   

        parc.save(new File ("E:/xmlBeanTest/parcAuto1.xml"), opts);	

	}

}
