/**
 * 
 */
package giosi.kurt.peter.language.course.sample;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String line = "basta de; Schluss mit";
		String[] split = line.split(";");
		for (String string : split) {
			System.out.println(string);
		}
	}
	
}
