package it.supunasp.di.scope;

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
