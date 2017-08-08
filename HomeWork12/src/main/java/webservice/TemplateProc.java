package webservice;

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
public class TemplateProc {
    private static final String HTML_DIR = "tml";
    private static TemplateProc instance = new TemplateProc();

    private final Configuration configuration;

    public static TemplateProc instance() {
        return instance;
    }

    private TemplateProc() {
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
