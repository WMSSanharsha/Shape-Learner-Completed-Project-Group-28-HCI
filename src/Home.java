import models.ShapeDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

//Home Page
public class Home extends JFrame {
    public  Home(){
        setLayout(new BorderLayout());
        setSize(1024,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new HeaderPanel(),BorderLayout.NORTH);
        add(new FooterPanel(this) , BorderLayout.SOUTH);
        add(new ContentPanel() , BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Home().setVisible(true);
    }
}

class HeaderPanel extends  JPanel{
    public HeaderPanel(){
        setBorder(new EmptyBorder(20, 20, 20, 10));
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        Label heading = new Label("Shape Learner");
        Label subHeading = new Label("Easy way to learn shapes");
        heading.setFont(new Font("Arial",Font.BOLD,30));
        add(heading);
        subHeading.setFont(new Font("Arial",Font.PLAIN,20));
        add(subHeading);
    }
}

class FooterPanel extends  JPanel{
    public FooterPanel(Home home){
        setBorder(new EmptyBorder(20, 20, 20, 20));
        GridLayout gridLayout = new GridLayout(0,2);
        setLayout(gridLayout);
        Label heading = new Label("Let’s start learning shapes");
        JButton continueBtn = new JButton("Let's Go");
        heading.setFont(new Font("Arial",Font.BOLD,20));
        continueBtn.setBackground(Color.blue);
        continueBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setVisible(false);
                new ShapeEditorScreen(new ShapeDTO("Square",new Square()),0);
            }
        });
        continueBtn.setForeground(Color.white);
        continueBtn.setFocusPainted(false);
        continueBtn.setBorder(new EmptyBorder(15,10,15,10));
        continueBtn.setFont(new Font("Arial",Font.BOLD,16));
        add(heading);
        add(continueBtn);
    }
}

class ContentPanel extends  JPanel{
    Image backgroundImage;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null){
            g.drawImage(backgroundImage,0,0,this);
        }
    }

    public ContentPanel() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        loadImage();
    }

    public void loadImage(){
        String projectDirectory = System.getProperty("user.dir");
        File file = new File(projectDirectory+"\\src\\Images\\1712340556.jpg");
        try {
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

