package com.novoda.noplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PlayerCapabilitiesFoo {

    private final PlayerType playerType;
    private final List<Capability> capabilities;

    static PlayerCapabilitiesFoo of(PlayerType playerType, Capability capability, Capability... capabilities) {
        List<Capability> capabilitiesCopy = new ArrayList<>();
        capabilitiesCopy.add(capability);
        capabilitiesCopy.addAll(Arrays.asList(capabilities));
        return new PlayerCapabilitiesFoo(playerType, capabilitiesCopy);
    }

    PlayerCapabilitiesFoo(PlayerType playerType, List<Capability> capabilities) {
        this.playerType = playerType;
        this.capabilities = capabilities;
    }

    public List<Capability> capabilities() {
        return capabilities;
    }

    public PlayerType playerType() {
        return playerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerCapabilitiesFoo that = (PlayerCapabilitiesFoo) o;

        return capabilities != null ? capabilities.equals(that.capabilities) : that.capabilities == null;
    }

    @Override
    public int hashCode() {
        return capabilities != null ? capabilities.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerCapabilitiesFoo{" +
                "capabilities=" + capabilities +
                '}';
    }

    enum Capability {
        SUBTITLES,
        AUDIO_DESCRIPTION,
        WIDEVINE_CLASSIC,
        WIDEVINE_MODULAR_STREAM,
        WIDEVINE_MODULAR_DOWNLOAD
    }
}
