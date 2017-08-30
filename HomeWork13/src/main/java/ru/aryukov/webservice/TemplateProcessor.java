package ru.aryukov.webservice;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by dev on 08.08.17.
 */
public class TemplateProcessor {
    private static final String HTML_DIR = "/HomeWork13/tml";
    private static TemplateProcessor instance = new TemplateProcessor();

    private final Configuration configuration;

    public static TemplateProcessor instance() {
        return instance;
    }

    private TemplateProcessor() {
        configuration = new Configuration();
    }

    public String getPage(String filename, Map<String, Object> data) throws IOException {
        try {
            Writer stream = new StringWriter();
            Template template = configuration.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
            return stream.toString();

        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }
}
