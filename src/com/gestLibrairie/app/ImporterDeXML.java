package com.gestLibrairie.app;

import java.io.File;   

import noNamespace.ParcautomobileDocument;   

import noNamespace.MarqueDocument.Marque;   

import noNamespace.ParcautomobileDocument.Parcautomobile;   

import noNamespace.PuissanceDocument.Puissance;   

import noNamespace.VehiculeDocument.Vehicule;   




public class ImporterDeXML {

	public static void main(String[] args) throws Exception  {
		
		//Parsing du fichier XML   

        ParcautomobileDocument Parcautomobile = ParcautomobileDocument.Factory.parse(new File ("E:/xmlBeanTest/parcAuto.xml"));   

           

        Parcautomobile parc = Parcautomobile.getParcautomobile();   

           

        //modelesVehicule est un tableau d'objets Vehicule   

        Vehicule[] modelesVehicule = parc.getVehiculeArray();          

        System.out.println("Nombre de modeles de vehicules: " + modelesVehicule.length);   

         

      //parcours de tous les modèles de vehicule   

       for (Vehicule v : modelesVehicule){              

            System.out.println(v.getModele());   

             //parcours de toutes les marques de chaque modèle de vehicule   

             Marque[] lesMarques =  v.getMarqueArray();   

             for (Marque m : lesMarques){                              

                System.out.println(m.getNom()+ " " + m.getCouleur() + " " + m.getNombre());                

                Puissance puissance = m.getPuissance();   

                System.out.println(puissance.getChevaux()+ " " + puissance.getVitessemax());           

              }          

              System.out.println();                   

       }   
		
		
	}

}
