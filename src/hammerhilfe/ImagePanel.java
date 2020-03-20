package hammerhilfe;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private int imageWidth = -1;
	private int imageHeight = -1;
	
	public ImagePanel(BufferedImage img) {
		this.image = img;
	}
	
	public ImagePanel(BufferedImage img, int width, int height) {
		setImageWidth(width);
		setImageHeight(height);
		this.image = img;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		if(image != null) {
			if(imageWidth == -1 && imageHeight == -1) {
				g.drawImage(image, 0, 0, null);
			}else if (imageWidth == -1 && imageHeight != -1) {
				double f = (imageHeight * 1.0) / (image.getHeight() * 1.0);
				
				int width = (int) (image.getWidth() * f);
				
				g.drawImage(image, 0, 0, width, imageHeight, null);
			}else if (imageHeight == -1 && imageWidth != -1) {
				double f = (imageWidth * 1.0) / (image.getWidth() * 1.0);
				
				int height = (int) (image.getHeight() * f);
				
				g.drawImage(image, 0, 0, height, imageWidth, null);
			}else{
				g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			}
		}
	}
	
	public int getImageWidth() {
		return imageWidth;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}
	
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	
}
