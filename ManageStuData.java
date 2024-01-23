/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom.helper;

import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ianmoore
 */
public class ManageStuData extends javax.swing.JPanel {

    /**
     * Creates new form ManageStuData
     */
    
    /**
     * Creates new form ManageStuDataGUI
     */
    private ClassroomHelper app;
    private JPanel lastPage;
    private GUIFrame baseFrame;
    private String[][] data;
    //private ListSelectionEvent[] lastSel = new ListSelectionEvent[2];
    private int[] lastSel = new int[2];
    private ListSelectionListener liSelLi;
    private int selectedRow;
    private boolean rowAdded = false, threadWaiting = false;
    private TableModelListener taMoLi;
    private EventObject editCellEvent;
    private TableCellEditor cellEditor;
    private TableModelEvent taEvt;
    private Thread secondThread;
    private Object monitorObject = new Object();
    private KeyAdapter editorKeyLi;
    private boolean valEdited;
    private boolean invalRqst = false;
    private int newSxnGra;
    private boolean createNewSxn;
    private int lastOldStu;
    private String[][] allData;
    private ArrayList<ArrayList<String>> stusRemoved;
    //private ManageStuData manStus;
    /**
     * Creates new form ManageStudentData
     */
    public ManageStuData(ClassroomHelper hlpr, JPanel last, GUIFrame frame, boolean createSxn) {
        //System.out.println
        baseFrame = frame;
        boolean startGUI = true;
        app = hlpr;
        createNewSxn = createSxn;
        lastPage = last;
        
        System.out.println("Parent: " + baseFrame);
        System.out.println("this: " + this);
        //manStus = this;
        if(app.getSxnNames().size() == 0 || createSxn) {
            data = new String[0][3];
            //if(createSxn) {
            Object[] options = {"11", "12"};
            String gra = (String) javax.swing.JOptionPane.showInputDialog(null, "Choose a grade: ", "Create New Section - Grade Selection Popup", JOptionPane.PLAIN_MESSAGE, null, options, "11");
            System.out.println("gra is null: " + gra == null);
            if(gra == null) {
                System.out.println("isNull true");
                startGUI = false;
                //this.setEnabled(false);
                //this.setVisible(false);
                baseFrame.setContentPane(lastPage);
                lastPage.setVisible(true);
                
                //lastPage.setVisible(true);
            } else {
                newSxnGra = Integer.parseInt(gra);
                baseFrame.setContentPane(this);
                initComponents();
                saveValsB.setEnabled(false);
                remRowB.setEnabled(false);
                lastPageB.setEnabled(false);
                lastPageB.setVisible(false);
                System.out.println("new sxn gra: " + newSxnGra);
                lastSel[0] = -1;
                //this.setEnabled(false);
                //this.setVisible(false);
                //this.setLayout(lastPage.getLayout());
                //baseFrame.setEnabled(true);
                //baseFrame.setVisible(true);
                //startGUI = true;
                //manStus.setEnabled(true);
                //manStus.setVisible(true);
                
                //initComponents();
            }
            //}
        } else {
            baseFrame.setContentPane(this);
            initComponents();
            remRowB.setEnabled(false);
            addStuDataToJTable();
            lastSel[0] = -1;
        }
        //editCellEvent = new EventObject(0);
        System.out.println("StartGUI: " + startGUI);
        if(startGUI) {
        valEdited = false;
        saveValsB.setEnabled(false);
        jLabel1.setVisible(false);
        jTable1.getSelectionModel().addListSelectionListener(liSelLi = new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTable1ValueChanged();
            }
        });
        
