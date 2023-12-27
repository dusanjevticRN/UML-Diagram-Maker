package raf.dsw.classycraft.app.gui.swing.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.serializer.CodeConverter;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;

public class CodeConvertAction extends AbstractClassyAction{
    public CodeConvertAction() {
        this.putValue(SMALL_ICON, loadIcon("/images/code.png"));
        this.putValue(SHORT_DESCRIPTION, "Code");
        this.putValue(NAME, "Code");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectMapper objectMapper = new ObjectMapper();
        CodeConverter codeConverter = new CodeConverter();
        Diagram diagram = MainFrame.getInstance().getPackageView().getDiagram();
        try {
            AppCore.getInstance().getPatternSerializer().saveProject("saved_codes/" + "tmpCode.json", diagram);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            // Assuming the file path is correct and the file exists
            File file = new File("saved_codes/tmpCode.json");
            JsonNode rootNode = objectMapper.readTree(file);

            String code = codeConverter.convert(rootNode);
            System.out.println(code);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
