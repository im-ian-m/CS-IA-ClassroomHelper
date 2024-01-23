/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom.helper;

import static classroom.helper.ClassroomHelper.getIndex;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ianmoore
 */
public class ManageSxnData extends javax.swing.JPanel {

    private ClassroomHelper app;
    private JPanel lastPage;
    private GUIFrame baseFrame;
    
    public ManageSxnData(ClassroomHelper appl, JPanel last, GUIFrame frame) {
        app = appl;
        lastPage = last;
        baseFrame = frame;
        baseFrame.setContentPane(this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editSxnB = new javax.swing.JButton();
        createSxnB = new javax.swing.JButton();
        remSxnB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        editSxnB.setText("Edit a Section");
        editSxnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSxnBActionPerformed(evt);
            }
        });

        createSxnB.setText("Create a Section");
        createSxnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSxnBActionPerformed(evt);
            }
        });

        remSxnB.setText("Remove a Section");
        remSxnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remSxnBActionPerformed(evt);
            }
        });

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("Manage Section Data Homepage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(308, 308, 308)
                                .addComponent(editSxnB))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(remSxnB)
                                    .addComponent(createSxnB))))
                        .addGap(0, 340, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lastPageB)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastPageB)
                    .addComponent(jLabel1))
                .addGap(114, 114, 114)
                .addComponent(editSxnB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createSxnB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remSxnB)
                .addContainerGap(178, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editSxnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSxnBActionPerformed
        String sxnFPath;
        int index;
        String sxnStr = (String) javax.swing.JOptionPane.showInputDialog(null, "Choose a section: ", "Edit Section - Section Selection Popup", JOptionPane.PLAIN_MESSAGE, null, app.getSxnNames().toArray(), app.getSxnNames().get(0));
        if(sxnStr != null) {
            System.out.println("SxnStr: " + sxnStr);
            for(int i = 0; i < app.getSxnNames().size(); i++) {
                System.out.println("Section Name: " + app.getSxnNames().get(i));
                if(sxnStr.equals(app.getSxnNames().get(i))) {
                    if(app.getSxns().size() == 0) {
                        sxnFPath = app.getSxnPaths().get(i);
                        app.loadStus(sxnFPath);
                        app.getSxns().add(new Section(app.getSxnMems(), Integer.parseInt(sxnStr.substring(0, 2)), Integer.parseInt(sxnStr.substring(3, 5)), sxnFPath, app.getAllPros()));
                        app.setCurSxn(app.getSxns().get(0));
                        app.setCurSxnNum(0);
                        break;
                    }
                    for(int j = 0; j < app.getSxns().size(); j++) {
                        System.out.println("sxn in sxns: " + app.getSxns().get(j).getName() + " v.s. sxn chosen: " + app.getSxnNames().get(i));
                        if(app.getSxns().get(j).getName().equals(app.getSxnNames().get(i))) {
                            app.setSxnMems(app.getSxns().get(j).getStus());
                            app.setCurSxn(app.getSxns().get(j));
                            app.setCurSxnNum(j);
                            break;
                        } else if(j == app.getSxns().size() - 1) {
                            //this section doesn't exist yet
                            sxnFPath = app.getSxnPaths().get(i);
                            app.loadStus(sxnFPath);
                            index = getIndex(app.getSxns(), sxnStr, 0, app.getSxns().size() - 1);
                            //if this sxns section object hasn't already been created
                            
                            //System.out.println("Name of section found in Sections: " + app.getSxns().get(index).getName());
                            //System.out.println("Name of new section: " + sxnStr);
                            //if(app.getSxns().get(index).getStus() != app.getSxnMems()) {
                                app.getSxns().add(index, new Section(app.getSxnMems(), Integer.parseInt(sxnStr.substring(0, 2)), Integer.parseInt(sxnStr.substring(3, 5)), sxnFPath, app.getAllPros()));
                                app.setCurSxn(app.getSxns().get(index));
                                app.setCurSxnNum(index);
                                break;
                            //}
                        }
                    }
                    break;
                }
            }
            System.out.println(app.getCurSxn().getStus().toString());
            new ManageStuData(app, this, baseFrame, false);
        }
    }//GEN-LAST:event_editSxnBActionPerformed

    private void createSxnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSxnBActionPerformed
        new ManageStuData(app, this, baseFrame, true);
    }//GEN-LAST:event_createSxnBActionPerformed

    private void remSxnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remSxnBActionPerformed
        File stuF = null, proF = null, dir = null;
        String sxnStr = (String) javax.swing.JOptionPane.showInputDialog(null, "Choose a section to delete: ", "Delete Section - Section Selection Popup", JOptionPane.PLAIN_MESSAGE, null, app.getSxnNames().toArray(), app.getSxnNames().get(0));
        if(sxnStr != null) {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete this section?" , "Delete Section - Confirmation", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                for(int i = 0; i < app.getSxnNames().size(); i++) {
                    if(app.getSxnNames().get(i).equals(sxnStr)) {
                        for(int j = 0; j < app.getSxns().size(); j++) {
                            if(app.getSxns().get(j).getName().equals(sxnStr)) {
                                stuF = app.getSxns().get(j).getStuF();
                                proF = app.getSxns().get(j).getProF();
                                if(app.getSxns().get(j) == app.getCurSxn()) {
                                    app.setCurSxn(null);
                                    app.setSxnMems(null);
                                }
                                app.getSxns().remove(j);
                                break;
                            }
                        }
                        if(stuF == null && proF == null) {
                            System.out.println("Sxn Path: " + app.getSxnPaths().get(i));
                            stuF = new File(app.getSxnPaths().get(i) + "/Students.tsv");
                            proF = new File(app.getSxnPaths().get(i) + "/Prompts.tsv");
                        }
                        stuF.delete();
                        proF.delete();
                        dir = new File(app.getSxnPaths().get(i));
                        dir.delete();
                        //System.out.println("test: " + app.getSxnPaths().get(i).substring(0, app.getSxnPaths().get(i).length() - 2));
                        dir = new File(app.getSxnPaths().get(i).substring(0, app.getSxnPaths().get(i).length() - 2));
                        dir.delete();
                        app.getSxnPaths().remove(i);
                        app.getSxnNames().remove(i);
                        break;
                    }
                }
            }
        }
    }//GEN-LAST:event_remSxnBActionPerformed

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_lastPageBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createSxnB;
    private javax.swing.JButton editSxnB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton remSxnB;
    // End of variables declaration//GEN-END:variables
}