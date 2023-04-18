/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobtopia;

import java.util.Locale;
import jobtopia.Entities.Candidature;
import jobtopia.Services.ServiceCandidature;
import jobtopia.Utils.MyDB;
import jobtopia.Entities.Skills;
import jobtopia.Services.ServiceSkills;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.*;
import jobtopia.Services.Scoring;
import jobtopia.Services.VoiceReader;

/**
 *
 * @author HP
 */
public class JobTopia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //Candidature c = new Candidature(5,2,1,1); 
        //ServiceCandidature sc = new ServiceCandidature();
        //Candidature c = new Candidature("offretest") ;
        //System.out.println(sc.afficherFreelancer());

        /*Skills s = new Skills("css");
        ServiceSkills sc = new ServiceSkills();
        sc.add(s);
        System.out.println(sc.afficher());/
        try
        {
            PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
            PythonInterpreter interp = new PythonInterpreter();
            interp.execfile("C:/Users/HP/Documents/myfuckingfiles/esprit/PI_DEV/JobTopia/src/jobtopia/Services/resumeScoring.py");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         */
        // create a new ProcessBuilder for running the Python script
        /*ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\HP\\Documents\\myfuckingfiles\\esprit\\PI_DEV\\JobTopia\\src\\jobtopia\\Services\\resumeScoring.py");

        // start the process
        Process p = pb.start();

        // get the input stream of the process to read the output
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // read the output line by line
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        // wait for the process to finish
        try {
            int exitCode = p.waitFor();
            System.out.println("Python script exited with code " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // Set the text to be spoken
        /*try {
            String salut = "hello";
            // Créer un processus pour exécuter le script Python
            ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\HP\\Documents\\myfuckingfiles\\esprit\\PI_DEV\\JobTopia\\src\\jobtopia\\Services\\voiceReader.py", salut);

            Process process = pb.start();
            
            // Lire la sortie du script Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line;
            System.out.println("jobtopia.JobTopia.main()");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Attendre la fin de l'exécution du script Python
            int exitCode = process.waitFor();
            System.out.println("Le script Python a terminé avec le code de sortie " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }*/
        
        Candidature c = new Candidature();
        Scoring scoring = new Scoring();
        try {
            scoring.scoringWithPython("devops","C:/Users/HP/Documents/myfuckingfiles/esprit/PI_DEV/JobTopia/src/jobtopia/Services/kaycer-Resume.pdf");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        

    }
}
