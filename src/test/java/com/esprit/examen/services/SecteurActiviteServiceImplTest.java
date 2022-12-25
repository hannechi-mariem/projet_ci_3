package com.esprit.examen.services;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertNull;
 import static org.mockito.Mockito.when;

 import java.text.ParseException;
 import java.util.Optional;

 import com.esprit.examen.entities.*;
 import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;

import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class SecteurActiviteServiceImplTest {

	@InjectMocks
    SecteurActiviteServiceImpl secteurActiviteService;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    private SecteurActivite secteurActivite1 ;
    private SecteurActivite secteurActivite2 ;

    @BeforeEach
    void setUp(){
    	secteurActivite1 = new SecteurActivite(1L, "Categorie1", "libelle1", null);
    	secteurActivite2 = new SecteurActivite(2L, "Categorie2", "libelle2", null);
    }

    @Test
     void testAddSecteurActivite() {
        when(secteurActiviteRepository.save(secteurActivite1)).thenReturn(secteurActivite1);

        SecteurActivite persistedSecteurActivite = secteurActiviteService.addSecteurActivite(secteurActivite1);

        assertEquals(secteurActivite1, persistedSecteurActivite);
    }

    @Test
    void testRetrieveSecteurActivite() {
        when(secteurActiviteRepository.findById(secteurActivite1.getIdSecteurActivite())).thenReturn(Optional.of(secteurActivite1));

        assertEquals(secteurActivite1, secteurActiviteService.retrieveSecteurActivite(secteurActivite1.getIdSecteurActivite()));
    }

    @Test
     void testDeleteSecteurActivite() throws ParseException {
        SecteurActivite c = new SecteurActivite(3L, "Categorie3", "libelle3", null);
        SecteurActivite secteurActivite = secteurActiviteService.addSecteurActivite(c);
        secteurActiviteService.deleteSecteurActivite(secteurActivite.getIdSecteurActivite());
        assertNull(secteurActiviteService.retrieveSecteurActivite(secteurActivite.getIdSecteurActivite()));
    }
    @Test
     void testUpdateSecteurActivite() {
        when(secteurActiviteRepository.save(secteurActivite1)).thenReturn(secteurActivite1);
        assertEquals(secteurActivite1, secteurActiviteService.updateSecteurActivite(secteurActivite1));
    }

}
