/*============================================================================*
 * WARNING      This class contains automatically modified code.      WARNING *
 *                                                                            *
 * The method initComponents() and the variable declarations between the      *
 * "// Variables declaration - do not modify" and                             *
 * "// End of variables declaration" comments will be overwritten if modified *
 * by hand. Using the NetBeans IDE to edit this file is strongly recommended. *
 *                                                                            *
 * See http://jmri.org/help/en/html/doc/Technical/NetBeansGUIEditor.shtml for *
 * more information.                                                          *
 *============================================================================*/
package jmri.profile;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jmri.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Add a new {@link Profile} to JMRI.
 *
 * @author Randall Wood
 */
public class AddProfileDialog extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = -2838864019793309792L;
    private String profileId;
    private boolean setNextProfile = false;
    private Profile source = null;

    public AddProfileDialog(java.awt.Frame parent, boolean modal, boolean setNextProfile) {
        super(parent, modal);
        this.setNextProfile = setNextProfile;
        initComponents();
    }

    AddProfileDialog(Dialog parent, boolean modal, boolean setNextProfile) {
        super(parent, modal);
        this.setNextProfile = setNextProfile;
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

        lblProfileNameAndLocation = new JLabel();
        jSeparator2 = new JSeparator();
        profileName = new JTextField();
        lblProfileName = new JLabel();
        lblProfileLocation = new JLabel();
        profileLocation = new JTextField();
        profileFolder = new JTextField();
        btnBrowse = new JButton();
        lblProfileFolder = new JLabel();
        jSeparator1 = new JSeparator();
        btnCancel = new JButton();
        btnOk = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ResourceBundle bundle = ResourceBundle.getBundle("jmri/profile/Bundle"); // NOI18N
        setTitle(bundle.getString("AddProfileDialog.title")); // NOI18N
        setMinimumSize(new Dimension(413, 239));

        lblProfileNameAndLocation.setFont(lblProfileNameAndLocation.getFont().deriveFont(lblProfileNameAndLocation.getFont().getStyle() | Font.BOLD));
        lblProfileNameAndLocation.setText(bundle.getString("AddProfileDialog.lblProfileNameAndLocation.text")); // NOI18N

        profileName.setText("");
        profileName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                profileNameActionPerformed(null);
            }
            public void insertUpdate(DocumentEvent e) {
                profileNameActionPerformed(null);
            }
            public void removeUpdate(DocumentEvent e) {
                profileNameActionPerformed(null);
            }
        });
        profileName.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                profileNameFocusLost(evt);
            }
        });
        profileName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                profileNameActionPerformed(evt);
            }
        });
        profileName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                profileNameKeyTyped(evt);
            }
        });

        lblProfileName.setText(bundle.getString("AddProfileDialog.lblProfileName.text")); // NOI18N

        lblProfileLocation.setText(bundle.getString("AddProfileDialog.lblProfileLocation.text")); // NOI18N

        profileLocation.setText(ProfileManager.getDefault().getDefaultSearchPath().getPath());
        profileLocation.setMinimumSize(new Dimension(14, 128));
        profileLocation.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                profileLocationFocusLost(evt);
            }
        });
        profileLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                profileLocationActionPerformed(evt);
            }
        });
        profileLocation.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                profileLocationKeyTyped(evt);
            }
        });

        profileFolder.setText(ProfileManager.getDefault().getDefaultSearchPath().getPath());
        profileFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                profileFolderActionPerformed(evt);
            }
        });

        btnBrowse.setText(bundle.getString("AddProfileDialog.btnBrowse.text")); // NOI18N
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        lblProfileFolder.setText(bundle.getString("AddProfileDialog.lblProfileFolder.text")); // NOI18N

        btnCancel.setText(bundle.getString("AddProfileDialog.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOk.setText(bundle.getString("AddProfileDialog.btnOk.text")); // NOI18N
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProfileName)
                                .addGap(24, 24, 24)
                                .addComponent(profileName, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProfileLocation)
                                    .addComponent(lblProfileFolder))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(profileFolder)
                                    .addComponent(profileLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowse))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProfileNameAndLocation)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProfileNameAndLocation)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(profileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProfileName))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(profileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse)
                    .addComponent(lblProfileLocation))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(profileFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProfileFolder))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileNameActionPerformed(ActionEvent evt) {//GEN-FIRST:event_profileNameActionPerformed
        String location = this.profileLocation.getText();
        if (!location.endsWith(File.separator)) {
            location = location + File.separator;
        }
        this.profileId = FileUtil.sanitizeFilename(this.profileName.getText());
        this.profileFolder.setText(location + this.profileId);
    }//GEN-LAST:event_profileNameActionPerformed

    private void btnBrowseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        JFileChooser chooser = new JFileChooser(this.profileLocation.getText());
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // TODO: Use NetBeans OpenDialog if its availble
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                this.profileLocation.setText(chooser.getSelectedFile().getCanonicalPath());
                this.profileNameActionPerformed(evt);
            } catch (IOException ex) {
                log.error("Error selecting profile location", ex);
            }
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void profileFolderActionPerformed(ActionEvent evt) {//GEN-FIRST:event_profileFolderActionPerformed
        this.profileNameActionPerformed(evt);
    }//GEN-LAST:event_profileFolderActionPerformed

    private void profileLocationActionPerformed(ActionEvent evt) {//GEN-FIRST:event_profileLocationActionPerformed
        this.profileNameActionPerformed(evt);
    }//GEN-LAST:event_profileLocationActionPerformed

    private void btnCancelActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOkActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Profile p = new Profile(this.profileName.getText(), this.profileId, new File(this.profileFolder.getText()));
            ProfileManager.getDefault().addProfile(p);
            if (this.source != null) {
                // TODO: if source is active profile, save source first
                FileUtil.copy(source.getPath(), p.getPath());
                p.save();
            }
            if (this.setNextProfile) {
                ProfileManager.getDefault().setNextActiveProfile(p);
            } else {
                ProfileManager.getDefault().setActiveProfile(p);
            }
            ProfileManager.getDefault().saveActiveProfile(p, ProfileManager.getDefault().isAutoStartActiveProfile());
            if (this.source != null) {
                log.info("Created profile \"{}\" by copying profile \"{}\"", p.getName(), this.source.getName());
            } else {
                log.info("Created new profile \"{}\"", p.getName());
            }
            this.setCursor(Cursor.getDefaultCursor());
            this.dispose();
        } catch (IOException | IllegalArgumentException ex) {
            this.setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error Creating Profile", JOptionPane.ERROR_MESSAGE);
            log.error("Error saving profile", ex);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void profileNameKeyTyped(KeyEvent evt) {//GEN-FIRST:event_profileNameKeyTyped
        this.profileNameActionPerformed(null);
    }//GEN-LAST:event_profileNameKeyTyped

    private void profileNameFocusLost(FocusEvent evt) {//GEN-FIRST:event_profileNameFocusLost
        this.profileNameActionPerformed(null);
    }//GEN-LAST:event_profileNameFocusLost

    private void profileLocationKeyTyped(KeyEvent evt) {//GEN-FIRST:event_profileLocationKeyTyped
        this.profileNameActionPerformed(null);
    }//GEN-LAST:event_profileLocationKeyTyped

    private void profileLocationFocusLost(FocusEvent evt) {//GEN-FIRST:event_profileLocationFocusLost
        this.profileNameActionPerformed(null);
    }//GEN-LAST:event_profileLocationFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnBrowse;
    private JButton btnCancel;
    private JButton btnOk;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JLabel lblProfileFolder;
    private JLabel lblProfileLocation;
    private JLabel lblProfileName;
    private JLabel lblProfileNameAndLocation;
    private JTextField profileFolder;
    private JTextField profileLocation;
    private JTextField profileName;
    // End of variables declaration//GEN-END:variables
    private static final Logger log = LoggerFactory.getLogger(AddProfileDialog.class);

    void setSourceProfile(Profile profile) {
        this.source = profile;
        this.setTitle(Bundle.getMessage("AddProfileDialog.copyTitle"));
    }
}
