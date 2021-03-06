package main;

import engine.graphics.*;
import engine.io.Input;
import engine.io.Window;
import engine.math.Vector2f;
import engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {

    public Thread engine;
    public Window window;
    public Renderer renderer;

    public Shader shader;

    public final int WINDOW_WIDTH = 640;
    public final int WINDOW_HEIGHT = 480;

    public Mesh mesh = new Mesh(new Vertex[]{
            new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 1.0f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(0.0f, 0.0f))
    }, new int[]{
            0, 1, 2,
            0, 3, 1
    }, new Material("/textures/Bricks.png"));

    public void start()
    {
        engine = new Thread(this, "OpenGL Engine");
        engine.start();
    }

    public void run()
    {
        init();
        while(!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE))
        {
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11))
            {
                window.setFullScreen(!window.isFullScreen());
            }
        }
        close();
    }

    public void init()
    {
        window = new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "OpenGL 3D Engine");
        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        renderer = new Renderer(shader);
        window.setBackgroundColor(1, 0, 0);
        window.create();
        mesh.create();
        shader.create();
    }

    private void update()
    {
        window.update();
        if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
        {
            System.out.println("X: "+Input.getScrollX()+" | Y: "+Input.getScrollY());
        }
    }

    private void render()
    {
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }

    private void close()
    {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    public static void main(String[] args)
    {
        new Main().start();
    }
}
