# Turok-Framework
Wrapper with utils to help modding in Minecraft forge 1.12.2.
Turok-Framework use the suffix `framework`, but it is a wrapper and not a framework.

# Render
- To initialise OpenGL helper follow the steps:

Invoke init function from TurokRenderGL at some constructor/one tick method.
`TurokRenderGL.init()`

There is a chance to happens nullpointer except when you use scissor test or the gradient fade effect render;
To fix you just need init the TurokMouse and TurokDisplay, create TurokDisplay and TurokMouse wrapper.
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

# Hardware
- Mouse:
```
TurokMouse mouse = new TurokMouse(int x, int y);
```

# Utils
- TurokRect: Simple rect with pos and size;
- TurokMath: Math operations;
- TurokClass: Reflection utils;
- TurokDisplay: Display util, (require Minecraft forge);
- TurokTick: Timing utils;
- TurokGeneric: Simple generic object;
