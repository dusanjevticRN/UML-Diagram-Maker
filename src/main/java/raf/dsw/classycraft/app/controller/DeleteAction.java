package raf.dsw.classycraft.app.controller;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractClassyAction{
    public DeleteAction() {
        super("/images/delete.png");

        this.putValue(SHORT_DESCRIPTION, "Delete");
        this.putValue(NAME, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
