package Display;

/**
 * Abstract base class for all display panels.
 * Enforces a display() method for UI output.
 */
public abstract class DisplayPanel {
    /**
     * All panels must implement their own display logic.
     */
    public abstract void display();
}