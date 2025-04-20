/**
 * 
 */
package giosi.kurt.peter.language.course.views.grammar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.componentfactory.pdfviewer.PdfViewer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

/**
 * 
 *
 * @author peter.mundt@orcasys.ch
 */
@SuppressWarnings("serial")
@PageTitle("Ver gramática")
@Route("grammar-view")
@Menu(order = 3, icon = LineAwesomeIconUrl.ADDRESS_BOOK)
public class GrammarView extends VerticalLayout {

	private static final Logger  LOGGER = LoggerFactory.getLogger(GrammarView.class);

	public GrammarView() {
		LOGGER.debug("constructor");

		this.setSizeFull();

		PdfViewer pdfViewer = new PdfViewer();
		StreamResource resource = new StreamResource("sprachurlaub.de_grammatik-spanisch.pdf", () -> this.getClass().getResourceAsStream("/sprachurlaub.de_grammatik-spanisch.pdf"));
		pdfViewer.setSrc(resource);
		pdfViewer.setSizeFull();
		this.add(pdfViewer);
	}
}
