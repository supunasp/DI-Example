package it.supunasp.di.annotate;

public enum ScopeType {
    /**
     * Object will be created one per application
     */
    SINGLETON,

    /**
     * New Instance will be created every time request is made
     */
    PROTOTYPE
}
