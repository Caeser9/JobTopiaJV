package jobtopia.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import jobtopia.Entities.Candidature;

public class Scoring {

    Candidature c = new Candidature();
    ServiceCandidature sc = new ServiceCandidature();
    private String filepath;
    private String nomOffre;

    public void scoringWithPython(String nomOffre, String filepath) throws IOException, InterruptedException {
        this.filepath = filepath;
        this.nomOffre = nomOffre;
        System.out.println(nomOffre + "\n" + filepath);
        ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\HP\\Documents\\myfuckingfiles\\esprit\\PI_DEV\\JobTopia\\src\\jobtopia\\services\\resumeScoring.py");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        // get input stream to provide input to the script
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

        // get output stream to receive output from the script
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        // provide input values to the script
        writer.write(nomOffre + "\n");
        writer.write(filepath + "\n");
        writer.flush();

        // read the script's output
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            // add print statement to show the output
            System.out.println(line);
            c.setScore(line);
            System.out.println(c.getScore());

        }

        int exitCode = p.waitFor();

    }
}
