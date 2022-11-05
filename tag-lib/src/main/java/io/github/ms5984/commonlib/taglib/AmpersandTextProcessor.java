package io.github.ms5984.commonlib.taglib;
/*
 *  Copyright 2022 ms5984
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.ms5984.commonlib.taglib.TagLib.tagFromLegacy;

/**
 * Processes ampersand codes in a text string.
 *
 * @since 0.0.1
 */
public final class AmpersandTextProcessor implements LegacyTextProcessor {
    /**
     * Matches all ampersand-based color, format, and hex codes.
     */
    public static final Pattern COLOR_PATTERN = Pattern.compile("&([a-fA-F\\dklmnor]|#[a-fA-F\\d]{6})");
    final String input;

    AmpersandTextProcessor(@NotNull String input) {
        this.input = input;
    }

    @Override
    public @NotNull String process() {
        return process_(null);
    }

    @Override
    public @NotNull String prependProcess(@NotNull String overrides) {
        return process_(overrides);
    }

    private @NotNull String process_(String overrides) {
        if (input.isEmpty() && (overrides == null || overrides.isEmpty())) return ""; // no input, no/empty overrides; return empty string
        final var match = COLOR_PATTERN.matcher(input);
        final StringBuilder sb = overrides != null ? new StringBuilder(overrides) : new StringBuilder(); // only prepend overrides if they exist
        final HashSet<String> activeFormats = new HashSet<>();
        // search for legacy codes
        while (match.find()) {
            final var group = match.group(1);
            switch (group.toLowerCase(Locale.ROOT).charAt(0)) {
                // add format to active formats if necessary
                case 'k', 'l', 'm', 'n', 'o' -> activeFormats.add(group);
                // if we find a color code, close all active formats
                case 'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '#' -> {
                    match.appendReplacement(sb, activeFormats.stream()
                            .map(format -> tagFromLegacy(format, true))
                            .collect(Collectors.joining()) + tagFromLegacy(group, false));
                    activeFormats.clear();
                    continue;
                }
            }
            match.appendReplacement(sb, tagFromLegacy(group, false));
        }
        match.appendTail(sb);
        return sb.toString();
    }
}
