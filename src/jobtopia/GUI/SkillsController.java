/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobtopia.GUI;

import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import jobtopia.Entities.Candidature;
import jobtopia.Entities.EtatEnum;
import jobtopia.Entities.Skills;
import jobtopia.Services.ServiceCandidature;
import jobtopia.Services.ServiceSkills;

/**
 *
 * @author HP
 */
public class SkillsController implements Initializable {

    @FXML
    private TextField SkillF;
    @FXML
    private TextField search;
    @FXML
    private TableView<Skills> skillsTab;
    @FXML
    private TableColumn<Skills, Void> modifierCol;
    @FXML
    private TableColumn<Skills, Void> supprimerCol;
    @FXML
    private TableColumn showid;
    @FXML
    private TableColumn showskills;
    @FXML
    private Label titreErrorLabel;
    @FXML
    private Label descpErrorLabel;
    
    Connection cnx;
    Statement stm;

    ServiceSkills ss = new ServiceSkills();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Skills> skills = FXCollections.observableArrayList(ss.afficher());

        //Data from DB
        ServiceSkills ss = new ServiceSkills();

        skillsTab.setItems(skills);
        showid.setCellValueFactory(new PropertyValueFactory<>("id"));
        showskills.setCellValueFactory(new PropertyValueFactory<>("nom"));

        //Recherche 
        FilteredList<Skills> filteredList = new FilteredList<>(skills, p -> true);
        skillsTab.setItems(filteredList);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Candidature -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Candidature.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                /*if (Candidature.getOffreCategorie().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }*/
                return false;
            });
        });
        //Delete+update buttons
        modifierCol.setCellFactory(new Callback<TableColumn<Skills, Void>, TableCell<Skills, Void>>() {
            @Override
            public TableCell<Skills, Void> call(final TableColumn<Skills, Void> param) {
                final TableCell<Skills, Void> cell = new TableCell<Skills, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            // Récupérer la candidature correspondante
                            Skills skills = getTableView().getItems().get(getIndex());

                            // Ouvrir la fenêtre de modification pour cette candidature
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("SkillsModification.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(SkillsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            SkillsModificationController controller = loader.getController();
                            controller.setSkills(skills);
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();

                            // Rafraîchir la table des candidatures
                            skillsTab.setItems(FXCollections.observableArrayList(ss.afficher()));
                        });
                    }

                    // Cette méthode updateItem() sera appelée pour chaque ligne de la tableOffres,
                    // ce qui permettra de mettre un bouton "Modifier" pour chaque ligne
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        supprimerCol.setCellFactory(new Callback<TableColumn<Skills, Void>, TableCell<Skills, Void>>() {
            @Override
            public TableCell<Skills, Void> call(final TableColumn<Skills, Void> param) {
                final TableCell<Skills, Void> cell = new TableCell<Skills, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Skills skills = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de la suppression");
                            alert.setHeaderText("Voulez-vous vraiment supprimer cette offre ?");
                            alert.setContentText("L'offre sera définitivement supprimée.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                ss.supprimer(skills);
                                skillsTab.setItems(FXCollections.observableArrayList(ss.afficher()));
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
    }
    @FXML

    private void afficher(ActionEvent event) {
        ObservableList<Skills> skills = FXCollections.observableArrayList(ss.afficher());
        ServiceSkills ss = new ServiceSkills();

        skillsTab.setItems(skills);
        showid.setCellValueFactory(new PropertyValueFactory<>("id"));
        showskills.setCellValueFactory(new PropertyValueFactory<>("nom"));

        }
    @FXML
    private boolean ajouter(ActionEvent event) {

        String NomSkill = SkillF.getText();
       

        //Controle de saisie des champs 
        boolean isValid = true;
        if (NomSkill == null || NomSkill.trim().isEmpty()) {
            titreErrorLabel.setText("IL faut saisir le titre!");
            isValid = false;
        }
        

        if (isValid) {
            Skills s = new Skills();
            s.setNom(SkillF.getText());

            ss.add(s);
            //skillsTab.getItems().setAll(ss.afficher());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Candidature ajoutée");
            alert.setHeaderText("Candidature ajoutée avec succès");
            alert.showAndWait();
        }

        return isValid;
    }
}
