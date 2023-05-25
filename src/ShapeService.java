
import models.Shape;
import models.ShapeDTO;

import java.awt.*;
import java.awt.geom.Path2D;

public class ShapeService {
    static public ShapeDTO[] shapeDTOS = new ShapeDTO[]{
            new ShapeDTO(ShapeType.Square.name(),new Square()),
            new ShapeDTO(ShapeType.Circle.name(),new Circle()),
            new ShapeDTO(ShapeType.Rectangle.name(),new Rectangle()),
            new ShapeDTO(ShapeType.Triangle.name(),new Triangle()),
            new ShapeDTO(ShapeType.Pentagon.name(),new Pentagon()),
            new ShapeDTO(ShapeType.Hexagon.name(),new Hexagon()),
            new ShapeDTO(ShapeType.Octagon.name(),new Octagon()),
            new ShapeDTO(ShapeType.Heptagon.name(),new Heptagon()),
            new ShapeDTO(ShapeType.Nonagon.name(), new Nonagon()),
            new ShapeDTO(ShapeType.Decagon.name(), new Decagon()),
            new ShapeDTO(ShapeType.Ellipse.name(),new Ellipse()),
            new ShapeDTO(ShapeType.Diamond.name(),new Diamond()),
            new ShapeDTO(ShapeType.Trapezoid.name(),new Trapezoid()),
            new ShapeDTO(ShapeType.Parallelogram.name(),new Parallelogram()),
            new ShapeDTO(ShapeType.Cloud.name(),new Cloud()),
            new ShapeDTO(ShapeType.Cross.name(),new Cross()),
            new ShapeDTO(ShapeType.Oval.name(), new Oval()),
            new ShapeDTO(ShapeType.Flower.name(), new Flower()),
            new ShapeDTO(ShapeType.Star.name(), new Star()),
            new ShapeDTO(ShapeType.Heart.name(), new Heart()),
    };
}


enum ShapeType{
    Rectangle,
    Circle,
    Triangle,
    Square,
    Pentagon,
    Hexagon,
    Octagon,
    Ellipse,
    Diamond,
    Trapezoid,
    Parallelogram,
    Cloud,
    Cross,
    Oval,
    Flower,
    Heptagon,
    Nonagon,
    Decagon,
    Star,
    Heart
}

class Square implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }
}
class Rectangle implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }
}

class Circle implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x, y, width, height);
    }
}

class Triangle implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x, x + width / 2, x + width};
        int[] yPoints = {y + height, y, y + height};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
class Pentagon implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int radius = Math.min(width, height) / 2;
        int[] xPoints = {(int) (x + width / 2 + radius * Math.cos(0)),
                (int) (x + width / 2 + radius * Math.cos(2 * Math.PI / 5)),
                (int) (x + width / 2 + radius * Math.cos(4 * Math.PI / 5)),
                (int) (x + width / 2 + radius * Math.cos(6 * Math.PI / 5)),
                (int) (x + width / 2 + radius * Math.cos(8 * Math.PI / 5))};
        int[] yPoints = {(int) (y + height / 2 - radius * Math.sin(0)),
                (int) (y + height / 2 - radius * Math.sin(2 * Math.PI / 5)),
                (int) (y + height / 2 - radius * Math.sin(4 * Math.PI / 5)),
                (int) (y + height / 2 - radius * Math.sin(6 * Math.PI / 5)),
                (int) (y + height / 2 - radius * Math.sin(8 * Math.PI / 5))};
        g.fillPolygon(xPoints, yPoints, 5);
    }
}

class Hexagon implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width / 4, x + width * 3 / 4, x + width, x + width * 3 / 4, x + width / 4, x};
        int[] yPoints = {y, y, y + height / 2, y + height, y + height, y + height / 2};
        g.fillPolygon(xPoints, yPoints, 6);
    }
}

class Octagon implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width / 3, x + width * 2 / 3, x + width, x + width, x + width * 2 / 3, x + width / 3, x, x};
        int[] yPoints = {y, y, y + height / 3, y + height * 2 / 3, y + height, y + height, y + height * 2 / 3, y + height / 3};
        g.fillPolygon(xPoints, yPoints, 8);
    }
}

class Ellipse implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x, y, width, height);
    }
}

class Diamond implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width / 2, x + width, x + width / 2, x};
        int[] yPoints = {y, y + height / 2, y + height, y + height / 2};
        g.fillPolygon(xPoints, yPoints, 4);
    }
}

class Trapezoid implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width / 4, x + width * 3 / 4, x + width, x};
        int[] yPoints = {y, y, y + height, y + height};
        g.fillPolygon(xPoints, yPoints, 4);
    }
}

