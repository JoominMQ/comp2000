import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PedestrianManager {

    private List<Pedestrian> pedestrians;

    private LightManager trafficLights;
    private VehicleManager vehicleManager;

    private static final int PEDESTRIAN_SPAWN_INTERVAL_MS = 2000;

    private int lastPedestrianSpawnTime = 0;

    private static final float WALK_SPEED = 35f;

    public PedestrianManager(
            LightManager trafficLights,
            VehicleManager vehicleManager) {

        this.trafficLights = trafficLights;
        this.vehicleManager = vehicleManager;

        pedestrians = new ArrayList<>();
    }

    public void update(
            double deltaTime,
            float worldTimer) {

        // -----------------------------------------
        // SPAWN PEDESTRIANS
        // -----------------------------------------

        if (worldTimer - lastPedestrianSpawnTime
                >= PEDESTRIAN_SPAWN_INTERVAL_MS) {

            Random random = new Random();

            int side = random.nextInt(4);

            Pedestrian pedestrian = null;

            switch (side) {

                // =================================
                // TOP
                // RIGHT -> LEFT
                // =================================
                //
                // Pedestrian crosses the TOP
                // traffic light at y = 295.
                //

                case 0:

                    pedestrian = new Pedestrian(
                            800,
                            295,
                            7,
                            WALK_SPEED,
                            (float) Math.PI
                    );

                    break;

                // =================================
                // BOTTOM
                // LEFT -> RIGHT
                // =================================
                //
                // Pedestrian crosses the BOTTOM
                // traffic light at y = 505.
                //

                case 1:

                    pedestrian = new Pedestrian(
                            0,
                            505,
                            7,
                            WALK_SPEED,
                            0
                    );

                    break;

                // =================================
                // LEFT
                // BOTTOM -> TOP
                // =================================
                //
                // Pedestrian crosses the LEFT
                // traffic light at x = 295.
                //

                case 2:

                    pedestrian = new Pedestrian(
                            295,
                            800,
                            7,
                            WALK_SPEED,
                            (float) (
                                    3 * Math.PI / 2
                            )
                    );

                    break;

                // =================================
                // RIGHT
                // TOP -> BOTTOM
                // =================================
                //
                // Pedestrian crosses the RIGHT
                // traffic light at x = 505.
                //

                case 3:

                    pedestrian = new Pedestrian(
                            505,
                            0,
                            7,
                            WALK_SPEED,
                            (float) (
                                    Math.PI / 2
                            )
                    );

                    break;
            }

            pedestrians.add(pedestrian);

            lastPedestrianSpawnTime =
                    (int) worldTimer;
        }

        // -----------------------------------------
        // UPDATE PEDESTRIANS
        // -----------------------------------------

        for (Pedestrian pedestrian : pedestrians) {

            if (pedestrian.isHit()) {
                continue;
            }

            handleCrossing(pedestrian);

            pedestrian.update(deltaTime);

            checkCarCollision(pedestrian);
        }

        // -----------------------------------------
        // REMOVE PEDESTRIANS
        // -----------------------------------------

        Iterator<Pedestrian> iterator =
                pedestrians.iterator();

        while (iterator.hasNext()) {

            Pedestrian pedestrian =
                    iterator.next();

            if (pedestrian.shouldRemove()) {
                iterator.remove();
            }
        }
    }

    // =========================================
    // CROSSING LOGIC
    // =========================================

    private void handleCrossing(
            Pedestrian pedestrian) {

        float x = pedestrian.getX();
        float y = pedestrian.getY();

        // =========================================
        // TOP
        // RIGHT -> LEFT
        // =========================================

        if (Math.abs(y - 295) < 1) {

            // Already crossing:
            // IGNORE LIGHT UNTIL FULLY ACROSS
            if (pedestrian.isCrossing()) {

                if (x <= 300) {

                    pedestrian.setCrossing(false);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);
                }

                return;
            }

            // Reaching the TOP traffic light
            if (x <= 500 && x > 480) {

                if (verticalRoadIsRed()) {

                    pedestrian.setCrossing(true);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);

                } else {

                    pedestrian.setWaiting(true);
                    pedestrian.setSpeed(0);
                }

                return;
            }
        }

        // =========================================
        // BOTTOM
        // LEFT -> RIGHT
        // =========================================

        if (Math.abs(y - 505) < 1) {

            // Already crossing
            if (pedestrian.isCrossing()) {

                if (x >= 500) {

                    pedestrian.setCrossing(false);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);
                }

                return;
            }

            // Reaching the BOTTOM traffic light
            if (x >= 300 && x < 320) {

                if (verticalRoadIsRed()) {

                    pedestrian.setCrossing(true);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);

                } else {

                    pedestrian.setWaiting(true);
                    pedestrian.setSpeed(0);
                }

                return;
            }
        }

        // =========================================
        // LEFT
        // BOTTOM -> TOP
        // =========================================

        if (Math.abs(x - 295) < 1) {

            // Already crossing
            if (pedestrian.isCrossing()) {

                if (y <= 300) {

                    pedestrian.setCrossing(false);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);
                }

                return;
            }

            // Reaching LEFT traffic light
            if (y <= 500 && y > 480) {

                if (horizontalRoadIsRed()) {

                    pedestrian.setCrossing(true);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);

                } else {

                    pedestrian.setWaiting(true);
                    pedestrian.setSpeed(0);
                }

                return;
            }
        }

        // =========================================
        // RIGHT
        // TOP -> BOTTOM
        // =========================================

        if (Math.abs(x - 505) < 1) {

            // Already crossing
            if (pedestrian.isCrossing()) {

                if (y >= 500) {

                    pedestrian.setCrossing(false);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);
                }

                return;
            }

            // Reaching RIGHT traffic light
            if (y >= 300 && y < 320) {

                if (horizontalRoadIsRed()) {

                    pedestrian.setCrossing(true);
                    pedestrian.setWaiting(false);
                    pedestrian.setSpeed(WALK_SPEED);

                } else {

                    pedestrian.setWaiting(true);
                    pedestrian.setSpeed(0);
                }

                return;
            }
        }

        // -----------------------------------------
        // NOT AT A CROSSING
        // -----------------------------------------

        if (!pedestrian.isCrossing()
                && !pedestrian.isWaiting()) {

            pedestrian.setSpeed(WALK_SPEED);
        }
    }

    // =========================================
    // VERTICAL ROAD LIGHT
    // =========================================

    private boolean verticalRoadIsRed() {

        return trafficLights
                .getLightTop()
                .isRed()

                && trafficLights
                .getLightBottom()
                .isRed();
    }

    // =========================================
    // HORIZONTAL ROAD LIGHT
    // =========================================

    private boolean horizontalRoadIsRed() {

        return trafficLights
                .getLightLeft()
                .isRed()

                && trafficLights
                .getLightRight()
                .isRed();
    }

    // =========================================
    // CAR COLLISION
    // =========================================

    private void checkCarCollision(
            Pedestrian pedestrian) {

        float[] pedestrianPosition = {
                pedestrian.getX(),
                pedestrian.getY()
        };

        for (Car car :
                vehicleManager.getVehicles()) {

            float[] carPosition =
                    car.getPosition();

            double distance =
                    VehicleManager.getDistance(
                            pedestrianPosition[0],
                            pedestrianPosition[1],
                            carPosition[0],
                            carPosition[1]
                    );

            double collisionDistance =
                    pedestrian.getRadius()
                            + car.getRadius();

            if (distance <= collisionDistance) {

                pedestrian.hit();

                return;
            }
        }
    }

    // =========================================
    // DRAW
    // =========================================

    public void draw(Graphics g) {

        for (Pedestrian pedestrian :
                pedestrians) {

            pedestrian.draw(g);
        }
    }

    // =========================================
    // GETTERS
    // =========================================

    public List<Pedestrian> getPedestrians() {
        return pedestrians;
    }
}