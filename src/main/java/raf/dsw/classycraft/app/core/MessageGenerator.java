package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.messageGenerator.EventType;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.observer.IPublisher;

//MessageGenerator extenduje Publishera zbog njegovih metoda koje su smislene za ovaj tip "posla"
public interface MessageGenerator extends IPublisher
{
    void generate(EventType typeOfEvent); //Metoda za generisanje poruka u sistemu
}
