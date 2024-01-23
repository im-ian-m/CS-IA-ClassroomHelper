package classroom.helper;

/**
 *
 * @author ianmoore
 */
public class GruSorterHome extends javax.swing.JPanel {

    private ClassroomHelper app;
    private ClassS classS;
    private ClassHome homepage;
    private GUIFrame baseFrame;
    public GruSorterHome(ClassroomHelper appl, ClassS cla, ClassHome home, GUIFrame frame) {
        app = appl;
        classS = cla;
        homepage = home;
        baseFrame = frame;
        baseFrame.setContentPane(this);
        initComponents();
        setViewGrusBState();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createGrusB = new javax.swing.JButton();
        viewGrusB = new javax.swing.JButton();
        lastPageB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        createGrusB.setText("Create New Groups");
        createGrusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGrusBActionPerformed(evt);
            }
        });

        viewGrusB.setText("View Existing Groups");
        viewGrusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGrusBActionPerformed(evt);
            }
        });

        lastPageB.setText("Back");
        lastPageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 25)); // NOI18N
        jLabel1.setText("Group Sorter Homepage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createGrusB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewGrusB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(304, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lastPageB)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lastPageB))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)))
                .addGap(131, 131, 131)
                .addComponent(createGrusB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewGrusB)
                .addContainerGap(199, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createGrusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGrusBActionPerformed
        new GruSorter(app, classS, homepage, this, baseFrame);
    }//GEN-LAST:event_createGrusBActionPerformed

    private void viewGrusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewGrusBActionPerformed
        new ViewAndEditGrus(app, classS, homepage, this, baseFrame, false);
    }//GEN-LAST:event_viewGrusBActionPerformed

    private void lastPageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBActionPerformed
        baseFrame.setContentPane(homepage);
    }//GEN-LAST:event_lastPageBActionPerformed

    //updates the enabled/disabled state of the viewGroups button
    public void setViewGrusBState() {
        viewGrusB.setEnabled(classS.getGrus().size() != 0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createGrusB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton lastPageB;
    private javax.swing.JButton viewGrusB;
    // End of variables declaration//GEN-END:variables

}
