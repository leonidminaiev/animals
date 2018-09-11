package com.minaiev.animals.processor;

import com.minaiev.animals.processor.handler.impl.AnimalsLineHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class FileProcessorTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private FileProcessor fileProcessor;
    private File file;

    @Before
    public void setUp() throws IOException {
        fileProcessor = new FileProcessor();
        file = testFolder.newFile();
    }

    @Test
    public void emptyFileShouldNotLeadToFail() throws IOException {
        fileProcessor.processFile(file.getAbsolutePath());
    }

    @Test
    public void shouldCorrectlyProcessFile() throws IOException {
        fileProcessor.setLineHandlers(Collections.singletonList(new AnimalsLineHandler()));
        String text = "ANIMALS\nsheep\ncow";

        Files.write(Paths.get(file.toURI()), text.getBytes());
        fileProcessor.processFile(file.getAbsolutePath());
    }

}