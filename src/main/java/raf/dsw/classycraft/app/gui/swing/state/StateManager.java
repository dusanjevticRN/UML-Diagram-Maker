package raf.dsw.classycraft.app.gui.swing.state;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.state.stateImpl.AddClassState;
import raf.dsw.classycraft.app.gui.swing.state.stateImpl.AddEnumState;
import raf.dsw.classycraft.app.gui.swing.state.stateImpl.AddInterfaceState;
import raf.dsw.classycraft.app.gui.swing.state.stateImpl.SelectState;

@Getter
public class StateManager {
    private State currentState;
    private AddClassState addClassState;
    private AddEnumState addEnumState;
    private AddInterfaceState addInterfaceState;
    private SelectState selectState;
    public StateManager(){
        initialiseStates();
    }

    private void initialiseStates(){
        addClassState = new AddClassState();
        addEnumState = new AddEnumState();
        addInterfaceState = new AddInterfaceState();
        selectState = new SelectState();
        currentState = selectState;
    }

    public void setAddClassState() {
        this.currentState = addClassState;
    }
    public void setAddEnumState() {
        this.currentState = addEnumState;
    }
    public void setAddInterfaceState() {
        this.currentState = addInterfaceState;
    }
    public void setSelectState() {
        this.currentState = selectState;
    }
}
