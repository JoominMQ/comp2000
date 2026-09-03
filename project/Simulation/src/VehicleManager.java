// VehicleManager.java manages a collection of vehicles in the simulation, allowing for adding, removing, and retrieving vehicles.
// It also checks for distances and collisions between vehicles and other objects in the simulation.
// It DOES NOT handle the behavior or movement of vehicles.

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
    private List<Vehicle> vehicles;

    public VehicleManager() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}