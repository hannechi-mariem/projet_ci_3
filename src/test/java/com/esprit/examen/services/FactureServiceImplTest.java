package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import com.esprit.examen.entities.*;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class FactureServiceImplTest {

    @InjectMocks
    FactureServiceImpl factureService;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private OperateurRepository operateurRepository;

    private Facture facture1 ;
    private Facture facture2 ;
    private Operateur operateur ;

    @BeforeEach
    void setUp(){
        Set<Facture> set = new HashSet<>();
        facture1 = new Facture(1L, (float) 10.56, 300.58f, new Date(), new Date(), true, null, null, null);
        set.add(facture1);
        facture2 = new Facture(2L, (float) 20.88, 100.58f, new Date(), new Date(), true, null, null, null);
        operateur = new Operateur(1L,"Op1","OpPrenom","123456",set);
    }

    @Test
    void testAddFacture() {
        when(factureRepository.save(facture1)).thenReturn(facture1);

        Facture persistedFacture = factureService.addFacture(facture1);

        assertEquals(facture1, persistedFacture);
    }

    @Test
    void testRetrieveAllFactures() {
        List<Facture> listOfFactures=new ArrayList<Facture>();
        listOfFactures.add(facture1);
        listOfFactures.add(facture2);
        when(factureRepository.findAll()).thenReturn(listOfFactures);
        assertEquals(2,factureService.retrieveAllFactures().size());
        assertEquals(listOfFactures,factureService.retrieveAllFactures());
    }
    
    @Test
    void testRetrieveProduit() {
        when(factureRepository.findById(facture1.getIdFacture())).thenReturn(Optional.of(facture1));

        assertEquals(facture1, factureService.retrieveFacture(facture1.getIdFacture()));
    }
    @Test
    void testAssignOperateurToFacture() {
        when(factureRepository.findById(facture1.getIdFacture())).thenReturn(Optional.of(facture1));
        when(operateurRepository.findById(operateur.getIdOperateur())).thenReturn(Optional.of(operateur));
        when(factureRepository.save(facture1)).thenReturn(facture1);

        factureService.assignOperateurToFacture(facture1.getIdFacture(), operateur.getIdOperateur());

        verify(factureRepository,times(1)).findById(facture1.getIdFacture());
        verify(operateurRepository,times(1)).findById(operateur.getIdOperateur());

    }


}
