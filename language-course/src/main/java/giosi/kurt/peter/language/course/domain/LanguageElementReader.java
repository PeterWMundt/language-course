/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.common.util.StringUtils;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public class LanguageElementReader {
	
	private static final Logger  LOGGER = LoggerFactory.getLogger(LanguageElementReader.class);

	/**
	 * Reads sentences from a file into a list. 
	 * <p>
	 * A language element contains two sentences the first is 'foreign' the second 'native'. <br>
	 * It is expected that these 2 sentences follow immediately. <br>
	 * Between the pairs of sentences can be any number of empty lines! 
	 * 
	 * @param pathAsString
	 * @return list of language elements 
	 */
	public static List<LanguageElement> readSentencesFromResources(String pathAsString)  {
    	LOGGER.debug("readSentencesFromResources - pathAsString={}", pathAsString);
		
		List<LanguageElement> listSentence = new ArrayList<>();
		
		try {
			Path path = Paths.get(pathAsString);
			List<String> lines = Files.lines(path).collect(Collectors.toList());
			
			String line1 = "";
			for (String line : lines) {
				String trimmed = line.trim();
				if(StringUtils.isNotEmpty(trimmed)) {
					if (0 == line1.length()) {
						line1 = trimmed;
					} else {
						LanguageElement languageElement = new LanguageElement(line1, trimmed);
						LOGGER.debug("readSentencesFromResources - languageElement={} ", languageElement);
						listSentence.add(languageElement);
						line1 = "";
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return listSentence;
	}
	
	
	/**
	 * basta de; Schluss mit
	 * 
	 *  
	 * @param wordPath
	 * @return
	 */
	public static List<LanguageElement> readWordsFromResources(String wordPath, String regex) {
		List<LanguageElement> listSentence = new ArrayList<>();
		
		try {
			Path path = Paths.get(wordPath);
			List<String> lines = Files.lines(path).collect(Collectors.toList());
			
			for (String line : lines) {
				
				String[] split = line.split(regex);
				if((null != split) && (2 <= split.length)) {
					listSentence.add( new LanguageElement(split[0].trim(), split[1].trim()));
				} else {
					LOGGER.warn("readWordsFromResources - parsing failed! line={}", line);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return listSentence;
	}


}
