package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Optional;

import com.esprit.examen.entities.*;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.repositories.StockRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class StockServiceImplTest {

	@InjectMocks
    StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    private Stock stock1 ;
    private Stock stock2 ;

    @BeforeEach
    void setUp(){
    	stock1 = new Stock("crr", 33, 33);
    	stock2 = new Stock("njkn", 334, 54);
    }

    @Test
     void testAddSecteurActivite() {
        when(stockRepository.save(stock1)).thenReturn(stock1);

        Stock persistedStock = stockService.addStock(stock1);

        assertEquals(stock1, persistedStock);
    }

    @Test
    void testRetrieveSecteurActivite() {
        when(stockRepository.findById(stock1.getIdStock())).thenReturn(Optional.of(stock1));

        assertEquals(stock1, stockService.retrieveStock(stock1.getIdStock()));
    }

   /* @Test
     void testDeleteStock() throws ParseException {
        Stock c = new Stock("f", 1, 1);
        Stock stock = stockService.addStock(c);
        stockService.deleteStock(stock.getIdStock());
        assertNull(stockService.retrieveStock(stock.getIdStock()));}
      */
        
    
    @Test
     void testUpdateSecteurActivite() {
        when(stockRepository.save(stock1)).thenReturn(stock1);
        assertEquals(stock1, stockService.updateStock(stock1));
    }

}
