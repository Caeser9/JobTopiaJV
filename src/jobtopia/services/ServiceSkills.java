package jobtopia.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jobtopia.Entities.Candidature;
import jobtopia.Entities.Skills;
import jobtopia.Utils.MyDB;

/**
 *
 * @author HP
 */
public class ServiceSkills implements IServiceSkills<Skills> {

    Connection cnx;
    Statement stm;

    public ServiceSkills() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public Boolean add(Skills s) {
        int res = 0;
        try {
            Statement stm = cnx.createStatement();
            String req = "INSERT INTO `skills`(`nom`) VALUES ('" + s.getNom()+ "');";
            res = stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println("Error inserting Candidature: " + ex.getMessage());
            return false;
        }
        return res != 0;
    }

    @Override
    public ObservableList<Skills> afficher() {
       List<Skills> skills = new ArrayList();
        try {
            String qry = "SELECT `id`,`nom` FROM `skills`";
            
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Skills s = new Skills();
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("nom"));
                skills.add(s);
            }
          

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return FXCollections.observableArrayList(skills);
    }

    @Override
    public Boolean modifier(Skills s) {
         int res = 0;
        try {

            stm = cnx.createStatement();
            String req = "UPDATE `skills` SET `nom`= '"+s.getNom()+"' where id='"+s.getId()+"'";
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
    public Boolean supprimer(Skills s) {
       int res = 0;
        try {

            stm = cnx.createStatement();
            String req = "UPDATE `skills` SET `isDeleted`='" + 1 + "' WHERE `id`='" + s.getId() + "';";
            res = stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return false;
    }

    
}
