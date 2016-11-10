package net.tqxr.containers.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FtlTransformer extends ResponseDefinitionTransformer {
    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {


        String body = responseDefinition.getBody();

        System.out.println(
                String.format(
                        "** FILES ** PATH: '%s', URI: '%s', EXISTS: %b",
                        files.getPath(),
                        files.getUri(),
                        files.exists()
                )
        );

        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);

            if (!responseDefinition.getBodyFileName().isEmpty()) {
                System.out.println("BODY FILE NAME: " + responseDefinition.getBodyFileName());
                body = new String(Files.readAllBytes(
                        FileSystems.getDefault().getPath(
                                Paths.get(files.getPath().toString(),
                                responseDefinition.getBodyFileName()).toString()
                        )));
            }

            Template template = new Template("TPL", new StringReader(body), configuration);

            StringWriter writer = new StringWriter();
            template.process(parameters, writer);
            body = writer.toString();

            System.out.println("---------------------------------------------------------------");
            System.out.println("BODY:");
            System.out.println(body);
            System.out.println("---------------------------------------------------------------");

            ResponseDefinitionBuilder
                    .like(responseDefinition)
                    .withBody(body)
                    .build();
        } catch (Exception ignored) {
        }

        return new ResponseDefinitionBuilder()
//                .withHeader("MyHeader", "Transformed")
                .withHeaders(responseDefinition.getHeaders())
                .withStatus(200)
                .withBody(body)
                .build();

    }


    @Override
    public String getName() {
        return "freemarker-renderer";
    }
}
