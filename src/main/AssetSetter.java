// File: src/main/AssetSetter.java
package main;

import object.OBJ_Key;
import object.SuperObject;
import object.elementals.OBJ_fire_elemental;
import object.elementals.OBJ_water_elemental;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Key
        SuperObject key = new OBJ_Key("Key", "/objects/key.png");
        key.worldX = 10 * gp.screenWidth / 2 + 360;
        key.worldY =  5 * gp.screenHeight / 2 + 288;
        gp.objects.add(key);

        // Fire Elemental
        SuperObject fire_elemental = new OBJ_fire_elemental("Fire Elemental", "/objects/elementals/fire_elemental.png");
        fire_elemental.worldX = 300; //10 * gp.screenWidth / 2 + 400;
        fire_elemental.worldY =  2295; //5 * gp.screenHeight / 2 + 288;
        gp.objects.add(fire_elemental);

        SuperObject water_elemental = new OBJ_Key("Water", "/objects/elementals/water_elemental.png");
        water_elemental.worldX = 350; //fix values later
        water_elemental.worldY = 2295;
        gp.objects.add(water_elemental);
    }
}
