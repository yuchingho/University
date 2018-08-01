import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Extend this class to add your picture manipulation methods.
public class Picture {
	private String source;
	private JFrame frame;
	private JLabel label;
	private BufferedImage image;

	// Constructs a picture with no image.
	public Picture() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel("(No image)");
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
	}

	public String getSource() {
		return source;
	}

	public Picture clone() {
		Picture tmp = new Picture();
		tmp.load(source);

		for (int x = 0; x < getWidth(); x++)
			for (int y = 0; y < getHeight(); y++) {
				Color c = getColorAt(x, y);
				tmp.setColorAt(x, y, c);
			}
		return tmp;
	}

	@SuppressWarnings("deprecation")
	public void hide() {
		frame.hide();
	};

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	// Loads a picture from a given source.
	public void load(String source) {
		try {
			this.source = source;
			BufferedImage img;
			if (source.startsWith("http://"))
				img = ImageIO.read(new URL(source).openStream());
			else
				img = ImageIO.read(new File(source));

			setImage(img);
		} catch (Exception ex) {
			this.source = null;
			ex.printStackTrace();
		}
	}

	public void saveAs(String filename) {
		try {
			File outputfile = new File(filename + ".png");
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Could not save the picture.");
		}
	}

	// Reloads this picture, undoing any manipulations.
	public void reload() {
		load(source);
	}

	// Displays a file chooser for picking a picture.
	public void pick() {
		JFileChooser chooser = new JFileChooser(".");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			load(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	private void setImage(BufferedImage image) {
		this.image = image;
		label.setIcon(new ImageIcon(image));
		label.setText(" ");
		frame.pack();
	}

	/**
	 * Gets the color of a pixel.
	 * @param x - the column index (between 0 and getWidth() - 1)
	 * @param y - the row index (between 0 and getHeight() - 1)
	 * @return the color of the pixel at position (x, y)
	 */
	public Color getColorAt(int x, int y) {
		Raster raster = image.getRaster();
		ColorModel model = image.getColorModel();
		int argb = model.getRGB(raster.getDataElements(x, y, null));
		return new Color(argb, true);
	}

	/**
	 * Sets the color of a pixel.
	 * @param x -the column index (between 0 and getWidth() - 1)
	 * @param y - the row index (between 0 and getHeight() - 1)
	 * @param c - the color for the pixel at position (x, y)
	 */
	public void setColorAt(int x, int y, Color c) {
		WritableRaster raster = image.getRaster();
		ColorModel model = image.getColorModel();
		Object colorData = model.getDataElements(c.getRGB(), null);
		raster.setDataElements(x, y, colorData);
		label.repaint();
	}
	
	public void setVisible() {
		frame.setVisible(false);
	}
}
