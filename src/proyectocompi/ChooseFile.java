/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompi;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Phoenix
 * Extraido de:
 * http://stackoverflow.com/questions/7494478/jfilechooser-from-a-command-line-program-and-popping-up-underneath-all-windows
 */
  public class ChooseFile {
    private JFrame frame;
    public ChooseFile() {
        frame = new JFrame();

        frame.setVisible(true);
        BringToFront();
    }
    public File getFile() {
        JFileChooser fc = new JFileChooser();
        if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)){
            frame.setVisible(false);
            return fc.getSelectedFile();
        }else {
            System.out.println("Next time select a file.");
            System.exit(1);
        }
        return null;
    }

    private void BringToFront() {                  
                    frame.setExtendedState(JFrame.ICONIFIED);
            frame.setExtendedState(JFrame.NORMAL);

    }

}
