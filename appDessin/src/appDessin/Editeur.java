package appDessin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputListener;

import java.util.LinkedList;

import java.io.File;
import java.io.IOException;

public class Editeur extends JPanel implements MouseInputListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Figure> figures;
	private LinkedList<Point> temp_Pts;
	private String mode;
	private String fig_path;
	private Figure hoverFig;
	private Point temp_Pt;
	private Point hoverPt;
	private Boolean temp_fig;
	private Color couleurInit;
	private Color couleur;
	private File img_path;
	private Image img;
    private JPopupMenu menuClickDr;
    private JMenuItem Copier, Couper, Coller, Retour, Refaire, Translater;
    private BufferedImage bufferedImg;
    private JFileChooser Fchooser;
	
	public Editeur() {
		figures = new LinkedList<Figure>();
		temp_Pts = new LinkedList<Point>();
		couleurInit = Color.BLACK;
		
		menuClickDr = new JPopupMenu();
		Copier = new JMenuItem("Copier");
        Copier.addActionListener(this);
        Couper = new JMenuItem("Couper");
        Couper.addActionListener(this);
        Coller = new JMenuItem("Coller");
        Coller.addActionListener(this);
        Translater = new JMenuItem("Translater");
        Translater.addActionListener(this);
        Retour = new JMenuItem("Retour");
        Retour.addActionListener(this);
        Refaire = new JMenuItem("Refaire");
        Refaire.addActionListener(this);
        
        menuClickDr.add(Retour); // décoratif
        menuClickDr.add(Refaire); // décoratif
        
        menuClickDr.addSeparator();
        
        menuClickDr.add(Translater);
        
        menuClickDr.addSeparator();
        
        menuClickDr.add(Copier); // décoratif
        menuClickDr.add(Couper); // décoratif
        menuClickDr.add(Coller); // décoratif
        
        Fchooser = new JFileChooser();
		Fchooser.setApproveButtonText("Exporter"); 
		
		repaint();
	}
	
	public Editeur(LinkedList<Figure> f) {
		figures = f;
		temp_Pts = new LinkedList<Point>();
		couleurInit = Color.BLACK;
		repaint();
	}
	
	public void paint(Graphics gc) {
		super.paint(gc);
		for(Figure f : figures) {
			f.paint(gc, f.equals(hoverFig));
		}
	}
	
	// Getters/Setters
	public LinkedList<Figure> getFigures() {
        return figures;
    }

	public void setFigures(LinkedList<Figure> figures) {
        this.figures = figures;
    }
	
    public LinkedList<Point> getTemp_Pts() {
		return temp_Pts;
	}

	public void setTemp_Pts(LinkedList<Point> temp_Pts) {
		this.temp_Pts = temp_Pts;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFig_path() {
		return fig_path;
	}

	public void setFig_path(String fig_path) {
		this.fig_path = fig_path;
	}

	public Point getTemp_Pt() {
		return temp_Pt;
	}

	public void setTemp_Pt(Point temp_Pt) {
		this.temp_Pt = temp_Pt;
	}

	public Boolean getTemp_fig() {
		return temp_fig;
	}

	public void setTemp_fig(Boolean temp_fig) {
		this.temp_fig = temp_fig;
	}

	public Color getCouleurInit() {
		return couleurInit;
	}

	public void setCouleurInit(Color couleurInit) {
		this.couleurInit = couleurInit;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public File getImg_path() {
		return img_path;
	}

	public void setImg_path(File img_path) {
		this.img_path = img_path;
	}

	public Image getImg() {
		return img;
	}

	public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        if (img != null) {
            img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            gc.drawImage(img, 0, 0, null);
        }
    }
	
	public void setImg() {
		try {
            this.img = ImageIO.read(img_path);
            img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        repaint();
        } 
        catch (IOException e) {
            System.err.println(img_path +" introuvable " + e);
        }
	}

	// Methods
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
            mode = null;
            menuClickDr.show(this, e.getX(), e.getY());
        }
        
        else if (mode == "Select") {
            if (hoverFig != null) {
                hoverFig.setSelect(true);
            }

            else {
                
                for (Figure figure : figures) {
                    figure.setSelect(false);
                }
            }
        }
		else {
        	for (Figure F : figures) {
				F.setSelect(false);
			}
			if (mode == "Point") {
				//System.out.println("Point");
				figures.add(new Point(couleur, e.getX(), e.getY()));
			}
		
			else if (mode == "Segment") {
				if (temp_Pt == null) {
					temp_Pt = new Point(couleur, e.getX(), e.getY());
					temp_fig = false;
				}
				else {
					//System.out.println("Segment");
					figures.add(new Segment(couleur, temp_Pt, new Point(couleur, e.getX(), e.getY())));
					temp_Pt = null;
				}
			}
		
			else if (mode == "Cercle") {
				if (temp_Pt == null) {
					temp_Pt = new Point(couleur, e.getX(), e.getY());
					temp_fig = false;
				}
				else {
					//System.out.println("Cercle");
					figures.add(new Cercle(couleur, temp_Pt, new Point(couleur, e.getX(), e.getY())));
					temp_Pt = null;
				}
			}
		
			else if (mode == "Polygone") {
				if (temp_Pt == null) {
					//System.out.println("Poly");
					temp_Pts.add(new Point(couleur, e.getX(), e.getY()));
				}
				else {
					temp_Pts.clear();
				}
			}
			else if (mode == "Libre") {
				//System.out.println("Libre");
				figures.add(new Point(couleur, e.getX(), e.getY()));
        	}
		
		repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mode == "Translater") {
            hoverPt = new Point(Color.BLACK, e.getX(), e.getY());
        }
		else if (mode == "Polygone") {
			if (e.getButton() == MouseEvent.BUTTON1) {
				temp_Pts.add(new Point(couleur, e.getX(), e.getY()));
			}
			else if (e.getButton() == MouseEvent.BUTTON3) {
				figures.add(new Polygone(couleur, temp_Pts));
				temp_Pts = new LinkedList<Point>();
			}
		}
		
		repaint();
	}
	
	public void hov_Point(int hpx, int hpy) {
        Point mouse = new Point(Color.BLACK, hpx - 3, hpy - 3);
        hoverFig = null;

        for (Figure figure : figures) {
            if (figure instanceof Point) {
                Point p = (Point) figure;
                if (p.distance(mouse) < 6 ) {
                    hoverFig = p;
                }
            }
            else if (figure instanceof Segment) {
                Segment s = (Segment) figure;
                if (s.getP1().distance(mouse) < 6) {
                    hoverFig = s;
                }
                if (s.getP2().distance(mouse) < 6) {
                    hoverFig = s;
                }
            }
            else if (figure instanceof Cercle) {
                Cercle c = (Cercle) figure;
                if (c.getCentre().distance(mouse) < 6) {
                    hoverFig = c;
                }
            }
            else if (figure instanceof Polygone) {
                Polygone p = (Polygone) figure;
                for (Point pt : p.getListe()) {
                    if (pt.distance(mouse) < 6) {
                        hoverFig = p;
                    }
                }
            }
        }
    }

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mode == "Translater") {
            int tx = e.getX() - hoverPt.getX();
            int ty = e.getY() - hoverPt.getY();
            for (Figure figure : figures) {
                if (figure.getSelect()) {
                    figure.translater(tx, ty);
                }
            }
            hoverPt.translater(tx, ty);
        }

        else if (mode == "Libre") {
            figures.add(new Point(couleur, e.getX(), e.getY()));
        }

        repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (mode == "Select") {
            hov_Point(e.getX(), e.getY());
        }
		else if (mode == "Segment" && temp_Pt != null) {
            Point P = new Point(couleur, e.getX(), e.getY());
            if (temp_fig == true) {
               figures.removeLast();
            }
            figures.add(new Segment(couleur, temp_Pt, P));
            temp_fig = true;
        }

        else if (mode == "Cercle" && temp_Pt != null) {
            Point P = new Point(couleur, e.getX(), e.getY());
            if (temp_fig == true) {
                figures.removeLast();
            }
            figures.add(new Cercle(couleur, temp_Pt, P));
            temp_fig = true;
        }
        
        else if (mode == "Polygone" && temp_Pts.size() != 0) {
            temp_Pts.remove(temp_Pts.getLast());
            temp_Pts.add(new Point(couleur, e.getX(), e.getY()));
            figures.add(new Polygone(couleur, temp_Pts));
        }
  
        repaint();
    }
	
	@Override
    public Cursor getCursor() {
        return super.getCursor();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem scr = (JMenuItem) e.getSource();
        String txt = scr.getText();
        System.out.println(txt);
        if (txt == "Translater") {
            mode = txt;
        }
	}
	
	public void save_img_as() {
		if (Fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 		bufferedImg = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
	 		Graphics gc = bufferedImg.createGraphics();
			this.paint(gc);
            gc.dispose();
			try {
                img_path = Fchooser.getSelectedFile();
				ImageIO.write(bufferedImg, "JPG", Fchooser.getSelectedFile());
                
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Création du fichier impossible", null, JOptionPane.ERROR_MESSAGE);
			} 
		}
    }
    public void save_img() {
        bufferedImg = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics gc = bufferedImg.createGraphics();
        this.paint(gc);
        gc.dispose();
        try {
            ImageIO.write(bufferedImg, "JPG", img_path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Création impossible", null, JOptionPane.ERROR_MESSAGE);
        } 
    }
}