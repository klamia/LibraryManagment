package com.gestLibrairie.app;

import java.io.File;

import noNamespace.CategorieType;
import noNamespace.EditionType;
import noNamespace.LibrairieDocument;
import noNamespace.LibrairieType;
import noNamespace.LivreType;


public class ImporterXML {

	public static void main(String[] args) throws Exception {
		
		//Parsing du fichier XML   

		LibrairieDocument Librairie =  LibrairieDocument.Factory.parse(new File ("E:/GESTLIB/librairie1.xml"));   

        LibrairieType lib = Librairie.getLibrairie();   

           

        //Disciplines Livres est un tableau d'objets Livres  

        LivreType[] disciplineLivres = lib.getLivreArray();         

        System.out.println("Nombre de discipline de livres: " + disciplineLivres.length);   

         

      //parcours de tous les disciplines du livres   

       for (LivreType l : disciplineLivres){              

            System.out.println(l.getDiscipline());   

             //parcours de toutes les catégories de chaque discipline de livre   

             CategorieType[] lesCategories =  l.getCategorieArray();   

             for (CategorieType c : lesCategories){                              

                System.out.println(c.getNom()+ " " + c.getTitre() + " " + c.getNombrePage() +" "+ c.getAuteur());                

                EditionType edition = c.getEdition();   

                System.out.println(edition.getEditeur()+ " " + edition.getAnneeEdition());           

              }          

              System.out.println();                   

       }   
		
		

		
	}

}
