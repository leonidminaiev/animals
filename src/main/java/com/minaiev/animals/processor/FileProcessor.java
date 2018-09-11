package com.minaiev.animals.processor;

import com.minaiev.animals.processor.handler.CategoryLineHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * Process file with given collection of <code>CategoryLineHandler</code> to handle lines of file and prints results.
 */
public class FileProcessor {
    /**
     * Configured category line handlers.
     */
    private Collection<CategoryLineHandler> lineHandlers = Collections.emptyList();
    /**
     * Current category storage.
     */
    private CategoryLineHandler currentHandler;

    /**
     * Setups line handlers for this processor.
     *
     * @param lineHandlers collections of <code>LineHandler</code>.
     * @return this <code>FileProcessor</code> object.
     */
    public FileProcessor setLineHandlers(Collection<CategoryLineHandler> lineHandlers) {
        this.lineHandlers = lineHandlers;
        return this;
    }

    /**
     * Processes given file with configured handlers.
     *
     * @param filename File name to process.
     * @throws IOException in case of any IO Exception.
     */
    public String processFile(String filename) throws IOException {
        Files.lines(Paths.get(filename))
                .map(line -> {
                    currentHandler = getCategoryHandler(line).orElse(currentHandler);
                    return Pair.of(currentHandler, line);
                })
                .filter(handlerLinePair -> Objects.nonNull(handlerLinePair.getKey()))
                .filter(handlerLinePair -> handlerLinePair.getKey().canHandle(handlerLinePair.getValue()))
                .forEachOrdered(handlerLinePair -> handlerLinePair.getKey().handle(handlerLinePair.getValue()));

        return formatSummaryResults();
    }

    /**
     * Returns category line handler if line equals to category of handler.
     *
     * @param line Line from file to be handled.
     * @return Category line handler if present.
     */
    private Optional<CategoryLineHandler> getCategoryHandler(String line) {
        return lineHandlers.stream()
                .filter(handlerEntry -> handlerEntry.getCategory().equals(line))
                .findFirst();
    }

    private String formatSummaryResults() {
        return lineHandlers.stream()
                .map(handler -> String.format("%s:\n%s", handler.getCategory(), handler.getFormattedOutput()))
                .reduce((output1, output2) -> output1 + "\n" + output2)
                .orElse(StringUtils.EMPTY);
    }
}
