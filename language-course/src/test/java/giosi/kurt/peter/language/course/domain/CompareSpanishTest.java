/**
 * 
 */
package giosi.kurt.peter.language.course.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
public class CompareSpanishTest {
	
	CompareSpanish compareSpanish = new CompareSpanish(System.out);
	
	@Test
	public void normalizeWord() {
		assertEquals("estres", this.compareSpanish.normalizeWord("estrés"));
		assertEquals("Si", this.compareSpanish.normalizeWord("Sí"));

		assertEquals("aAeEiInNoOuuUU", this.compareSpanish.normalizeWord("áÁéÉíÍñÑóÓúüÚÜ¿¡?.,;"));
}
	
}
