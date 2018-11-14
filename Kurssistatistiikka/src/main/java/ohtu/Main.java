package ohtu;

import com.google.gson.Gson;
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
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String kurssiText = Request.Get(kurssiUrl).execute().returnContent().asString();
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
        System.out.println();
        System.out.println( kurssiText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        String [] palat= kurssiText.split(",");
        String str = mapper.fromJson("fullname", String.class);
        System.out.println(str);
        int tehtavia =0;
        int tunteja=0;
        System.out.println("opiskelijanumero " + studentNr);
        System.out.println();
        for (Submission submission : subs) {
            System.out.println(submission);
            tehtavia += submission.getTehtavaMaara();
            tunteja += submission.getHours();
        }
        System.out.println();
        System.out.println("yhteensä: " + tehtavia + " tehtävää " + tunteja + " tuntia");
        
    }
}