package w06.firstWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndButtonClickListener implements ActionListener {

    public void actionPerformed(ActionEvent e){ //the only method required by interface ActionListener

        System.exit(0); //exit normally

    }
}

