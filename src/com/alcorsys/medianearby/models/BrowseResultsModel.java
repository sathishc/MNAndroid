package com.alcorsys.medianearby.models;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class BrowseResultsModel {

    @JsonDeserialize(as=OwnershipInstance[].class)
    private OwnershipInstance[] ownershipInstanceList;
    private int ownershipInstanceTotal;
    private String message;
    private boolean loggedIn;
    private int total;

    public BrowseResultsModel(){
        super();
    }

    public OwnershipInstance[] getResults() {
        return ownershipInstanceList;
    }

    public void setResults(OwnershipInstance[] instanceList) {
        this.ownershipInstanceList = instanceList;
    }

    public int getOwnershipInstanceTotal() {
        return ownershipInstanceTotal;
    }

    public void setOwnershipInstanceTotal(int ownershipInstanceTotal) {
        this.ownershipInstanceTotal = ownershipInstanceTotal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<HashMap<String,String>> getOwnershipArrayList(){

        //return ownershipInstanceList;

        ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String, String>>();
        if(ownershipInstanceList == null) return arrayList;
        if(ownershipInstanceList.length <= 0) return arrayList;

        int i=0;
        int length = ownershipInstanceList.length;
        for(i=0;i < length;i++){
            OwnershipInstance instance = ownershipInstanceList[i];
            arrayList.add(instance.toHashMap());
        }
        return arrayList;
    }
}
