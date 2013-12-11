package bundles;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

public class BundlesTest {

    @Test
    public void should_display_value_with_language() {
        ResourceBundle frBundle = ResourceBundle.getBundle("bundles.BundlesTest", Locale.FRENCH);

        String translatedValue = frBundle.getString("value");

        assertThat(translatedValue).isEqualTo("francais");
    }

    @Test
    public void should_display_value_with_language_and_country() {
        ResourceBundle fr_FRBundle = ResourceBundle.getBundle("bundles.BundlesTest", Locale.FRANCE);

        String translatedValue = fr_FRBundle.getString("value");

        assertThat(translatedValue).isEqualTo("francais de France");
    }

    @Test
    public void should_display_value_with_default_Locale() {
        Locale defaultLocale = Locale.getDefault();
        try {
            Locale.setDefault(Locale.ENGLISH);
            ResourceBundle enBundle = ResourceBundle.getBundle("bundles.BundlesTest");

            String translatedValue = enBundle.getString("value");

            assertThat(translatedValue).isEqualTo("english");
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    @Test
    public void should_display_value_without_Locale_when_no_file_given() {
        Locale defaultLocale = Locale.getDefault();
        try {
            Locale.setDefault(Locale.CHINA);
            ResourceBundle defaultBundle = ResourceBundle.getBundle("bundles.BundlesTest", Locale.GERMANY);

            String translatedValue = defaultBundle.getString("value");

            assertThat(translatedValue).isEqualTo("esperanto");
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    @Test
    public void should_use_fallbackLocale_from_Control() {
        ResourceBundle defaultBundle = ResourceBundle.getBundle("bundles.BundlesTest", Locale.GERMANY, new ResourceBundle.Control() {
            @Override
            public Locale getFallbackLocale(String baseName, Locale locale) {
                return Locale.FRANCE;
            }
        });

        String translatedValue = defaultBundle.getString("value");

        assertThat(translatedValue).isEqualTo("francais de France");
    }

}
