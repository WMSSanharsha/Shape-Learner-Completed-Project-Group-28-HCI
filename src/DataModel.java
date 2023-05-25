import models.Shape;
import models.ShapeDTO;

import java.awt.*;
import java.beans.PropertyChangeSupport;

public class DataModel extends PropertyChangeSupport {

    private ShapeDTO selectedShape;
    private Color selectedColor;
    private int shapeWidth;
    private int shapeHeight;
    private int shapeScale;

    private boolean captureScreenShot;

    public DataModel() {
        super(new Object());
    }

    public void setCaptureScreenShot(boolean newValue) {
        boolean oldValue = this.captureScreenShot;
        this.captureScreenShot = newValue;
        firePropertyChange("captureScreenShot", oldValue, newValue);
    }

    public void setSelectedColor(Color newValue) {
        Color oldValue = this.selectedColor;
        this.selectedColor = newValue;
        firePropertyChange("selectedColor", oldValue, newValue);
    }

    public void setShapeScale(int newValue) {
        int oldValue = this.shapeScale;
        this.shapeScale = newValue;
        firePropertyChange("shapeScale", oldValue, newValue);
    }

    public int getShapeWidth() {
        return shapeWidth;
    }

    public void setShapeWidth(int shapeWidth) {
        int oldValue = this.shapeWidth;
        this.shapeWidth = shapeWidth;
        firePropertyChange("shapeWidth", oldValue, shapeWidth);
    }

    public int getShapeHeight() {
        return shapeHeight;
    }

    public void setShapeHeight(int shapeHeight) {
        int oldValue = this.shapeHeight;
        this.shapeHeight = shapeHeight;
        firePropertyChange("shapeHeight", oldValue, shapeHeight);
    }

    public void setSelectedShape(ShapeDTO selectedShape) {
        ShapeDTO oldValue = this.selectedShape;
        this.selectedShape = selectedShape;
        firePropertyChange("selectedShape", oldValue, selectedShape);
    }
}
