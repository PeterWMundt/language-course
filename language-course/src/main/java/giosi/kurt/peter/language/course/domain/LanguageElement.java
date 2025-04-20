/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public class LanguageElement {
	
	public static final String	JSON_TEMPLATE		= "{\"foreignLanguage\" : \"%s\", \"native\" : \"%s\"}";
	

	
	private String	foreignLanguage;
	private String	nativeLanguage;
	
	public LanguageElement(String foreignLanguage, String nativeLanguage) {
		this.foreignLanguage = foreignLanguage;
		this.nativeLanguage = nativeLanguage;
	}
	
	
	
	public String getForeignLanguage() {
		return this.foreignLanguage;
	}



	public String getNativeLanguage() {
		return this.nativeLanguage;
	}



	@Override
	public String toString() {
		return String.format(JSON_TEMPLATE, this.foreignLanguage, this.nativeLanguage);
	}



}
