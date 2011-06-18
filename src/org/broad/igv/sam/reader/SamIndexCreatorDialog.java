/*
 * Copyright (c) 2007-2011 by The Broad Institute of MIT and Harvard.  All Rights Reserved.
 *
 * This software is licensed under the terms of the GNU Lesser General Public License (LGPL),
 * Version 2.1 which is available at http://www.opensource.org/licenses/lgpl-2.1.php.
 *
 * THE SOFTWARE IS PROVIDED "AS IS." THE BROAD AND MIT MAKE NO REPRESENTATIONS OR
 * WARRANTES OF ANY KIND CONCERNING THE SOFTWARE, EXPRESS OR IMPLIED, INCLUDING,
 * WITHOUT LIMITATION, WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, NONINFRINGEMENT, OR THE ABSENCE OF LATENT OR OTHER DEFECTS, WHETHER
 * OR NOT DISCOVERABLE.  IN NO EVENT SHALL THE BROAD OR MIT, OR THEIR RESPECTIVE
 * TRUSTEES, DIRECTORS, OFFICERS, EMPLOYEES, AND AFFILIATES BE LIABLE FOR ANY DAMAGES
 * OF ANY KIND, INCLUDING, WITHOUT LIMITATION, INCIDENTAL OR CONSEQUENTIAL DAMAGES,
 * ECONOMIC DAMAGES OR INJURY TO PROPERTY AND LOST PROFITS, REGARDLESS OF WHETHER
 * THE BROAD OR MIT SHALL BE ADVISED, SHALL HAVE OTHER REASON TO KNOW, OR IN FACT
 * SHALL KNOW OF THE POSSIBILITY OF THE FOREGOING.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SamIndexCreatorDialog.java
 *
 * Created on Feb 11, 2009, 1:45:19 PM
 */
package org.broad.igv.sam.reader;

import org.broad.igv.ui.util.MessageUtils;
import org.broad.igv.ui.util.UIUtilities;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * @author jrobinso
 */
public class SamIndexCreatorDialog extends javax.swing.JDialog {

    File samFile;
    File idxFile;
    IndexWorker worker;

    /**
     * Creates new form SamIndexCreatorDialog
     */
    public SamIndexCreatorDialog(java.awt.Frame parent, boolean modal,
                                 File samFile,
                                 File idxFile) {
        super(parent, modal);
        initComponents();

        this.samFile = samFile;
        this.idxFile = idxFile;
        int timeEst = 1 + (int) Math.ceil(samFile.length() / 1000000000.0);
        String txt = introText.replace("@filename", samFile.getName()).replace(
                "@time", String.valueOf(timeEst));
        this.introTextPane.setText(txt);

        this.introTextPane.setBorder(BorderFactory.createEmptyBorder());

        worker = new IndexWorker();
    }

    public FeatureIndex getIndex() {
        if (worker == null || !worker.isDone()) {
            return null;
        } else {
            try {
                return worker.get();
            } catch (Exception ex) {
                MessageUtils.showMessage(ex.getMessage());
            }
            return null;
        }
    }

    public class IndexWorker extends SwingWorker<FeatureIndex, Void> {

        @Override
        protected FeatureIndex doInBackground() throws Exception {
            AlignmentIndexer indexer = AlignmentIndexer.getInstance(samFile, progressBar, this);
            return indexer.createSamIndex(idxFile, 16000);
        }

        @Override
        protected void done() {
            setVisible(false);
        }

        public void setTimeRemaining(long timeInMillis) {
            final int timeRemaining = (int) (timeInMillis / (60 * 1000));
            UIUtilities.invokeOnEventThread(new Runnable() {

                public void run() {
                    String txt = String.valueOf(timeRemaining) + " minutes";
                    if (timeRemaining == 1) {
                        txt = "1 minute";
                    } else if (timeRemaining < 1) {
                        txt = " < 1 minute";
                    }
                    timeRemainingLabel.setText(txt);
                }
            });

        }
    }

    /**
     * ProgressListener listens to "progress" property
     * changes in the SwingWorkers that search and load
     * images.
     */
    class ProgressListener implements PropertyChangeListener {
        // prevent creation without providing a progress bar
        private ProgressListener() {
        }

        ProgressListener(JProgressBar progressBar) {
            this.progressBar = progressBar;
            this.progressBar.setValue(0);
        }

        public void propertyChange(PropertyChangeEvent evt) {
            String strPropertyName = evt.getPropertyName();
            if ("progress".equals(strPropertyName)) {
                progressBar.setIndeterminate(false);
                int progress = (Integer) evt.getNewValue();
                progressBar.setValue(progress);
            }
        }

        private JProgressBar progressBar;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        timeRemainingLabel = new javax.swing.JLabel();
        goButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        introTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Estimated time remaining: ");

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        introTextPane.setBackground(getParent().getBackground());
        introTextPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        introTextPane.setEditable(false);
        introTextPane.setText("An index file for [filename goes here] could not be located.  An index is required to view alignments in IGV.  Click \"Go\" to create one now.  This will take approximately [time goes here] to complete.");
        introTextPane.setFocusable(false);
        jScrollPane2.setViewportView(introTextPane);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 343, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(layout.createSequentialGroup()
                                        .add(jLabel1)
                                        .add(18, 18, 18)
                                        .add(timeRemainingLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(layout.createSequentialGroup()
                                        .add(cancelButton)
                                        .add(18, 18, 18)
                                        .add(goButton))
                                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 351, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(35, 35, 35)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(timeRemainingLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(goButton)
                                .add(cancelButton))
                        .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed

        if (worker.isDone() || worker.isCancelled()) {
            setVisible(false);
        } else {
            goButton.setEnabled(false);
            worker.execute();
        }

    }//GEN-LAST:event_goButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if (worker != null) {
            worker.cancel(true);
        }
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                File samFile = new File("/Users/jrobinso/IGV/Sam/30DWM.7.sam");
                File idxFile = new File("/Users/jrobinso/IGV/Sam/30DWM.7.sai");
                SamIndexCreatorDialog dialog = new SamIndexCreatorDialog(
                        new javax.swing.JFrame(), true,
                        samFile, idxFile);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton goButton;
    private javax.swing.JTextPane introTextPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel timeRemainingLabel;
    // End of variables declaration//GEN-END:variables
    static String introText = "An index file for @filename could not " +
            "be located. An index is required to view alignments in IGV.  " +
            "Click \"Go\" to create one now.";
}
