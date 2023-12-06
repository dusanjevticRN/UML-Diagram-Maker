package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.view.optionBars.MyMenyBar;
import raf.dsw.classycraft.app.gui.swing.view.optionBars.MyToolBar;
import raf.dsw.classycraft.app.gui.swing.view.optionBars.UMLDiagramToolBar;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabView;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;
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
    private JToolBar UMLToolBar;
    private JPanel eastPanel;
    private JSplitPane jSplitPane;
    private JSplitPane splitTabPane;
    private ClassyTabView classyTabView;
    private JPanel workingAreaPane;
    private ClassyTree classyTree;
    private List<ISubscriber> subscriberList;
    private PackageView packageView;

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
        setSize(screenWidth / 2, screenHeight / 2 + 15);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ClassyCrafT");

        this.menu = new MyMenyBar();
        this.setJMenuBar(menu);

        this.toolBar = new MyToolBar();
        this.add(toolBar, BorderLayout.NORTH);

        this.UMLToolBar = new UMLDiagramToolBar();
        this.add(UMLToolBar, BorderLayout.EAST);

        this.eastPanel = new JPanel(new BorderLayout());
        this.eastPanel.add(UMLToolBar, BorderLayout.NORTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.eastPanel.setVisible(true);

        this.jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        classyTabView = new ClassyTabView();
        packageView = new PackageView();
        JTree projectExplorer = classyTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer(), classyTabView, packageView);
        this.workingAreaPane = new JPanel();
        JScrollPane scrollPane = new JScrollPane(projectExplorer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMinimumSize(new Dimension(200, 150));

        this.splitTabPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitTabPane.setTopComponent(classyTabView);

        splitTabPane.setBottomComponent(packageView);
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
        if(notification instanceof ClassyTabView) //Ako je doslo do neke promene, obavesteni smo i pozivamo repaint da bi se "crtez" osvezio i primenio promene
            this.repaint();

        Message message = (Message) notification;
        String messageType = ((Message)notification).getType();

        switch (messageType)
        {
            case "Information":
                JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), message.getType(), JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Warning":
                JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), message.getType(), JOptionPane.WARNING_MESSAGE);
                break;
            case "Error":
                JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), message.getType(), JOptionPane.ERROR_MESSAGE);
                break;
            default:
                return;
        }
    }
}
