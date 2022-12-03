
        package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import com.esprit.examen.services.ProduitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

@ExtendWith(SpringExtension.class)
public class ReglementServiceImpTest {

    @InjectMocks
    ReglementServiceImpl reglementService;

    @Mock
    private ReglementRepository reglementRepository;

    @Mock
    private FactureRepository factureRepository;

    private Reglement reglement1 ;
    private Reglement reglement2 ;
    private Facture facture;

    @BeforeEach
    void setUp(){
        reglement1 = new Reglement(null,200,50,true,new Date(),null);
        reglement2 = new Reglement(null,100,20,false,new Date(),null);
        facture =new Facture();

    }

    @Test
    public void testAddReglement() {
        when(reglementRepository.save(reglement1)).thenReturn(reglement1);

        Reglement persistedReglement = reglementService.addReglement(reglement1);

        assertEquals(reglement1, persistedReglement);
    }

    @Test
    void testRetrieveAllReglements() {
        List<Reglement> listOfReglements=new ArrayList<Reglement>();
        listOfReglements.add(reglement1);
        listOfReglements.add(reglement2);
        when(reglementRepository.findAll()).thenReturn(listOfReglements);
        assertEquals(2,reglementService.retrieveAllReglements().size());
        assertEquals(listOfReglements,reglementService.retrieveAllReglements());
    }

    @Test
    void testRetrieveReglement() {
        when(reglementRepository.findById(reglement1.getIdReglement())).thenReturn(Optional.of(reglement1));

        assertEquals(reglement1, reglementService.retrieveReglement(reglement1.getIdReglement()));
    }


}