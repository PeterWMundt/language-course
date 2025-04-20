/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public class SentencesRepository extends LanguageElementRepository {

	/**
	 * @param path
	 */
	public SentencesRepository(String path) {
		super(path);
	}

	@Override
	protected void readLanguageElementsFromResources(String path) {
		this.listLanguageElements		= LanguageElementReader.readSentencesFromResources(path);
	}
	
}
