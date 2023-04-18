/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobtopia.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jobtopia.Entities.Candidature;
import jobtopia.Entities.EtatEnum;
import jobtopia.Utils.MyDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static jdk.nashorn.internal.objects.NativeString.search;
import jobtopia.Entities.EtatEnum;

/**
 *
 * @author HP
 */
public class ServiceCandidature implements IService<Candidature> {

    Connection cnx;
    Statement stm;

    public ServiceCandidature() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public Boolean add(Candidature c) {
        int res = 0;
        try {
            String cv = c.getCv().replace("\\", "/");
            Statement stm = cnx.createStatement();
            String req = "INSERT INTO `candidatures`( `etat`, `offreID`, `freelancerID`,`lettreMotivation`,`cv`,`score`) VALUES ('" + c.getEtatID() + "',(Select `id` from Offres Where `titre`= '" + c.getOffreTitle() + "'),(Select `id` from users Where `nom`= '" + c.getFreelancerName() + "'),'"+c.getLettreMotivation()+"','"+cv+"','"+c.getScore()+"')";
            res = stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println("Error inserting Candidature: " + ex.getMessage());
            return false;
        }
        return res != 0;
    }

    @Override
    public ObservableList<Candidature> afficherFreelancer() {
        List<Candidature> candidature = new ArrayList();
        try {
            String qry = "SELECT c.id, c.etat, o.titre, o.description,  o.categorie, c.score FROM candidatures c  INNER JOIN offres o ON c.offreID = o.id INNER JOIN users u ON c.freelancerID = u.id where c.isDeleted=0 and u.role='freelancer' ";

            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Candidature c = new Candidature();
                c.setId(rs.getInt("c.id"));
                c.setEtatID(rs.getString("c.etat"));
                c.setOffreTitle(rs.getString("o.titre"));
                c.setOffreDescription(rs.getString("o.description"));
                c.setOffreCategorie(rs.getString("o.categorie"));
                c.setScore(rs.getString("c.score"));

                candidature.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(candidature);
    }

    @Override
    public ObservableList<Candidature> afficherClient() {
        List<Candidature> candidature = new ArrayList();
        try {
            String qry = "SELECT c.id, c.etat, o.titre, o.description, u.nom , u.prenom ,u.email, u.profession ,c.score FROM candidatures c  INNER JOIN offres o ON c.offreID = o.id INNER JOIN users u ON c.freelancerID = u.id where c.isDeleted=0 ";

            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Candidature c = new Candidature();
                c.setId(rs.getInt("c.id"));
                c.setEtatID(rs.getString("c.etat"));
                c.setOffreTitle(rs.getString("o.titre"));
                c.setOffreDescription(rs.getString("o.description"));
                c.setFreelancerName(rs.getString("u.nom"));
                c.setFreelancerLName(rs.getString("u.prenom"));
                c.setFreelancerEmail(rs.getString("u.email"));
                c.setFreelancerProfession(rs.getString("u.profession"));                
                c.setScore(rs.getString("c.score"));
                candidature.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(candidature);
    }

    /*
    public List<Candidature> affciher() {
        List<Candidature> candidature = new ArrayList();
        try {
            String qry = "SELECT c.id, c.etat, o.nom, u.nom FROM candidatures c  INNER JOIN offres o ON c.offreID = o.id INNER JOIN users u ON c.freelancerID = u.id Where c.isDeleted=0";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            if (rs.next()) {
                Candidature c = new Candidature();
                showID.setCellValueFactory(new PropertyValueFactory<Candidature, Integer>("c.id"));
                showetat.setCellValueFactory(new PropertyValueFactory<Candidature, String>("c.etat"));
                showoffre.setCellValueFactory(new PropertyValueFactory<Candidature, String>("o.nom"));
                showfreelancer.setCellValueFactory(new PropertyValueFactory<Candidature, String>("u.nom"));
                ObservableList<Candidature> data = FXCollections.observableArrayList(
                        new Candidature(rs.getInt("c.id"),rs.getString("etat"),rs.getString("o.nom"),rs.getString("u.nom")));

                table.getItems().addAll(data);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return candidature;
    
     */
    @Override
    public Boolean modifier(Candidature t) {
        int res = 0;
        try {

            stm = cnx.createStatement();
            String req = "UPDATE `candidatures` SET `etat`='" + t.getEtatID() + "',`offreID`=(Select `id` from `offres` where titre='" + t.getOffreTitle() + "'),`freelancerID`=(Select `id` from users where `nom`='" + t.getFreelancerName() + "') WHERE `id`='" + t.getId() + "';";
            res = stm.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        if (res != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean archiver(Candidature t) {
        int res = 0;
        try {

            stm = cnx.createStatement();
            String req = "UPDATE `candidatures` SET `isDeleted`='" + 1 + "' WHERE `id`='" + t.getId() + "';";
            res = stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return false;
    }

    public int affciheID(Candidature t) {
        List<Candidature> candidature = new ArrayList();
        try {
            String qry = "SELECT * FROM `candidatures` where id =" + t.getId();
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Candidature c = new Candidature();
                c.setId(rs.getInt("id"));

            }
            return 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @Override
    public int supprimer(Candidature t) {
        int res = 0;
        try {

            stm = cnx.createStatement();
            String req = "DELETE FROM `candidatures` WHERE id= " + t.getId();
            res = stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Boolean restaure(Candidature t) {
        int res = 0;
        try {
            stm = cnx.createStatement();
            String req = "UPDATE `candidatures` SET `isDeleted`='" + 0 + "' WHERE `id`='" + t.getId() + "';";
            res = stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Candidature> rechercheCandidature(String search) {
        List<Candidature> candidatures = new ArrayList<>();
        try {
            stm = cnx.createStatement();
            String req = "SELECT c.id, c.etat, o.titre, o.description, u.nom , u.prenom ,u.email, u.profession FROM candidatures c  INNER JOIN offres o ON c.offreID = o.id INNER JOIN users u ON c.freelancerID = u.id where (c.isDeleted=0 and u.role='client') and (o.titre LIKE '%" + search + "%'Or u.nom LIKE '%" + search + "%' Or c.etatLIKE '%" + search + "%' );";
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Candidature c = new Candidature();
                c.setId(rs.getInt("c.id"));
                c.setEtatID(rs.getString("c.etat"));
                c.setOffreTitle(rs.getString("o.titre"));
                c.setOffreDescription(rs.getString("o.description"));
                c.setFreelancerName(rs.getString("u.nom"));
                c.setFreelancerLName(rs.getString("u.prenom"));
                c.setFreelancerEmail(rs.getString("u.email"));
                c.setFreelancerProfession(rs.getString("u.profession"));
                candidatures.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return candidatures;
    }

    @Override
    public void runVoice(int id)throws IOException, InterruptedException {
        Candidature c = new Candidature();
        id = 18;

        ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\HP\\Documents\\myfuckingfiles\\esprit\\PI_DEV\\JobTopia\\src\\jobtopia\\Services\\voiceReader.py");
        Process p = pb.start();

// get input stream to provide input to the script
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

// get output stream to receive output from the script
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

// provide input values to the script
        writer.write(id + "\n");
        writer.flush();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = p.waitFor();
        System.out.println("\nExited with error code " + exitCode);
    }

}
