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
 * Text utilities for Kyori Adventure MiniMessage.
 *
 * @since 0.0.1
 */
public class TagLib {
    /**
     * Process a string, replacing its ampersand color and format codes
     * with the appropriate MiniMessage tags.
     *
     * @param input an input string
     * @return a legacy text processor for ampersand codes
     */
    public static LegacyTextProcessor ampersand(@NotNull String input) {
        return new AmpersandTextProcessor(input);
    }

    /**
     * Convert an ampersand code to its respective MiniMessage tag.
     * <p>
     * Supports legacy color codes, legacy format codes, and hex colors.
     * <p>
     * Hex colors must be in the format {@code &#RRGGBB}.
     *
     * @param code the ampersand code
     * @param close false for an opening tag, true for a closing tag
     * @return a MiniMessage tag
     */
    public static String tagFromAmpersand(String code, boolean close) {
        final StringBuilder sb = new StringBuilder("<");
        if (close) sb.append('/');
        if (code.length() == 7) {
            // hex code
            return sb.append(code).append(">").toString();
        } else if (code.length() != 1) {
            // invalid code
            return "";
        }
        return sb.append(switch (code.charAt(0)) {
            case '0' -> "black";
            case '1' -> "dark_blue";
            case '2' -> "dark_green";
            case '3' -> "dark_aqua";
            case '4' -> "dark_red";
            case '5' -> "dark_purple";
            case '6' -> "gold";
            case '7' -> "gray";
            case '8' -> "dark_gray";
            case '9' -> "blue";
            case 'a' -> "green";
            case 'b' -> "aqua";
            case 'c' -> "red";
            case 'd' -> "light_purple";
            case 'e' -> "yellow";
            case 'f' -> "white";
            case 'k' -> "obfuscated";
            case 'l' -> "bold";
            case 'm' -> "strikethrough";
            case 'n' -> "underline";
            case 'o' -> "italic";
            case 'r' -> "reset";
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }).append(">").toString();
    }
}
