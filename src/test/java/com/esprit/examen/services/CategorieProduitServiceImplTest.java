package com.esprit.examen.services;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertNull;
 import static org.mockito.Mockito.when;

 import java.text.ParseException;
 import java.util.Optional;

 import com.esprit.examen.entities.*;
 import com.esprit.examen.repositories.CategorieProduitRepository;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class CategorieProduitServiceImplTest {

    @InjectMocks
    CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    private CategorieProduit categorieProduit1 ;
    private CategorieProduit categorieProduit2 ;

    @BeforeEach
    void setUp(){
        categorieProduit1 = new CategorieProduit(1L, "Categorie1", "libelle1", null);
        categorieProduit2 = new CategorieProduit(2L, "Categorie2", "libelle2", null);
    }

    @Test
     void testAddCategorieProduit() {
        when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);

        CategorieProduit persistedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit1);

        assertEquals(categorieProduit1, persistedCategorieProduit);
    }

    @Test
    void testRetrieveCategorieProduit() {
        when(categorieProduitRepository.findById(categorieProduit1.getIdCategorieProduit())).thenReturn(Optional.of(categorieProduit1));

        assertEquals(categorieProduit1, categorieProduitService.retrieveCategorieProduit(categorieProduit1.getIdCategorieProduit()));
    }

    @Test
     void testDeleteCategorieProduit() throws ParseException {
        CategorieProduit c = new CategorieProduit(3L, "Categorie3", "libelle3", null);
        CategorieProduit CategorieProduit = categorieProduitService.addCategorieProduit(c);
        categorieProduitService.deleteCategorieProduit(CategorieProduit.getIdCategorieProduit());
        assertNull(categorieProduitService.retrieveCategorieProduit(CategorieProduit.getIdCategorieProduit()));
    }
    @Test
     void testUpdateCategorieProduct() {
        when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);
        assertEquals(categorieProduit1, categorieProduitService.updateCategorieProduit(categorieProduit1));
    }

}