class Parallelogram implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = {x + width / 4, x + width, x + width * 3 / 4, x};
        int[] yPoints = {y, y, y + height, y + height};
        g.fillPolygon(xPoints, yPoints, 4);
    }
}

class Cloud implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x, y, width, height);
        g.fillOval(x + width / 4, y - height / 2, width / 2, height);
        g.fillOval(x + width / 2, y, width / 2, height);
    }
}

class Oval implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x, y, width, height);
    }
}

class Cross implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int lineThickness = Math.min(width, height) / 5;
        g.fillRect(x, y + height / 2 - lineThickness / 2, width, lineThickness);
        g.fillRect(x + width / 2 - lineThickness / 2, y, lineThickness, height);
    }
}

class Flower implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x, y, width, height);
        g.fillOval(x + width / 2, y + height / 2, width, height);
        g.fillOval(x - width / 2, y + height / 2, width, height);
        g.fillOval(x + width / 2, y - height / 2, width, height);
        g.fillOval(x - width / 2, y - height / 2, width, height);
    }
}

 class Heptagon implements Shape {

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = calculateXPoints(x, width);
        int[] yPoints = calculateYPoints(y, height);

        g.fillPolygon(xPoints, yPoints, 7);
    }

    private int[] calculateXPoints(int x, int width) {
        int[] xPoints = new int[7];
        double angle = 2 * Math.PI / 7;

        for (int i = 0; i < 7; i++) {
            xPoints[i] = (int) (x + width / 2 + width / 2 * Math.cos(i * angle));
        }

        return xPoints;
    }

    private int[] calculateYPoints(int y, int height) {
        int[] yPoints = new int[7];
        double angle = 2 * Math.PI / 7;

        for (int i = 0; i < 7; i++) {
            yPoints[i] = (int) (y + height / 2 + height / 2 * Math.sin(i * angle));
        }

        return yPoints;
    }
}



 class Nonagon implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = new int[9];
        int[] yPoints = new int[9];

        double angle = 0;
        double angleIncrement = 2 * Math.PI / 9;
        int radius = Math.min(width, height) / 2;

        for (int i = 0; i < 9; i++) {
            xPoints[i] = (int) (x + width / 2 + radius * Math.cos(angle));
            yPoints[i] = (int) (y + height / 2 + radius * Math.sin(angle));
            angle += angleIncrement;
        }

        g.fillPolygon(xPoints, yPoints, 9);
    }
}

 class Decagon implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xCoordinates = new int[10];
        int[] yCoordinates = new int[10];

        double angle = Math.PI / 10;

        for (int i = 0; i < 10; i++) {
            xCoordinates[i] = (int) (x + width / 2 + width / 2 * Math.cos(2 * i * angle));
            yCoordinates[i] = (int) (y + height / 2 + height / 2 * Math.sin(2 * i * angle));
        }
        g.fillPolygon(xCoordinates, yCoordinates, 10);
    }
}

 class Star implements Shape {
    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];
        double theta = Math.PI / 5; // 36 degrees
        double angle = 0.0;
        int radius = width / 2;
        int innerRadius = width / 4;

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                xPoints[i] = x + (int) (radius * Math.cos(angle));
                yPoints[i] = y + (int) (radius * Math.sin(angle));
            } else {
                xPoints[i] = x + (int) (innerRadius * Math.cos(angle));
                yPoints[i] = y + (int) (innerRadius * Math.sin(angle));
            }
            angle += theta;
        }

        Polygon star = new Polygon(xPoints, yPoints, 10);
        g.fillPolygon(star);
    }
}


class Heart implements Shape {
    private static final long serialVersionUID = 1L;

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;

        int margin = 5;
        int panelWidth = width;
        int panelHeight = height;
        int boxSize = Math.min(panelWidth, panelHeight) - margin * 2;
        float boxX = (panelWidth - boxSize) / 2.0f;
        float boxY = (panelHeight - boxSize) / 2.0f;

        if (boxSize > 0) {
            Path2D heartPath = createHeartPath(boxX, boxY, boxSize, boxSize);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fill(heartPath);
        }
    }

    private Path2D createHeartPath(float x, float y, float width, float height) {
        float beX = x + width / 2;
        float beY = y + height;

        float c1DX = width  * 0.968f;
        float c1DY = height * 0.672f;
        float c2DX = width  * 0.281f;
        float c2DY = height * 1.295f;
        float teDY = height * 0.850f;

        Path2D.Float heartPath = new Path2D.Float();
        heartPath.moveTo(beX, beY);
        heartPath.curveTo(
                beX - c1DX, beY - c1DY,
                beX - c2DX, beY - c2DY,
                beX       , beY - teDY);
        heartPath.curveTo(
                beX + c2DX, beY - c2DY,
                beX + c1DX, beY - c1DY,
                beX       , beY);
        return heartPath;
    }
}










