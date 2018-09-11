package com.minaiev.animals;

import com.minaiev.animals.processor.FileProcessor;
import com.minaiev.animals.processor.handler.CategoryLineHandler;
import com.minaiev.animals.processor.handler.impl.AnimalsLineHandler;
import com.minaiev.animals.processor.handler.impl.CarsLineHandler;
import com.minaiev.animals.processor.handler.impl.NumbersLineHandler;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;


/**
 * Main Application class.
 * Starts file processing for given filename.
 */
public class Application {

    public static void main(String[] args) throws IOException {
        Application application = new Application();
        try {
            String filename = getFilenameFromArgs(args).orElseThrow(IllegalArgumentException::new);
            application.processFile(filename, configureHandlers());
        } catch (IllegalArgumentException e) {
            System.out.println("Please provide filename within argument");
        }
    }

    private static Optional<String> getFilenameFromArgs(String[] args) {
        return Arrays.stream(args).filter(Objects::nonNull).findFirst();
    }

    private void processFile(String filename, Collection<CategoryLineHandler> handlers) {
        try {
            String output = new FileProcessor()
                    .setLineHandlers(handlers)
                    .processFile(filename);
            System.out.println(output);
        } catch (NoSuchFileException e) {
            System.out.printf("File '%s' not found.", filename);
        } catch (Exception e) {
            System.out.printf("Error reading file '%s'", filename);
        }
    }

    private static Collection<CategoryLineHandler> configureHandlers() {
        return Arrays.asList(new AnimalsLineHandler(), new NumbersLineHandler(), new CarsLineHandler());
    }
}
