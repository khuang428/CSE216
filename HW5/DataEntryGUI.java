import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataEntryGUI extends Application {

    @Override
    public void start(Stage primaryStage){
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10,10,10,10));

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);

        NameField na1 = new NameField();
        NameField na2 = new NameField();
        NameField na3 = new NameField();
        NumberField nu1 = new NumberField();
        NumberField nu2 = new NumberField();
        NumberField nu3 = new NumberField();
        MyButton b = new MyButton();

        vbox.getChildren().add(gp);
        vbox.getChildren().add(b);

        gp.add(na1,0,0);
        gp.add(nu1,1,0);
        gp.add(na2,0,1);
        gp.add(nu2,1,1);
        gp.add(na3,0,2);
        gp.add(nu3,1,2);

        na1.setColor();
        na2.setColor();
        na3.setColor();
        nu1.setColor();
        nu2.setColor();
        nu3.setColor();
        b.enable(na1,nu1,na2,nu2,na3,nu3);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InputAlert ia = new InputAlert(na1,nu1,na2,nu2,na3,nu3);
                Alert alert = ia.getPopup();
                alert.showAndWait();
            }
        });

        Scene s = new Scene(vbox, 400, 200);
        primaryStage.setTitle("Data Entry GUI");
        primaryStage.setMinHeight(190);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
