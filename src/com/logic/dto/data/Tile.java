package com.logic.dto.data;

public class Tile extends StaticEntity
{
    private float defenseMultiplier;
    private float healingMultiplier;
    private float luckMultiplier;
    private Accesibility accesibility;

    public Tile(int x, int y) {
        super(x,y);
        defenseMultiplier = 0;
        healingMultiplier = 0;
        luckMultiplier = 0;
        accesibility = Accesibility.WALKABLE;
    }


    public Tile(int x, int y, float defenseMultiplier, float healingMultiplier, float luckMultiplier, Accesibility accesibility)
    {
        super(x, y);
        this.defenseMultiplier = defenseMultiplier;
        this.healingMultiplier = healingMultiplier;
        this.luckMultiplier = luckMultiplier;
        this.accesibility = accesibility;
    }

    public float getDefenseMultiplier() 
    {
        return defenseMultiplier;
    }
    public Accesibility getAccesibility() 
    {
        return accesibility;
    }
    public void setAccesibility(Accesibility accesibility) 
    {
        this.accesibility = accesibility;
    }
    public float getLuckMultiplier() 
    {
        return luckMultiplier;
    }
    public void setLuckMultiplier(float luckMultiplier) 
    {
        this.luckMultiplier = luckMultiplier;
    }
    public float getHealingMultiplier() 
    {
        return healingMultiplier;
    }
    public void setHealingMultiplier(float healingMultiplier) 
    {
        this.healingMultiplier = healingMultiplier;
    }
    public void setDefenseMultiplier(float defenseMultiplier) 
    {
        this.defenseMultiplier = defenseMultiplier;
    }
}
