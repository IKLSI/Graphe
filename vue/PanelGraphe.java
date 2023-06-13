// Déclaration du package auquel appartient le fichier

package vue;

// Importation des classes utilisées dans ce fichier

import controleur.Controleur;
import metier.*;

import java.awt.*;

import java.awt.event.MouseListener;

import javax.swing.JPanel;

// Initialisation de la classe PanelGraphe

public class PanelGraphe extends JPanel implements MouseListener
{
	// Déclaration des attributs de la classe PanelGraphe

	private Controleur ctrl;
	private static final Color[] tabCouleur = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
											   Color.PINK, Color.MAGENTA, Color.CYAN, Color.GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY};
	private String sommet;

	// Création du JPanel

	public PanelGraphe(Controleur ctrl) { this.ctrl = ctrl; this.addMouseListener(this); }

	// Méthode permettant de dessiner le graphe à savoir les sommets, les arêtes et les régions

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Dessin des sommets

		for (Sommet s : this.ctrl.getSommet())
		{
			g.drawOval(s.getPosX()*7-5, s.getPosY()*7-5, 10, 10);
			g.drawString(String.valueOf(s.getSommet()), s.getPosX()*7-3, s.getPosY()*7-8);
		}

		/* Dessin des arêtes, si l'arête ne possède pas la valeur 5 c'est une bonus alors elle est dessinée en plus gros 
		* sinon elle est classique et possède la valeur 0 */

		for (Arete a : this.ctrl.getArr())
		{
			g.setColor(a.getCouleur());

			if (a.getPoints() != 0)
			{
				Graphics2D g2d = (Graphics2D) g;
				float nouvelleEpaisseur = 3.0f;
				Stroke ancienStroke = g2d.getStroke();
				g2d.setStroke(new BasicStroke(nouvelleEpaisseur));
				g2d.drawLine(a.getSommet1().getPosX()*7, a.getSommet1().getPosY()*7, a.getSommet2().getPosX()*7, a.getSommet2().getPosY()*7);
				g2d.setStroke(ancienStroke);
			}
			else if (a.getCouleur () != Color.BLACK)
			{
				Graphics2D g2d = (Graphics2D) g;
				float nouvelleEpaisseur = 3.0f;
				Stroke ancienStroke = g2d.getStroke();
				g2d.setStroke(new BasicStroke(nouvelleEpaisseur));
				g2d.drawLine(a.getSommet1().getPosX()*7, a.getSommet1().getPosY()*7, a.getSommet2().getPosX()*7, a.getSommet2().getPosY()*7);
				g2d.setStroke(ancienStroke);
			}
			else
				g.drawLine(a.getSommet1().getPosX()*7, a.getSommet1().getPosY()*7, a.getSommet2().getPosX()*7, a.getSommet2().getPosY()*7);
		}

		int indice = 0;

		// Dessin des régions --> chaque région possède une couleur différente et colorie les sommets qui lui appartiennent

		for (Region r : this.ctrl.getRegion())
		{
			for (Sommet s : r.getEnsSommet())
			{
				g.setColor(tabCouleur[indice]);
				g.fillOval(s.getPosX()*7-5, s.getPosY()*7-5, 10, 10);
			}
			indice++;
		}
	}

	// Méthode permettant de colorier une arête en fonction de la couleur passée en paramètre

	public void setCouleurArete(Arete a, Color c)
	{
		Graphics g = this.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
	
		float nouvelleEpaisseur = 3.0f;
		Stroke ancienStroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(nouvelleEpaisseur));
	
		g2d.setColor(c);
		g2d.drawLine(a.getSommet1().getPosX() * 7, a.getSommet1().getPosY() * 7, a.getSommet2().getPosX() * 7,
				a.getSommet2().getPosY() * 7);

		g2d.setStroke(ancienStroke);

		a.setCouleur(c);
	}

	// Méthode permettant de récupérer les coordonnées de la souris lors d'un clic et de comparer avec l'ensemble des sommets

	public void mouseClicked(java.awt.event.MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();

		for (Sommet s : ctrl.getSommet())
		{
			if (x >= s.getPosX()*7-15 && x <= s.getPosX()*7+15 && y >= s.getPosY()*7-15 && y <= s.getPosY()*7+15)
			{
				this.sommet = "" + s.getSommet();
				this.ctrl.setTxt(this.sommet);
			}
		}
	}
	
	public void mouseEntered(java.awt.event.MouseEvent e) { }

	public void mouseExited(java.awt.event.MouseEvent e) { }

	public void mousePressed(java.awt.event.MouseEvent e) { }

	public void mouseReleased(java.awt.event.MouseEvent e) { }
}