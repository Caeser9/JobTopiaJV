/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobtopia.Services;

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

/**
 *
 * @author HP
 */
public class VoiceReader {

    private int id;

    public void readingWithPython(int id) throws IOException, InterruptedException {
        Candidature c = new Candidature();
        
        this.id=id;
        System.out.println(id);
        ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\HP\\Documents\\myfuckingfiles\\esprit\\PI_DEV\\JobTopia\\src\\jobtopia\\Services\\voiceReader.py");
        Process p = pb.start();

// get input stream to provide input to the script
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

// get output stream to receive output from the script
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

// provide input values to the script
        
        writer.write(id + "\n");
        reader.read();
        writer.flush();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = p.waitFor();
        
        //System.out.println("\nExited with error code " + exitCode);
    }
}
