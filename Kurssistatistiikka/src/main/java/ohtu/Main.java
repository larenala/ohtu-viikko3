package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String kurssiUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String ohtuUrl = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String rubyUrl = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String kurssiText = Request.Get(kurssiUrl).execute().returnContent().asString();
        String ohtuText = Request.Get(ohtuUrl).execute().returnContent().asString();
        String rubyText=  Request.Get(rubyUrl).execute().returnContent().asString();


        Gson mapper = new Gson();
        
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        JsonElement element = mapper.fromJson (kurssiText, JsonArray.class);
        JsonArray kurssit = element.getAsJsonArray();
        JsonObject ohtu = kurssit.get(0).getAsJsonObject();
        JsonObject docker = kurssit.get(1).getAsJsonObject();
        JsonObject ruby = kurssit.get(2).getAsJsonObject();
        
        JsonElement ohtuElement = mapper.fromJson (ohtuText, JsonObject.class);
        JsonObject ohtuObj = ohtuElement.getAsJsonObject();
        
        JsonElement rubyElement = mapper.fromJson (rubyText, JsonObject.class);
        JsonObject rubyObj = rubyElement.getAsJsonObject();
        
        
        int tehtavia =0;
        int tunteja=0;

        System.out.println("opiskelijanumero " + studentNr);
        System.out.println();
        
        System.out.println(ohtu.get("fullName").getAsString() + " " + ohtu.get("term").getAsString() + " " + ohtu.get("year").getAsString());
        System.out.println();
        int ohtuKaikki=0;
        for (Submission submission : subs) {
            tehtavia=0;
            if(submission.getCourse() != null && submission.getCourse().equals(ohtu.get("name").getAsString())) {
                
                tehtavia += submission.getTehtavaMaara();
                ohtuKaikki += tehtavia;
                tunteja += submission.getHours();
                System.out.println("viikko " + submission.getWeek() + ":");
                System.out.print("  tehtyjä tehtäviä " + tehtavia + "/" + ohtu.get("exercises").getAsJsonArray().get(submission.getWeek()));
                System.out.println(submission);
            }
           
        }

        int kaikki=0;
        for(int i=0; i<ohtu.get("exercises").getAsJsonArray().size(); i++) {
            kaikki+=ohtu.get("exercises").getAsJsonArray().get(i).getAsInt();
        }
        System.out.println();
        System.out.println("yhteensä: " + ohtuKaikki + "/" + kaikki + " tehtävää " + tunteja + " tuntia");
        System.out.println();
        int palautukset =0;
        int tehtavatYht =0;
        int tunnitYht=0;

        for (Integer i=1; i<=ohtuObj.size(); i++) {
            String luku = Integer.toString(i);
            palautukset += ohtuObj.get(luku).getAsJsonObject().get("students").getAsInt();
            tehtavatYht += ohtuObj.get(luku).getAsJsonObject().get("exercise_total").getAsInt();
            tunnitYht += ohtuObj.get(luku).getAsJsonObject().get("hour_total").getAsInt();
        }
        
        System.out.println("Kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä "  + tehtavatYht +
        " kpl, aikaa käytetty yhteensä " + tunnitYht + " tuntia");
        /*tehtavia=0;
        tunteja=0;
        System.out.println();
        int dockerKaikki=0;
        System.out.println(docker.get("fullName").getAsString() + " " + docker.get("term").getAsString() + " " + docker.get("year").getAsString());
        System.out.println();
        for (Submission submission : subs) {
            tehtavia=0;
            if(submission.getCourse() != null && submission.getCourse().equals(docker.get("name").getAsString())) {
                
                tehtavia += submission.getTehtavaMaara();
                dockerKaikki+= tehtavia;
                tunteja += submission.getHours();
                System.out.println("viikko " + submission.getWeek() + ":");
                System.out.print("  tehtyjä tehtäviä " + tehtavia + "/" + docker.get("exercises").getAsJsonArray().get(submission.getWeek()));
                System.out.println(submission);
            }
            
        }
        kaikkitehtavat+=dockerKaikki;
        kokonaisaika+=tunteja;  
        kaikki=0;
        for(int i=0; i<docker.get("exercises").getAsJsonArray().size(); i++) {
            kaikki+=docker.get("exercises").getAsJsonArray().get(i).getAsInt();
        }
        System.out.println();
        System.out.println("yhteensä: " + dockerKaikki + "/" + kaikki + " tehtävää " + tunteja + " tuntia");*/
        System.out.println();
      
        
        tehtavia=0;
        tunteja=0;
        System.out.println();
        int rubyKaikki=0;
        System.out.println(ruby.get("fullName").getAsString() + " " + ruby.get("term").getAsString() + " " + ruby.get("year").getAsString());
        System.out.println();
        for (Submission submission : subs) {
            tehtavia=0;
            if(submission.getCourse() != null && submission.getCourse().equals(ruby.get("name").getAsString())) {               
                
                tehtavia += submission.getTehtavaMaara();
                rubyKaikki +=tehtavia;
                tunteja += submission.getHours();
                System.out.println("viikko " + submission.getWeek() + ":");
                System.out.print("  tehtyjä tehtäviä " + tehtavia + "/" + ruby.get("exercises").getAsJsonArray().get(submission.getWeek()));
                System.out.println(submission);
            }
        }

        kaikki=0;
        for(int i=0; i<ruby.get("exercises").getAsJsonArray().size(); i++) {
            kaikki+=ruby.get("exercises").getAsJsonArray().get(i).getAsInt();
        }
        System.out.println();
        System.out.println("yhteensä: " + rubyKaikki + "/" + kaikki + " tehtävää " + tunteja + " tuntia");
        
        System.out.println();
        palautukset =0;
        tehtavatYht =0;
        tunnitYht=0;
        System.out.println("koko: " + rubyObj.size());
        for (Integer i=1; i<=rubyObj.size(); i++) {
            String luku = Integer.toString(i);
            palautukset += rubyObj.get(luku).getAsJsonObject().get("students").getAsInt();
            tehtavatYht += rubyObj.get(luku).getAsJsonObject().get("exercise_total").getAsInt();
            tunnitYht += rubyObj.get(luku).getAsJsonObject().get("hour_total").getAsInt();
        }
        
        System.out.println("Kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä "  + tehtavatYht +
        " kpl, aikaa käytetty yhteensä " + tunnitYht + " tuntia");
        System.out.println();
        
    }
}