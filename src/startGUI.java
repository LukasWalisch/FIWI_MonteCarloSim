import model.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class startGUI extends JFrame {


    private JButton button1;
    private JPanel root;

    public startGUI(){
        setContentPane(root);
        pack();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile openFile = new OpenFile();
                Stock ans=openFile.importStockFromCSV();
                //label.setText(String.valueOf(ans));
            }

        });
        setVisible(true);
    }

}
