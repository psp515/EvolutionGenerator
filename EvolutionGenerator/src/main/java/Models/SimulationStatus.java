package Models;

public class SimulationStatus {

    /**
     * Represents if there is ongoing simulation.
     */
    public boolean isRunning;

    /**
     * Represents if run method has ended.
     */
    public boolean isFinished;

    public boolean notReadyToStartAction()
    {
        return !isFinished || isRunning;
    }
}
