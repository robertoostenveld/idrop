package org.irods.jargon.idrop.desktop.systraygui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.irods.jargon.idrop.desktop.systraygui.IDROPDesktop;

public class EditSynchronizationDialogCancelActionListener implements ActionListener {

    private IDROPDesktop desktop;

    public EditSynchronizationDialogCancelActionListener(IDROPDesktop desktop) {
        super();
        this.desktop = desktop;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        desktop.editSynchronizationDialog.setVisible(false);
        desktop.editSynchronizationsDialog.requestFocus();
    }

}
