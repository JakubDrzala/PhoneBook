import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*


            !!!!!! 218 - EDIT   !!!!!!
            !!!!!  223 - delete !!!!!!


*/


public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton addNewButton;
    private JComboBox comboBoxLaunguage;
    public JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel popUpAddNew;
    private JButton searchShowButton;
    private JButton confirmToAddButton;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JPanel popUpSearch;
    private JPanel menuPanel;
    private JScrollPane dataPanel;
    private JLabel launguageLabel;
    private JLabel titleLabel;
    private JButton findButton;
    private JButton ADD;
    private Object[][] data = {
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
            {1, "name1", "secondname1", 12321, "ewq@eqw.qw", "edit", "delete"},
            {2, "name2", "secondname2", 65454, "kjl@eqw.qw", "edit", "delete"},
            {3, "name3", "secondname3", 97897, "asdas@eqw.qw", "edit", "delete"},
    };
    private Object[] columnNames = {"id", "name", "secondname", "phone", "email", "edit", "delete"};


    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("test");
        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        table1.getColumn("edit").setCellRenderer(new ButtonRenderer());
        table1.getColumn("edit").setCellEditor(new ButtonEditor(new JCheckBox(), table1, "edit"));
        table1.getColumn("delete").setCellRenderer(new ButtonRenderer());
        table1.getColumn("delete").setCellEditor(new ButtonEditor(new JCheckBox(), table1, "delete"));
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
        table1.getColumnModel().getColumn(0).setMaxWidth(40);
        table1.getColumnModel().getColumn(1).setPreferredWidth(120);
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);
        table1.getColumnModel().getColumn(3).setPreferredWidth(120);
        table1.getColumnModel().getColumn(4).setPreferredWidth(160);
        table1.getColumnModel().getColumn(5).setMaxWidth(80);
        table1.getColumnModel().getColumn(6).setMaxWidth(80);
        popUpAddNew.setVisible(false);
        popUpSearch.setVisible(false);
        comboBoxLaunguage.addItem("English");
        comboBoxLaunguage.addItem("Deutsch");


        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (popUpAddNew.isVisible()) {
                    popUpAddNew.setVisible(false);
                    addNewButton.setText("Add new");
                } else {
                    popUpAddNew.setVisible(true);
                    addNewButton.setText("Cancel");
                }
            }
        });
        searchShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (popUpSearch.isVisible()) {
                    popUpSearch.setVisible(false);
                    searchShowButton.setText("Search");
                } else {
                    popUpSearch.setVisible(true);
                    searchShowButton.setText("Hide");
                }
            }
        });
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }


    public static void main(String[] args) {
        new MainFrame();
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMaximumSize(new Dimension(10000, 2147483647));
        mainPanel.setMinimumSize(new Dimension(550, 550));
        mainPanel.setPreferredSize(new Dimension(550, 550));
        menuPanel = new JPanel();
        menuPanel.setLayout(new FormLayout("fill:100px:grow,left:4dlu:noGrow,fill:120px:noGrow,left:4dlu:noGrow,fill:p:grow,left:4dlu:noGrow,fill:85px:noGrow,left:4dlu:noGrow,fill:p:grow,left:4dlu:noGrow,fill:82px:noGrow,fill:30px:noGrow,fill:85px:noGrow,left:4dlu:noGrow,fill:95px:noGrow", "center:30px:noGrow,top:4dlu:noGrow,center:max(p;150px):noGrow,top:4dlu:noGrow,center:max(p;4px):noGrow"));
        mainPanel.add(menuPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addNewButton = new JButton();
        addNewButton.setText("Add new");
        CellConstraints cc = new CellConstraints();
        menuPanel.add(addNewButton, cc.xy(1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
        launguageLabel = new JLabel();
        launguageLabel.setText("Launguage:");
        menuPanel.add(launguageLabel, cc.xy(13, 1));
        comboBoxLaunguage = new JComboBox();
        menuPanel.add(comboBoxLaunguage, cc.xy(15, 1));
        titleLabel = new JLabel();
        titleLabel.setText("Phone Book");
        menuPanel.add(titleLabel, cc.xy(7, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final Spacer spacer1 = new Spacer();
        menuPanel.add(spacer1, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        popUpAddNew = new JPanel();
        popUpAddNew.setLayout(new GridLayoutManager(5, 2, new Insets(0, 5, 0, 0), -1, -1));
        menuPanel.add(popUpAddNew, cc.xywh(1, 3, 3, 3, CellConstraints.LEFT, CellConstraints.TOP));
        final JLabel label1 = new JLabel();
        label1.setText("Name:");
        popUpAddNew.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Surname:");
        popUpAddNew.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        popUpAddNew.add(textField1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        popUpAddNew.add(textField2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Number:");
        popUpAddNew.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        popUpAddNew.add(textField3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("e-mail:");
        popUpAddNew.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField4 = new JTextField();
        popUpAddNew.add(textField4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        confirmToAddButton = new JButton();
        confirmToAddButton.setText("Confirm");
        popUpAddNew.add(confirmToAddButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchShowButton = new JButton();
        searchShowButton.setText("Search");
        menuPanel.add(searchShowButton, cc.xy(11, 5, CellConstraints.RIGHT, CellConstraints.BOTTOM));
        final Spacer spacer2 = new Spacer();
        menuPanel.add(spacer2, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer3 = new Spacer();
        menuPanel.add(spacer3, cc.xy(11, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
        popUpSearch = new JPanel();
        popUpSearch.setLayout(new GridLayoutManager(5, 2, new Insets(0, 5, 0, 0), -1, -1));
        menuPanel.add(popUpSearch, cc.xywh(12, 3, 4, 3, CellConstraints.RIGHT, CellConstraints.BOTTOM));
        final JLabel label5 = new JLabel();
        label5.setText("e-mail");
        popUpSearch.add(label5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField5 = new JTextField();
        popUpSearch.add(textField5, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Number");
        popUpSearch.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Surname");
        popUpSearch.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Name:");
        popUpSearch.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField6 = new JTextField();
        popUpSearch.add(textField6, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField7 = new JTextField();
        popUpSearch.add(textField7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField8 = new JTextField();
        popUpSearch.add(textField8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        findButton = new JButton();
        findButton.setText("Find");
        popUpSearch.add(findButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        menuPanel.add(spacer4, cc.xy(9, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer5 = new Spacer();
        menuPanel.add(spacer5, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        dataPanel = new JScrollPane();
        mainPanel.add(dataPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable(data,columnNames);
        dataPanel.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable tableData;
    private String actionType;

    public ButtonEditor(JCheckBox checkBox,JTable table, String action) {
        super(checkBox);
        tableData = table;
        actionType = action;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            switch (actionType){
                case "edit":
                    edit();
                    break;
                case "delete":
                    delete();
                    break;
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    public void edit(){
        JOptionPane.showMessageDialog(button, "edit - " + tableData.getSelectedRow());
        // data.edit(tableData.getSelectedRow()) -- odw do metody do innej klassy i metody edit(int indexOfRow)
    }

    public void delete(){
        JOptionPane.showMessageDialog(button, "delete - " + tableData.getSelectedRow());
        // data.delete(tableData.getSelectedRow()) -- odw do metody do innej klassy i metody delete(int indexOfRow)
    }
}