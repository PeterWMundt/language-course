/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public class WordsRepository extends LanguageElementRepository {

	private static final String REGEX = ";";

	/**
	 * @param path
	 */
	public WordsRepository(String path) {
		super(path);
	}

	@Override
	protected void readLanguageElementsFromResources(String path) {
		this.listLanguageElements		= LanguageElementReader.readWordsFromResources(path, REGEX);
	}
	
}
