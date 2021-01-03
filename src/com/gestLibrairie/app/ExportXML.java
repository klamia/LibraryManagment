package com.gestLibrairie.app;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlOptions;

import noNamespace.CategorieType;
import noNamespace.CategorieType.Auteur;
import noNamespace.CategorieType.Nom;
import noNamespace.CategorieType.NombrePage;
import noNamespace.CategorieType.Titre;
import noNamespace.EditionType;
import noNamespace.LibrairieDocument;
import noNamespace.LibrairieType;
import noNamespace.LivreType;



public class ExportXML {

	public static void main(String[] args) throws IOException {
		 //lib est une instance du document XML   

        LibrairieDocument lib =  LibrairieDocument.Factory.newInstance();   

           

        //ajout d'un tag librairie au document XML   

        LibrairieType librairie = lib.addNewLibrairie();   

           

        // ajout d'un livre au Librairie   

        LivreType livre1 = librairie.addNewLivre();                          

        livre1.setDiscipline("Informatique");   
        

        //Livre de discipline INFORMATIQUE, 1ère categorie    

        CategorieType categorie1 = livre1.addNewCategorie(); 

        categorie1.setNom(Nom.DEVELOPPEMENT_WEB);   
        categorie1.setTitre(Titre.HTML_5_ET_CSS_3);
           
        EditionType edition1 = EditionType.Factory.newInstance();            

        edition1.setEditeur("Eyrolles");   

        edition1.setAnneeEdition((short)2012);              

        categorie1.setEdition(edition1);     

        categorie1.setNombrePage((short)98);  

        categorie1.setAuteur(Auteur.JEAN_ENGELS);   

           

         //Livre de discipline INFORMATIQUE, 2ème categorie     

        CategorieType categorie2 = livre1.addNewCategorie(); 

        categorie2.setNom(Nom.CONCEPTION_ET_MODELISATION);   
        categorie2.setTitre(Titre.UML_2_POUR_LES_DEVELOPPEURS);
           
        EditionType edition2 = EditionType.Factory.newInstance();            

        edition2.setEditeur("Eyrolles");   

        edition2.setAnneeEdition((short)2014);              

        categorie2.setEdition(edition2);     

        categorie2.setNombrePage((short)215);  

        categorie2.setAuteur(Auteur.ISABELLE_MOUNIER);   

     /***************************************************************************************************/                          

        // ajout d'un Livre au Librairie 

        LivreType livre2 = librairie.addNewLivre();                          

        livre2.setDiscipline("Sciences et Techniques");
        
        //Livre de discipline Sciences et Techniques, 1ère categorie     

        CategorieType categorie3 = livre2.addNewCategorie(); 

        categorie3.setNom(Nom.ELECTRONIQUE);   
        categorie3.setTitre(Titre.L_ELECTRONIQUE_PAR_L_EXPÉRIENCE);
           
        EditionType edition3 = EditionType.Factory.newInstance();            

        edition3.setEditeur("Dunod");   

        edition3.setAnneeEdition((short)2000);              

        categorie3.setEdition(edition3);     

        categorie3.setNombrePage((short)65);  

        categorie3.setAuteur(Auteur.PIERRE_MAYE);   

           

        //Livre de discipline Sciences et Techniques, 2ème categorie    

        CategorieType categorie4 = livre2.addNewCategorie(); 

        categorie4.setNom(Nom.AUTOMOBILE);   
        categorie4.setTitre(Titre.LA_MAINTENANCE_AUTOMOBILE_EN_60_FICHES_PRATIQUES);
           
        EditionType edition4 = EditionType.Factory.newInstance();            

        edition4.setEditeur("Dunod");   

        edition4.setAnneeEdition((short)2009);              

        categorie4.setEdition(edition4);     

        categorie4.setNombrePage((short)132);  

        categorie4.setAuteur(Auteur.BRUNO_COLLOMB); 
           

        
        // Validation de l'instance du schema XML   

          boolean estValide = lib.validate();   

           

          if (! estValide){   

               System.err.println("Attention, cette instance du schema n'est pas valide ! ");   

               return;   

          }   

           

        XmlOptions opts = new XmlOptions();   

        //cette option permet d'indenter le code    

        opts.setSavePrettyPrint();   

           

        System.out.println(lib.xmlText(opts));   

        lib.save(new File ("E:/GESTLIB/librairie1.xml"), opts);		

	}

}
