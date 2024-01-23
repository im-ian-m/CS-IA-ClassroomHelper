package classroom.helper;

/**
 *
 * @author ianmoore
 */
public class ManageDataHome extends javax.swing.JPanel {

    ClassroomHelper app;
    ClassS classS;
    private ClassHome homepage;
    private GUIFrame baseFrame;
    
    public ManageDataHome(ClassroomHelper appl, ClassS cla, ClassHome home, GUIFrame frame) {
        app = appl;
        classS = cla;
        homepage = home;
        baseFrame = frame;
        baseFrame.setContentPane(this);
        initComponents();
        if(app.getCurSxn().getStus().size() == 1) {
            manStuParDataB.setEnabled(false);
        }
        if(classS.getLvl().equals("HL")) {
            setProStatusesB2.setVisible(false);
            setProStatusesB1.setText("Set HL ERQ Statuses");
        }
        setPromptPageButtonStates();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        markAbsStusB = new javax.swing.JButton();
        manStuParDataB = new javax.swing.JButton();
        setProStatusesB1 = new javax.swing.JButton();
        manGrusB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();
        setProStatusesB2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        markAbsStusB.setText("Mark Student Attendance");
        markAbsStusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markAbsStusBActionPerformed(evt);
            }
        });

        manStuParDataB.setText("Manage Student Partner Settings");
        manStuParDataB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manStuParDataBActionPerformed(evt);
            }
        });

        setProStatusesB1.setText("Set SL SAQ Statuses");
        setProStatusesB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setProStatusesB1ActionPerformed(evt);
            }
        });

        manGrusB.setText("Manage Groups");
        manGrusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manGrusBActionPerformed(evt);
            }
        });

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        setProStatusesB2.setText("Set SL ERQ Statuses");
        setProStatusesB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setProStatusesB2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("Manage Data Homepage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manGrusB)
                    .addComponent(setProStatusesB1)
                    .addComponent(manStuParDataB)
                    .addComponent(markAbsStusB)
                    .addComponent(setProStatusesB2))
                .addGap(348, 348, 348))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lastPageB)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastPageB)
                    .addComponent(jLabel1))
                .addGap(71, 71, 71)
                .addComponent(markAbsStusB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manStuParDataB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manGrusB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setProStatusesB1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setProStatusesB2)
                .addContainerGap(159, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manStuParDataBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manStuParDataBActionPerformed
        new ManagePartnerBlists(app, classS, homepage, this, baseFrame);
    }//GEN-LAST:event_manStuParDataBActionPerformed

    private void markAbsStusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markAbsStusBActionPerformed
        new MarkStuAttendance(app, classS, homepage, this, baseFrame);
    }//GEN-LAST:event_markAbsStusBActionPerformed

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        baseFrame.setContentPane(homepage);
    }//GEN-LAST:event_lastPageBActionPerformed

    private void manGrusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manGrusBActionPerformed
        new ViewAndEditGrus(app, classS, homepage, this, baseFrame, true);
    }//GEN-LAST:event_manGrusBActionPerformed

    private void setProStatusesB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setProStatusesB1ActionPerformed
        if(classS.getLvl().equals("SL")) {
            new SetPromptStatuses(app, classS, homepage, this, baseFrame, "SAQ");
        } else {
            new SetPromptStatuses(app, classS, homepage, this, baseFrame, "ERQ");
        }
    }//GEN-LAST:event_setProStatusesB1ActionPerformed

    private void setProStatusesB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setProStatusesB2ActionPerformed
        new SetPromptStatuses(app, classS, homepage, this, baseFrame, "ERQ");
    }//GEN-LAST:event_setProStatusesB2ActionPerformed

    //updates the enabled/disabled state of the setProStatuses button depending on whether prompts exist
    public void setPromptPageButtonStates() {
        if(classS.getLvl().equals("HL")) {
            if(classS.getERQs().size() == 0) {
                setProStatusesB1.setEnabled(false);
            }
        } else {
            if(classS.getSAQs().size() == 0) {
                setProStatusesB1.setEnabled(false);
            }
            if(classS.getERQs().size() == 0) {
                setProStatusesB2.setEnabled(false);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton manGrusB;
    private javax.swing.JButton manStuParDataB;
    private javax.swing.JButton markAbsStusB;
    private javax.swing.JButton setProStatusesB1;
    private javax.swing.JButton setProStatusesB2;
    // End of variables declaration//GEN-END:variables
}
