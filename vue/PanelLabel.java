// Déclaration du package auquel appartient le fichier

package vue;

// Importation des classes utilisées dans ce fichier

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;

// Initialisation de la classe PanelLabel

public class PanelLabel extends JPanel
{
	// Déclaration des attributs de la classe PanelLabel

	private JLabel lblMessage;
	private JLabel lblMessageErreur;
	private int cpt = 0;

	public PanelLabel ( )
	{
		this.setLayout(new BorderLayout());

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		this.lblMessage = new JLabel("Nombre de point du joueur : " + cpt + "          ");
		this.lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblMessage.setFont(new Font("Arial", Font.BOLD, 20));

		this.lblMessageErreur = new JLabel("");
		this.lblMessageErreur.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblMessageErreur.setFont(new Font("Arial", Font.BOLD, 20));

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.add(lblMessage, BorderLayout.CENTER);
		this.add(lblMessageErreur, BorderLayout.SOUTH);
	}

	public void resetMessage(int points)
	{
		this.cpt = points;
		this.lblMessage.setText("Nombre de points du joueur : " + cpt + "          ");
	}

	public void setMessage(String message) { this.lblMessageErreur.setText(message); }
}