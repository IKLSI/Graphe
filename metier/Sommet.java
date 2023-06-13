// Déclaration du package auquel appartient le fichier

package metier;

// Initialisation de la classe Sommet

public class Sommet
{
	// Déclaration des attributs de la classe Sommet

	private Region region;

	private int sommet;
	private int posX;
	private int posY;

	public Sommet(int sommet, int posX, int posY)
	{
		this.sommet = sommet;
		this.posX = posX;
		this.posY = posY;
	}

	// Mutateurs et accesseurs nécessaires à la classe Sommet

	public void setSommet(int sommet) { this.sommet = sommet; }

	public int getSommet() { return this.sommet; }

	public void setRegion(Region region) { this.region = region; }

	public Region getRegion() { return this.region; }

	public int getPosX() { return this.posX; }

	public int getPosY() { return this.posY; }
}