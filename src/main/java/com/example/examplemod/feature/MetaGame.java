package com.example.examplemod.feature;

public class MetaGame {
    boolean combat = false;

    public MetaGame(){

    }

    public boolean getCombat(){
        return combat;
    }
    public void setCombat(boolean pCombat){
        this.combat = pCombat;
    }
}
