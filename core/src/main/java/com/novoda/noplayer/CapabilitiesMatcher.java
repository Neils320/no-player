package com.novoda.noplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CapabilitiesMatcher {

    private final List<PlayerCapabilitiesFoo> playerCapabilities;

    public CapabilitiesMatcher(List<PlayerCapabilitiesFoo> playerCapabilities) {
        this.playerCapabilities = playerCapabilities;
    }

    List<PlayerType> playersWith(PlayerCapabilitiesFoo.Capability requiredCapability, PlayerCapabilitiesFoo.Capability... requiredCapabilities) {
        List<PlayerCapabilitiesFoo.Capability> requiredCapabilitiesCopy = new ArrayList<>();
        requiredCapabilitiesCopy.add(requiredCapability);
        requiredCapabilitiesCopy.addAll(Arrays.asList(requiredCapabilities));
        return playersWith(requiredCapabilitiesCopy);
    }

    private List<PlayerType> playersWith(List<PlayerCapabilitiesFoo.Capability> requiredCapabilities) {
        List<PlayerType> matchingCapabilties = new ArrayList<>();

        for (PlayerCapabilitiesFoo playerCapability : playerCapabilities) {
            boolean playerContainsCapabilities = playerContainsCapabilities(playerCapability.capabilities(), requiredCapabilities);
            if (playerContainsCapabilities) {
                matchingCapabilties.add(playerCapability.playerType());
            }
        }
        return matchingCapabilties;
    }

    private boolean playerContainsCapabilities(List<PlayerCapabilitiesFoo.Capability> capabilities, List<PlayerCapabilitiesFoo.Capability> requiredCapabilities) {
        List<PlayerCapabilitiesFoo.Capability> similar = new ArrayList<>(requiredCapabilities);
        similar.retainAll(capabilities);
        return similar.size() == requiredCapabilities.size();
    }
}
