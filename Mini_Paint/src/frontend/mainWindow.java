package frontend;

import backend.CircleShape;
import backend.Draw;
import backend.Shape;
import backend.SquareShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class mainWindow extends JFrame {
    private JPanel mainPanel;
    private JComboBox chooseShapeBox;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JButton circleButton;
    private JButton lineSegmentButton;
    private JButton squareButton;
    private JButton rectangleButton;
    private JPanel canvasPanel;
    private Draw draw;
    private Canvas canvas;

    public mainWindow() {
        setContentPane(mainPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Vector Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(canvasPanel.getSize());
        canvas.setBackground(Color.WHITE);
        canvasPanel.add(canvas);

        draw = new Draw();

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CirclePropertiesDialog dialog = new CirclePropertiesDialog(canvas);
                if(dialog.getStatus().equals("Cancel")) {
                    return;
                }
                String x = dialog.getxTF();
                String y = dialog.getyTF();
                String radius = dialog.getRadiusTF();
                CircleShape circle = new CircleShape();
                Shape[] shapes = draw.getShapes();
                int circleCount = 0;
                for (Shape shape : shapes) {
                    if (shape instanceof CircleShape) {
                        circleCount++;
                    }
                }
                circle.setName("Circle" + (circleCount + 1));
                circle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                circle.setProperties(Map.of("radius", Double.parseDouble(radius)));
                circle.setColor(Color.BLACK);
                circle.draw(canvas.getGraphics());
                draw.addShape(circle);
                chooseShapeBox.addItem(circle.getName());
                dialog.dispose();
            }
        });
        lineSegmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SquarePropertiesDialog dialog = new SquarePropertiesDialog(canvas);
                if(dialog.getStatus().equals("Cancel")) {
                    return;
                }
                String x = dialog.getxTF();
                String y = dialog.getyTF();
                String side = dialog.getLengthTF();
                SquareShape square = new SquareShape();
                Shape[] shapes = draw.getShapes();
                int squareCount = 0;
                for (Shape shape : shapes) {
                    if (shape instanceof SquareShape) {
                        squareCount++;
                    }
                }
                square.setName("Square" + (squareCount + 1));
                square.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                square.setProperties(Map.of("side", Double.parseDouble(side)));
                square.setColor(Color.BLACK);
                square.draw(canvas.getGraphics());
                draw.addShape(square);
                chooseShapeBox.addItem(square.getName());
                dialog.dispose();
            }
        });
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        colorizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new mainWindow();
    }

}
