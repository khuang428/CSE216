import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Button;

public class MyButton extends Button {

    public MyButton(){
        super("Create Profiles");
        this.setDisable(true);
    }

    public void enable(NameField na1, NumberField nu1, NameField na2, NumberField nu2, NameField na3, NumberField nu3){
        BooleanBinding bind = na1.textProperty().isEmpty().or(nu2.textProperty().isEmpty()).or(na2.textProperty().isEmpty()).or(
                              nu2.textProperty().isEmpty()).or(na3.textProperty().isEmpty()).or(nu3.textProperty().isEmpty());
        disableProperty().bind(bind);
    }

}
