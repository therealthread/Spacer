# Spacer
Create negative and positive spaces for Minecraft resource packs

```java
String a Spacer.getSpace(16, Spacer.SpaceType.POSITIVE);
String b Spacer.getSpace(128, Spacer.SpaceType.NEGATIVE);
```


# With Kyori Adventure
```java

    import net.kyori.adventure.text.Component;
    import net.kyori.adventure.text.minimessage.MiniMessage;

    public static Component getSpace(@Subst("a-z0-9_.-") String font, int i, SpaceType type) {
        return MiniMessage.miniMessage().deserialize("<font:minecraft:" + font +">" + build(i, type) + "</font>");
    }
```

# PlaceholderAPI
```java

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpacerExpansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "spacer";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Anemys";
    }

    @Override
    @Using(info = "%spacer_128_negative%")
    public String onPlaceholderRequest(Player player, @NotNull String params) {

        String[] split = params.split("_");
        if (split.length != 2) return "";

        try {
        
            int amount = Integer.parseInt(split[0]);
            Spacer.SpaceType type = Spacer.SpaceType.valueOf(split[1].toUpperCase());

            return Spacer.getSpace(amount, type);
            
        } catch (Exception e) {
            return "";
        }
        
    }
}
```
