package w06.firstWindowRewrite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndButtonClickListener implements ActionListener {
    //the following is the only method in the ActionListener Interface
    public void actionPerformed(ActionEvent e){

        System.exit(0); //exit program normally
    }
}

