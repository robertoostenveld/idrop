/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irods.jargon.idrop.desktop.systraygui;

import java.awt.Toolkit;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.irods.jargon.conveyor.core.ConveyorExecutionException;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import static org.irods.jargon.idrop.desktop.systraygui.TransferAccountingManagerDialog.log;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.SynchConfigTableModel;
import org.irods.jargon.idrop.finder.IRODSFinderDialog;
import org.irods.jargon.transfer.dao.domain.FrequencyType;
import org.irods.jargon.transfer.dao.domain.Synchronization;
import org.irods.jargon.transfer.dao.domain.SynchronizationType;
import org.openide.util.Exceptions;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lisa stillwell
 */
public class SynchronizationDialogTwo extends javax.swing.JDialog implements ListSelectionListener {

    private final IDROPCore idropCore;
    private int selectedTableRow = -1;
    private static final org.slf4j.Logger log = LoggerFactory
            .getLogger(SynchronizationDialogTwo.class);

    private Synchronization synchronization = null;

    /**
     * Creates new form SynchronizationDialog
     */
    //public SynchronizationDialog(java.awt.Frame parent, boolean modal, final IDROPCore idropCore) 
    public SynchronizationDialogTwo(AdvancedOptionsDialog parent, boolean modal, final IDROPCore idropCore) {
        super(parent, modal);
        if (idropCore == null) {
            throw new IllegalArgumentException("null idropCore");
        }

        this.idropCore = idropCore;
        initComponents();
        this.tblSynchs.getSelectionModel().addListSelectionListener(this);
        buildSynchTable();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnAddSynchActionPerformed = new javax.swing.JButton();
        btnEditSync = new javax.swing.JButton();
        btnRemoveSelected = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnLaunchSelected = new javax.swing.JButton();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        pnlTable = new javax.swing.JPanel();
        pnlSynchDetails = new javax.swing.JPanel();
        lblSynchDetails = new javax.swing.JLabel();
        scrollPanelSynchs = new javax.swing.JScrollPane();
        tblSynchs = new javax.swing.JTable();
        pnlBottom = new javax.swing.JPanel();
        bntClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 450));

        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 4, 4, 4));
        pnlMain.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 68));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnAddSynchActionPerformed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/irods/jargon/idrop/desktop/systraygui/images/glyphicons_190_circle_plus.png"))); // NOI18N
        btnAddSynchActionPerformed.setMnemonic('a');
        btnAddSynchActionPerformed.setText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnAddSynchActionPerformed.text")); // NOI18N
        btnAddSynchActionPerformed.setToolTipText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnAddSynchActionPerformed.toolTipText")); // NOI18N
        btnAddSynchActionPerformed.setActionCommand(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnAddSynchActionPerformed.actionCommand")); // NOI18N
        btnAddSynchActionPerformed.setFocusable(false);
        btnAddSynchActionPerformed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddSynchActionPerformed.setPreferredSize(new java.awt.Dimension(74, 55));
        btnAddSynchActionPerformed.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddSynchActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSynchActionPerformedActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddSynchActionPerformed);

        btnEditSync.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/irods/jargon/idrop/desktop/systraygui/images/glyphicons_030_pencil.png"))); // NOI18N
        btnEditSync.setMnemonic('e');
        btnEditSync.setToolTipText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnEditSync.toolTipText")); // NOI18N
        btnEditSync.setEnabled(false);
        btnEditSync.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditSync.setLabel(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnEditSync.label")); // NOI18N
        btnEditSync.setPreferredSize(new java.awt.Dimension(74, 55));
        btnEditSync.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSyncActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditSync);

        btnRemoveSelected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/irods/jargon/idrop/desktop/systraygui/images/glyphicons_016_bin.png"))); // NOI18N
        btnRemoveSelected.setMnemonic('d');
        btnRemoveSelected.setText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnRemoveSelected.text")); // NOI18N
        btnRemoveSelected.setToolTipText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnRemoveSelected.toolTipText")); // NOI18N
        btnRemoveSelected.setEnabled(false);
        btnRemoveSelected.setFocusable(false);
        btnRemoveSelected.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRemoveSelected.setPreferredSize(new java.awt.Dimension(74, 55));
        btnRemoveSelected.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRemoveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSelectedActionPerformed(evt);
            }
        });
        jPanel1.add(btnRemoveSelected);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/irods/jargon/idrop/desktop/systraygui/images/glyphicons_081_refresh.png"))); // NOI18N
        btnRefresh.setMnemonic('r');
        btnRefresh.setText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnRefresh.text")); // NOI18N
        btnRefresh.setToolTipText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnRefresh.toolTipText")); // NOI18N
        btnRefresh.setFocusable(false);
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnRefresh.setPreferredSize(new java.awt.Dimension(74, 55));
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh);

        btnLaunchSelected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/irods/jargon/idrop/desktop/systraygui/images/glyphicons_173_play.png"))); // NOI18N
        btnLaunchSelected.setMnemonic('b');
        btnLaunchSelected.setText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnLaunchSelected.text")); // NOI18N
        btnLaunchSelected.setToolTipText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.btnLaunchSelected.toolTipText")); // NOI18N
        btnLaunchSelected.setEnabled(false);
        btnLaunchSelected.setFocusable(false);
        btnLaunchSelected.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLaunchSelected.setPreferredSize(new java.awt.Dimension(74, 55));
        btnLaunchSelected.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLaunchSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaunchSelectedActionPerformed(evt);
            }
        });
        jPanel1.add(btnLaunchSelected);
        jPanel1.add(filler11);
        jPanel1.add(filler13);

        pnlMain.add(jPanel1, java.awt.BorderLayout.NORTH);

        pnlTable.setLayout(new java.awt.BorderLayout());

        lblSynchDetails.setText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.lblSynchDetails.text")); // NOI18N
        pnlSynchDetails.add(lblSynchDetails);

        pnlTable.add(pnlSynchDetails, java.awt.BorderLayout.NORTH);

        tblSynchs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPanelSynchs.setViewportView(tblSynchs);

        pnlTable.add(scrollPanelSynchs, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlTable, java.awt.BorderLayout.CENTER);

        pnlBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bntClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/irods/jargon/idrop/desktop/systraygui/images/glyphicons_388_exit.png"))); // NOI18N
        bntClose.setMnemonic('E');
        bntClose.setText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.bntClose.text")); // NOI18N
        bntClose.setToolTipText(org.openide.util.NbBundle.getMessage(SynchronizationDialogTwo.class, "SynchronizationDialogTwo.bntClose.toolTipText")); // NOI18N
        bntClose.setFocusable(false);
        bntClose.setPreferredSize(new java.awt.Dimension(82, 42));
        bntClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCloseActionPerformed(evt);
            }
        });
        pnlBottom.add(bntClose);

        pnlMain.add(pnlBottom, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSelectedActionPerformed
            log.info("btnRemoveSelectedActionPerformed");
              if (synchronization == null) {
            log.error("cannot de;ete, no synch provided");
        }

        log.info("deleting synch:{}", synchronization);
        try {
            idropCore.getConveyorService().getSynchronizationManagerService().deleteSynchronization(synchronization);
            MessageManager.showMessage(this, "Synchronization was deleted");
            this.buildSynchTable();
        } catch (ConveyorExecutionException ex) {
            log.error("unable to launch synchronization", ex);
            MessageManager.showError(this, ex.getMessage());
        }



    }//GEN-LAST:event_btnRemoveSelectedActionPerformed

    private void btnLaunchSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaunchSelectedActionPerformed

        log.info("launching synchronization...");

        if (synchronization == null) {
            log.error("cannot launch, no synch provided");
        }

        log.info("launching synch:{}", synchronization);
        try {
            idropCore.getConveyorService().getSynchronizationManagerService().triggerSynchronizationNow(synchronization);
            MessageManager.showMessage(this, "Synchronization was triggered");
        } catch (ConveyorExecutionException ex) {
            log.error("unable to launch synchronization", ex);
            MessageManager.showError(this, ex.getMessage());
        }

    }//GEN-LAST:event_btnLaunchSelectedActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        final SynchronizationDialogTwo dialog = this;

        log.info("refreshing transfer table");

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog.buildSynchTable();

            }
        });
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void bntCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCloseActionPerformed
        this.dispose();


    }//GEN-LAST:event_bntCloseActionPerformed

    private void btnAddSynchActionPerformedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSynchActionPerformedActionPerformed

        AddSynchronizationDialog dlgAddSynchronization = new AddSynchronizationDialog(this, true, idropCore);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (toolkit.getScreenSize().width - dlgAddSynchronization
                    .getWidth()) / 2;
        int y = (toolkit.getScreenSize().height - dlgAddSynchronization
                    .getHeight()) / 2;
        dlgAddSynchronization.setLocation(x, y);
        dlgAddSynchronization.setVisible(true);
        final Synchronization newSync = dlgAddSynchronization.getSynchronization();
        
        final SynchronizationDialogTwo dialog = this;

        log.info("refreshing transfer table");

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                log.info("updating!");
                if (newSync != null) {
                    dialog.buildSynchTable();
                }

            }

        });
    }//GEN-LAST:event_btnAddSynchActionPerformedActionPerformed

    private void btnEditSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSyncActionPerformed
        
        if (synchronization != null) {  
            
            AddSynchronizationDialog dlgAddSynchronization = new AddSynchronizationDialog(this, true, idropCore, synchronization);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            int x = (toolkit.getScreenSize().width - dlgAddSynchronization
                        .getWidth()) / 2;
            int y = (toolkit.getScreenSize().height - dlgAddSynchronization
                        .getHeight()) / 2;
            dlgAddSynchronization.setLocation(x, y);
            dlgAddSynchronization.setVisible(true);
            final Synchronization newSync = dlgAddSynchronization.getSynchronization();

            final SynchronizationDialogTwo dialog = this;

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    log.info("updating!");
                    if (newSync != null) {
                        dialog.buildSynchTable();
                    }

                }

            });
        }
        else {
            log.error("cannot edit, no synch provided");
        }
    }//GEN-LAST:event_btnEditSyncActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntClose;
    private javax.swing.JButton btnAddSynchActionPerformed;
    private javax.swing.JButton btnEditSync;
    private javax.swing.JButton btnLaunchSelected;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemoveSelected;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblSynchDetails;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSynchDetails;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JScrollPane scrollPanelSynchs;
    private javax.swing.JTable tblSynchs;
    // End of variables declaration//GEN-END:variables

    private void buildSynchTable() {
        log.info("buildSynchTable()");
        try {
            List<Synchronization> synchs = idropCore.getConveyorService().getSynchronizationManagerService().listAllSynchronizations();
            SynchConfigTableModel synchConfigTableModel = new SynchConfigTableModel(idropCore, synchs);
            tblSynchs.setModel(synchConfigTableModel);
            if (synchs.size() > 0) {
                tblSynchs.getSelectionModel().setLeadSelectionIndex(0);
            }
        } catch (ConveyorExecutionException ex) {
            log.error("error listing synchs", ex);
            MessageManager.showError(this, ex.getMessage());
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (e.getValueIsAdjusting()) {
            return;
        }

        int selectedRow = tblSynchs.getSelectedRow();

        if (selectedRow >= 0) {
            selectedRow = tblSynchs.convertRowIndexToModel(selectedRow);
            SynchConfigTableModel synchConfigTableModel = (SynchConfigTableModel) tblSynchs.getModel();
            this.setSynchronization(synchConfigTableModel.getSynchronizationAt(selectedRow));
            initViewFromSynch();
            log.info("selected:{}", this.getSynchronization());
        }
        else {
            configureButtonsForSynchSelected(false);
        }

    }

    private void initViewFromSynch() {
//        if (synchronization == null) {
//            synchronization = new Synchronization();
//        }

        final SynchronizationDialogTwo dialog = this;

        log.info("init view from sync");

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                configureButtonsForSynchSelected(true);
            }

        });

    }

    synchronized void setSynchronization(final Synchronization synchronization) {
        this.synchronization = synchronization;
    }

    synchronized Synchronization getSynchronization() {
        return this.synchronization;
    }

    private void configureButtonsForSynchSelected(boolean isSelected) {
        this.btnLaunchSelected.setEnabled(isSelected);
        this.btnRemoveSelected.setEnabled(isSelected);
        this.btnEditSync.setEnabled(isSelected);
    }

}

class FrequencyTypeModelToo extends AbstractListModel<FrequencyType> implements ComboBoxModel<FrequencyType> {

    private FrequencyType selected = null;

    FrequencyType[] frequencyTypes = FrequencyType.values();

    @Override
    public int getSize() {
        return frequencyTypes.length;
    }

    @Override
    public FrequencyType getElementAt(int index) {
        return frequencyTypes[index];
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (FrequencyType) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

}
