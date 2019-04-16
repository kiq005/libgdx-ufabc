# libgdx-ufabc

TODO:
- [ ] Create a JSON based asset loader to control game objects loading by level
- [ ] Create a test map
- [ ] Create a base player character
- [ ] Create a base enemy
- [ ] Implement auto-generated dungeons
- [ ] Implement combat system
- [ ] Create main map

## Model Manager
The model manager class manage assets loading and unloading based on a `.json` config file.
To use it, create a configuration file on `assets/Configs/AssetsBundles/` with a named object and the path to the `.g3db` mode, then, call `loadBundle` with the config name. The class will load all necessary models and unload anything that is unused.
