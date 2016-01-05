import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by lukas on 05.01.2016.
 */
public class HelloWorld {

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                else if (f.getName().endsWith(".csv"))
                    return true;
                else
                    return false;
            }

            @Override
            public String getDescription() {
                return "*.csv";
            }
        });
        try {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            System.out.println(file.getName());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return;
        }
    }
}

