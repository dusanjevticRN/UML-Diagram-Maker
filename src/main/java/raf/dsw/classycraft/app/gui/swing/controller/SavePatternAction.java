package raf.dsw.classycraft.app.gui.swing.controller;

import java.awt.event.ActionEvent;

public class SavePatternAction extends AbstractClassyAction{
    public SavePatternAction() {
        this.putValue(SMALL_ICON, loadIcon("/images/save.png"));
        this.putValue(SHORT_DESCRIPTION, "SavePattern");
        this.putValue(NAME, "SavePattern");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
