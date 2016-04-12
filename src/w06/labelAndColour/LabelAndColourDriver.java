package w06.labelAndColour;

import java.awt.*;

public class LabelAndColourDriver {

    public static void main(String[] args) {
        LabelAndColour lacDefault = new LabelAndColour();
        lacDefault.setVisible(true);

        LabelAndColour lacBlue = new LabelAndColour(Color.BLUE);
        lacBlue.setVisible(true);
    }
}
