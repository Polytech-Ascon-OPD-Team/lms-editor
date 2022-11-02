package lmseditor.gui.panel;

import lmseditor.Main;
import lmseditor.backend.QuestionXmlParser;
import lmseditor.backend.question.Question;
import lmseditor.backend.question.QuestionCategory;
import lmseditor.backend.question.QuestionCollection;
import lmseditor.backend.question.QuestionType;
import lmseditor.gui.customComponents.*;
import lmseditor.gui.dialog.QuestionTypeDialog;
import lmseditor.gui.panel.workspace.EmptyWorkspace;
import lmseditor.gui.panel.workspace.Workspace;
import lmseditor.gui.util.Util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeftPanel extends CPanel {
    private QuestionXmlParser parser = new QuestionXmlParser();

    public void saveToFile(String filePath) {
        categories.forEach(CategoryPnl::updateData);
        parser.marshallToFile(questionCollection, new File(filePath));

    }

    public QuestionCollection load(String filePath) {
        return parser.unmarshallFromFile(new File(filePath));
    }

    private void clear() {
        while (categories.size() > 0) {
            categories.get(0).removeIt();
        }
        Main.mainFrame.clearWorkspace();
    }

    public void loadFromFile(String filePath) {
        clear();
        QuestionCollection localQuestionCollection = load(filePath);
        List<QuestionCategory> newCategories = localQuestionCollection.getCategoriesList();
        newCategories.forEach(category -> {
            CategoryPnl currCategoryPnl = addCategory(category);
            boolean check = false;
            for (Question question : localQuestionCollection.getQuestionsFromCategory(category)) {
                if (!check) {
                    check = true;
                    currCategoryPnl.enableOpenButton();
                }
                currCategoryPnl.addQuestion(question);
            }
        });

    }

    class LoadPanel extends JPanel {

        public LoadPanel() {
            this.setLayout(new GridLayout(1, 3));
            StandardButton newButton = new StandardButton("Новый");
            StandardButton load = new StandardButton("Загрузить");
            StandardButton save = new StandardButton("Сохр.");
            this.add(newButton);
            newButton.setAction(LeftPanel.this::clear);
            this.add(load);
            this.add(save);
            load.setAction(() -> {
                try {
                    loadFromFile(Util.chooseXMLPathFilePath());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            save.setAction(() -> {
                try {
                    saveToFile(Util.saveXMLPathFilePath());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    private static final int STEP = 5;
    private static final Color LEFT_PANEL_COLOR = new Color(58, 58, 68);

    private class CategoryPnl extends CPanel {

        private class XButton extends ClickableCPanel {
            private static final int step = 5;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = this.getWidth();
                int h = this.getHeight();
                g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.setColor(Color.RED);
                g2.drawLine(step, step, w - step, h - step);
                g2.drawLine(w - step, step, step, h - step);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(24, 24);
            }
        }

        private class PlusButton extends ClickableCPanel {
            private static final int step = 5;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = this.getWidth();
                int h = this.getHeight();
                g2.setStroke(new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
                g2.setColor(Color.BLACK);
                g2.drawLine(step, h / 2, w - step, h / 2);
                g2.drawLine(w / 2, step, w / 2, h - step);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(24, 24);
            }
        }

        private class QuestionElement extends CPanel {
            public final int NUMBER;
            private Question question;
            private Workspace workspace;
            private JButton questionButton = new JButton();

            public void setName(String name) {
                questionButton.setText(name);
                question.getQuestionHeader().getName().setId(name);
            }

            void updateData() {
                workspace.loadData();
            }

            private void init() {
                this.setLayout(new BorderLayout());
                questionButton.addActionListener(e -> {
                    Main.mainFrame.getWorkspace().loadData();
                    Main.mainFrame.setWorkspace(workspace);
                });

                questionButton.setFocusPainted(false);
                this.add(questionButton, BorderLayout.CENTER);
                XButton xButton = new XButton();
                xButton.setAction(this::remove);
                this.add(xButton, BorderLayout.EAST);
                setPreferredSize(new Dimension(12, 12));
            }

            public QuestionElement() {
                if (questionElements.size() > 0) {
                    this.NUMBER = questionElements.get(questionElements.size() - 1).NUMBER + 1;
                } else {
                    this.NUMBER = 1;
                }
                String name = questionCategory.getName() + " " + NUMBER;
                QuestionTypeDialog dialog = new QuestionTypeDialog();
                QuestionType type = dialog.getSelectedType();
                question = Util.getQuestionForType(type);
                assert question != null;
                question.getQuestionHeader().getName().setId(name);
                workspace = Util.getWorkspaceForQuestionAndType(question, type);
                questionButton.setText(name);
                init();
            }

            public QuestionElement(Question question) {
                if (questionElements.size() > 0) {
                    this.NUMBER = questionElements.get(questionElements.size() - 1).NUMBER + 1;
                } else {
                    this.NUMBER = 1;
                }

                this.question = question;
                questionButton.setText(question.getQuestionHeader().getName().getId());
                workspace = Util.getWorkspaceForQuestionAndType(question, question.getType());
                init();
            }

            private void remove() {
                if (this.workspace == Main.mainFrame.getWorkspace()) {
                    Main.mainFrame.clearWorkspace();
                }
                questionCollection.removeQuestion(this.question);
                questionElements.remove(this);
                questionsPanel.remove(this);
                if (questionElements.size() == 0) {
                    categoryLink.openButton.setVisible(false);
                }
                Main.mainFrame.setWorkspace(new EmptyWorkspace());
                updateGraphic();
            }

        }

        private QuestionCategory questionCategory;
        private List<QuestionElement> questionElements = new ArrayList<>();
        private BasicArrowButton openButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        private CategoryPnl categoryLink = this;
        private CPanel questionsPanel = new CPanel();
        private boolean isOpened = false;
        public final int NUMBER;
        private JTextField textField;

        private void updateData() {
            questionElements.forEach(QuestionElement::updateData);
        }

        private void updateName() {
            String text = textField.getText();
            questionCategory.setName(text);
            questionElements.forEach(it -> it.setName(text + " " + it.NUMBER));
            updateUI();
        }

        public void enableOpenButton() {
            isOpened = true;
            openButton.setDirection(BasicArrowButton.NORTH);
            openButton.setVisible(true);
        }

        private void init(String name) {
            this.setLayout(new BorderLayout());
            questionsPanel.setLayout(new AdvancedLayouter());
            CPanel upperPanel = new CPanel();
            upperPanel.setLayout(new BorderLayout());
            CPanel rightUpperPanel = new CPanel();
            rightUpperPanel.setLayout(new GridLayout(1, 4));

            textField = new JTextField();
            textField.setText(name);
            upperPanel.add(textField, BorderLayout.CENTER);
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateName();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateName();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateName();
                }
            });


            openButton.setVisible(false);
            openButton.addActionListener(event -> {
                if (isOpened) {
                    isOpened = false;
                    openButton.setDirection(BasicArrowButton.SOUTH);
                    questionsPanel.setVisible(false);
                    questionsPanel.doLayout();

                } else {
                    isOpened = true;
                    openButton.setDirection(BasicArrowButton.NORTH);
                    questionsPanel.setVisible(true);
                    questionsPanel.doLayout();

                }
            });
            XButton xButton = new XButton();
            xButton.setAction(this::removeIt);
            PlusButton plusButton = new PlusButton();
            plusButton.setAction(() -> {
                if (!isOpened) {
                    openButton.doClick();
                }
                if (!openButton.isVisible()) {
                    openButton.setVisible(true);
                }
                addNewQuestion();
                // updateUI();
            });
            rightUpperPanel.add(plusButton);
            rightUpperPanel.add(xButton);
            rightUpperPanel.add(openButton);
            upperPanel.add(rightUpperPanel, BorderLayout.EAST);

            this.add(upperPanel, BorderLayout.NORTH);
            this.add(questionsPanel, BorderLayout.SOUTH);
        }

        public CategoryPnl() {
            questionCategory = new QuestionCategory();
            if (categories.size() > 0) {
                this.NUMBER = categories.get(categories.size() - 1).NUMBER + 1;
            } else {
                this.NUMBER = 1;
            }
            String name = "Категория (" + NUMBER + ")";
            questionCategory.setName(name);
            init(name);
        }

        public CategoryPnl(QuestionCategory category) {
            questionCategory = category;
            if (categories.size() > 0) {
                this.NUMBER = categories.get(categories.size() - 1).NUMBER + 1;
            } else {
                this.NUMBER = 1;
            }
            init(category.getName());
        }

        private void addQuestionElement(QuestionElement questionElement) {
            questionCollection.addQuestionToCategory(questionCategory, questionElement.question);
            questionElements.add(questionElement);
            questionsPanel.addLayoutable(questionElement);
            questionElement.boundsSetter = () -> {
                int y;
                if (this.questionElements.indexOf(questionElement) == 0) {
                    y = STEP;
                } else {
                    QuestionElement prev = questionElements.get(
                            this.questionElements.indexOf(questionElement) - 1
                    );
                    y = STEP + prev.getLocation().y + prev.getHeight();
                }
                return new Rectangle(STEP * 2, y, questionsPanel.getWidth() - 4 * STEP, 24);
            };
            questionsPanel.doLayout();
            updateGraphic();
        }

        public void addNewQuestion() {
            QuestionElement questionElement = new QuestionElement();
            addQuestionElement(questionElement);
        }

        public void addQuestion(Question question) {
            QuestionElement questionElement = new QuestionElement(question);
            addQuestionElement(questionElement);
        }

        public void removeIt() {
            for (QuestionElement questionElement : this.questionElements) {
                if (questionElement.workspace == Main.mainFrame.getWorkspace()) {
                    Main.mainFrame.clearWorkspace();
                    break;
                }
            }
            questionCollection.removeCategory(this.questionCategory);
            categories.remove(this);
            this.getParent().remove(this);
            updateGraphic();
        }

    }

    private QuestionCollection questionCollection = new QuestionCollection();
    private List<CategoryPnl> categories = new ArrayList<>();

    private JScrollPane scrollPane = new JScrollPane();
    private CPanel panel = new CPanel();

    public LeftPanel() {
        setBackground(LEFT_PANEL_COLOR);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().add(panel);
        panel.setLayout(new AdvancedLayouter());
        CPanel downPanel = new CPanel();
        StandardButton addCategoryButton = new StandardButton(" Добавить категорию ");
        downPanel.setLayout(new BorderLayout(1, 1));
        downPanel.add(addCategoryButton, BorderLayout.CENTER);
        addCategoryButton.setAction(this::addNewCategory);
        JLabel label1 = new JLabel("Список вопросов: ");
        label1.setForeground(Color.white);
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(new LoadPanel(), BorderLayout.CENTER);
        label1.setForeground(Color.BLACK);
        upperPanel.add(label1, BorderLayout.SOUTH);
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(downPanel, BorderLayout.SOUTH);
    }

    public QuestionCollection getQuestionCollection() {
        return questionCollection;
    }

    private void addCategoryPnl(CategoryPnl categoryPnl) {
        questionCollection.addCategory(categoryPnl.questionCategory);
        categories.add(categoryPnl);
        panel.addLayoutable(categoryPnl);
        categoryPnl.boundsSetter = () -> {
            int y;
            if (categories.indexOf(categoryPnl) == 0) {
                y = STEP;
            } else {
                CategoryPnl prev = categories.get(categories.indexOf(categoryPnl) - 1);
                y = STEP + prev.getLocation().y + prev.getHeight();
            }
            return new Rectangle(STEP, y, panel.getWidth() - 2 * STEP, categoryPnl.getPreferredSize().height);
        };
        updateGraphic();
    }

    public void addNewCategory() {
        CategoryPnl categoryPnl = new CategoryPnl();
        addCategoryPnl(categoryPnl);
    }

    public CategoryPnl addCategory(QuestionCategory category) {
        CategoryPnl categoryPnl = new CategoryPnl(category);
        addCategoryPnl(categoryPnl);
        return categoryPnl;
    }

    private void updateGraphic() {
        //костыльный метод
        categories.forEach(it -> {
            it.setBounds(it.boundsSetter.get());
        });
        this.setVisible(false);
        this.setVisible(true);
        this.repaint();
    }

}

