package classroom.helper;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ianmoore
 */
public class GruSorter extends javax.swing.JPanel {

    private ClassroomHelper app;
    private ClassS classS;
    private ClassHome homepage;
    private JPanel lastPage;
    private GUIFrame baseFrame;
    private KeyAdapter keyPressEvtLi;
    private IntFormatter intFormatter;
    
    public GruSorter(ClassroomHelper appl, ClassS cla, ClassHome home, JPanel last, GUIFrame frame) {
        app = appl;
        classS = cla;
        homepage = home;
        lastPage = last;
        baseFrame = frame;
        baseFrame.setContentPane(this);
        initComponents();
        useNoOfGrusB.setEnabled(false);
        numEntryF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            //prevents the user from entering invalid values into the text field and updates which buttons can be pressed based on the contents
            public void warn() {
                if(!numEntryF.getText().equals("")) {
                    if(Integer.parseInt(numEntryF.getText()) == 0 || Integer.parseInt(numEntryF.getText()) > cla.getPresStus().size()){
                        intFormatter.setInvalidInput();
                        useNoOfGrusB.setEnabled(false);
                        setStusPerGruB.setEnabled(false);
                    } else {
                        useNoOfGrusB.setEnabled(true);
                        setStusPerGruB.setEnabled(false);
                    }
                } else {
                    useNoOfGrusB.setEnabled(false);
                    setStusPerGruB.setEnabled(true);
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numEntryF = new javax.swing.JFormattedTextField(intFormatter = new IntFormatter());
        jLabel3 = new javax.swing.JLabel();
        useNoOfGrusB = new javax.swing.JButton();
        setStusPerGruB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("Create New Groups");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setText("<html>Enter the number of groups to be created in the box below or leave it blank to create groups based on the number of students per group instead.</html>");

        numEntryF.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        numEntryF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numEntryFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                numEntryFFocusLost(evt);
            }
        });

        jLabel3.setText("Number of groups:");

        useNoOfGrusB.setText("Proceed using equal-sized groups");
        useNoOfGrusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useNoOfGrusBActionPerformed(evt);
            }
        });

        setStusPerGruB.setText("Set groups by number of students per group");
        setStusPerGruB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setStusPerGruBActionPerformed(evt);
            }
        });

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(numEntryF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useNoOfGrusB)
                    .addComponent(setStusPerGruB))
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lastPageB))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lastPageB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numEntryF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(useNoOfGrusB)
                .addGap(31, 31, 31)
                .addComponent(setStusPerGruB)
                .addContainerGap(182, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useNoOfGrusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useNoOfGrusBActionPerformed
        classS.createNewGrus(false, Integer.parseInt(numEntryF.getText()));
        new ViewAndEditGrus(app, classS, homepage, this, baseFrame, false);
    }//GEN-LAST:event_useNoOfGrusBActionPerformed

    private void setStusPerGruBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setStusPerGruBActionPerformed
        new SortStusPerGru(app, classS, homepage, this, baseFrame);
    }//GEN-LAST:event_setStusPerGruBActionPerformed

    private void numEntryFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numEntryFFocusGained
        numEntryF.addKeyListener(keyPressEvtLi = new java.awt.event.KeyAdapter() {
            //prevents the user from typing non-number characters into the text field
            @Override
            public void keyTyped(java.awt.event.KeyEvent ev) {
                if(Character.isDigit(ev.getKeyChar()) || ev.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if(numEntryF.getText().length() != 0) {
                        if(numEntryF.getText().charAt(0) == '0') {
                            numEntryF.setValue("");
                        } else if(Integer.parseInt(numEntryF.getText()) > classS.getPresStus().size()) {
                            ev.consume();
                        }
                    }
                } else {
                    ev.consume();
                }
                
            }
        });
    }//GEN-LAST:event_numEntryFFocusGained

    private void numEntryFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numEntryFFocusLost
        numEntryF.removeKeyListener(keyPressEvtLi);
        if(evt.getOppositeComponent() == useNoOfGrusB) {
            useNoOfGrusB.doClick();
        }
    }//GEN-LAST:event_numEntryFFocusLost

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        baseFrame.setContentPane(lastPage);
    }//GEN-LAST:event_lastPageBActionPerformed

    static class IntFormatter extends JFormattedTextField.AbstractFormatter {

        @Override
        public Object stringToValue(String text) throws ParseException {        
            return text;
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            return (String) value;
        }
        
        //triggers user feedback behaviour if they enter invalid input
        public void setInvalidInput() {
            this.invalidEdit();
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton lastPageB;
    private javax.swing.JFormattedTextField numEntryF;
    private javax.swing.JButton setStusPerGruB;
    private javax.swing.JButton useNoOfGrusB;
    // End of variables declaration//GEN-END:variables
}
