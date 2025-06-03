/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public abstract class LanguageElementRepository {

	
	protected Logger  logger = LoggerFactory.getLogger(this.getClass());

	protected List<LanguageElement>		listLanguageElements;
	
	private Random				random				= new Random();
	/**
	 * @param baseSentencesPath
	 */
	public LanguageElementRepository() {
	}
	
	protected abstract List<LanguageElement>	 readLanguageElementsFromResources();
	/**
	 * @return
	 */
	public LanguageElement getNewLanguageElement() {
		this.logger.debug("getNewLanguageElement");
		return this.getListLanguageElements().get(this.random.nextInt(this.getListLanguageElements().size()));
	}
	/**
	 * @return
	 */
	public int listSize() {
		this.logger.debug("listSize");
		return this.getListLanguageElements().size();
	}

	private List<LanguageElement> getListLanguageElements() {
		this.logger.debug("getListLanguageElements");
		if(null == this.listLanguageElements) {
			this.listLanguageElements = this.readLanguageElementsFromResources();			
		}
		return this.listLanguageElements;
	}

}
