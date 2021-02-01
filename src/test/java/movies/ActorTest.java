/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
Mennään näyttelijäsivulle
Tarkistetaan ettei sivulla ole tekstiä "Uuno Turhapuro"
Etsitään kenttä, jonka id on "name" ja asetetaan kenttään teksti "Uuno Turhapuro".
Lähetetään lomake.
Tarkistetaan että sivulla on teksti "Uuno Turhapuro"
Klikataan "Uuno Turhapuro"on liittyvää poista-nappia
Tarkistetaan ettei sivulla ole tekstiä "Uuno Turhapuro"
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorTest extends org.fluentlenium.adapter.junit.FluentTest{
    
    @LocalServerPort
    private Integer port;    
    

    @Test
    public void uunotus() {

        goTo("http://localhost:" + port + "/actors");

        assertFalse(pageSource().contains("Uuno Turhapuro"));

        
        // Etsi kenttä, jonka attribuutin 'id' arvo on nimi ja täytä kentän arvoksi Rolle
        find("#name").fill().with("Uuno Turhapuro");

        // Lähetä lomake
        find("form").first().submit();
        
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        $("#remove-1").submit();
        //find(".form-group").first().find("button").click();

        // Varmista, että sivulle on lisätty Rolle
        assertFalse(pageSource().contains("Uuno Turhapuro"));

    }    
}
