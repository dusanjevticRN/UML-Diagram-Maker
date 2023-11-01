package raf.dsw.classycraft.app.messageGenerator;

import raf.dsw.classycraft.app.core.MessageGenerator;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorClass implements MessageGenerator
{
    private List<ISubscriber> subscribers;

    @Override
    public void generate(EventType typeOfEvent)
    {
        if(typeOfEvent == EventType.NODE_CANNOT_BE_DELETED)
            this.notifySubscriber(new Message("Nije moguce brisati cvor", "Error"), null);

        else if(typeOfEvent == EventType.NAME_CANNOT_BE_EMPTY)
            this.notifySubscriber(new Message("Ne moze da se ostavi prazno ime", "Error"), null);

        else if(typeOfEvent == EventType.CANT_DELETE_PROJECT_EXPLORER)
            this.notifySubscriber(new Message("Project Explorer ne moze biti obrisan", "Error"), null);

        else if(typeOfEvent == EventType.CANT_RENAME_AUTHOR)
            this.notifySubscriber(new Message("Ime autora ne moze biti promenjeno", "Error"), null);

        else if(typeOfEvent == EventType.CANT_EMPTY_STRING)
            this.notifySubscriber(new Message("String ne sme biti prazan", "Error"), null);

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
