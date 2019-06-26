import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class InputAlert{
    String alert = "";
    public InputAlert(NameField na1, NumberField nu1, NameField na2, NumberField nu2, NameField na3, NumberField nu3) {
        if(!isValidInput(na1, nu1, na2, nu2, na3, nu3)){
            alert = "Invalid input error";
        }else{
            alert = "Data Saved";
            na1.setEditable(false);
            na2.setEditable(false);
            na3.setEditable(false);
            nu1.setEditable(false);
            nu2.setEditable(false);
            nu3.setEditable(false);
        }

    }

    public boolean isValidInput(NameField na1, NumberField nu1, NameField na2, NumberField nu2, NameField na3, NumberField nu3){
        return na1.isValid() && nu1.isValid() && na2.isValid() && nu2.isValid() && na3.isValid() && nu3.isValid();
    }

    public Alert getPopup(){
        Alert a = new Alert(Alert.AlertType.NONE,"", ButtonType.CLOSE);
        if(alert.equals("Invalid input error")){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Invalid input: you have attempted to provide one or more invalid input(s). Please correct the information displayed in red and retry.");
            a.setTitle(alert);
        }else if(alert.equals("Data Saved")){
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("The profiles have been saved and added to the database.");
            a.setTitle(alert);
        }
        return a;
    }
}
