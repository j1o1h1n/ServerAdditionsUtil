# ServerAdditionsUtil
A utility that allows for easy server-side-only additions to the game.

**This project is still very much a work in progress and as such nothing is final! Literally *everything* is still subject to major changes!**

## Maven
```gradle
repositories {
    maven { url "https://minecraft.curseforge.com/api/maven"}
}
dependencies {
    modCompile "serveradditionsutil:ServerAdditionsUtil:{VERSION}"
}
```

## Usage
For basic usage, simply create new instances of or extend `ServerSideBlock`, `ServerSideBlockItem` and `ServerSideItem`. The blocks/items created will be able to be used by unmodded clients. The names and recipes of these blocks/items should be specified just like any other blocks/items.

For more advanced usage, Blocks may implement the interface `ClientBlockStateProvider` and Items may implement the interface `ClientItemStackProvider`. This should have the same effect.

You may also take a look at the `testing` branch of this repository to see an example implementation.

## Known issues
There are still many issues, but here are some that I already know of:
* Picking up an item stack of a modded item in the creative inventory turns the item into what the client actually sees (**WIP**)
* Just opening the creative inventory while in possesion of a modded block renders the block useless (**WIP**)
* Picking a block (middle click) in creative just gives you the vanilla version
* Using the added blocks/items in commands doesn't allow for auto-complete (**WIP**)
* Command feedback for added blocks/items doesn't give the translated name (**WIP**)
* Blocks with different strength to their representation don't have correct progress on client who's breaking block (**Partially solved, probably impossible to fully solve**)

## Features being considered
* Allow blocks/items to be displayed without the vanilla variant on clients with the mod installed