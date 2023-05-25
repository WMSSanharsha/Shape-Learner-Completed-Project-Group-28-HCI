package models;

public class ShapeDTO {
    public ShapeDTO(String shapeName, Shape shape) {
        this.shapeName = shapeName;
        this.shape = shape;
    }

    String shapeName;

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    Shape shape;

}
