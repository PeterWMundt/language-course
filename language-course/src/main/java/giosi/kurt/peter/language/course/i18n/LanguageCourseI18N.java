package giosi.kurt.peter.language.course.i18n;

//public class Vap3I18N {}

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.ResourceBundle.getBundle;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.vaadin.flow.i18n.I18NProvider;

@SuppressWarnings("serial")
@Component
public class LanguageCourseI18N implements I18NProvider {

	public static final String	RESOURCE_BUNDLE_NAME	= "language_course";

	private static final Locale			ENGLISH					= Locale.ENGLISH;
	private static final Locale			es_ES					=  Locale.of("es", "ES");

	private static final List<Locale>	providedLocales			= unmodifiableList(asList(ENGLISH,es_ES));


	private static final ResourceBundle	RESOURCE_BUNDLE_EN		= getBundle(RESOURCE_BUNDLE_NAME, ENGLISH);
	private static final ResourceBundle	RESOURCE_BUNDLE_es_ES		= getBundle(RESOURCE_BUNDLE_NAME, es_ES);

	@Override
	public List<Locale> getProvidedLocales() {
		return providedLocales;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		ResourceBundle resourceBundle = es_ES.equals(locale) ? RESOURCE_BUNDLE_es_ES : RESOURCE_BUNDLE_EN;

		if (resourceBundle.containsKey(key)) {
			if ((params == null) 	|| (0 == params.length)) {
				return resourceBundle.getString(key);
			} else {
				return MessageFormat.format(resourceBundle.getString(key), params);
			}
		} else {
			// TODO logger().info("missing resource key (i18n) " + key);
			return key + " - " + locale;

		}
	}

}


