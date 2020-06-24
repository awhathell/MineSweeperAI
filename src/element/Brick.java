/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import javax.swing.JLabel;

public class Brick extends JLabel {

    private static final long serialVersionUID = 1L;
    private boolean mineTag;
    private boolean expendTag;
    private boolean flagTag;
    public boolean openTag;
    private int rowx;
    private int coly;
    public int counAround;
    private int rightClickCount;

    public Brick(int x, int y) {
        this.rowx = x;
        this.coly = y;

    }

    public boolean isMineTag() {
        return mineTag;
    }

    public void setMineTag(boolean mineTag) {
        this.mineTag = mineTag;
    }

    public boolean isExpendTag() {
        return expendTag;
    }
    
    public boolean isOpenTag() {
        return openTag;
    }
    
    public void setExpendTag(boolean expendTag) {
        this.expendTag = expendTag;
    }

    public boolean isFlagTag() {
        return flagTag;
    }

    public void setFlagTag(boolean flagTag) {
        this.flagTag = flagTag;
    }

    public int getRowx() {
        return rowx;
    }

    public void setRowx(int rowx) {
        this.rowx = rowx;
    }

    public int getColy() {
        return coly;
    }

    public void setColy(int coly) {
        this.coly = coly;
    }

    public int getCounAround() {
        return counAround;
    }

    public void setCounAround(int counAround) {
        this.counAround = counAround;
    }

    public int getRightClickCount() {
        return rightClickCount;
    }

    public void setRightClickCount(int rightClickCount) {
        this.rightClickCount = rightClickCount;
    }

}
