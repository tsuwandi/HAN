package main.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -3432233086880210410L;
	BufferedImage image;
	
	public ImagePanel() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println(image);
		if(image != null) {
			g = image.getGraphics();
			g.drawImage(image, 0, 0, null);
			revalidate();
			updateUI();
		}
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}