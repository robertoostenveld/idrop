/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UploadFromURLDialog.java
 *
 * Created on Jan 6, 2012, 5:07:00 PM
 */

package org.irods.jargon.idrop.lite;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.slf4j.LoggerFactory;

/**
 *
 * @author lisa
 */
public class UploadFromURLDialog extends javax.swing.JDialog implements TableModelListener {
	
	iDropLiteApplet idropApplet = null;
	ImageIcon plusIcon;
	ImageIcon minusIcon;
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(iDropLiteApplet.class);

    /** Creates new form UploadFromURLDialog */
    public UploadFromURLDialog(final iDropLiteApplet parent, final boolean modal) {
    	
    	super(parent.getiDropCore().findAppletParentFrame(parent), modal);
        initComponents();
        Border empty_border = BorderFactory.createEmptyBorder (0,10,0,10);
        jPanel1.setBorder(empty_border);
        idropApplet = parent;
        
        getIcons();
        
        setupURL_Table();
    }
    
    private void setupURL_Table() {
        tblUploadURLS.setFillsViewportHeight(true);
        tblUploadURLS.setBackground(jPanel3.getBackground());
        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        jScrollPane1.setBackground(jPanel3.getBackground());
        jPanel3.setBorder(BorderFactory.createEmptyBorder (0,4,0,4));
        tblUploadURLS.getColumnModel().getColumn(0).setPreferredWidth(300);
        tblUploadURLS.getColumnModel().getColumn(1).setPreferredWidth(2);
        tblUploadURLS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblUploadURLS.setRowSelectionAllowed(false);
        tblUploadURLS.setColumnSelectionAllowed(false);
        // save edits to cells in table when focus goes to another component
        tblUploadURLS.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        // add renderer for URL in first column
        tblUploadURLS.getColumnModel().getColumn(0).setCellRenderer(new UploadToURLTableURLRenderer());
        tblUploadURLS.getColumnModel().getColumn(0).setCellEditor(new UploadToURLTableURLCellEditor());
        // add renderer for cancel icon
        tblUploadURLS.getColumnModel().getColumn(1).setCellRenderer(new UploadFromURLTablePlusMinusRenderer(plusIcon, minusIcon));
        
        tblUploadURLS.getModel().addTableModelListener(this);
    }
    
    private void getIcons() {
    	// load table cancel icon
        java.net.URL imgPlus = getClass().getResource("/plus.gif");
        java.net.URL imgMinus = getClass().getResource("/minus.gif");
    	
        if (imgPlus != null) {
            plusIcon = new ImageIcon(imgPlus, "image used to denote addition of another URL entry text field");
        } else {
            log.error("cannot find image: plus.gif for Upload From URL Table");
        }
        if (imgMinus != null) {
            minusIcon = new ImageIcon(imgMinus, "image used to denote removal of an URL entry text field");
        } else {
            log.error("cannot find image: minus.gif for Upload From URL Table");
        }
    	
    }
    
    private void highliteRow(int row) {
    	String badURL = (String)tblUploadURLS.getModel().getValueAt(row, 0);
    	tblUploadURLS.requestFocusInWindow();
    	tblUploadURLS.editCellAt(row, 0);
    	JTextField badTextField = (JTextField)tblUploadURLS.getCellEditor().getTableCellEditorComponent(tblUploadURLS, badURL, Boolean.FALSE, row, 0);
    }
    
