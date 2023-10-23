import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton addNewButton;
    private JComboBox comboBoxLaunguage;
    public JTable table1;
    private JTextField nameAddInput;
    private JTextField surnameAddInput;
    private JTextField numberAddInput;
    private JTextField emailAddInput;
    private JPanel popUpAddNew;
    private JButton searchShowButton;
    private JButton confirmToAddButton;
    private JTextField emailSearchInput;
    private JTextField numberSearchInput;
    private JTextField surnameSearchInput;
    private JTextField nameSearchInput;
    private JPanel popUpSearch;
    private JPanel menuPanel;
    private JScrollPane dataPanel;
    private JLabel launguageLabel;
    private JLabel titleLabel;
    private JButton findButton;
    private JLabel name1;
    private JButton ADD;
    private static ResourceBundle resourceBundle;
    private Object[][] tableData;
    private String[] columnNames = {"id", "Name", "Surname", "Phone Number", "e-mail", "edit", "delete"};
    private Object[] sortBy = {0, "ascending"};

    private JLabel add_name;
    private JLabel add_surname;
    private JLabel add_number;
    private JLabel add_email;
    private JLabel search_email;
    private JLabel search_number;
    private JLabel search_surname;
    private JLabel search_name;


    public MainFrame() {
        //main settings
        setContentPane(mainPanel);
        setTitle("test");
        setSize(750, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //table settings
        getData();
        updateTable();

        //sort
        table1.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTableHeader header = (JTableHeader) e.getSource();
                Integer column = header.columnAtPoint(e.getPoint());
                if (column < 5)
                    sort(column);
            }
        });

        popUpAddNew.setVisible(false);
        popUpSearch.setVisible(false);

        //launguage settings
        comboBoxLaunguage.addItem(Locale.US);
        comboBoxLaunguage.addItem(Locale.GERMANY);
        setTexts();
        comboBoxLaunguage.addItemListener(itemEvent -> setTexts());


        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale locale = (Locale) comboBoxLaunguage.getItemAt(comboBoxLaunguage.getSelectedIndex());
                resourceBundle = ResourceBundle.getBundle("Bundle", locale);
                if (popUpAddNew.isVisible()) {
                    popUpAddNew.setVisible(false);
                    addNewButton.setText(resourceBundle.getString("add.new"));
                } else {
                    popUpAddNew.setVisible(true);
                    addNewButton.setText(resourceBundle.getString("cancel"));
                }
            }
        });
        searchShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale locale = (Locale) comboBoxLaunguage.getItemAt(comboBoxLaunguage.getSelectedIndex());
                resourceBundle = ResourceBundle.getBundle("Bundle", locale);
                if (popUpSearch.isVisible()) {
                    popUpSearch.setVisible(false);

                    searchShowButton.setText(resourceBundle.getString("search"));
                } else {
                    popUpSearch.setVisible(true);
                    searchShowButton.setText(resourceBundle.getString("hide"));
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameSearchInput.getText();
                String surname = surnameSearchInput.getText();
                String number = numberSearchInput.getText();
                String email = emailSearchInput.getText();
                search(name, surname, number, email);
            }
        });
        confirmToAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameAddInput.getText();
                String surname = surnameAddInput.getText();
                String number = numberAddInput.getText();
                String email = emailAddInput.getText();
                addNewElement(name, surname, number, email);
            }
        });
    }

    private void getData() {
        final Object[][] DATA = {
                {"id", "name", "secondname", "phone", "email", "edit", "delete"},       //example of DATA
        };


        //cutting DATA
        tableData = Arrays.copyOfRange(DATA, 1, DATA.length);

    }

    private void getData(final Object[][] DATA) {
        //cutting DATA
        tableData = Arrays.copyOfRange(DATA, 1, DATA.length);

    }

    private void setTexts() {
        Locale locale = (Locale) comboBoxLaunguage.getItemAt(comboBoxLaunguage.getSelectedIndex());
        resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        setTitle(resourceBundle.getString("app.title"));
        addNewButton.setText(resourceBundle.getString("add.new"));
        confirmToAddButton.setText(resourceBundle.getString("confirm"));
        searchShowButton.setText(resourceBundle.getString("search"));
        findButton.setText(resourceBundle.getString("find"));
        launguageLabel.setText(resourceBundle.getString("language"));
        columnNames = new String[]{"id", resourceBundle.getString("name"),
                resourceBundle.getString("surname"), resourceBundle.getString("number"),
                resourceBundle.getString("email"), "edit", "delete"};
        updateTable();
        add_name.setText(resourceBundle.getString("name"));
        add_surname.setText(resourceBundle.getString("surname"));
        add_number.setText(resourceBundle.getString("number"));
        add_email.setText(resourceBundle.getString("email"));
        search_name.setText(resourceBundle.getString("name"));
        search_surname.setText(resourceBundle.getString("surname"));
        search_number.setText(resourceBundle.getString("number"));
        search_email.setText(resourceBundle.getString("email"));
        titleLabel.setText(resourceBundle.getString("app.title"));

    }

    private void search(String name, String surname, String number, String email) {
        /*








        SEARCH FUNCTION










        */
        final Object[][] DATA = {{}};           //here must DATA

        getData(DATA);                          //cutting new DATA after searching
        updateTable();     //update with new DATA
    }

    private void addNewElement(String name, String surname, String number, String email) {
        /*









        ADD FUNCTION









        */
        getData();
        updateTable();
    }

    private void updateTable() {
        table1.setModel(new DefaultTableModel(tableData, columnNames));
        //add buttons to table
        table1.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table1.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), table1, columnNames[5]));
        table1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table1.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), table1, columnNames[6]));
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
        table1.getColumnModel().getColumn(0).setMaxWidth(40);
        table1.getColumnModel().getColumn(1).setPreferredWidth(120);
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);
        table1.getColumnModel().getColumn(3).setPreferredWidth(120);
        table1.getColumnModel().getColumn(4).setPreferredWidth(160);
        table1.getColumnModel().getColumn(5).setMaxWidth(80);
        table1.getColumnModel().getColumn(6).setMaxWidth(80);
    }

    private void sort(Integer column) {
        if (sortBy[0] == column) {
            if (((String) sortBy[1]).equals("decreasing")) sortBy[1] = "ascending";
            else if (((String) sortBy[1]).equals("ascending")) sortBy[1] = "decreasing";
        } else sortBy = new Object[]{column, "ascending"};

        String order = (String) sortBy[1];

        if (column != 0) {
            HashMap<String, String> map = new HashMap<>();
            LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
            ArrayList<String> list = new ArrayList<>();
            for (Object[] tableDatum : tableData) {
                String value;
                try {
                    value = (String) tableDatum[column];
                } catch (Exception e) {
                    value = Integer.toString((Integer) tableDatum[column]);
                }

                map.put(Integer.toString((Integer) tableDatum[0]), value);
            }

            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(entry.getValue());
            }
            Collections.sort(list, new Comparator<String>() {
                public int compare(String str, String str1) {
                    return (str).compareTo(str1);
                }
            });
            for (String str : list) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (entry.getValue().equals(str)) {
                        sortedMap.put(entry.getKey(), str);
                    }
                }
            }

            Object[][] localTableData = new Object[tableData.length][6];
            if (order.equals("ascending")) {
                final int[] i = {0};
                sortedMap.forEach((id, value) -> {
                    int tableID = 0;
                    for (int j = 0; j < tableData.length; j++) {
                        if (Integer.parseInt(id) == (int) tableData[j][0]) tableID = j;
                    }
                    localTableData[i[0]] = tableData[tableID];
                    i[0]++;
                });
            } else {
                final int[] i = {tableData.length - 1};
                sortedMap.forEach((id, value) -> {
                    int tableID = 0;
                    for (int j = 0; j < tableData.length; j++) {
                        if (Integer.parseInt(id) == (int) tableData[j][0]) tableID = j;
                    }
                    localTableData[i[0]] = tableData[tableID];
                    i[0]--;
                });
            }
            tableData = localTableData;

        } else {
            ArrayList<Integer> columnData = new ArrayList<>();
            for (Object row[] : tableData) {
                columnData.add((Integer) row[column]);
            }
            if (Objects.equals(order, "ascending")) columnData.sort(Comparator.naturalOrder());
            else columnData.sort(Comparator.reverseOrder());

            Object[][] localTableData = new Object[tableData.length][6];
            for (int i = 0; i < tableData.length; i++) {
                int ID = 0;
                for (int j = 0; j < tableData.length; j++) {
                    if (columnData.get(i).equals(tableData[j][0])) ID = j;
                }
//                ID = Integer.parseInt(Integer.toString(columnData.get(i))) - 1;
                localTableData[i] = tableData[ID];
            }
            ;
            tableData = localTableData;
        }
        updateTable();

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
        menuPanel.setLayout(new FormLayout("fill:120px:grow,left:4dlu:noGrow,fill:120px:noGrow,left:4dlu:noGrow,fill:p:grow,left:4dlu:noGrow,fill:85px:noGrow,left:4dlu:noGrow,fill:p:grow,left:4dlu:noGrow,fill:max(p;100px):noGrow,fill:30px:noGrow,fill:85px:noGrow,left:4dlu:noGrow,fill:95px:noGrow", "center:30px:noGrow,top:4dlu:noGrow,center:150px:noGrow,top:4dlu:noGrow,center:30px:noGrow"));
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
        popUpAddNew.setBackground(new Color(-2957850));
        menuPanel.add(popUpAddNew, cc.xywh(1, 3, 3, 3, CellConstraints.LEFT, CellConstraints.TOP));
        add_name = new JLabel();
        add_name.setText("Name:");
        popUpAddNew.add(add_name, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_surname = new JLabel();
        add_surname.setText("Surname:");
        popUpAddNew.add(add_surname, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameAddInput = new JTextField();
        popUpAddNew.add(nameAddInput, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        surnameAddInput = new JTextField();
        popUpAddNew.add(surnameAddInput, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_number = new JLabel();
        add_number.setText("Number:");
        popUpAddNew.add(add_number, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberAddInput = new JTextField();
        popUpAddNew.add(numberAddInput, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_email = new JLabel();
        add_email.setText("e-mail:");
        popUpAddNew.add(add_email, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailAddInput = new JTextField();
        popUpAddNew.add(emailAddInput, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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
        search_email = new JLabel();
        search_email.setText("e-mail");
        popUpSearch.add(search_email, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailSearchInput = new JTextField();
        popUpSearch.add(emailSearchInput, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        search_number = new JLabel();
        search_number.setText("Number");
        popUpSearch.add(search_number, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        search_surname = new JLabel();
        search_surname.setText("Surname");
        popUpSearch.add(search_surname, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        search_name = new JLabel();
        search_name.setText("Name:");
        popUpSearch.add(search_name, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberSearchInput = new JTextField();
        popUpSearch.add(numberSearchInput, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        surnameSearchInput = new JTextField();
        popUpSearch.add(surnameSearchInput, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameSearchInput = new JTextField();
        popUpSearch.add(nameSearchInput, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        findButton = new JButton();
        findButton.setText("Find");
        popUpSearch.add(findButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        menuPanel.add(spacer4, cc.xy(9, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer5 = new Spacer();
        menuPanel.add(spacer5, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        dataPanel = new JScrollPane();
        mainPanel.add(dataPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
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