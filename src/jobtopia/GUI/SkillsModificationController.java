package jobtopia.GUI;

import jobtopia.Entities.Skills;
import jobtopia.Entities.EtatEnum;
import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jobtopia.Services.ServiceSkills ;


public class SkillsModificationController implements Initializable {

    @FXML
    private TextField skillmod;
    
    @FXML
    private Button enregistrer;
    
    private Skills skills;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    }

    @FXML
    private void validerButton(ActionEvent event) {
        Skills s = skills;
        s.setNom(skillmod.getText());

        ServiceSkills ss = new ServiceSkills();
        boolean resultat = ss.modifier(s);
        if (resultat) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Cette skill à été modifiée avec succès");
            alert.showAndWait();
        }
        Stage stage = (Stage) enregistrer.getScene().getWindow();
        stage.close();
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
        skillmod.setText(skills.getNom());
       
        
    }
}
