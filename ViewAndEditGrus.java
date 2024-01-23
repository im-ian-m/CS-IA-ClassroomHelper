/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classroom.helper;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ianmoore
 */
public class ViewAndEditGrus extends javax.swing.JPanel {

    private ClassroomHelper app;
    private ClassS classS;
    private ClassHome homepage;
    private JPanel lastPage;
    private GUIFrame baseFrame;
    private NoSelectionModel noSelectionModel;
    private String[] data1, data2;
    private boolean editing;
    private boolean changesMade = false;
    private ArrayList<Integer> groupHeadings1;
    private ArrayList<Integer> groupHeadings2;
    private FocusListener list1FocusListener;
    private FocusListener list2FocusListener;
    
    public ViewAndEditGrus(ClassroomHelper appl, ClassS cla, ClassHome home, JPanel last, GUIFrame frame, boolean edit) {
        app = appl;
        classS = cla;
        homepage = home;
        lastPage = last;
        baseFrame = frame;
        editing = edit;
        baseFrame.setContentPane(this);
        initComponents();
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        setEditingModeEnabled(editing);
        addGruDataToLists();
        jList1.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {}

            @Override
            public void ancestorRemoved(AncestorEvent event) {}

            @Override
            public void ancestorMoved(AncestorEvent event) {
                jPanel1.setPreferredSize(new Dimension(jPanel1.getWidth(), jList1.getHeight() + 16));
                jList1.removeAncestorListener(this);
            }
        });
        
        
    }

    public void setEditingModeEnabled(boolean enabled) {
        if(enabled) {
            editing = true;
            jList1.addFocusListener(list1FocusListener = new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(!jList1.isSelectionEmpty()) {
                        swapStuToGruB.setEnabled(true);
                        addRemStuToGruB.setEnabled(true);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if(e.getOppositeComponent() == jList2) {
                        jList1.getSelectionModel().clearSelection();
                    }
                }
            });
            jList2.addFocusListener(list2FocusListener = new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(!jList2.isSelectionEmpty()) {
                        swapStuToGruB.setEnabled(true);
                        addRemStuToGruB.setEnabled(true);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if(e.getOppositeComponent() == jList1) {
                        jList2.getSelectionModel().clearSelection();
                    }
                }
            });
            if(lastPage.getClass() == GruSorter.class || lastPage.getClass() == SortStusPerGru.class || lastPage.getClass() == GruSorterHome.class) {
                lastPageB.setVisible(true);
            }
            jLabel1.setText("Edit Groups");
            jLabel2.setText("Select a student's name to change their group. Hover over either button below the lists to see what it does.");
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            okayB.setEnabled(false);
            editGroupsB.setVisible(false);
            swapStuToGruB.setVisible(true);
            addRemStuToGruB.setVisible(true);
            swapStuToGruB.setEnabled(false);
            addRemStuToGruB.setEnabled(false);
            jList1.setSelectionModel(new GroupSelectionModel1());
            jList2.setSelectionModel(new GroupSelectionModel2());
            okayB.setText("Save");
        } else {
            jLabel1.setText("View Groups");
            jLabel2.setText("");
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            if(editing) {
                editing = false;
                jList1.removeFocusListener(list1FocusListener);
                jList2.removeFocusListener(list2FocusListener);
            }
            System.out.println(lastPage.getClass());
            if(lastPage.getClass() == GruSorter.class || lastPage.getClass() == SortStusPerGru.class || lastPage.getClass() == GruSorterHome.class) {
                lastPageB.setVisible(false);
                editGroupsB.setVisible(true);
            }
            okayB.setEnabled(true);
            jList1.setSelectionModel(new NoSelectionModel());
            jList2.setSelectionModel(new NoSelectionModel());
            okayB.setText("Okay");
            swapStuToGruB.setVisible(false);
            addRemStuToGruB.setVisible(false);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jList1 = new javax.swing.JList<>();
        jList2 = new javax.swing.JList<>();
        okayB = new javax.swing.JButton();
        addRemStuToGruB = new javax.swing.JButton();
        swapStuToGruB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        editGroupsB = new java.awt.Button();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setFocusTraversalKeysEnabled(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(454, 308));
        jScrollPane1.setViewportView(jPanel1);

        jPanel1.setPreferredSize(new java.awt.Dimension(652, 308));

        jList1.setFocusTraversalKeysEnabled(false);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });

        jList2.setFocusTraversalKeysEnabled(false);
        jList2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jList2.setMinimumSize(new java.awt.Dimension(50, 85));
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jList1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jList2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jList2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(jList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        okayB.setText("Save");
        okayB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayBActionPerformed(evt);
            }
        });

        addRemStuToGruB.setText("Adding/Removing");
        addRemStuToGruB.setToolTipText("Removes a student from their group and adds them to a different one");
        addRemStuToGruB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemStuToGruBActionPerformed(evt);
            }
        });

        swapStuToGruB.setText("Swapping out");
        swapStuToGruB.setToolTipText("Swaps a student with a student in another group");
        swapStuToGruB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swapStuToGruBActionPerformed(evt);
            }
        });

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("View Groups");

        jLabel2.setText(" ");

        editGroupsB.setLabel("Edit Groups");
        editGroupsB.setVisible(false);
        editGroupsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editGroupsBActionPerformed(evt);
            }
        });

        jLabel3.setText("Move a student to a different group by");

        jLabel4.setText("or");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lastPageB))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(editGroupsB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addRemStuToGruB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(swapStuToGruB)
                        .addGap(32, 32, 32)
                        .addComponent(okayB)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 71, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastPageB)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editGroupsB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(okayB)
                            .addComponent(addRemStuToGruB)
                            .addComponent(swapStuToGruB)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(16, 16, 16))))
        );

        addRemStuToGruB.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void okayBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayBActionPerformed
        if(editing) {
            int confirm = -2;
            for(int i = 0; i < classS.getGrus().size(); i++) {
                if(classS.getGrus().get(i).getMems().size() == 0) {
                    confirm = (int) new JOptionPane().showConfirmDialog(baseFrame,  "One or more groups have no students in them and will be deleted if you continue. Continue?", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(confirm == JOptionPane.YES_OPTION) {
                        app.getCurSxn().updateStuData();
                        if(lastPage.getClass() == GruSorter.class || lastPage.getClass() == SortStusPerGru.class) {
                            setEditingModeEnabled(false);
                        } else {
                            baseFrame.setContentPane(lastPage);
                        }
                    }
                    break;
                }
            }
            if(confirm == -2) {
                app.getCurSxn().updateStuData();
                if(lastPage.getClass() == GruSorter.class || lastPage.getClass() == SortStusPerGru.class) {
                    setEditingModeEnabled(false);
                } else {
                    baseFrame.setContentPane(lastPage);
                }
            }
        } else {
            new GruSorterHome(app, classS, homepage, baseFrame);
        }
    }//GEN-LAST:event_okayBActionPerformed
                
    private void swapStuToGruBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swapStuToGruBActionPerformed
        swapStuToGruB.setEnabled(false);
        addRemStuToGruB.setEnabled(false);
        Integer[] groupOptions = new Integer[classS.getGrus().size() - 1];
        Student student = null;
        if(jList1.getSelectionModel().isSelectionEmpty()) {
            int firstGruInList = groupHeadings1.size();
            if(groupHeadings2.size() == 1) {
                student = classS.getGrus().get(firstGruInList).getMems().get(jList2.getSelectedIndex() - 1);
            } else {
                for(int i = 0; i < groupHeadings2.size(); i++) {
                    if(jList2.getSelectedIndex() < groupHeadings2.get(i)) {
                        System.out.println("SelectedIndex: " + jList2.getSelectedIndex());
                        System.out.println("MostRecentGru: " + groupHeadings2.get(i - 1));
                        student = classS.getGrus().get(firstGruInList + i - 1).getMems().get(jList2.getSelectedIndex() - groupHeadings2.get(i - 1) - 1);
                        break;
                    } else if(i == groupHeadings2.size() - 1) {
                        student = classS.getGrus().get(firstGruInList + i).getMems().get(jList2.getSelectedIndex() - groupHeadings2.get(i) - 1);
                    }
                }
            }
        } else {
            if(groupHeadings1.size() == 1) {
                student = classS.getGrus().get(0).getMems().get(jList1.getSelectedIndex() - 1);
            } else {
                for(int i = 1; i < groupHeadings1.size(); i++) {
                    System.out.println("gruHeadings1 size - 1: " + (groupHeadings1.size() - 1));
                    System.out.println("i: " + i);
                    if(jList1.getSelectedIndex() < groupHeadings1.get(i)) {
                        System.out.println("SelectedIndex: " + jList1.getSelectedIndex());
                        System.out.println("MostRecentGru: " + groupHeadings1.get(i - 1));
                        student = classS.getGrus().get(i - 1).getMems().get(jList1.getSelectedIndex() - groupHeadings1.get(i - 1) - 1);
                        break;
                    } else if(i == groupHeadings1.size() - 1) {
                        student = classS.getGrus().get(i).getMems().get(jList1.getSelectedIndex() - groupHeadings1.get(i) - 1);
                    }
                }
            }
        }
        System.out.println("Student to Swap: " + student.getFuName());
        
        for(int i = 0; i < classS.getGrus().size(); i++) {
            if(i < student.getGru()) {
                groupOptions[i] = i + 1;
                System.out.println("Group at index " + i + " is group " + (i + 1));
            } else if(i > student.getGru()) {
                groupOptions[i - 1] = i + 1;
                System.out.println("Group at index " + (i - 1) + " is group " + (i + 1));
            }
        }
                
        Integer group = (Integer) javax.swing.JOptionPane.showInputDialog(null, "Choose a group to move the selected student to: ", "Edit Groups - Swap to Group - Group Selection Popup", JOptionPane.PLAIN_MESSAGE, null, groupOptions, groupOptions[0]);
        
        if(group == null) {
            if(jList1.isSelectionEmpty()) {
                jList2.requestFocusInWindow();
            } else {
                jList1.requestFocusInWindow();
            }
        } else {
            group--;
            String[] studentOptions = new String[classS.getGrus().get(group).getMems().size()];
            Student secondStudentToSwap;
            for(int i = 0; i < classS.getGrus().get(group).getMems().size(); i++) {
                studentOptions[i] = classS.getGrus().get(group).getMems().get(i).getFuName();
            }
            String studentStr = (String) javax.swing.JOptionPane.showInputDialog(null, "Choose a student to swap the selected student with: ", "Edit Groups - Swap to Group - Student Selection Popup", JOptionPane.PLAIN_MESSAGE, null, studentOptions, studentOptions[0]);
            
            if(studentStr == null) {
                if(jList1.isSelectionEmpty()) {
                    jList2.requestFocusInWindow();
                } else {
                    jList1.requestFocusInWindow();
                }
            } else {
                changesMade = true;
                okayB.setEnabled(true);
                int studentNum = -1;
                for(int i = 0; i < studentOptions.length; i++) {
                    if(studentStr == studentOptions[i]) {
                        studentNum = i;
                        break;
                    }
                }
                secondStudentToSwap = classS.getGrus().get(group).getMems().get(studentNum);
                classS.swapToGru(student, secondStudentToSwap);
                for(Group g: classS.getGrus()) {
                    System.out.println("Group " + g.getNum());
                    for(Student s: g.getMems()) {
                        System.out.println(s.getFuName());
                    }
                }
                addGruDataToLists();
            }
        }
    }//GEN-LAST:event_swapStuToGruBActionPerformed

    private void addRemStuToGruBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemStuToGruBActionPerformed
        swapStuToGruB.setEnabled(false);
        addRemStuToGruB.setEnabled(false);
        Integer[] groupOptions = new Integer[classS.getGrus().size() - 1];
        Student student = null;
        if(jList1.getSelectionModel().isSelectionEmpty()) {
            int firstGruInList = groupHeadings1.size();
            if(groupHeadings2.size() == 1) {
                student = classS.getGrus().get(firstGruInList).getMems().get(jList2.getSelectedIndex() - 1);
            } else {
                for(int i = 0; i < groupHeadings2.size(); i++) {
                    if(jList2.getSelectedIndex() > groupHeadings2.get(i) && i == groupHeadings2.size() - 1) {
                        student = classS.getGrus().get(firstGruInList + i).getMems().get(jList2.getSelectedIndex() - groupHeadings2.get(i) - 1);
                        break;
                    } else if(jList2.getSelectedIndex() < groupHeadings2.get(i)) {
                        student = classS.getGrus().get(firstGruInList + i - 1).getMems().get(jList2.getSelectedIndex() - groupHeadings2.get(i - 1) - 1);
                        break;
                    }
                }
            }
        } else {
            if(groupHeadings1.size() == 1) {
                student = classS.getGrus().get(0).getMems().get(jList1.getSelectedIndex() - 1);
            } else {
                for(int i = 1; i < groupHeadings1.size(); i++) {
                    if(jList1.getSelectedIndex() > groupHeadings1.get(i) && i == groupHeadings1.size() - 1) {
                        student = classS.getGrus().get(i).getMems().get(jList1.getSelectedIndex() - groupHeadings1.get(i) - 1);
                        break;
                    } else if(jList1.getSelectedIndex() < groupHeadings1.get(i)) {
                        student = classS.getGrus().get(i - 1).getMems().get(jList1.getSelectedIndex() - groupHeadings1.get(i - 1) - 1);
                        break;
                    }
                }
            }
        }
        
        for(int i = 0; i < classS.getGrus().size(); i++) {
            if(i < student.getGru()) {
                groupOptions[i] = i + 1;
            } else if(i > student.getGru()) {
                groupOptions[i - 1] = i + 1;
            }
        }
                
        Integer group = (Integer) javax.swing.JOptionPane.showInputDialog(null, "Choose a group to move the selected student to: ", "Edit Groups - Swap to Group - Group Selection Popup", JOptionPane.PLAIN_MESSAGE, null, groupOptions, groupOptions[0]);
        
        if(group == null) {
            if(jList1.isSelectionEmpty()) {
                jList2.requestFocusInWindow();
            } else {
                jList1.requestFocusInWindow();
            }
        } else {
            changesMade = true;
            okayB.setEnabled(true);
            group--;
            classS.getGrus().get(student.getGru()).remMem(student);
            classS.getGrus().get(group).addMem(student);
            addGruDataToLists();
        }
    }//GEN-LAST:event_addRemStuToGruBActionPerformed

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        if(changesMade) {
            retrieveOldData();
        }
        
        if(lastPage.getClass() == GruSorter.class || lastPage.getClass() == SortStusPerGru.class || (editing && lastPage.getClass() == GruSorterHome.class)) {
            if(changesMade) {
                addGruDataToLists();
            }
            setEditingModeEnabled(false);
        } else {
            baseFrame.setContentPane(lastPage);
        }
    }//GEN-LAST:event_lastPageBActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if(jList1.getSelectedIndex() == -1) {
            for(int i = 0; i < groupHeadings1.size(); i++) {
                if(jList1.getCellBounds(groupHeadings1.get(i), groupHeadings1.get(i)).contains(evt.getPoint())) {
                    swapStuToGruB.setEnabled(false);
                    addRemStuToGruB.setEnabled(false);
                    evt.consume();
                } else if(i > 0) {
                    if(jList1.getCellBounds(groupHeadings1.get(i) - 1, groupHeadings1.get(i) - 1).contains(evt.getPoint())) {
                        swapStuToGruB.setEnabled(false);
                        addRemStuToGruB.setEnabled(false);
                        evt.consume();
                    }
                } else if(i == groupHeadings1.size() - 1) {
                    swapStuToGruB.setEnabled(true);
                    addRemStuToGruB.setEnabled(true);
                }
            }
        } else {
            //occurs when we have gained focus on jList1 by clicking on an invalid item and then selected a valid item
            swapStuToGruB.setEnabled(true);
            addRemStuToGruB.setEnabled(true);
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        if(jList2.getSelectedIndex() == -1) {
            for(int i = 0; i < groupHeadings2.size(); i++) {
                if(jList2.getCellBounds(groupHeadings2.get(i), groupHeadings2.get(i)).contains(evt.getPoint())) {
                    swapStuToGruB.setEnabled(false);
                    addRemStuToGruB.setEnabled(false);
                    evt.consume();
                } else if(i > 0) {
                    if(jList2.getCellBounds(groupHeadings2.get(i) - 1, groupHeadings2.get(i) - 1).contains(evt.getPoint())) {
                        swapStuToGruB.setEnabled(false);
                        addRemStuToGruB.setEnabled(false);
                        evt.consume();
                    }
                } else if(i == groupHeadings2.size() - 1) {
                    swapStuToGruB.setEnabled(true);
                    addRemStuToGruB.setEnabled(true);
                }
            }
        } else {
            swapStuToGruB.setEnabled(true);
            addRemStuToGruB.setEnabled(true);
        }
    }//GEN-LAST:event_jList2MouseClicked

    private void editGroupsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editGroupsBActionPerformed
        setEditingModeEnabled(true);
    }//GEN-LAST:event_editGroupsBActionPerformed
    
    private void retrieveOldData() {
        File studentFile = app.getCurSxn().getStuF();
        try {
            Scanner scan = new Scanner(studentFile);
            int group;
            scan.nextLine();
            scan.nextLine();
            for(int i = 0; i < app.getCurSxn().getStus().size() * 6 && scan.hasNext(); i++) {
                if(i % 6 == 4) {
                    group = Integer.parseInt(scan.next());
                    app.getCurSxn().getStus().get(i / 6).setGru(group);
                    if(classS.getGrus().get(app.getCurSxn().getStus().get(i / 6).getGru()).getMems().size() > 0) {
                        classS.clearGrus();
                    }
                } else {
                    System.out.println(scan.next());
                }
            }
            scan.close();
            classS.loadGrus();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewAndEditGrus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGruDataToLists() {
        groupHeadings1 = new ArrayList<Integer>();
        groupHeadings2 = new ArrayList<Integer>();
        //classS.getGrus().size()
        //note: test the jlists with more students and see if the scrollbars work
        
        DefaultListModel model1 = new DefaultListModel();
        DefaultListModel model2 = new DefaultListModel();
        int data1L = 0, data2L = 0;
        for(int i = 0; i < classS.getGrus().size(); i++) {
            for(int j = 0; j < classS.getGrus().get(i).getMems().size(); j++) {
                if(i < Math.ceil(classS.getGrus().size() / 2.0)) {
                    data1L++;
                } else {
                    data2L++;
                }
            }
        }
        data1 = new String[data1L];
        data2 = new String[data2L];
        for(int i = 0, tally = 0; i < classS.getGrus().size(); i++) {
            if(i < Math.ceil(classS.getGrus().size() / 2.0)) { //was (i % 2 == 0) - every odd group (if we go by array indices)
                model1.add(model1.getSize(), "Group " + (i + 1));
                groupHeadings1.add(tally);
                tally++;
                for(int j = 0; j < classS.getGrus().get(i).getMems().size(); j++) {
                    data1[j] = classS.getGrus().get(i).getMems().get(j).getFuName();
                    model1.add(model1.getSize(), data1[j]);
                    tally++;
                }
                if(i + 1 == Math.ceil(classS.getGrus().size() / 2.0)) {
                    tally = 0;
                } else {
                    model1.add(model1.getSize(), " ");
                    tally++;
                }
            } else {
                model2.add(model2.getSize(), "Group " + (i + 1));
                groupHeadings2.add(tally);
                tally++;
                for(int j = 0; j < classS.getGrus().get(i).getMems().size(); j++) {
                    data2[j] = classS.getGrus().get(i).getMems().get(j).getFuName();
                    model2.add(model2.getSize(), data2[j]);
                    tally++;
                }
                if(i + 1 != classS.getGrus().size()) {
                    model2.add(model2.getSize(), " ");
                    tally++;
                }
            }
        }
        System.out.println("groupHeadings1: " + groupHeadings1.toString());
        System.out.println("groupHeadings2: " + groupHeadings2.toString());
        jList1.setModel(model1);
        jList2.setModel(model2);
    }
    
    private class GroupSelectionModel1 extends DefaultListSelectionModel {
        @Override
        public void setAnchorSelectionIndex(final int anchorIndex) {
            if(isValidIndex(anchorIndex)) {
                super.setAnchorSelectionIndex(anchorIndex);
            }
        }

        @Override
        public void setLeadSelectionIndex(final int leadIndex) {
            if(isValidIndex(leadIndex)) {
                super.setLeadSelectionIndex(leadIndex);
            }
        }

        @Override
        public void setSelectionInterval(final int index0, final int index1) {
            if(isValidIndex(index0) && isValidIndex(index1)) {
                super.setSelectionInterval(index0, index1);
            }
        }
        
        private boolean isValidIndex(int index) {
            for(int i = 0; i < groupHeadings1.size(); i++) {
                if(index == groupHeadings1.get(i) || index == groupHeadings1.get(i) - 1) {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    private class GroupSelectionModel2 extends DefaultListSelectionModel {
        @Override
        public void setAnchorSelectionIndex(final int anchorIndex) {
            if(isValidIndex(anchorIndex)) {
                super.setAnchorSelectionIndex(anchorIndex);
                //System.out.println("List2 Selection at: " + anchorIndex);
            }
        }

        @Override
        public void setLeadSelectionIndex(final int leadIndex) {
            if(isValidIndex(leadIndex)) {
                super.setLeadSelectionIndex(leadIndex);
                //System.out.println("List2 Selection at: " + leadIndex);
            }
        }

        @Override
        public void setSelectionInterval(final int index0, final int index1) {
            if(isValidIndex(index0) && isValidIndex(index1)) {
                super.setSelectionInterval(index0, index1);
                //System.out.println("List2 Selection from " + index0 + " to " + index1);
            }
        }
        
        private boolean isValidIndex(int index) {
            for(int i = 0; i < groupHeadings2.size(); i++) {
                if(index == groupHeadings2.get(i) || index == groupHeadings2.get(i) - 1) {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    private class NoSelectionModel extends DefaultListSelectionModel {
        @Override
        public void setAnchorSelectionIndex(final int anchorIndex) {}

        @Override
        public void setLeadAnchorNotificationEnabled(final boolean flag) {}

        @Override
        public void setLeadSelectionIndex(final int leadIndex) {}

        @Override
        public void setSelectionInterval(final int index0, final int index1) { }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRemStuToGruB;
    private java.awt.Button editGroupsB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton okayB;
    private javax.swing.JButton swapStuToGruB;
    // End of variables declaration//GEN-END:variables

}
