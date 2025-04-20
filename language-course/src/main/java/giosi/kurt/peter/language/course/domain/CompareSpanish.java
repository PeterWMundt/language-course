/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import java.io.PrintStream;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ¿¡üúóíéáñ
 * 
 * áéíóúüñ¿¡
 * 
 * ÁÉÍÓÚÜÑ
 * 
 * 
 * @author peter.mundt@orcasys.ch
 */
public class CompareSpanish {

	private static final Logger  LOGGER = LoggerFactory.getLogger(CompareSpanish.class);

	private PrintStream out;

	public CompareSpanish(PrintStream out){
		this.out = out;
	}
	
	public int compare(String enteredText, String  correctSentence) {
		
		this.out.println("*****************************************");
		this.out.println("*** " + enteredText);
		this.out.println("*** " + correctSentence);

		int distance = LevenshteinDistance.getDefaultInstance().apply(this.normalizeWord(enteredText), this.normalizeWord(correctSentence));
		this.out.println("*** " + distance);
		
		return distance; 
	}
	
	/**
	 * ¿¡üúóíéáñ
	 * áéíóúüñ¿¡
	 * ÁÉÍÓÚÜÑ
	 * 
	 * @param word
	 * @return
	 */
	public String normalizeWord(String word) {
		LOGGER.trace("normalizeWord - word={}", word);

		word = word.replace("á", "a");
		word = word.replace("Á", "A");

		word = word.replace("é", "e");
		word = word.replace("É", "E");

		word = word.replace("í", "i");
		word = word.replace("Í", "I");

		word = word.replace("ñ", "n");
		word = word.replace("Ñ", "N");

		word = word.replace("ó", "o");
		word = word.replace("Ó", "O");

		word = word.replace("ú", "u");
		word = word.replace("ü", "u");
		word = word.replace("Ú", "U");
		word = word.replace("Ü", "U");

		word = word.replace("¿", "");
		word = word.replace("¡", "");

		word = word.replace("?", "");
		word = word.replace(".", "");
		word = word.replace(",", "");
		word = word.replace(";", "");

		LOGGER.trace("normalizeWord - DONE! word={}", word);
		return word; 
	}
	
}
