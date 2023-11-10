package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.view.optionBars.MyMenyBar;
import raf.dsw.classycraft.app.gui.swing.view.optionBars.MyToolBar;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabView;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabbedPane;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.core.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MainFrame extends JFrame implements ISubscriber
{
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JSplitPane jSplitPane;
    private JSplitPane splitTabPane;
    private ClassyTabView classyTabView;
    private JPanel workingAreaPane;
    private ClassyTree classyTree;
    private List<ISubscriber> subscriberList;
    private ClassyTabbedPane classyTabbedPane;

    private MainFrame() {}

    private void initialise()
    {
        this.actionManager = new ActionManager();
        this.classyTree = new ClassyTreeImplementation();
        AppCore.getInstance().getMessageGenerator().addSubscriber(this);

        initialiseGUI();
    }

    private void initialiseGUI()
    {
        this.subscriberList = new ArrayList<>();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ClassyCrafT");

        this.menu = new MyMenyBar();
        this.setJMenuBar(menu);

        this.toolBar = new MyToolBar();
        this.add(toolBar, BorderLayout.NORTH);

        this.jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);


        classyTabView = new ClassyTabView();
        classyTabbedPane = new ClassyTabbedPane();
        JTree projectExplorer = classyTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer(), classyTabView, classyTabbedPane);
        this.workingAreaPane = new JPanel();
        JScrollPane scrollPane = new JScrollPane(projectExplorer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMinimumSize(new Dimension(200, 150));

        this.splitTabPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitTabPane.setTopComponent(classyTabView);

        splitTabPane.setBottomComponent(classyTabbedPane);
        splitTabPane.setResizeWeight(0.08);

        jSplitPane.setLeftComponent(scrollPane);
        jSplitPane.setRightComponent(splitTabPane);
        jSplitPane.setOneTouchExpandable(true);
        this.add(jSplitPane, BorderLayout.CENTER);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    @Override
    public void update(Object notification, Object typeOfUpdate)
    {
        Message message = (Message) notification;

        if(message.getType() == "Information")
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), message.getType(), JOptionPane.INFORMATION_MESSAGE);

        else
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), message.getType(), JOptionPane.ERROR_MESSAGE);
    }
}
