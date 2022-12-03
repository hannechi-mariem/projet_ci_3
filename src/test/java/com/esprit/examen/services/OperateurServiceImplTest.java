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

import javax.persistence.OneToMany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

@ExtendWith(SpringExtension.class)
public class OperateurServiceImplTest {
	
	@InjectMocks
	OperateurServiceImpl operateurServiceImpl;
	
    @Mock
    private OperateurRepository operateurRepository;
    
    @Mock
    private StockRepository stockRepository;
    
    private Operateur operateur1 ;
    private Produit operateur2 ;
    
    
    @BeforeEach
    void setUp(){
    	
    	operateur1 = new Operateur(null,null,"operateur1", "test1" ,null);
    	
    	operateur1 = new Operateur(null,null,"operateur2", "test2" ,null);
    
    }
	
	@Test 
	public void testAddOperateur() {
    	when(operateurRepository.save(operateur1)).thenReturn(operateur1);
    
    	Operateur persistedProduct = operateurServiceImpl.addOperateur(operateur1);
    	
		assertEquals(operateur1, persistedProduct); 
	} 
	
	@Test 
     void testRetrieveAllOperators() {
    	List<Operateur> listOfProducts=new ArrayList<Operateur>();
    	listOfProducts.add(operateur1);
    	listOfProducts.add(operateur1);
		when(operateurRepository.findAll()).thenReturn(listOfProducts);
    	assertEquals(2,operateurServiceImpl.retrieveAllOperateurs().size());
    	assertEquals(listOfProducts,operateurServiceImpl.retrieveAllOperateurs());
    }
    @Test 
    public void testUpdateOperateur() {
    	when(operateurRepository.save(operateur1)).thenReturn(operateur1);
    	assertEquals(operateur1, operateurServiceImpl.updateOperateur(operateur1));
    }
    @Test
     void testRetrieveOperateur() {
    	when(operateurRepository.findById(operateur1.getIdOperateur())).thenReturn(Optional.of(operateur1));
    	
    	assertEquals(operateur1, operateurServiceImpl.retrieveOperateur(operateur1.getIdOperateur()));
    }

}
