
package ohtu.verkkokauppa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author laurareivinen
 */
public class KauppaTest {
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    
    public KauppaTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pankki = mock(Pankki.class);   
        this.viite = mock(Viitegeneraattori.class);
        this.varasto = mock(Varasto.class);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
    
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);


        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void voidaanOstaaKaksiEriTuotetta() {
         // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);


        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(8));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
        
    }
    
     @Test
    public void voidaanOstaaKaksiSamaaTuotetta() {
         // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);


        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(10));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
        
    }
    
     @Test
    public void koriinLisataanKaksiTuotettaJaToinenOnLoppu() {
         // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 3));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
        
    }
    
    @Test
    public void kaytetaanUuttaViitettaJokaMaksuun() {

        Kauppa k = new Kauppa(varasto, pankki, viite);  
        // määrittelemme minkä arvon viitegeneraattori palauttaa kun sen metodia
        // seuraava() kutsutaan
        when(viite.uusi()).thenReturn(55);

        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.lisaaKoriin(5);
        kauppa.tilimaksu("pekka", "12345");

        verify(viite, times(1)).uusi();
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("mari", "12346");

        verify(viite, times(2)).uusi();
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(viite, times(3)).uusi();
    }
    
    @Test
    public void aloitaAsiointiNollaaSumman() {
        Kauppa kauppa=new Kauppa(varasto, pankki, viite);
        
        when(varasto.saldo(1)).thenReturn(20); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("mari", "12346");
        
        verify(viite, times(1)).uusi();
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(10));
    }
    
    @Test
    public void poistaKoristaToimii() {
        Kauppa kauppa=new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.poistaKorista(5);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(0));   
    }
    
}
