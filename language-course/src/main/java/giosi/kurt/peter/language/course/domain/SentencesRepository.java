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
public class SentencesRepository extends LanguageElementRepository {

	
	@Value("${base.sentences.path:src/main/resources/base_sentences.txt}")
	private String	baseSentencesPath;

	@Override
	protected List<LanguageElement>	 readLanguageElementsFromResources() {
		this.logger.debug("readLanguageElementsFromResources - baseSentencesPath={}", this.baseSentencesPath);
		return LanguageElementReader.readSentencesFromResources(this.baseSentencesPath);
	}

}
