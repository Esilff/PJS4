package nivalis.tools.controls;

import nivalis.tools.transform.Camera;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Mouse {
    private static Mouse instance;
    private final double SCREEN_WIDTH = 1920;
    private final double SCREEN_HEIGHT = 1920;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private Mouse() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static Mouse get() {
        if (instance == null) instance = new Mouse();
        return Mouse.instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }

        }
        else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
            }
        }

    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX() { return (float) get().xPos;}

    public static float getNormX(Camera camera) {
        float offset = camera.getX();

        float width = (float) get().SCREEN_WIDTH;
        float x = (float)get().xPos;

        return ((x/(width/2)) - 1.0f )* 10.0f;
    }

    public static float getNormY(Camera camera) {

        return (float) (-((get().yPos)/((get().SCREEN_HEIGHT + camera.getY())/2) -1 + 0.45f ) * 10);
    }

    public static float getY() { return (float) get().yPos;}

    public static float getDx() { return (float) get().lastX; }

    public static float getDy() { return (float) get().lastY; }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length){
            return get().mouseButtonPressed[button];
        }
        else return false;
    }

}
