package giosi.kurt.peter.language.course.views.translate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import giosi.kurt.peter.language.course.domain.CompareSpanish;
import giosi.kurt.peter.language.course.domain.LanguageElement;
import giosi.kurt.peter.language.course.domain.LanguageElementRepository;
import giosi.kurt.peter.language.course.domain.WordsRepository;

@SuppressWarnings("serial")
@PageTitle("Traducir palabras")
@Route(TranslateWords.TRANSLATE_WORDS_PATH)
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class TranslateWords extends VerticalLayout {
	
	private static final Logger			LOGGER					= LoggerFactory.getLogger(TranslateWords.class);
	
	public static final String			TRANSLATE_WORDS_PATH	= "translate-words";
	
	public static final String			WORD_PATH				= "src/main/resources/words.txt";
	
	private static final String			WIDTH_WORD				= "50%";
	
	private CompareSpanish				compareSpanish;
	private LanguageElementRepository	languageElementRepository;
	private LanguageElement				newLanguageElement;
	
	// ui 
	
	private Paragraph	paragraphListSentencesInformation;
	
	private TextField	nativeLanguage;
	private TextField	foreignLanguage;
	
	private Paragraph	paragraphCorrectWord;
	
	private Button		buttonCompare;
	private Button		buttonNew;
	
	private Div			iconContainer	= new Div();
	
	private Icon		closeIcon		= VaadinIcon.CLOSE.create();
	private Icon		checkCircleIcon	= VaadinIcon.CHECK_CIRCLE.create();
	
	@Value("${translate.words.allowed.distance:3}")
	private int			allowedDistance;
	
	public TranslateWords() {
		this.setSpacing(false);

		this.languageElementRepository = new WordsRepository(WORD_PATH);
		this.compareSpanish = new CompareSpanish(System.out);

		H2 header = new H2(this.getTranslation("translate.from.native.to.foreign"));
		header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
		this.add(header);
		
		this.paragraphListSentencesInformation = new Paragraph();
		this.add(this.paragraphListSentencesInformation);
		
		this.nativeLanguage = new TextField(this.getTranslation("native.language"));
		this.nativeLanguage.setWidth(WIDTH_WORD);
		
		this.add(this.nativeLanguage);
		
		this.foreignLanguage = new TextField(this.getTranslation("foreign.language"));
		this.foreignLanguage.setWidth(WIDTH_WORD);
		this.add(this.foreignLanguage);

		this.paragraphCorrectWord = new Paragraph("");
		this.paragraphCorrectWord.setHeight("2.5em");
		this.add(new HorizontalLayout(this.paragraphCorrectWord, this.iconContainer));
		

		this.buttonCompare = new Button(this.getTranslation("compare.word"));
		this.buttonCompare.addClickListener(clickEvent -> {
			
			this.compare();
		});
		
		this.buttonNew = new Button(this.getTranslation("new.word"));
		this.buttonNew.addClickListener(clickEvent -> {
			//is.newWord = this.listWords.get(this.random.nextInt(this.listWords.size()));
			this.newLanguageElement = this.languageElementRepository.getNewLanguageElement();
			this.nativeLanguage.setValue(this.newLanguageElement.getNativeLanguage());
			this.foreignLanguage.setValue("");
			this.paragraphCorrectWord.setText("");
			this.foreignLanguage.focus();
			this.buttonNew.setEnabled(false);
			this.iconContainer.removeAll();
		});
		this.add(new HorizontalLayout(this.buttonCompare, this.buttonNew));
		
		//this.listWords = LanguageElementReader.readWordsFromResources(WORD_PATH);
		this.paragraphListSentencesInformation.setText(String.format(this.getTranslation("list.words.information"), this.languageElementRepository.listSize()));
	}
	
	private void compare() {
		LOGGER.debug("compare");
		
		String enteredText = this.foreignLanguage.getValue();
		String correctSentence = this.newLanguageElement.getForeignLanguage();
		
		//t distance = LevenshteinDistance.getDefaultInstance().apply(enteredText, correctSentence);
		int distance = this.compareSpanish.compare(enteredText, correctSentence);
		
		this.iconContainer.removeAll();
		if (this.allowedDistance < distance) {
			this.iconContainer.add(this.closeIcon);
		} else {
			this.iconContainer.add(this.checkCircleIcon);
		}
		
		this.paragraphCorrectWord.setText(correctSentence);
		this.buttonNew.setEnabled(true);
	}
	
}
