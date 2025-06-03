package giosi.kurt.peter.language.course.views.translate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import giosi.kurt.peter.language.course.domain.CompareSpanish;
import giosi.kurt.peter.language.course.domain.LanguageElement;
import giosi.kurt.peter.language.course.domain.LanguageElementRepository;
import giosi.kurt.peter.language.course.domain.SentencesRepository;

@SuppressWarnings("serial")
@PageTitle("Traducir frases")
@Route("") // .. default
@RouteAlias(TranslateSentences.TRANSLATE_SENTENCES_ROUTE_ALIAS)
@Menu(order = 1, icon = LineAwesomeIconUrl.ATOM_SOLID)
public class TranslateSentences extends VerticalLayout {

	private static final Logger  LOGGER = LoggerFactory.getLogger(TranslateSentences.class);

	public static final String	TRANSLATE_SENTENCES_ROUTE_ALIAS	= "translate-sentences";
	
	
	@Value("${translate.sentences.allowed.distance:3}")
	private int			allowedDistance;

	// domain
	
	private CompareSpanish				compareSpanish;
	private LanguageElementRepository	languageElementRepository;
	private LanguageElement				newLanguageElement;
	
	// ui 
	
	private Paragraph	paragraphListSentencesInformation;
	
	private TextField	nativeLanguage;
	private TextField	foreignLanguage;
	
	private Paragraph	paragraphCorrectSentence;
	
	private Button		buttonCompareSentence;
	private Button		buttonNewSentence;
	
	private Div			iconContainer	= new Div();
	
	private Icon		closeIcon		= VaadinIcon.CLOSE.create();
	private Icon		checkCircleIcon	= VaadinIcon.CHECK_CIRCLE.create();
	
	
	public TranslateSentences(@Autowired SentencesRepository sentencesRepository) {
		this.setSpacing(false);
		
		this.languageElementRepository = sentencesRepository;
		this.compareSpanish = new CompareSpanish(System.out);

		LOGGER.debug("allowedDistance ={}", this.allowedDistance);

		H2 header = new H2(this.getTranslation("translate.from.native.to.foreign"));
		header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
		this.add(header);
		
		this.paragraphListSentencesInformation = new Paragraph();
		this.add(this.paragraphListSentencesInformation);
		
		this.nativeLanguage = new TextField(this.getTranslation("native.language"));
		this.nativeLanguage.setWidthFull();
		this.add(this.nativeLanguage);
		
		this.foreignLanguage = new TextField(this.getTranslation("foreign.language"));
		this.foreignLanguage.setWidthFull();
		this.add(this.foreignLanguage);
		
		this.paragraphCorrectSentence = new Paragraph("");
		this.paragraphCorrectSentence.setHeight("2.5em");
		this.add(new HorizontalLayout(this.paragraphCorrectSentence, this.iconContainer));
		
		this.buttonCompareSentence = new Button(this.getTranslation("compare.sentence"));
		this.buttonCompareSentence.addClickListener(clickEvent -> {
			
			this.compare();
		});
		
		this.buttonNewSentence = new Button(this.getTranslation("new.sentence"));
		this.buttonNewSentence.addClickListener(clickEvent -> {
			//is.newLanguageElement = this.listSentences.get(this.random.nextInt(this.listSentences.size()));
			this.newLanguageElement = this.languageElementRepository.getNewLanguageElement();
			this.nativeLanguage.setValue(this.newLanguageElement.getNativeLanguage());
			this.foreignLanguage.setValue("");
			this.paragraphCorrectSentence.setText("");
			this.foreignLanguage.focus();
			this.buttonNewSentence.setEnabled(false);
			this.iconContainer.removeAll();
		});
		this.add(new HorizontalLayout(this.buttonCompareSentence, this.buttonNewSentence));
		
		//this.listSentences		= LanguageElementReader.readSentencesFromResources(BASE_SENTENCES_PATH);
		this.paragraphListSentencesInformation.setText(String.format(this.getTranslation("list.sentences.information"), this.languageElementRepository.listSize()));
	}
	
	private void compare() {
		String enteredText = this.foreignLanguage.getValue();
		String correctSentence = this.newLanguageElement.getForeignLanguage();
		
		int distance = this.compareSpanish.compare(enteredText, correctSentence);
		
		this.iconContainer.removeAll();
		if (this.allowedDistance < distance) {
			this.iconContainer.add(this.closeIcon);
		} else {
			this.iconContainer.add(this.checkCircleIcon);
		}
		
		this.paragraphCorrectSentence.setText(correctSentence);
		this.buttonNewSentence.setEnabled(true);
	}
	
}
