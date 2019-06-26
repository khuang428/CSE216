import javafx.embed.swing.JFXPanel;

import static org.junit.Assert.*;

public class InputAlertTest {
    JFXPanel panel = new JFXPanel();
    NameField na1;
    NameField na2;
    NameField na3;
    NameField na4;
    NameField na5;
    NameField na6;
    NumberField nu1;
    NumberField nu2;
    NumberField nu3;
    NumberField nu4;
    NumberField nu5;
    NumberField nu6;
    MyButton b;

    @org.junit.Before
    public void setUp(){
        na1 = new NameField();
        na1.setText("e");
        na2 = new NameField();
        na2.setText("f");
        na3 = new NameField();
        na3.setText("g");
        na4 = new NameField();
        na4.setText("Hek Ko");
        na5 = new NameField();
        na5.setText("Bob Ross");
        na6 = new NameField();
        na6.setText("Jo Fish");

        nu1 = new NumberField();
        nu1.setText("1");
        nu2 = new NumberField();
        nu2.setText("2");
        nu3 = new NumberField();
        nu3.setText("3");
        nu4 = new NumberField();
        nu4.setText("(111) 222-3333");
        nu5 = new NumberField();
        nu5.setText("(123) 456-7890");
        nu6 = new NumberField();
        nu6.setText("(742) 578-3578");

        b = new MyButton();
    }

    @org.junit.After
    public void tearDown(){
        na1 = null;
        na2 = null;
        na3 = null;
        na4 = null;
        na5 = null;
        na6 = null;
        nu1 = null;
        nu2  = null;
        nu3 = null;
        nu4 = null;
        nu5  = null;
        nu6 = null;
        b = null;
    }

    @org.junit.Test
    public void invalidTest(){
        InputAlert a = new InputAlert(na1, nu1, na2, nu2, na3, nu3);
        assertEquals("Invalid input error",a.alert);
    }

    @org.junit.Test
    public void validTest(){
        InputAlert a = new InputAlert(na4, nu4, na5, nu5, na6, nu6);
        assertEquals("Data Saved",a.alert);
        assertFalse(na4.isEditable());
        assertFalse(nu4.isEditable());
        assertFalse(na5.isEditable());
        assertFalse(nu5.isEditable());
        assertFalse(na6.isEditable());
        assertFalse(nu6.isEditable());
    }
}