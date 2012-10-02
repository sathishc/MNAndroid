package com.alcorsys.medianearby.models;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShelfListModel {
    @JsonDeserialize(as=OwnershipInstance[].class)
    private ShelfListInstance[] ownershipInstanceList;
    private int ownershipInstanceTotal;

    public ShelfListModel(){
        super();
    }

    public ShelfListInstance[] getOwnershipInstanceList() {
        return ownershipInstanceList;
    }

    public void setOwnershipInstanceList(ShelfListInstance[] ownershipInstanceList) {
        this.ownershipInstanceList = ownershipInstanceList;
    }


    public int getOwnershipInstanceTotal() {
        return ownershipInstanceTotal;
    }

    public void setOwnershipInstanceTotal(int ownershipInstanceTotal) {
        this.ownershipInstanceTotal = ownershipInstanceTotal;
    }
}
