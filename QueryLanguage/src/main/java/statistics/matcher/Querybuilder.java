
package statistics.matcher;

import statistics.Player;
import statistics.PlayerReaderImpl;
import statistics.Statistics;

public class Querybuilder {
    private Matcher m;

    Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
    
    public Querybuilder() {
        this.m = new All();
    }
       
    public Querybuilder playsIn(String team) {
        Matcher vanha = m;
        Matcher uusi =new PlaysIn(team);
        this.m = new And(vanha, uusi);
        return this;
    }

    public Querybuilder hasAtLeast(int luku, String goals) {
        Matcher vanha = m;
        Matcher uusi=new HasAtLeast(luku, goals);
        this.m = new And(vanha, uusi);
        return this;
    }
    
    public Querybuilder hasFewerThan(int luku, String goals) {
        Matcher vanha = m;
        Matcher uusi=new HasFewerThan(luku, goals);
        this.m = new And(vanha, uusi);
        return this;
    }
    
    public Querybuilder oneOf(Matcher m1, Matcher m2) {
        
        this.m = new Or(m1, m2);
        return this;
    }

    
    public Matcher build() {
        return m;
    }
}
