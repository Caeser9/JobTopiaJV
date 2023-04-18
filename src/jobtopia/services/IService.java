package jobtopia.Services;


import java.io.IOException;
import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public interface IService<T> {
    
    public ObservableList<T> afficherFreelancer();
    public ObservableList<T> afficherClient();
   public Boolean add(T t );
    public Boolean modifier(T t);
    public int supprimer(T t);
    public Boolean archiver(T t);
    public Boolean restaure(T t);
    public List<T> rechercheCandidature(String s);
    public void runVoice(int id)throws IOException, InterruptedException;
}

