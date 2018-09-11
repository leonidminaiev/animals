package com.minaiev.animals.processor.handler.impl;

import com.minaiev.animals.processor.handler.CategoryLineHandler;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

/**
 * Abstract implementation of <code>CategoryLineHandler</code>.
 */
public abstract class AbstractCategoryLineHandler implements CategoryLineHandler {
    private String category;
    private Collection<String> storage;
    private Function<Collection<String>, String> resultsFormatter;

    protected AbstractCategoryLineHandler(String category, Collection<String> storage,
                                          Function<Collection<String>, String> resultsFormatter) {
        this.category = category;
        this.storage = storage;
        this.resultsFormatter = resultsFormatter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(String line) {
        storage.add(line);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canHandle(String line) {
        return !Objects.equals(line, category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getResults() {
        return storage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedOutput() {
        return resultsFormatter.apply(getResults());
    }
}
