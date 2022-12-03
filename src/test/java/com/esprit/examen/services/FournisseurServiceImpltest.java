package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.repositories.StockRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

@ExtendWith(SpringExtension.class)
class FournisseurServiceImpltest {
	
	@InjectMocks
	FournisseurServiceImpl fournisseurServiceImpl;
	
    @Mock
    private FournisseurRepository fournisseurRepository;
    
    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;
    private SecteurActiviteRepository secteurActivityRepository;
    
    private Fournisseur fournisseur1 ;
    private Fournisseur fournisseur2 ;
    private SecteurActivite secteurActivity ;
    
    @BeforeEach
    void setUp(){
    	fournisseur1 = new Fournisseur (null,"365425","fournisseur1",null,null,null,null);
    	fournisseur2 = new Fournisseur(null,"365425","fournisseur2",null,null,null,null);
    	secteurActivity = new SecteurActivite();
    }
	
	@Test 
	void testAddFournisseur() {
    	when(fournisseurRepository.save(fournisseur1)).thenReturn(fournisseur1);
    
    	Fournisseur persistedProduct = fournisseurServiceImpl.addFournisseur(fournisseur1);
    	
		assertEquals(fournisseur1, persistedProduct); 
	} 
	
	@Test 
     void testretRieveAllFournisseurs() {
    	List<Fournisseur> listOfProducts=new ArrayList<Fournisseur>();
    	listOfProducts.add(fournisseur1);
    	listOfProducts.add(fournisseur2);
		when(fournisseurRepository.findAll()).thenReturn(listOfProducts);
    	assertEquals(2,fournisseurServiceImpl.retrieveAllFournisseurs().size());
    	assertEquals(listOfProducts,fournisseurServiceImpl.retrieveAllFournisseurs());
    }
    @Test 
    void testUpdateFournisseur() {
    	when(fournisseurRepository.save(fournisseur1)).thenReturn(fournisseur1);
    	assertEquals(fournisseur1, fournisseurServiceImpl.updateFournisseur(fournisseur1));
    }
    @Test
     void testRetrieveFournisseur() {
    	when(fournisseurRepository.findById(fournisseur1.getIdFournisseur())).thenReturn(Optional.of(fournisseur1));
    	
    	assertEquals(fournisseur1, fournisseurServiceImpl.retrieveFournisseur(fournisseur1.getIdFournisseur()));
    }
   
}