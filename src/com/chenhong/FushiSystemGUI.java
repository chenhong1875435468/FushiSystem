package com.chenhong;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *  This class provides a GUI for the retest system and also implements the click event in the page.
 *
 * @author chenhong
 * @version 1.1.0
 * @see EnglishTest
 * @see ExamPaper
 * @see MathTest
 * @see ProfessionalTest
 * @see Student
 * @see StudentCatalog
 * @see Test
 * @see TestDatabase
 * @see TestItem
 * @see FileFushiTestDatabaseLoader
 * @see FushiTestDatabaseLoader
 * @see StudentsFormatter
 * @see PlainTextStudentsFormatter
 * @see HTMLStudentsFormatter
 * @see XMLStudentsFormatter
 */
@SuppressWarnings("all")
public class FushiSystemGUI extends  JPanel {

    /* Standard error stream */
    static private PrintWriter stdErr = new PrintWriter(System.err, true);

    /* Window width in pixels */
    static private int WIDTH = 1200;

    /* Window height in pixels */
    static private int HEIGHT = 660;

    /* Size of the catalog list cell in pixels */
    static private int CATALOG_CELL_SIZE = 50;

    /* Visible rows in catalog list */
    static private int CATALOG_LIST_ROWS = 14;

    /* Rows in status text area rows */
    static private int STATUS_ROWS = 10;

    /* Rows in status text area cols */
    static private int STATUS_COLS = 40;

    private JList catalogList;
    private JButton generateExamPaperButton;
    private JButton displayExamPaperButton;
    private JButton saveExamPaperButton;
    private JTextArea statusTextArea;
    private JRadioButton plainRadioButton;
    private JRadioButton HTMLRadioButton;
    private JRadioButton XMLRadioButton;

    private static TestDatabase testDatabase;
    private static StudentCatalog studentCatalog;
    private static Student student;
    private StudentsFormatter studentsFormatter;

    /**
     * Loads a test database and starts the application.
     *
     * @param args
     *            String arguments. Not used.
     * @throws IOException
     *             if there are errors in the loading the test database.
     */
    public static void main(String[] args) throws IOException {

        String filename = "";

        if (args.length != 1) {
            filename = "testCatalog.dat";
        } else {
            filename = args[0];
        }
        try {
            testDatabase = (new FileFushiTestDatabaseLoader()).loadTestDatabase(filename);
            JFrame frame = new JFrame("Fushi System");
            frame.setContentPane(new FushiSystemGUI(testDatabase));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setResizable(true);
            frame.setVisible(true);

        } catch (FileNotFoundException fnfe) {
            stdErr.println("The file does not exist");

            System.exit(1);

        } catch (DataFormatException dfe) {
            stdErr.println("The file contains malformed data: " + dfe.getMessage());

            System.exit(1);
        }
    }

