package raf.dsw.classycraft.app.messageGenerator;

import raf.dsw.classycraft.app.core.MessageGenerator;
import raf.dsw.classycraft.app.core.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorClass implements MessageGenerator
{
    private List<ISubscriber> subscribers;

    @Override
    public void generate(Enum typeOfEvent)
    {
        if(typeOfEvent == EventType.NODE_CANNOT_BE_DELETED)
            this.notifySubscriber(new Message("Node cannot be deleted", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.NAME_CANNOT_BE_EMPTY)
            this.notifySubscriber(new Message("Name cannot be empty", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANNOT_EDIT_PROJECT_EXPLORER)
            this.notifySubscriber(new Message("Editing the project explorer is not allowed", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.NODE_IS_NOT_PROJECT)
            this.notifySubscriber(new Message("Packages can only be added within a project", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_ADD_PROJECT_IN_PROJECT)
            this.notifySubscriber(new Message("Adding a project inside another project is not allowed", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_ADD_DIAGRAM_IN_DIARGAM)
            this.notifySubscriber(new Message("Adding a diagram inside another diagram is not allower", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_ADD_PROJECT_IN_PACKAGE)
            this.notifySubscriber(new Message("Projects can only be added within project explorer", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_ADD_ANYTHING_IN_DIAGRAM)
            this.notifySubscriber(new Message("Cannot add elements inside this diagram", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.NO_ITEM_TO_DELETE)
            this.notifySubscriber(new Message("No item selected for deletion", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.NODE_IS_NOT_PROJECT_OR_PACKAGE)
            this.notifySubscriber(new Message("Diagrams can only be added within a project or package", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.NODE_IS_NOT_SELECTED)
            this.notifySubscriber(new Message("Project explorer is not selected", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_DELETE_PROJECT_EXPLORER)
            this.notifySubscriber(new Message("Project Explorer cant be deleted", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_RENAME_AUTHOR)
            this.notifySubscriber(new Message("Authors name cant be changed", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.CANT_EMPTY_STRING)
            this.notifySubscriber(new Message("String cannot be empty", "Error"), typeOfEvent);

        else if(typeOfEvent == EventType.SELECT_ITEM_TO_EDIT)
            this.notifySubscriber(new Message("Please select item to edit", "Information"), typeOfEvent);

    }

    @Override
    public void addSubscriber(ISubscriber subscriber)
    {
        if(subscriber == null)
            return;

        if(this.subscribers == null)
            this.subscribers = new ArrayList<ISubscriber>();

        if(this.subscribers.contains(subscriber))
            return;

        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber)
    {
        if(subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
            return;

        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber(Object notification, Object typeOfUpdate) //Iteriramo kroz listu i zovemo update metodu nad observerima
    {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber subscriber : subscribers)
            subscriber.update(notification, typeOfUpdate);
    }


}
