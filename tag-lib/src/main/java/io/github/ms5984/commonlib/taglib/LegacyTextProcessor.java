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

/**
 * Processes legacy codes in a text string.
 *
 * @since 0.0.1
 */
public interface LegacyTextProcessor {
    /**
     * Replace all legacy codes in the input string with the respective
     * MiniMessage tags, applying overrides to cancel out Minecraft's
     * default display name styling (italics).
     *
     * @return a processed string
     * @see #prependProcess(String)
     */
    default @NotNull String displayNameOverride() {
        return prependProcess("<!i>"); // override MC-default italics
    }

    /**
     * Replace all legacy codes in the input string with the respective
     * MiniMessage tags, applying overrides to cancel out Minecraft's
     * default lore line styling (italics, dark_purple).
     *
     * @return a processed string
     * @see #prependProcess(String)
     */
    default @NotNull String loreLineOverride() {
        return prependProcess("<!i><white>"); // override MC-default italics + dark_purple
    }

    /**
     * Replace all legacy codes in the input string
     * with the respective MiniMessage tags.
     *
     * @return a processed string
     */
    @NotNull String process();

    /**
     * Replace all legacy codes in the input string with the respective
     * MiniMessage tags, prepending a given set of overrides.
     *
     * @param overrides MiniMessage tags to prepend
     * @return a processed string
     */
    @NotNull String prependProcess(@NotNull String overrides);
}
