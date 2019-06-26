import javafx.scene.control.TextField;

public class NameField extends TextField {

    public NameField(){
        super.setPromptText("Name");
    }

    public boolean isValid(){
        return getText().length() <= 20 && getText().matches("[A-Z][a-z]*[\\s][A-Z][a-z]*");
    }

    public void color(){
        if(isValid()){
            setStyle("-fx-text-fill:black;");
        }else{
            setStyle("-fx-text-fill:red;");
        }
    }

    public void setColor(){
        focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if(!isFocused()){
                color();
            }else{
                setStyle("-fx-text-fill:black;");
            }
        }));
    }
}
