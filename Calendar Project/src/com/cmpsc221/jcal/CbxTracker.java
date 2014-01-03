
package com.cmpsc221.jcal;

import java.util.ArrayList;
import javax.swing.JCheckBox;


public class CbxTracker {
    
    private int calNum;
    private int eventGroupNum;
    private JCheckBox cbxDynEG;
    
    public CbxTracker()
    {
        this.calNum = 0;
        this.eventGroupNum = 0;
        this.cbxDynEG = new JCheckBox();
    }

    public void setBothCbxCal(JCheckBox newCheckBox, int newCal, int newEvnGrpNum)
    {
        this.setCalNum(newCal);
        this.setEventGroupNum(newEvnGrpNum);
        this.setCbxDynEG(newCheckBox);
    }
    
    public void setCalNum(int newCalNum)
    {
        this.calNum = newCalNum;
    }


    public int getCalNum() {
        return calNum;
    }

    public JCheckBox getCbxDynEG() {
        return cbxDynEG;
    }


    public void setCbxDynEG(JCheckBox cbxDynEG) {
        this.cbxDynEG = cbxDynEG;
    }

    public int getEventGroupNum() {
        return eventGroupNum;
    }

    public void setEventGroupNum(int eventGroupNum) {
        this.eventGroupNum = eventGroupNum;
    }
    
  
    
    
}
