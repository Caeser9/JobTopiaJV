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
public class Skills {
    int id; 
    String nom; 

    public Skills() {
    }

    public Skills(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Skills(String nom) {
        this.nom = nom;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Skills{" + "id=" + id + ", nom=" + nom + '}';
    }
   
    
    
}