        jTable1.getModel().addTableModelListener(taMoLi = new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE) {
                    synchronized(monitorObject) {
                        //threadWaiting = false;
                        //jButton1.setEnabled(false);
                        if(secondThread != null) {
                            System.out.println("2ndThreadAtRowAdded: " + secondThread);
                            if(secondThread.getState() == Thread.State.WAITING) {
                                System.out.println("set to waiting");
                                threadWaiting = true;
                            }
                        }
                        System.out.println("EvtIsRowAdded: " + (e.getType() == TableModelEvent.INSERT));
                        //table changed for row added/removed
                        System.out.println("lastSel: " + lastSel[0]);
                        if(editCellEvent != null) {if(editCellEvent.getSource() != null) {System.out.println("editCellEventSrc: " + editCellEvent.getSource().toString());}}
                        System.out.println("at table changed: " + jTable1.getModel().getValueAt(lastSel[0], 0) + " " + jTable1.getModel().getValueAt(lastSel[0], 1));
                        System.out.println("lastSelCol0 is blank: " + jTable1.getModel().getValueAt(lastSel[0], 0).equals("") + ", lastSelCol1 is blank: " + jTable1.getModel().getValueAt(lastSel[0], 1).equals(""));
                        if(jTable1.getModel().getValueAt(lastSel[0], 0).equals("")) { //use == instead?
                            System.out.println("lastSel1: " + lastSel[1] + ", lastSel0: " + lastSel[0] + ", editCellEvtSrc" + editCellEvent.getSource().toString());
                            System.out.println("alert0");
                            //why are these print statements not showing up??????? does it matter?????
                            jLabel1.setVisible(true);
                            //jButton1.setEnabled(false);
                            System.out.println(editCellEvent);

                            if(editCellEvent != null) {System.out.println(editCellEvent.getSource().toString());}
                            //our last selection was at the same location as editCellEvent was triggered when we are adding a new row
                            //if(editCellEvent == null) {
                                //editCellEvent = new EventObject(lastSel[0]);
                                //jTable1.editCellAt(lastSel[0], 0, editCellEvent);
                            if(!(editCellEvent.getSource().toString().equals(Integer.toString(lastSel[0])))) {
                                System.out.println("Triggered!");
                                //rowAdded = false; // temp
                                //if(rowAdded)
                                invalRqst = true;
                                editCellEvent = new EventObject(lastSel[0]);
                                jTable1.editCellAt(lastSel[0], 0, editCellEvent);
                            }
                            //rowAdded = true;
                            //below condition doesn't work anymore 
                            /*if(e.getType() == TableModelEvent.INSERT && e.getColumn() == TableModelEvent.ALL_COLUMNS && !(jTable1.getValueAt(lastSel[0], 0).equals(""))) {
                                System.out.println("fired");
                                editCellEvent = new EventObject(lastSel[0]);
                                ((DefaultTableModel) jTable1.getModel()).fireTableChanged(new TableModelEvent(jTable1.getModel(), data.length - 1, data.length - 1, 1, TableModelEvent.INSERT));
                            }*/

                        } else if(jTable1.getModel().getValueAt(lastSel[0], 1).equals("") && threadWaiting == false) {//!rowAdded is new
                            System.out.println("alert, rowAdded: " + rowAdded);
                            System.out.println("editCellEventSrc: " + editCellEvent.getSource().toString() + ", LastSel: " + lastSel[0]);
                            jLabel1.setVisible(true);
                            //jButton1.setEnabled(false);
                            //System.out.println("EditCellEventIsNull: " + editCellEvent.getSource() == null);
                            //if(rowAdded) {
                                //rowAdded = false;
                                //jTable1.getCellEditor().cancelCellEditing();
                                //editCellEvent = new EventObject(0);
                                //jTable1.changeSelection(lastSel[0], 1, false, false);
                                //jTable1.editCellAt(lastSel[0], 1);
                                //jTable1.changeSelection(lastSel[0], 1, false, false);
                            //}

                            System.out.println("Src is null: " + editCellEvent.getSource() == null);

                            if(!(editCellEvent.getSource().toString().equals(Integer.toString(lastSel[0])))) { //supposed to act like a do-while loop
                                /*if(rowAdded) {
                                    jTable1.getCellEditor().cancelCellEditing();
                                }*/
                                invalRqst = true;
                                editCellEvent = new EventObject(lastSel[0]);
                                jTable1.editCellAt(lastSel[0], 1, editCellEvent);
                            }
                        } else {
                            //rowAdded = false; // temp
                            valEdited = true;
                            invalRqst = false;
                            jLabel1.setVisible(false);
                            //app.getSxnMems().get(lastSel[0]).setFiName((String) jTable1.getModel().getValueAt(lastSel[0], 0));
                            data[lastSel[0]][0] = (String) jTable1.getModel().getValueAt(lastSel[0], 0);
                            System.out.print("Stu: " + data[lastSel[0]][0] + " ");
                            //app.getSxnMems().get(lastSel[0]).setLaName((String) jTable1.getModel().getValueAt(lastSel[0], 1));
                            data[lastSel[0]][1] = (String) jTable1.getModel().getValueAt(lastSel[0], 1);
                            data[lastSel[0]][2] = (String) jTable1.getModel().getValueAt(lastSel[0], 2);
                            System.out.println(data[lastSel[0]][1] + " " + data[lastSel[0]][2]);
                            //jButton1.setEnabled(true);
                        }
                        //synchronized(monitorObject) {
                        if(threadWaiting) {
                            monitorObject.notify();
                            threadWaiting = false;
                            System.out.println("thread waiting finished");
                        } else if(rowAdded) {
                            rowAdded = false;
                        }
                        
                        
                        //}
                    }
                }
            }
        });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel2.setText("Section Manager - Manage Students");

        addRowB.setText("Add student");
        addRowB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowBActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        remRowB.setText("Remove student");
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

        jLabel3.setText("<HTML>Double click on a cell in the \"First Name\" or \"Last Name\" column to edit it. Select a row and click once in the \"Level\" column to change the selected student's level or press \"Remove student\" to delete them.</HTML>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(addRowB)
                        .addGap(18, 18, 18)
                        .addComponent(remRowB)
                        .addGap(18, 18, 18)
                        .addComponent(saveValsB)
                        .addGap(58, 58, 58))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void addRowBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowBActionPerformed
        String[][] dataCopy = new String[data.length + 1][3];
        boolean success;
        
        valEdited = true;
        
        for(int i = 0; i < data.length; i++) {
            dataCopy[i][0] = data[i][0];
            dataCopy[i][1] = data[i][1];
            dataCopy[i][2] = data[i][2];
        }
        data = dataCopy;
        data[data.length - 1][0] = "";
        data[data.length - 1][1] = "";
        data[data.length - 1][2] = "SL";

        System.out.println(addRowB.isEnabled());
        ((DefaultTableModel) jTable1.getModel()).addRow(data[data.length - 1]);
        rowAdded = true;
        for(String[] s: data) {
            System.out.println(s[0] + " " + s[1] + " " + s[2]);
        }

        //jButton1.setEnabled(false);
        secondThread = new Thread(new RowHandler());
        jTable1.changeSelection(data.length - 1, 0, false, false);
        jTable1.editCellAt(data.length - 1, 0);
        System.out.println(secondThread);
        secondThread.start();
    }//GEN-LAST:event_addRowBActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        //if(lastSel[1] != null && lastSel[0] != null) {
            //System.out.println("first1: " + lastSel[1] + ", first0: " + lastSel[0]/* + ", last1: " + lastSel[1] + ", last0: " + lastSel[0]*/);
            if(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).equals("") || jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1).equals("")) {
                invalRqst = true;
                jLabel1.setVisible(true);
            } else {
                invalRqst = false;
                jLabel1.setVisible(false);
            }
            if(!(((String) jTable1.getModel().getValueAt(jTable1.rowAtPoint(evt.getPoint()), 0)).equals("") || ((String) jTable1.getModel().getValueAt(jTable1.rowAtPoint(evt.getPoint()), 1)).equals(""))) {
                if(lastSel[1] == lastSel[0] && jTable1.isEditing() == false) {
                    //System.out.println("sel same");
                    if(jTable1.columnAtPoint(evt.getPoint()) == 2) {
                        //if(jTable1.rowAtPoint(evt.getPoint()) != -1) {
                            if(jTable1.getModel().getValueAt(jTable1.rowAtPoint(evt.getPoint()), jTable1.columnAtPoint(evt.getPoint())) == "SL") {
                                jTable1.getModel().setValueAt("HL", jTable1.rowAtPoint(evt.getPoint()), jTable1.columnAtPoint(evt.getPoint()));
                                //data[jTable1.rowAtPoint(evt.getPoint())][2] = "HL";
                            } else {
                                jTable1.getModel().setValueAt("SL", jTable1.rowAtPoint(evt.getPoint()), jTable1.columnAtPoint(evt.getPoint()));
                                //data[jTable1.rowAtPoint(evt.getPoint())][2] = "HL";
                            }
                            valEdited = true;
                            saveValsB.setEnabled(true);
                            //}
                    }
                } else if(((String) jTable1.getModel().getValueAt(data.length - 1, 1)).equals("")/* && rowAdded*/) { // could be replaced with rowAdded
                    System.out.println("switched");
                    jTable1.changeSelection(data.length - 1, 1, false, false);
                    //jTable1.getEditorComponent().
                    evt.consume();
                }
            }
            //if()

            //}
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        if(evt.getPropertyName().equals("tableCellEditor")) {
            //System.out.println("activated");
            if(jTable1.isEditing()) {
                addRowB.setEnabled(false);
                saveValsB.setEnabled(false);
                lastPageB.setEnabled(false);
                remRowB.setEnabled(false);
                jTable1.getEditorComponent().addKeyListener(editorKeyLi = new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent ev) {
                        //System.out.println("key pressed");
                        if(ev.getKeyChar() == Character.toChars(27)[0]) {
                            //System.out.println("esc pressed");
                            if(jTable1.isEditing()) {
                                //System.out.println("isFocused: " + jTable1.getEditorComponent().);
                                //if we click away from the editing cell and the previous editor value is blank and
                                //if(jTable1.)
                                //System.out.println("invalRqst: " + invalRqst);
                                if(rowAdded || threadWaiting || invalRqst) {
                                    //System.out.println("rowAdded: " + rowAdded + ", threadWaiting: " + threadWaiting);
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
            //if(javax.swing.JTable.)
        }
    }//GEN-LAST:event_jTable1PropertyChange

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyChar() == Character.toChars(27)[0]) {
            evt.consume();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void remRowBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remRowBActionPerformed
        ArrayList<String> remdStu;
        String[][] dataCopy = new String[data.length - 1][3];
        
        valEdited = true;
        
        if(lastOldStu >= jTable1.getSelectedRow() && !createNewSxn) {
            if(stusRemoved == null) {
                stusRemoved = new ArrayList<ArrayList<String>>();
            }
            if(stusRemoved.size() == 0) {
                stusRemoved.add(new ArrayList<String>());
                //stusRemoved.get(0).add(0, allData[jTable1.getSelectedRow()][0]);
                stusRemoved.get(0).add(app.getSxnMems().get(jTable1.getSelectedRow()).getFiName());
                //stusRemoved.get(0).add(1, allData[jTable1.getSelectedRow()][1]);
                stusRemoved.get(0).add(app.getSxnMems().get(jTable1.getSelectedRow()).getLaName());
                //stusRemoved.get(0).add(2, allData[jTable1.getSelectedRow()][2]);
                stusRemoved.get(0).add(app.getSxnMems().get(jTable1.getSelectedRow()).getIsHL() ? "HL" : "SL");
            } else {
                remdStu = new ArrayList<String>();
                for(int i = 0, tally = 0; i <= lastOldStu; i++) {
                    for(int j = 0; j < stusRemoved.size(); j++) {
                        if(app.getSxnMems().get(i).getFiName() == stusRemoved.get(j).get(0) && app.getSxnMems().get(i).getLaName() == stusRemoved.get(j).get(1) && (app.getSxnMems().get(i).getIsHL() ? "HL" : "SL").equals(stusRemoved.get(j).get(2))) {
                            //if i is the index of a student who was removed
                            tally++;
                            break;
                        }
                    }
                    if(i - tally == jTable1.getSelectedRow()) {
                        //if we took away all of the students that were removed (tally) up to here (i) from allData, would we have the same row we are trying to delete?
                        remdStu.add(app.getSxnMems().get(i).getFiName());
                        remdStu.add(app.getSxnMems().get(i).getLaName());
                        remdStu.add(app.getSxnMems().get(i).getIsHL() ? "HL" : "SL");
                        /*stusRemoved.get(stusRemoved.size() - 1).add(0, allData[i][0]);
                        stusRemoved.get(stusRemoved.size() - 1).add(1, allData[i][1]);
                        stusRemoved.get(stusRemoved.size() - 1).add(2, allData[i][2]);*/

                        stusRemoved.add(remdStu);
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
        if(lastOldStu >= jTable1.getSelectedRow()) {
            //System.out.println("Stu removed: " + stusRemoved.get(stusRemoved.size() - 1).get(0) + " " + stusRemoved.get(stusRemoved.size() - 1).get(1) + " " + stusRemoved.get(stusRemoved.size() - 1).get(2));
            lastOldStu--;
            //System.out.println("lastOldStu: " + lastOldStu);
        }
        
        ((DefaultTableModel) jTable1.getModel()).removeRow(jTable1.getSelectedRow());
        
        
    }//GEN-LAST:event_remRowBActionPerformed

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        jTable1.removeAll();
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_lastPageBActionPerformed

    private void saveValsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveValsBActionPerformed
        int sxnInd = 0;
        //boolean stuUpdated;
        if(createNewSxn) {
            Section newSxn = createNewSxn();
            app.setCurSxn(newSxn);
            if(app.getSxns().size() == 0) {
                app.getSxns().add(newSxn);
            } else {
                sxnInd = app.getIndex(app.getSxns(), newSxn.getName(), 0, app.getSxns().size() - 1);
                //app.setCurSxnNum(sxnInd);
                app.getSxns().add(sxnInd, newSxn);
            }
            app.setCurSxnNum(sxnInd);
            app.setSxnMems(newSxn.getStus());
            newSxn.setPBlists();

            newSxn.updateStuData();

            /*for(Section i: app.getSxns()) {
                if(i.equals(newSxn)) {
                    for(Student s: i.getStus()) {
                        System.out.println(s.getFuName() + ", isHl: " + s.getIsHL());
                    }
                }
            }*/

        } else {
            if(stusRemoved != null) {
                for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
                    for(int j = 0; j < stusRemoved.size(); j++) {
                        if(app.getCurSxn().getStus().get(i).getFiName() == stusRemoved.get(j).get(0) && app.getCurSxn().getStus().get(i).getLaName() == stusRemoved.get(j).get(1) && (app.getCurSxn().getStus().get(i).getIsHL() ? "HL" : "SL").equals(stusRemoved.get(j).get(2))) {
                            //if a student in current sxn was removed, then delete their slot (and info) from current sxn
                            app.getCurSxn().purgeStudent(i);
                            break;
                        }
                    }
                }
            }
            for(int i = 0; i < data.length; i++) {
                //for each student in data
                if(i <= lastOldStu) {
                    //for every student in current sxn who wasn't removed, update their information
                    app.getCurSxn().getStus().get(i).setFiName(data[i][0]);
                    app.getCurSxn().getStus().get(i).setLaName(data[i][1]);
                    if(app.getCurSxn().getStus().get(i).getIsHL() != data[i][2].equals("HL") && app.getCurSxn().getClaSsn() != null) {
                        if(app.getCurSxn().getClaSsn().getLvl().equals("HL") && data[i][2].equals("SL")) {
                            app.getCurSxn().getClaSsn().removeStuFromCurrentGru(app.getCurSxn().getStus().get(i));
                        }
                    }
                    app.getCurSxn().getStus().get(i).setIsHL(data[i][2].equals("HL"));
                    System.out.println("Stu Updated: Stu " + i + " updated to " + app.getCurSxn().getStus().get(i).getFuName() + ", PBList: " + app.getCurSxn().getStus().get(i).getBPBlist());                                      
                } else {
                    //add a student that doesn't exist yet
                    app.getCurSxn().getStus().add(new Student(data[i][0], data[i][1], (data[i][2].equals("HL"))));
                    System.out.println("Stu Added: " + app.getCurSxn().getStus().get(app.getCurSxn().getStus().size() - 1).getFuName());
                }                
            }
            if(app.getCurSxn().getClaSsn() != null) {
                app.getCurSxn().getClaSsn().updatePresStus();
            }
            app.getCurSxn().updateStuData();
        }
        jTable1.removeAll();
        if(lastPage.getClass() == StartPage.class) {
            baseFrame.setContentPane(new ManageProData(app, lastPage, baseFrame, true));
        } else {
            baseFrame.setContentPane(lastPage);
        }
        /*System.out.println("Current Sxn Stus: ");
        for(Student s: app.getCurSxn().getStus()) {
            System.out.println(s.getFiName() + " " + s.getLaName() + " " + s.getIsHL());
        }
        System.out.println("Sxn Mems Stus: ");
        for(Student s: app.getSxnMems()) {
            System.out.println(s.getFiName() + " " + s.getLaName() + " " + s.getIsHL());
        }*/
        System.out.println("Sxn Mems == Current Sxn Stus: " + (app.getSxnMems() == app.getCurSxn().getStus()));
    }//GEN-LAST:event_saveValsBActionPerformed

    private void jTable1ValueChanged() { 
        lastSel[1] = lastSel[0];
        lastSel[0] = jTable1.getSelectedRow();
        if(lastSel[0] == -1) {
            remRowB.setEnabled(false);
        } else if(lastSel[1] == -1) {
            remRowB.setEnabled(true);
        }
        /*rowAdded = jTable1.isEditing();
        if(rowAdded) {
            System.out.println("editCellEventisNull: " + editCellEvent == null);
            if(editCellEvent != null) {
                System.out.println("editCellEventSrcisNull: " + editCellEvent.getSource() == null);
                if(editCellEvent.getSource() != null) {
                    System.out.println("editCellEventSrc = " + editCellEvent.toString());
                }
            }
        }*/
    }
    
    private void addStuDataToJTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        data = new String[app.getSxnMems().size()][3];
        //allData = new String[data.length][3];
        String lvl;
        System.out.println("allData: ");
        for (int i = 0; i < app.getSxnMems().size(); i++) {
            data[i][0] = app.getSxnMems().get(i).getFiName();
            //allData[i][0] = data[i][0];
            data[i][1] = app.getSxnMems().get(i).getLaName();
            //allData[i][1] = data[i][1];
            if (app.getSxnMems().get(i).getIsHL()) {
                lvl = "HL";
            } else {
                lvl = "SL";
            }
            data[i][2] = lvl;
            //allData[i][2] = lvl;
            //System.out.println(allData[i][0] + " " + allData[i][1] + " " + allData[i][2]);
        }
        for(int i = 0; i < app.getSxnMems().size(); i++) {
            model.addRow(data[i]);
        }
        
        lastOldStu = app.getSxnMems().size() - 1;
    }
    
    //public void
    /*if(app.getCurSxn() != null) {
                    stus = app.getCurSxn().getStus();
                    for(int j = 0; j < stus.size(); j++) {
                        if(data[i][0] == stus.get(j).getFiName()) {
                            if(app.getCurSxn().getClaSsn() != null) {
                                if(app.getCurSxn().getClaSsn().getGrus().get(stus.get(j).getGru()).getMems().size() == 1) {
                                    app.getCurSxn().getClaSsn().remGru(stus.get(j).getGru());
                                }
                            }
                            app.getCurSxn().getStus().remove(j);
                        }
                    }
                }*/
    
    private Section createNewSxn() {
        //System.out.println("making new file!");
        String basePath = "", proData;
        File f1 = new File(""), f2 = new File("");
        FileWriter newFile = null;
        int newSxnNum = 0;
        String[] grades, sxns;
        if(new File("").getAbsolutePath().endsWith("src")) {
            basePath = "../"; // running from application package
        }
        basePath += "SectionData/";
        
        LinkedList<Student> stus = new LinkedList<Student>();
        for(int i = 0; i < data.length; i++) {
            stus.add(new Student(data[i][0], data[i][1], (data[i][2]).equals("HL")));
        }
        
        basePath += newSxnGra;
        
        try {
            new File(basePath).mkdir();
            if(app.getSxnNames().size() == 0) {
                basePath += "/01";
                //System.out.println("basePath0: " + basePath);
                app.setCurSxnNum(0);
            } else if(newSxnGra == 12) {
                basePath += "/";
                if(app.getSxnNames().size() < 10) {
                    basePath += "0";
                }
               
                basePath += (Integer.parseInt(app.getSxnNames().get(app.getSxnNames().size() - 1).substring(3)));
                //System.out.println("basePath1: " + basePath);
                app.setCurSxnNum(app.getSxnNames().size());
            } else {
                basePath += "/";
                for(newSxnNum = 0; newSxnNum < app.getSxnNames().size() && !app.getSxnNames().get(newSxnNum).substring(0, 2).equals("12"); newSxnNum++) {}
                if(newSxnNum + 1 < 10) {
                    basePath += "0";
                }
                //System.out.println("newSxnnum: " + newSxnNum);
                basePath += newSxnNum + 1;
                //System.out.println("basePath2: " + basePath);
                app.setCurSxnNum(newSxnNum);
            }
            new File(basePath).mkdir();
            //System.out.println(basePath + "/Students.tsv");
            f1 = new File(basePath + "/Students.tsv");
            f1.createNewFile();
            f2 = new File(basePath + "/Prompts.tsv");
            f2.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ManageStuData.class.getName()).log(Level.SEVERE, null, ex);
        }

        app.getSxnNames().add(app.getCurSxnNum(), basePath.substring(basePath.length() - 5, basePath.length() - 3) + "." + basePath.substring(basePath.length() - 2));
        app.getSxnPaths().add(app.getCurSxnNum(), basePath);
        
        proData = "PromptNo isAnswered\n";
        for(int i = 0; i < app.getAllPros().size(); i++) {
           proData += i + " false\n";
           if(i == app.getAllPros().size() - 1) {
               proData = proData.substring(0, proData.length() - 1);
           }
        }
        
        
        try {
            newFile = new FileWriter(basePath + "/Students.tsv");
            newFile.write("N/A\nFirstName LastName isHL isPresent Group parBlist\n");
            newFile.close();
            newFile = new FileWriter(basePath + "/Prompts.tsv");
            newFile.write(proData);
            newFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ManageStuData.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(app.getSxnNames().size() == 0) {  
            //for(int)
            return new Section(stus, newSxnGra, 1, basePath, app.getAllPros());
        }
        if(newSxnGra == 12) {
            return new Section(stus, newSxnGra, app.getSxns().size(), basePath, app.getAllPros());
        }
        
        return new Section(stus, newSxnGra, newSxnNum, basePath, app.getAllPros());
    }
    
    public class RowHandler implements Runnable {

        @Override
        public void run() {
            synchronized(monitorObject) {
                System.out.println("changing selection here");
                //System.out.println
                //jTable1.changeSelection(data.length - 1, 0, false, false);
                System.out.println("waiting here");
                try {
                    monitorObject.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ManageStuData.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("thread finishing");
                //jTable1.getCellEditor().cancelCellEditing();
                jTable1.changeSelection(data.length - 1, 1, false, false);
                //jButton1.setEnabled(false);
                jTable1.editCellAt(data.length - 1, 1);
            }
        }

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
