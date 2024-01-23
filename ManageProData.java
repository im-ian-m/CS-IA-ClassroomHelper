package classroom.helper;

import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ianmoore
 */
public class ManageProData extends javax.swing.JPanel {
    private ClassroomHelper app;
    private JPanel lastPage;
    private GUIFrame baseFrame;
    private String[][] data;
    private int[] lastSel = new int[2];
    private boolean rowAdded = false;
    private EventObject editCellEvent;
    private Object monitorObject = new Object();
    private KeyAdapter editorKeyLi;
    private boolean valEdited;
    private boolean invalRqst = false;
    private boolean createNewSxn;
    private int lastOldPro;
    private ArrayList<ArrayList<String>> prosRemoved;
    private boolean setupApp;
    
    public ManageProData(ClassroomHelper hlpr, JPanel last, GUIFrame frame, boolean startUp) {
        baseFrame = frame;
        app = hlpr;
        lastPage = last;
        setupApp = startUp;
        baseFrame.setContentPane(this);
        initComponents();
        remRowB.setEnabled(false);
        addProDataToJTable();
        lastSel[0] = -1;
        valEdited = false;
        saveValsB.setEnabled(false);
        jLabel1.setVisible(false);
        if(setupApp) {
            lastPageB.setVisible(false);
            lastPageB.setEnabled(false);
        }
        jTable1.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTable1ValueChanged();
            }
        });
        
        jTable1.getModel().addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    if(jTable1.getModel().getValueAt(lastSel[0], 0).equals("")) {
                        jLabel1.setVisible(true);
                        //our last selection was at the same location as editCellEvent was triggered when we are adding a new row
                        if(!(editCellEvent.getSource().toString().equals(Integer.toString(lastSel[0])))) {
                            invalRqst = true;
                            editCellEvent = new EventObject(lastSel[0]);
                            jTable1.editCellAt(lastSel[0], 0, editCellEvent);
                        }
                    } else {
                        rowAdded = false;
                        valEdited = true;
                        invalRqst = false;
                        jLabel1.setVisible(false);
                        data[lastSel[0]][0] = (String) jTable1.getModel().getValueAt(lastSel[0], 0);
                        data[lastSel[0]][1] = (String) jTable1.getModel().getValueAt(lastSel[0], 1);
                        data[lastSel[0]][2] = (String) jTable1.getModel().getValueAt(lastSel[0], 2);
                    }
                }
                
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addRowB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        remRowB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();
        saveValsB = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Invalid input - please enter a value");
        jLabel1.setMixingCutoutShape(null);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel2.setText("Data Manager - Manage Prompts");

        addRowB.setText("Add prompt");
        addRowB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowBActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prompt", "Level", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(600);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        remRowB.setText("Remove prompt");
        remRowB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remRowBActionPerformed(evt);
            }
        });

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

        jLabel3.setText("<HTML>Double click on a cell in the \"Prompt\" column to edit it. Select a row and click once in the \"Level\" or \"Type\" column to change the selected prompt's level or type respectively or press \"Remove prompt\" to delete it.</HTML>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lastPageB)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                .addComponent(addRowB)
                                .addGap(18, 18, 18)
                                .addComponent(remRowB)
                                .addGap(18, 18, 18)
                                .addComponent(saveValsB)))
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(lastPageB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addRowB)
                        .addComponent(remRowB)
                        .addComponent(saveValsB)))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    //updates the prompt data for all sections
    public void updateProData() {
        String filePath;
        File f;
        int hasSxn;
        FileWriter newFile;
        boolean done;
        int prosRemd;
        Scanner scan = new Scanner("");
        String newData, answered;
        for(int i = 0; i < app.getSxnPaths().size(); i++) {
            hasSxn = -1;
            prosRemd = 0;
            filePath = app.getSxnPaths().get(i) + "/Prompts.tsv";
            newData = "PromptNo isAnswered\n";
            f = new File(filePath);
            
            try {
                scan = new Scanner(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int l = 0; l < app.getSxns().size(); l++) {
                if(app.getSxns().get(l).getName().equals(app.getSxnNames().get(i))) {
                    hasSxn = l;
                }
            }
            
            scan.nextLine();
            
            
            for(int j = 0; j < app.getAllPros().size(); j++) {
                done = false;
                if(prosRemoved != null) {
                    for(int k = 0; k < prosRemoved.size(); k++) {
                        if(app.getAllPros().get(j).getQue().equals(prosRemoved.get(k).get(0)) && (app.getAllPros().get(j).getIsHL() ? "HL" : "SL" ).equals(prosRemoved.get(k).get(1)) && app.getAllPros().get(j).getType().equals(prosRemoved.get(k).get(2))) {
                            prosRemd++;
                            scan.nextLine();
                            if(hasSxn > -1) {
                                app.getSxns().get(hasSxn).getPros().remove(j);
                            }
                            done = true;
                            break;
                        }
                    }
                }
                if(!done) {
                    if(scan.hasNextLine()) {
                        answered = scan.next();
                    } else {
                        answered = "false";
                    }
                    if(hasSxn > -1) {
                        app.getSxns().get(hasSxn).getPros().get(j - prosRemd).setId(j - prosRemd);
                    }
                    newData += app.getAllPros().get(j - prosRemd).getId() + " " + answered + "\n";
                }
            }
            
            for(int j = setupApp ? 0 : lastOldPro + 1; j < data.length; j++) {
                newData += j + " false\n";
            }
            
            newData = newData.substring(0, newData.length() - 1);
            
            try {
                newFile = new FileWriter(filePath);
                newFile.write(newData);
                newFile.close();
            } catch (IOException ex) {
                Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
    
    //adds a row to the table
    private void addRowBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowBActionPerformed
        String[][] dataCopy = new String[data.length + 1][3];
        boolean success;
        
        rowAdded = true;
        valEdited = true;
        
        for(int i = 0; i < data.length; i++) {
            dataCopy[i][0] = data[i][0];
            dataCopy[i][1] = data[i][1];
            dataCopy[i][2] = data[i][2];
        }
        data = dataCopy;
        data[data.length - 1][0] = "";
        data[data.length - 1][1] = "SL";
        data[data.length - 1][2] = "SAQ";

        ((DefaultTableModel) jTable1.getModel()).addRow(data[data.length - 1]);
        rowAdded = true;
        jTable1.changeSelection(data.length - 1, 0, false, false);
        jTable1.editCellAt(data.length - 1, 0);
    }//GEN-LAST:event_addRowBActionPerformed

    //handles table input in the cell editor
    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        if(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).equals("")) {
            invalRqst = true;
            jLabel1.setVisible(true);
        } else {
            invalRqst = false;
            jLabel1.setVisible(false);
            jLabel1.setText("Invalid input - please enter a value");
        }
        if(!((String) jTable1.getModel().getValueAt(jTable1.rowAtPoint(evt.getPoint()), 0)).equals("")) {
            if(lastSel[1] == lastSel[0] && jTable1.isEditing() == false) {
                if(jTable1.columnAtPoint(evt.getPoint()) == 1) {
                    if(jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), jTable1.columnAtPoint(evt.getPoint())).equals("SL")) {
                        jTable1.getModel().setValueAt("HL", jTable1.rowAtPoint(evt.getPoint()), 1);
                        if(jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 2).equals("SAQ")) {
                            jTable1.getModel().setValueAt("ERQ", jTable1.rowAtPoint(evt.getPoint()), 2);
                        }
                    } else {
                        jTable1.getModel().setValueAt("SL", jTable1.rowAtPoint(evt.getPoint()), jTable1.columnAtPoint(evt.getPoint()));
                    }
                    valEdited = true;
                    saveValsB.setEnabled(true);
                } else if(jTable1.columnAtPoint(evt.getPoint()) == 2) {
                    if(jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 2).equals("SAQ")) {
                        if(jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 1).equals("SL")) {
                            jTable1.getModel().setValueAt("ERQ", jTable1.rowAtPoint(evt.getPoint()), 2);
                            valEdited = true;
                            saveValsB.setEnabled(true);
                        }
                    } else {
                        if(jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 1).equals("SL")) {
                            jTable1.getModel().setValueAt("SAQ", jTable1.rowAtPoint(evt.getPoint()), 2);
                            valEdited = true;
                            saveValsB.setEnabled(true);
                        } else {
                            jLabel1.setText("<html>HL prompts cannot be SAQs. Please change </br>the type to ERQ and try again.<html>");
                            jLabel1.setVisible(true);
                        }
                    }
                }
            }
        }  
    }//GEN-LAST:event_jTable1MousePressed

    //sets button states depending on whether editing is occuring
    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        if(evt.getPropertyName().equals("tableCellEditor")) {
            if(jTable1.isEditing()) {
                addRowB.setEnabled(false);
                saveValsB.setEnabled(false);
                lastPageB.setEnabled(false);
                remRowB.setEnabled(false);
                jTable1.getEditorComponent().addKeyListener(editorKeyLi = new java.awt.event.KeyAdapter() {
                    //prevents the tab character from being entered in the process of adding a row and breaking the user out of the input validation loop
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent ev) {
                        if(ev.getKeyChar() == Character.toChars(27)[0]) {
                            if(jTable1.isEditing()) {
                                if(invalRqst || rowAdded) {
                                    ev.consume();
                                }
                            }
                        }
                    }
                });
            } else {
                addRowB.setEnabled(true);
                if(valEdited) {saveValsB.setEnabled(true);}
                lastPageB.setEnabled(true);
                remRowB.setEnabled(true);
                jTable1.getEditorComponent().removeKeyListener(editorKeyLi);
            }
        }
    }//GEN-LAST:event_jTable1PropertyChange

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyChar() == Character.toChars(27)[0]) {
            evt.consume();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    //removes a row from the table
    private void remRowBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remRowBActionPerformed
        ArrayList<String> remdPro;
        String[][] dataCopy = new String[data.length - 1][3];
        
        valEdited = true;
        
        if(lastOldPro >= jTable1.getSelectedRow() && !createNewSxn) {
            if(prosRemoved == null) {
                prosRemoved = new ArrayList<ArrayList<String>>();
            }
            if(prosRemoved.size() == 0) {
                prosRemoved.add(new ArrayList<String>());
                prosRemoved.get(0).add(app.getAllPros().get(jTable1.getSelectedRow()).getQue());
                prosRemoved.get(0).add(app.getAllPros().get(jTable1.getSelectedRow()).getIsHL() ? "HL" : "SL");
                prosRemoved.get(0).add(app.getAllPros().get(jTable1.getSelectedRow()).getType());
            } else {
                remdPro = new ArrayList<String>();
                for(int i = 0, tally = 0; i <= lastOldPro; i++) {
                    for(int j = 0; j < prosRemoved.size(); j++) {
                        if(app.getAllPros().get(i).getQue() == prosRemoved.get(j).get(0) && (app.getAllPros().get(i).getIsHL() ? "HL" : "SL" ).equals(prosRemoved.get(j).get(1)) && app.getAllPros().get(i).getType().equals(prosRemoved.get(j).get(2))) {
                            //if i is the index of a student who was removed
                            tally++;
                            break;
                        }
                    }
                    if(i - tally == jTable1.getSelectedRow()) {
                        //tests if we took away all of the students that were removed (tally) up to here (i) from allData, would we have the same row we are trying to delete?
                        remdPro.add(app.getAllPros().get(i).getQue());
                        remdPro.add(app.getAllPros().get(i).getIsHL() ? "HL" : "SL");
                        remdPro.add(app.getAllPros().get(i).getType());
                        
                        prosRemoved.add(remdPro);
                        break;
                    }
                }
            }
        }
        
        for(int i = 0; i < data.length; i++) {
            if(i == jTable1.getSelectedRow()) {
                continue;
            } else if(i > jTable1.getSelectedRow()) {
                dataCopy[i - 1][0] = data[i][0];
                dataCopy[i - 1][1] = data[i][1];
                dataCopy[i - 1][2] = data[i][2];
            } else {
                dataCopy[i][0] = data[i][0];
                dataCopy[i][1] = data[i][1];
                dataCopy[i][2] = data[i][2];
            }
        }
        
        data = dataCopy;
        if(jTable1.getRowCount() == 1) {
            saveValsB.setEnabled(false);
            remRowB.setEnabled(false);
        } else {
            saveValsB.setEnabled(true);
        }
        
        
        //move the end of the original list down by one if one of the original students is removed
        if(lastOldPro >= jTable1.getSelectedRow()) {
            lastOldPro--;
        }
        
        ((DefaultTableModel) jTable1.getModel()).removeRow(jTable1.getSelectedRow());
    }//GEN-LAST:event_remRowBActionPerformed

    //returns the user to the last page without saving
    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        jTable1.removeAll();
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_lastPageBActionPerformed

    //saves the table data and returns to the previous page
    private void saveValsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveValsBActionPerformed
        boolean reloadClassPrompts = false;
        String newData = "Num_Question_IsHL_Type\n";
        FileWriter newFile = null;
        Prompt proCopy;
        
        updateProData();
        
        if(prosRemoved != null) {
            for(int i = 0; i < app.getAllPros().size(); i++) {
                for(int j = 0; j < prosRemoved.size(); j++) {
                    if(app.getAllPros().get(i).getQue().equals(prosRemoved.get(j).get(0)) && (app.getAllPros().get(i).getIsHL() ? "HL" : "SL" ).equals(prosRemoved.get(j).get(1)) && app.getAllPros().get(i).getType().equals(prosRemoved.get(j).get(2))) {
                        app.getAllPros().remove(i);
                        break;
                    }
                }
            }
            reloadClassPrompts = true;
        }
        for(int i = 0; i < data.length; i++) {
            //for each student in data
            if(i <= lastOldPro) {
                //for every student in current sxn who wasn't removed, update their information
                app.getAllPros().get(i).setId(i);
                app.getAllPros().get(i).setQue(data[i][0]);
                if(app.getCurSxn() != null) {
                    if(app.getCurSxn().getClaSsn() != null) {
                        if(app.getAllPros().get(i).getIsHL() != data[i][1].equals("HL") || !app.getAllPros().get(i).getType().equals(data[i][2])) {
                            //if the type or level of a prompt changed then update the class session prompt list
                            reloadClassPrompts = true;
                        }
                    }
                }
                app.getAllPros().get(i).setIsHL(data[i][1].equals("HL"));
                app.getAllPros().get(i).setType(data[i][2]);     
            } else {
                //add a student that doesn't exist yet
                app.getAllPros().add(new Prompt(i, data[i][0], data[i][1].equals("HL"), data[i][2]));
                reloadClassPrompts = true;
            }
            newData += i + "_" + data[i][0] + "_" + data[i][1].equals("HL") + "_" + data[i][2];
            if(i != data.length - 1) {
                newData += "\n";
            }
        }
        
        if(reloadClassPrompts) {
            app.getCurSxn().getClaSsn().loadPrompts();
        }
        
        for(int i = 0; i < app.getSxns().size(); i++) {
            for(int j = app.getSxns().get(i).getPros().size(); j < app.getAllPros().size(); j++) {
                //make a copy of the original prompt object so we don't overwrite the section-specific isAnswered value
                proCopy = new Prompt(app.getAllPros().get(j).getId(), app.getAllPros().get(j).getQue(), app.getAllPros().get(j).getIsHL(), app.getAllPros().get(j).getType());
                app.getSxns().get(i).getPros().add(proCopy);
            }
        }
                
        try {
            newFile = new FileWriter("AllPrompts.tsv");
            newFile.write(newData);
            newFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ManageProData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jTable1.removeAll();
        
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_saveValsBActionPerformed

    //keeps track of user selection in the table
    private void jTable1ValueChanged() { 
        lastSel[1] = lastSel[0];
        lastSel[0] = jTable1.getSelectedRow();
        if(lastSel[0] == -1) {
            remRowB.setEnabled(false);
        } else if(lastSel[1] == -1) {
            remRowB.setEnabled(true);
        }
    }
    
    //adds data to the table
    private void addProDataToJTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        data = new String[app.getAllPros().size()][3];
        String lvl;
        for (int i = 0; i < app.getAllPros().size(); i++) {
            data[i][0] = app.getAllPros().get(i).getQue();
            if (app.getAllPros().get(i).getIsHL()) {
                lvl = "HL";
            } else {
                lvl = "SL";
            }
            data[i][1] = lvl;
            
            data[i][2] = app.getAllPros().get(i).getType();
        }
        for(int i = 0; i < app.getAllPros().size(); i++) {
            model.addRow(data[i]);
        }
        
        
        lastOldPro = app.getAllPros().size() - 1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRowB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton remRowB;
    private javax.swing.JButton saveValsB;
    // End of variables declaration//GEN-END:variables
}
