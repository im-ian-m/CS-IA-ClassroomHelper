package classroom.helper;

import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ianmoore
 */
public class ManagePartnerBlists extends javax.swing.JPanel {

    private ClassroomHelper app;
    private ClassS classS;
    private ClassHome homepage;
    private JPanel lastPage;
    private GUIFrame baseFrame;
    private String[][] data;
    private Student secondStuToUpdate;
    private boolean changesMade;
    
    public ManagePartnerBlists(ClassroomHelper appl, ClassS cla, ClassHome home, JPanel last, GUIFrame frame) {
        app = appl;
        classS = cla;
        homepage = home;
        lastPage = last;
        baseFrame = frame;
        baseFrame.setContentPane(this);
        initComponents();
        addStuDataToTable();
        addToParBlistB.setEnabled(false);
        remFromBlistB.setEnabled(false);
        saveValsB.setEnabled(false);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateButtonStates();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lastPageB = new javax.swing.JButton();
        saveValsB = new javax.swing.JButton();
        addToParBlistB = new javax.swing.JButton();
        remFromBlistB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Partner Blacklist"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        saveValsB.setText("Save");
        saveValsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveValsBActionPerformed(evt);
            }
        });

        addToParBlistB.setText("Add Student to Blacklist");
        addToParBlistB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToParBlistBActionPerformed(evt);
            }
        });

        remFromBlistB.setText("Remove Student from Blacklist");
        remFromBlistB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remFromBlistBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("Manage Student Partner Settings");

        jLabel2.setText("<HTML>Select a student in the list to add/remove other students to/from their partner blacklist and vice versa. Students will only be grouped with those in their blacklist as a last resort. All students in the current section are shown.</HTML>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addToParBlistB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remFromBlistB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveValsB)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lastPageB))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastPageB)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveValsB)
                    .addComponent(addToParBlistB)
                    .addComponent(remFromBlistB))
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    //updates the enabled/disabled states of the add/remove buttons based on whether there is anything to add/remove
    private void updateButtonStates() {
        if(jTable1.getValueAt(jTable1.getSelectedRow(), 1).equals("N/A")) {
            addToParBlistB.setEnabled(true);
            remFromBlistB.setEnabled(false);
        } else if(app.getCurSxn().getStus().get(jTable1.getSelectedRow()).getParBlist().size() == app.getCurSxn().getStus().size() - 1) {
            addToParBlistB.setEnabled(false);
            remFromBlistB.setEnabled(true);
        } else {
            addToParBlistB.setEnabled(true);
            remFromBlistB.setEnabled(true);
        }
    }
    
    //triggers a popup to request input for the name of a student
    private void addToParBlistBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToParBlistBActionPerformed
        if(jTable1.getSelectedRow() != -1) {
            new EditParBlistPopup(app, classS, baseFrame, true, this, true, jTable1.getSelectedRow()).setVisible(true);
        }
        if(secondStuToUpdate != null) {
            updateStuPBlistTableData(jTable1.getSelectedRow());
            for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
                if(app.getCurSxn().getStus().get(i) == secondStuToUpdate) {
                    updateStuPBlistTableData(i);
                    break;
                }
            }
            
            secondStuToUpdate = null;
            changesMade = true;
            saveValsB.setEnabled(true);
        }
        updateButtonStates();
    }//GEN-LAST:event_addToParBlistBActionPerformed

    //triggers a popup to request input for the name of a student
    private void remFromBlistBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remFromBlistBActionPerformed
        if(jTable1.getSelectedRow() != -1) {
            new EditParBlistPopup(app, classS, baseFrame, true, this, false, jTable1.getSelectedRow()).setVisible(true);
        }
        if(secondStuToUpdate != null) {
            updateStuPBlistTableData(jTable1.getSelectedRow());
            for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
                if(app.getCurSxn().getStus().get(i) == secondStuToUpdate) {
                    updateStuPBlistTableData(i);
                    break;
                }
            }

            secondStuToUpdate = null;
            changesMade = true;
            saveValsB.setEnabled(true);
        }
        updateButtonStates();
    }//GEN-LAST:event_remFromBlistBActionPerformed

    //reverts changes and returns to previous page
    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        if(changesMade) {
            for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
                app.getCurSxn().getStus().get(i).setParBlistToEmpty();
            }
            //the basic versions of the partner blacklists were never updated so they can be used to retrieve the original values
            app.getCurSxn().setPBlists();
        }
        
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_lastPageBActionPerformed

    //saves changes and returns to previous page
    private void saveValsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveValsBActionPerformed
        for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
            app.getCurSxn().getStus().get(i).updateBPBlist();
        }
        app.getCurSxn().updateStuData();
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_saveValsBActionPerformed
    
    //updates the data of a specific row to show the most recent data
    private void updateStuPBlistTableData(int row) {
        LinkedList<Student> stus = app.getCurSxn().getStus();
        
        data[row][1] = "";
        for(int i = 0; i < stus.get(row).getParBlist().size(); i++) {
            data[row][1] += stus.get(row).getParBlist().get(i).getFuName();
            if(i != stus.get(row).getParBlist().size() - 1) {
                data[row][1] += ", ";
            }
        }
        if(data[row][1].equals("")) {
            data[row][1] = "N/A";
        }
        jTable1.getModel().setValueAt(data[row][1], row, 1);
    }
    
    public void set2ndStuToUpdate(Student stu) {
        secondStuToUpdate = stu;
    }
    
    //adds the data to the data table
    private void addStuDataToTable() {
        data = new String[app.getCurSxn().getStus().size()][2];
        for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
            data[i][0] = app.getCurSxn().getStus().get(i).getFuName();
            if(app.getCurSxn().getStus().get(i).getParBlist().size() == 0) {
                data[i][1] = "N/A";
            } else {
                data[i][1] = "";
            }
            for(int j = 0; j < app.getCurSxn().getStus().get(i).getParBlist().size(); j++) {
                data[i][1] += app.getCurSxn().getStus().get(i).getParBlist().get(j).getFuName();
                if(j != app.getCurSxn().getStus().get(i).getParBlist().size() - 1) {
                    data[i][1] += ", ";
                }
            }
            
            ((DefaultTableModel) jTable1.getModel()).addRow(data[i]);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToParBlistB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton remFromBlistB;
    private javax.swing.JButton saveValsB;
    // End of variables declaration//GEN-END:variables
}
