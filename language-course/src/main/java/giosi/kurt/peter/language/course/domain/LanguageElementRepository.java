/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import java.util.List;
import java.util.Random;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public abstract class LanguageElementRepository {
	
	protected List<LanguageElement>		listLanguageElements;
	
	private Random				random				= new Random();
	/**
	 * @param baseSentencesPath
	 */
	public LanguageElementRepository(String path) {
		this.readLanguageElementsFromResources(path);
	}
	
	protected abstract void readLanguageElementsFromResources(String path);
	/**
	 * @return
	 */
	public LanguageElement getNewLanguageElement() {
		return this.listLanguageElements.get(this.random.nextInt(this.listLanguageElements.size()));
	}
	/**
	 * @return
	 */
	public int listSize() {
		return this.listLanguageElements.size();
	}

}
