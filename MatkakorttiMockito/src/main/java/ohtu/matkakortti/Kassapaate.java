package ohtu.matkakortti;

public class Kassapaate {
    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }
    
    public void lataa(Matkakortti kortti, int summa){
        if (summa > 0) {
            kortti.lataa(summa);
        } else {
            kortti.lataa(0);
        }
    }
    
    public void ostaLounas(Matkakortti kortti) {
        int saldo = kortti.getSaldo();
        if(saldo >= HINTA) {
            kortti.osta(HINTA);
            myytyjaLounaita++;
        } 
    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}