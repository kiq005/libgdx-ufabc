# libgdx-ufabc

TODO:
- [x] Create a JSON based asset loader to control game objects loading by level
- [x] Create a test map
- [x] Create a base player character
- [x] Create a base enemy
- [x] Implement auto-generated dungeons
- [ ] Implement combat system
- [ ] Create main map

## Model Manager
The model manager class manage assets loading and unloading, based on a `.json` config file.
To use it, create a configuration file on `assets/Configs/AssetsBundles/` with a name reference to the object and the path to the `.g3db` model, then, call `loadBundle` with the config name. The class will load all necessary models and unload anything that is unused.

After loading the models, to get the **Game Object** just pass the name reference to `getModel`.

## Links
- Hero:
https://free3d.com/3d-model/momon-636489.html


Link- fbxconv-gui
https://github.com/ASneakyFox/libgdx-fbxconv-gui

