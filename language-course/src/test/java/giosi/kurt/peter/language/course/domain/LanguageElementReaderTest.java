/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * C:\\tmp\\sentences.txt
 * ----------------------
 * sentence1_spanish
 * sentence1_german
 * 
 * sentence2_spanish
 * sentence2_german
 *
 * @author peter.mundt@orcasys.ch
 */
public class LanguageElementReaderTest {
	
	@Test
	public void readSentencesFromResources_FILE_SYSTEM() {
		
		String pathAsString = "C:\\tmp\\sentences.txt";
		List<LanguageElement> list =  LanguageElementReader.readSentencesFromResources(pathAsString );
		for (LanguageElement languageElement : list) {
			System.out.println(languageElement);
		}
	}
	
	
}
