package com.novoda.noplayer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static com.novoda.noplayer.PlayerCapabilitiesFoo.Capability.AUDIO_DESCRIPTION;
import static com.novoda.noplayer.PlayerCapabilitiesFoo.Capability.SUBTITLES;
import static com.novoda.noplayer.PlayerCapabilitiesFoo.Capability.WIDEVINE_CLASSIC;
import static com.novoda.noplayer.PlayerCapabilitiesFoo.Capability.WIDEVINE_MODULAR_DOWNLOAD;
import static com.novoda.noplayer.PlayerCapabilitiesFoo.Capability.WIDEVINE_MODULAR_STREAM;
import static org.fest.assertions.api.Assertions.assertThat;

public class CapabilitiesMatcherTest {

    private static final PlayerCapabilitiesFoo EXO_PLAYER_CAPABILITIES = PlayerCapabilitiesFoo.of(
            PlayerType.EXO_PLAYER,
            SUBTITLES,
            AUDIO_DESCRIPTION,
            WIDEVINE_MODULAR_STREAM,
            WIDEVINE_MODULAR_DOWNLOAD
    );

    private static final PlayerCapabilitiesFoo MEDIA_PLAYER_CAPABILITIES = PlayerCapabilitiesFoo.of(
            PlayerType.MEDIA_PLAYER,
            AUDIO_DESCRIPTION,
            WIDEVINE_CLASSIC
    );

    private CapabilitiesMatcher capabilitiesMatcher;

    @Before
    public void setUp() {
        capabilitiesMatcher = new CapabilitiesMatcher(Arrays.asList(EXO_PLAYER_CAPABILITIES, MEDIA_PLAYER_CAPABILITIES));
    }

    @Test
    public void givenAudioDescriptionCapabilityRequired_whenMatching_thenReturnsExoPlayerAndMediaPlayer() {
        List<PlayerType> playerTypes = capabilitiesMatcher.playersWith(AUDIO_DESCRIPTION);

        assertThat(playerTypes).isEqualTo(Arrays.asList(PlayerType.EXO_PLAYER, PlayerType.MEDIA_PLAYER));
    }

    @Test
    public void givenAudioDescriptionAndSubtitlesCapabilityRequired_whenMatching_thenReturnsExoPlayer() {
        List<PlayerType> playerTypes = capabilitiesMatcher.playersWith(AUDIO_DESCRIPTION, SUBTITLES);

        assertThat(playerTypes).isEqualTo(Collections.singletonList(PlayerType.EXO_PLAYER));
    }

    @Test
    public void givenUnableToMatchCapabilities_whenMatching_thenReturnsEmptyList() {
        List<PlayerType> playerTypes = capabilitiesMatcher.playersWith(WIDEVINE_CLASSIC, SUBTITLES);

        assertThat(playerTypes).isEmpty();
    }
}
