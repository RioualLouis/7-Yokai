package ui;

import model.Famille;

public class StringFormat {
	private static String RESET = "\u001B[0m";
	private static String RESET_INTENSITY = "\u001B[22m";
	private static String BOLD = "\u001B[1m";
	private static String IT = "\u001B[3m";
	private static String STANDARD = "\u001B[38;5;244m";
	private static String HIGHLIGHT = "\u001B[38;5;14m";
	private static String MAJOR = "\u001B[38;5;221m";
	
	/*!
	 * \brief Applique le formatage pour un texte standard.
	 * 
	 * \param input La chaine à formater.
	 */
	public static String std(String input) {
		return STANDARD + input + RESET;
	}
	
	/*!
	 * \brief Applique le formatage de texte pour un texte à mettre en évidence.
	 * 
	 * \param input La chaine à formater.
	 */
	public static String emph(String input) {
		return BOLD + HIGHLIGHT + input + RESET;
	}
	
	/*!
	 * \brief Applique le formatage de texte pour un texte important.
	 * 
	 * \param input La chaine à formater.
	 */
	public static String strike(String input) {
		return IT + MAJOR + input + RESET;
	}
	
	/*!
	 * \brief Applique le formatage de texte pour un nom de joueur.
	 * 
	 * \param input La chaine à formater.
	 */
	public static String joueur(String input) {
		return BOLD + input + RESET_INTENSITY; 
	}
	
	/*!
	 * \brief Permet d'afficher la famille avec sa couleur dans un texte.
	 * 
	 * \param famille Famille à colorer.
	 * 
	 * \return une chaine de caractères à insérer pour afficher la famille avec la couleur correspondante. 
	 */
	public static String famille(Famille famille) {
		switch (famille) {
		case VERT : return BOLD + "\u001B[38;5;40m" + famille + RESET; //
		case GRIS : return BOLD + "\u001B[38;5;103m" + famille + RESET;
		case ROSE : return BOLD + "\u001B[38;5;207m" + famille + RESET;
		case JAUNE : return BOLD + "\u001B[38;5;221m" + famille + RESET;
		case VIOLET : return BOLD + "\u001B[38;5;141m" + famille + RESET;
		case ROUGE : return BOLD + "\u001B[38;5;202m" + famille + RESET;
		case BLEU : return BOLD + "\u001B[38;5;33m" + famille + RESET;
		default : return BOLD + famille + RESET;
		}
	}
}
