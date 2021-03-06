package com.logic.utils.pathfinding.render;

import com.view.MainSpriteEnum;
import com.view.MainSpriteSheet;
import nivalis.engine.render.RenderBatch;
import nivalis.tools.TestSpriteEnum;
import nivalis.tools.TestSpritesheet;

import nivalis.tools.transform.Camera;
import com.logic.utils.pathfinding.dto.*;

import java.util.ArrayList;
import java.util.List;

public class RenderedPath  {

    private List<RenderedPoint> path;
    private int counter, max;
    private RenderBatch renderBatch;
    private MainSpriteSheet spriteSheet;
    //public TestSpritesheet spriteSheet = new TestSpritesheet("./res/spritesheet/testspritesheet.png",16,16);

    public RenderedPath() {
        path = new ArrayList<>();
        renderBatch = new RenderBatch();
        spriteSheet= new MainSpriteSheet();
    }


    public void render(Camera camera) {
        renderBatch.render(camera);
    }

    public void init(Path path) {
        clear();
        renderBatch.clear();
        max = path.getList().size() - 1;
        counter = 0;
        path.getList().forEach(pathPoint -> {
            boolean isStraight = false;
            if (counter != max && counter != 0) {
                if (path.getList().get(counter - 1).getX() != pathPoint.getX() && path.getList().get(counter - 1).getY() == pathPoint.getY()
                        && path.getList().get(counter + 1).getX() != pathPoint.getX() && path.getList().get(counter + 1).getY() == pathPoint.getY()) {
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.STRAIGHT_ARROW, pathPoint.getX(), pathPoint.getY(),0));
                    isStraight = true;
                }
                if (path.getList().get(counter - 1).getX() == pathPoint.getX() && path.getList().get(counter - 1).getY() != pathPoint.getY()
                        && path.getList().get(counter + 1).getX() == pathPoint.getX() && path.getList().get(counter + 1).getY() != pathPoint.getY()) {
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.STRAIGHT_ARROW, pathPoint.getX(), pathPoint.getY(),90));
                    isStraight = true;
                }

                if (path.getList().get(counter +1).getX() - path.getList().get(counter - 1).getX() > 0 && pathPoint.getY() == path.getList().get(counter + 1).getY() && !isStraight) {
                    int rotation = (path.getList().get(counter + 1).getY() - path.getList().get(counter -1).getY() >0)?270:0;
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.TURN_ARROW, pathPoint.getX(), pathPoint.getY(),rotation));
                }
                if (path.getList().get(counter +1).getX() - path.getList().get(counter - 1).getX() < 0 && pathPoint.getY() == path.getList().get(counter + 1).getY() && !isStraight) {
                    int rotation = (path.getList().get(counter + 1).getY() - path.getList().get(counter -1).getY() >0)?90:180;
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.TURN_ARROW, pathPoint.getX(), pathPoint.getY(),rotation));                }
                if (path.getList().get(counter +1).getY() - path.getList().get(counter - 1).getY() > 0 && pathPoint.getX() == path.getList().get(counter + 1).getX() && !isStraight) {
                    int rotation = (path.getList().get(counter + 1).getX() - path.getList().get(counter -1).getX() >0)?0:0;
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.TURN_ARROW, pathPoint.getX(), pathPoint.getY(),rotation));                }
                if (path.getList().get(counter +1).getY() - path.getList().get(counter - 1).getY() < 0  && pathPoint.getX() == path.getList().get(counter + 1).getX()  && !isStraight) {
                    int rotation = (path.getList().get(counter + 1).getX() - path.getList().get(counter -1).getX() >0)?90:180;
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.TURN_ARROW, pathPoint.getX(), pathPoint.getY(),rotation));
                }
            } else if (counter == max || counter ==0) {
                if (counter == 0) {
                    int rotation = (path.getList().get(counter +1).getX() == pathPoint.getX())?(path.getList().get(counter+1).getY() > pathPoint.getY())?90:270:(path.getList().get(counter + 1).getX() > pathPoint.getX())?0:180;
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.SOURCE_ARROW, pathPoint.getX(), pathPoint.getY(), rotation));                }
                if (counter == max) {
                    int rotation = (path.getList().get(counter -1).getX() == pathPoint.getX())?(path.getList().get(counter-1).getY() > pathPoint.getY())?270:90:(path.getList().get(counter - 1).getX() > pathPoint.getX())?180:0;
                    renderBatch.addSprite(spriteSheet.getSprite(MainSpriteEnum.END_ARROW, pathPoint.getX(), pathPoint.getY(), rotation));                }
            }
            counter++;
        });

    }

    public void clear() {
        this.path.clear();
    }
}
