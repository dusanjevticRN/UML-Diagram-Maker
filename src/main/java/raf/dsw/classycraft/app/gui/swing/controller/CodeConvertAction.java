package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.serializer.CodeConverter;

import java.awt.event.ActionEvent;

public class CodeConvertAction extends AbstractClassyAction{
    public CodeConvertAction() {
        this.putValue(SMALL_ICON, loadIcon("/images/code.png"));
        this.putValue(SHORT_DESCRIPTION, "Code");
        this.putValue(NAME, "Code");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        CodeConverter codeConverter = new CodeConverter(project);
        codeConverter.convert();
    }
}
