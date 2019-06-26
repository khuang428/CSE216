import javafx.embed.swing.JFXPanel;

import static org.junit.Assert.*;

public class NumberFieldTest {
    JFXPanel panel = new JFXPanel();
    NumberField n1;
    NumberField n2;
    NumberField n3;
    NumberField n4;

    @org.junit.Before
    public void setUp(){
        n1 = new NumberField();
        n1.setText("123-456-7890");
        n2 = new NumberField();
        n2.setText("(121)555-0000");
        n3 = new NumberField();
        n3.setText("(847) 012-5674");
        n4 = new NumberField();
        n4.setText("(111) 222-3333");
    }

    @org.junit.After
    public void tearDown(){
        n1 = null;
        n2 = null;
        n3 = null;
        n4 = null;
    }

    @org.junit.Test
    public void setPromptText(){
        assertEquals("(###) ###-####", n1.getPromptText());
    }

    @org.junit.Test
    public void isValid() {
        assertFalse(n1.isValid());
        assertFalse(n2.isValid());
        assertTrue(n3.isValid());
        assertTrue(n4.isValid());
    }

    @org.junit.Test
    public void color() {
        n1.color();
        assertEquals("-fx-text-fill:red;", n1.getStyle());
        n2.color();
        assertEquals("-fx-text-fill:red;", n2.getStyle());
        n3.color();
        assertEquals("-fx-text-fill:black;", n3.getStyle());
        n4.color();
        assertEquals("-fx-text-fill:black;", n4.getStyle());
    }
}