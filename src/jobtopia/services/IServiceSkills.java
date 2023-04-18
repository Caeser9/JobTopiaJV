/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobtopia.Services;
import java.util.Observable;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Mohamed
 */
public interface IServiceSkills<T> {
    public Boolean add(T t);
    public ObservableList<T> afficher();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);
    
}
