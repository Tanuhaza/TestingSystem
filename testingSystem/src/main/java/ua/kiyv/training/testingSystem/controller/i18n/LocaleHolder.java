package ua.kiyv.training.testingSystem.controller.i18n;



import ua.kiyv.training.testingSystem.utils.constants.Attributes;

import java.util.Locale;

/**
 * Thi class contains all supported locales
 */
public class LocaleHolder {
    private Locale currentLocale;

    public static final Locale[] SUPPORTED_LOCALES = {
            new Locale(Attributes.EN, Attributes.EN.toUpperCase()),
            new Locale(Attributes.UA, Attributes.UA.toUpperCase()),
    };

    public static final Locale DEFAULT_LOCALE = new Locale(Attributes.EN, Attributes.EN.toUpperCase());

    public LocaleHolder(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}