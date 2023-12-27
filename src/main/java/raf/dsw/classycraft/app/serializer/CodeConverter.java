package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CodeConverter{


    public String convert(JsonNode rootNode) {
        StringBuilder code = new StringBuilder();
        JsonNode children = rootNode.path("children");

        for (JsonNode child : children) {
            JsonNode diagramElements = child.path("children").path("diagramElements");

            for (JsonNode element : diagramElements) {
                if ("class".equals(element.path("typeICC").asText())) {
                    String className = element.path("name").asText().split(":")[1];
                    code.append("class ").append(className).append(" {\n");

                    JsonNode classContents = element.path("ClassContent");
                    for (JsonNode classContent : classContents) {
                        if ("Atribut".equals(classContent.path("typeMA").asText())) {
                            String attributeName = classContent.path("name").asText();
                            String dataType = classContent.path("dataType").asText();
                            code.append("    private ").append(dataType).append(" ").append(attributeName).append(";\n");
                        }
                    }
                    code.append("}\n\n");
                }
            }
        }

        return code.toString();
    }
}

