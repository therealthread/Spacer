package me.anemys.aneplayercosmetics.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Spacer {

    private static final Map<Integer, Character> POSITIVE_SPACES = new LinkedHashMap<>();
    private static final Map<Integer, Character> NEGATIVE_SPACES = new LinkedHashMap<>();
    private static final Map<SpaceType, Map<Integer, String>> CACHE = new HashMap<>();

    static {

        CACHE.put(SpaceType.POSITIVE, new HashMap<>());
        CACHE.put(SpaceType.NEGATIVE, new HashMap<>());

        @SuppressWarnings("unused")
        final String jsonProvider =
                """
                {
                    "providers": [
                		{
                			"type": "space",
                			"advances": {
                				" ": 4,
                				" ": 0,
                				"\uEFF0": -1,
                				"\uEFF1": -2,
                				"\uEFF2": -4,
                				"\uEFF3": -8,
                				"\uEFF4": -16,
                				"\uEFF5": -32,
                				"\uEFF6": -64,
                				"\uEFF7": -128,
                				"\uEFE0": 1,
                				"\uEFE1": 2,
                				"\uEFE2": 4,
                				"\uEFE3": 8,
                				"\uEFE4": 16,
                				"\uEFE5": 32,
                				"\uEFE6": 64,
                				"\uEFE7": 128
                			}
                		}
                    ]
                }
                """;

        POSITIVE_SPACES.put(128, '\uEFE7');
        POSITIVE_SPACES.put(64, '\uEFE6');
        POSITIVE_SPACES.put(32, '\uEFE5');
        POSITIVE_SPACES.put(16, '\uEFE4');
        POSITIVE_SPACES.put(8, '\uEFE3');
        POSITIVE_SPACES.put(4, '\uEFE2');
        POSITIVE_SPACES.put(2, '\uEFE1');
        POSITIVE_SPACES.put(1, '\uEFE0');

        NEGATIVE_SPACES.put(128, '\uEFF7');
        NEGATIVE_SPACES.put(64, '\uEFF6');
        NEGATIVE_SPACES.put(32, '\uEFF5');
        NEGATIVE_SPACES.put(16, '\uEFF4');
        NEGATIVE_SPACES.put(8, '\uEFF3');
        NEGATIVE_SPACES.put(4, '\uEFF2');
        NEGATIVE_SPACES.put(2, '\uEFF1');
        NEGATIVE_SPACES.put(1, '\uEFF0');
        
    }

    public static String getSpace(int i, SpaceType type) {
        return build(i, type);
    }

    private static String build(int i, SpaceType type) {

        Map<Integer, String> typeCache = CACHE.get(type);

        if (typeCache.containsKey(i)) {
            return typeCache.get(i);
        }

        StringBuilder builder = new StringBuilder();
        Map<Integer, Character> spaceMap = (type == SpaceType.POSITIVE) ? POSITIVE_SPACES : NEGATIVE_SPACES;

        int abs = Math.abs(i);

        for (Map.Entry<Integer, Character> entry : spaceMap.entrySet()) {
            int unit = entry.getKey();
            char symbol = entry.getValue();

            while (abs >= unit) {
                builder.append(symbol);
                abs -= unit;
            }
        }

        typeCache.put(i, builder.toString());
        return typeCache.get(i);
    }

    public enum SpaceType {
        NEGATIVE,
        POSITIVE
    }
}
