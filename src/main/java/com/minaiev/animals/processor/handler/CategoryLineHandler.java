package com.minaiev.animals.processor.handler;

import java.util.Collection;

/**
 * Interface for category line handler.
 */
public interface CategoryLineHandler {
    /**
     * Returns category of handler.
     *
     * @return category.
     */
    String getCategory();

    /**
     * Handles line from file.
     *
     * @param line line from file.
     */
    void handle(String line);

    /**
     * Checks if the handler can handle line.
     *
     * @param line line of file.
     * @return true if can handle.
     */
    boolean canHandle(String line);

    /**
     * Returns results from handler.
     *
     * @return collection with results.
     */
    Collection<String> getResults();

    /**
     * Returns formatted representation of results.
     *
     * @return formatted representation of results.
     */
    String getFormattedOutput();
}
