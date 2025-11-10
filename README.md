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
