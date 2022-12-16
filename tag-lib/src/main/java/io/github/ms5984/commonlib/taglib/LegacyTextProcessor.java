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
     * Replaces each legacy code in the input string with an equivalent
     * MiniMessage tag and prepends a tag to cancel out Minecraft's
     * default display name styling of italics.
     *
     * @return a processed string
     * @implSpec Display names are rendered by default with {@code italic}
     * styles in Minecraft. Implementations should use one or more MiniMessage
     * tags to cancel out that styling.
     * @implNote The default implementation uses {@code <!i>}.
     * @see #prependProcess(String)
     */
    default @NotNull String displayNameOverride() {
        return prependProcess("<!i>"); // override MC-default italics
    }

    /**
     * Replaces each legacy code in the input string with an equivalent
     * MiniMessage tag and prepends tags to cancel out Minecraft's
     * default lore line styling of italics and dark purple color.
     *
     * @return a processed string
     * @implSpec Lore (and thus each line) are rendered by default with
     * {@code italic}, {@code dark_purple} styles in Minecraft.
     * Implementations should use one or more MiniMessage tags to cancel out
     * that styling.
     * @implNote The default implementation uses {@code <!i><white>}.
     * @see #prependProcess(String)
     */
    default @NotNull String loreLineOverride() {
        return prependProcess("<!i><white>"); // override MC-default italics + dark_purple
    }

    /**
     * Replaces each legacy code in the input string with an equivalent
     * MiniMessage tag.
     *
     * @return a processed string
     */
    @NotNull String process();

    /**
     * Replaces each legacy code in the input string with an equivalent
     * MiniMessage tag and prepends an arbitrary string {@code prefix}
     * contain additional tags to assist processing.
     *
     * @param prefix MiniMessage tags to prepend
     * @return a processed string
     */
    @NotNull String prependProcess(@NotNull String prefix);
}
