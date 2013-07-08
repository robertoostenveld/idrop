/*
 * ReparentIRODSDirectoryDialog.java
 *
 * Created on Sep 3, 2010, 9:52:12 AM
 */
package org.irods.jargon.idrop.desktop.systraygui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;
import org.irods.jargon.conveyor.core.ConveyorExecutionException;
import org.irods.jargon.conveyor.core.QueueManagerService;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonFileOrCollAlreadyExistsException;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.idrop.desktop.systraygui.utils.TreeUtils;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.IRODSNode;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.IRODSOutlineModel;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.IRODSTree;
import org.irods.jargon.idrop.desktop.systraygui.viscomponents.LocalFileTree;
import org.irods.jargon.idrop.exceptions.IdropException;
import org.irods.jargon.idrop.exceptions.IdropRuntimeException;
import org.irods.jargon.transfer.dao.domain.Transfer;
import org.irods.jargon.transfer.dao.domain.TransferType;
import org.slf4j.LoggerFactory;

/**
 * Dialog to confirm and process the move of a file to a new iRODS location
 * (phymove gesture)
 * 
 * @author mikeconway
 */
public class MoveOrCopyiRODSDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5429495810997374114L;
	private final iDrop idrop;
	private final IRODSTree stagingViewTree;
	private final IRODSOutlineModel irodsFileSystemModel;
	private final IRODSNode targetNode;
	private final String targetAbsolutePath;
	private final IRODSFile sourceFile;
	private final List<IRODSFile> sourceFiles;
	public static org.slf4j.Logger log = LoggerFactory
			.getLogger(MoveOrCopyiRODSDialog.class);
	private final boolean isCopy;

	public MoveOrCopyiRODSDialog(final iDrop parent, final boolean modal,
			final IRODSNode targetNode, final IRODSTree stagingViewTree,
			final IRODSFile sourceFile, final String targetAbsolutePath,
			final boolean isCopy) {
		super(parent, modal);
		idrop = parent;
		this.targetNode = targetNode;
		this.stagingViewTree = stagingViewTree;
		irodsFileSystemModel = (IRODSOutlineModel) stagingViewTree
				.getOutlineModel();
		this.targetAbsolutePath = targetAbsolutePath;
		this.sourceFile = sourceFile;
		sourceFiles = null;
		this.isCopy = isCopy;
		initializeDialog();
	}

	public MoveOrCopyiRODSDialog(final iDrop parent, final boolean modal,
			final IRODSNode targetNode, final IRODSTree stagingViewTree,
			final List<IRODSFile> sourceFiles, final String targetAbsolutePath,
			final boolean isCopy) {
		super(parent, modal);
		idrop = parent;
		this.targetNode = targetNode;
		this.stagingViewTree = stagingViewTree;
		irodsFileSystemModel = (IRODSOutlineModel) stagingViewTree.getModel();
		this.targetAbsolutePath = targetAbsolutePath;
		sourceFile = null;
		this.sourceFiles = sourceFiles;
		this.isCopy = isCopy;
		initializeDialog();
	}

	private void initializeDialog() {
		initComponents();
		if (isCopy) {
			lblTitle.setText("Do you wish to copy the iRODS file to the new iRODS location?");
		} else {
			lblTitle.setText("Do you wish to move the iRODS file to the new iRODS location?");
		}
		txtNewLocation.setText(targetAbsolutePath);

		if (sourceFile != null) {
			txtCurrentParent.setText(sourceFile.getAbsolutePath());
		} else {
			txtCurrentParent.setText("multiple files");
		}

		registerKeystrokeListener();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		lblTitle = new javax.swing.JLabel();
		pnlFolderData = new javax.swing.JPanel();
		pnlCurrentParent = new javax.swing.JPanel();
		lblCurrentParent = new java.awt.Label();
		lblNewDiretoryName = new java.awt.Label();
		scrollCurrentParent = new javax.swing.JScrollPane();
		txtCurrentParent = new javax.swing.JTextArea();
		scrollNewLocation = new javax.swing.JScrollPane();
		txtNewLocation = new javax.swing.JTextArea();
		pnlBottom = new javax.swing.JPanel();
		btnCancel = new javax.swing.JButton();
		btnOK = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("iRODS Move File - Confirmation Dialog");
		setName("NewParentDialog"); // NOI18N

		lblTitle.setText("Confirm the move of the iRODS File to a new collection");

		lblCurrentParent.setText("Current location:");

		lblNewDiretoryName.setText("New location:");

		txtCurrentParent.setColumns(20);
		txtCurrentParent.setEditable(false);
		txtCurrentParent.setRows(5);
		txtCurrentParent.setWrapStyleWord(true);
		txtCurrentParent.setFocusable(false);
		scrollCurrentParent.setViewportView(txtCurrentParent);

		txtNewLocation.setColumns(20);
		txtNewLocation.setEditable(false);
		txtNewLocation.setRows(5);
		txtNewLocation.setWrapStyleWord(true);
		txtNewLocation.setFocusable(false);
		scrollNewLocation.setViewportView(txtNewLocation);

		org.jdesktop.layout.GroupLayout pnlCurrentParentLayout = new org.jdesktop.layout.GroupLayout(
				pnlCurrentParent);
		pnlCurrentParent.setLayout(pnlCurrentParentLayout);
		pnlCurrentParentLayout
				.setHorizontalGroup(pnlCurrentParentLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(pnlCurrentParentLayout
								.createSequentialGroup()
								.add(44, 44, 44)
								.add(pnlCurrentParentLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(pnlCurrentParentLayout
												.createSequentialGroup()
												.add(20, 20, 20)
												.add(lblNewDiretoryName,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(
														org.jdesktop.layout.LayoutStyle.RELATED)
												.add(scrollNewLocation,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														531, Short.MAX_VALUE))
										.add(pnlCurrentParentLayout
												.createSequentialGroup()
												.add(lblCurrentParent,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(
														org.jdesktop.layout.LayoutStyle.RELATED)
												.add(scrollCurrentParent,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														531, Short.MAX_VALUE)))
								.addContainerGap()));
		pnlCurrentParentLayout
				.setVerticalGroup(pnlCurrentParentLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(pnlCurrentParentLayout
								.createSequentialGroup()
								.add(51, 51, 51)
								.add(pnlCurrentParentLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(scrollCurrentParent,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(lblCurrentParent,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED)
								.add(pnlCurrentParentLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(lblNewDiretoryName,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(scrollNewLocation,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(21, 21, 21)));

		lblNewDiretoryName.getAccessibleContext().setAccessibleName(
				"New directory name:");

		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});

		btnOK.setText("OK");
		btnOK.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(final java.awt.event.ActionEvent evt) {
				btnOKActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout pnlBottomLayout = new org.jdesktop.layout.GroupLayout(
				pnlBottom);
		pnlBottom.setLayout(pnlBottomLayout);
		pnlBottomLayout.setHorizontalGroup(pnlBottomLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				pnlBottomLayout
						.createSequentialGroup()
						.add(451, 451, 451)
						.add(btnCancel)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED)
						.add(btnOK).add(4, 4, 4)));
		pnlBottomLayout
				.setVerticalGroup(pnlBottomLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(pnlBottomLayout
								.createSequentialGroup()
								.add(5, 5, 5)
								.add(pnlBottomLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(btnCancel).add(btnOK))));

		org.jdesktop.layout.GroupLayout pnlFolderDataLayout = new org.jdesktop.layout.GroupLayout(
				pnlFolderData);
		pnlFolderData.setLayout(pnlFolderDataLayout);
		pnlFolderDataLayout
				.setHorizontalGroup(pnlFolderDataLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(pnlFolderDataLayout
								.createSequentialGroup()
								.add(pnlCurrentParent,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(61, Short.MAX_VALUE))
						.add(org.jdesktop.layout.GroupLayout.TRAILING,
								pnlFolderDataLayout
										.createSequentialGroup()
										.addContainerGap(134, Short.MAX_VALUE)
										.add(pnlBottom,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		pnlFolderDataLayout.setVerticalGroup(pnlFolderDataLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(pnlFolderDataLayout
						.createSequentialGroup()
						.add(pnlCurrentParent,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.UNRELATED)
						.add(pnlBottom,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(lblTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						622, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
				.add(pnlFolderData,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout
						.createSequentialGroup()
						.add(lblTitle)
						.add(pnlFolderData,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnCancelActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		dispose();
	}// GEN-LAST:event_btnCancelActionPerformed

	private void btnOKActionPerformed(final java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOKActionPerformed
		processMoveOrCopy();
	}// GEN-LAST:event_btnOKActionPerformed
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JButton btnCancel;

	private javax.swing.JButton btnOK;

	private java.awt.Label lblCurrentParent;

	private java.awt.Label lblNewDiretoryName;

	private javax.swing.JLabel lblTitle;

	private javax.swing.JPanel pnlBottom;

	private javax.swing.JPanel pnlCurrentParent;

	private javax.swing.JPanel pnlFolderData;

	private javax.swing.JScrollPane scrollCurrentParent;

	private javax.swing.JScrollPane scrollNewLocation;

	private javax.swing.JTextArea txtCurrentParent;

	private javax.swing.JTextArea txtNewLocation;

	// End of variables declaration//GEN-END:variables
    private void processMoveOrCopy() {
        // add the new folder to irods, add to the tree, and scroll the tree
        // into view
        final MoveOrCopyiRODSDialog thisDialog = this;
        log.info("processing move or copy");
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    log.info("procssing move of a file in iRODS tree");
                    thisDialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    DataTransferOperations dataTransferOperations;
                    try {
                        dataTransferOperations = idrop.getiDropCore().getIRODSAccessObjectFactory().getDataTransferOperations(
                                idrop.getIrodsAccount());
                    } catch (Exception e) {
                        idrop.getiDropCore().closeIRODSConnection(
                                idrop.getIrodsAccount());
                        thisDialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        throw new IdropRuntimeException(e);
                    }

                    List<IRODSFile> filesThatHadOverwriteError = new ArrayList<IRODSFile>();

                    if (sourceFile != null) {
                        log.info("processing the move/copy for one file:{}",
                                sourceFile);
                        try {
                            if (isCopy) {
                                processACopyOfAnIndividualFile(
                                        dataTransferOperations, sourceFile,
                                        targetAbsolutePath);
                            } else {
                                processAMoveOfAnIndividualFile(
                                        dataTransferOperations, sourceFile,
                                        targetAbsolutePath);
                            }
                        } catch (JargonFileOrCollAlreadyExistsException ex) {
                            Logger.getLogger(
                                    MoveOrCopyiRODSDialog.class.getName()).log(
                                    Level.SEVERE, null, ex);
                            filesThatHadOverwriteError.add(sourceFile);
                        } catch (JargonException je) {
                            if (je.getMessage().indexOf("-834000") > -1
                                    || je.getMessage().indexOf("-833000") > -1) {
                                filesThatHadOverwriteError.add(sourceFile);
                            } else {
                                throw new IdropException(je);
                            }
                        }
                    } else if (sourceFiles != null) {
                        log.info("processing move/copy of multiple files");
                        for (IRODSFile sourceFileEntry : sourceFiles) {
                            try {
                                if (isCopy) {
                                    processACopyOfAnIndividualFile(
                                            dataTransferOperations,
                                            sourceFileEntry, targetAbsolutePath);
                                } else {
                                    processAMoveOfAnIndividualFile(
                                            dataTransferOperations,
                                            sourceFileEntry, targetAbsolutePath);
                                }
                            } catch (JargonFileOrCollAlreadyExistsException ex) {
                                // FIXME: fix in jargon core to differentiate!
                                Logger.getLogger(
                                        MoveOrCopyiRODSDialog.class.getName()).log(Level.SEVERE, null, ex);
                                filesThatHadOverwriteError.add(sourceFile);
                            } catch (JargonException je) {
                                if (je.getMessage().indexOf("-834000") > -1
                                        || je.getMessage().indexOf("-833000") > -1) {
                                    filesThatHadOverwriteError.add(sourceFile);
                                } else {
                                    throw new IdropException(je);
                                }
                            }
                        }
                    }

                    log.debug("move done");
                    if (!isCopy) {
                        if (filesThatHadOverwriteError.isEmpty()) {
                            idrop.showMessageFromOperation("irods move processed");
                        } else {
                            idrop.showMessageFromOperation("irods move processed, some files were not moved as files of the same name already existed");
                        }
                    } else {
                        idrop.showMessageFromOperation("The file copy operation has been placed on the work queue");
                    }
                    thisDialog.dispose();

                } catch (IdropException ex) {
                    Logger.getLogger(IRODSTree.class.getName()).log(
                            Level.SEVERE, null, ex);
                    idrop.showIdropException(ex);
                    return;
                } finally {
                    thisDialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    idrop.getiDropCore().closeIRODSConnection(
                            idrop.getIrodsAccount());
                }
            }
        });
    }

    private void processAMoveOfAnIndividualFile(
            final DataTransferOperations dataTransferOperations,
            final IRODSFile sourceFile, final String targetAbsolutePath)
            throws JargonFileOrCollAlreadyExistsException, IdropException {

        try {
            
             boolean isFile = sourceFile.isFile();
            IRODSFile targetFile = idrop.getiDropCore().getIRODSFileFactoryForLoggedInAccount().instanceIRODSFile(targetAbsolutePath);
            
            dataTransferOperations.move(sourceFile.getAbsolutePath(),
                    targetAbsolutePath);

             String targetPathForNotify = null;
            if (isFile) {
                log.debug("source file is a file, do a move");

                if (targetFile.isDirectory()) {
                    targetPathForNotify = targetFile.getAbsolutePath() + "/"
                            + sourceFile.getName();
                } else {
                    targetPathForNotify = targetFile.getAbsolutePath();
                }

                irodsFileSystemModel.notifyFileShouldBeAdded(stagingViewTree,
                        targetPathForNotify);

            } else {
                log.debug("source file is a collection, reparent it");
                   targetPathForNotify = targetFile.getAbsolutePath() + "/"
                            + sourceFile.getName();

                irodsFileSystemModel.notifyFileShouldBeAdded(stagingViewTree,
                        targetPathForNotify);
            }
        } catch (JargonFileOrCollAlreadyExistsException fcae) {
            throw fcae;
        } catch (JargonException je) {
            throw new IdropException(je);
        }

        TreePath sourceNodePath = TreeUtils.buildTreePathForIrodsAbsolutePath(
                stagingViewTree, sourceFile.getAbsolutePath());
        if (sourceNodePath == null) {
            log.info("could not find tree path for source node, ignore");
            return;
        }
        IRODSNode sourceNode = (IRODSNode) sourceNodePath.getLastPathComponent();
        irodsFileSystemModel.notifyFileShouldBeRemoved(sourceNode);
    }

    private void processACopyOfAnIndividualFile(
            final DataTransferOperations dataTransferOperations,
            final IRODSFile sourceFile, final String targetAbsolutePath)
            throws IdropException {
        
           log.info("initiating put transfer");
                       try {
                            QueueManagerService qms = idrop.getiDropCore().getConveyorService().getQueueManagerService();
                            Transfer transfer = new Transfer();
                            transfer.setTransferType(TransferType.COPY);
                            transfer.setIrodsAbsolutePath(sourceFile.getAbsolutePath());
                            transfer.setLocalAbsolutePath(targetAbsolutePath);
                            qms.enqueueTransferOperation(transfer, idrop.getIrodsAccount());
                        } catch (ConveyorExecutionException ex) {
                            java.util.logging.Logger.getLogger(
                                    LocalFileTree.class.getName()).log(
                                    java.util.logging.Level.SEVERE, null, ex);
                            idrop.showIdropException(ex);
                        }
  
        // notifications are done at completion of transfer using status
        // callbacks
    }

    /**
     * Register a listener for the enter event, so login can occur.
     */
    private void registerKeystrokeListener() {

        KeyStroke enter = KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_ENTER, 0);
        Action enterAction = new AbstractAction() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                processMoveOrCopy();
            }
        };
        btnOK.registerKeyboardAction(enterAction, enter,
                JComponent.WHEN_IN_FOCUSED_WINDOW);

    }
}
