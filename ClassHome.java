package classroom.helper;

/**
 *
 * @author ianmoore
 */
public class ClassHome extends javax.swing.JPanel {

    private final ClassroomHelper app;
    private final StartPage startPage;
    private final GUIFrame baseFrame;
    private ClassS classS;
    private int SAQTally;
    private int ERQTally;
    
    public ClassHome(ClassroomHelper appl, ClassS cla, StartPage start, GUIFrame frame) {
        app = appl;
        baseFrame = frame;
        startPage = start;
        classS = cla;
        baseFrame.setContentPane(this);
        initComponents();
        blockProChooser();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gruSortB = new javax.swing.JButton();
        stuPickerB = new javax.swing.JButton();
        proChooserB = new javax.swing.JButton();
        manDataB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        gruSortB.setText("Group Sorter");
        gruSortB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gruSortBActionPerformed(evt);
            }
        });

        stuPickerB.setText("Student Picker");
        stuPickerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stuPickerBActionPerformed(evt);
            }
        });

        proChooserB.setText("Prompt Chooser");
        proChooserB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proChooserBActionPerformed(evt);
            }
        });

        manDataB.setText("Manage Data");
        manDataB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manDataBActionPerformed(evt);
            }
        });

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("Class Homepage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manDataB)
                    .addComponent(stuPickerB)
                    .addComponent(gruSortB)
                    .addComponent(proChooserB))
                .addContainerGap(319, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lastPageB)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastPageB)
                    .addComponent(jLabel1))
                .addGap(107, 107, 107)
                .addComponent(gruSortB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stuPickerB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proChooserB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manDataB)
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void gruSortBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gruSortBActionPerformed
        new GruSorterHome(app, classS, this, baseFrame);
    }//GEN-LAST:event_gruSortBActionPerformed

    private void stuPickerBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stuPickerBActionPerformed
        new StudentSelector(app, classS, this, baseFrame);
    }//GEN-LAST:event_stuPickerBActionPerformed

    private void proChooserBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proChooserBActionPerformed
        new PromptChooserHome(app, classS, this, baseFrame, SAQTally, ERQTally);
    }//GEN-LAST:event_proChooserBActionPerformed

    private void manDataBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manDataBActionPerformed
        new ManageDataHome(app, classS, this, baseFrame);
    }//GEN-LAST:event_manDataBActionPerformed

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        baseFrame.setContentPane(startPage);
    }//GEN-LAST:event_lastPageBActionPerformed

    //disables the prompt chooser button if not enough promts of either type exist
    public void blockProChooser() {
        ERQTally = 0;
        if(classS.getLvl().equals("SL")) {
            SAQTally = 0;
            for(int i = 0; i < classS.getSAQs().size(); i++) {
                if(!classS.getSAQs().get(i).getIsAns()) {
                    SAQTally++;
                }
            }
        }
        for(int i = 0; i < classS.getERQs().size(); i++) {
            if(!classS.getERQs().get(i).getIsAns()) {
                ERQTally++;
            }
        }
        if(ERQTally < 1 && SAQTally < 1) {
            proChooserB.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gruSortB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton manDataB;
    private javax.swing.JButton proChooserB;
    private javax.swing.JButton stuPickerB;
    // End of variables declaration//GEN-END:variables
}
