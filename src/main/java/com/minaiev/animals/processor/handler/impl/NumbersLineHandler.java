package com.minaiev.animals.processor.handler.impl;

import com.google.common.collect.HashMultiset;
import org.apache.commons.lang3.StringUtils;

/**
 * Category Line Handler implementation for Numbers category.
 */
public class NumbersLineHandler extends AbstractCategoryLineHandler {

    private static final String OUTPUT_FORMAT = "%s: %s";
    private static final String NUMBERS_CATEGORY = "NUMBERS";

    public NumbersLineHandler() {
        super(NUMBERS_CATEGORY, HashMultiset.create(),
                collection -> {
                    HashMultiset<String> multiset = (HashMultiset<String>) collection;
                    return multiset.entrySet().stream()
                            .map(numberEntry -> String.format(OUTPUT_FORMAT, numberEntry.getElement(), numberEntry.getCount()))
                            .reduce((num1, num2) -> num1 + "\n" + num2)
                            .orElse(StringUtils.EMPTY);
                });
    }
}
