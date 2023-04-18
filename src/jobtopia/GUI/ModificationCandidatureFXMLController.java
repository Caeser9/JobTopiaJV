package jobtopia.GUI;

import jobtopia.Entities.Candidature;
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
import jobtopia.Services.ServiceCandidature ;


public class ModificationCandidatureFXMLController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextField descp;
    @FXML
    private ComboBox<String> comboboxfd;
    @FXML
    private DatePicker DateCr;
    @FXML
    private DatePicker DateLimite;
    @FXML
    private TextField idClient;
    @FXML
    private Button enregistrer;
    @FXML
    private TextField upID;
    @FXML
    private TextField upEtat;
    @FXML
    private TextField upOff;
    @FXML
    private TextField upFree;
    @FXML
    private TextField delID;
    @FXML
    private ComboBox<String> etatChoice;
    private Candidature candidature;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EnumSet.allOf(EtatEnum.class).stream().forEach(s -> etatChoice.getItems().add(s.toString()));

    }

    @FXML
    private void validerButton(ActionEvent event) {
        Candidature c = candidature;
        c.setEtatID(etatChoice.getValue());
        c.setOffreTitle(upOff.getText());
        c.setFreelancerName(upFree.getText());

        ServiceCandidature sc = new ServiceCandidature();
        boolean resultat = sc.modifier(c);
        if (resultat) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Candidature modifiée avec succès");
            alert.showAndWait();
        }
        Stage stage = (Stage) enregistrer.getScene().getWindow();
        stage.close();
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
        etatChoice.setValue(candidature.getEtatID());
        upOff.setText(candidature.getOffreTitle());
        upFree.setText(candidature.getFreelancerName());
       
        
    }
}
