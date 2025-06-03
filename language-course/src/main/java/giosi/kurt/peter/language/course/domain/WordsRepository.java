/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
@Component
public class WordsRepository extends LanguageElementRepository {

	private static final String REGEX = ";";

	@Value("${words.path:src/main/resources/words.txt}")
	private String	wordsPath;


	@Override
	protected List<LanguageElement> readLanguageElementsFromResources() {
		this.logger.debug("readLanguageElementsFromResources - wordsPath={}", this.wordsPath);
		return LanguageElementReader.readWordsFromResources(this.wordsPath, REGEX);
	}
	
}
