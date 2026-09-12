// SettingsDialog.java
// A small popup window that lets the user set the maximum number of cars
// and pedestrians allowed to exist in the simulation at once.

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {

    public SettingsDialog(JFrame parent, VehicleManager vehicleManager, PedestrianManager pedestrianManager) {

        super(parent, "Simulation Settings", true); // modal dialog

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Max cars ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Max cars:"), gbc);

        SpinnerNumberModel carModel = new SpinnerNumberModel(
                vehicleManager.getMaxVehicles(), // current value
                0,                                // min
                200,                              // max
                1                                 // step
        );
        JSpinner carSpinner = new JSpinner(carModel);
        gbc.gridx = 1;
        add(carSpinner, gbc);

        // --- Max pedestrians ---
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Max pedestrians:"), gbc);

        SpinnerNumberModel pedestrianModel = new SpinnerNumberModel(
                pedestrianManager.getMaxPedestrians(), // current value
                0,                                       // min
                200,                                     // max
                1                                        // step
        );
        JSpinner pedestrianSpinner = new JSpinner(pedestrianModel);
        gbc.gridx = 1;
        add(pedestrianSpinner, gbc);

        // --- Buttons ---
        JButton applyButton = new JButton("Apply");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(e -> {
            vehicleManager.setMaxVehicles((Integer) carSpinner.getValue());
            pedestrianManager.setMaxPedestrians((Integer) pedestrianSpinner.getValue());
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}