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
	
	private CompareSpanish compareSpanish = new CompareSpanish(System.out);
	
	@Test
	public void normalizeWord() {
		assertEquals("estres", this.compareSpanish.normalizeWord("estrés"));
		assertEquals("Si", this.compareSpanish.normalizeWord("Sí"));
		
		assertEquals("aAeEiInNoOuuUU", this.compareSpanish.normalizeWord("áÁéÉíÍñÑóÓúüÚÜ¿¡?.,;"));
	}
	
	@Test
	public void compare() {
		assertEquals(0, this.compareSpanish.compare("alguien necesita una ambulancia", "¡Alguien necesita una ambulancia!" ));

		assertEquals(3, this.compareSpanish.compare("nunca almuerzo en el parque", "Yo nunca almuerzo en el parque." ));
	}
}
