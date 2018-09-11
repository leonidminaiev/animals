package com.minaiev.animals.processor.handler.impl;

import com.google.common.collect.Sets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

/**
 * Category Line Handler implementation for Cars category.
 */
public class CarsLineHandler extends AbstractCategoryLineHandler {

    private static final String OUTPUT_FORMAT = "%s (%s)";
    private static final String CARS_CATEGORY = "CARS";

    public CarsLineHandler() {
        super(CARS_CATEGORY, Sets.newTreeSet(Comparator.reverseOrder()),
                collection -> collection.stream()
                        .map(car -> String.format(OUTPUT_FORMAT, car, DigestUtils.md5Hex(car)))
                        .reduce((car1, car2) -> car1 + "\n" + car2)
                        .orElse(StringUtils.EMPTY));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(String line) {
        super.handle(line.toLowerCase());
    }
}
