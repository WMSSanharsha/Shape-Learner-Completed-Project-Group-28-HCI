import models.ShapeDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShapeEditorScreen extends JFrame {

    private final DataModel dataModel;
    private final ShapeDTO shapeDTO;
    private int i = 0;

    public ShapeEditorScreen(ShapeDTO shapeDTO , int i){
        this.i = i;
        dataModel = new DataModel();
        dataModel.setSelectedShape(shapeDTO);
        this.shapeDTO = shapeDTO;
        setSize(1078,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  // Use BorderLayout for the main frame
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new ShapeCanvas(dataModel , this , shapeDTO),BorderLayout.LINE_START);
        add(sidePanel(),BorderLayout.LINE_END);
        pack();
        setVisible(true);
    }

    private  JPanel  sidePanel(){
        JPanel sidePanel = new JPanel();
        sidePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        sidePanel.setPreferredSize(new Dimension(this.getWidth()/3,this.getHeight()));
        sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.white);

        JPanel shapeNamePanel = new JPanel();
        shapeNamePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        Label label = new Label(shapeDTO.getShapeName());
        label.setFont(new Font("Arial",Font.BOLD , 35));
        shapeNamePanel.add(headingLabel("Shape Name"));
        shapeNamePanel.add(label);
        shapeNamePanel.setBackground(Color.white);
        sidePanel.add(shapeNamePanel);

        JPanel colorChooserPanel = new JPanel();
        colorChooserPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        colorChooserPanel.add(headingLabel("Change Color"));
        JPanel colorChooser = createColorPickerPanel();
        colorChooserPanel.add(colorChooser);
        colorChooserPanel.setBackground(Color.white);
        sidePanel.add(colorChooserPanel);

        JPanel widthChooserPanel = new JPanel();
        widthChooserPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        widthChooserPanel.add(headingLabel("Change Width"));
        JSlider widthSlider = customSlider();
        widthSlider.addChangeListener(e -> {
            int width = widthSlider.getValue();
            dataModel.setShapeWidth(width);
        });
        widthChooserPanel.setBackground(Color.white);
        widthChooserPanel.add(widthSlider);
        sidePanel.add(widthChooserPanel);

        JPanel heightChooserPanel = new JPanel();
        heightChooserPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        heightChooserPanel.add(headingLabel("Change Height"));
        JSlider heightSlider = customSlider();
        heightSlider.addChangeListener(e -> {
            int height = heightSlider.getValue();
            dataModel.setShapeHeight(height);
        });
        heightChooserPanel.setBackground(Color.white);
        heightChooserPanel.add(heightSlider);
        sidePanel.add(heightChooserPanel);

        JPanel scaleChooserPanel = new JPanel();
        scaleChooserPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        scaleChooserPanel.add(headingLabel("Change Scale"));
        JSlider scaleSlider = customSlider();
        scaleSlider.addChangeListener(e -> {
            int scale = scaleSlider.getValue();
            dataModel.setShapeScale(scale);
        });
        scaleChooserPanel.setBackground(Color.white);
        scaleChooserPanel.add(scaleSlider);
        sidePanel.add(scaleChooserPanel);


        sidePanel.add(footerControl());

        return sidePanel;
    }

    JPanel footerControl(){
        JPanel footerControlPanel = new JPanel();
        footerControlPanel.setPreferredSize(new Dimension(200,50));
        footerControlPanel.setSize(new Dimension(this.getWidth()/3,30));
        footerControlPanel.setLayout(new GridLayout(2 , 3));
        JButton prevButton = new JButton("Previous");
        prevButton.setFocusPainted(false);
        prevButton.setFont(new Font("Arial",Font.BOLD,14));
        if(i==0){
            prevButton.setText("Home");
            prevButton.addActionListener(ev->{
                this.setVisible(false);
                new Home().setVisible(true);
            });
        }else if(i > 0){
            prevButton.addActionListener(ev->{
                i = i-1;
                this.setVisible(false);
                new ShapeEditorScreen(ShapeService.shapeDTOS[i],i).setVisible(true);
            });
        }
        footerControlPanel.add(prevButton);

        JButton nextButton = new JButton("Next");
        nextButton.setFocusPainted(false);
        nextButton.setFont(new Font("Arial",Font.BOLD,14));
        footerControlPanel.add(nextButton);

        if(i < ShapeService.shapeDTOS.length-1){
            nextButton.addActionListener(ev->{
                i = i+1;
                this.setVisible(false);
                new ShapeEditorScreen(ShapeService.shapeDTOS[i],i).setVisible(true);
            });
        }else{
            nextButton.setEnabled(false);
        }


        JButton saveButton = new JButton("Save");
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Arial",Font.BOLD,14));
        footerControlPanel.add(saveButton);
        saveButton.addActionListener(ev-> {
            this.dataModel.setCaptureScreenShot(true);
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        exitButton.setFocusPainted(false);
        exitButton.setFont(new Font("Arial",Font.BOLD,14));
        footerControlPanel.add(exitButton);


        return  footerControlPanel;
    }

    JSlider customSlider(){
        JSlider customSlider = new JSlider(50, 200, 100);
        customSlider.setBackground(Color.white);
        customSlider.setMajorTickSpacing(50);
        customSlider.setMinorTickSpacing(10);
        customSlider.setPaintTicks(true);
        customSlider.setPaintLabels(true);
        customSlider.setPreferredSize(new Dimension((this.getWidth()/3)-50,50));
        return customSlider;
    }

    public  JPanel createColorPickerPanel() {
        JPanel colorPickerPane = new JPanel();
        colorPickerPane.setLayout(new GridLayout(4, 7, 2, 2)); // Adjust grid layout as needed
        colorPickerPane.setBackground(Color.white);
        Color[] colors = {
                Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,
                Color.WHITE, Color.decode("#FF7F00"), Color.decode("#00FF7F"), Color.decode("#7F00FF"),
                Color.decode("#00FFFF"), Color.decode("#FF00FF"), Color.decode("#FFD700"),
                Color.decode("#00FF00"), Color.decode("#0000FF"), Color.decode("#FF1493"), Color.decode("#800080"),
                Color.decode("#FF4500"), Color.decode("#32CD32"), Color.decode("#00CED1"), Color.decode("#8B0000"),
                Color.decode("#FF69B4")
        };

        for (Color color : colors) {
            JButton colorButton = new JButton();
            colorButton.setPreferredSize(new Dimension(42, 42));
            colorButton.setBackground(color);
            colorButton.addActionListener(e -> dataModel.setSelectedColor(((JButton) e.getSource()).getBackground()));
            colorPickerPane.add(colorButton);
        }

        return colorPickerPane;
    }

    Label headingLabel(String heading){
        Font headingFont = new Font("Arial",Font.BOLD , 18);
        Label label = new Label(heading);
        label.setFont(headingFont);
        label.setPreferredSize(new Dimension(this.getWidth()/3,30));
        return label;
    }
}

class ShapeCanvas extends  JPanel{
    private ShapeDTO selectedShape;
    private Color shapeColor;
    private int shapeWidth;
    private int shapeHeight;
    private int shapeScale;

    private  DataModel dataModel;
    public ShapeCanvas(DataModel dataModel , JFrame frame , ShapeDTO selectedShape){
        setBackground(Color.white);
        this.selectedShape = selectedShape;
        this.dataModel = dataModel;
        dataModel.addPropertyChangeListener(evt -> {
            if ("selectedColor".equals(evt.getPropertyName())) {
                Color newValue = (Color) evt.getNewValue();
                shapeColor = newValue;
                repaint();
            }
        });

        dataModel.addPropertyChangeListener(evt -> {
            if ("shapeScale".equals(evt.getPropertyName())) {
                int newValue = (int) evt.getNewValue();
                shapeScale = newValue;
                repaint();
            }
        });

        dataModel.addPropertyChangeListener(evt -> {
            if ("shapeWidth".equals(evt.getPropertyName())) {
                int newValue = (int) evt.getNewValue();
                shapeWidth = newValue;
                repaint();
            }
        });

        dataModel.addPropertyChangeListener(evt -> {
            if ("shapeHeight".equals(evt.getPropertyName())) {
                int newValue = (int) evt.getNewValue();
                shapeHeight = newValue;
                repaint();
            }
        });

        dataModel.addPropertyChangeListener(evt -> {
            if ("selectedShape".equals(evt.getPropertyName())) {
                ShapeDTO newValue = (ShapeDTO) evt.getNewValue();
                this.selectedShape = newValue;
                repaint();
            }
        });

        dataModel.addPropertyChangeListener(evt->{
            if ("captureScreenShot".equals(evt.getPropertyName())) {
                boolean newValue = (boolean) evt.getNewValue();
                if(newValue){
                    this.takeScreenShot();
                    repaint();
                }
            }
        });
        setPreferredSize(new Dimension(2*frame.getWidth()/3,frame.getHeight()));
        setSelectedShape();
    }

    private  void takeScreenShot(){
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        this.print(g2);
        g2.dispose();
        File outputFile = new File("saved_shapes/shape_" + System.currentTimeMillis() + ".png");
        try {
            ImageIO.write(image, "png", outputFile);
           JOptionPane.showMessageDialog(null,"Shape saved successfully to "+outputFile.getPath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error saving shape: " + ex.getMessage());
        } finally {
            this.dataModel.setCaptureScreenShot(false);
        }
    }

    public void setSelectedShape() {
        if(this.selectedShape == null){
            this.selectedShape = new ShapeDTO("Square",new Square());
        }
        shapeColor = Color.BLACK;
        shapeWidth = 100;
        shapeHeight = 100;
        shapeScale = 100;
        if(selectedShape.getShapeName().equals(ShapeType.Rectangle.name())
                || selectedShape.getShapeName().equals(ShapeType.Cloud.name())
                || selectedShape.getShapeName().equals(ShapeType.Ellipse.name())){
            shapeWidth*=1.5;
        }

        if(selectedShape.getShapeName().equals(ShapeType.Oval.name())){
            shapeHeight*=1.5;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - shapeWidth) / 2;
        int y = (getHeight() - shapeHeight) / 2;

        int scaledWidth = shapeWidth * shapeScale / 100;
        int scaledHeight = shapeHeight * shapeScale / 100;

        g.setColor(shapeColor);
        this.selectedShape.getShape().draw(g, x, y, scaledWidth, scaledHeight);
    }

}




