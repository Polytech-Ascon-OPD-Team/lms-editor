package lmseditor.gui.panel;

import lmseditor.Main;
import lmseditor.backend.question.Question;
import lmseditor.backend.question.QuestionCategory;
import lmseditor.backend.question.QuestionCollection;
import lmseditor.gui.customComponents.*;
import lmseditor.gui.dialog.QuestionTypeDialog;
import lmseditor.gui.panel.workspace.EmptyWorkspace;
import lmseditor.gui.panel.workspace.Workspace;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LeftPanel extends CPanel {

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
            private Question question;
            private Workspace workspace;
            private JButton questionButton = new JButton();

            public QuestionElement() {
                QuestionTypeDialog dialog = new QuestionTypeDialog();
                question = dialog.getQuestion();
                workspace = dialog.getWorkspace();

                this.setLayout(new BorderLayout());
                questionButton.setText(" вопрос (" + String.valueOf(questionElements.size() + 1) + ") ");
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

            private void remove() {
                //TODO(): Remove question from question category
                questionElements.remove(this);
                questionsPanel.remove(this);
                if (questionElements.size() == 0) {
                    categoryLink.openButton.setVisible(false);
                }
                Main.mainFrame.setWorkspace(new EmptyWorkspace());
                updateGraphic();
            }

        }

        private QuestionCategory questionCategory = new QuestionCategory();
        private List<QuestionElement> questionElements = new ArrayList<>();
        private BasicArrowButton openButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        private CategoryPnl categoryLink = this;
        private CPanel questionsPanel = new CPanel();
        private boolean isOpened = false;

        public CategoryPnl() {
            this.setLayout(new BorderLayout());
            questionsPanel.setLayout(new AdvancedLayouter());
            CPanel upperPanel = new CPanel();
            upperPanel.setLayout(new BorderLayout());
            CPanel rightUpperPanel = new CPanel();
            rightUpperPanel.setLayout(new GridLayout(1, 4));
            JTextField textField = new JTextField(" Категория (" + String.valueOf(categories.size() + 1) + ") ");
            upperPanel.add(textField, BorderLayout.CENTER);

            /* TODO()
            textField.addVetoableChangeListener(new VetoableChangeListener() {
                @Override
                public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                    System.out.println(evt);
                }
            });*/
            questionCategory.setName(" Категория (" + String.valueOf(categories.size() + 1) + ") ");
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
                addQuestion();
                // updateUI();
            });
            rightUpperPanel.add(plusButton);
            rightUpperPanel.add(xButton);
            rightUpperPanel.add(openButton);
            upperPanel.add(rightUpperPanel, BorderLayout.EAST);

            this.add(upperPanel, BorderLayout.NORTH);
            this.add(questionsPanel, BorderLayout.SOUTH);
        }

        public void addQuestion() {
            QuestionElement questionElement = new QuestionElement();
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

        public void removeIt() {
            //TODO: удаление категории из questionCollection
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
        this.add(label1, BorderLayout.NORTH);
        this.add(downPanel, BorderLayout.SOUTH);
    }

    public QuestionCollection getQuestionCollection() {
        return questionCollection;
    }

    public void addNewCategory() {
        CategoryPnl categoryPnl = new CategoryPnl();
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

