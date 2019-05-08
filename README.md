# Frame VM
> A VM using the scopes as frames paradigm described by [Poulsen et al.](http://drops.dagstuhl.de/opus/volltexte/2016/6114/)

For an overview of available instructions, see the `docs` folder.

## Importing Stacy
Include the following in your projects `pom.xml`:
```XML
<dependencies>
  <dependency>
    <groupId>org.metaborg.lang</groupId>
    <artifactId>framevm-stacy</artifactId>
    <version>1.0.0</version>
    <type>spoofax-language</type>
  </dependency>
</dependencies>
```

Include the following in your `metaborg.yaml`:
```yaml
dependencies:
  compile:
  - org.metaborg.lang:framevm-stacy:1.0.0
```

The following files contain the definitions you might want to use when compiling to Stacy:
- `signatures/framevm-stacy-sig`: Signatures of Stacy constructs
- `signatures/fvm-common-sig`: Signatures of the Frame VM
- `stc-common`: Common (desugared) constructors and usefull functions when operating on Stacy ASTs:
  - `pp-framevm-stacy`: Pretty-print a Stacy AST
  - `eval-framevm-stacy`: Run a Stacy AST (Note: for this to work you need to copy over the java strategies and register them)
  - `eval-framevm-stacy-debug`: Run a Stacy AST (Note: for this to work you need to copy over the java strategies and register them)
- `stc-util`: Helper functions when compiling to Stacy. See the `docs` folder for a more detailed description
  - `stc-from-flat`: Build a valid Stacy AST from a list of instructions
  - `framevm-path-from-nabl2`: Get an access path from an Nabl2 reference

If you need other strategies from other files, please notify me such that I can add them to these files. This as any other file might change in the future.

### Linking the standard library
Stacy includes a number of functions in a library like string concatenation and string printing.

To make use of this library, you have to make the files visible to importing files. There are two ways to do this: The first is to simply copy over the `stdlib` folder from `framevm-stacy.lib` to the root of your project that contains the Stacy programs. This method is not encouraged as you need to manually update the library when it is updated. You might notice that making a symlink fixes this issue, sadly these don't work nicely in combination with Eclipse. The second solution is to select your project in Eclipse, add a Spoofax nature (right click -> Spoofax-meta -> add Spoofax nature), open its properties (project -> properties), navigate to the 'Java build path' page and click `link source`. In the dialog, select the `framevm-stacy.lib/stdlib`-folder and give it the name 'stdlib'. On this same page you might see `src` as an other linked source, this one must be removed. Stacy programs now should be able to import functions from the library.

## Importing Roger
> Work in progress
