
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm=0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko <0) {
            throw new IndexOutOfBoundsException("Kapasiteetti väärin");//heitin vaan jotain :D
        }       
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
    }

    public void kasvataTaulukkoa() {
        int[]taulukkoOld = lukujono;
        kopioiTaulukko(lukujono, taulukkoOld);
        lukujono = new int[alkioidenLkm + OLETUSKASVATUS];
        kopioiTaulukko(taulukkoOld, lukujono);
    }
    
    public void alustaLukujono(int luku) {
        lukujono[0] = luku;
        alkioidenLkm++;
    }
    
    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            alustaLukujono(luku);
            return true;
        } 
        if (!kuuluukoLukuJoukkoon(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukujono.length == 0) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluukoLukuJoukkoon(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }
    
    public void poistaLukuTaulukosta(int kohta) {
        int apu=0;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
    }
    
    public int etsiLuvunSijainti(int luku) {
        int kohta=-1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                lukujono[i] = 0;
                break;
            }
        }
        return kohta;
    }
    
    public boolean poista(int luku) {
        int kohta = etsiLuvunSijainti(luku);
        if (kohta != -1) {
            poistaLukuTaulukosta(kohta);           
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }
    
    public String luoStringLukujonosta() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujono[i];
            tuotos += ", ";
        }
        tuotos += lukujono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {            
            return luoStringLukujonosta();
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
    
  
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
 
        return z;
    }
        
}