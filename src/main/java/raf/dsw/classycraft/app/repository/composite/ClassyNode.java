package raf.dsw.classycraft.app.repository.composite;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class ClassyNode implements IPublisher {
    protected transient ClassyNode parent;
    private transient List<ISubscriber> subscribers;
    protected String name;
    protected String type;

    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ClassyNode) {
            ClassyNode otherObj = (ClassyNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }


    public void addSubscriber(ISubscriber subscriber) {
        if(subscriber == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(subscriber))
            return;

        subscribers.add(subscriber);

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);

    }

}
