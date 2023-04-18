/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobtopia.Entities;

/**
 *
 * @author HP
 */
public class Candidature {

    private int id, offerID;

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public int getOfferID() {
        return offerID;
    }
    private String etatID, offreTitle, offreDescription, freelancerName, freelancerLName, freelancerEmail, freelancerProfession, offreCategorie, lettreMotivation,cv,score;

    public Candidature() {

    }

    public Candidature(int id) {
        this.id = id;
    }

    public Candidature(String offreTitle) {
        this.offreTitle = offreTitle;
    }

    public Candidature(int id, String etatID, String offreTitle, String offreDescription,String freelancerName) {

        this.id = id;
        this.etatID = etatID;
        this.offreTitle = offreTitle;
        this.offreDescription = offreDescription;
        this.freelancerName = freelancerName;

    }

    public void setOffreCategorie(String offreCategorie) {
        this.offreCategorie = offreCategorie;
    }

    public String getOffreCategorie() {
        return offreCategorie;
    }

    public Candidature(int id, String etatID, String offreTitle, String offreDescription, String freelancerName, String freelancerLName, String freelancerEmail, String freelancerProfession,String score) {
        this.id = id;
        this.etatID = etatID;
        this.offreTitle = offreTitle;
        this.offreDescription = offreDescription;
        this.freelancerName = freelancerName;
        this.freelancerLName = freelancerLName;
        this.freelancerEmail = freelancerEmail;
        this.freelancerProfession = freelancerProfession;
        this.score=score;
    }

    public Candidature(String etatID, int offreId, String freelancerName, String lettreMotivation, String cv, String score) {
        this.etatID = etatID;
        this.offerID = offreId;
        this.freelancerName = freelancerName;
        this.lettreMotivation = lettreMotivation;
        this.cv = cv;
        this.score = score;
        
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCv() {
        return cv;
    }

    public Candidature(String etatID, String offreTitle, String freelancerName) {
        this.etatID = etatID;
        this.offreTitle = offreTitle;
        this.freelancerName = freelancerName;

    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public int getId() {
        return id;
    }

    public String getEtatID() {
        return etatID;
    }

    public String getOffreTitle() {
        return offreTitle;
    }

    public String getOffreDescription() {
        return offreDescription;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public String getFreelancerLName() {
        return freelancerLName;
    }

    public String getFreelancerEmail() {
        return freelancerEmail;
    }

    public String getFreelancerProfession() {
        return freelancerProfession;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtatID(String etatID) {
        this.etatID = etatID;
    }

    public void setOffreTitle(String offreTitle) {
        this.offreTitle = offreTitle;
    }

    public void setOffreDescription(String offreDescription) {
        this.offreDescription = offreDescription;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public void setFreelancerLName(String freelancerLName) {
        this.freelancerLName = freelancerLName;
    }

    public void setFreelancerEmail(String freelancerEmail) {
        this.freelancerEmail = freelancerEmail;
    }

    public void setFreelancerProfession(String freelancerProfession) {
        this.freelancerProfession = freelancerProfession;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    

    @Override
    public String toString() {
        return "Candidature{" + "id=" + id + ", etat=" + etatID + ", offreDescription=" + offreTitle + ", freeelancer=" + freelancerName + '}';
    }

    public String showID() {
        return "Candidature numero: " + id;
    }

}