    @Override
	public void tableChanged(TableModelEvent tme) {
    	Boolean value = null;
    	int type = tme.getType();
        int row = tme.getFirstRow();
        int column = tme.getColumn();
        DefaultTableModel tm = (DefaultTableModel) tblUploadURLS.getModel();

        int rowcount = tm.getRowCount();
        
        if (type == TableModelEvent.UPDATE && column == 1) {
            if(tm.getValueAt(row, column) instanceof Boolean) {
            	value = (Boolean)tm.getValueAt(row, column);
            }
            if(value && (rowcount > 1)) {
            	// clicked on a minus - delete this row
            	tm.removeRow(row);
            	tm.fireTableRowsDeleted(row, row);
            }
            else {
            	// clicked on a plus - create a new row
            	Object [] rowData = new Object[2];
                rowData[0] = "Insert URL";
             	rowData[1] = Boolean.TRUE;
                tm.addRow(rowData);
                tm.fireTableRowsInserted(0, 0);
            }
        }
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUploadURLS = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnUploadDFromURL_Add = new javax.swing.JButton();
        btnUploadFromURL_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(UploadFromURLDialog.class, "UploadFromURLDialog.title")); // NOI18N
        setSize(new java.awt.Dimension(495, 125));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 6, 0, 0));
        jPanel2.setMinimumSize(new java.awt.Dimension(239, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(495, 40));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setText(org.openide.util.NbBundle.getMessage(UploadFromURLDialog.class, "UploadFromURLDialog.jLabel1.text")); // NOI18N
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 6, 4, 6));
        jPanel3.setPreferredSize(new java.awt.Dimension(495, 40));
        jPanel3.setLayout(new java.awt.BorderLayout());

        tblUploadURLS.setModel(new javax.swing.table.DefaultTableModel(
        	new Object [][] {
        		{"Insert URL", Boolean.TRUE}
        	},
        	new String [] {
        		"", ""
        	}
        ) {
        	Class[] types = new Class [] {
        		java.lang.Object.class, java.lang.Boolean.class
        	};

        	public Class getColumnClass(int columnIndex) {
        		return types [columnIndex];
        	}
        });

        tblUploadURLS.setIntercellSpacing(new java.awt.Dimension(1, 10));
        tblUploadURLS.setRowHeight(40);
        tblUploadURLS.setShowGrid(false);
        jScrollPane1.setViewportView(tblUploadURLS);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 2, 4, 2));
        jPanel4.setPreferredSize(new java.awt.Dimension(495, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(200, 30));

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 200, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 30, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.WEST);

        jPanel6.setPreferredSize(new java.awt.Dimension(155, 100));
        jPanel6.setLayout(new java.awt.BorderLayout());

        btnUploadDFromURL_Add.setFont(new java.awt.Font("Lucida Grande", 0, 12));
        btnUploadDFromURL_Add.setText(org.openide.util.NbBundle.getMessage(UploadFromURLDialog.class, "UploadFromURLDialog.btnUploadDFromURL_Add.text")); // NOI18N
        btnUploadDFromURL_Add.setSize(new java.awt.Dimension(80, 29));
        btnUploadDFromURL_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadDFromURL_AddActionPerformed(evt);
            }
        });
        jPanel6.add(btnUploadDFromURL_Add, java.awt.BorderLayout.WEST);

        btnUploadFromURL_Cancel.setFont(new java.awt.Font("Lucida Grande", 0, 12));
        btnUploadFromURL_Cancel.setText(org.openide.util.NbBundle.getMessage(UploadFromURLDialog.class, "UploadFromURLDialog.btnUploadFromURL_Cancel.text")); // NOI18N
        btnUploadFromURL_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadFromURL_CancelActionPerformed(evt);
            }
        });
        jPanel6.add(btnUploadFromURL_Cancel, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadFromURL_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadFromURL_CancelActionPerformed
    	this.dispose();
    }//GEN-LAST:event_btnUploadFromURL_CancelActionPerformed

    private void btnUploadDFromURL_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadDFromURL_AddActionPerformed
        URL url = null;
        String urlName = null;
        HttpURLConnection connection = null;
        int httpCode;
        int fileSize = 0;
        DefaultTableModel tm = (DefaultTableModel) tblUploadURLS.getModel();
        int rowcount = tm.getRowCount();
        DefaultTableModel tblModel = idropApplet.getiDropCore().getUploadTableModel();
        
        // first go through table and make sure all entries are valid
        for (int row=0; row<rowcount; row++) {
        	
        	urlName = (String)tm.getValueAt(row, 0);
        	
        	if(urlName != null) {
            	try {
            		url = new URL(urlName);
            		connection = (HttpURLConnection) url.openConnection();
            		connection.setRequestMethod ("GET");
            		connection.connect (); 
                	httpCode = connection.getResponseCode();
            		fileSize = connection.getContentLength();
            		
            	} catch (MalformedURLException e) {
            		idropApplet.showMessageFromOperation("Please enter a valid URL");
            		highliteRow(row);
            		return;
            	} catch (IOException e) {
            		idropApplet.showMessageFromOperation("Please enter a valid URL");
            		highliteRow(row);
            		return;
            	} catch (NullPointerException e) {
            		idropApplet.showMessageFromOperation("Please enter a valid URL");
            		highliteRow(row);
            		return;
            	}
    
            	if(httpCode != HttpURLConnection.HTTP_OK) {
            		idropApplet.showMessageFromOperation("Please enter a valid URL file for download");
            		highliteRow(row);
            		return;
            	}
            	if(fileSize < 0)  {
            		idropApplet.showMessageFromOperation("Please enter a valid URL file for download - file size for " +
            				urlName + " is less than 0");
            		highliteRow(row);
            		return;
            	}
        	}
        }
            	
        // now actually process them
        for (int row=0; row<rowcount; row++) {
                	
        	urlName = (String)tm.getValueAt(row, 0);
    
            Object [] rowData = new Object[5];
            rowData[0] = urlName;
            rowData[1] = 0;
            rowData[2] = new TransferProgressInfo();
            rowData[3] = Boolean.TRUE;
            rowData[4] = iDropLiteApplet.uploadURL;  // treat this as a file
            tblModel.addRow(rowData);
            	
        }
        
        this.dispose();
    }//GEN-LAST:event_btnUploadDFromURL_AddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUploadDFromURL_Add;
    private javax.swing.JButton btnUploadFromURL_Cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUploadURLS;
    // End of variables declaration//GEN-END:variables

}
