import javafx.scene.control.TextField;

public class NumberField extends TextField {

    public NumberField(){
        super.setPromptText("(###) ###-####");
    }

    public boolean isValid(){
        return getText().matches("\\([\\d]{3}\\)[\\s][\\d]{3}-[\\d]{4}");
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