    /**
     * Instantiates the components and arranges them in a window.
     *
     * @param testDatabase
     *            a test database.
     */
    public FushiSystemGUI(TestDatabase testDatabase) {

        // create the components
        catalogList = new JList();
        catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        catalogList.setVisibleRowCount(CATALOG_LIST_ROWS);
        catalogList.setFixedCellWidth(CATALOG_CELL_SIZE);
        generateExamPaperButton = new JButton("Generate Exampaper");
        displayExamPaperButton = new JButton("Display Exampaper");
        saveExamPaperButton = new JButton("Save Exampaper");
        statusTextArea = new JTextArea(STATUS_ROWS, STATUS_COLS);
        statusTextArea.setEditable(false);
        plainRadioButton = new JRadioButton("Plain", true);
        HTMLRadioButton = new JRadioButton("HTML");
        XMLRadioButton = new JRadioButton("XML");

        ButtonGroup group = new ButtonGroup();

        group.add(plainRadioButton);
        group.add(HTMLRadioButton);
        group.add(XMLRadioButton);

        // Catalog panel
        JPanel catalogPanel = new JPanel(new BorderLayout());

        catalogPanel.setBorder(BorderFactory.createTitledBorder("Catalog"));
        catalogPanel.add(generateExamPaperButton,BorderLayout.SOUTH);
        catalogPanel.add(new JScrollPane(catalogList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        catalogPanel.setPreferredSize(new Dimension(190, 150));

        // examPaper panel
        JPanel examPaperPanel = new JPanel(new BorderLayout());

        examPaperPanel.setBorder(BorderFactory.createTitledBorder("Exampaper"));

        JPanel examPaperButtonsPanel = new JPanel(new GridLayout(1, 5));

        examPaperButtonsPanel.add(plainRadioButton);
        examPaperButtonsPanel.add(HTMLRadioButton);
        examPaperButtonsPanel.add(XMLRadioButton);
        examPaperButtonsPanel.add(displayExamPaperButton);
        examPaperButtonsPanel.add(saveExamPaperButton);
        examPaperPanel.add(new JScrollPane(statusTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        examPaperPanel.add(examPaperButtonsPanel, BorderLayout.SOUTH);

        // arrange panels in window
        setLayout(new BorderLayout());
        add(catalogPanel, BorderLayout.WEST);
        add(examPaperPanel, BorderLayout.CENTER);



        // start listening for list and buttons events
        catalogList.addListSelectionListener(new DisplayStudentListener());
        generateExamPaperButton.addActionListener(new generateExamPaperListener());
        displayExamPaperButton.addActionListener(new DisplayExamPaperListener());
        saveExamPaperButton.addActionListener(new SaveExamPaperListener());
        plainRadioButton.addActionListener(new PlainListener());
        HTMLRadioButton.addActionListener(new HTMLListener());
        XMLRadioButton.addActionListener(new XMLListener());

        // populate the student catalog
        studentCatalog = loadStudent();
        String[] codes = new String[studentCatalog.getNumberOfStudents()];
        int i = 0;
        for (Student student : studentCatalog) {
            codes[i++] = student.toString();
        }
        catalogList.setListData(codes);
        studentsFormatter = PlainTextStudentsFormatter.getSingletonInstance();
    }

    /**
     * Loads the information of a student catalog.
     */
    private static StudentCatalog loadStudent() {
        StudentCatalog studentCatalog = new StudentCatalog();

        studentCatalog.addStudent(new Student("2019213001", "?????????"));
        studentCatalog.addStudent(new Student("2019213002", "?????????"));
        studentCatalog.addStudent(new Student("2019213003", "?????????"));
        studentCatalog.addStudent(new Student("2019213004", "??????"));
        studentCatalog.addStudent(new Student("2019213005", "?????????"));
        studentCatalog.addStudent(new Student("2019213006", "?????????"));
        studentCatalog.addStudent(new Student("2019213007", "??????"));
        studentCatalog.addStudent(new Student("2019213008", "?????????"));
        studentCatalog.addStudent(new Student("2019213009", "?????????"));
        studentCatalog.addStudent(new Student("2019213010", "??????"));
        studentCatalog.addStudent(new Student("2019213011", "??????"));
        studentCatalog.addStudent(new Student("2019213012", "?????????"));
        studentCatalog.addStudent(new Student("2019213013", "?????????"));
        studentCatalog.addStudent(new Student("2019213014", "?????????"));
        studentCatalog.addStudent(new Student("2019213015", "?????????"));
        studentCatalog.addStudent(new Student("2019213016", "??????"));
        studentCatalog.addStudent(new Student("2019213017", "?????????"));
        studentCatalog.addStudent(new Student("2019213018", "??????"));
        studentCatalog.addStudent(new Student("2019213019", "?????????"));
        studentCatalog.addStudent(new Student("2019213020", "?????????"));
        studentCatalog.addStudent(new Student("2019213021", "??????"));
        studentCatalog.addStudent(new Student("2019213022", "??????"));
        studentCatalog.addStudent(new Student("2019213023", "??????"));
        studentCatalog.addStudent(new Student("2019213024", "??????"));
        studentCatalog.addStudent(new Student("2019213025", "??????"));
        studentCatalog.addStudent(new Student("2019213026", "??????"));
        studentCatalog.addStudent(new Student("2019213027", "?????????"));
        studentCatalog.addStudent(new Student("2019213028", "?????????"));
        studentCatalog.addStudent(new Student("2019213029", "??????"));
        studentCatalog.addStudent(new Student("2019213030", "??????"));
        studentCatalog.addStudent(new Student("2019213031", "??????"));
        studentCatalog.addStudent(new Student("2019213032", "?????????"));
        studentCatalog.addStudent(new Student("2019213033", "??????"));
        studentCatalog.addStudent(new Student("2019213034", "??????"));
        studentCatalog.addStudent(new Student("2019213035", "??????"));
        studentCatalog.addStudent(new Student("2019213036", "??????"));
        studentCatalog.addStudent(new Student("2019213037", "??????"));
        studentCatalog.addStudent(new Student("2019213038", "??????"));
        studentCatalog.addStudent(new Student("2019213039", "?????????"));
        studentCatalog.addStudent(new Student("2019213040", "?????????"));
        return studentCatalog;
    }

    /**
     * This inner class handles list-selection events.
     */
    class DisplayStudentListener implements ListSelectionListener {

        /**
         * Gets the information of the selected student.
         *
         * @param event
         *            the event object.
         */
        public void valueChanged(ListSelectionEvent event) {

            if (!catalogList.getValueIsAdjusting()) {

                String studentInfo = (String) catalogList.getSelectedValue();
                StringTokenizer token = new StringTokenizer(studentInfo, "_");
                student = studentCatalog.getStudent(token.nextToken());
            }
        }

    }

    /**
     * This inner class processes <code>generateExamPaperButton</code> events.
     */
    class generateExamPaperListener implements ActionListener {

        /**
         * Generate a random retest paper for the designated student.
         *
         * @param event
         *            the event object.
         */
        public void actionPerformed(ActionEvent event) {

            try{
                if(student.getExamPaper() == null) {
                    generateExamPaper();
                } else {
                    statusTextArea.setText("Test papers have been generated for " + student.getId());
                }

            } catch (Exception e) {
                statusTextArea.setText("Test paper generation failed for "+ student.getId());
            }
        }
    }

    /**
     * Generate a random retest paper for the designated student.
     */
    private void generateExamPaper() throws IOException {


            if (testDatabase.getNumberOfTests() < 10) {
                statusTextArea.setText("There are less than ten test questions in the test question bank, "
                        + "and the test paper cannot be generated.");
            } else {

                int[] testTypeNums = new int[3];
                for (int i = 0; i < testTypeNums.length; i++) {
                    testTypeNums[i] = 0;
                }
                Iterator<Test> iterator = testDatabase.iterator();
                while (iterator.hasNext()) {
                    Test test = iterator.next();
                    if (test instanceof EnglishTest) {
                        testTypeNums[0]++;
                    } else if (test instanceof MathTest) {
                        testTypeNums[1]++;
                    } else if (test instanceof ProfessionalTest) {
                        testTypeNums[2]++;
                    }
                }

                if (testTypeNums[0] < 3 || testTypeNums[1] < 3 || testTypeNums[2] < 4) {
                    statusTextArea.setText("There are not enough English questions or math questions or professional "
                            + "questions in the test database.");
                    return;
                }
                //????????????????????????
                Random random = new Random();
                ExamPaper examPaper = new ExamPaper();

                for (int i = 0; i < testTypeNums.length; i++) {
                    testTypeNums[i] = 0;
                }

                int allTestCount = testDatabase.getNumberOfTests();

                boolean[] testIsChoose = new boolean[allTestCount];

                while (examPaper.getNumberOfItems() < 10) {

                    int target = random.nextInt(allTestCount);
                    Test test = testDatabase.getTest(target);

                    if (test instanceof EnglishTest) {
                        if (testTypeNums[0] < 3 && (!testIsChoose[target])) {
                            testTypeNums[0]++;
                            examPaper.addTestItem(new TestItem(test, 0));
                            testIsChoose[target] = true;
                        }
                    } else if (test instanceof MathTest) {
                        if (testTypeNums[1] < 3 && (!testIsChoose[target])) {
                            testTypeNums[1]++;
                            examPaper.addTestItem(new TestItem(test, 0));
                            testIsChoose[target] = true;
                        }
                    } else if (test instanceof ProfessionalTest) {
                        if (testTypeNums[2] < 4 && (!testIsChoose[target])) {
                            testTypeNums[2]++;
                            examPaper.addTestItem(new TestItem(test, 0));
                            testIsChoose[target] = true;
                        }
                    }

                }
                student.setExamPaper(examPaper);
                statusTextArea.setText("Test paper generated successfully for "+student.getId());
            }

    }

    /**
     * This inner class processes <code>displayExamPaperButton</code>events.
     */
    class DisplayExamPaperListener implements ActionListener {

        /**
         * Displays the students' exam paper information.
         *
         * @param event
         *            the event object.
         */
        public void actionPerformed(ActionEvent event) {
            statusTextArea.setText(studentsFormatter.formatStudents(studentCatalog));
        }
    }

    /**
     * This inner class processes <code>saveExamPaperButton</code> events.
     */
    class SaveExamPaperListener implements ActionListener {

        /**
         * Saves the students' exam paper information in a file.
         *
         * @param event
         *            the event object.
         */
        public void actionPerformed(ActionEvent event) {

            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "??????");
            File file=jfc.getSelectedFile();
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(studentsFormatter.formatStudents(studentCatalog));
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            statusTextArea.setText("?????????????????????"+jfc.getSelectedFile().getName());

        }
    }

    /*
     * This inner class processes <code>plainRadioButton</code> events.
     */
    class PlainListener implements ActionListener {

        /**
         * Sets the students formatter to plain text.
         *
         * @param event
         *            the event object.
         */
        public void actionPerformed(ActionEvent event) {

            studentsFormatter = PlainTextStudentsFormatter.getSingletonInstance();
        }
    }

    /*
     * This inner class processes <code>HTMLRadioButton</code> events.
     */
    class HTMLListener implements ActionListener {

        /**
         * Sets the students formatter to HTML.
         *
         * @param event
         *            the event object.
         */
        public void actionPerformed(ActionEvent event) {

            studentsFormatter = HTMLStudentsFormatter.getSingletonInstance();
        }
    }

    /*
     * This inner class processes <code>XMLRadioButton</code> events.
     */
    class XMLListener implements ActionListener {

        /**
         * Sets the students formatter to XML.
         *
         * @param event
         *            the event object.
         */
        public void actionPerformed(ActionEvent event) {

            studentsFormatter = XMLStudentsFormatter.getSingletonInstance();
        }
    }
}
