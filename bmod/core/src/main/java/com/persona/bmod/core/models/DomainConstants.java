package com.persona.bmod.core.models;

public class DomainConstants {
    /**
     * This property is _not_ the way Thymeleaf is supposed to be used. It forces via boilerplate to use strict
     * typing on the name of a filed that is used to reference what view is being loaded within the main layout.
     * It does not even guarantee that if the name CONTENT_VIEW is changed, only the Java code is updated (but the
     * layout.html keeps referencing to the same name).
     * Conclusion: This is not proposing a front approach, but just toying around with concepts
     */
    // beware of using unsupported characters (e.g. '-')
    public static final String CONTENT_VIEW = "content_view";
}
