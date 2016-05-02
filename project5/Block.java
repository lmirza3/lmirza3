import java.awt.*;

public class Block {
 private Color fillColor;
 
 public Block(Color color) {
  this.fillColor = color;
 }
 
 public boolean isEmpty() {
  return (fillColor.equals(Color.BLACK));
 }

 public void draw(int x, int y, Graphics g) {
  g.setColor(fillColor);
  g.fillRect(x*25, (y-1)*25, 25, 25);
 }
}

class EmptyBlock extends Block {
 
 public EmptyBlock(Color color) {
  super(color);
 }
 
 public void draw(int x, int y, Graphics g) {
  g.setColor(Color.BLACK);
  g.fillRect(x*25, (y-1)*25, 25, 25);
 }
}

