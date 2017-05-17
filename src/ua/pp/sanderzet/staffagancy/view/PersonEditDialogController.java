package ua.pp.sanderzet.staffagancy.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ua.pp.sanderzet.staffagancy.model.Person;
import ua.pp.sanderzet.staffagancy.util.DateUtil;

/**
 * Created by alzet on 26.04.17.
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField passportField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField dateOfContractField;
    @FXML
    private TextField sanBookField;
    @FXML
    private TextField endOfVisaField;
    @FXML
    private TextField fileNumberField;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

private Stage personAddStage;
private Person person;
private boolean okClicked = false;

    @FXML
    private void initialize () {

//        If pressed Enter - button must fire (not only Space pressed)
        okButton.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                okButton.fire();
            }
        });
        cancelButton.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                cancelButton.fire();
            }
        });

    }


    public void setPersonAddStage(Stage personAddStage) {
        this.personAddStage = personAddStage;
    }


    public void setPerson(Person person){
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        passportField.setText(person.getPassport());
        phoneField.setText(person.getPhone());
dateOfContractField.setText(DateUtil.format(person.getDataOfContract()));
dateOfContractField.setPromptText("dd.mm.yyyy");
sanBookField.setText(person.getSanBook());
endOfVisaField.setText(DateUtil.format(person.getEndOfVisa()));
endOfVisaField.setPromptText("dd.mm.yyyy");
        fileNumberField.setText(person.getFileNumber());
    }


//public void setMainApp (MainApp mainApp) {
//        this.mainApp = mainApp;
//        jobTable.setItems(mainApp.getJobData());
//}

    public boolean isOkClicked(){
        return okClicked;
    }


@FXML
public void handleOk() {
        if (isInputValid()) {
            person.setLastName(lastNameField.getText());
            person.setFirstName(firstNameField.getText());
            person.setPassport(passportField.getText());
            person.setPhone(phoneField.getText());
            person.setDataOfContract(DateUtil.parse(dateOfContractField.getText()));
            person.setSanBook(sanBookField.getText());
            person.setEndOfVisa(DateUtil.parse(endOfVisaField.getText()));
            person.setFileNumber(fileNumberField.getText());
            okClicked = true;
            personAddStage.close();
        }
}


@FXML
public void handleCancel() {
        personAddStage.close();
}


public boolean isInputValid () {
String errorMessage = "";

if (lastNameField.getText() == null || lastNameField.getLength() == 0)
    errorMessage += "No valid last name\n";
if (dateOfContractField.getText() == null || dateOfContractField.getLength() == 0)
    errorMessage += "No valid date of contract\n";
else {
    if (!DateUtil.validDate(dateOfContractField.getText()))
        errorMessage += "No valid date of contract. Use the format dd.mm.yyyy !\n";
}

if (endOfVisaField.getText() == null || endOfVisaField.getLength() == 0)
    errorMessage += "No valid data of Visa expiration\n";
else {
    if (!DateUtil.validDate(endOfVisaField.getText()))
        errorMessage += "No valid date of Visa expiration. Use the format dd.mm.yyyy !\n";
}

if (errorMessage.length() == 0)
    return true;

else {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.initOwner(personAddStage);
    alert.setTitle("Invalid fields");
    alert.setHeaderText("Please correct invalid fields");
    alert.setContentText(errorMessage);
//    Text in alert window can be no seen completely so we need to do some manipulation
    alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
    alert.showAndWait();
    return false;
}

    }

}



