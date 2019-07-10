# NbScratchFile - Scratch files for Netbeans
![License](https://img.shields.io/github/license/Chris2011/NbScratchFile.svg)
![Release](https://img.shields.io/github/release/Chris2011/NbScratchFile.svg)
![Contributors](https://img.shields.io/github/contributors/Chris2011/NbScratchFile.svg)
![Downloads](https://img.shields.io/github/downloads/Chris2011/NbScratchFile/total.svg)


## Hint
(Required JDK 8 > 1.8.0_122 - <a href="https://github.com/Chris2011/NbScratchFile/issues/1#issuecomment-331884374">#1</a>) <a href="https://travis-ci.org/Chris2011/NbScratchFile"><img src="https://travis-ci.org/Chris2011/NbScratchFile.svg?branch=develop" /></a>


## Description
With this plugin you can create scratch files which will be saved on disk. You don't need a project for this. After typing `Ctrl + Alt + Shift + N` you can choose a language and the file will be saved to your user dir: `/userDir/.netbeans/scratches`

The idea came up while using Sublime Text 2, where you can type `Ctrl + N` to creates an empty file.
This is really simple, it will open a new tab with a Untitled file. Nothins special.

IntelliJ has a similar but more powerful functionalty too: <a href="http://blog.jetbrains.com/idea/2014/09/intellij-idea-14-eap-138-2210-brings-scratch-files-and-better-mercurial-integration/">IntelliJ IDEA 14 eap 138.2210 brings scratch files...</a>. 

So my plugin is more like the IntelliJ functionaliy, because you will choose the target language after hitting `Ctrl + Shift + Alt + N` and it will open and automatically
save the file to your user `/userDir/.netbeans/scratches`. You can see all the saved scratches as nodes at `Services -> Scratches`


## Screenshots
### `File -> New Scratch File` or `Ctrl + Alt + Shift + N`
![Alt text](/screenshots/NbScratchFile.jpg?raw=true)


### Choose target language
![Alt text](/screenshots/ChooseLanguage.gif?raw=true)


### Service node integration
![Alt text](/screenshots/ServiceNodeIntegration.gif?raw=true)


## Changelog
See [Changelog](./Changelog.md) for all information 


Plugin is available at http://plugins.netbeans.org/plugin/58020/nbscratchfile