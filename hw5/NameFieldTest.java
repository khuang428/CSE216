import javafx.embed.swing.JFXPanel;

import static org.junit.Assert.*;

public class NameFieldTest {
    JFXPanel panel = new JFXPanel();
    NameField n1;
    NameField n2;
    NameField n3;
    NameField n4;

    @org.junit.Before
    public void setUp(){
        n1 = new NameField();
        n1.setText("abc def");
        n2 = new NameField();
        n2.setText("AbcDefgh");
        n3 = new NameField();
        n3.setText("Hillbilly Terreinson");
        n4 = new NameField();
        n4.setText("Beate Schrute");
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
        assertEquals("Name", n1.getPromptText());
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