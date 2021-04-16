package appDessin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.util.LinkedList;

public class GUI extends JFrame implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private Dimension dimensions;
	private Editeur editor;
    private JMenuBar Menu;
    private JMenu Fichiers, Edition, Aide;
    private JMenuItem
    	mNvFich,
    	mOvFich,
    	mEnFich,
    	mEnSoFich,
    	mExit,
    	mRetour,
    	mRefaire,
    	mCopie,
    	mCoupe,
    	mColle,
    	mExpFig,
    	mExpAsFig;
    private JToolBar outils;
    private JButton
    	btnNew,
    	btnSave,
    	btnSaveAs,
    	btnCopy,
    	btnCut,
    	btnPaste,
    	btnExit,
    	btnPoint,
    	btnSegment,
    	btnCercle,
    	btnPolygone,
    	btnLibre,
    	btnCouleur,
    	btnClear,
    	btnSelect;
    private JFileChooser FChooser;
	
	public static void main(String args[]) {
		GUI gui = new GUI("PAIN (T)");
		gui.setVisible(true);
	}
	
	public GUI(String title) {
		setTitle(title);
		
		dimensions = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(dimensions.getWidth()/2), (int)(dimensions.getHeight()/2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Menu = new JMenuBar();
		
		// Menu Fichiers
		Fichiers = new JMenu("Fichiers");

        mNvFich = new JMenuItem("Nouveau fichier", new ImageIcon("icons/new.png"));
        mNvFich.addActionListener(this::JMenuItemListener);
        mNvFich.setMnemonic(KeyEvent.VK_N);
        mNvFich.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        Fichiers.add(mNvFich);
        
        Fichiers.addSeparator();
        
        mOvFich = new JMenuItem("Ouvrir fichier",new ImageIcon("icons/open_file.png"));
        mOvFich.addActionListener(this::JMenuItemListener);
        mOvFich.setMnemonic(KeyEvent.VK_N);
        mOvFich.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        Fichiers.add(mOvFich);
        
        mEnFich = new JMenuItem("Enregistrer", new ImageIcon("icons/save.png"));
        mEnFich.addActionListener(this::JMenuItemListener);
        mEnFich.setMnemonic(KeyEvent.VK_N);
        mEnFich.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        Fichiers.add(mEnFich);
        
        mEnSoFich = new JMenuItem("Enregistrer sous", new ImageIcon("icons/save_as.png"));
        mEnSoFich.addActionListener(this::JMenuItemListener);
        Fichiers.add(mEnSoFich);

        Fichiers.addSeparator();

        mExpAsFig = new JMenuItem("Exporter sous", new ImageIcon("icons/export.png"));
        mExpAsFig.addActionListener(this::JMenuItemListener);
        Fichiers.add(mExpAsFig);

        mExpFig = new JMenuItem("Exporter", new ImageIcon("icons/export.png"));
        mExpFig.addActionListener(this::JMenuItemListener);
        Fichiers.add(mExpFig);
        
        Fichiers.addSeparator();
        
        mExit = new JMenuItem( "Sortie", new ImageIcon( "icons/exit.png" ) );
        mExit.addActionListener(this::JMenuItemListener);
        mExit.setMnemonic(KeyEvent.VK_F4);
        mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        Fichiers.add(mExit);

        // Menu Edition
        Edition = new JMenu( "Edition" );

        mRetour = new JMenuItem("Retour", new ImageIcon( "icons/undo.png" ));
        mRetour.addActionListener(this::JMenuItemListener);
        mRetour.setMnemonic(KeyEvent.VK_Z);
        mRetour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));        
        Edition.add(mRetour);
        
        mRefaire = new JMenuItem("Refaire", new ImageIcon( "icons/redo.png" ));
        mRefaire.addActionListener(this::JMenuItemListener);
        mRefaire.setMnemonic(KeyEvent.VK_Y);
        mRefaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));        
        Edition.add(mRefaire);
        
        Edition.addSeparator();
        
        mCopie = new JMenuItem("Copier", new ImageIcon( "icons/copy.png" ));
        mCopie.addActionListener(this::JMenuItemListener);
        mCopie.setMnemonic(KeyEvent.VK_C);
        mCopie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        Edition.add(mCopie);
        
        mCoupe = new JMenuItem("Couper", new ImageIcon( "icons/cut.png" ));
        mCoupe.addActionListener(this::JMenuItemListener);
        mCoupe.setMnemonic(KeyEvent.VK_X);
        mCoupe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        Edition.add(mCoupe);
        
        mColle = new JMenuItem("Coller", new ImageIcon( "icons/paste.png" ));
        mColle.addActionListener(this::JMenuItemListener);
        mColle.setMnemonic(KeyEvent.VK_V);
        mColle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        Edition.add(mColle);

        // Menu Aide
        Aide = new JMenu( "Aide" );
        Aide.addActionListener(this::JMenuListener);

        // Raccourcis
        Menu.add(Fichiers);
        Menu.add(Edition);
        Menu.add(Aide);

        setJMenuBar(Menu);


        // Outils
        outils = new JToolBar();

        btnNew = new JButton(new ImageIcon("icons/new.png"));
        btnNew.setToolTipText("Nouveau fichier (Ctrl+N)");
        btnNew.addActionListener(this);
        outils.add(btnNew);
        
        btnSave = new JButton(new ImageIcon("icons/save.png"));
        btnSave.setToolTipText("Enregistrer (Ctrl+S)");
        btnSave.addActionListener(this);
        outils.add(btnSave);

        btnSaveAs = new JButton(new ImageIcon("icons/save_as.png"));
        btnSaveAs.setToolTipText("Enregistrer sous (Ctrl+Shift+S)");
        btnSaveAs.addActionListener(this);
        outils.add(btnSaveAs);

        outils.addSeparator();

        btnCopy = new JButton(new ImageIcon( "icons/copy.png"));
        btnCopy.setToolTipText("Copier (Ctrl+C)");
        btnCopy.addActionListener(this);
        outils.add(btnCopy);

        btnCut = new JButton(new ImageIcon( "icons/cut.png"));
        btnCut.setToolTipText("Couper (Ctrl+X)");
        btnCut.addActionListener(this);
        outils.add( btnCut );

        btnPaste = new JButton(new ImageIcon( "icons/paste.png"));
        btnPaste.setToolTipText("Coller (Ctrl+V)");
        btnPaste.addActionListener(this);
        outils.add(btnPaste);

        outils.addSeparator();

        btnExit = new JButton(new ImageIcon( "icons/exit.png"));
        btnExit.setToolTipText("Sortie (Alt+F4)");
        btnExit.addActionListener(this);
        outils.add(btnExit);

        outils.addSeparator();

        // Boutons figures
        btnPoint = new JButton(new ImageIcon( "icons/dot.png"));
        btnPoint.setToolTipText("Point");
        btnPoint.addActionListener(this);
        outils.add(btnPoint);

        btnSegment = new JButton(new ImageIcon( "icons/line.png"));
        btnSegment.setToolTipText("Segment");
        btnSegment.addActionListener(this);
        outils.add(btnSegment);

        btnCercle = new JButton(new ImageIcon( "icons/circle.png"));
        btnCercle.setToolTipText("Cercle");
        btnCercle.addActionListener(this);
        outils.add(btnCercle);

        btnPolygone = new JButton(new ImageIcon( "icons/polygon.png"));
        btnPolygone.setToolTipText("Polygone");
        btnPolygone.addActionListener(this);
        outils.add(btnPolygone);
        
        btnLibre = new JButton(new ImageIcon( "icons/draw.png"));
        btnLibre.setToolTipText("Libre");
        btnLibre.addActionListener(this);
        outils.add(btnLibre);

        outils.addSeparator();

        btnSelect = new JButton(new ImageIcon( "icons/select.png"));
        btnSelect.setToolTipText("Select");
        btnSelect.addActionListener(this);
        outils.add(btnSelect);

        outils.addSeparator();
        
        // Autres boutons
        btnCouleur = new JButton(new ImageIcon( "icons/color.png"));
        btnCouleur.setToolTipText("Chosir Couleur");
        btnCouleur.addActionListener(this);
        outils.add(btnCouleur);

        outils.addSeparator();

        btnClear = new JButton(new ImageIcon( "icons/clear.png"));
        btnClear.setToolTipText("Nettoyer tableau");
        btnClear.addActionListener(this);
        outils.add(btnClear);

        add(outils, BorderLayout.NORTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton scr = (JButton) e.getSource();
        String txt = scr.getToolTipText();
        
        if (txt == "Nouveau fichier (Ctrl+N)") {
            System.out.println(editor);
            editor = new Editeur();
            add(editor, BorderLayout.CENTER);
            editor.setBackground(Color.white);
            editor.addMouseListener(editor);
            editor.addMouseMotionListener(editor);
            editor.repaint();
            this.validate();
        }
        else if (txt == "Enregistrer (Ctrl+S)") {
        	System.out.println("Enregistrement");
        }
        else if (txt == "Enregistrer sous (Ctrl+Shift+S)") {
            System.out.println("Enregistrement Sous");
            editor.save_img_as();
        }
        else if (txt == "Copier (Ctrl+C)") {
            System.out.println("copie");
        }
        else if (txt == "Couper (Ctrl+X)") {
        	System.out.println("coupe");
        }
        else if (txt == "Coller (Ctrl+V)") {
        	System.out.println("colle");
        }
        else if (txt == "Sortie (Alt+F4)" && editor != null) {
        	System.out.println("sortie");
            setVisible(false);
            dispose();
        }
        else if ((txt == "Point" || txt == "Segment" || txt == "Cercle" || txt == "Polygone" || txt == "Libre" || txt == "Select") && editor != null){
            editor.setMode(txt);
        }

        else if (txt == "Chosir Couleur" && editor != null){  
            Color c = JColorChooser.showDialog(this,"Choisir couleur",editor.getCouleurInit());
            editor.setCouleur(c);
        }
        else if(txt == "Nettoyer tableau" && editor != null) {
            editor.setBackground(Color.WHITE);
            editor.setFigures(new LinkedList<Figure>());
            editor.repaint();
        }   
    }
    
    private void JMenuItemListener( ActionEvent e ) {
        JMenuItem scr = (JMenuItem) e.getSource();
        String txt = scr.getText();
        
        if (txt == "Nouveau fichier") {
            System.out.println("test");
            editor = new Editeur();
            add(editor, BorderLayout.CENTER);
            editor.setBackground(Color.WHITE);
            editor.addMouseListener(editor);
            editor.addMouseMotionListener(editor);
            editor.repaint();
            this.validate();
        }
        else if (txt == "Ouvrir fichier"){
            FChooser = new JFileChooser();
            FChooser.setFileFilter(new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"));
            int value = FChooser.showOpenDialog(this);
            if(value == JFileChooser.APPROVE_OPTION) {
                File path = FChooser.getSelectedFile();
                if (editor != null) {
                editor.setImg_path(path);
                editor.setImg();
                }
            }
        }

        else if (txt == "Enregistrer") {
            if (editor.getImg_path() != null) {
                editor.save_img();
            }
            else {
                editor.save_img_as();
            }
        }
        else if (txt == "Enregister sous") {
            editor.save_img_as();
        }
        else if (txt == "Retour") {
        }
        else if (txt == "Refaire") {
        }
        else if (txt == "Copier") {
        }
        else if (txt == "Couper") {
        }
        else if (txt == "Coller") {
        }
        else if (txt == "Sortie") {
            setVisible(false);
            dispose();
        }
        else if ((txt == "Point" || txt == "Segment" || txt == "Cercle" || txt == "Polygone" || txt == "Libre") && editor != null){
            editor.setMode(txt);
        }
	}

	private void JMenuListener( ActionEvent event ) {
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
