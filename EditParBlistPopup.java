package classroom.helper;

import java.util.ArrayList;

/**
 *
 * @author ianmoore
 */
public class EditParBlistPopup extends javax.swing.JDialog {

    private ClassroomHelper app;
    private ClassS classS;
    private int stuSel;
    boolean add;
    private ArrayList<Student> options;
    private String[] ops;
    private ManagePartnerBlists mainPanel;
    
    public EditParBlistPopup(ClassroomHelper appl, ClassS cla, java.awt.Frame parent, boolean modal, ManagePartnerBlists mainP, boolean addStu, int selectedStu) {
        super(parent, modal);
        app = appl;
        classS = cla;
        stuSel = selectedStu;
        add = addStu;
        mainPanel = mainP;
        addOpsToComboBox();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        doneB = new javax.swing.JButton();
        cancelB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(ops));

        doneB.setText("Done");
        doneB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneBActionPerformed(evt);
            }
        });

        cancelB.setText("Cancel");
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText((add ? "Add Student to" : "Remove Student from") + " Partner Blacklist - Student Selection");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(doneB))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1)))
                        .addGap(0, 231, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneB)
                    .addComponent(cancelB))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doneBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneBActionPerformed
        if(add) {
            app.getCurSxn().getStus().get(stuSel).addToParBlist(options.get(jComboBox1.getSelectedIndex()));
        } else {
            app.getCurSxn().getStus().get(stuSel).remFromParBlist(options.get(jComboBox1.getSelectedIndex()));
        }
        mainPanel.set2ndStuToUpdate(options.get(jComboBox1.getSelectedIndex()));
        this.dispose();
    }//GEN-LAST:event_doneBActionPerformed

    private void cancelBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBActionPerformed

    //adds options to the combo box for the user to select from
    private void addOpsToComboBox() {
        if(add) {
            options = new ArrayList<Student>();
            ops = new String[app.getCurSxn().getStus().size() - app.getCurSxn().getStus().get(stuSel).getParBlist().size() - 1];
            for(int i = 0; i < app.getCurSxn().getStus().size(); i++) {
                if(i == stuSel) {
                    continue;
                } else if(app.getCurSxn().getStus().get(stuSel).getParBlist().size() == 0){
                    options.add(app.getCurSxn().getStus().get(i));
                    ops[options.size() - 1] = app.getCurSxn().getStus().get(i).getFuName();
                }
                for(int j = 0; j < app.getCurSxn().getStus().get(stuSel).getParBlist().size(); j++) {
                    if(app.getCurSxn().getStus().get(i) == app.getCurSxn().getStus().get(stuSel).getParBlist().get(j)) {
                        break;
                    } else if(j == app.getCurSxn().getStus().get(stuSel).getParBlist().size() - 1) {
                        //the student at i is not already on the selected students partner blist
                        options.add(app.getCurSxn().getStus().get(i));
                        ops[options.size() - 1] = app.getCurSxn().getStus().get(i).getFuName();
                    }
                }
            }
        } else {
            options = new ArrayList<Student>();
            ops = new String[app.getCurSxn().getStus().get(stuSel).getParBlist().size()];
            for(int i = 0; i <  app.getCurSxn().getStus().get(stuSel).getParBlist().size(); i++) {
                options.add(app.getCurSxn().getStus().get(stuSel).getParBlist().get(i));
                ops[i] = options.get(i).getFuName();
            }
        }
        
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelB;
    private javax.swing.JButton doneB;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
