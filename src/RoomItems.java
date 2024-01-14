import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class RoomItems {
    protected JPanel roomPanel;
    protected JFrame frame;
    RoomItems(JFrame baseFrame, JPanel panel){
        frame=baseFrame;
        roomPanel=panel;
    }
    protected JPanel showImage(String imageName,JPanel ogPanel){
        JPanel imagePanel=new JPanel();
        imagePanel.setSize(800,600);
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        Image pic = image.getImage(); // transform it
        Image newimg = pic.getScaledInstance(800, 600,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        image = new ImageIcon(newimg);
        imagePanel.add(new JLabel(image));
        frame.add(imagePanel);
        ogPanel.setVisible(false);
        return imagePanel;
    }
    protected JButton createButton(String imageName, int x, int y, JPanel panel){
        JButton button=new JButton(new ImageIcon(getClass().getResource(imageName)));
        button.setBounds(x,y, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.setBorderPainted(false); //maybe get rid of
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        panel.add(button);
        return button;
    }
    protected JDialog catText(String message, String imageName,JButton button){
        JDialog catDialog = new JDialog(frame, "You");
        ImageIcon cat=new ImageIcon(getClass().getResource(imageName));
        catDialog.setLayout(new BorderLayout());
        catDialog.setPreferredSize(new Dimension(600, 300)); //size of dialog
        JPanel catPanel=new JPanel();
        JLabel imageLabel=new JLabel(cat);
        catPanel.add(imageLabel);
        JLabel textLabel=new JLabel(message);
        catPanel.add(textLabel);
        catDialog.add(catPanel, BorderLayout.CENTER);
        catDialog.add(button, BorderLayout.SOUTH);
        catDialog.pack();
        catDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        catDialog.setVisible(true);
        return catDialog;
    }
    protected void messageText(String message, String title, String buttonLabel){

    }
}
