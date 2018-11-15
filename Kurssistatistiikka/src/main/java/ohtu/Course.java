
package ohtu;

public class Course {
    private String name;
    private String fullName;
    private int[] exercises;
    
    public Course(String name, String fullname, int[]exercises) {
        this.name = name;
        this.fullName=fullname;
        this.exercises=exercises;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getFullname() {
        return this.fullName;
    }
    
    public int getHarjoitustenMaara(int viikko) {
        return exercises[viikko];
    }
}
