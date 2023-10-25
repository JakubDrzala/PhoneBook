package org.example;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.example.Controller.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;


public class  MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton addNewButton;
    private JComboBox comboBoxLaunguage;
    public JTable table1;
    private JButton clearFilters;
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
    private static ResourceBundle resourceBundle;
    private Object[][] tableData;
    private String[] columnNames = {"id", "Name", "Surname", "Phone Number", "e-mail","newCol", "edit", "delete"};
    private Object[] sortBy = {0, "ascending"};
    private JLabel search_email;
    private JLabel search_number;
    private JLabel search_surname;
    private JLabel search_name;
    private JTextField newColSearchInput;

    private Connection con;
    private boolean editAbleTable = false;

    public MainFrame() throws SQLException {
	Login loginFrame = new Login();
        loginFrame.pack();
        loginFrame.setVisible(true);

        if (loginFrame.exit) System.exit(0);

        String login = loginFrame.getLogin();
        String password = loginFrame.getPassword();

        String dbURL = "jdbc:derby:WORKERS;create=true";
        con = DriverManager.getConnection(dbURL);

        PermissionsManager permMan = new PermissionsManager(con);
        Encrypt enc = new Encrypt();
        String typeOfUser = permMan.checkPassword(login, password); //permMan.checkPassword(login, enc.encrypt(password));

        switch (typeOfUser){
            case "ADMIN":
                runAdmin();
                break;
            case "USER":
                break;
            case "EXIT":
            default:
                System.exit(0);
        }

        //main settings
        setContentPane(mainPanel);
        setTitle("test");
        setSize(800, 500);
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

        

        //launguage settings
        comboBoxLaunguage.addItem(Locale.US);
        comboBoxLaunguage.addItem(Locale.GERMANY);
        setTexts();
        comboBoxLaunguage.addItemListener(itemEvent -> setTexts());



        clearFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameSearchInput.setText("");
                surnameSearchInput.setText("");
                numberSearchInput.setText("");
                emailSearchInput.setText("");
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                find();
            }
        });

        nameSearchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                find();
            }
        });
        surnameSearchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                find();
            }
        });
        numberSearchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                find();
            }
        });
        emailSearchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                find();
            }
        });
    }

    private void runAdmin(){
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog addDialog = new Dialog(comboBoxLaunguage.getItemAt(comboBoxLaunguage.getSelectedIndex()));
                addDialog.pack();
                addDialog.setVisible(true);

                Object[] newRow = addDialog.getValues();

                try {

                    if(!addDialog.isCancel)
                        addNewElement(newRow[1].toString(), newRow[2].toString(), newRow[3].toString(), newRow[4].toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    getData();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                updateTable();
            }
        });
        editAbleTable = true;
    }

    private void find(){
        String name = nameSearchInput.getText();
        String surname = surnameSearchInput.getText();
        String number = numberSearchInput.getText();
        String email = emailSearchInput.getText();
        String newCol = newColSearchInput.getText();
        try {
            search(name, surname, number, email, newCol);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getData() throws SQLException {
        Object[][] DATA = new Object[][]{};

        Search search = new Search(con);
        try{
            DATA = search.getAll();
        }catch (Exception error){
            System.out.println(error);
        }

        //cutting DATA
        tableData = DATA;
    }

    private void setTexts() {
        Locale locale = (Locale) comboBoxLaunguage.getItemAt(comboBoxLaunguage.getSelectedIndex());
        resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        setTitle(resourceBundle.getString("app.title"));
        addNewButton.setText(resourceBundle.getString("add.new"));


        clearFilters.setText(resourceBundle.getString("clearFilter"));

        findButton.setText(resourceBundle.getString("find"));
        launguageLabel.setText(resourceBundle.getString("language"));
        columnNames = new String[]{"id", resourceBundle.getString("name"),
                resourceBundle.getString("surname"),
                resourceBundle.getString("number"),
                resourceBundle.getString("email"),
                "newCol",
                resourceBundle.getString("edit"),
                resourceBundle.getString("delete")};
        updateTable();
        search_name.setText(resourceBundle.getString("name"));
        search_surname.setText(resourceBundle.getString("surname"));
        search_number.setText(resourceBundle.getString("number"));
        search_email.setText(resourceBundle.getString("email"));
        titleLabel.setText(resourceBundle.getString("app.title"));
    }

    private void search(String name, String surname, String number, String email, String newCol) throws SQLException {
        Search search = new Search(con);
        List<String> inputs = new ArrayList<>();
        inputs.add(name);
        inputs.add(surname);
        inputs.add(number);
        inputs.add(email);
        inputs.add(newCol);

        final Object[][] DATA = search.searchFor(inputs);           //here must DATA

        tableData=DATA;                      //cutting new DATA after searching
        updateTable();     //update with new DATA
    }

    private void addNewElement(String name, String surname, String number, String email) throws SQLException {

        Add addElement = new Add(con);
        addElement.insert(name,surname,number,email);

        getData();
        updateTable();
    }

    private void updateTable() {
        table1.setModel(new DefaultTableModel(tableData, columnNames));
        //add buttons to table
        if (editAbleTable) {
            table1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
            table1.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), table1, "edit"));
            table1.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
            table1.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox(), table1, "delete"));
        }
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
        table1.getColumnModel().getColumn(0).setMaxWidth(40);
        table1.getColumnModel().getColumn(1).setPreferredWidth(120);
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);
        table1.getColumnModel().getColumn(3).setPreferredWidth(120);
        table1.getColumnModel().getColumn(4).setPreferredWidth(160);
        table1.getColumnModel().getColumn(5).setPreferredWidth(120);
        table1.getColumnModel().getColumn(6).setMaxWidth(80);
        table1.getColumnModel().getColumn(7).setMaxWidth(80);
        table1.getTableHeader().setReorderingAllowed(false);
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
    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;
        private String actionType;

        public ButtonEditor(JCheckBox checkBox, JTable table, String action) {
            super(checkBox);
            this.table = table;
            actionType = action;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        fireEditingStopped();
                    }catch (Exception error){}
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
                switch (actionType) {
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

        public void edit() {
//            String newName = JOptionPane.showInputDialog(button, "Edit name:", tableData[table.getSelectedRow()][1].toString());
//            tableData[table.getSelectedRow()][1] = newName;
//            updateTable();
            int row = table.getSelectedRow();
            Dialog dialog = new Dialog(tableData[row], comboBoxLaunguage.getItemAt(comboBoxLaunguage.getSelectedIndex()));
            dialog.pack();

            dialog.setVisible(true);
            Object[] newRow = dialog.getValues();

            Modify modify = new Modify(con);
            modify.setId(Integer.parseInt(newRow[0].toString()));
            try{
                modify.modify(newRow[1].toString(),newRow[2].toString(),newRow[3].toString(),newRow[4].toString());
            }catch (Exception error){
                System.out.println(error);
            }
            tableData[row] = newRow;
            updateTable();


        }

        public void delete() {
            Object[] options = new Object[]{"Delete this row",
                    "Don't delete this row"};
            int option = JOptionPane.showOptionDialog(
                    button,
                    "Are you sure?",
                    "Delete",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );
            if (option == 0) {
                /*
                
                
                            DELETE FUNCTION
                
                
                */
                int id = Integer.parseInt(tableData[table.getSelectedRow()][0].toString());
                Delete delete = new Delete(con);
                delete.setId(id);
                try{
                    delete.delete();
                    getData();

                }catch (Exception error){
                    System.out.println(error);
                }
                ;
                updateTable();
            }

        }
    }

    public static void main(String[] args) throws SQLException {
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
        menuPanel.setLayout(new FormLayout("fill:100px:grow,left:4dlu:noGrow,fill:120px:noGrow,left:4dlu:noGrow,fill:p:grow,left:4dlu:noGrow,fill:85px:noGrow,left:4dlu:noGrow,fill:p:grow,left:4dlu:noGrow,fill:82px:noGrow,fill:30px:noGrow,fill:85px:noGrow,left:4dlu:noGrow,fill:95px:noGrow", "center:30px:noGrow,top:4dlu:noGrow,center:150px:noGrow,top:4dlu:noGrow,center:30px:noGrow"));
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
        clearFilters = new JButton();
        clearFilters.setText("Search");
        menuPanel.add(clearFilters, cc.xy(11, 5, CellConstraints.RIGHT, CellConstraints.BOTTOM));
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
