# Turok-Framework
 Frameworker helper for anarchy client.

# Render;
- To init OpenGL helper in your Minecraft mod/client, follow the steps:

In A constructor where you can init the TurokRenderGL:
`TurokRenderGL.init()`

There is a chance happens nullpointer except when you use scissor test or the shading effect with fade render;
To fix you just need init the TurokMouse and TurokDisplay; OBS: Create in one loop;
```
TurokDisplay display = new TurokDisplay(Minecraft mc);
TurokMouse mouse = new TurokMouse(int x, int y);
```

- Font:
Simple font system, first create one TurokFont (the real owner of font is Hal);
```
TurokFont font = new TurokFont(Font font, bool alias, bool matric);

TurokFontManager.render(TurokFont font, int x, int y, bool shadow, Color color);
```

- Image:
Error;

# Hardware:
- Mouse:
```
TurokMouse mouse = new TurokMouse(int x, int y);
```

# Utils;
- TurokRect: Simple rect with coordinates and siez;
- TurokMath: A lot math functions;
- TurokClass: Reflection utils;
- TurokDisplay: Display util, (require Minecraft);
- TurokTick: Time utils to count or ticks;
- TurokGeneric: Simple generic value;
