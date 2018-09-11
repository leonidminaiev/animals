package com.minaiev.animals.processor.handler.impl;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

/**
 * Category Line Handler implementation for Animals category.
 */
public class AnimalsLineHandler extends AbstractCategoryLineHandler {

    private static final String ANIMALS_CATEGORY = "ANIMALS";

    public AnimalsLineHandler() {
        super(ANIMALS_CATEGORY, Sets.newTreeSet(),
                collection -> collection.stream()
                        .reduce((animal1, animal2) -> animal1 + "\n" + animal2)
                        .orElse(StringUtils.EMPTY));
    }
}

