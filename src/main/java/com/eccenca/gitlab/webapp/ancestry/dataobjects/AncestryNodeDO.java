package com.eccenca.gitlab.webapp.ancestry.dataobjects;

public class AncestryNodeDO {

    private int parentValue;
    private int childValue;


    public AncestryNodeDO(int parentValue, int childValue){
        this.parentValue = parentValue;
        this.childValue = childValue;
    }

    public int getChildValue() {
        return childValue;
    }

    public void setChildValue(int childValue) {
        this.childValue = childValue;
    }

    public int getParentValue() {
        return parentValue;
    }

    public void setParentValue(int parentValue) {
        this.parentValue = parentValue;
    }
}
